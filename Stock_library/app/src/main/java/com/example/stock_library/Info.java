package com.example.stock_library;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.Serializable;

class Info implements Serializable{
    String name;
    int buy_price;
    int sell_price;
    String day;

    Info(String n, int b) {
        name = n;
        buy_price = b;
        sell_price = 0;
        //LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        day = sdf.format(new Date());

    }

    Info(String n, int b, int s, String d) {
        name = n;
        buy_price = b;
        sell_price = s;
        day = d;

    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Info other = (Info) obj;

        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        // equals で使うフィールドと同じものを使う
        return java.util.Objects.hash(name);
    }


    //setter,getter等
    void set_buy(int x) {
        buy_price = x;
    }

    void set_sell(int y) {
        sell_price = y;
    }

    void set_name(String n) {
        name = n;
    }

    int get_buy() {
        return buy_price;
    }

    int get_sell() {
        return sell_price;
    }

    String get_name() {
        return name;
    }

    String get_day() {
        return day;
    }
}