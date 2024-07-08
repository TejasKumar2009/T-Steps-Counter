package com.vibroid.t_steps_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TrackingActivity extends AppCompatActivity implements SensorEventListener {

    Button stop_tracking_btn;
    TextView steps;

    SensorManager sensorManager;
     Sensor steps_counter_sensor;

    float stepsno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        Intent intent3 = getIntent();
        if (intent3!=null){

        }


        stop_tracking_btn = findViewById(R.id.stop_tracking);
        steps = findViewById(R.id.steps);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager!=null) {
//            Toast.makeText(this, "Great!", Toast.LENGTH_SHORT).show();

            steps_counter_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

            if (steps_counter_sensor == null) {
                Toast.makeText(this, "The sensor is not available !", Toast.LENGTH_SHORT).show();
            } else {
//                sensorManager.registerListener(this, steps_counter_sensor, SensorManager.SENSOR_DELAY_UI    );
//                Toast.makeText(this, "Sensor is available !", Toast.LENGTH_SHORT).show();
            }

        }



        stop_tracking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, steps_counter_sensor, SensorManager.SENSOR_DELAY_NORMAL);
//        Toast.makeText(this, "Registered !", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
////        Toast.makeText(this, "Unregistered !", Toast.LENGTH_SHORT).show();
//    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        Toast.makeText(this, "Almost there !", Toast.LENGTH_SHORT).show();
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            stepsno = sensorEvent.values[0];

            SharedPreferences sharedPreferences = getSharedPreferences("steps_target_data", MODE_PRIVATE);
            int steps_target = sharedPreferences.getInt("todayStepsTarget", 0);

            steps.setText(stepsno+" / "+steps_target);

//            boolean isReset = bundle.getBoolean("isReset");

            if (stepsno>steps_target){
                Toast.makeText(this, "You have completed your Today's goal !", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences2 = getSharedPreferences("steps_target_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putBoolean("isTargetCompleted", true);
                editor.commit();


                    }

            }
            
        }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}