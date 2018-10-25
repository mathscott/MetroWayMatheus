package com.example.matheus.metrowaymatheus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class TelaReclamacao extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    Spinner teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_reclamacao);
        teste = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, R.layout.support_simple_spinner_dropdown_item);
//        teste.setAdapter(adapter);
        String nomes [] = {"eu", "voce", "zubumafu"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, TelaInicial.estacoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        teste.setAdapter(spinnerArrayAdapter);

        Button submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d1 = new Date();
                TextView nome = (TextView) findViewById(R.id.nomeText);
//                TextView estacao = (TextView) findViewById(R.id.estacaoText);
                TextView descricao = (TextView) findViewById(R.id.descricaoText);
                writeNewReclamacao(teste.getSelectedItem().toString(), nome.getText().toString(), d1, descricao.getText().toString());
                nome.setText("");
                descricao.setText("");

            }
        });


    }

    private void writeNewReclamacao(String estacao, String nome, Date data, String descricao) {
        Reclamacao reclamacao = new Reclamacao(estacao, nome, data, descricao);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("reclamacoes").push().setValue(reclamacao);
    }
}
