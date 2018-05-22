package fanos.com.lole.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;
import fanos.com.lole.adapters.MenuRVAdapter;
import fanos.com.lole.model.Order;
import fanos.com.lole.viewmodel.OrderViewModel;

public class OrderActivity extends AppCompatActivity implements MenuRVAdapter.MenuItemClickListener {

    private final static String TAG = OrderActivity.class.getSimpleName();

    @BindView(R.id.rv_orders)
    RecyclerView mRecyclerView;

    private MenuRVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ButterKnife.bind(this);

        mAdapter = new MenuRVAdapter(this, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);


        setUpViewModel();
    }

    private void setUpViewModel() {

        OrderViewModel viewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        viewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                Log.d(TAG, "Updating list of orders from LiveData in ViewMModel");
            }
        });
    }

    @Override
    public void onMenuItemClickListener(int position) {

    }
}
