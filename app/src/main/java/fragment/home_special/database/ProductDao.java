package fragment.home_special.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import user.product.Product;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<Product> products);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
    @Query("SELECT * FROM products")
    LiveData<List<Product>> getAllProductsLiveData();
}
