package com.example.waka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.waka.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout botonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        botonIngresar=findViewById(R.id.botonIngresar);
        botonIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
            }
        });
        //layout=findViewById(R.id.pantalla);
        //layout.setBackgroundColor(getResources().getColor(R.color.fondoWaka));
        //t=findViewById(R.id.texto);
        //t.setText("Hola Wakapiedraso");

    }
}
