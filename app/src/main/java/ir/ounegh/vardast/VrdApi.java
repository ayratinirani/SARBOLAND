package ir.ounegh.vardast;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by aseme on 13/12/2017.
 */

public interface VrdApi {
    @FormUrlEncoded
    @POST("getlist.php")
    Call<List<Mlocation>> getList(@FieldMap Map<String,String >params);

    @GET("getcats.php")
    Call<List<Category>> getCats();

    @FormUrlEncoded
    @POST("getlist.php")
    Call<String> getStringResponse(@FieldMap Map<String,String>params);
}
