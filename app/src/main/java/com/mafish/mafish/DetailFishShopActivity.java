package com.mafish.mafish;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFishShopActivity extends AppCompatActivity {

    TextView tvFishName, tvPrice, tvDescription;
    ImageView ivFish;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fish_shop);

        Intent i=getIntent();

        final String fishName=i.getExtras().getString("fish_name");
        final String price=i.getExtras().getString("price");
        final String description=i.getExtras().getString("description");
        final byte[] fishImage = i.getExtras().getByteArray("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(fishImage, 0, fishImage.length);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rincian Ikan");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        tvFishName = (TextView) findViewById(R.id.tv_fish_name);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        ivFish = (ImageView) findViewById(R.id.iv_fish);
        btnBuy = findViewById(R.id.btn_buy);

        tvFishName.setText(fishName);
        tvPrice.setText(price);
        tvDescription.setText(description);
        ivFish.setImageBitmap(bitmap);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(btnBuy, "Ikan terbeli! fitur ini masih dalam proses:)", Snackbar.LENGTH_LONG);
            }
        });
        }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
