package com.example.ezyf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] txtIcon;
    int[] imageId = {R.drawable.drink1, R.drawable.snack1, R.drawable.food1, R.drawable.topup1};
    DBHelper db1;

    RecyclerView iniRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintitle);

        iniRecyclerView = findViewById(R.id.iniRecyclerViewID);
        txtIcon = getResources().getStringArray(R.array.title);

        iniAdapter adapter = new iniAdapter(this, txtIcon, imageId);

        db1 = new DBHelper(this);
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
                Toast.makeText(this, "You're at Homepage", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}