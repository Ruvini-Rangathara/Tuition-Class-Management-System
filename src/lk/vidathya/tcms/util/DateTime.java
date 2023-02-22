package lk.vidathya.tcms.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

   // public Label time;
    private static volatile boolean stop=false;

    public static void timeNow(Label time){
//
//        Thread thread = new Thread(()->{
//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//            while(!stop){
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//
//                final String timeNow = sdf.format(new Date());
//                Platform.runLater(()->{
//                    time.setText(timeNow);
//                });
//            }
//
//        });
//        thread.start();
    }

    public static void localTime(Label time){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event -> time.setText(
                                new SimpleDateFormat("hh:mm").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void dateToday(Label date){

        //Initializing the date formatter
        DateFormat Date = DateFormat.getDateInstance();
        //Initializing Calender Object
        Calendar cals = Calendar.getInstance();
        //Using format() method for conversion
        String currentDate = Date.format(cals.getTime());
        date.setText(currentDate);
        //System.out.println("Formatted Date: " + currentDate);

    }

    public static String setDate(String date){

        String monthName = MonthName.getMonthName(date);
        String dayName = DayName.getDayName(date);
        int year = Integer.parseInt(date.split("-")[0]);
        int day = Integer.parseInt(date.split("-")[2]);

        String today = year+"-"+monthName+"-"+day;
        return today;
    }

}
