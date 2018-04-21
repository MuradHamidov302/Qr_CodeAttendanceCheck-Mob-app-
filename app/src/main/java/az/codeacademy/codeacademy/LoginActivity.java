package az.codeacademy.codeacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import az.codeacademy.codeacademy.api.retrofitUrl.RetrofitLoginService;
import az.codeacademy.codeacademy.api.retrofitUrl.token.generator.TokenCreate;
import az.codeacademy.codeacademy.login.system.UserResponse;
import az.codeacademy.codeacademy.shared.preference.SharedPreferenceA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    SharedPreferenceA sp;
    String userid;

    public static String email ,password;
    private Button startBtn;
    public EditText userNameEdit, userPasswordEdit;

    private ProgressDialog progressDialog;
    public static final String BASE_URL = "http://codeacademyattendancesystemapi.azurewebsites.net/api/student/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp=new SharedPreferenceA(LoginActivity.this);

        startBtn = (Button) findViewById(R.id.startBtn);
        userNameEdit = (EditText) findViewById(R.id.userName);
        userPasswordEdit = (EditText) findViewById(R.id.userPassword);



        //log in button--------------------------------------------------
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(userNameEdit) && !isEmpty(userPasswordEdit)) {
                    email=userNameEdit.getText().toString();//------------------------------------------------------
                    password=userPasswordEdit.getText().toString();//-----------------------------------------------
                    postRequestMethod();
                } else if (isEmpty(userNameEdit)) {
                    userNameEdit.requestFocus();
                    userNameEdit.setError(getString(R.string.yor_email));
                } else {
                    userPasswordEdit.requestFocus();
                    userPasswordEdit.setError(getString(R.string.your_password));
                }
            }
        });
//------------------------------------------------------------------------


    }

    //log in code----------------------------------------------------
    private boolean isEmpty(EditText edittext) {
        if (edittext.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    private void postRequestMethod(){

    progressDialog =new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitLoginService retrofitLoginService = retrofit.create(RetrofitLoginService.class);

    String deviceid=WelcomeActivity.deviceid.toString();//------------------------------------------

    //token class object--------------------------------------

        TokenCreate tokenCreate=new TokenCreate();

        String tokencode=tokenCreate.getSpesificKey()+email;

       String token=TokenCreate.get_SHA_512_SecurePassword(tokencode);

        //--------------------------------------------------------


    //call request----------------------------------------------------
        Call<UserResponse> call = retrofitLoginService.login(email,password,deviceid,token);
        Log.w("request",call.request().toString());



        call.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                UserResponse userResponse = response.body();
                if (userResponse.getSuccess()){

                    userid=userResponse.getStudentId().toString();

                    sp.storeData(userid);
                    Log.e("log",""+userid);

                    Intent intent=new Intent();
                    if ( userResponse.getSetNewPassword() == false) {

                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("id",userResponse.getStudentId());
                        startActivity(intent);


                    } else if (userResponse.getSetNewPassword() == true) {
                        //set new password-------------------------------------------------------

                        intent=new Intent(LoginActivity.this,NewPasswordActivity.class);
                        intent.putExtra("id",userResponse.getStudentId());
                        startActivity(intent);

                        //--------------------------------------------------------------------------
                    }finish();
                    }else {
                        Toast.makeText(getApplicationContext(), userResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }
        //----------------------------------------------------------------





}
