package com.yoga.aplikasipenghitunggaji.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        btnInputGaji =
                view.findViewById(R.id.btnInputGaji);

        btnInputGaji.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.frameLayout,
                            new InputGajiFragment())
                    .commit();
        });

        return view;
    }
}