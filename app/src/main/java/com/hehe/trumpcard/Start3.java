package com.hehe.trumpcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start3 extends AppCompatActivity {

    private EditText num;
    private Button button, button2;
    private String num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start3);

        num = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button12);
        button2 = (Button) findViewById(R.id.button10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = num.getText().toString();

                Intent intent = new Intent(Start3.this, Start33.class);
                intent.putExtra("num1", num1);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Start3.this, Methods.class);
                startActivity(intent);
            }
        });

    }
}
