package com.mafish.mafish;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mafish.mafish.Database.DbHandler;
import com.mafish.mafish.adapter.FishAdapter;
import com.mafish.mafish.model.Fish;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class AddFishShopActivity extends AppCompatActivity {

    EditText etFishName, etPrice, etDescription;
    Button btnSelectImage, btnSubmit, btnList;
    ImageView ivFish;

    final static int REQUEST_CODE_GALLERY = 999;

    private DbHandler dbHandler;
    private FishAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fish_shop);

        etFishName = findViewById(R.id.et_fish_name);
        etPrice = findViewById(R.id.et_price);
        etDescription = findViewById(R.id.et_description);
        ivFish = findViewById(R.id.iv_fish);
        btnSelectImage = findViewById(R.id.btn_select_image);
        btnSubmit = findViewById(R.id.btn_submit);

        dbHandler = new DbHandler(this);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddFishShopActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiForm();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Anda tidak memiliki akses!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivFish.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    // FUNGSI INI UNTUK MEMVALIDASI FORM JIKA ADA YANG KOSONG ATAU TIDAK
    // LALU DILANJUT UNTUK MENJALANKAN PERINTAH SELANJUTNYA
    private void validasiForm(){
        String fishName = etFishName.getText().toString();
        String price = etPrice.getText().toString();
        String description = etDescription.getText().toString();
        imageViewToByte(ivFish);

        if (fishName.isEmpty()){
            etFishName.setError("Nama ikan jangan kosong");
            etFishName.requestFocus();
        } if (price.isEmpty()){
            etPrice.setError("Price jangan kosong");
            etPrice.requestFocus();
        } else {
            dbHandler.tambahFish(new Fish(fishName, price, description, imageViewToByte(ivFish)));
            List<Fish> mahasiswaList = dbHandler.getAllFish();
            adapter = new FishAdapter(mahasiswaList);
            adapter.notifyDataSetChanged();

            Toast.makeText(AddFishShopActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}
