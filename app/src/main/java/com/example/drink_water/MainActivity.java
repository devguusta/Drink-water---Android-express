package com.example.drink_water;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timerPicker;

    private int hour;
    private int minute;
    private int interval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btnNotify = findViewById(R.id.btn_notify);
         editMinutes = findViewById(R.id.edit_txt_number_interval);
         timerPicker = findViewById(R.id.time_picker);
         timerPicker.setIs24HourView(true);

         btnNotify.setOnClickListener(notifyClick);
    }

    public View.OnClickListener notifyClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String sInterval =  editMinutes.getText().toString();

            hour = timerPicker.getCurrentHour();
            minute = timerPicker.getCurrentMinute();
            interval = Integer.parseInt(sInterval);
            Log.d("Teste","hora:" + hour + "minutor:" + minute + "intervalo" + interval);
        }
    };

//    public void notifyClick(View view) {
//
//
//    }
}