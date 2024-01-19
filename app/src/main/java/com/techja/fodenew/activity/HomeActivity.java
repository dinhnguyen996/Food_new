package com.techja.fodenew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techja.fodenew.R;

import java.util.List;

import fragment.FragmentMycard;
import fragment.HomeFragmentSpecail;
import myapp.singleton.ModelDetailSingletonMycard;
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
        SingletonModeldetail.getInstance().getLiveDataProductList.observe(this, listProductObserver );

    }
    Observer<List<ModelDetailSingletonMycard>> listProductObserver = new Observer<List<ModelDetailSingletonMycard>>() {

        @Override
        public void onChanged(List<ModelDetailSingletonMycard> list) {

            Log.d("huhu", "onChanged: " + list.size());
            if (list.isEmpty()) {
                layout_shop_buy_sum.setVisibility(View.GONE);
            } else {
                layout_shop_buy_sum.setVisibility(View.VISIBLE);
            }
        }
    };

    SingletonModeldetail singletonModeldetail1=SingletonModeldetail.getInstance();
    List<ModelDetailSingletonMycard> productList =singletonModeldetail1.getProductList();
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HomeActivity", "onResume: " + productList.size());
//        if (productList.isEmpty()) {
////            layout_shop_buy_sum.setVisibility(View.GONE);
//        } else {
//            layout_shop_buy_sum.setVisibility(View.VISIBLE);
//        }
    }
}