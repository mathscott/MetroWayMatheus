package com.example.matheus.metrowaymatheus;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class TelaReclamacao extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    Spinner spinnerEstacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_reclamacao);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Tela de Reclamação");
        actionbar.setDisplayHomeAsUpEnabled(true);
        spinnerEstacoes = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, TelaInicial.estacoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEstacoes.setAdapter(spinnerArrayAdapter);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        TextInputEditText textInputName = (TextInputEditText) findViewById(R.id.textInputName);
        TextInputEditText textInputDescricao = (TextInputEditText) findViewById(R.id.textInputDescricao);
        textInputName.getText().toString();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d1 = new Date();
                TextInputEditText textInputName = (TextInputEditText) findViewById(R.id.textInputName);
                TextInputEditText textInputDescricao = (TextInputEditText) findViewById(R.id.textInputDescricao);
                writeNewReclamacao(spinnerEstacoes.getSelectedItem().toString(), textInputName.getText().toString(), d1, textInputDescricao.getText().toString());
                textInputName.setText("");
                textInputDescricao.setText("");

            }
        });


    }

    private void writeNewReclamacao(String estacao, String nome, Date data, String descricao) {
        Reclamacao reclamacao = new Reclamacao(estacao, nome, data, descricao);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("reclamacoes").push().setValue(reclamacao);
    }
}
