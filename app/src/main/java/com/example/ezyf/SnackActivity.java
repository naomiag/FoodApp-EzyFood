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

public class SnackActivity extends AppCompatActivity {
    String[] txtIcon2;
    String[] txtPrice2;
    TextView cat;
    int[] imageId2 = {R.drawable.beng1, R.drawable.cheetos1, R.drawable.chitatoayambumbu1, R.drawable.chitatomiegoreng1,
            R.drawable.lays1, R.drawable.milosnackbar1, R.drawable.silverqueen1, R.drawable.silverqueencrispy1};
    RecyclerView iniRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniRecyclerView = findViewById(R.id.iniRecyclerViewID);
        cat = findViewById(R.id.categoryId);
        cat.setText("Snacks");
        txtIcon2 = getResources().getStringArray(R.array.snackName);
        txtPrice2 = getResources().getStringArray(R.array.snackPrice);
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
