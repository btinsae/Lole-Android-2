package fanos.com.lole.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private Context mContext;
    private List<ItemCategory> list;
    final ItemListRVAdapter.ItemListClickListener listener;

    public MainRVAdapter(Context mContext, ItemListRVAdapter.ItemListClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void setList(List<ItemCategory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<ItemCategory> getList() {
        return list;
    }

    @NonNull
    @Override
    public MainRVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_recycler_view, parent, false);
        return new MainRVholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MainRVholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.vertical_recycler_view)
        RecyclerView mRecyclerView;

        MainRVholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(new ItemCategoryRVAdapter(mContext, list, listener));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
