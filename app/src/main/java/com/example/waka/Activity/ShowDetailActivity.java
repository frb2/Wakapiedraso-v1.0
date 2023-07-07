package com.example.waka.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Domain.PlatosDomain;
import com.example.waka.Helper.ManagementCart;
import com.example.waka.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descrptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private BebidasDomain objectBebidas;
    //private PlatosDomain objectPlatos;
    int numberOrder=1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart=new ManagementCart(this);
        initView();
        getBundleBebidas();
    }

    private void getBundleBebidas(){
        objectBebidas=(BebidasDomain) getIntent().getSerializableExtra("object");


        int drawableResourceId=this.getResources().getIdentifier(objectBebidas.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(objectBebidas.getNombre());
        feeTxt.setText("S/."+objectBebidas.getFee());
        descrptionTxt.setText(objectBebidas.getDescripcion());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectBebidas.setNumberInCart(numberOrder);
                managementCart.insertFood(objectBebidas);
            }
        });

    }
    /*private void getBundlePlatos(){
        objectPlatos=(BebidasDomain) getIntent().getSerializableExtra("object");


        int drawableResourceId=this.getResources().getIdentifier(objectPlatos.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(objectBebidas.getNombre());
        feeTxt.setText("S/."+objectBebidas.getFee());
        descrptionTxt.setText(objectBebidas.getDescripcion());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectBebidas.setNumberInCart(numberOrder);
                managementCart.insertFood(objectBebidas);
            }
        });

    }*/

    private void initView(){
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descrptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn=findViewById(R.id.minusBtn);
        picFood=findViewById(R.id.picFood);
    }
}