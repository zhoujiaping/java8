package java8.chapter4datetime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class DateTimeTest {

    @Test
    public void demo1_1() throws InterruptedException {
        // 瞬间
        System.out.println("瞬间");
        Instant instant = Instant.now();
        Thread.sleep(10);
        boolean isBefore = instant.isBefore(Instant.now());
        System.out.println(isBefore);
        
        //日期
        System.out.println("日期");
        LocalDate date = LocalDate.now();
        System.out.println(date);//打印2017-03-06 说明重写了toString方法
        date = LocalDate.of(2017, 3, 8);
        System.out.println(date);
        //时间
        System.out.println("时间");
        LocalTime time = LocalTime.now();
        System.out.println(time);
        time = LocalTime.of(16, 7, 30);
        System.out.println(time);
        time = time.plusHours(2);
        System.out.println(time);
        //日期时间
        System.out.println("datetime");
        LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime);
        datetime = LocalDateTime.of(2017, 3, 6, 16, 9, 10);
        datetime = datetime.plusMinutes(15);
        System.out.println(datetime);
        //日期校正器
        System.out.println("日期校正器");
        date = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));//下个礼拜星期天
        System.out.println(date);
        //格式化和解析 如果不提供格式化对象，会按默认格式解析和格式化
        System.out.println("格式化和解析");
        datetime = LocalDateTime.parse("20170306161450", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(datetime);//2017-03-06T16:14:50
        String s = datetime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println(s);
    }
}
