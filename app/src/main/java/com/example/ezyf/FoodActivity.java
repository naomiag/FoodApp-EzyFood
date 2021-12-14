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

public class FoodActivity extends AppCompatActivity {
    String[] txtIcon2;
    String[] txtPrice2;
    TextView cat;
    int[] imageId2 = {R.drawable.spatchockedgrilledchicken1, R.drawable.molassessglazedchicken2, R.drawable.veghakkanoodles3, R.drawable.sukiyakishiratakinoodles4,
            R.drawable.kimchinoodles5, R.drawable.heartyminestronesoup6};
    RecyclerView iniRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniRecyclerView = findViewById(R.id.iniRecyclerViewID);
        cat = findViewById(R.id.categoryId);
        cat.setText("Foods");
        txtIcon2 = getResources().getStringArray(R.array.foodkName);
        txtPrice2 = getResources().getStringArray(R.array.foodPrice);
        iniAdapterDrink adapter = new iniAdapterDrink(this, txtIcon2, imageId2, txtPrice2);

        iniRecyclerView.setAdapter(adapter);
        iniRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
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
