package com.mafish.mafish;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class InfoSipiActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sipi);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Info Surat Jalan");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.web_view);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "Surat Izin Penangkapan Ikan ( SIPI ) adalah surat izin yang harus dimiliki setiap kapal perikanan berbendera Indonesia yang melakukan kegiatan penangkapan ikan diperairan Indonesia dan atau Zona Ekonomi Eksklusif Indonesia  ( ZEEI ) yang merupakan bagian yang tidak terpisahkan dari IUP yang selanjutnya disebut SPI. Masa berlaku SIPI selama 3 tahun.\n" +
                "\n" +
                "Surat Izin Penangkapan Ikan ( SIPI ) tidak diperlukan bagi :\n" +
                "\n" +
                "Penangkapan ikan dengan mempergunakan kapal perikanan tidak bermotor.\n" +
                "Penangkapan ikan dengan mempergunakan kapal perikanan bermotor dalam ( inboard ) dan motor luar (outboard) yang berbobot kurang dari 5 GT dan atau dengan kekuatan mesin tidak lebih dari 10 PK dan berbobot lebih dari 10 GT dan atau dengan berkekuatan lebih dari 30 PK.\n" +
                "Persyaratan pembuatan SIPI :\n <br>" +
                "\n" +
                "Surat permohonan\n" +
                "Surat pernyataan keabsahan dokumen ( materai 6000 )\n\n" +
                "Surat pernyataan alat tangkap ( materai 6000 )\n" +
                "Surat kuasa 9 apabila si pemilik kapal tidak dapat hadir / materai 6000 )\n" +
                "Surat kuasa ( apabila si pemilik kapal tidak dapat hadir / materai 6000 )\n" +
                "Fotocopy KTP\n" +
                "NPWP\n" +
                "PAS Besar untuk kapal > 7-30 GT, PAS Kecil untuk kapal 5-7 GT\n" +
                "Surat Ukur\n" +
                "Sertifikat kelaikan dan pengawakan kapal penangkap ikan\n" +
                "Gross Akte\n" +
                "Pas photo 4 x 6 berwarna sebanyak 2 lb\n" +
                "Berita acara cek fisik kapal\n" +
                "Retribusi SIPI berdasarkan PERDA Provinsi Banten No : 9 Tahun 2011 sebesar Rp 15.000/GT/3 tahun";
        text+= "</p></body></html>";
        webView.loadData(text, "text/html", "utf-8");

    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}
