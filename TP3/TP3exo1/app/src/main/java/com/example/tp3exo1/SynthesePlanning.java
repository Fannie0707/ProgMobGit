package com.example.tp3exo1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class SynthesePlanning extends Activity {
    private TextView texteSythese;
    private String Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_synthese_planning);

        texteSythese = findViewById(R.id.TVsynthese);

        Bundle infosConnexion = getIntent().getExtras();
        assert infosConnexion != null;
        this.Login = infosConnexion.getString("l");

        BaseDeDonnees bdd = new BaseDeDonnees(this);
        Cursor cursor = bdd.getUserInfo(Login);
        if (cursor.moveToFirst()) {
            texteSythese.setText("8h-10h: " + cursor.getString(9) + "\n" +
                    "10h-12h: " + cursor.getString(10) + "\n" +
                    "14h-16h: " + cursor.getString(11) +"\n" +
                    "16-18h " + cursor.getString(12));
        }
    }
}
