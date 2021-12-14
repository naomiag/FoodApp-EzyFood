package com.example.ezyf;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class iniAdapter extends RecyclerView.Adapter<iniAdapter.IniViewHolder> {

    String[] textAdapter;
    int[] imageIdAdapter;
    Context ct;

    public iniAdapter (Context ct1, String[] txt, int[] id){
        this.ct = ct1;
        this.textAdapter = txt;
        this.imageIdAdapter = id;
    }

    @NonNull
    @Override
    public IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iniInflater = LayoutInflater.from(ct);
        View iniView = iniInflater.inflate(R.layout.card, parent, false);

        return new IniViewHolder(iniView);
    }

    @Override
    public void onBindViewHolder(@NonNull IniViewHolder holder, int position) {
        holder.viewHolderNama.setText(textAdapter[position]);
        holder.viewHolderImage.setImageResource(imageIdAdapter[position]);

    }

    @Override
    public int getItemCount() {
        return textAdapter.length;
    }

    public class IniViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView viewHolderNama;
        ImageView viewHolderImage;


        public IniViewHolder(@NonNull View itemView) {
            super(itemView);
            viewHolderNama = itemView.findViewById(R.id.iniText);
            viewHolderImage = itemView.findViewById(R.id.iniImage);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            ct = itemView.getContext();
        }

        public void onClick(View view){
            final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    intent = new Intent(ct, DrinkActivity.class);
                    break;
                case 1:
                    intent = new Intent(ct, SnackActivity.class);
                    break;
                case 2:
                    intent = new Intent(ct, FoodActivity.class);
                    break;
                case 3:
                    intent = new Intent(ct, MainActivity.class);
                    Toast.makeText(ct, "Coming Soon", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    intent = new Intent(ct, MainActivity.class);
            }
            ct.startActivity(intent);
        }

    }
}
