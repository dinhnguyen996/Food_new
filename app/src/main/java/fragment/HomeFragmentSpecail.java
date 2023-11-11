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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.techja.fodenew.R;

import adapter.home_special.AdapterHomeSpecial;

public class HomeFragmentSpecail extends Fragment {
    private boolean isBackButtonEnabled = false;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private AdapterHomeSpecial adapterHomeSpecial;
    private ImageView imgToSale;//chuyển fragment
    private ImageView imgToSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.home_fragment_specail,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgToSale=view.findViewById(R.id.imv_to_sale);
        imgToSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển các fragment với nhau
                HomeFragmentSale fragmentSale=new HomeFragmentSale();
                getParentFragmentManager().beginTransaction()
                        .addToBackStack("1")
                        .replace(R.id.fragment_home_holder,fragmentSale)
                        .commit();
            }
        });
        imgToSearch=view.findViewById(R.id.imv_home_special_tosearch);
        imgToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentSearch homeFragmentSearch=new HomeFragmentSearch();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSearch)
                        .commit();
            }
        });



        //thực hiện hiển thị dữ liệu
        viewPager2=view.findViewById(R.id.viewpage2_home_special_layout);
        adapterHomeSpecial=new AdapterHomeSpecial(this);
        viewPager2.setAdapter(adapterHomeSpecial);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setUserInputEnabled(false);//không cho vuốt chuyển fragment
        //xử lý tablayout
        tabLayout=view.findViewById(R.id.tab_layout_home_special);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tạo mới taplayout sử dụng viewpager2
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("Populer");
                    break;
                    case 1: tab.setText("Pizza");
                    break;
                    case 2: tab.setText("Top");
                    break;
                    case 3: tab.setText("AllMenu");
                    break;
                    case 4: tab.setText("Food");
                }
            }
        }).attach();

        //lấy bundle gửi về từ FooDetail để hiển thị số lượng lên giỏ hàng
//        Bundle arg=getArguments();
//        if (arg !=null){
//            String name=arg.getString("name");
//            String description=arg.getString("description");
//            String price=arg.getString("price");
//            String img=arg.getString("img");
//
//        }

    }

}
