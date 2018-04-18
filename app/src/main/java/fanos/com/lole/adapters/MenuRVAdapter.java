package fanos.com.lole.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fanos.com.lole.R;
import fanos.com.lole.activities.CartActivity;
import fanos.com.lole.activities.ProgressActivity;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuVHolder> {
    Context mContext;

    public MenuRVAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MenuVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MenuVHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuVHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

     class MenuVHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         MenuVHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View view) {
             mContext.startActivity(new Intent(mContext, CartActivity.class));

         }
     }
}
