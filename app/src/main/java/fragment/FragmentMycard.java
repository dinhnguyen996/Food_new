package fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techja.fodenew.R;

import java.util.List;

import adapter.AdapterMyCard;
import myapp.singleton.ModelDetailSingletonMycard;
import myapp.singleton.SingletonModeldetail;

public class FragmentMycard extends Fragment implements AdapterMyCard.OnDeleteItemClickListener {
    private List<ModelDetailSingletonMycard> listMycard;
    private RecyclerView recyclerView;
    private AdapterMyCard adapterMyCard;
    public static TextView total_price_mycard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mycard,container,false);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listMycard= SingletonModeldetail.getInstance().getLiveDataProductList.getValue();//lấy dữ liệu từ singleton
        recyclerView=view.findViewById(R.id.rcv_fragment_mycard);
        adapterMyCard=new AdapterMyCard(listMycard,getContext());
        adapterMyCard.setOnDeleteItemClickListener(this);
        //gán fragemnt là đối tuượng người nghe thao tác cho adapter

        recyclerView.setAdapter(adapterMyCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //hiển thị tổng giá tiền tất cả các sp ở recyclerview
        total_price_mycard=view.findViewById(R.id.total_price_mycard);
        double totalPrice= AdapterMyCard.caculateTotalPrice();
        total_price_mycard.setText("$"+totalPrice);


    }
//hàm ghi đè inter face
    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onDeleteItemClick(int position) {
        // Xoá sản phẩm tại vị trí position từ danh sách
        listMycard.remove(position);

        // Cập nhật RecyclerView
        adapterMyCard.notifyDataSetChanged();
        // Cập nhật tổng giá tiền
        double totalPrice = AdapterMyCard.caculateTotalPrice();
        total_price_mycard.setText("$" + totalPrice);
    }
}
