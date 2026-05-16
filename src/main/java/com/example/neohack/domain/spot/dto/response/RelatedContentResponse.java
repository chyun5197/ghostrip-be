package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.RelatedContent;
import lombok.Getter;

@Getter
public class RelatedContentResponse {
    private String relatedContentId;
    private String title;
    private String thumbUrl;

    public static RelatedContentResponse from(RelatedContent relatedContent) {
        RelatedContentResponse response = new RelatedContentResponse();
        response.relatedContentId = relatedContent.getRelatedContentId();
        response.title = relatedContent.getTitle();
        response.thumbUrl = relatedContent.getThumbUrl();
        return response;
    }
}
