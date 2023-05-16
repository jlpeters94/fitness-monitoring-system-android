package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Triminator extends AppCompatActivity {

    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit);

        button2 = (Button) findViewById(R.id.button30);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Triminator.this, WeightLossCheck2.class);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.button47);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Triminator.this, WeightLoss.class);
                startActivity(intent);
            }
        });

    }
}
