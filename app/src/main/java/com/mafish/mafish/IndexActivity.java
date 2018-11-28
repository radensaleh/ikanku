package com.mafish.mafish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    private Button btnLanjut;
    private ImageView img;
    private TextView txtindex, txtdesc;
    private Animation uptodown, downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        btnLanjut = (Button) findViewById(R.id.btnLanjut);

        img = (ImageView) findViewById(R.id.img);

        txtindex  = (TextView) findViewById(R.id.txtindex);
        txtdesc   = (TextView) findViewById(R.id.txtdesc);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        img.setAnimation(uptodown);
        txtindex.setAnimation(uptodown);
        txtdesc.setAnimation(downtoup);
        btnLanjut.setAnimation(downtoup);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IndexActivity.this, MainSlideActivity.class);
                startActivity(i);
            }
        });
    }
}
