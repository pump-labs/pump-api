package com.example.holaserver.Item;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Item.DTO.ItemSaveBody;
import com.example.holaserver.Item.DTO.ItemUpdateBody;
import com.example.holaserver.Store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final StoreService storeService;
    private final AuthService authService;

    @Transactional
    public Map<String, Object> saveItems(Long storeId, ItemSaveBody[] itemSaveBodies, Boolean isReady) {
        if (!storeService.existStoreById(storeId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        List<Item> items = Arrays.stream(itemSaveBodies).map(item -> {
            if (item.getId() == 0)
                return item.createSaveItemBuilder(storeId, isReady);
            else
                return item.updateSaveItemBuilder(storeId, isReady, item.getId());
        }).collect(Collectors.toList());
        ModelAndView result = new ModelAndView();
        List<Long> itemIds = itemRepository.saveAll(items).stream().map(Item::getId)
                .collect(Collectors.toList());
        result.addObject("itemIds", itemIds);
        storeService.updateStoreStatusById(storeId, isReady);
        return result.getModel();
    }

    @Transactional
    public Map<String, Object> updateItems(Long storeId, ItemUpdateBody[] itemUpdateBodies) {
        ModelMap result = new ModelMap();
        if (!storeService.existStoreById(storeId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        List<Item> deleteTargetItems = itemRepository.findItemsByStoreId(storeId);
        for (Item item : deleteTargetItems) item.setRemovedAt(new Timestamp(System.currentTimeMillis()));
        List<Item> items = Arrays.stream(itemUpdateBodies).map(item -> item.updateItemBuilder(storeId)).collect(Collectors.toList());
        List<Long> itemIds = itemRepository.saveAll(items).stream().map(Item::getId).collect(Collectors.toList());
        return result.addAttribute("result", itemIds);
    }

    public List<Item> findByStoreId(Long storeId) {
        authService.getPayloadByToken();
        if (!storeService.existStoreById(storeId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 가게입니다 \uD83D\uDE2D  \\n 다른 가게를 이용해 주세요.");
        return itemRepository.findItemsByStoreIdAndIsReady(storeId, true);
    }

    public Boolean existsByStoreId(Long storeId){
        return itemRepository.existsByStoreIdAndRemovedAtIsNull(storeId);
    }
}
