package com.example.img.outils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<BasicNameValuePair> parametres;
    private String ret = null;
    public AsycResponse delegate = null;
    /**
     * Constructor
     */
    public AccesHTTP() {
        parametres = new ArrayList<BasicNameValuePair>();
    }

    /**
     * Ajout d'un post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur) {
        parametres.add(new BasicNameValuePair(nom, valeur));
    }

    /**
     * Connexion en tâche de fond avec un thread séparé
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHTTP = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            //Encodage des paramètres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //Connexion et envoi de paramètres, attente de la réponse
            HttpResponse response = cnxHTTP.execute(paramCnx);
            //Transformation de la réponse
            ret = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur endodage", "******************" + e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur protocole", "******************" + e.toString());
        } catch (IOException e) {
            Log.d("Erreur I/O", "******************" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result) {
        delegate.processFinish(ret.toString());
    }
}
