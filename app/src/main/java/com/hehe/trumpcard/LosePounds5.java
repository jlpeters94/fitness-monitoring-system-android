package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LosePounds5 extends AppCompatActivity {

    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_pounds5);

        button = (Button) findViewById(R.id.button29);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LosePounds5.this, LosePounds6.class);
                startActivity(intent);
            }
        });

        button2 = (Button) findViewById(R.id.button39);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LosePounds5.this, LosePounds4.class);
                startActivity(intent);
            }
        });


    }
}
