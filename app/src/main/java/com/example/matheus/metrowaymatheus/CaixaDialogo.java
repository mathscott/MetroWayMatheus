package com.example.matheus.metrowaymatheus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.Hashtable;

public class CaixaDialogo extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private String nome;
    private Marker marker;

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Button getBtnReport() {
        return btnReport;
    }

    private Button btnReport;
    private Button btnShow;

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.info_window, null);
        btnReport = view.findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TelaReport.class);
                intent.putExtra("estacao", marker.getTitle());
                startActivity(intent);
            }
        });

        btnShow = view.findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TelaShow.class);
                intent.putExtra("estacao", marker.getTitle());
                startActivity(intent);
            }
        });
        view.setBackgroundColor(TelaInicial.getHashtable().get(nome));
        builder.setView(view).setTitle(nome);
        return builder.create();
    }


}
