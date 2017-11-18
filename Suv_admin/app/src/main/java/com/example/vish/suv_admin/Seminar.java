package com.example.vish.suv_admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Seminar extends AppCompatActivity {
    Logout l;
    TextView t1,t2;
    Spinner spinner1;
    int id[];
    EditText tittle,by,topic,tm,dt;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seminar);
        Button bt=(Button) findViewById(R.id.button18);
        l=new Logout(this);
        t1=(TextView) findViewById(R.id.textView13);
        t2=(TextView) findViewById(R.id.textView16);
        t1.setText(Appconstants.username);
        topic = (EditText) findViewById(R.id.editText3);
        tm = (EditText) findViewById(R.id.editText4);
        dt = (EditText) findViewById(R.id.editText5);
        tittle=(EditText)findViewById(R.id.editText8);
        by=(EditText)findViewById(R.id.editText12);

        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Seminar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tm.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

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
                new DatePickerDialog(Seminar.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        spinner1=(Spinner) findViewById(R.id.spinner3);
        Button bt1=(Button) findViewById(R.id.button16);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    addseminar(topic.getText().toString(), tm.getText().toString(), dt.getText().toString(), spinner1.getSelectedItem().toString(), tittle.getText().toString(), by.getText().toString());
                }
            }
        });

        getUsers();
    }
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(myFormat, Locale.US);

        dt.setText(sdf.format(myCalendar.getTime()));
    }
        void getUsers(){

        String url = "http://suviseshabhavan.com/suvi/android/getcenter_2.php";
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

                Seminar.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showUser(myResponse);
                        //  startMainpage();

                        //  txtString.setText(myResponse);
                    }
                });

            }
        });
    }
    void showUser(String user)
    {

        try {
            JSONObject obj=new JSONObject(user);
            JSONArray arr = new JSONArray(obj.get("user").toString());
            String[]  users=new String[arr.length()];
            id=new int[arr.length()];
            List<String> userlist = new ArrayList<String>();



            // Creating adapter for spinner

            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj1=new JSONObject(arr.getString(i));

                userlist.add(obj1.getString("cname"));
                id[i]=Integer.parseInt(obj1.getString("cid"));
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userlist);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            // Binding resources Array to ListAdapter



        } catch(Exception e){
            e.printStackTrace();
        }

    }
    public boolean validation() {
        if (topic.getText().toString().compareTo("") == 0) {
            t2.setText("Topic,Date and Time Cannot be blank");
            return false;
        }
        if (tm.getText().toString().compareTo("") == 0) {
            t2.setText("Topic,Date and Time Cannot be blank");
            return false;
        }
        if (tittle.getText().toString().compareTo("") == 0) {
            t2.setText("Contents Cannot be blank");
            return false;
        }
        if (by.getText().toString().compareTo("") == 0) {
            t2.setText("Contents Cannot be blank");
            return false;
        }
        if (dt.getText().toString().compareTo("") == 0) {
            t2.setText("Topic,Date and Time Cannot be blank");
            return false;
        }
        if(!verifyingDate(dt.getText().toString())){
            t2.setText("Invalid Date");
            return false;
        }

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

        if (spinner1.getSelectedItem().toString().compareTo("") == 0) {
            t2.setText("Topic,Date,Time and Place Cannot be blank");
            return false;
        }

        return true;
    }

    public void addseminar(String topic,String tm,String dt,String place,String tittle,String by) {
        String url = "http://suviseshabhavan.com/suvi/android/addseminar.php?topic="+topic+"&tm="+tm+"&dt="+dt+"&place="+place+"&tittle="+tittle+"&by="+by;
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

                Seminar.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Seminar added Successfully",Toast.LENGTH_LONG).show();
                        //  txtString.setText(myResponse);
                        t2.setText("scheduled");
                    }
                });

            }
        });





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
