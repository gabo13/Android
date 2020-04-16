package com.example.alarm1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlert();
            }
        });
    }
    protected void startAlert() {
        EditText text = (EditText) findViewById(R.id.time);
        Toast.makeText(MainActivity.this,"Button clicked...",Toast.LENGTH_LONG).show();

        int i;
        try {
            i = Integer.parseInt(text.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Wrong time",Toast.LENGTH_SHORT).show();
            i = 0;
        }
        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),123456789, intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(this.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(i*1000),pendingIntent);
        Toast.makeText(this, "Alarm set: "+i,Toast.LENGTH_SHORT).show();
    }
}
