package com.example.waka.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waka.Domain.PlatosDomain;
import com.example.waka.R;

import java.util.ArrayList;

public class PlatosAdaptor extends RecyclerView.Adapter<PlatosAdaptor.ViewHolder> {
    ArrayList<PlatosDomain> platosDomains;

    public PlatosAdaptor(ArrayList<PlatosDomain> platosDomains){
        this.platosDomains =platosDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_platos,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatosAdaptor.ViewHolder holder, int position) {
        holder.title.setText(platosDomains.get(position).getNombre());
        holder.fee.setText(String.valueOf(platosDomains.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(platosDomains.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

    }
    @Override
    public int getItemCount() {
        return platosDomains.size();
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
