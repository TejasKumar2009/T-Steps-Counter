package com.vibroid.t_steps_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.LineNumberReader;

public class MainActivity extends AppCompatActivity {

    Button set_target;
    Button start_tracking_btn, reset;
    TextView steps_target_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set_target = findViewById(R.id.set_target);
        steps_target_text = findViewById(R.id.steps_target_text);
        start_tracking_btn = findViewById(R.id.start_tracking);


        SharedPreferences sharedPreferences = getSharedPreferences("steps_target_data", MODE_PRIVATE);
        int steps_target = sharedPreferences.getInt("todayStepsTarget", 0);
        steps_target_text.setText("Steps Target : "+String.valueOf(steps_target));



        set_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  StepsTarget.class);
                startActivity(intent);
            };

        });

        start_tracking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  TrackingActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Please Don't close the app !", Toast.LENGTH_SHORT).show();
            };

        });

    }
}
