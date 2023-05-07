package com.example.holaserver.Store.RequestStore.DTO;

import com.example.holaserver.Store.RequestStore.RequestStore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StoreRequestBody {
    private Long storeId;

    public RequestStore storeRequestBody(Long storeId, Long userId){
        return RequestStore.builder()
                .storeId(storeId)
                .userId(userId)
                .build();
    }
}
