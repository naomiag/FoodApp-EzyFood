package com.example.ezyf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    Button btnMore, btnCancel;
    TextView namaMinuman;
    TextView hargaMinuman;
    TextView quantity;
    EditText inputQuan;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        Intent intent = getIntent();
        DBHelper db = new DBHelper(this);

        String namaMinuman1 = intent.getStringExtra("namaMinuman");
        String hargaMinuman1 = intent.getStringExtra("hargaMinuman");

        btnMore = findViewById(R.id.btnOrdMor);
        btnCancel = findViewById(R.id.btnback);
        namaMinuman = findViewById(R.id.orderJudul);
        hargaMinuman = findViewById(R.id.orderHarga);
        quantity = findViewById(R.id.orderQuan);
        inputQuan = findViewById(R.id.orderInput);

        namaMinuman.setText(namaMinuman1);
        hargaMinuman.setText(hargaMinuman1);
        quantity.setText("Quantity");

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hrgminum = hargaMinuman1;
                String drinkQuan = inputQuan.getText().toString();
                if(drinkQuan.equals("0")){
                    inputQuan.requestFocus();
                    inputQuan.setError("FIELD CANNOT BE 0");
                    return;
                }
                else if(!drinkQuan.matches("[0-9]+")){
                    inputQuan.requestFocus();
                    inputQuan.setError("ENTER ONLY NUMERICAL CHARACTER");
                    return;
                }

                hrgminum = hrgminum.replace("Rp ","");
                hrgminum = hrgminum.replace(".","");

                int flag;
                int jml=0;
                int drinkQuanInt = Integer.parseInt(drinkQuan);
                String quanlama; //quan sebelum diupdate
                int quanlamaToInt = 0;
                int hargaMinumanInt = Integer.valueOf(hrgminum);

                Cursor res = db.check(namaMinuman1);
                if(res.getCount()!=0){
                    res.moveToNext();
                    quanlama = res.getString(3);
                    quanlamaToInt = Integer.valueOf(quanlama);
                    flag=1;
                }
                else{
                    flag=0;
                    quanlamaToInt=0;
                }

                jml = hargaMinumanInt * (drinkQuanInt + quanlamaToInt);
                String jumlah = String.valueOf(jml);

                if(flag==1){
                    //ada
                    String quanbarutoString = String.valueOf(drinkQuanInt + quanlamaToInt);
                    boolean check = db.update(namaMinuman1, hrgminum, quanbarutoString, jumlah);
                    if(check==true){
                        Toast.makeText(view.getContext(), "Added to Basket", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(view.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    //gk ada
                    boolean check2 = db.insertData(namaMinuman1, hrgminum, drinkQuan, jumlah);
                    if(check2==true)
                        Toast.makeText(view.getContext(), "Added to Basket", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(view.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
