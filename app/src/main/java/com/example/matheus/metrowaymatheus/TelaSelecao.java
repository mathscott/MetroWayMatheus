package com.example.matheus.metrowaymatheus;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaSelecao extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_selecao);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Tela de Seleção");
        actionbar.setDisplayHomeAsUpEnabled(true);
        final DatabaseReference myRef = database.getReference("message");
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);
        final RadioButton rbReclamacao = (RadioButton) findViewById(R.id.rbReclamacao) ;
        final RadioButton rbSugestao = (RadioButton) findViewById(R.id.rbSugestao) ;
        final RadioButton rbElogio = (RadioButton) findViewById(R.id.rbElogio) ;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                if (rbReclamacao.isChecked()) {
                    Intent intent = new Intent(TelaSelecao.this, TelaReclamacao.class);
                    startActivity(intent);
                } else if (rbElogio.isChecked()) {
                    Intent intent = new Intent(TelaSelecao.this, TelaElogio.class);
                    startActivity(intent);
                } else if (rbSugestao.isChecked()) {

                } else {

                }



            }
        });



//        botao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String valorTexto = texto.getText().toString();
//                myRef.child("teste").setValue(valorTexto);
//            }
//        });
        myDialog = new Dialog(this);

    }

    public void openDialog () {
        CaixaDialogo exampleDialog = new CaixaDialogo();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


}
