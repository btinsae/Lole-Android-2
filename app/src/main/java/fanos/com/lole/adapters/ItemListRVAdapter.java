package fanos.com.lole.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fanos.com.lole.R;
import fanos.com.lole.activities.DetailActivity;

/**
 * Created by Birhane on 2/17/2018.
 */

public class ItemListRVAdapter extends RecyclerView.Adapter<ItemListRVAdapter.ItemListViewHolder> {
    Context mContext;

    public ItemListRVAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

     class ItemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         ItemListViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View view) {
           Intent intent=new Intent(view.getContext(), DetailActivity.class);
           mContext.startActivity(intent);
         }
     }
}
