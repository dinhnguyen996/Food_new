package myapp.singleton;

public class ModelDetailSingletonMycard {
    private String name;
    private String decription;
    private String img;
    private String calories;
    private int selectSize;
    private String quanlity;
    private String size;

    public ModelDetailSingletonMycard(String name, String decription, String img, String calories, int selectSize, String quanlity, String size) {
        this.name = name;
        this.decription = decription;
        this.img = img;
        this.calories = calories;
        this.selectSize = selectSize;
        this.quanlity = quanlity;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public int getSelectSize() {
        return selectSize;
    }

    public void setSelectSize(int selectSize) {
        this.selectSize = selectSize;
    }

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
