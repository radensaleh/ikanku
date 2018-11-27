package com.mafish.mafish.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mafish.mafish.DetailFishShopActivity;
import com.mafish.mafish.R;
import com.mafish.mafish.model.Fish;

import java.util.ArrayList;
import java.util.List;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.FishViewHolder> {

    private List<Fish> fishList = new ArrayList<>();
    Context c;
    public FishAdapter(List<Fish> fishList) {
        this.fishList = fishList;
    }

    @Override
    public FishAdapter.FishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fish, parent, false);
        FishViewHolder fishViewHolder = new FishViewHolder(view);
        return fishViewHolder;
    }

    @Override
    public void onBindViewHolder(FishAdapter.FishViewHolder holder, final int position) {
        holder.tvFishName.setText(fishList.get(position).getFish_name());
        holder.tvPrice.setText(fishList.get(position).getPrice());
        holder.tvDescription.setText(fishList.get(position).getDescription());
        byte[] fishImage = fishList.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(fishImage, 0, fishImage.length);
        holder.ivFish.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c, DetailFishShopActivity.class);

                i.putExtra("fish_name",fishList.get(position).getFish_name());
                i.putExtra("price",fishList.get(position).getPrice());
                i.putExtra("description",fishList.get(position).getDescription());
                i.putExtra("image",fishList.get(position).getImage());

                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fishList.size();
    }

    public static class FishViewHolder extends RecyclerView.ViewHolder {

        TextView tvFishName;
        TextView tvPrice;
        TextView tvDescription;
        ImageView ivFish;

        public FishViewHolder(View itemView) {
            super(itemView);

            tvFishName = (TextView) itemView.findViewById(R.id.tv_fish_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            ivFish = (ImageView) itemView.findViewById(R.id.iv_fish);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
