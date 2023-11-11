package fragment.home_special;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.techja.fodenew.R;

import java.util.List;

import adapter.home_special.AdapterSpecialPopuler;
import api.APIFode;
import fragment.FragmentLogin;
import fragment.home_special.database.AppDatabase;
import fragment.home_special.database.DataInsertionCallback;
import fragment.home_special.database.ProductDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import user.product.ApiResponseAllproduct;
import user.product.Product;

public class FragmentSpecialPopular extends Fragment  {
    //implements DataInsertionCallback để xử lý việc lấy data api về trước rồi mới lưu data base
    private static final String KEY="PUT TOKEN";
    String TAG = "f1";
    public List<Product> productList;//ds nhận về từ api
    private AdapterSpecialPopuler adapterSpecialPopuler;
    public  static List<Product> storedProducts;//list sp sau khi lấy từ data base,act khác có thể lấy
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_1,container,false);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.recyclerview_home);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        String token =  FragmentLogin.token; // CÁCH 1
        //CÁC2 DÙNG MySharedPreferences
//        MySharedPreferences mySharedPreferences=new MySharedPreferences(getContext());
//        String token=mySharedPreferences.getToken(KEY,"");//khai báo String key

        Log.d(TAG, "onViewCreated: " + token);
        //ngay si khi nhấn login chuyển màng sang home, home sẽ add fragment 1 ngay đầu tiên
        //và sẽ thức hiện call api để lấy list product để hiển thị ngay

        APIFode.retrofit.productGetAll(token).enqueue(new Callback<ApiResponseAllproduct>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponseAllproduct> call, @NonNull Response<ApiResponseAllproduct> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    productList = response.body().data.getProducts();//dữ liêu lấy về

                    adapterSpecialPopuler=new AdapterSpecialPopuler(productList,getContext());
                    recyclerView.setAdapter(adapterSpecialPopuler);

                    // Gọi hàm xử lý dữ liệu sau khi nhận được từ API
                    handleApiData(productList);//gửi productlist cho pt handleApiData sử dụng
                } else {
                    // Xử lý lỗi API
                }
                //sử dụng sigeleton để add list vào
//                myapp.singleton.Singleton.Companion.additem(mListUserProductHomes);
            }
            @Override
            public void onFailure(@NonNull Call<ApiResponseAllproduct> call, Throwable t) {
            }
        });

    }

    private void handleApiData(List<Product> productList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //khởi tạo data base
                AppDatabase db = Room.databaseBuilder(getActivity(), AppDatabase.class, "product-database").build();
                ProductDao productDao = db.productDao();
                productDao.insertProducts(productList);

                //iterface
                ProductDao productDao1 = db.productDao();
                storedProducts = productDao1.getAllProducts();

                if (storedProducts != null && !storedProducts.isEmpty()) {

                } else {
                    // Danh sách sản phẩm chưa được lưu trong cơ sở dữ liệu
                }

            }
        }).start();
    }



}
