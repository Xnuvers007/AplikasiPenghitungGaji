package com.yoga.aplikasipenghitunggaji.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yoga.aplikasipenghitunggaji.MainActivity;
import com.yoga.aplikasipenghitunggaji.R;

public class HomeFragment extends Fragment {

    Button btnInputGaji;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false);

        btnInputGaji = view.findViewById(R.id.btnInputGaji);

        btnInputGaji.setOnClickListener(v -> {
            // Navigasi ke InputGajiFragment sebagai sub-halaman (navItemId = -1)
            // sehingga tab Home tetap terpilih dan back stack bisa kembali
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity())
                        .navigateToFragment(new InputGajiFragment(), -1);
            }
        });

        return view;
    }
}