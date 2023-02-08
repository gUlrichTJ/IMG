package com.example.img.vue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.img.R;
import com.example.img.controleur.Controle;
import com.example.img.outils.MesOutils;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    //Propriétés
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * Initialisation des liens avec les objets ...
     */
    @SuppressLint("WrongViewCast")
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
        //recupProfile();
    }

    /**
     * Ecoute l'évènement sur le bouton calculer
     */
    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int poids = 0, taille = 0, age = 0, sexe = 0;

                //Récupération des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {

                }

                if (rdHomme.isChecked()) {
                    sexe = 1;
                }

                //Contrôle des données saisies
                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(CalculActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }

    private void afficheResult(int poids, int taille, int age, int sexe) {
        //Création du profil et récupération des informations
        this.controle.creerProfile(poids, taille, age, sexe, this);

        float img = this.controle.getImg();
        String message = this.controle.getMessage();

        //Affichage
        if (message == "normal") {
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        } else {
            lblIMG.setTextColor(Color.RED);
            if (message == "trop faible") {
                imgSmiley.setImageResource(R.drawable.mince);
            } else {
                imgSmiley.setImageResource(R.drawable.gros);
            }
        }
        lblIMG.setText(MesOutils.format2Decimal(img) + " IMG " + message);
    }

    /**
     * Récupération de Profile s'il a été sérialisé
     */
    public void recupProfile() {
        if (controle.getPoids() != null) {
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());

            rdFemme.setChecked(true);
            if (controle.getSexe() == 1) {
                rdHomme.setChecked(true);
            }

            //Simule le clic sur le bouton calculer
            //((Button)findViewById(R.id.btnCalc)).performClick();
        }
    }

    /**
     * Retour au menu
     */
    private void ecouteRetourMenu() {
        ((ImageButton)findViewById(R.id.btnRetourDeCalcul)).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}