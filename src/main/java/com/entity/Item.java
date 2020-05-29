package com.entity;

import lombok.Data;

/**
 *商品实体类
 *@date 2019/8/10
 *@author danielHua
 */
@Data
public class Item
{
    private Long id;

    private String title;

    private String pic;

    private String desc;

    private Long price;

    public Item() {

    }

    public Item(long id, String title, String pic, String desc, Long price) {
        this.id=id;
        this.title=title;
        this.pic=pic;
        this.desc=desc;
        this.price=price;
    }
}
