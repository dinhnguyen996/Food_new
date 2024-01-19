package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techja.fodenew.R;

import java.util.ArrayList;
import java.util.List;

import fragment.FragmentMycard;
import myapp.singleton.ModelDetailSingletonMycard;
import myapp.singleton.SingletonModeldetail;
import user.product.Product;

public class AdapterMyCard extends RecyclerView.Adapter<AdapterMyCard.ViewHolderMycard> {
    private List<ModelDetailSingletonMycard> listMycard;
    private Context context;

    public AdapterMyCard(List<ModelDetailSingletonMycard> listMycard, Context context) {
        this.listMycard = listMycard;
        this.context = context;
    }
    //xoá sản phẩm
    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }
    private OnDeleteItemClickListener listener;//lưu trữ

    // ...

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener listener) {
        this.listener = listener;
        // ở đâu dùng listener thì có thể sử dụng phương thức interface của fragment kế thừa interface
        //
    }

    @NonNull
    @Override
    public ViewHolderMycard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_fragment_mycard,parent,false);
        ViewHolderMycard viewHolderMycard=new ViewHolderMycard(view);
        return viewHolderMycard;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderMycard holder, @SuppressLint("RecyclerView") int position) {
        ModelDetailSingletonMycard product=listMycard.get(position);
        holder.tv_mycard_name.setText(product.getName());
        holder.tv_mycard_dicription.setText(product.getDecription());
        holder.tv_mycard_price.setText("$"+ product.getSelectSize());
        holder.tv_quality.setText(product.getQuanlity());
        holder.tv_mycard_size.setText(product.getSize());

        Picasso.get().load(product.getImg()).into(holder.imgMycard);

        holder.tv_quality_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy giá trị hiện tại của số lượng
                int currentQuantity = Integer.parseInt(product.getQuanlity());
                    currentQuantity++;
                    product.setQuanlity(String.valueOf(currentQuantity));//set lại giá trị sau khi tăng
                    holder.tv_quality.setText(String.valueOf(currentQuantity));//hiển thi sau khi set
                // Cập nhật tổng giá
                double totalPrice = AdapterMyCard.caculateTotalPrice();//hàm tính toán trả về kết quả
                FragmentMycard.total_price_mycard.setText("$" + totalPrice);//lấy text total bên Mycard

            }
        });
        holder.tv_quality_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(product.getQuanlity());
                if (currentQuantity > 1) {
                    // dk số lượng lớn hơn  1 mới giảm
                    currentQuantity--;
                    product.setQuanlity(String.valueOf(currentQuantity));
                    holder.tv_quality.setText(String.valueOf(currentQuantity)); // Cập nhật số lượng trực tiếp
                    // Cập nhật tổng giá
                    double totalPrice = AdapterMyCard.caculateTotalPrice();
                    FragmentMycard.total_price_mycard.setText("$" + totalPrice);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMycard.size();
    }

    public class ViewHolderMycard extends RecyclerView.ViewHolder{
        private ImageView imgMycard;
        private TextView tv_mycard_name;
        private TextView tv_mycard_dicription;
        private TextView tv_mycard_price;
        private TextView tv_quality;
        //lấy các nup tăng giảm sp
        private TextView tv_quality_up;
        private TextView tv_quality_dow;
        //get size
        private TextView tv_mycard_size;
        private ImageView clear_product;//xoá sp
        public ViewHolderMycard(@NonNull View itemView) {
            super(itemView);
            imgMycard=itemView.findViewById(R.id.imv_mycard);
            tv_mycard_name=itemView.findViewById(R.id.tv_mycard_name);
            tv_mycard_dicription=itemView.findViewById(R.id.tv_mycard_dicription);
            tv_mycard_price=itemView.findViewById(R.id.tv_mycard_price);
            tv_quality=itemView.findViewById(R.id.tv_quality);
            //nút tăng giảm sp
            tv_quality_up=itemView.findViewById(R.id.tv_quality_up);
            tv_quality_dow=itemView.findViewById(R.id.tv_quality_dow);
            //get size
            tv_mycard_size=itemView.findViewById(R.id.tv_mycard_size);
            //xoá sp sủ dụng interface
            clear_product=itemView.findViewById(R.id.clear_product);
            clear_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDeleteItemClick(getAdapterPosition());
                        //người thực hiện thao tác
                    }
                }
            });
        }
    }
    //tính tổng giá tiền
    public static double caculateTotalPrice(){
        SingletonModeldetail singletonModeldetail=SingletonModeldetail.getInstance();
        List<ModelDetailSingletonMycard> listCard =  singletonModeldetail.getLiveDataProductList.getValue();
        double total=0.0;
        for (ModelDetailSingletonMycard product : listCard){
            double price=(double)product.getSelectSize();//ép sang kiểu double
            int quanlity= Integer.parseInt(product.getQuanlity());
            total+=price*quanlity;
        }
        return total;

    }
}
