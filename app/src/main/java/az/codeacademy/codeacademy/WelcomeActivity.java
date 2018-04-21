package az.codeacademy.codeacademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import az.codeacademy.codeacademy.shared.preference.SharedPreferenceA;

public class WelcomeActivity extends AppCompatActivity {

    public static String deviceid;
    SharedPreferenceA sp;
    private ProgressDialog progressDialog;
    String userid;

    public static int time=2000;
    TextView idtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //getting unique id for device-----------------------------------
       deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        idtext=findViewById(R.id.idtext);

        idtext.setText("Devices Id : "+deviceid+"\n"+"@Code Academy 2018");

        //---------------------------------------------------------------------------


        sp=new SharedPreferenceA(WelcomeActivity.this);
        HashMap<String,String> map=sp.DetailStore();
        userid=map.get(sp.KEY_ID);
        Log.e("Prof",""+userid);



        //welcome screen---------------------------------------------------------------


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                sp.checkLogin();
                if (userid!=null){

                    startActivity(new Intent(WelcomeActivity.this, ProfileActivity.class));


                    finish();
                }

            }
        },time);

        //----------------------------------------------------------------------------------

    }


}
