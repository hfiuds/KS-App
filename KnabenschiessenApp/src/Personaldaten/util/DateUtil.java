package Personaldaten.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Hilfsfunktion zum Umgang mit Termindaten.
 * 
 * Autor Reto Caviezel
 */
public class DateUtil {
    
    /** Der Daten pattern ist benötigt zur Umwandlung.
    */
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    
    /** Der Daten formatter. */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    /**
     * Returniert die gut formatierten Daten als String. Das 
     * {@link DateUtil#DATE_PATTERN} ist genutzt.
     * 
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Transformiert den String definiert in {@link DateUtil#DATE_PATTERN} 
     * zu{@link LocalDate} einem Objekt.
     * 
     * Wenn null String konnte nicht umgewandelt werden.
     * 
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * prüft den String ob es ein gültiges Datum date.
     * 
     */
    public static boolean validDate(String dateString) {
        // gliedern der Eingabe
        return DateUtil.parse(dateString) != null;
    }
}