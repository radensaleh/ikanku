package com.mafish.mafish;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.mafish.mafish.Database.DbHelper;

public class MainActivity extends AppCompatActivity {

    CardView cvJualBeliIkan, cvInfoHargaIkan, cvSuratJalan, cvLaporkan, cvMonitoring;
    public static DbHelper sqLiteHelper;
    TextView tvNoHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvJualBeliIkan = findViewById(R.id.cv_jual_beli_ikan);
        cvInfoHargaIkan = findViewById(R.id.cv_info_harga_ikan);
        cvSuratJalan = findViewById(R.id.cv_surat_jalan);
        cvLaporkan = findViewById(R.id.cv_laporkan);
        cvMonitoring = findViewById(R.id.cv_monitoring);

        tvNoHp = findViewById(R.id.tv_nohp);

        cvJualBeliIkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FishShopActivity.class);
                startActivity(i);
            }
        });

        cvInfoHargaIkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InfoPriceFishActivity.class);
                startActivity(i);
            }
        });

        cvSuratJalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InfoSipiActivity.class);
                startActivity(i);
            }
        });

        cvLaporkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(i);
            }
        });

        cvMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, MonitoringActivity.class);
//                startActivity(i);
                Snackbar.make(cvMonitoring, "Fitur ini masih dalam proses:)", Snackbar.LENGTH_LONG).show();

            }
        });

        Intent i=getIntent();

        final String noHandphone=i.getExtras().getString("KEY_NO_HP");
        tvNoHp.setText(noHandphone);
    }
}