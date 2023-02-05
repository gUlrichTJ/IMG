package com.example.img.controleur;

import android.content.Context;

import com.example.img.modele.AccesDistant;
import com.example.img.modele.Profile;
import com.example.img.outils.Serializer;

import org.json.JSONArray;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profile profile;
    private static String monFic = "saveprofil";
    //private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;

    /**
     * Constructor privé
     */
    private Controle() {
        super();
    }

    /**
     * Création de l'instance
     * @return il retourne l'instance
     */
    public static final Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            //accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
            //profile = accesLocal.recupDernier();
            accesDistant.envoi("dernier", new JSONArray());
            //recupSerializable(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profile
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfile(int poids, int taille, int age, int sexe, Context context) {
        profile = new Profile(new Date(), poids, taille, age, sexe);
        //accesLocal.ajout(profile);
        accesDistant.envoi("enreg", profile.convertToJSONArray());
        //Serializer.serialize(monFic, profile, context);
    }

    /**
     * Récupération de l'img de Profile
     * @return
     */
    public float getImg() {
        return profile.getImg();
    }

    /**
     * Récupération du message de Profil
     * @return
     */
    public String getMessage() {
        return profile.getMessage();
    }

    /**
     * Récupération de l'objet sérialisé (Profile)
     * @param context
     */
    private static void recupSerializable(Context context) {
        profile = (Profile) Serializer.deSerialize(monFic, context);
    }

    public Integer getPoids() {
        if (profile == null) {
            return null;
        } else {
            return profile.getPoids();
        }
    }

    public Integer getTaille() {
        if (profile == null) {
            return null;
        } else {
            return profile.getTaille();
        }
    }

    public Integer getAge() {
        if (profile == null) {
            return null;
        } else {
            return profile.getAge();
        }
    }

    public Integer getSexe() {
        if (profile == null) {
            return null;
        } else {
            return profile.getSexe();
        }
    }
}
