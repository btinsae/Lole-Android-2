package fanos.com.lole.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;
import fanos.com.lole.adapters.MenuRVAdapter;

public class MenuActivity extends AppCompatActivity implements MenuRVAdapter.MenuItemClickListener {
    @BindView(R.id.menu_recycler_view)
    RecyclerView mRecyclerView;
    MenuRVAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        mAdapter = new MenuRVAdapter(this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMenuItemClickListener(int position) {
        Intent startDetailActivity = new Intent(this, CartActivity.class);
        mAdapter.getMenuItems().get(position);
        startActivity(startDetailActivity);
    }
}
