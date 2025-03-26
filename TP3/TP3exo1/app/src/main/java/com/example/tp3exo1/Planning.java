package com.example.tp3exo1;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Planning extends Activity {
    private TextView creneau1;
    private TextView creneau2;
    private TextView creneau3;
    private TextView creneau4;

    EditText Editcreneau1;
    EditText Editcreneau2 ;
    EditText Editcreneau3 ;
    EditText Editcreneau4;
    Button Soumettre2;

    private String Login;
    private String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_planning);

        Bundle infosConnexion = getIntent().getExtras();
        assert infosConnexion != null;
        this.Login = infosConnexion.getString("l");
        this.mdp = infosConnexion.getString("m");

        creneau1 = findViewById(R.id.creneau1);
        creneau2 = findViewById(R.id.creneau2);
        creneau3 = findViewById(R.id.creneau3);
        creneau4 = findViewById(R.id.creneau4);

        BaseDeDonnees bdd = new BaseDeDonnees(this);
        Cursor cursor = bdd.getUserInfo(Login);


        Editcreneau1 = findViewById(R.id.Editcreneau1);
        Editcreneau2 = findViewById(R.id.Editcreneau2);
        Editcreneau3 = findViewById(R.id.Editcreneau3);
        Editcreneau4 = findViewById(R.id.Editcreneau4);
        Soumettre2 = findViewById(R.id.button_Soumettre2);

        if (cursor.moveToFirst()) {
            Editcreneau1.setText(cursor.getString(9) );
            Editcreneau2.setText(cursor.getString(10) );
            Editcreneau3.setText(cursor.getString(11));
            Editcreneau4.setText(cursor.getString(12));
        }

        Soumettre2.setOnClickListener(v -> {
            String TexteEditcreneau1 = Editcreneau1.getText().toString();
            String TexteEditcreneau2 = Editcreneau2.getText().toString();
            String TexteEditcreneau3 = Editcreneau3.getText().toString();
            String TexteEditcreneau4 = Editcreneau4.getText().toString();

            boolean inserted = bdd.insertPlanning(Login, TexteEditcreneau1, TexteEditcreneau2, TexteEditcreneau3, TexteEditcreneau4);
            if (inserted) {
                Toast.makeText(Planning.this, "Planning enregistré !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Planning.this, SynthesePlanning.class);
                i.putExtra("l", Login);
                startActivity(i);
            } else {
                Toast.makeText(Planning.this, "Erreur lors de l'enregistrement.", Toast.LENGTH_SHORT).show();
            }
        });


        cursor.close();

    }
}
