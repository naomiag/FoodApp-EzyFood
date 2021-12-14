package com.example.ezyf;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.IniViewHolder>{

    String[] namaProdukAdapter;
    String[] hargaProdukAdapter;
    String[] quanProdukAdapter;
    Context ct;

    DBHelper db;

    public PaymentAdapter (Context ct1, String[] namaProduk, String[] hargaProduk, String[] quanProduk ){
        this.ct = ct1;
        this.namaProdukAdapter = namaProduk;
        this.hargaProdukAdapter = hargaProduk;
        this.quanProdukAdapter = quanProduk;

        db = new DBHelper(ct);
    }

    @NonNull
    @Override
    public PaymentAdapter.IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iniInflater = LayoutInflater.from(ct);
        View iniView = iniInflater.inflate(R.layout.complete_card, parent, false);

        return new PaymentAdapter.IniViewHolder(iniView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.IniViewHolder holder, int position) {
        holder.viewHolderNama.setText(namaProdukAdapter[position]);
        holder.viewHolderHarga.setText("Rp " + hargaProdukAdapter[position]);
        holder.viewHolderQuan.setText(" X " + quanProdukAdapter[position]);
        int pos = position;

    }

    @Override
    public int getItemCount() {
        return namaProdukAdapter.length;
    }

    public class IniViewHolder extends RecyclerView.ViewHolder{

        TextView viewHolderNama;
        TextView viewHolderHarga;
        TextView viewHolderQuan;
        Button btnDelete;

        public IniViewHolder(@NonNull View itemView) {
            super(itemView);
            viewHolderNama = itemView.findViewById(R.id.myOrderNama);
            viewHolderHarga = itemView.findViewById(R.id.myOrderPrice);
            viewHolderQuan = itemView.findViewById(R.id.myOrderQuan);
            btnDelete = itemView.findViewById(R.id.myOrderDltBtn);
        }

    }


}
