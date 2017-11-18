package com.example.vish.suv_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.name;

public class Adduser extends AppCompatActivity {
    Logout l;
    TextView loggeduser,verifying;
    EditText usernam,passwrd,repasswd,cntrname,address,phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);
        Button adduser=(Button) findViewById(R.id.button33);
        verifying = (TextView) findViewById(R.id.textView30);
        Button bt=(Button) findViewById(R.id.button34);
        l=new Logout(this);
        loggeduser=(TextView) findViewById(R.id.textView29);
        loggeduser.setText(Appconstants.username);
        cntrname = (EditText) findViewById(R.id.editText16);
        address = (EditText) findViewById(R.id.editText15);
        phno = (EditText) findViewById(R.id.editText6);
        usernam = (EditText) findViewById(R.id.editText7);
        passwrd = (EditText) findViewById(R.id.editText10);
        repasswd = (EditText) findViewById(R.id.editText11);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        } );

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String center_usenam = usernam.getText().toString();
                String center_pass = passwrd.getText().toString();
                String center_repass = repasswd.getText().toString();
                String center_name = cntrname.getText().toString();
                String center_address = address.getText().toString();
                String center_phno = phno.getText().toString();
                if(TextUtils.isEmpty(center_name)) {
                    verifying.setText("cannot be blank");
                    return;
                }if(TextUtils.isEmpty(center_address)) {
                    verifying.setText("cannot be blank");
                    return;
                }if(TextUtils.isEmpty(center_phno)) {
                    verifying.setText("cannot be blank");
                    return;
                }
                if(TextUtils.isEmpty(center_usenam)) {
                    verifying.setText("cannot be blank");
                    return;
                }
                if(usernam.getText().toString().substring(0, 1).matches("[0-9]")){
                    verifying.setText("First character must be alphabet");
                    return;
                }
                if(usernam.getText().toString().contains(" ")){
                    verifying.setText("Space not allowed in username");
                    return;
                }
                if(passwrd.getText().toString().length()<8){
                    verifying.setText("Password length less than 8 are not allowed");
                    return;
                }
                if (center_pass.compareTo(center_repass) ==0) {
                    verifying.setText("Matched");

                addcenter(center_usenam, center_pass,center_name,center_address,center_phno);
                }
                else
                {
                    verifying.setText("Does not Match");
                }
        }});

    }

    public void addcenter(String center_usename,String center_pass,String center_name,String center_address,String center_phno) {
        String url = "http://suviseshabhavan.com/suvi/android/addcenter.php?uname="+center_usename+"&pswd="+center_pass+"&center_name="+center_name+"&center_address="+center_address+"&center_phno="+center_phno;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                if(myResponse.substring(0,1).compareTo("1")==0) {
                    Adduser.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "center added Successfully", Toast.LENGTH_LONG).show();
                            //  Toast.makeText(getApplicationContext(),"Exam added Successfully",Toast.LENGTH_LONG).show();
                            //  txtString.setText(myResponse);
                        }
                    });
                }
                else{
                    Adduser.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "center already opened", Toast.LENGTH_LONG).show();
                            //  Toast.makeText(getApplicationContext(),"Exam added Successfully",Toast.LENGTH_LONG).show();
                            //  txtString.setText(myResponse);
                        }
                    });
                }

            }
        });





    }


}
