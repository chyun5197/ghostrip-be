package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.RelatedContent;
import lombok.Getter;

@Getter
public class RelatedContentResponse {
    private Long relatedContentId;
    private String title;
    private String youtubeUrl;
    private String thumbUrl;

    public static RelatedContentResponse from(RelatedContent relatedContent) {
        RelatedContentResponse response = new RelatedContentResponse();
        response.relatedContentId = relatedContent.getRelatedContentId();
        response.title = relatedContent.getTitle();
        response.youtubeUrl = relatedContent.getYoutubeUrl();
        response.thumbUrl = extractYoutubeThumbnail(relatedContent.getYoutubeUrl());
        return response;
    }

    private static String extractYoutubeThumbnail(String url) {
        if (url == null) return null;
        String videoId = null;

        if (url.contains("youtu.be/")) {
            videoId = url.substring(url.indexOf("youtu.be/") + 9).split("[?&]")[0];
        } else if (url.contains("youtube.com/watch")) {
            for (String param : url.split("[?&]")) {
                if (param.startsWith("v=")) {
                    videoId = param.substring(2);
                    break;
                }
            }
        }

        return videoId != null
                ? "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg"
                : null;
    }
}
