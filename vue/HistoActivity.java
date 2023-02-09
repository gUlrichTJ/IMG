package com.example.img.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.img.R;
import com.example.img.controleur.Controle;
import com.example.img.modele.Profile;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle = Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }

    /**
     * Retour au menu
     */
    private void ecouteRetourMenu() {
        ((ImageButton)findViewById(R.id.btnRetourDeHisto)).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void creerListe() {
        ArrayList<Profile> lesProfiles = controle.getLesProfiles();
        Collections.sort(lesProfiles, Collections.reverseOrder());
        if (lesProfiles != null) {
            ListView lstHisto = (ListView) findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(this, lesProfiles);
            lstHisto.setAdapter(adapter);
        }
    }

    /**
     * Demande d'afficher le profil dans CalculActivity
     * @param profile
     */
    public void afficheProfile(Profile profile) {
        controle.setProfile(profile);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}