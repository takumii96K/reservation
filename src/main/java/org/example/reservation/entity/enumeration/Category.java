package org.example.reservation.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // Lombokを使ってgetterメソッドを自動生成
@AllArgsConstructor
public enum Category {
    FOOD("食品"),
    DRINK("飲料"),
    SIDE("サイド");

    private final String status; // enumを格納するフィールド

}