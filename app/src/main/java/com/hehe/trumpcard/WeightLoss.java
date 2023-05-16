package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeightLoss extends AppCompatActivity {

    private Button button, back;
    public static Button button7;
    private Button button3;
    public static Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss);

        button = (Button) findViewById(R.id.button18);
        button7 = (Button) findViewById(R.id.button19);
        button3 = (Button) findViewById(R.id.button21);
        button8 = (Button) findViewById(R.id.button20);
        back = (Button) findViewById(R.id.button54);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLoss.this, HIIT.class);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLoss.this, HIIT2.class);
                startActivity(intent);
            }
        });
        button7.setAlpha(WeightLossCheck1.alpha5);
        button7.setClickable(WeightLossCheck1.IsButtonClickable31);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLoss.this, Triminator.class);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLoss.this, LosePounds.class);
                startActivity(intent);
            }
        });
        button8.setAlpha(WeightLossCheck2.alpha6);
        button8.setClickable(WeightLossCheck2.IsButtonClickable32);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightLoss.this, Start.class);
                startActivity(intent);
            }
        });

    }
}
