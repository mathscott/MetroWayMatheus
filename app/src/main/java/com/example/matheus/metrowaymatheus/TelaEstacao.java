package com.example.matheus.metrowaymatheus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class TelaEstacao extends AppCompatActivity {
    boolean lentidao;
    boolean assedio;
    boolean lotacao;
    private DatabaseReference mDatabase;
    String estacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_estacao);



        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            estacao = extras.getString("estacao");
        } else {
            Log.d("ts", "entrou");
        }

        final ImageButton btnLentidao = findViewById(R.id.botaoLentidao);
        btnLentidao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLentidao.setSelected(true);
            }
        });

        Button botaoOk = findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewEnvio(btnLentidao.isSelected(), assedio, lotacao, estacao);
                btnLentidao.setSelected(false);
            }
        });


    }

    private void writeNewEnvio(boolean lentidao, boolean assedio, boolean lotacao, String estacao) {
        Envio envio = new Envio();
        envio.setAssedio(assedio);
        envio.setLentidao(lentidao);
        envio.setLotacao(lotacao);
        envio.setEstacao(estacao);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(estacao).push().setValue(envio);
    }
}
