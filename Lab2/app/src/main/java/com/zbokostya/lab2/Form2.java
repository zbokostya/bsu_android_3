package com.zbokostya.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zbokostya.lab2.R;

public class Form2 extends AppCompatActivity {
    TextView counter;
    EditText editText;

    String strExtra = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counter);
        String str = getIntent().getStringExtra(strExtra);
        if (getIntent().getStringExtra(strExtra) != null) {
            counter.setText(str);
        }

        Button nextForm = findViewById(R.id.nextFormButton);
        nextForm.setOnClickListener(oclBtnOpenForm2);

        editText = findViewById(R.id.enterField);
        editText.setText("");


    }

    View.OnClickListener oclBtnOpenForm2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            int val = Integer.parseInt(editText.getText().toString())
                    + Integer.parseInt(counter.getText().toString());
            intent.putExtra(strExtra, val + "");
            editText.setText("");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

}
