package fanos.com.lole.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        Glide.with(mContext).load(R.drawable.food_two).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

     class MenuVHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.appCompatImageView)
        AppCompatImageView imageView;
         MenuVHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
             ButterKnife.bind(this,itemView);
        }

         @Override
         public void onClick(View view) {
             mContext.startActivity(new Intent(mContext, CartActivity.class));

         }
     }
}
