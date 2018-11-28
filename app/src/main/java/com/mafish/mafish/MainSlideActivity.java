package com.mafish.mafish;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainSlideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private SlideAdapter myAdapter;

    private TextView[] tdots;

    private Button btnNext;
    private Button btnPrev;
    private Button btnFinish;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slide);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myAdapter = new SlideAdapter(this);
        viewPager.setAdapter(myAdapter);

        dotLayout = (LinearLayout) findViewById(R.id.dots);

        btnNext   = (Button) findViewById(R.id.btnNext);
        btnPrev   = (Button) findViewById(R.id.btnPrev);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

        //onclick
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage + 1);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position){

        tdots = new TextView[5];
        dotLayout.removeAllViews();

        for(int i = 0; i < tdots.length; i++){
            tdots[i] = new TextView(this);
            tdots[i].setText(Html.fromHtml("&#8226;"));
            tdots[i].setTextSize(45);
            tdots[i].setTextColor(getResources().getColor(R.color.colorTransparantWhite));

            dotLayout.addView(tdots[i]);
        }

        if( tdots.length > 0 ){
            tdots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if( i == 0 ){
                btnNext.setEnabled(true);
                btnPrev.setEnabled(false);
                btnFinish.setEnabled(false);

                btnNext.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.INVISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("");
            }else if( i == tdots.length -1 ){
                btnNext.setEnabled(false);
                btnPrev.setEnabled(true);
                btnFinish.setEnabled(true);

                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.VISIBLE);

                btnFinish.setText("Finish");
                btnPrev.setText("Back");

                btnFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainSlideActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                });

            }else{
                btnNext.setEnabled(true);
                btnPrev.setEnabled(true);
                btnFinish.setEnabled(false);

                btnNext.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
