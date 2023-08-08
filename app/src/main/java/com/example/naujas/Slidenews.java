package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
public class Slidenews extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mhome,mhealth,mtech,mentertainment,msports,mlt;
    PageAdapter pageAdapter;
    Toolbar mtoolbar;
    String api ="74daee441d8d43aaa1f77bada423ce02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidenews);
        mtoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        mhome=findViewById(R.id.home);
        mhealth=findViewById(R.id.health);
        msports=findViewById(R.id.sports);
        mtech=findViewById(R.id.tech);
        mentertainment=findViewById(R.id.enter);
        mlt=findViewById(R.id.lt);
        ViewPager viewPager=findViewById(R.id.fragmentContainer);
        tabLayout=findViewById(R.id.include);
        pageAdapter=new PageAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pageAdapter);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0||tab.getPosition()==1||tab.getPosition()==2||
                        tab.getPosition()==3||tab.getPosition()==4){
                    pageAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}