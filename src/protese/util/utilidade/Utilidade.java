package protese.util.utilidade;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author vinihds
 */
public class Utilidade {

    private static Utilidade unique = null;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdfTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat dataMY = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dataMYTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat hourMin = new SimpleDateFormat("HH:mm");
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.00");

    private static final DateTimeFormatter sdfLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter sdfTimeStampLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter dataMYLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dataMYTimeStampLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Utilidade() {
    }

    public static Utilidade getInstance() {
        if (unique == null) {
            unique = new Utilidade();
        }
        return unique;
    }
    
    public static String decimalFormat(double numero) {
        try {
            return decimalFormat.format(numero);
        } catch (Exception e) {
        }

        return "0";
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    public static String sdf(LocalDateTime data) {
        return data.format(sdfLocalDate);
    }

    public static String sdfTimeStamp(LocalDateTime data) {
        return data.format(sdfTimeStampLocalDate);
    }

    public static String dataMY(LocalDateTime data) {
        return data.format(dataMYLocalDate);
    }

    public static String dataMYTimeStamp(LocalDateTime data) {
        return data.format(dataMYTimeStampLocalDate);
    }
    
    public static LocalDate sdf(String data) {
        return LocalDate.parse(data, sdfLocalDate);
    }

    public static LocalDateTime sdfTimeStamp(String data) {
        return LocalDateTime.parse(data, sdfTimeStampLocalDate);
    }

    public static LocalDate dataMY(String data) {
        return LocalDate.parse(data, dataMYLocalDate);
    }

    public static LocalDateTime dataMYTimeStamp(String data) {
        return LocalDateTime.parse(data, dataMYTimeStampLocalDate);
    }
}
