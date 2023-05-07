package com.example.holaserver.Store.RequestStore;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Store.RequestStore.DTO.StoreRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RequestStoreController {
    private final RequestStoreService requestStoreService;

    @PostMapping("/requestStore")
    public ResponseTemplate<Map<String, Object>> StoreRequestSave(@RequestBody StoreRequestBody storeRequestBody) throws Exception {
        return new ResponseTemplate<>(requestStoreService.saveStoreRequest(storeRequestBody), "입점 요청 성공");
    }

    @DeleteMapping("/requestStore")
    public ResponseTemplate<Map<String, Object>> StoreRequestDelete(@RequestBody StoreRequestBody storeRequestBody) throws Exception {
        return new ResponseTemplate<>(requestStoreService.removeStoreRequest(storeRequestBody), "입점 요청 취소 성공");
    }
}
