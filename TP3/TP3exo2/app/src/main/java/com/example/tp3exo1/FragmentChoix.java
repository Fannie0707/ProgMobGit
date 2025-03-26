package com.example.tp3exo1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class FragmentChoix extends Fragment {
    private Button buttonSoumettre;
    private EditText EditLogin, Editmdp, EditNom, EditPrenom, EditDateNaissance, EditNumTel, EditEmail;
    private CheckBox checkBoxSport, checkBoxMusique, checkBoxLecture, checkBoxArt, checkBoxInformatique, checkBoxMaths, checkBoxChats;
    private BaseDeDonnees bdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_choix, container, false);

        bdd = new BaseDeDonnees(getContext());

        EditLogin = v.findViewById(R.id.EditLogin);
        Editmdp = v.findViewById(R.id.Editmdp);
        EditNom = v.findViewById(R.id.EditNom);
        EditPrenom = v.findViewById(R.id.EditPrenom);
        EditDateNaissance = v.findViewById(R.id.EditDateNaissance);
        EditNumTel = v.findViewById(R.id.EditNumTel);
        EditEmail = v.findViewById(R.id.EditEmail);
        checkBoxSport = v.findViewById(R.id.checkBoxSport);
        checkBoxMusique = v.findViewById(R.id.checkBoxMusique);
        checkBoxLecture = v.findViewById(R.id.checkBoxLecture);
        checkBoxArt = v.findViewById(R.id.checkBoxArt);
        checkBoxInformatique = v.findViewById(R.id.checkBoxInformatique);
        checkBoxMaths = v.findViewById(R.id.checkBoxMaths);
        checkBoxChats = v.findViewById(R.id.checkBoxChats);
        buttonSoumettre = v.findViewById(R.id.button_Soumettre);

        buttonSoumettre.setOnClickListener(b -> {
            String login = EditLogin.getText().toString();
            String password = Editmdp.getText().toString();
            String nom = EditNom.getText().toString();
            String prenom = EditPrenom.getText().toString();
            String dateNaissance = EditDateNaissance.getText().toString();
            String numTel = EditNumTel.getText().toString();
            String email = EditEmail.getText().toString();

            List<String> interetsList = new ArrayList<>();
            if (checkBoxSport.isChecked()) interetsList.add("Sport");
            if (checkBoxMusique.isChecked()) interetsList.add("Musique");
            if (checkBoxLecture.isChecked()) interetsList.add("Lecture");
            if (checkBoxArt.isChecked()) interetsList.add("Art");
            if (checkBoxInformatique.isChecked()) interetsList.add("Informatique");
            if (checkBoxMaths.isChecked()) interetsList.add("Mathématiques");
            if (checkBoxChats.isChecked()) interetsList.add("Chats");
            String interets = String.join(", ", interetsList);

            boolean inserted = bdd.insertUser(login, password, nom, prenom, dateNaissance, numTel, email, interets);
            if (inserted) {
                Toast.makeText(getContext(), "Inscription réussie !", Toast.LENGTH_SHORT).show();

                // Passer au FragmentAffichage après l'inscription réussie
                FragmentAffichage fragmentAffichage = new FragmentAffichage();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_main, fragmentAffichage);
                transaction.addToBackStack(null); // Permet de revenir en arrière
                transaction.commit();
            } else {
                Toast.makeText(getContext(), "Erreur lors de l'inscription.", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
