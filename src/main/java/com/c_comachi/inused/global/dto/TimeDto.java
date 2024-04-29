package com.c_comachi.inused.global.dto;

import com.c_comachi.inused.global.Time;

import java.util.Date;

public class TimeDto {
    private String date; // String으로 선언해 줄 것 - "1 일전", "12 시간 전" ..

    public String getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = Time.calculateTime(date);
    }
}
