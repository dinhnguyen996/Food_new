package api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import user.product.ApiResponseAllproduct;
import user.product.Data;
import user.product.Product;

public interface APIFode {
    //tạo luôn thư viện retrofit ở đây
    //http://ec2-57-180-22-166.ap-northeast-1.compute.amazonaws.com/
    APIFode retrofit=new Retrofit.Builder()
            .baseUrl("http://ec2-57-180-22-166.ap-northeast-1.compute.amazonaws.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIFode.class);
    //login là đuôi của api cần dùng
    @FormUrlEncoded
    @POST("login") //api đăng nhập
    Call<ResponseLoginData> login (@Field("username")String username,
                                   @Field("password")String password);
    @FormUrlEncoded
    @POST("registration") //api đăng ký
    Call<RegisterData> register(@Field("username")String username,
                                @Field("phone_number")String phone_number,
                                @Field("email")String email,
                                @Field("password")String password);

    @GET("product/get-all")//api lấy list sản phẩm
    Call<ApiResponseAllproduct> productGetAll (@Header("Authorization") String authHeader);

}
