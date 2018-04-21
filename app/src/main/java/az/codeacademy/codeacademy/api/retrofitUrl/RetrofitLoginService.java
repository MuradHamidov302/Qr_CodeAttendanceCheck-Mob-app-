package az.codeacademy.codeacademy.api.retrofitUrl;

import az.codeacademy.codeacademy.login.system.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Murad on 03/30/2018.
 */

public interface RetrofitLoginService {

    @GET("login/{email}/{password}/{deviceid}/{token}")
    Call<UserResponse> login(@Path("email") String email, @Path("password") String password
                            ,@Path("deviceid") String deviceid
                            ,@Path("token") String token);

}