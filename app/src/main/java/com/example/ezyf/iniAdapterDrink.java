package com.example.ezyf;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class iniAdapterDrink extends RecyclerView.Adapter<iniAdapterDrink.IniViewHolder> {

    String[] textAdapter;
    int[] imageIdAdapter;
    String[] priceAdapter;
    Context ct;

    public iniAdapterDrink (Context ct1, String[] txt, int[] id, String[] price){
        this.ct = ct1;
        this.textAdapter = txt;
        this.imageIdAdapter = id;
        this.priceAdapter = price;

    }

    @NonNull
    @Override
    public IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iniInflater = LayoutInflater.from(ct);
        View iniView = iniInflater.inflate(R.layout.drink_activity, parent, false);

        return new IniViewHolder(iniView);
    }

    @Override
    public void onBindViewHolder(@NonNull IniViewHolder holder, int position) {
        holder.viewHolderNama.setText(textAdapter[position]);
        holder.viewHolderImage.setImageResource(imageIdAdapter[position]);
        holder.viewHolderPrice.setText(priceAdapter[position]);
    }

    @Override
    public int getItemCount() {
        return textAdapter.length;
    }

    public class IniViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView viewHolderNama;
        ImageView viewHolderImage;
        TextView viewHolderPrice;

        public IniViewHolder(@NonNull View itemView) {
            super(itemView);
            viewHolderNama = itemView.findViewById(R.id.iniText);
            viewHolderImage = itemView.findViewById(R.id.iniImage);
            viewHolderPrice = itemView.findViewById(R.id.iniHarga);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            ct = itemView.getContext();
        }

        public void onClick(View view){
            Intent intent = new Intent(ct, OrderActivity.class);

            intent.putExtra("namaMinuman", textAdapter[getAdapterPosition()]);
            intent.putExtra("hargaMinuman", priceAdapter[getAdapterPosition()]);

            ct.startActivity(intent);
        }

    }
}
