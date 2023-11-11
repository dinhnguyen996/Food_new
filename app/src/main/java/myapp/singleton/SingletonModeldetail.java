package myapp.singleton;

import java.util.ArrayList;
import java.util.List;

public class SingletonModeldetail {
    private static SingletonModeldetail instance;
    private List<ModelDetailSingletonMycard> productList;
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
