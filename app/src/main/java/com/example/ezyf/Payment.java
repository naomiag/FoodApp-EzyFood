package com.example.ezyf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity {
    RecyclerView iniRecyclerView;
    DBHelper db;
    TextView totalView;
    TextView thanksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_complete_activity);

        db = new DBHelper(this);
        Cursor res = db.viewData();
        iniRecyclerView = findViewById(R.id.recyclerViewIdComplete);

        totalView = findViewById(R.id.totalViewId);
        thanksView = findViewById(R.id.thanksMesId);

        int totalHarga = 0;

        if(res.getCount()==0){
            totalView.setText("  Total harga: Rp 0   ");
            return;
        }

        //isi array
        final List<String> namaProdukList = new ArrayList<>();
        final List<String> hargaProdukList = new ArrayList<>();
        final List<String> quanProdukList = new ArrayList<>();

        StringBuffer aBuf = new StringBuffer();
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
        //yhanks

        PaymentAdapter adapter = new PaymentAdapter(this, namaProduk, hargaProduk, quanProduk);
        //sampe sini

        iniRecyclerView.setAdapter(adapter);
        iniRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                db.deleteAllData();
                startActivity(intent);
                return true;

            case R.id.homeXml:
                intent = new Intent(this, MainActivity.class);
                db.deleteAllData();
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
