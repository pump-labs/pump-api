package com.example.holaserver.Store.RequestStore;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Item.ItemService;
import com.example.holaserver.Store.RequestStore.DTO.StoreRequestBody;
import com.example.holaserver.Store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RequestStoreService {
    private final RequestStoreRepository requestStoreRepository;
    private final StoreService storeService;

    private final ItemService itemService;
    private final AuthService authService;

    public Map<String, Object> findStoreRequest(Long storeId){
        ModelMap result = new ModelMap();

        Long userId = authService.getPayloadByToken();

        Boolean isEntered = itemService.existsByStoreId(storeId);
        Boolean isRequested = requestStoreRepository.existsByStoreIdAndUserId(storeId, userId);
        Long requestStoreCount = requestStoreRepository.countByStoreIdAndRemovedAtIsNull(storeId);

        result.addAttribute("isEntered", isEntered);
        result.addAttribute("isRequested", isRequested);
        result.addAttribute("count", requestStoreCount);
        return result;
    }

    @Transactional
    public Map<String, Object> saveStoreRequest(StoreRequestBody storeRequestBody){
        ModelMap result = new ModelMap();

        Long storeId = storeRequestBody.getStoreId();
        Long userId = authService.getPayloadByToken();

        if (!storeService.existStoreById(storeId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        if (requestStoreRepository.existsByStoreIdAndUserId(storeId, userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 입점을 신청한 가게입니다 \uD83D\uDE1C");
        }

        Long requestStoreId = requestStoreRepository.save(new StoreRequestBody().storeRequestBody(storeId, userId)).getId();
        Boolean isRequested = requestStoreRepository.existsByStoreIdAndUserId(storeId, userId);
        Long requestStoreCount = requestStoreRepository.countByStoreIdAndRemovedAtIsNull(storeId);

        result.addAttribute("RequestStoreId", requestStoreId);
        result.addAttribute("isRequested", isRequested);
        result.addAttribute("count", requestStoreCount);
        return result;
    }

    @Transactional
    public Map<String, Object> removeStoreRequest(StoreRequestBody storeRequestBody){
        ModelMap result = new ModelMap();

        Long storeId = storeRequestBody.getStoreId();
        Long userId = authService.getPayloadByToken();

        if (!storeService.existStoreById(storeId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        if (!requestStoreRepository.existsByStoreIdAndUserId(storeId, userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 완료된 요청입니다 \uD83D\uDE09 ");
        }

        RequestStore requestStore = requestStoreRepository.findByStoreIdAndUserId(storeId, userId);
        requestStore.removeStoreRequest();

        Boolean isRequested = requestStoreRepository.existsByStoreIdAndUserId(storeId, userId);
        Long requestStoreCount = requestStoreRepository.countByStoreIdAndRemovedAtIsNull(storeId);
        result.addAttribute("isRequested", isRequested);
        result.addAttribute("count", requestStoreCount);
        return result;
    }

}
