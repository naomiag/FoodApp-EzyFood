package com.example.ezyf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyOrder extends AppCompatActivity {

    RecyclerView iniRecyclerView;
    DBHelper db;
    TextView totalView;
    TextView emptyView;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_activity);

        db = new DBHelper(this);
        Cursor res = db.viewData();
        iniRecyclerView = findViewById(R.id.recyclerViewIdMyOrder);

        totalView = findViewById(R.id.totalViewId);
        emptyView = findViewById(R.id.emptyId);
        btnPay = findViewById(R.id.payId);

        int totalHarga = 0;

        if(res.getCount()==0){
            totalView.setText("  Total harga: Rp 0   ");
            emptyView.setText("No Item yet");
            return;
        }

        //isi array
        final List<String> namaProdukList = new ArrayList<>();
        final List<String> hargaProdukList = new ArrayList<>();
        final List<String> quanProdukList = new ArrayList<>();

        res.moveToFirst();

        do{
            final String aProduk = res.getString(1);
            final String aHarga = res.getString(2);
            final String aQuan = res.getString(3);

            namaProdukList.add(new String(aProduk));
            hargaProdukList.add(new String(aHarga));
            quanProdukList.add(new String(aQuan));

            final String harga = res.getString(4);
            int hargaToInt = Integer.valueOf(harga);
            totalHarga += hargaToInt;

        }while(res.moveToNext());

        String[] namaProduk, hargaProduk, quanProduk;

        namaProduk = namaProdukList.toArray(new String[namaProdukList.size()]);
        hargaProduk = hargaProdukList.toArray(new String[hargaProdukList.size()]);
        quanProduk = quanProdukList.toArray(new String[quanProdukList.size()]);
        String totaltoString = String.valueOf(totalHarga);

        totalView.setText("  Total harga: Rp " + totaltoString + "   ");

        iniAdapterMyOrder adapter = new iniAdapterMyOrder(this, namaProduk, hargaProduk, quanProduk);
        iniRecyclerView.setAdapter(adapter);
        iniRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Payment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater iniInflater = getMenuInflater();
        iniInflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.myOrderXml:
                intent = new Intent(this, MyOrder.class);
                startActivity(intent);
                return true;

            case R.id.homeXml:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
