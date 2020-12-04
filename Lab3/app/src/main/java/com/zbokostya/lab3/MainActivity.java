package com.zbokostya.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.zbokostya.lab3.service.ConverterService;

public class MainActivity extends AppCompatActivity {

    Boolean isServiceBind;
    ConverterService converterService = null;
    int current = 0;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextDegree);
        bindService(new Intent(this, ConverterService.class), scon, BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    protected void onStop() {

        unbindService(scon);
        super.onStop();
    }

    ServiceConnection scon = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("1", "MainActivity onServiceConnected");
            ConverterService.LocalBinder binder = (ConverterService.LocalBinder) service;
            converterService = binder.getService();
            isServiceBind = true;
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d("1", "MainActivity onServiceDisconnected");
            converterService = null;
            isServiceBind = false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        double degree = Double.parseDouble(editText.getText().toString());
        //0 = cel
        //1 = kelvin
        //2 = far
        switch (item.getItemId()) {
            case R.id.kelvin:
                if (current == 0) {
                    degree = converterService.convertCelsiusToKelvin(degree);
                } else if (current == 2) {
                    degree = converterService.convertFahrenheitToKelvin(degree);
                }
                editText.setText(degree + "");
                current = 1;
                return true;
            case R.id.celsius:
                if (current == 1) {
                    degree = converterService.convertKelvinToCelsius(degree);
                } else if (current == 2) {
                    degree = converterService.convertFahrenheitToCelsius(degree);
                }
                editText.setText(degree + "");
                current = 0;
                return true;
            case R.id.fahrenheit:
                if (current == 1) {
                    degree = converterService.convertKelvinToFahrenheit(degree);
                } else if (current == 0) {
                    degree = converterService.convertCelsiusToFahrenheit(degree);
                }
                editText.setText(degree + "");
                current = 2;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}