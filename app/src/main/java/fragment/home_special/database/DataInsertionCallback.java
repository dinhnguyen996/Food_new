package fragment.home_special.database;

import java.util.List;

import user.product.Product;

public interface DataInsertionCallback {
    //pt onDataInserted sẽ tự chạy khi lưu api hoàn thành
    void onDataInserted(List<Product> storedProducts);
}
