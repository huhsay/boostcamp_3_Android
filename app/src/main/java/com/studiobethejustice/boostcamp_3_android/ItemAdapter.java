package com.studiobethejustice.boostcamp_3_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
                Bitmap backArrowBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_arrow_back_white_14dp);

                // chrome custom tabs
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                builder.setCloseButtonIcon(backArrowBitmap);
                builder.setStartAnimations(mContext, R.anim.slide_in_right, R.anim.slide_out_left);
                builder.setExitAnimations(mContext, R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent intent = builder.build();
                intent.launchUrl((AppCompatActivity) mContext, Uri.parse(item.getLink()));
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
