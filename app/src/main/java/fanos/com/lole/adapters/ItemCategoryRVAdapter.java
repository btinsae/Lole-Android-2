package fanos.com.lole.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;
import fanos.com.lole.activities.MenuActivity;
import fanos.com.lole.model.ItemCategory;

/**
 * Created by Birhane on 2/17/2018.
 * <p>
 * Horizontal Recyclerview Adapter form Item category list
 */

public class ItemCategoryRVAdapter extends RecyclerView.Adapter<ItemCategoryRVAdapter.ItemCategoryViewHolder> {

    private List<ItemCategory> list = new ArrayList<>();
    private Context mContext;
    private ItemListRVAdapter.ItemListClickListener itemListClickListener;

    ItemCategoryRVAdapter(Context mContext, List<ItemCategory> list, ItemListRVAdapter.ItemListClickListener listener) {
        this.list = list;
        this.mContext = mContext;
        this.itemListClickListener = listener;
    }

    @Override
    public ItemCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false);
        return new ItemCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemCategoryViewHolder holder, int position) {

        Glide.with(mContext).load(R.drawable.food_one).into(holder.categoryIcon);
    }

    public interface ItemClickListener {
        void onItemClickListener(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ItemCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_item_image)
        AppCompatImageView categoryIcon;
//        @BindView(R.id.item_category_name)
//        AppCompatTextView categoryName;

        ItemCategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);


        }

        @Override
        public void onClick(View view) {
            itemListClickListener.onClickListener(getAdapterPosition());
//            Intent intent = new Intent(view.getContext(), MenuActivity.class);
//            mContext.startActivity(intent);
        }
    }
}
