package com.mafish.mafish.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mafish.mafish.R;
import com.mafish.mafish.model.Fish;

import java.util.ArrayList;

public class FishListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Fish> fishList;

    public FishListAdapter(Context context, int layout, ArrayList<Fish> foodsList) {
        this.context = context;
        this.layout = layout;
        this.fishList = foodsList;
    }

    @Override
    public int getCount() {
        return fishList.size();
    }

    @Override
    public Object getItem(int position) {
        return fishList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView ivFish;
        TextView tvFishName, tvPrice, tvDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.tvFishName = (TextView) row.findViewById(R.id.tv_fish_name);
            holder.tvPrice = (TextView) row.findViewById(R.id.tv_price);
            holder.tvDescription = (TextView) row.findViewById(R.id.tv_description);
            holder.ivFish = (ImageView) row.findViewById(R.id.iv_fish);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Fish fish = fishList.get(position);

        holder.tvFishName.setText(fish.getFish_name());
        holder.tvPrice.setText(fish.getPrice());
        holder.tvDescription.setText(fish.getDescription());

        byte[] fishImage = fish.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(fishImage, 0, fishImage.length);
        holder.ivFish.setImageBitmap(bitmap);

        return row;
    }
}
