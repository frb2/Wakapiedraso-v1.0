package com.example.waka.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.Domain.PlatoFondoDomain;
import com.example.waka.R;

import java.util.ArrayList;

public class BebidasAdaptor extends RecyclerView.Adapter<BebidasAdaptor.ViewHolder> {
    ArrayList<BebidasDomain> bebidasDomains;

    public BebidasAdaptor(ArrayList<BebidasDomain> bebidasDomains){
        this.bebidasDomains =bebidasDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bebidas,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BebidasAdaptor.ViewHolder holder, int position) {
        holder.title.setText(bebidasDomains.get(position).getNombre());
        holder.fee.setText(String.valueOf(bebidasDomains.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(bebidasDomains.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

    }
    @Override
    public int getItemCount() {
        return bebidasDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titulo);
            fee=itemView.findViewById(R.id.food);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);

        }
    }
}
