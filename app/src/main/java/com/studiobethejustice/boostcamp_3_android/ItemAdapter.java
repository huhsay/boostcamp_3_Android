package com.studiobethejustice.boostcamp_3_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.studiobethejustice.boostcamp_3_android.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private static final String TAG = "ItemAdapter";

    private List<Item> items = new ArrayList<>();
    private Context mContext;

    public ItemAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Item item = items.get(position);
        holder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("link", item.getLink());
                mContext.startActivity(intent);
                Log.d(TAG, "onClick: item clicked"+position);
                Toast.makeText(mContext, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAllItem(ArrayList<Item> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private RatingBar ratingBar;
        private TextView tvPubYear;
        private TextView tvDirector;
        private TextView tvActors;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.text_title);
            ratingBar = itemView.findViewById(R.id.rating);
            tvPubYear = itemView.findViewById(R.id.text_year);
            tvDirector = itemView.findViewById(R.id.text_director);
            tvActors = itemView.findViewById(R.id.text_actor);
        }

        public void setItem(Item item) {


            RequestOptions options = new RequestOptions();
            options.centerCrop();
            Glide.with(mContext)
                    .load(item.getImage())
                    .apply(options)
                    .into(ivPoster);

            this.tvTitle.setText(Html.fromHtml(item.getTitle()));
            this.tvPubYear.setText(String.valueOf(item.getPubDate()));
            this.ratingBar.setRating(item.getUserRating() / 2);
            this.tvDirector.setText(Html.fromHtml(item.getDirector()));
            this.tvActors.setText(Html.fromHtml(item.getActor()));
        }
    }
}
