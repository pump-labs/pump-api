package com.example.holaserver.Review.DTO;

import com.example.holaserver.Review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private Long userId;
    private Long storeId;
    private String reviewText;

    public Review createSaveStoreBuilder(){
        return Review.builder()
                .storeId(storeId)
                .userId(userId)
                .reviewText(reviewText)
                .build();
    }
}
