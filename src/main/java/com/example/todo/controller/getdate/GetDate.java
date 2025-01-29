package com.example.todo.controller.getdate;
import java.util.Calendar;
import java.util.List;

public class GetDate {
    //今日の日付
    public static List<Integer> getToday(){
        Calendar cal = Calendar.getInstance();
        int today_year = cal.get(Calendar.YEAR);
        int today_month = cal.get(Calendar.MONTH) + 1;
        int today_day = cal.get(Calendar.DATE);
        return List.of(today_year,today_month,today_day);
    };

    //明日の日付
    public static List<Integer> getTomorror(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        int tomorror_year = cal.get(Calendar.YEAR);
        int tomorror_month = cal.get(Calendar.MONTH) + 1;
        int tomorror_day = cal.get(Calendar.DATE);
        return List.of(tomorror_year,tomorror_month,tomorror_day);
    };
}
