package Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewProductData {


    private Integer connection;

    private Integer result;

    private List<Product_Data> productdata;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<Product_Data> getProductdata() {
        return productdata;
    }

    public void setProductdata(List<Product_Data> productdata) {
        this.productdata = productdata;
    }
}
