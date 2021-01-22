package com.example.dotipsandtricks.ui.activities.ship;

import android.content.Intent;
import android.os.Bundle;

import com.example.dotipsandtricks.R;
import com.example.dotipsandtricks.ui.fragments.shiptabbed.ShipAbilitiesFragment;
import com.example.dotipsandtricks.ui.fragments.shiptabbed.ShipDesignsFragment;
import com.example.dotipsandtricks.ui.fragments.shiptabbed.ShipDetailsFragment;
import com.example.dotipsandtricks.ui.fragments.shiptabbed.ShipModulesFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

import com.example.dotipsandtricks.ui.adapters.ship.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShipActivity extends AppCompatActivity {

    ShipDetailsFragment fragment1;
    ShipAbilitiesFragment fragment2;
    ShipModulesFragment fragment3;
    ShipDesignsFragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        fragment1 = new ShipDetailsFragment();
        fragment2 = new ShipAbilitiesFragment();
        fragment3 = new ShipModulesFragment();
        fragment4 = new ShipDesignsFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Intent i=this.getIntent();
        String title = i.getStringExtra("SHIP_NAME");

        TextView textView = findViewById(R.id.title);
        textView.setText(title);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}