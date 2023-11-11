package adapter.home_special;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techja.fodenew.R;

import java.util.List;

import fragment.FragmentFoodDetail2;
import user.product.Product;

public class AdapterSpecialPopulerSearch extends RecyclerView.Adapter<AdapterSpecialPopulerSearch.ViewHolder> {
    private List<Product> mlist;
    private Context context;

    public AdapterSpecialPopulerSearch(List<Product> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }
    public void setItems(List<Product> productList) {
        this.mlist = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_recycerview_search_activiti,parent,false);
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
        Picasso.get().load(product.getImage_url()).into(holder.imvSearchRecyclerview);
        //lấy data gửi đi
        int tvCaloris=product.getCalories();
        String name=product.getName();
        double tvPrice=product.getPrice();
        String tvDescription=product.getDescription();
        String img=product.getImage_url();


       //sự kiện khi click vào item product
        holder.rlt_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putDouble("tvPrice",tvPrice);
                bundle.putInt("tvCaloris",tvCaloris);
                bundle.putString("name",name);
                bundle.putString("tvDescription",tvDescription);
                bundle.putString("img",img);
                FragmentFoodDetail2 fragmentFoodDetail2=new FragmentFoodDetail2();
                fragmentFoodDetail2.setArguments(bundle);

                //ép kiểu để thay thế fragment chứa adapter mình click bằng fragment mong muốn
                FragmentManager fragmentManager=((FragmentActivity)context ).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack("special")
                        .replace(R.id.fragment_home_holder,fragmentFoodDetail2).commit();
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

        private ImageView imvSearchRecyclerview;
        private RelativeLayout rlt_seach;//itemlayout


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_item_name_search);
            tvCaloris=itemView.findViewById(R.id.tv_item_calories_search_rcv);
            tvDescription=itemView.findViewById(R.id.tv_item_description_search);
            tvPrice=itemView.findViewById(R.id.tv_item_price_sarch);
            imvSearchRecyclerview=itemView.findViewById(R.id.imv_search);
            rlt_seach=itemView.findViewById(R.id.rlt_seach);
        }
    }
}
