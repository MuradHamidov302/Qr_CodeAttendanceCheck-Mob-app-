package az.codeacademy.codeacademy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

import az.codeacademy.codeacademy.api.retrofitUrl.RetrofitProfileDataUrl;
import az.codeacademy.codeacademy.api.retrofitUrl.RetrofitQRcode;
import az.codeacademy.codeacademy.api.retrofitUrl.token.generator.TokenCreate;
import az.codeacademy.codeacademy.login.system.UserProfile;
import az.codeacademy.codeacademy.qr.code.sendUrl.QRcodeResponse;
import az.codeacademy.codeacademy.shared.preference.SharedPreferenceA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    String userid;

    private ProgressDialog progressDialog;
    public static final String BASE_URL = "http://codeacademyattendancesystemapi.azurewebsites.net/api/student/";

    SharedPreferenceA sp;

    private String qrcodetext;
    String named ,surnamed,phoned,fathernamed,groupd, group_Scheduled,
            lessonTimed,lessonBeginTimed,lessonEndTimed,attendanceRatiod;


    ImageView logoutbtn,scanbtn,attd;

    TextView namesurname,phone,group, group_Schedule,
            lessonTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        sp=new SharedPreferenceA(ProfileActivity.this);
        HashMap<String,String> map=sp.DetailStore();
        userid=map.get(sp.KEY_ID);
        Log.e("Prof",""+userid);

        sp.checkLogin();

        if (userid==null){
            Toast.makeText(getApplicationContext(),"userid "+userid,Toast.LENGTH_SHORT).show();
        }else{

            postRequestMethodProfile();
        }





