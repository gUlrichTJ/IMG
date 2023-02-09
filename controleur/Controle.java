package com.example.img.controleur;

import android.content.Context;
import android.util.Log;

import com.example.img.modele.AccesDistant;
import com.example.img.modele.Profile;
import com.example.img.outils.Serializer;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profile profile;
    private static String monFic = "saveprofil";
    private static Context context;
    //private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private ArrayList<Profile> lesProfiles = new ArrayList<Profile>();

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
        if (context != null) {
            Controle.context = context;
        }
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            //accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
            //profile = accesLocal.recupDernier();
//            accesDistant.envoi("dernier", new JSONArray());
            accesDistant.envoi("tous", new JSONArray());
            //recupSerializable(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profile
     * @param poids
     * @param taille en cm
     * @param age .
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfile(int poids, int taille, int age, int sexe, Context context) {
        Profile unProfile = new Profile(new Date(), poids, taille, age, sexe);
        lesProfiles.add(unProfile);
        //A quoi ressemble le formatage de cette date?
        Log.d("date", "****************" + (new Date()));
        //accesLocal.ajout(profile);
        accesDistant.envoi("enreg", unProfile.convertToJSONArray());
        //Serializer.serialize(monFic, profile, context);
    }

    /**
     * Cette méthode permet de supprimer le profil dans la collection et dans la base distante
     * @param profile
     */
    public void delProfile(Profile profile) {
        accesDistant.envoi("del", profile.convertToJSONArray());
        lesProfiles.remove(profile);
    }

    public void setProfile(Profile profile) {
        Controle.profile = profile;
        //((CalculActivity)context).recupProfile();
    }

    /**
     * Récupération de l'img de Profile
     * @return
     */
    public float getImg() {
//        return profile.getImg();
        return lesProfiles.get(getLesProfiles().size() - 1).getImg();
    }

    /**
     * Récupération du message de Profil
     * @return
     */
    public String getMessage() {
//        return profile.getMessage();
        return lesProfiles.get(getLesProfiles().size() - 1).getMessage();
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

    public ArrayList<Profile> getLesProfiles() {
        return lesProfiles;
    }

    public void setLesProfiles(ArrayList<Profile> lesProfiles) {
        this.lesProfiles = lesProfiles;
    }
}
