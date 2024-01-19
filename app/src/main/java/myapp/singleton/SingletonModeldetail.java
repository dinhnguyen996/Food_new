package myapp.singleton;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class SingletonModeldetail {
    private static SingletonModeldetail instance;
    private List<ModelDetailSingletonMycard> productList;

    private MutableLiveData<List<ModelDetailSingletonMycard>> liveDataProductList = new MutableLiveData<>();

    public  MutableLiveData<List<ModelDetailSingletonMycard>> getLiveDataProductList = liveDataProductList;
    private boolean isDataUpdated = false;
    public boolean isDataUpdated() {
        return isDataUpdated;
    }

    public void setDataUpdated(boolean updated) {
        isDataUpdated = updated;
    }
    private SingletonModeldetail() {
        productList = new ArrayList<>();
    }
    public void addProduct(ModelDetailSingletonMycard product) {
        productList.add(product);
    }
    public void removeProduct(ModelDetailSingletonMycard product) {
        productList.remove(product);
    }
    public static SingletonModeldetail getInstance() {
        if (instance == null) {
            instance = new SingletonModeldetail();
        }
        return instance;
    }

    public List<ModelDetailSingletonMycard> getProductList() {
        return productList;
    }
    public ModelDetailSingletonMycard getProduct(){
        return getProduct();
    }

}
