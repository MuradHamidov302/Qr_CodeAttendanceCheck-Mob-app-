package az.codeacademy.codeacademy.api.retrofitUrl;

import az.codeacademy.codeacademy.login.system.NewPassResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Murad on 03/31/2018.
 */

public interface RetrofitNewPassword {

    @GET("setnewpassword/{userid}/{newpassword}/{deviceid}/{token}")
    Call<NewPassResponse> newpass( @Path("userid") String userid,
                                  @Path("newpassword") String new_password,@Path("deviceid") String deviceid,
                                  @Path("token") String token);


}