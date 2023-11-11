package fragment.home_special.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import user.product.Product;

public class ItemViewModel extends AndroidViewModel {
    private LiveData<List<Product>> products;
    private ProductDao productDao;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase=AppDatabase.getInstance(application);
        productDao=appDatabase.productDao();
        products=productDao.getAllProductsLiveData();
    }
    public LiveData<List<Product>> getProducts(){
        return products;
    }
    public void insertItem(List<Product> product) {
        productDao.insertProducts(product);
    }
}
