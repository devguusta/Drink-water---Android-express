package com.example.drink_water;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timerPicker;
    Context context;
    private int hour;
    private int minute;
    private int interval;
    private boolean activated;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context  = getApplicationContext();

         btnNotify = findViewById(R.id.btn_notify);
         editMinutes = findViewById(R.id.edit_txt_number_interval);
         timerPicker = findViewById(R.id.time_picker);
         timerPicker.setIs24HourView(true);

         btnNotify.setOnClickListener(notifyClick);
         preferences = getSharedPreferences("db", Context.MODE_PRIVATE);
     activated =  preferences.getBoolean("activated", false);
         if(activated){
             btnNotify.setText(R.string.pause);

             btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(context, android.R.color.black));
          int interval =  preferences.getInt("interval", 0);
          int hour =   preferences.getInt("hour", timerPicker.getCurrentHour());
          int minute =   preferences.getInt("minute", timerPicker.getCurrentMinute());
          editMinutes.setText(String.valueOf(interval));
          timerPicker.setCurrentHour(hour);
          timerPicker.setCurrentMinute(minute);

         }
    }

    public View.OnClickListener notifyClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String sInterval =  editMinutes.getText().toString();

            if(sInterval.isEmpty()){

                CharSequence text = "Digite o intervalo de tempo";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
           toast.show();
                return;
            }

            hour = timerPicker.getCurrentHour();
            minute = timerPicker.getCurrentMinute();
            interval = Integer.parseInt(sInterval);

            if(!activated){
                btnNotify.setText(R.string.pause);

                btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(context, android.R.color.black));
                activated = true;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("activated", true);
                editor.putInt("interval", interval);
                editor.putInt("hour", hour);
                editor.putInt("minute", minute);
                editor.apply();
            } else {
                btnNotify.setText(R.string.pause);

                btnNotify.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.teal_200));
                activated = false;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("activated", false);
                editor.remove("interval");
                editor.remove("hour");
                editor.remove("minute");
                editor.apply();
            }



            Log.d("Teste","hora:" + hour + "minutor:" + minute + "intervalo" + interval);
        }
    };

//    public void notifyClick(View view) {
//
//
//    }
}