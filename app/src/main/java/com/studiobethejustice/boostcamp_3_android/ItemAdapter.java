package com.studiobethejustice.boostcamp_3_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.studiobethejustice.boostcamp_3_android.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<Item> items = new ArrayList<>();
    private Context mContext;

    public ItemAdapter(Context context) { this.mContext = context;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAllItem(ArrayList<Item> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvPubYear;
        TextView tvActors;
        RatingBar ratingBar;


        public ViewHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvPubYear = itemView.findViewById(R.id.text_year);
            tvActors = itemView.findViewById(R.id.text_actor);
            ratingBar = itemView.findViewById(R.id.rating);
        }

        public void setItem(Item item){

            Glide.with(mContext)
                    .load(item.getImage())
                    .into(ivPoster);

            this.tvTitle.setText(Html.fromHtml(item.getTitle()));
            this.tvPubYear.setText(String.valueOf(item.getPubDate()));
            this.ratingBar.setRating(item.getUserRating()/2);
            this.tvActors.setText(Html.fromHtml(item.getActor()));
        }
    }
}
