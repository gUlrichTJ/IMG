package com.example.img.modele;

import android.util.Log;

import com.example.img.controleur.Controle;
import com.example.img.outils.AccesHTTP;
import com.example.img.outils.AsycResponse;
import com.example.img.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsycResponse {

//    private static final String SERVERADDR = "http://10.176.145.11/opt/lampp/htdocs/coach/serveurcoach.php"
    private static final String SERVERADDR = "http://10.176.145.11/coach/serveurcoach.php";
    private Controle controle;

    //Constructor
    public AccesDistant() {
        controle = Controle.getInstance(null);
    }
    /**
     * Retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) throws JSONException {
        Log.d("Serveur", "**************" + output);
        //Découpage du message reçu
        String[] message = output.split("%");
        //Dans message[0] j'ai soit "enreg", "dernier", "Erreur"
        //Dans message[1] j'ai le reste du message

        //S'il y a deux cases
        if (message.length > 1) {
            if (message[0].equals("enreg")) {
                Log.d("enreg", "**************" + message[1]);
            } else {
                if (message[0].equals("dernier")) {
                    Log.d("dernier", "**************" + message[1]);
                    try {
                        JSONObject info = new JSONObject(message[1]);
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        String datemesure = info.getString("datemesure");
                        Date date =  MesOutils.convertStringToDate(datemesure, "yyyy-MM-dd hh:mm:ss");
                        Log.d("date mysql", "*************retour mysql" + date);
                        Profile profile = new Profile(new Date(), poids, taille, age, sexe);
                        controle.setProfile(profile);
                    } catch (JSONException e) {
                        Log.d("erreur", "****************" + message[1]);
                    }
                } else {
                    if (message[0].equals("tous")) {
                        Log.d("tous", "**************" + message[1]);
                        JSONArray jsonInfo = new JSONArray(message[1]);
                        ArrayList<Profile> lesProfiles = new ArrayList<Profile>();
                        for (int k = 0; k < jsonInfo.length(); k++) {
                            JSONObject info = new JSONObject(jsonInfo.get(k).toString());
                            Integer poids = info.getInt("poids");
                            Integer taille = info.getInt("taille");
                            Integer age = info.getInt("age");
                            Integer sexe = info.getInt("sexe");
                            String datemesure = info.getString("datemesure");
                            Date date =  MesOutils.convertStringToDate(datemesure, "yyyy-MM-dd hh:mm:ss");
                            Profile profile = new Profile(new Date(), poids, taille, age, sexe);
                            lesProfiles.add(profile);
                        }
                        controle.setLesProfiles(lesProfiles);
                    } else {
                        if (message[0].equals("Erreur !")) {
                            Log.d("Erreur !", "**************" + message[1]);
                        }
                    }
                }
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        //Lien de délégation
        accesDonnees.delegate = this;
        //Ajout des paramètres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesDonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
