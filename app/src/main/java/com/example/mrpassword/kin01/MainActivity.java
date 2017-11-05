package com.example.mrpassword.kin01;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    private Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;


    private BottomNavigationView.OnNavigationItemSelectedListener bnvSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home_bar:
                            recycleFragment();
                            return true;
                        case R.id.lb_bar:
                            libaryFragment();
                            setToolbar1();
                            toggle.syncState();
                            return true;
                        case R.id.search_bar:
                            searchFragment();
                            setToolbar1();
                            toggle.syncState();
                            return true;
                        case R.id.acc_bar:
                            accountFragment();
                            setToolbar1();
                            toggle.syncState();
                            return true;
                    }
                    return false;
                }
            };

    private BottomNavigationView.OnNavigationItemReselectedListener bnvReselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {

        }
    };


    private void accountFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AccountFragment.newInstance("Account"))
                .commit();
    }


    private void libaryFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LibaryFragment.newInstance("Libary"))
                .commit();
    }

    private void searchFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance("Search"))
                .commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Test Database
        // Write a message to the database

        toolbar1 = findViewById(R.id.toolbar1);

        setToolbar1();

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);

        if (savedInstanceState == null) {
            recycleFragment();
        }
        BottomNavigationView bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(bnvSelectedListener);
        bnv.setOnNavigationItemReselectedListener(bnvReselectedListener);

        myDialog = new Dialog(this);
    }


    // POP UP FOOD //////////////////////////////////food
    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.popup_food);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    //////////////////////////////////////////////////////food

    private void setToolbar1() {
        setSupportActionBar(toolbar1);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void recycleFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RecyclerFragment.newInstance(500))
                .commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }


    //menu bar right corner /////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reset_default:
                Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }
}
