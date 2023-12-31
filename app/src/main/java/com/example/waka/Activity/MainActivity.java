package com.example.waka.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.waka.Adaptor.BebidasAdaptor;
import com.example.waka.Adaptor.CartListAdaptor;
import com.example.waka.Adaptor.PlatoFondoAdaptor;
import com.example.waka.Adaptor.PlatosAdaptor;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Domain.PlatoFondoDomain;
import com.example.waka.Domain.PlatosDomain;
import com.example.waka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private  RecyclerView recyclerViewCategoryList, recyclerViewBebidasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewBebidas();
        bottomNavigation();
    }

    private void bottomNavigation(){

        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        //LinearLayout homeBtn=findViewById(R.id);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, CartListActivity.class));

            }
        });
    }


    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBebidasList=findViewById(R.id.recyclerView);
        recyclerViewBebidasList.setLayoutManager(linearLayoutManager);

        ArrayList<BebidasDomain> platos=new ArrayList<>();
        platos.add(new BebidasDomain("Cabrito Tierno", "cat_1", "Plato de Fondo", 30.00));
        platos.add(new BebidasDomain("Pato Estofado", "cat_2","Plato de Fondo", 35.00));
        platos.add(new BebidasDomain("Arroz con Pato", "cat_3","Plato de Fondo", 22.00));
        platos.add(new BebidasDomain("Bistec a lo Pobre", "cat_4","Plato de Fondo", 25.00));
        platos.add(new BebidasDomain("Pescado apanado de Tollo", "cat_5","Plato de Fondo", 28.00));

        adapter=new BebidasAdaptor(platos);
        recyclerViewBebidasList.setAdapter(adapter);


    }

    private void recyclerViewBebidas(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBebidasList=findViewById(R.id.recyclerView2);
        recyclerViewBebidasList.setLayoutManager(linearLayoutManager);

        ArrayList<BebidasDomain> bebidas=new ArrayList<>();
        bebidas.add(new BebidasDomain("Pilsen Trujillo", "cat_4", "Cerveza", 5.00));
        bebidas.add(new BebidasDomain("Pilsen Callao", "cat_4","Cerveza", 6.00));
        bebidas.add(new BebidasDomain("Gaseosa 1.5L", "cat_4","Gaseosa", 9.00));
        bebidas.add(new BebidasDomain("Gaseosa Personal", "cat_4","Gaseosa", 15.00));
        bebidas.add(new BebidasDomain("Jarra de Limonada", "cat_4","Bebida Natural", 5.00));

        adapter2=new BebidasAdaptor(bebidas);
        recyclerViewBebidasList.setAdapter(adapter2);

    }
}