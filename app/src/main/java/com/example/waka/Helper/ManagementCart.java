package com.example.waka.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Domain.PlatosDomain;
import com.example.waka.Interface.ChangeNumberItemsListener;

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
            tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show();
    }
    /*public void insertFoodPlatos(PlatosDomain item){
        ArrayList<PlatosDomain> listPlatos=getListCart();
        boolean existAlready=false;
        int n=0;
        for (int i = 0; i < listPlatos.size(); i++) {
            if (listPlatos.get(i).getDescripcion().equals(item.getNombre())){
                existAlready=true;
                n=i;
                break;

            }
        }
        if(existAlready){
            listPlatos.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listPlatos.add(item);

        }
        tinyDB.putListObject("CartList",listPlatos);
        Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show();
    }*/

    public ArrayList<BebidasDomain> getListCart(){
        return tinyDB.getListObject("CartList");

    }


    public void plusNumberBebidas(ArrayList<BebidasDomain> listBebidas, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listBebidas.get(position).setNumberInCart(listBebidas.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listBebidas);
        changeNumberItemsListener.changed();
    }
    public void minusNumberBebidas(ArrayList<BebidasDomain> listBebidas, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if ((listBebidas.get(position).getNumberInCart() == 1)) {
            listBebidas.remove(position);
        }else {
            listBebidas.get(position).setNumberInCart(listBebidas.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listBebidas);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<BebidasDomain> listBebidas=getListCart();
        double fee=0;
        for (int i=0; i<listBebidas.size();i++){
            fee=fee+(listBebidas.get(i).getFee()*listBebidas.get(i).getNumberInCart());

        }
        return fee;
    }
}
