package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.fodenew.R;

import java.util.List;

import adapter.AdapterHomeSale;
import fragment.home_special.FragmentSpecialPopular;
import user.product.Product;

public class HomeFragmentSale extends Fragment {
    private RecyclerView recyclerView;
    private AdapterHomeSale adapterHomeSale;
    private List<Product> slist;
    private ImageView imvToSpecial;//chuyển fragment specail
    private ImageView imvToSearch; // chuyển fragment search
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.home_fragment_sale,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imvToSpecial=view.findViewById(R.id.imv_home_fragment_sale_tospecial);
        imvToSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentSpecail homeFragmentSpecail=new HomeFragmentSpecail();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSpecail)
                        .commit();
            }
        });
        imvToSearch=view.findViewById(R.id.imv_home_fragment_sale_to_search);
        imvToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentSearch homeFragmentSearch=new HomeFragmentSearch();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_home_holder,homeFragmentSearch)
                        .commit();
            }
        });
        slist= FragmentSpecialPopular.storedProducts;//lấy ds từ FragmentSpecialPoPular
        recyclerView=view.findViewById(R.id.rcv_fragment_homesale);
        adapterHomeSale=new AdapterHomeSale(slist);
        recyclerView.setAdapter(adapterHomeSale);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


    }

}
