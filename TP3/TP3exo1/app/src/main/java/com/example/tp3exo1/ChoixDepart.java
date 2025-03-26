package com.example.tp3exo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChoixDepart extends Activity {
    private Button Inscription;
    private Button Connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_dep);

        Inscription = findViewById(R.id.button_Inscription);
        Connexion = findViewById(R.id.button_Connexion);

        Inscription.setOnClickListener(b->{
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        });

        Connexion.setOnClickListener(b->{
            Intent i = new Intent(this,Connexion.class);
            startActivity(i);
        });

    }
}
