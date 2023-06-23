package com.example.waka.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waka.Domain.BebidasDomain;
import com.example.waka.R;
import com.example.waka.Activity.ShowDetailActivity;

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
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object",bebidasDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

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
