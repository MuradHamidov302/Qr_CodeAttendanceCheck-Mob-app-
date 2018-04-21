package az.codeacademy.codeacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import az.codeacademy.codeacademy.api.retrofitUrl.RetrofitAttandanceUrl;
import az.codeacademy.codeacademy.api.retrofitUrl.token.generator.TokenCreate;
import az.codeacademy.codeacademy.login.system.UserAttendance;
import az.codeacademy.codeacademy.shared.preference.SharedPreferenceA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static final String BASE_URL = "http://codeacademyattendancesystemapi.azurewebsites.net/api/student/";
    String userid;

    SharedPreferenceA sp;
    TextView attendanceInfod,list;
    Button back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        sp=new SharedPreferenceA(AttendanceActivity.this);
        HashMap<String,String> map=sp.DetailStore();
        userid=map.get(sp.KEY_ID);
        Log.e("Prof",""+userid);

        sp.checkLogin();


        list=findViewById(R.id.list);
        attendanceInfod=findViewById(R.id.attendanceInfod);
      if (userid==null){
            Toast.makeText(getApplicationContext(),"userid"+userid,Toast.LENGTH_SHORT).show();
        }else{
            postRequestMethod();
        }

        //button click -------------------------------------------------

      back4=findViewById(R.id.back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog =new ProgressDialog(AttendanceActivity.this);
                progressDialog.setMessage(getString(R.string.loading));
                progressDialog.show();
                Intent btnI=new Intent(AttendanceActivity.this,ProfileActivity.class);
                startActivity(btnI);finish();
            }
        });


        //------------------------------------------------------------


        }



    //Profile data text send code----------------------------------------------------


    private void postRequestMethod(){

        progressDialog =new ProgressDialog(AttendanceActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAttandanceUrl retrofitAttandanceUrl = retrofit.create(RetrofitAttandanceUrl.class);


        String deviceid=WelcomeActivity.deviceid.toString();

        //token class object--------------------------------------

        TokenCreate tokenCreate=new TokenCreate();

        String tokencode=tokenCreate.getSpesificKey()+userid ;
        String token=TokenCreate.get_SHA_512_SecurePassword(tokencode);

        //--------------------------------------------------------



        Call<UserAttendance> call = retrofitAttandanceUrl.attdata(token ,deviceid,userid);
        Log.w("request",call.request().toString());

        call.enqueue(new Callback<UserAttendance>()

        {
            @Override
            public void onResponse(Call<UserAttendance> call, Response<UserAttendance> response) {
                progressDialog.dismiss();
                UserAttendance userAttendance = response.body();
                 String lw="";
                if (userAttendance.getSuccess() && userAttendance.getSetNewPassword() == false) {

                    int sizea=userAttendance.getSuccessMessage().getAttendanceTable().size();


                        for (int i = 0; i < sizea; i++) {
                            if (userAttendance.getSuccessMessage().getAttendanceTable().get(i).getAttendanceStatus()!=null ) {
                            lw += i + userAttendance.getSuccessMessage().getAttendanceTable().get(i).getAttendanceDate();
                            if (userAttendance.getSuccessMessage().getAttendanceTable().get(i).getAttendanceStatus() == true ) {
                                lw += "   " + "+" + "\n\n";
                            } else {
                                lw += "   " + "-" + "\n\n";
                            }


                        }

                        String attendanceInfoda = userAttendance.getSuccessMessage().getAttendanceInfo();
                        list.setText(lw);
                        attendanceInfod.setText(attendanceInfoda);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), userAttendance.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserAttendance> call, Throwable t) {
                progressDialog.dismiss();
            }

        });


    }
    //----------------------------------------------------------------



}
