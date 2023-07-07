package com.example.waka.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.waka.R;

public class IntroActivity extends AppCompatActivity {
    //button btnScan;
    //EditText txtResultados;
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

   /* btnScan=findViewById(R.id.btnScan);
    txtResultados=findViewById(R.id.txtResultado);

    btnScan.setOnClickListener(New View.OnClickListener(){
        public void onClick(View view){
            IntentIntegrator integrador=new IntentIntegrator(Activity: MainActivity.this);
            integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrador.setPrompt("Lector - CDP");
            integrador.setCameraId(0);
            integrador.setBeepEnabled(true);
            integrador.setBarcodeImageEnabled(true);
            integrador.initiateScan();
            }
        });
    }
    protected void onActivityResult (int requestCode,int resultCode,Intent data){
        IntentResult result=IntentIntegrator.parseActivityResult(resultCode,resultCode,data);
        if(result !=null){
            if (result.getContents()
            ==null){
                Toast.makeText(this,"Lectora Cancelada", Toast.LENGTH_LONG ).show();
            }
            else {
                Toast.makeText(this,result.getContents(), Toast.LENGTH_LONG ).show();
                txtResultados.setText(result.getContents());
            }

        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }*/


    }
}
