package com.techja.fodenew.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techja.fodenew.R;

import fragment.FragmentMycard;
import fragment.HomeFragmentSpecail;
import myapp.singleton.SingletonModeldetail;

public class HomeActivity extends AppCompatActivity  {
    boolean isFragmentMycardVisible = false;//kiểm tra xem đã chuyển sang FragmentMycard chưa
    private TextView tv_home_quanlity;
    private SingletonModeldetail singletonModeldetail;
    private LinearLayout layout_shop_buy_sum;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        HomeFragmentSpecail homeFragmentSpecail=new HomeFragmentSpecail();
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("1")
                .replace(R.id.fragment_home_holder,homeFragmentSpecail)
                .commit();
        //ẩn hiện giỏ hàng tổng
        layout_shop_buy_sum=findViewById(R.id.layout_shop_buy_sum);
        //lắng nghe sự kiện click giỏ hàng
        layout_shop_buy_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFragmentMycardVisible) {
                    // Nếu FragmentMycard đã được thêm vào, quay trở lại Home Activity
                    getSupportFragmentManager().popBackStack();
                    isFragmentMycardVisible = false;
                    //nếu vào if sau khi chạy xong sẽ set lại biến thành fale như ban đầu
                } else {
                    //vì biến đặt là fale nên đầu tiên khi nhấn sẽ chạy vào đây
                    // Nếu FragmentMycard chưa được thêm vào, thêm nó vào
                    FragmentMycard fragmentMycard = new FragmentMycard();
                    getSupportFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_home_holder, fragmentMycard)
                            .commit();
                    //sau khi nhấn sẽ gán biến thành true,nếu nhấn 1 lần nữa thì sẽ vào if
                    isFragmentMycardVisible = true;
                }
            }
        });


        //set số sản phẩm có trong giỏ
        tv_home_quanlity=findViewById(R.id.tv_home_quanlity);
    }

}