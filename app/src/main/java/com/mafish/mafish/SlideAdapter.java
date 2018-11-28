package com.mafish.mafish;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {


    Context ctx;
    LayoutInflater LI;

    //list of images
    public int[] list_images = {
            R.drawable.letter,
            R.drawable.fish,
            R.drawable.shop,
            R.drawable.report,
            R.drawable.map
    };

    //list of title
    public String[] list_title = {
            "INFO SURAT JALAN",
            "INFO HARGA IKAN",
            "YUK JUAL BELI IKAN",
            "YUK LAPORKAN",
            "MONITORING NELAYAN"
    };

    //Llist of description
    public String[] list_desc = {
            "'' Melakukan perjanjian surat jalan tanpa harus mendatangi kantor. ''",
            "'' Memantau harga ikan terbaru dari seluruh Indonesia. ''",
            "'' Melakukan jual beli antara pembeli langsung kepada nelayan, sehingga harga beli lebih mudah dan harga jual lebih tinggi ''",
            "'' Melaporkan berbagai permasalahan yang ditemui oleh nelayan atau pihak yang berkaitan ''",
            "'' Memantau posisi nelayan secara realtime sehingga jika terjadi sesuatu dapat segera ditindaklanjuti ''"
    };

    //list of backgrounds colors
    public int[] list_bg = {
            Color.rgb(114,146,202),
            Color.rgb(134,198,218),
            Color.rgb(234,184,55),
            Color.rgb(91,173,50),
            Color.rgb(219,76,76)
    };


    public SlideAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LI = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View v = LI.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) v.findViewById(R.id.slideLL);
        ImageView imgslide = (ImageView) v.findViewById(R.id.slideImg);
        TextView textTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView textDesc  = (TextView) v.findViewById(R.id.txtDescription);

        layoutslide.setBackgroundColor(list_bg[position]);
        imgslide.setImageResource(list_images[position]);
        textTitle.setText(list_title[position]);
        textDesc.setText(list_desc[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

}
