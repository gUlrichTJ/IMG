package com.example.img.outils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {

    /**
     * //Conversion chaîne sous format
     * @param uneDate :
     * @return
     */
    public static Date convertStringToDate(String uneDate) {
        return convertStringToDate(uneDate, "EEE MMM dd hh:mm:ss 'GMT' yyyy");
    }

    /**
     * Conversion en fonction d'un format
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String uneDate, String formatAttendu) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date date =  formatter.parse(uneDate);
        } catch (ParseException e) {
            Log.d("erreur", "parse de la date impossible " + e.toString());
        }
        return null;
    }

    /**
     * Conversion de date en String
     * @param uneDate
     * @return
     */
    public static String convertDateToString(String  uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(uneDate);
    }

    /**
     * Retourne un float au format String avec un chiffre après la virgule
     * @param valeur
     * @return
     */
    public static String format2Decimal(float valeur) {
        return String.format("%.01f", valeur);
    }

}
