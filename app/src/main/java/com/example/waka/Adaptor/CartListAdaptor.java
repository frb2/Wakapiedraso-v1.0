package com.example.waka.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Helper.ManagementCart;
import com.example.waka.Interface.ChangeNumberItemsListener;
import com.example.waka.R;

import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {
    private ArrayList<BebidasDomain> bebidasDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdaptor(ArrayList<BebidasDomain> bebidasDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.bebidasDomains=bebidasDomains;
        this.managementCart=new ManagementCart(context);
        this.changeNumberItemsListener=changeNumberItemsListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdaptor.ViewHolder holder, int position) {
        holder.title.setText(bebidasDomains.get(position).getNombre());
        holder.feeEachItem.setText(String.valueOf(bebidasDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((bebidasDomains.get(position).getNumberInCart()*bebidasDomains.get(position).getFee())*100)/100));
        holder.num.setText(String.valueOf(bebidasDomains.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(bebidasDomains.get(position).getPic()
                ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                managementCart.plusNumberBebidas(bebidasDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });

            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                managementCart.minusNumberBebidas(bebidasDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return bebidasDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
        }

    }
}
