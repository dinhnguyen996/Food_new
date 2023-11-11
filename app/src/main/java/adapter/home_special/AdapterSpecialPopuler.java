package adapter.home_special;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techja.fodenew.R;
import com.techja.fodenew.activity.HomeActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import fragment.FragmentFoodDetail;
import fragment.HomeFragmentSpecail;
import fragment.home_special.FragmentSpecialPopular;
import user.product.Product;

public class AdapterSpecialPopuler extends RecyclerView.Adapter<AdapterSpecialPopuler.ViewHolder> {
    private List<Product> mlist;
    private Context context;

    public AdapterSpecialPopuler(List<Product> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_recycerview_home_activiti,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=mlist.get(position);
        holder.tvCaloris.setText(product.getCalories()+ "");
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("$"+ product.getPrice()+"");
        holder.tvDescription.setText(product.getDescription());
        Picasso.get().load(product.getImage_url()).into(holder.imvHomeRecyclerview);
        //lấy các dữ liệu cần thiết gửi đi
        String name=product.getName();
        String decription=product.getDescription();
        int calories=product.getCalories();
        int price= (int) product.getPrice();
        String img=product.getImage_url();


        //set sự kiện khi click vào item product
        holder.relativeLayoutFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("decription",decription);
                bundle.putString("name",name);
                bundle.putString("img",img);
                bundle.putInt("calories",calories);
                bundle.putInt("price",price);

                //khởi tạo fragment cần tới
                FragmentFoodDetail productDetailFragment = new FragmentFoodDetail();
                productDetailFragment.setArguments(bundle);

                //ép kiểu để thay thế fragment chứa adapter mình click bằng fragment mong muốn
                FragmentManager fragmentManager=((FragmentActivity)context ).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("special")
                        .replace(R.id.fragment_home_holder,productDetailFragment).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvCaloris;
        private TextView tvDescription;

        private ImageView imvHomeRecyclerview;
        private RelativeLayout relativeLayoutFull;//lấy layout để lắng nghe sự kiện

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_item_name_home);
            tvCaloris=itemView.findViewById(R.id.tv_item_calories_home_rcv);
            tvDescription=itemView.findViewById(R.id.tv_item_description_home);
            tvPrice=itemView.findViewById(R.id.tv_item_price_home);
            imvHomeRecyclerview=itemView.findViewById(R.id.imv_home_rcv);
            relativeLayoutFull=itemView.findViewById(R.id.full);
        }

    }
}
