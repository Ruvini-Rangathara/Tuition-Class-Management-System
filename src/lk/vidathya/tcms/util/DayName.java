package lk.vidathya.tcms.util;

import java.time.LocalDate;

public class DayName {

    static int d;
    static int m;
    static int y;

    static int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] days = {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    public static String getDayName(String date) {

        //String date = String.valueOf(LocalDate.now());

        y = Integer.parseInt(date.split("-")[0]);
        m = Integer.parseInt(date.split("-")[1]);
        d = Integer.parseInt(date.split("-")[2]);

        //Checking if the inputted year is a leap year or not
        if (y % 400 == 0 || (y % 100 != 0 && y % 4 == 0)) {
            month[2] = 29;
        }

        String s = days[6];

        if (y == 2023) {
            s = days[7];
        }
        if (y == 2024) {
            s = days[1];
        }
        if (y == 2025) {
            s = days[3];
        }
        if (y == 2026) {
            s = days[4];
        }
        if (y == 2027) {
            s = days[5];
        }
        if (y == 2028) {
            s = days[6];
        }
        if (y == 2029) {
            s = days[1];
        }
        if (y == 2030) {
            s = days[2];
        }
        if (y == 2031) {
            s = days[3];
        }
        if (y == 2032) {
            s = days[4];
        }
        if (y == 2033) {
            s = days[6];
        }
        if (y == 2034) {
            s = days[7];
        }
        if (y == 2035) {
            s = days[1];
        }
        if (y == 2036) {
            s = days[2];
        }
        if (y == 2037) {
            s = days[4];
        }
        if (y == 2038) {
            s = days[5];
        }
        if (y == 2039) {
            s = days[6];
        }
        if (y == 2040) {
            s = days[7];
        }
        if (y == 2041) {
            s = days[2];
        }
        if (y == 2042) {
            s = days[3];
        }
        if (y == 2043) {
            s = days[4];
        }
        if (y == 2044) {
            s = days[5];
        }
        if (y == 2045) {
            s = days[7];
        }
        if (y == 2046) {
            s = days[1];
        }
        if (y == 2047) {
            s = days[2];
        }
        if (y == 2048) {
            s = days[3];
        }
        if (y == 2049) {
            s = days[5];
        }
        if (y == 2050) {
            s = days[6];
        }

        int dn = 0; // day number

        for (int i = 0; i < m; i++) {
            dn += month[i];
        }

        dn += d; //final day number after adding all the months and dates

        int x = 0; // position of day  name of first jan in the "days" array
        for (int i = 1; i <= 7; i++) {
            if (s.equalsIgnoreCase(days[i])) {
                x = i;
            }
        }

        for (int i = 1; i < dn; i++) {
            x++;
            if (x == 8) {
                x = 1;
            }
        }

        return days[x];
    }
}
