package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.fodenew.R;

import java.util.ArrayList;
import java.util.List;

import adapter.home_special.AdapterSpecialPopulerSearch;
import fragment.home_special.FragmentSpecialPopular;
import fragment.home_special.database.ItemViewModel;
import user.product.Product;

public class HomeFragmentSearch extends Fragment {
//    public List<Product> mlist;//khai báo biến để nhận list product được gửi về
    private List<Product> mlist = new ArrayList<>();
    private ItemViewModel itemViewModel;
    private List<Product> products;
    private RecyclerView recyclerView;
    private AdapterSpecialPopulerSearch adapterSpecialPopulerSearch;
    private ImageView imvToSpecial;//chuyển fragment special
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.home_fragment_search,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imvToSpecial=view.findViewById(R.id.imv_home_fragment_to_special);
        imvToSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentSpecail homeFragmentSpecail=new HomeFragmentSpecail();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSpecail)
                        .commit();
            }
        });
        //hiển thị danh sách sản phẩm lên recycleview
        // lấy danh sách sản pham từ activity fragment special popular
        products=FragmentSpecialPopular.storedProducts;//lấy từ biến products static
        adapterSpecialPopulerSearch=new AdapterSpecialPopulerSearch(products,getContext());
        recyclerView=view.findViewById(R.id.rcv_homesearch_fragment_search);
        recyclerView.setAdapter(adapterSpecialPopulerSearch);
        LinearLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        //xử lý sự kiện khi nhấn nút back mặc định của điẹn thoại
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Xử lý sự kiện khi nút Back được bấm trong Fragment
                HomeFragmentSpecail homeFragmentSpecail=new HomeFragmentSpecail();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSpecail)
                        .commit();
            }
        };
        //Đăng ký callback trong Fragment và truyền getViewLifecycleOwner():
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


    }


}
