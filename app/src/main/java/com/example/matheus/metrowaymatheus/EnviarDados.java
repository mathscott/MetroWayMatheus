package com.example.matheus.metrowaymatheus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnviarDados extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_dados);

        final DatabaseReference myRef = database.getReference("message");

        Button botao = (Button) findViewById(R.id.button);
//        final EditText texto = (EditText) findViewById(R.id.editText);
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnviarDados.this, TelaReclamacao.class);
                startActivity(intent);

            }
        });

//        botao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String valorTexto = texto.getText().toString();
//                myRef.child("teste").setValue(valorTexto);
//            }
//        });


    }


}
