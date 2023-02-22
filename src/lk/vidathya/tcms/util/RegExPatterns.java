package lk.vidathya.tcms.util;

import java.util.regex.Pattern;

public class RegExPatterns {

    private static Pattern namePattern = Pattern.compile("^[a-zA-Z '.-]{4,}$");
    private static Pattern idPattern = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
    private static Pattern timePattern = Pattern.compile("^(1[0-2]|0?[1-9]).([0-5]?[0-9])(●?[ ][ap]m)?$");
    private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    //private static Pattern salaryPattern = Pattern.compile("[0-9]{3,}$");
    private static Pattern salaryPattern = Pattern.compile("^-?(?:0|[1-9]\\d{0,2}(?:,?\\d{3})*)(?:\\.\\d+)?$");
    private static Pattern mobilePattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
    private static Pattern oldIDPattern = Pattern.compile("^[0-9]{9}[vVxX]$");
    private static Pattern dobPattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
    private static Pattern capacityPattern = Pattern.compile("^\\d{3,}$");
    private static Pattern ratePattern = Pattern.compile("^\\d{1,2}$");
    private static Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]{5,}$");
    private static Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{5,}$");


    public static Pattern getOldIDPattern() {
        return oldIDPattern;
    }
    public static Pattern getDobPattern() {
        return dobPattern;
    }
    public static Pattern getMobilePattern() {
        return mobilePattern;
    }
    public static Pattern getSalaryPattern() {
        return salaryPattern;
    }
    public static Pattern getEmailPattern() {
        return emailPattern;
    }
    public static Pattern getNamePattern() {
        return namePattern;
    }
    public static Pattern getIdPattern() {
        return idPattern;
    }
    public static Pattern getTimePattern() {return timePattern;}
    public static Pattern getCapacityPattern() {return capacityPattern;}
    public static Pattern getRatePattern() {return ratePattern;}
    public static Pattern getUsernamePattern() {return usernamePattern;}
    public static Pattern getPasswordPattern() {return passwordPattern;}


}
