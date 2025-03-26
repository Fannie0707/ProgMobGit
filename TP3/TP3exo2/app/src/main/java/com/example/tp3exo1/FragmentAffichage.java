package com.example.tp3exo1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentAffichage extends Fragment {
    private TextView textViewLogin;
    private TextView textViewMdp;
    private TextView textViewNom;
    private TextView textViewPrenom;
    private TextView textViewDateNaissance;
    private TextView textNumeroTel;
    private TextView textEmail;
    private TextView textInteret;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_aff, container, false);



        textViewLogin = v.findViewById(R.id.TVLogin);
        textViewMdp = v.findViewById(R.id.TVmdp);
        textViewNom = v.findViewById(R.id.TVNom);
        textViewPrenom = v.findViewById(R.id.TVPrenom);
        textViewDateNaissance = v.findViewById(R.id.TVDateNaissance);
        textNumeroTel = v.findViewById(R.id.TVNumTel);
        textEmail = v.findViewById(R.id.TVEmail);
        textInteret = v.findViewById(R.id.TVInteret);

        BaseDeDonnees bdd = new BaseDeDonnees(getContext());
        Cursor cursor = bdd.getLastUser();
        if (cursor.moveToFirst()) {
            textViewLogin.setText("Login: " + cursor.getString(1) );
            textViewNom.setText("Nom: " + cursor.getString(3) );
            textViewPrenom.setText("Prenom: " + cursor.getString(4));
            textViewDateNaissance.setText("Date de naissance: " + cursor.getString(5));
            textNumeroTel.setText("Téléphone: " + cursor.getString(6));
            textEmail.setText("Email: " + cursor.getString(7));
            textInteret.setText("Centres d'intérêt: " + cursor.getString(8));
        }
        cursor.close();

        return v;

    }
    public static FragmentAffichage newInstance(Donnes d) {
        FragmentAffichage fragment = new FragmentAffichage();
        Bundle args = new Bundle();

        args.putString("Nom", d.getNom());
        args.putString("Prenom", d.getPrenom());
        args.putString("DateNaissance", d.getDateNaissance());
        args.putString("NumeroTel", d.getNumTel());
        args.putString("Email", d.getEmail());
        args.putString("Interet", d.getInteret());

        fragment.setArguments(args);
        return fragment;
    }
}
