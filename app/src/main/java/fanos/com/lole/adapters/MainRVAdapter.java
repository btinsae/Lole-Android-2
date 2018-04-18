package fanos.com.lole.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;
import fanos.com.lole.model.ItemCategory;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.MainRVholder> {
    Context mContext;
    List<ItemCategory> list = new ArrayList<>();

    public MainRVAdapter(Context mContext, List<ItemCategory> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MainRVholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_recycler_view, parent, false);
        return new MainRVholder(view);
    }

    @Override
    public void onBindViewHolder(MainRVholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MainRVholder extends RecyclerView.ViewHolder {
        @BindView(R.id.vertical_recycler_view)
        RecyclerView mRecyclerView;

        MainRVholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            mRecyclerView.setAdapter(new ItemCategoryRVAdapter(mContext, list));
        }
    }
}
