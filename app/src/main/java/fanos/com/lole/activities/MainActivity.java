package fanos.com.lole.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.TextView;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fanos.com.lole.R;
import fanos.com.lole.adapters.ItemCategoryRVAdapter;
import fanos.com.lole.adapters.ItemListRVAdapter;
import fanos.com.lole.adapters.MainRVAdapter;
import fanos.com.lole.adapters.ViewPagerAdapter;
import fanos.com.lole.fragments.DrinkFragment;
import fanos.com.lole.fragments.FoodFragment;
import fanos.com.lole.fragments.LaundryFragment;
import fanos.com.lole.fragments.PriceFragment;
import fanos.com.lole.fragments.SpeedFragment;
import fanos.com.lole.model.ItemCategory;
import fanos.com.lole.model.Order;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SpeedFragment.OnFragmentInteractionListener, FoodFragment.OnFragmentInteractionListener, PriceFragment.OnFragmentInteractionListener, ItemListRVAdapter.ItemListClickListener {

    @BindView(R.id.message)
    @Nullable
    TextView mTextMessage;


//    @BindView(R.id.navigation)
//    BottomNavigationView navigation;
    @BindView(R.id.item_category)
    RecyclerView mRecyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private static final String EXTRA_MENU_ITEM = "menu item";

    //Firebase auth instance
    private FirebaseAuth mAuth;
    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 123;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    // mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_account:
//                    // mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_order:
//                    startActivity(new Intent(MainActivity.this, LaundryActivity.class));
//                    return true;
//                case R.id.navigation_checkout:
//                    // mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
//            return false;
//        }
//    };
    private boolean firstImage;
    private MainRVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bar);
        setSupportActionBar(bar);
       // setSupportActionBar(toolbar);

//        setupViewPager(viewPager);

//        tabLayout.setupWithViewPager(viewPager);


     //   navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new MainRVAdapter(this, this);
        mAdapter.setList(list());
        mRecyclerView.setAdapter(mAdapter);

        //Navigation Drawer

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
//
        navigationView.setNavigationItemSelectedListener(this);
    }

//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new FoodFragment(), "Delight");
//        adapter.addFragment(new PriceFragment(), "Price");
//        adapter.addFragment(new SpeedFragment(), "Speed");
//        viewPager.setAdapter(adapter);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this, OrderActivity.class));
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(MainActivity.this, SplashActivity.class));
                            finish();
                        }
                    });
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private List<ItemCategory> list() {
        List<ItemCategory> list = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            list.add(new ItemCategory(R.drawable.ic_account_circle_black_24dp, "Pizza"));
        }
        return list;
    }


    @Override
    public void onClickListener(int position) {
        Intent menuActivityIntent = new Intent(this, MenuActivity.class);
        menuActivityIntent.putExtra(EXTRA_MENU_ITEM, mAdapter.getList().get(position));
        startActivity(menuActivityIntent);
    }
}
