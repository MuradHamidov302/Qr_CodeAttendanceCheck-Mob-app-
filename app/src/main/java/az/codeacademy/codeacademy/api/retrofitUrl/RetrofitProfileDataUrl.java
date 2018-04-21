package az.codeacademy.codeacademy.api.retrofitUrl;

import az.codeacademy.codeacademy.login.system.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Murad on 04/04/2018.
 */

public interface RetrofitProfileDataUrl {


    @GET("studentprofile/{userid}/{deviceid}/{token}")
    Call<UserProfile> profildata(@Path("userid") String userid, @Path("deviceid") String deviceid,
                                 @Path("token") String token);



}



