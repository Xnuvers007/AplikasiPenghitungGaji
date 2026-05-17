package com.yoga.aplikasipenghitunggaji;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.yoga.aplikasipenghitunggaji.fragment.HomeFragment;
import com.yoga.aplikasipenghitunggaji.fragment.TentangFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView =
                findViewById(R.id.bottomNavigation);

        // Tampilan awal
        loadFragment(new HomeFragment());

        bottomNavigationView
                .setOnItemSelectedListener(item -> {

                    Fragment fragment = null;

                    if(item.getItemId()
                            == R.id.menu_utama){

                        fragment =
                                new HomeFragment();

                    } else if(item.getItemId()
                            == R.id.menu_tentang){

                        fragment =
                                new TentangFragment();
                    }

                    if(fragment != null){

                        loadFragment(fragment);
                    }

                    return true;
                });
    }

    private void loadFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.frameLayout,
                        fragment)
                .commit();
    }
}