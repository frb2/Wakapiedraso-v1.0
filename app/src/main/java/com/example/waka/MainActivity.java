package com.example.waka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.waka.Adaptor.BebidasAdaptor;
import com.example.waka.Adaptor.PlatoFondoAdaptor;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Domain.PlatoFondoDomain;

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
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<PlatoFondoDomain> platoFondo=new ArrayList<>();
        platoFondo.add(new PlatoFondoDomain("Cabrito Tierno", "cat_1"));
        platoFondo.add(new PlatoFondoDomain("Pato Estofado", "cat_2"));
        platoFondo.add(new PlatoFondoDomain("Arroz con Pato", "cat_3"));
        platoFondo.add(new PlatoFondoDomain("Bistec a lo Pobre", "cat_4"));
        platoFondo.add(new PlatoFondoDomain("Pescado apanado de Tollo", "cat_5"));

        adapter=new PlatoFondoAdaptor(platoFondo);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewBebidas(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBebidasList=findViewById(R.id.recyclerView2);
        recyclerViewBebidasList.setLayoutManager(linearLayoutManager);

        ArrayList<BebidasDomain> bebidas=new ArrayList<>();
        bebidas.add(new BebidasDomain("Coca-Cola", "cat_1", "bebida", 5.99));
        bebidas.add(new BebidasDomain("Inca-Cola", "cat_2","bebida", 6.99));
        bebidas.add(new BebidasDomain("Pepsi", "cat_3","bebida", 9.99));
        bebidas.add(new BebidasDomain("Sprite", "cat_4","bebida", 15.99));
        bebidas.add(new BebidasDomain("7up", "cat_5","bebida", 5.99));

        adapter2=new BebidasAdaptor(bebidas);
        recyclerViewBebidasList.setAdapter(adapter2);

    }
}