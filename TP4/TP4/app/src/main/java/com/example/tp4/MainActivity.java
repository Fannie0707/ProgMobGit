package com.example.tp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    Button btnValider;
    Button btnAnnuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnValider = findViewById(R.id.buttonValider);
        btnAnnuler = findViewById(R.id.buttonAnnuler);


        btnValider.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              EditText editNom = findViewById(R.id.editNom);
              EditText editPrenom = findViewById(R.id.editPrenom);
              EditText editWebPerso = findViewById(R.id.editWebPerso);
              EditText editLinkedInPerso = findViewById(R.id.editLinkedInPerso);
              EditText editWebEntreprise = findViewById(R.id.editWebEntreprise);
              EditText editLinkedInEntreprise = findViewById(R.id.editLinkedInEntreprise);

              String nom = editNom.getText().toString();
              String prenom = editPrenom.getText().toString();
              String webPerso = editWebPerso.getText().toString();
              String linkedinPerso = editLinkedInPerso.getText().toString();
              String webEntreprise = editWebEntreprise.getText().toString();
              String linkedinEntreprise = editLinkedInEntreprise.getText().toString();

              String FILENAME = "infos";
              String message ="Nom : "+ nom + "\n prénom : "+ prenom + "\n Page web personnelle : " +
                      webPerso + "\n Page linkedin personnelle : " +linkedinPerso+ "\n Page web de l'entreprise :" + webEntreprise +
                      "\n Page linkedin de l'entreprise : " + linkedinEntreprise;

              try {
                  FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                  fos.write(message.getBytes());
                  fos.close();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }

              String[] urls = {webPerso,linkedinPerso,webEntreprise,linkedinEntreprise};
              Intent intent = new Intent(MainActivity.this, ServiceTelechargement.class);
              intent.putExtra("urls", urls);
              startService(intent);

              Intent i = new Intent(MainActivity.this, SecondActivity.class);
              startActivity(i);



          }
        });



    }


}
