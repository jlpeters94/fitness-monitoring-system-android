package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuscleGain extends AppCompatActivity {

    private Button button, back;
    public static Button button5;
    private Button button3;
    public static Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_gain);

        button = (Button) findViewById(R.id.button18);
        button5 = (Button) findViewById(R.id.button19);
        button3 = (Button) findViewById(R.id.button21);
        button6 = (Button) findViewById(R.id.button20);
        back = (Button) findViewById(R.id.button54);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuscleGain.this, PPL.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuscleGain.this, JNT.class);
                startActivity(intent);
            }
        });
        button5.setAlpha(MuscleGainCheck1.alpha);
        button5.setClickable(MuscleGainCheck1.IsButtonClickable41);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuscleGain.this, StrongMeals.class);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuscleGain.this, Summon.class);
                startActivity(intent);
            }
        });
        button6.setAlpha(MuscleGainCheck2.alpha2);
        button6.setClickable(MuscleGainCheck2.IsButtonClickable42);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuscleGain.this, Start.class);
                startActivity(intent);
            }
        });

    }
}
