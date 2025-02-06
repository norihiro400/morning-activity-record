package com.example.todo.controller.getdate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetDate {
    //今日の日付
    public static String getToday(){
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern("M月d日"));
    };

    //明日の日付
    public static String getTomorror(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tomorrow.format(DateTimeFormatter.ofPattern("M月d日"));
    };
}
