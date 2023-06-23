package com.example.waka.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.waka.Domain.BebidasDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);

    }
    public void insertFood(BebidasDomain item){
        ArrayList<BebidasDomain> listFood=getListCart();
                boolean existAlready=false;
                int n=0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getDescripcion().equals(item.getNombre())){
                existAlready=true;
                n=i;
                break;

            }
        }
            if(existAlready){
                listFood.get(n).setNumberInCart(item.getNumberInCart());
            }else{
                listFood.add(item);

            }
            tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<BebidasDomain> getListCart(){
        return tinyDB.getListObject("CartList");

    }
}
