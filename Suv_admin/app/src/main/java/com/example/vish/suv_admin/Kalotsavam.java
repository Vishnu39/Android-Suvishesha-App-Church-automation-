package com.example.vish.suv_admin;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Kalotsavam extends AppCompatActivity {
    Logout l;
    TextView t1,t2;
    EditText evnt,dt;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalotsavam);
        Button bt=(Button) findViewById(R.id.button21);
        l=new Logout(this);
        evnt = (EditText) findViewById(R.id.editText13);
        dt = (EditText) findViewById(R.id.editText14);
        t1=(TextView) findViewById(R.id.textView17);
        t2=(TextView) findViewById(R.id.textView19);
        t1.setText(Appconstants.username);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        Button bt1=(Button) findViewById(R.id.button20);
        final    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Kalotsavam.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                addKalotsavam(evnt.getText().toString(),dt.getText().toString());
            }}
        });

    }
    public boolean validation() {
        if (evnt.getText().toString().compareTo("") == 0) {
            t2.setText("Event and Date Cannot be blank");
            return false;
        }
        if (dt.getText().toString().compareTo("") == 0) {
            t2.setText("Event and Date Cannot be blank");
            return false;
        }
        if(!verifyingDate(dt.getText().toString())){
            t2.setText("Invalid Date");
            return false;
        }
        return true;

    }


    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    public void addKalotsavam(String evnt,String dt) {
        String url = "http://suviseshabhavan.com/suvi/android/addkal.php?evnt="+evnt+"&dt="+dt;
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

                Kalotsavam.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Event added Successfully",Toast.LENGTH_LONG).show();
                        //  txtString.setText(myResponse);
                        t2.setText("scheduled");
                    }
                });

            }
        });





    }
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(myFormat, Locale.US);

        dt.setText(sdf.format(myCalendar.getTime()));
    }
    public boolean verifyingDate(String s){
        try{
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Date strDate = sdf.parse(s);

            if (new Date().after(strDate)) {
                return false;
            }
        }catch (Exception e)
        {

        }
        return true;
    }
}
