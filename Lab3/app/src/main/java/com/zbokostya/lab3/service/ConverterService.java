package com.zbokostya.lab3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class ConverterService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public ConverterService getService() {
            Toast.makeText(ConverterService.this, "service starting", Toast.LENGTH_SHORT).show();
            return ConverterService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double convertFahrenheitToKelvin(double a) {
        return (a + 459.67) / 9 * 5;
    }

    public double convertCelsiusToKelvin(double a) {
        return a + 273.15;
    }

    public double convertCelsiusToFahrenheit(double a) {
        return a * 1.8 + 32;
    }

    public double convertFahrenheitToCelsius(double a) {
        return (a - 32) / 1.8;
    }

    public double convertKelvinToCelsius(double a) {
        return a - 273.15;
    }

    public double convertKelvinToFahrenheit(double a) {
        return a / 5 * 9 - 459.67;
    }


//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
//        return START_NOT_STICKY;
//    }
}
