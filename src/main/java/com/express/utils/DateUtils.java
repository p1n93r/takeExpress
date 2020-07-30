package com.express.utils;

import java.util.Date;

public class DateUtils {
    /** 比较两个时间相差秒 */	
    public static double calculatetimeGapSecond(Date date1, Date date2) {
        double second = 0;
        double millisecond = date2.getTime() - date1.getTime();
        second = millisecond / (1000);
        return second;
    }

}
