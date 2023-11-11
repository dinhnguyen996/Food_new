package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.techja.fodenew.R;

import java.util.List;

import myapp.singleton.ModelDetailSingletonMycard;
import myapp.singleton.SingletonModeldetail;

public class FragmentFoodDetail2 extends Fragment {
    private int count=1;// biến đếm ban đầu để tăng giảm số lượng
    private LinearLayout btn_goto_mycard;
    private TextView tv_detail_hearder_name,tv_detail_calories;
    private TextView tv_detail_price,tv_detail_description;
    private TextView tv_quality_up,tv_quality_dow,tv_quality;//tăng giảm số lượng
    private ImageView imv_detailBack;//back lại trang trước
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fooddetail2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        double tvPrice=bundle.getDouble("tvPrice");
        int price=(int)tvPrice;//convert dữ lêuuj dang int

        int tvCaloris=bundle.getInt("tvCaloris");
        String calori=tvCaloris+"";
        String name=bundle.getString("name");
        String tvDescription=bundle.getString("tvDescription");
        String img=bundle.getString("img");

        tv_detail_hearder_name=view.findViewById(R.id.tv_detail_hearder_name);
        tv_detail_hearder_name.setText(name);
        tv_detail_calories=view.findViewById(R.id.tv_detail_calories);
        tv_detail_calories.setText(tvCaloris+ "caloris");
        tv_detail_price=view.findViewById(R.id.tv_detail_price);
        tv_detail_price.setText("$"+ tvPrice);
        tv_detail_description=view.findViewById(R.id.tv_detail_description);
        tv_detail_description.setText(tvDescription);
        //back lại trang trước
        imv_detailBack=view.findViewById(R.id.imv_detailBack);
        imv_detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentSearch homeFragmentSearch=new HomeFragmentSearch();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSearch).commit();
            }
        });

        //tăng giảm số lượng sp được chọn
        tv_quality=view.findViewById(R.id.tv_quality);
        tv_quality_up=view.findViewById(R.id.tv_quality_up);
        tv_quality_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_quality.setText(count+"");
            }
        });
        tv_quality_dow=view.findViewById(R.id.tv_quality_dow);
        tv_quality_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>1){
                    count--;
                    tv_quality.setText(count+"");
                }
            }
        });

        btn_goto_mycard=view.findViewById(R.id.btn_goto_mycard);
        btn_goto_mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quanlity=tv_quality.getText().toString();
                //tạo mới sản phẩm
                ModelDetailSingletonMycard newProduct=new ModelDetailSingletonMycard(name,tvDescription,img,calori,price,quanlity,"Pre size");

                SingletonModeldetail singletonModeldetail=SingletonModeldetail.getInstance();
                //get list product để kiểm tra trùng sản phẩm hay không
                List<ModelDetailSingletonMycard> listProdcut=singletonModeldetail.getProductList();

                //kiểm tra có trùng product trước khi add vào list
                boolean isProductExist = false; // Mặc định sản phẩm chưa tồn tại
                for (ModelDetailSingletonMycard product:listProdcut){
                    if (product.getName().equals(newProduct.getName())){
                        // Sản phẩm đã tồn tại trong danh sách
                        isProductExist = true;
                        break;
                    }
                }
                if (!isProductExist){
                    // Sản phẩm chưa tồn tại, thêm vào danh sách
                    singletonModeldetail.addProduct(newProduct);
                }
            }
        });

    }
}
