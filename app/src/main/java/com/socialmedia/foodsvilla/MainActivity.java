package com.socialmedia.foodsvilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.socialmedia.foodsvilla.Adapters.MainAdapter;
import com.socialmedia.foodsvilla.Models.MainModel;
import com.socialmedia.foodsvilla.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Veg Burger with extra cheese"));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "12", "Crunchy pizza with cheese"));
        list.add(new MainModel(R.drawable.momos, "Momos", "8", "Veg Steamed and fry both available"));
        list.add(new MainModel(R.drawable.rolls, "Rolls Mania", "10", "Aloo tikka and Paneer rool"));
        list.add(new MainModel(R.drawable.noodels, "Spicy Noodels", "10", "Spicy and non-sticky noodles"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.orders:
               startActivity(new Intent(MainActivity.this, OrderActivity.class));
               break;
       }
        return super.onOptionsItemSelected(item);
    }
}