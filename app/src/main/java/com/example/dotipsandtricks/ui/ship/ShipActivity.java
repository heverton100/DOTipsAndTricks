package com.example.dotipsandtricks.ui.ship;

import android.content.Intent;
import android.os.Bundle;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.ui.ship.shiptabbed.ShipAbilitiesFragment;
import com.example.dotipsandtricks.ui.ship.shiptabbed.ShipDetailsFragment;
import com.example.dotipsandtricks.ui.ship.shiptabbed.ShipModulesFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

import com.example.dotipsandtricks.ui.ship.shiptabbed.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShipActivity extends AppCompatActivity {

    private List<Fragment> fragments;
    ShipDetailsFragment fragment1;
    ShipAbilitiesFragment fragment2;
    ShipModulesFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        fragment1 = new ShipDetailsFragment();
        fragment2 = new ShipAbilitiesFragment();
        fragment3 = new ShipModulesFragment();

        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Intent i=this.getIntent();
        String title = i.getStringExtra("SHIPNAME");

        TextView textView = findViewById(R.id.title);
        textView.setText(title);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                onBackPressed();

                break;
        }
        return true;
    }
}