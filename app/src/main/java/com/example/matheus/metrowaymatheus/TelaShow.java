package com.example.matheus.metrowaymatheus;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class TelaShow extends AppCompatActivity {

    private String estacaoString;
    Button btnLimpeza;
    Button btnAssedio;
    Button btnLotacao;
    Button btnArCondicionado;
    Button btnSeguranca;
    Button btnLentidao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_show);
        getSupportActionBar().setTitle("MetroWay");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        btnLimpeza = findViewById(R.id.btnLimpezaShow);
        btnAssedio = findViewById(R.id.btnAssedioShow);
        btnLotacao = findViewById(R.id.btnLotacaoShow);
        btnArCondicionado = findViewById(R.id.btnArCondicionadoShow);
        btnSeguranca = findViewById(R.id.btnSegurancaShow);
        btnLentidao = findViewById(R.id.btnLentidaoShow);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            estacaoString = extras.getString("estacao");
        } else {
            Log.d("ts", "entrou");
        }

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot estacao: dataSnapshot.getChildren()) {
                    int ac = 0, lentidao = 0, lotacao = 0, limpeza = 0, assedio = 0, seguranca = 0;
                    Log.d("teste", estacao.getKey());
                    Log.d("teste", "entrou");
                    if (estacao.getKey().equals(estacaoString)) {
                        Log.d("teste", "entrou");
                        for (DataSnapshot uniqueKey: estacao.getChildren()) {
                            if (String.valueOf(uniqueKey.child("arCondicionado").getValue()) == "true") {
                                ac++;
                            }
                            if (String.valueOf(uniqueKey.child("lentidao").getValue()) == "true") {
                                lentidao++;
                            }
                            if (String.valueOf(uniqueKey.child("lotacao").getValue()) == "true") {
                                lotacao++;
                            }
                            if (String.valueOf(uniqueKey.child("limpeza").getValue()) == "true") {
                                limpeza++;
                            }
                            if (String.valueOf(uniqueKey.child("assedio").getValue()) == "true") {
                                assedio++;
                            }
                            if (String.valueOf(uniqueKey.child("seguranca").getValue()) == "true") {
                                seguranca++;
                            }

                        }
                        Log.d("teste", String.valueOf(ac));

                        btnLimpeza.setText(String.valueOf(limpeza));
                        btnAssedio.setText(String.valueOf(assedio));
                        btnArCondicionado.setText(String.valueOf(ac));
                        btnLentidao.setText(String.valueOf(lentidao));
                        btnSeguranca.setText(String.valueOf(seguranca));
                        btnLotacao.setText(String.valueOf(lotacao));
                    }



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(postListener);
    }

}
