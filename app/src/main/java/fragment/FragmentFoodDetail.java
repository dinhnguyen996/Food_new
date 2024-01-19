package fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.techja.fodenew.R;
import com.techja.fodenew.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import fragment.home_special.FragmentSpecialPopular;
import myapp.singleton.ModelDetailSingletonMycard;
import myapp.singleton.SingletonModeldetail;

public class FragmentFoodDetail extends Fragment {
    private ModelDetailSingletonMycard modelDetailSingletonMycard;
    private LinearLayout btn_goto_mycard;//nút add sản phẩm sang mycard
    private int priceSinglton;//giá được add vào singleton
    public boolean selectButton=true;
    private TextView tv_quality;//lấy số lượng sp được click
    private TextView tv_quality_up;// tăng số lượng sp
    private TextView tv_quality_dow;// giảm số lượng sp


    private TextView tv_detail_hearder_name;
  private TextView tv_detail_description;
  private TextView tv_detail_calories;
  private ImageView img_detail;
  private ImageView imv_detailBack;
  private TextView tv_detail_price;
  //set size cho product
    private String sizeProduct;
  private TextView btn_detail_size_S,btn_detail_size_M,btn_detail_size_L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_fragment_fooddetail,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_quality=view.findViewById(R.id.tv_quality);
        tv_quality_up=view.findViewById(R.id.tv_quality_up);
        tv_quality_dow=view.findViewById(R.id.tv_quality_dow);
        btn_goto_mycard=view.findViewById(R.id.btn_goto_mycard);
        tv_detail_hearder_name=view.findViewById(R.id.tv_detail_hearder_name);
        tv_detail_description=view.findViewById(R.id.tv_detail_description);
        tv_detail_calories=view.findViewById(R.id.tv_detail_calories);
        img_detail=view.findViewById(R.id.img_detail);
        tv_detail_price=view.findViewById(R.id.tv_detail_price);
        imv_detailBack=view.findViewById(R.id.imv_detailBack);
        //quay lại fragment trước
        imv_detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentSpecail fragmentSpecialPopular=new HomeFragmentSpecail();
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_home_holder,fragmentSpecialPopular)
                        .commit();
            }
        });
        //lấy bundle gửi về
        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name");
            String decription = args.getString("decription");
            String img = args.getString("img");
            int calories = args.getInt("calories");
            String caloris=calories+"";
            // Sử dụng dữ liệu ở đây để hiển thị

            tv_detail_hearder_name.setText(name);
            tv_detail_description.setText(decription);
            tv_detail_calories.setText(String.valueOf(calories));
            Picasso.get().load(img).into(img_detail);

//            khi click giỏ hàng sẽ add product vào singleton
            btn_goto_mycard.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //chú ý lấy quanlity trong onclick vì lấy ngoài sẽ lấy giá trị mặc định
            String quality=tv_quality.getText().toString();
            //tạo biến để kiểm tra trùng lặp prodcut
            boolean checkprodcut=false;//mặc định k trùng lặp
            //khởi tạo prodcut mới

            modelDetailSingletonMycard=new ModelDetailSingletonMycard(name,decription,img,caloris,priceSinglton,quality,sizeProduct);
            SingletonModeldetail singletonModeldetail=SingletonModeldetail.getInstance();//
            //get list product để so sánh

            List<ModelDetailSingletonMycard> listCard =  singletonModeldetail.getLiveDataProductList.getValue();
            if (listCard== null){
                Log.d("huhu", "onClick: null");
            }else {
                for (ModelDetailSingletonMycard product:listCard){
                    if (product.getName().equals(modelDetailSingletonMycard.getName()) &&product.getSize().equals(modelDetailSingletonMycard.getSize())){
                        checkprodcut=true;//trùng set bằng true sau đó thoát vòng for tránh for chạy hết
                        break;
                    }
                }
            }
            if (!checkprodcut){
                //khi không trùng lặp
               // singletonModeldetail.addProduct(modelDetailSingletonMycard);

                if (listCard == null) {
                    listCard = new ArrayList<>();
                }
                listCard.add(modelDetailSingletonMycard);

                singletonModeldetail.getLiveDataProductList.setValue(listCard);
            }
        }
    });

        } else {
            Toast.makeText(getContext(), "không nhận dc dữ liệu", Toast.LENGTH_LONG).show();
        }

        int priceBundle= args.getInt("price");//để sử dụng chung cập nhật giá sau khi lấy về

        //xử lý các nút chọn size và hiển thị giá tiền
        btn_detail_size_S=view.findViewById(R.id.btn_detail_size_S);
        if (selectButton){
            //kiểm tra điều kiện nếu chưa click
            tv_detail_price.setText("$"+priceBundle);
            btn_detail_size_S.setBackgroundResource(R.drawable.button_select_size);
            priceSinglton=priceBundle;//set giá trị để add singleton
            sizeProduct="Size S";
        }
        btn_detail_size_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton=false;//thay đổi điều kiện
                btn_detail_size_S.setBackgroundResource(R.drawable.button_select_size);
                btn_detail_size_M.setBackgroundResource(R.drawable.button_select_un_size);
                btn_detail_size_L.setBackgroundResource(R.drawable.button_select_un_size);
                priceSinglton=priceBundle;
                sizeProduct="Size S";
                tv_detail_price.setText("$"+priceSinglton);
            }
        });
        btn_detail_size_M=view.findViewById(R.id.btn_detail_size_M);
        btn_detail_size_M.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                selectButton=false;
                btn_detail_size_S.setBackgroundResource(R.drawable.button_select_un_size);
                btn_detail_size_M.setBackgroundResource(R.drawable.button_select_size);
                btn_detail_size_L.setBackgroundResource(R.drawable.button_select_un_size);
                priceSinglton=priceBundle+10;
                sizeProduct="Size M";//set size cho product
                tv_detail_price.setText("$"+priceSinglton);


            }
        });
        btn_detail_size_L=view.findViewById(R.id.btn_detail_size_L);
        btn_detail_size_L.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                selectButton=false;
                btn_detail_size_S.setBackgroundResource(R.drawable.button_select_un_size);
                btn_detail_size_M.setBackgroundResource(R.drawable.button_select_un_size);
                btn_detail_size_L.setBackgroundResource(R.drawable.button_select_size);
                priceSinglton=priceBundle+20;
                sizeProduct="Size L";
                tv_detail_price.setText("$"+priceSinglton);

            }

        });
        checkQuanlityUpdow();//lắng nghe tăng giảm sl sản phẩm
    }
    int count=1;//tạo biến đếm ban đầu
    private void checkQuanlityUpdow() {
        tv_quality_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_quality.setText(String.valueOf(count));

            }
        });
        tv_quality_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>1){
                    count--;
                    tv_quality.setText(String.valueOf(count));
                }
            }
        });
    }



}
