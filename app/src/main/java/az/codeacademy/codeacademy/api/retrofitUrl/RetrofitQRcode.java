package az.codeacademy.codeacademy.api.retrofitUrl;

import az.codeacademy.codeacademy.qr.code.sendUrl.QRcodeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Murad on 04/01/2018.
 */

public interface RetrofitQRcode {


    @GET("approveattendance/{userid}/{deviceid}/{qrcodetext}/{token}")
    Call<QRcodeResponse> qrcodesend(@Path("userid") String userid , @Path("deviceid") String deviceid,
                                    @Path("qrcodetext") String qrcodetext,
                                    @Path("token") String token);


}
