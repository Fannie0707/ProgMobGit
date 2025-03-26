package com.example.tp3exo1;

import android.content.Intent;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Button;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

public class Connexion extends Activity {
    private EditText EditLogin, Editmdp;
    private BaseDeDonnees bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_connexion);

        EditLogin = findViewById(R.id.EditconnexionLogin);
        Editmdp = findViewById(R.id.EditconnexionMdp);
        Button buttonSoumettre = findViewById(R.id.button_Soumettre);
        bdd = new BaseDeDonnees(this);


        buttonSoumettre.setOnClickListener(b -> {
            String login = EditLogin.getText().toString();
            String mdp = Editmdp.getText().toString();

            String temp = bdd.getUserExistence(login,mdp);
            if (!Objects.equals(temp, "Error!")) {
                Toast.makeText(Connexion.this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Connexion.this, Planning.class);
                i.putExtra("l", login);
                i.putExtra("m", mdp);
                startActivity(i);
            } else {
                Toast.makeText(Connexion.this, "Login ou mot de passe incorrect.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
