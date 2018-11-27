package com.mafish.mafish;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mafish.mafish.adapter.FishAdapter;
import com.mafish.mafish.model.Fish;

import java.util.ArrayList;
import java.util.List;

public class FishShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FishAdapter adapter;
    private DbHandler dbHandler;
    private List<Fish> fishList = new ArrayList<>();
    FloatingActionButton fab;
    CardView cvTuna, cvTuna2;

    private Spinner spinner;
    private String[] sort = {
            "Termurah",
            "Termahal",
            "Terfavorite",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_shop);

        spinner = findViewById(R.id.spinner_sort);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sort);
        // mengeset Array Adapter tersebut ke Spinner
        spinner.setAdapter(adapter);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();

        fab = findViewById(R.id.fab);
        cvTuna = findViewById(R.id.cv_tuna);
        cvTuna2 = findViewById(R.id.cv_tuna2);

        cvTuna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FishShopActivity.this, DetailTunaFishShopActivity.class);
                startActivity(i);
            }
        });

        cvTuna2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FishShopActivity.this, DetailTunaFishShopActivity.class);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FishShopActivity.this, AddFishShopActivity.class);
                startActivity(i);

                // mengeset listener untuk mengetahui saat item dipilih
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                        Snackbar.make(spinner, adapter.getItem(i), Snackbar.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }

    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_fish);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DbHandler(FishShopActivity.this);
        fishList = dbHandler.getAllFish();
        adapter = new FishAdapter(fishList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Fish fish = fishList.get(position);
                            String fishName = fish.getFish_name();
                            String price = fish.getPrice();
                            String description = fish.getDescription();

                            Intent i=new Intent(FishShopActivity.this, DetailFishShopActivity.class);

                            i.putExtra("fish_name",fishList.get(position).getFish_name());
                            i.putExtra("price",fishList.get(position).getPrice());
                            i.putExtra("description",fishList.get(position).getDescription());
                            i.putExtra("image",fishList.get(position).getImage());

                            startActivity(i);
//                            Toast.makeText(FishShopActivity.this, "Klik di " + fishName, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}

