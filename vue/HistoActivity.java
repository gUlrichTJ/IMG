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
                startActivity(intent);
            }
        });
    }

    private void creerListe() {
        ArrayList<Profile> lesProfiles = controle.getLesProfiles();
        if (lesProfiles != null) {
            ListView lstHisto = (ListView) findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(this, lesProfiles);
            lstHisto.setAdapter(adapter);
        }
    }

}