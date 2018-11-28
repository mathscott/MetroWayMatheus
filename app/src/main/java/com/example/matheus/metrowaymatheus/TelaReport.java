package com.example.matheus.metrowaymatheus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaReport extends AppCompatActivity {
    boolean lentidao;
    boolean assedio;
    boolean lotacao;
    private DatabaseReference mDatabase;
    String estacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_report);



        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            estacao = extras.getString("estacao");
        } else {
            Log.d("ts", "entrou");
        }
        final CheckableImageButton btnLentidao = findViewById(R.id.botaoLentidao);
        final CheckableImageButton btnLotacao = findViewById(R.id.botaoLotacao);
        final CheckableImageButton btnAssedio = findViewById(R.id.botaoAssedio);
        final CheckableImageButton btnLimpeza = findViewById(R.id.botaoLimpeza);
        final CheckableImageButton btnSeguranca = findViewById(R.id.botaoSeguranca);
        final CheckableImageButton btnArCondicionado = findViewById(R.id.botaoArCondicionado);



        Button botaoOk = findViewById(R.id.botaoOk);
        botaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewEnvio(btnLentidao.isChecked(), btnLotacao.isChecked(), btnAssedio.isChecked(), btnLimpeza.isChecked(), btnSeguranca.isChecked(), btnArCondicionado.isChecked(), estacao);
                btnLentidao.setSelected(false);
            }
        });


    }

    private void writeNewEnvio(boolean lentidao, boolean lotacao, boolean assedio, boolean limpeza, boolean seguranca, boolean arCondicionado, String estacao) {
        Report report = new Report();
        report.setAssedio(assedio);
        report.setLentidao(lentidao);
        report.setLotacao(lotacao);
        report.setArCondicionado(arCondicionado);
        report.setLimpeza(limpeza);
        report.setSeguranca(seguranca);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mDatabase.child(estacao).push().setValue(report);
        DatabaseReference estacaoRef = mDatabase.child(estacao).push();
        estacaoRef.setValue(report);


    }
}
