package Models;

public class ProductData {


 private    Integer connection ;
   private Integer productaddd;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getproductaddd() {
        return productaddd;
    }

    public void setproductaddd(Integer productaddd) {
        this.productaddd = productaddd;
    }

    public ProductData(Integer connection, Integer resualt) {
        this.connection = connection;
        this.productaddd = productaddd;







    }
    @Override
    public String toString() {
        return "ProductData{" +
                "connection=" + connection +
                ", productaddd=" + productaddd +
                '}';
    }
}