//TextView-----------------------------------------
        namesurname=findViewById(R.id.namesurname);
        logoutbtn=findViewById(R.id.logoutbtn);
        group_Schedule=findViewById(R.id.group_Schedule);
        group=findViewById(R.id.group);
        phone=findViewById(R.id.phone);
        lessonTime=findViewById(R.id.lessonTime);






        attd=findViewById(R.id.attd);//--------------------------------------------------------------------
        attd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog =new ProgressDialog(ProfileActivity.this);
                progressDialog.setMessage(getString(R.string.loading));
                progressDialog.show();
                Intent i=new Intent(ProfileActivity.this,AttendanceActivity.class);
                startActivity(i);
                finish();

            }
        });


        //log out button-----------------------------------------------------

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage( R.string.exsit)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!

                                sp.logOut();
                                sp.checkLogin();

                            }
                        })
                  .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog alert=builder.create();

                alert.show();

            }
        });

        //------------------------------------------------------------------

        //scan qr code camera------------------------------------

        final Activity activity;
        activity = this;

        scanbtn=findViewById(R.id.scanbtn);

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
               integrator.setOrientationLocked(false);
                integrator.setPrompt(getString(R.string.scan_qr_code));
               integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        //-----------------------------------------------------------------------------------------
    }



    //Profile data text send code----------------------------------------------------


    private void postRequestMethodProfile(){

        progressDialog =new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitProfileDataUrl retrofitProfileDataUrl = retrofit.create(RetrofitProfileDataUrl.class);


        String deviceid=WelcomeActivity.deviceid.toString();

        Intent i=getIntent();

        //token class object--------------------------------------

        TokenCreate tokenCreate=new TokenCreate();

        String tokencode=tokenCreate.getSpesificKey()+userid ;
        String token=TokenCreate.get_SHA_512_SecurePassword(tokencode);

        //--------------------------------------------------------



        Call<UserProfile> call = retrofitProfileDataUrl.profildata(userid,deviceid,token);
        Log.w("request",call.request().toString());

        call.enqueue(new Callback<UserProfile>()

        {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                progressDialog.dismiss();
                UserProfile userProfile = response.body();

                if (userProfile.getSuccess() && userProfile.getSetNewPassword() == false) {

                    named=userProfile.getSuccessMessage().getName();
                    surnamed=userProfile.getSuccessMessage().getSurname();
                    phoned=userProfile.getSuccessMessage().getPhone();
                    fathernamed=userProfile.getSuccessMessage().getFatherName();
                    groupd=userProfile.getSuccessMessage().getGrup();
                    group_Scheduled=userProfile.getSuccessMessage().getGroupSchedule();
                    lessonTimed=userProfile.getSuccessMessage().getLessonTime();
                    lessonBeginTimed=userProfile.getSuccessMessage().getLessonBeginTime();
                    lessonEndTimed=userProfile.getSuccessMessage().getLessonEndTime();
                    attendanceRatiod=userProfile.getSuccessMessage().getAttendanceRatio();


                    //textview settext------------------------------------------------------

                    String allname="";
                    if (named!=null){
                       allname+=named;
                    }if(surnamed!=null){
                       allname+=" "+surnamed;
                    } if(fathernamed!=null){
                        allname+=" "+fathernamed;
                    }
                    namesurname.setText(allname);

                    if (phoned!=null){ phone.setText(getString(R.string.phone)+phoned);}
                    if (groupd!=null){ group.setText(getString(R.string.group_000)+groupd);}
                    if (group_Scheduled!=null){group_Schedule.setText(""+group_Scheduled+"");}

                    if (lessonTimed!=null|| lessonBeginTimed!=null|| lessonEndTimed!=null){
                        lessonTime.setText(getString(R.string.lessontime)+lessonTimed+" - "+lessonBeginTimed+" "+lessonEndTimed);
                    }



                } else {
                    Toast.makeText(getApplicationContext(), userProfile.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                progressDialog.dismiss();
            }

        });


    }
    //----------------------------------------------------------------


    //qr code text send code----------------------------------------------------


    private void postRequestMethod(){

        progressDialog =new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitQRcode retrofitQRcode = retrofit.create(RetrofitQRcode.class);

        String deviceid=WelcomeActivity.deviceid.toString();

        //token class object--------------------------------------

        TokenCreate tokenCreate=new TokenCreate();

        String tokencode=tokenCreate.getSpesificKey()+userid;
        String token=TokenCreate.get_SHA_512_SecurePassword(tokencode);

        //--------------------------------------------------------

        Call<QRcodeResponse> call = retrofitQRcode.qrcodesend(userid,deviceid,qrcodetext,token);
        Log.w("request",call.request().toString());

        call.enqueue(new Callback<QRcodeResponse>()
        {
            @Override
            public void onResponse(Call<QRcodeResponse> call, Response<QRcodeResponse> response) {
                progressDialog.dismiss();
                QRcodeResponse qRcodeResponse = response.body();


                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    String upmsg="Empty";
                if (qRcodeResponse.getSuccess()&&qRcodeResponse.getSetNewPassword() == false) {
                    upmsg=qRcodeResponse.getSuccessMessage();
                    Toast.makeText(getApplicationContext(), qRcodeResponse.getSuccessMessage(), Toast.LENGTH_SHORT).show();
                    builder.setView(R.layout.chekediconlayout);
                } else {
                   upmsg= qRcodeResponse.getErrorMessage();
                    Toast.makeText(getApplicationContext(), qRcodeResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    builder.setView(R.layout.unchekediconlayout);
                }
                builder.setMessage(upmsg)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                AlertDialog alert=builder.create();

                alert.show();


            }

            @Override
            public void onFailure(Call<QRcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Fail Run", Toast.LENGTH_SHORT).show();
            }

        });


    }
    //----------------------------------------------------------------


//camrea result----------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if (result!=null){
            String nulltest=result.getContents();

            if (result.getContents()==null ){
                Toast.makeText(this, R.string.stop_scan,Toast.LENGTH_SHORT).show();
            }else if (nulltest==null ||nulltest==""||nulltest==" "){
                Toast.makeText(this,"empty QR code",Toast.LENGTH_SHORT).show();
            }else{
                    qrcodetext=nulltest;

                    Toast.makeText(this,getString(R.string.good_scan),Toast.LENGTH_SHORT).show();
                    postRequestMethod();
            }


            }else{
            super.onActivityResult(requestCode, resultCode, data);
            }


    }

//-------------------------------------------------------------------------------------------------------

}
