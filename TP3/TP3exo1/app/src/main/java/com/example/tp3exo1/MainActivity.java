package com.example.tp3exo1;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        BaseDeDonnees bdd = new BaseDeDonnees(this);
        bdd.db = bdd.open();
        Objects.requireNonNull(getSupportActionBar()).hide();

        if (savedInstanceState == null) {
            FragmentChoix fragment = new FragmentChoix();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_main, fragment);
            fragmentTransaction.commit();
        }
    }

}
