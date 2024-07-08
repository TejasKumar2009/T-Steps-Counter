package com.vibroid.t_steps_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StepsTarget extends AppCompatActivity {
    EditText steps_target_inp;
    Button set_target_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_target2);

        steps_target_inp = findViewById(R.id.steps_target_inp);
        set_target_btn = findViewById(R.id.set_target_btn);

        set_target_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StepsTarget.this, steps_target_inp.getText(), Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("steps_target_data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("todayStepsTarget", Integer.parseInt(steps_target_inp.getText().toString()));
                editor.commit();

                Intent intent = new Intent(StepsTarget.this,  MainActivity.class);
                startActivity(intent);
            }
        });


    }
}