package com.example.holaserver.Store.RequestStore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestStoreService {
    private final RequestStoreRepository requestStoreRepository;

    public Boolean checkRequestByUserId(Long storeId, Long userId){
        return requestStoreRepository.existsByStoreIdAndUserId(storeId, userId);
    }

}
