package com.example.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    TextView tvFichier;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        tvFichier = findViewById(R.id.TVfichier);
        webView = findViewById(R.id.webView);

        String FILENAME = "infos";
        StringBuilder fileContent = new StringBuilder();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            int c;
            while ((c = fis.read()) != -1) {
                fileContent.append((char) c);
            }
            fis.close();
        } catch (IOException e) {
            fileContent.append("Erreur lors de la lecture du fichier.");
        }

        tvFichier.setText(fileContent.toString());

        String webFileName ="page_webPerso.html";
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput(webFileName))
            );
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
            reader.close();

            webView.loadDataWithBaseURL(null, htmlContent.toString(), "text/html", "UTF-8", null);
        } catch (IOException e) {
            tvFichier.setText(fileContent.toString() + "\n\n[Erreur lors du chargement de la page web]");
        }

    }

}

