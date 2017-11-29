package com.example.mrpassword.kin01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    private Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    String ranName, ranImage;
    TextView txtclose;
    Button btnFollow;
    String selectchild;
    Food food = new Food();
    TypeF typeF = new TypeF();
    ArrayList<String> restlist ;

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
    private NavigationView nvDrawer;


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

    private void listfragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ListFragment.newInstance("List"))
                .commit();

    }

    //Random Function
    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    //End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar1 = findViewById(R.id.toolbar1);

//        TextView textView = (TextView) findViewById(R.id.textView3);
//        textView.setTextAppearance(getCallingActivity(),R.style.);
//
//
//        String names[] ={"A\t\t\t\t 50 บาท","B\t\t\t\t 50 บาท","C\t\t\t\t50 บาท","D\t\t\t\t 50 บาท"};


        setToolbar1();

        drawerLayout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nav_main);
        // Setup drawer view
        setupDrawerContent(nvDrawer);


        if (savedInstanceState == null) {
            recycleFragment();
        }


        BottomNavigationView bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(bnvSelectedListener);
        bnv.setOnNavigationItemReselectedListener(bnvReselectedListener);

        myDialog = new Dialog(this);

    }


    // POP UP FOOD //////////////////////////////////food
    TextView txtRandomName;
    public void ShowPopup(View v) {
        random();
        myDialog.setContentView(R.layout.popup_food);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (food.getName()!=null){
                    showrest();
                }
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        txtRandomName = (TextView) myDialog.findViewById(R.id.ranName);
        ImageView imageView = (ImageView) myDialog.findViewById(R.id.ranImage);
        TextView textD = (TextView) myDialog.findViewById(R.id.food_dis);

        txtRandomName.setText(food.getName());
        Picasso.with(this).load(food.getPic()).into(imageView);
        textD.setText(food.getFID());
    }
    void showrest(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.showrest, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("ร้านหารที่ขาย "+txtRandomName.getText());
        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,restlist);
        lv.setAdapter(adapter);
        alertDialog.show();
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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    public void random() {
        FirebaseDatabase.getInstance().getReference().child("TypeF").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int childcount = safeLongToInt(dataSnapshot.getChildrenCount());
//                selectchild = childcount+"";
                if (childcount == 0) return;
                Random rand = new Random();
                int random = rand.nextInt(childcount);
                selectchild = Integer.toString(random);
                typeF.setTID(dataSnapshot.child(selectchild).child("TID").getValue().toString());
//                food.setName(dataSnapshot.child(selectchild).child("TID").getValue().toString());
                Switch swfd = (Switch) findViewById(R.id.SWFD);
                Switch swfr = (Switch) findViewById(R.id.SWFR);
                Switch swfn = (Switch) findViewById(R.id.SWFN);
                if (swfd.isChecked() && swfn.isChecked() && swfr.isChecked()) {
                    String[] tmp = {"FD", "FN", "FR"};
                    Random generator = new Random();
                    int randomIndex = generator.nextInt(tmp.length);
                    typeF.setTID(tmp[randomIndex]);
                } else if (swfd.isChecked() && swfn.isChecked()) {
                    String[] tmp = {"FD", "FN"};
                    Random generator = new Random();
                    int randomIndex = generator.nextInt(tmp.length);
                    typeF.setTID(tmp[randomIndex]);
                } else if (swfn.isChecked() && swfr.isChecked()) {
                    String[] tmp = {"FN", "FR"};
                    Random generator = new Random();
                    int randomIndex = generator.nextInt(tmp.length);
                    typeF.setTID(tmp[randomIndex]);
                } else if (swfd.isChecked() && swfr.isChecked()) {
                    String[] tmp = {"FD", "FR"};
                    Random generator = new Random();
                    int randomIndex = generator.nextInt(tmp.length);
                    typeF.setTID(tmp[randomIndex]);
                } else if (swfn.isChecked()) {
                    typeF.setTID("FN");
                } else if (swfr.isChecked()) {
                    typeF.setTID("FR");
                } else if (swfd.isChecked()) {
                    typeF.setTID("FD");
                }
                FirebaseDatabase.getInstance().getReference().child("Food").child(typeF.getTID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int childcount = safeLongToInt(dataSnapshot.getChildrenCount());
//                        selectchild = childcount+"";
                        if (childcount == 0) return;
                        Random rand = new Random();
                        int random = rand.nextInt(childcount);
                        selectchild = Integer.toString(random + 1);
                        if (selectchild.length() == 1) {
                            selectchild = typeF.getTID() + "0" + selectchild;
                        } else {
                            selectchild = typeF.getTID() + selectchild;
                        }
                        if (dataSnapshot.child(selectchild).getValue().toString()!=null){
                            food.setName(dataSnapshot.child(selectchild).child("Name").getValue().toString());
                            food.setPic(dataSnapshot.child(selectchild).child("Pic").getValue().toString());
                            food.setFID(dataSnapshot.child(selectchild).child("FID").getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        restlist = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Rest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Log.e("ListRest-----",snapshot.getValue().toString());
                    for (DataSnapshot snapshot1 : snapshot.child("PFood").getChildren()) {
//                        Log.e("ListRest",snapshot.child("PFood").getValue().toString());

                        if (snapshot1.getKey().equals(food.getFID())) {
                            String format = "%-30s%-12s ";
                            String first = snapshot.child("Name").getValue().toString();
                            String price = snapshot1.getValue().toString();

                            String headerText = String.format(format, first, price);
                            restlist.add(headerText);
                            Log.e("ListRest", restlist.toString());
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    ///click drawer//////////////////////////////////////////////////////////////////
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_gallery:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_slideshow:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.nav_share:
                fragmentClass = OptionFragment.class;
                break;
            case R.id.nav_send:
                fragmentClass = InfoFragment.class;
                break;
            case R.id.log_nav:
                fragmentClass = LoginFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title

        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // The action bar home/up action should open or close the drawer.
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //////////////////////////////////////////////////////////////////////////////////////
}
