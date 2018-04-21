package az.codeacademy.codeacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import az.codeacademy.codeacademy.api.retrofitUrl.RetrofitNewPassword;
import az.codeacademy.codeacademy.api.retrofitUrl.token.generator.TokenCreate;
import az.codeacademy.codeacademy.login.system.NewPassResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPasswordActivity extends AppCompatActivity {

 Button changepaswbtn;
 EditText userNewPassword,paswordConfirm;

SharedPreferences sp;
    private ProgressDialog progressDialog;
    public static final String BASE_URL = "http://codeacademyattendancesystemapi.azurewebsites.net/api/student/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);


        changepaswbtn=findViewById(R.id.changepaswbtn);
        userNewPassword=findViewById(R.id.userNewPassword);
        paswordConfirm=findViewById(R.id.paswordConfirm);

//-change button------------------------------------------------------------------------------------

        changepaswbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (isEmpty(userNewPassword)) {
                    userNewPassword.requestFocus();
                    userNewPassword.setError(getString(R.string.newpassword));
                }else if(userNewPassword.length()<8){
                  Toast.makeText(getApplicationContext(), getString(R.string.min_simvol),Toast.LENGTH_LONG).show();
                }else if(userNewPassword.length()>16){
                    Toast.makeText(getApplicationContext(), getString(R.string.max_simvol),Toast.LENGTH_LONG).show();
                }else if(isEmpty(paswordConfirm)){
                    paswordConfirm.requestFocus();
                    paswordConfirm.setError(getString(R.string.confirm_password));
                }else if (!isEmpty(userNewPassword)&& isMatched()) {

                     postRequestMethod();

                 } else{
                    Toast.makeText(getApplicationContext(), R.string.password_not_matched,Toast.LENGTH_LONG).show();
                }
            }
        });
//-----------------------------------------------------------------------------------------------------------------

    }


    //new password  code----------------------------------------------------
    public boolean isMatched(){
        if (userNewPassword.getText().toString().equals(paswordConfirm.getText().toString()))
            return true;

        return false;
    }

    private boolean isEmpty(EditText edittext) {
        if (edittext.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void postRequestMethod(){

        progressDialog =new ProgressDialog(NewPasswordActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitNewPassword retrofitNewPassword = retrofit.create(RetrofitNewPassword.class);

        String newpsw=userNewPassword.getText().toString();

        String deviceid=WelcomeActivity.deviceid.toString();

Intent i=getIntent();

        String userid=i.getStringExtra("id");
        TokenCreate tokenCreate=new TokenCreate();

        String tokencode=tokenCreate.getSpesificKey()+userid ;
        String token=TokenCreate.get_SHA_512_SecurePassword(tokencode);

        //--------------------------------------------------------



        Call<NewPassResponse> call = retrofitNewPassword.newpass(userid,newpsw,deviceid,token);
        Log.w("request",call.request().toString());

        call.enqueue(new Callback<NewPassResponse>()

        {
            @Override
            public void onResponse(Call<NewPassResponse> call, Response<NewPassResponse> response) {
                progressDialog.dismiss();
                NewPassResponse newPassResponse = response.body();

                if (newPassResponse.getSuccess() && newPassResponse.getSetNewPassword() == false) {


                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), newPassResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<NewPassResponse> call, Throwable t) {
                progressDialog.dismiss();
            }

        });


    }
    //----------------------------------------------------------------





    ///add new password code this activity class;
}
