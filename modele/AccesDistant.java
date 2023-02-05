package com.example.img.modele;

import android.util.Log;

import com.example.img.outils.AccesHTTP;
import com.example.img.outils.AsycResponse;

import org.json.JSONArray;

public class AccesDistant implements AsycResponse {

//    private static final String SERVERADDR = "http://10.176.145.11/opt/lampp/htdocs/coach/serveurcoach.php"
    private static final String SERVERADDR = "http://10.176.145.11/coach/serveurcoach.php";

    //Constructor
    public AccesDistant() {
        super();
    }
    /**
     * Retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
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
                } else {
                    if (message[0].equals("Erreur !")) {
                        Log.d("Erreur !", "**************" + message[1]);
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
