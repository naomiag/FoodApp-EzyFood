package com.example.ezyf;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class iniAdapterMyOrder extends RecyclerView.Adapter<iniAdapterMyOrder.IniViewHolder> {

    String[] namaProdukAdapter;
    String[] hargaProdukAdapter;
    String[] quanProdukAdapter;
    Context ct;
    DBHelper db;

    public iniAdapterMyOrder (Context ct1, String[] namaProduk, String[] hargaProduk, String[] quanProduk ){
        this.ct = ct1;
        this.namaProdukAdapter = namaProduk;
        this.hargaProdukAdapter = hargaProduk;
        this.quanProdukAdapter = quanProduk;

        db = new DBHelper(ct);
    }

    @NonNull
    @Override
    public IniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iniInflater = LayoutInflater.from(ct);
        View iniView = iniInflater.inflate(R.layout.myorder_card, parent, false);

        return new IniViewHolder(iniView);
    }

    @Override
    public void onBindViewHolder(@NonNull IniViewHolder holder, int position) {
        holder.viewHolderNama.setText(namaProdukAdapter[position]);
        holder.viewHolderHarga.setText("Rp " + hargaProdukAdapter[position]);
        holder.viewHolderQuan.setText(" X "+quanProdukAdapter[position]);
        int pos = position;

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parameter1 = namaProdukAdapter[pos];
                String parameter3 =  quanProdukAdapter[pos];

                boolean check = db.deleteData(parameter1, parameter3);
                if(check==true){
                    Toast.makeText(view.getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), MyOrder.class);
                    view.getContext().startActivity(intent);

                }
                else
                    Toast.makeText(view.getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
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
