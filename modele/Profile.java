package com.example.img.modele;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profile implements Serializable {

    private static final int minFemme = 15; //Maigre si en dessous
    private static final int maxFemme = 30; //Grosse si au dessus
    private static final int minHomme = 10; //Maigre si en dessous
    private static final int maxHomme = 25; //Gros si au dessus
    //propriétes
    private Date dateMesure;
    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img;
    private String message;

    //Constructor
    public Profile(Date dateMesure, int poids, int taille, int age, int sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    private void calculIMG() {
        float tailleM = (float)taille/100;
        this.img = (float) (1.2 * poids / (tailleM * tailleM) + 0.23 * age - 10.83 * sexe - 5.4);
    }

    private void resultIMG() {
        int min = 0, max = 0;
        if (sexe == 0) {    //Femme
            min = minFemme;
            max = maxFemme;
        } else {    //Homme
            min = minHomme;
            max = maxHomme;
        }

        //Message correspondant
        message = "normal";
        if (img < min) {
            message = "trop faible";
        } else {
            if (img > max) {
                message = "trop élevé";
            }
        }
    }

    /**
     * Conversion au format JSONArray
     * @return
     */
    public JSONArray convertToJSONArray() {
        List laListe = new ArrayList<>();
        laListe.add(dateMesure);
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);
        return new JSONArray(laListe);
    }
}
