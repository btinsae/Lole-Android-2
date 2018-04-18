package fanos.com.lole.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fanos.com.lole.R;
import fanos.com.lole.activities.ProgressActivity;

/**
 * Created by Birhane on 2/17/2018.
 */

public class ItemListRVAdapter extends RecyclerView.Adapter<ItemListRVAdapter.ItemListViewHolder> {
    private Context mContext;
    private List<? extends Object> list = new ArrayList<>();

    public ItemListRVAdapter(Context mContext, List<? extends Object> list) {
        this.mContext = mContext;
        this.list=list;
    }

    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ProgressActivity.class);
            mContext.startActivity(intent);
        }
    }
}
