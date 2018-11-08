package com.example.matheus.metrowaymatheus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CaixaDialogo extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private String nome;
    private Button botaoReportar;
    private Button botaoVerSituacao;

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.info_window, null);
        view.setBackgroundColor();
        builder.setView(view).setTitle(nome);
        return builder.create();
    }


}
