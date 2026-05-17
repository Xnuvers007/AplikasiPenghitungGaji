package com.yoga.aplikasipenghitunggaji;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.yoga.aplikasipenghitunggaji.fragment.HomeFragment;
import com.yoga.aplikasipenghitunggaji.fragment.TentangFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Tampilan awal
        loadFragment(new HomeFragment());

        // Dipanggil saat tab berpindah ke tab lain
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment fragment = null;

            if (item.getItemId() == R.id.menu_utama) {
                fragment = new HomeFragment();

            } else if (item.getItemId() == R.id.menu_tentang) {
                fragment = new TentangFragment();
            }

            if (fragment != null) {
                // Bersihkan back stack agar fragment tidak menumpuk
                clearBackStack();
                loadFragment(fragment);
            }

            return true;
        });

        // FIX: Dipanggil saat tab yang SUDAH aktif diklik lagi
        // Tanpa ini, klik Home saat sudah di Home tidak akan bereaksi
        bottomNavigationView.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.menu_utama) {
                // Bersihkan back stack dan kembali ke HomeFragment
                clearBackStack();
                loadFragment(new HomeFragment());
            }
        });
    }

    /**
     * Bersihkan semua fragment yang ada di back stack.
     */
    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * Load fragment ke dalam frameLayout.
     */
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    /**
     * Method publik untuk digunakan fragment agar bisa navigasi
     * ke fragment lain sekaligus sinkronisasi BottomNavigationView.
     */
    public void navigateToFragment(Fragment fragment, int navItemId) {
        if (navItemId != -1) {
            // Update selected item di nav tanpa trigger listener (setSelectedItemId
            // akan trigger onItemSelected, tapi kita butuh itu supaya state sinkron)
            bottomNavigationView.setSelectedItemId(navItemId);
        } else {
            // Navigasi tanpa mengubah tab yang dipilih (misal: sub-halaman)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}