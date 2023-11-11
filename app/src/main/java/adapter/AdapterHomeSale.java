package adapter;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techja.fodenew.R;

import java.util.List;

import user.product.Product;

public class AdapterHomeSale extends RecyclerView.Adapter<AdapterHomeSale.ViewHolderSale> {
    private List<Product> productList;

    public AdapterHomeSale(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolderSale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_home_fragment_sale,parent,false);
        ViewHolderSale viewHolderSale=new ViewHolderSale(view);
        return viewHolderSale;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderSale holder, int position) {
        Product product=productList.get(position);
        holder.tvSaleOff.setText("Sale off " + product.getId() + "%");
        holder.tvPriceOld.setText("$" + product.getPrice());
        //tạo biến tính giá mới
        int priceold= (int) ((product.getPrice()) - (product.getPrice()*product.getId())/100);
        holder.tvPriceNew.setText("$" + priceold);
        Picasso.get().load(product.getImage_url()).into(holder.imvHomeSale);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderSale extends RecyclerView.ViewHolder{
        private final ImageView imvHomeSale;
        private final TextView tvSaleOff;
        private final TextView tvPriceOld;
        private final TextView tvPriceNew;
        public ViewHolderSale(@NonNull View itemView) {
            super(itemView);
            imvHomeSale=itemView.findViewById(R.id.img_home_sale);
            tvSaleOff=itemView.findViewById(R.id.tv_home_sale_saleoff);
            tvPriceOld=itemView.findViewById(R.id.tv_home_sale_price_old);
            //textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvPriceOld.setPaintFlags(tvPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            tvPriceNew=itemView.findViewById(R.id.tv_home_sale_price_new);
        }
    }
}
