package com.example.neohack.global.fal;

import com.example.neohack.global.s3.S3Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class FalAiService {

    @Value("${fal.ai.api-key}")
    private String apiKey;

    private final S3Service s3Service;
    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String COMPOSITE_PROMPT = """
            You are given two reference images.
            Image 1: a portrait photo of a specific person — preserve this person's face identity exactly.
            Image 2: an outdoor location (a haunted spot scene) — preserve this background scenery exactly.

            Task: Produce one new photorealistic image that places the person from Image 1 into the
            scene of Image 2. The person must stand prominently in the foreground center of the
            scene, occupying roughly 35-45% of the image height (large and clearly visible, NOT tiny).
            The person's face MUST be recognizably the same person as in Image 1 — keep facial
            features, hairstyle and skin tone identical. The background scenery must remain the
            exact same haunted spot as in Image 2 (same rocks, water, waterfall, trees, composition).

            After placing the person, apply a strong dark, gloomy, desaturated color grading to the
            ENTIRE image (both person and background) — low-light, horror-movie atmosphere, like
            a paranormal photograph taken at dusk. The result should feel ominous, creepy, eerie.

            Output exactly one photorealistic image. Do not omit the person. Do not change the
            person's identity. Do not replace the background.
            """;

    public String convertImage(String personImageUrl, String spotImageUrl) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("prompt", COMPOSITE_PROMPT);
        body.put("image_urls", new String[]{personImageUrl, spotImageUrl});
        body.put("image_size", "landscape_16_9");
        body.put("num_images", 1);
        body.put("max_images", 1);
        body.put("enable_safety_checker", true);

        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(body),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://fal.run/fal-ai/bytedance/seedream/v4/edit")
                .header("Authorization", "Key " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            log.info("[fal.ai seedream composite] status={} body={}", response.code(), responseBody);
            if (!response.isSuccessful()) {
                throw new IllegalStateException("fal.ai composite 호출 실패: " + response.code());
            }
            JsonNode json = objectMapper.readTree(responseBody);
            return json.get("images").get(0).get("url").asText();
        }
    }

    private static final String MOOD_PROMPT = """
            업로드한 사진을 기반으로 현실적인 심령사진 분위기로 자연스럽게 수정해줘.

            원본 사진의 장소와 구도는 유지하고,
            전체적으로 어둡고 으스스한 분위기를 추가해줘.

            다음 요소들을 자연스럽게 반영해줘:
            - 어두운 조명
            - 푸른빛 또는 회색빛 색감
            - 흐릿한 안개
            - 오래된 카메라로 찍은 듯한 노이즈
            - 희미한 그림자
            - 기묘한 분위기
            - 귀신이 나올 것 같은 공포 연출
            - 폐가/흉가 느낌
            - 심령사진 같은 현실적인 분위기

            단, 과하게 괴물처럼 만들지 말고
            실제로 찍힌 심령사진처럼 현실감 있게 표현해줘.
            """;

    public String convertMood(String backgroundImageUrl) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("prompt", MOOD_PROMPT);
        body.put("image_urls", new String[]{backgroundImageUrl});
        body.put("image_size", "landscape_16_9");
        body.put("num_images", 1);
        body.put("max_images", 1);
        body.put("enable_safety_checker", true);

        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(body),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://fal.run/fal-ai/bytedance/seedream/v4/edit")
                .header("Authorization", "Key " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            log.info("[fal.ai seedream mood] status={} body={}", response.code(), responseBody);
            if (!response.isSuccessful()) {
                throw new IllegalStateException("fal.ai mood 호출 실패: " + response.code());
            }
            JsonNode json = objectMapper.readTree(responseBody);
            return json.get("images").get(0).get("url").asText();
        }
    }

    private String callFluxImageToImage(String spotImageUrl) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("image_url", spotImageUrl);
        body.put("prompt", "a person standing at this haunted location, full body shot, horror atmosphere, cinematic lighting, photorealistic");
        body.put("negative_prompt", "bad quality, blurry, cartoon, anime, empty scene");
        body.put("strength", 0.65);
        body.put("num_inference_steps", 28);

        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(body),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://fal.run/fal-ai/flux/dev/image-to-image")
                .header("Authorization", "Key " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            log.info("[fal.ai flux i2i] status={} body={}", response.code(), responseBody);
            JsonNode json = objectMapper.readTree(responseBody);
            return json.get("images").get(0).get("url").asText();
        }
    }

    private String callFaceSwap(String targetImageUrl, String sourceImageUrl) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("base_image_url", targetImageUrl);   // 배경+AI사람
        body.put("swap_image_url", sourceImageUrl);   // 실제 인물 사진

        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(body),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://fal.run/fal-ai/face-swap")
                .header("Authorization", "Key " + apiKey)
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            log.info("[fal.ai face-swap] status={} body={}", response.code(), responseBody);
            JsonNode json = objectMapper.readTree(responseBody);
            // face-swap 응답: {"image": {"url": "..."}} 또는 {"images": [...]}
            JsonNode imageNode = json.has("image") ? json.get("image") : json.get("images").get(0);
            return imageNode.get("url").asText();
        }
    }
}
