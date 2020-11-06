package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView counter;
    EditText editText;

    int REQUEST_CODE = 100;
    String strExtra = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counter);

        if (getIntent().getStringExtra("counter") != null) {
            counter.setText(getIntent().getStringExtra(strExtra));
        }


        editText = findViewById(R.id.enterField);


        Button nextForm = findViewById(R.id.nextFormButton);
        nextForm.setOnClickListener(oclBtnOpenForm2);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(strExtra);
                TextView editor = findViewById(R.id.counter);
                editor.setText(result);

            }
        }
    }


    View.OnClickListener oclBtnOpenForm2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Form2.class);
            int val = Integer.parseInt(editText.getText().toString())
                    + Integer.parseInt(counter.getText().toString());
            intent.putExtra("counter", val + "");
            editText.setText("");
            startActivityForResult(intent, REQUEST_CODE);

        }
    };

}