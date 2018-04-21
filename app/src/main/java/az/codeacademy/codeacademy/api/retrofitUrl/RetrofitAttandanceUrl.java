package az.codeacademy.codeacademy.api.retrofitUrl;

import az.codeacademy.codeacademy.login.system.UserAttendance;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Murad on 04/04/2018.
 */

public interface RetrofitAttandanceUrl {


    @GET("StudentAttendanceList/{token}/{deviceid}/{userid}")
    Call<UserAttendance> attdata(@Path("token") String token, @Path("deviceid") String deviceid,
                                 @Path("userid") String userid);





}
