package Models;

import Models.LoginData;
import Models.ProductData;
import Models.RegisterUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyInterface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterUser> registerUser(@Field("name") String name, @Field("email")String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginUser(@Field("email")String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("addProduct.php")
    Call<ProductData> productUser(@Field("userid")String uid,
                                  @Field("pname")String name,
                                  @Field("pprize")String pprize,
                                  @Field("pdes")String pdes,
                                  @Field("productimage")String productimage);
    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<ViewProductData> viewproductuser (@Field("userid")String uid);
}


//'$uid','$name','$description','$price','$imagename'