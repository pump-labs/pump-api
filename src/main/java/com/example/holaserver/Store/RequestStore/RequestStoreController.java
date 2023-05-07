package com.example.holaserver.Store.RequestStore;

import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.Item.ItemService;
import com.example.holaserver.Store.RequestStore.DTO.StoreRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RequestStoreController {
    private final RequestStoreService requestStoreService;

    @GetMapping("/request-store/{storeId}")
    public ResponseTemplate<Map<String, Object>> findStoreRequest(@PathVariable Long storeId){
        return new ResponseTemplate<>(requestStoreService.findStoreRequest(storeId), "입점 요청 불러오기 성공");
    }
    @PostMapping("/request-store")
    public ResponseTemplate<Map<String, Object>> StoreRequestSave(@RequestBody StoreRequestBody storeRequestBody) throws Exception {
        return new ResponseTemplate<>(requestStoreService.saveStoreRequest(storeRequestBody), "입점 요청 성공");
    }

    @DeleteMapping("/request-store")
    public ResponseTemplate<Map<String, Object>> StoreRequestDelete(@RequestBody StoreRequestBody storeRequestBody) throws Exception {
        return new ResponseTemplate<>(requestStoreService.removeStoreRequest(storeRequestBody), "입점 요청 취소 성공");
    }
}
