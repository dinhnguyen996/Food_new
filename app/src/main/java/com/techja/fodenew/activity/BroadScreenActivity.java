package com.techja.fodenew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.techja.fodenew.R;

import adapter.AdapterBroadScreen;
import me.relex.circleindicator.CircleIndicator3;

public class BroadScreenActivity extends AppCompatActivity {

    private ImageView imvNext;
    private ViewPager2 viewPager2;
    private AdapterBroadScreen adapterFragment;
    private CircleIndicator3 circleIndicator3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broad_screen_layout);
        initUi();//ánh xạ view
        showFragment();

    }
    private void initUi() {
        imvNext=findViewById(R.id.imv_next);
        imvNext=findViewById(R.id.imv_next);
        imvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager2.getCurrentItem() <2){
                    //nếu đang ở vị trí màn có position <2 thì next màn tiếp
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }
            }
        });
    }

    private void showFragment() {
        viewPager2=findViewById(R.id.viewpager2_layout);
        adapterFragment=new AdapterBroadScreen(this);
        viewPager2.setAdapter(adapterFragment);
        circleIndicator3=findViewById(R.id.circleIndicator3);
        circleIndicator3.setViewPager(viewPager2);
        // thực hiện ẩn nút imv next khi add fragment 3
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (viewPager2.getCurrentItem()==2){
                    //nếu đang ở vị trí số 2 thì ẩn imv next
                    imvNext.setVisibility(View.GONE);
                }else {
                    imvNext.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}