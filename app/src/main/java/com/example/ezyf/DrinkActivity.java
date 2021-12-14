package com.example.ezyf;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkActivity extends AppCompatActivity {
    String[] txtIcon;
    String[] txtPrice;
    TextView cat;
    int[] imageId = {R.drawable.boba1, R.drawable.choco_milk1, R.drawable.manggo1, R.drawable.mocha_latte1,
                        R.drawable.strawberry_smoothie1, R.drawable.teh_poci1, R.drawable.tehtarik1, R.drawable.terbotol1};
    RecyclerView iniRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniRecyclerView = findViewById(R.id.iniRecyclerViewID);
        cat = findViewById(R.id.categoryId);
        cat.setText("Drinks");
        txtIcon = getResources().getStringArray(R.array.drinkname);
        txtPrice = getResources().getStringArray(R.array.drinkPrice);
        iniAdapterDrink adapter = new iniAdapterDrink(this, txtIcon, imageId, txtPrice);

        iniRecyclerView.setAdapter(adapter);
        iniRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
