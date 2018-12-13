package com.example.administrator.helloworld.fileList;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class MusicMainActivity extends AppCompatActivity implements View.OnClickListener {

    private int content;
    private TextView musicList, player, my;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private FragmentManager fm;
    private Fragment musicListFragment, playerFragment, myFragment;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);

        bindView();
        init();
    }

    private void bindView() {
        content = R.id.content;

        musicList = findViewById(R.id.musicList);
        player = findViewById(R.id.player);
        my = findViewById(R.id.my);
        viewPager = findViewById(R.id.content);
    }

    private void init() {
        fm = getSupportFragmentManager();
        fragments = new ArrayList<>();

        fragments.add(new MusicListFragment());
        fragments.add(new PlayerFragment());
        fragments.add(new MyFragment());
        adapter = new ViewPagerAdapter(fm);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        musicList.setOnClickListener(this);
        player.setOnClickListener(this);
        my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.musicList:
                viewPager.setCurrentItem(0);
                break;
            case R.id.player:
                viewPager.setCurrentItem(1);
                break;
            case R.id.my:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }
}
