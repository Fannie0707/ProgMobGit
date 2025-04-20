package com.example.tp4;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceTelechargement extends IntentService {

    private static final String TAG = "ServiceTelechargement";

    public ServiceTelechargement() {
        super("ServiceTelechargement");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String[] urls = intent.getStringArrayExtra("urls");
        String[] noms = {"webPerso","linkedinPerso","webEntreprise","linkedinEntreprise"};
        for (int i = 0; i < urls.length; i++) {
            telechargeEtEnregistre(urls[i], "page_" + noms[i] + ".html");
        }
    }

    private void telechargeEtEnregistre(String urlString, String filename) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }

            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(htmlContent.toString());
            writer.close();
            fos.close();

            Log.d(TAG, "Fichier enregistré : " + filename);

        } catch (Exception e) {
            Log.e(TAG, "Erreur téléchargement : " + e.toString());
        } finally {
            try {
                if (reader != null) reader.close();
                if (urlConnection != null) urlConnection.disconnect();
            } catch (Exception ignored) {}
        }
    }
}
