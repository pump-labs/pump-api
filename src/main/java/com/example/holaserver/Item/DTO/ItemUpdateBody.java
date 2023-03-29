package com.example.holaserver.Item.DTO;


import com.example.holaserver.Item.Enum.Unit;
import com.example.holaserver.Item.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemUpdateBody {
    private String title;
    private Long price;
    private Unit unit;
    private String brand;
    private String category;
    private String imgPath;
    private Boolean isReady;

    public Item updateItemBuilder(Long storeId) {
        return Item.builder()
                .storeId(storeId)
                .title(title)
                .price(price)
                .unit(unit)
                .brand(brand)
                .category(category)
                .imgPath(imgPath)
                .isReady(true)
                .build();
    }
}
