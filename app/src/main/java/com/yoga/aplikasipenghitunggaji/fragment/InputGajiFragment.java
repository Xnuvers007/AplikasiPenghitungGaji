package com.yoga.aplikasipenghitunggaji.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yoga.aplikasipenghitunggaji.R;

public class InputGajiFragment extends Fragment {

    EditText etNama, etHadir,
            etGaji, etBonus, etPotongan;

    Button btnHitung;

    TextView tvHasil;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_input_gaji,
                container,
                false);

        etNama = view.findViewById(R.id.etNama);

        etHadir = view.findViewById(R.id.etHadir);

        etGaji = view.findViewById(R.id.etGaji);

        etBonus = view.findViewById(R.id.etBonus);

        etPotongan =
                view.findViewById(R.id.etPotongan);

        btnHitung =
                view.findViewById(R.id.btnHitung);

        tvHasil =
                view.findViewById(R.id.tvHasil);

        btnHitung.setOnClickListener(v -> {

            if(etNama.getText().toString().isEmpty()
                    || etHadir.getText().toString().isEmpty()
                    || etGaji.getText().toString().isEmpty()
                    || etBonus.getText().toString().isEmpty()
                    || etPotongan.getText().toString().isEmpty()) {

                Toast.makeText(
                        getContext(),
                        "Semua data wajib diisi",
                        Toast.LENGTH_SHORT).show();

            } else {

                String nama =
                        etNama.getText().toString();

                int hadir =
                        Integer.parseInt(
                                etHadir.getText().toString());

                int gaji =
                        Integer.parseInt(
                                etGaji.getText().toString());

                int bonus =
                        Integer.parseInt(
                                etBonus.getText().toString());

                int potongan =
                        Integer.parseInt(
                                etPotongan.getText().toString());

                int total =
                        (hadir * gaji)
                                + bonus
                                - potongan;

                tvHasil.setText(

                        "Nama : " + nama +

                                "\nJumlah Hadir : " + hadir +

                                "\nTotal Gaji : Rp " + total
                );
            }
        });

        return view;
    }
}