package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeightGain extends AppCompatActivity {

    private Button button, back;
    public static Button button2;
    private Button button3;
    public static Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_gain);

        button = (Button) findViewById(R.id.button18);
        button2 = (Button) findViewById(R.id.button19);
        button3 = (Button) findViewById(R.id.button21);
        button4 = (Button) findViewById(R.id.button20);
        back = (Button) findViewById(R.id.button54);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGain.this, StartingStrength.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGain.this, Candito.class);
                startActivity(intent);
            }
        });
        button2.setAlpha(WeightGainCheck1.alpha3);
        button2.setClickable(WeightGainCheck1.IsButtonClickable21);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGain.this, Hardgainer.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGain.this, HardgainerTwo.class);
                startActivity(intent);
            }
        });
        button4.setAlpha(WeightGainCheck2.alpha4);
        button4.setClickable(WeightGainCheck2.IsButtonClickable22);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightGain.this, Start.class);
                startActivity(intent);
            }
        });

    }
}
