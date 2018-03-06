package fanos.com.lole.adapters;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fanos.com.lole.R;
import fanos.com.lole.activities.SearchableActivity;
import fanos.com.lole.model.ItemCategory;

/**
 * Created by Birhane on 2/17/2018.
 *
 * Horizontal Recyclerview Adapter form Item category list
 */

public class ItemCategoryRVAdapter extends RecyclerView.Adapter<ItemCategoryRVAdapter.ItemCategoryViewHolder>{

    private List<ItemCategory> list=new ArrayList<>();
    private Context mContext;

    public ItemCategoryRVAdapter(Context mContext,List<ItemCategory> list) {
        this.list=list;
        this.mContext=mContext;

    }

    @Override
    public ItemCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_category,parent,false);
        return new ItemCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemCategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AppCompatImageView categoryIcon;
        private AppCompatTextView categoryName;
        ItemCategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryIcon=itemView.findViewById(R.id.item_category_icon);
            categoryName=itemView.findViewById(R.id.item_category_name);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(view.getContext(), SearchableActivity.class);
            mContext.startActivity(intent);
        }
    }
}
