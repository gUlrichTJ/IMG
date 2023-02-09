package com.example.img.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.img.R;
import com.example.img.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controle = Controle.getInstance(this);
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuIMG) , CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMenuHistorique) , HistoActivity.class);
    }

    /**
     * Ouvrir l'activité correspondante
     * @param btn
     * @param classe
     */
    private void ecouteMenu(ImageButton btn, Class classe) {
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}