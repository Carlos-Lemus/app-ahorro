package com.achmadqomarudin.animatedbottombar.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;


import com.achmadqomarudin.animatedbottombar.R;
import com.achmadqomarudin.animatedbottombar.fragments.AhorrosFragment;
import com.achmadqomarudin.animatedbottombar.fragments.ControlFragment;
import com.achmadqomarudin.animatedbottombar.fragments.InicioFragment;

import org.jetbrains.annotations.NotNull;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = MenuActivity.class.getSimpleName();
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;

   // ************* Haces un Metodo aqui para comprobar si hay registros********************
    boolean vacio = true;
    //********** y cambias el estado en el metodo para que se muestra el fragment de la lista de los registros

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        animatedBottomBar = findViewById(R.id.animatedBottomBar);


            if (savedInstanceState == null) {
                animatedBottomBar.selectTabById(R.id.home, true);
                fragmentManager = getSupportFragmentManager();
                InicioFragment inicioVacioFragment = new InicioFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, inicioVacioFragment)
                        .commit();
            }


        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NotNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()) {
                    case R.id.home:
                        fragment = new InicioFragment();

                        break;
                    case R.id.book:
                        fragment = new ControlFragment();
                        break;
                    case R.id.account:
                        fragment = new AhorrosFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creating Fragment");
                }
            }
        });
    }


}
