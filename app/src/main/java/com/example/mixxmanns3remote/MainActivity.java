package com.example.mixxmanns3remote;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.google.android.material.snackbar.Snackbar;
import com.triggertrap.seekarc.SeekArc;
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    // private SeekArc mSeekArc;
    private ToggleButton tb;
    private SeekArc mSeekArc;
    private TextView mSeekArcProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter bt =  BluetoothAdapter.getDefaultAdapter();
        if (bt!=null) {
            Toast.makeText(this, "BLUETOOTH adapter not available", Toast.LENGTH_LONG).show();
            finish();
        }
        if (bt.isEnabled()) {
            // Bluetooth включен. Работаем.
        }
        else
        {
            // Bluetooth выключен. Предложим пользователю включить его.
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        mSeekArc = findViewById(R.id.seekArc);
        mSeekArc.setEnabled(false);
        mSeekArcProgress = findViewById(R.id.seekArcProgress);
        mSeekArcProgress.setEnabled(false);
        tb = findViewById(R.id.toggleButton);
        tb.setEnabled(false);
        mSeekArc.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onProgressChanged(SeekArc seekArc, int progress,
                                          boolean fromUser) {
                mSeekArcProgress.setText(String.valueOf(progress));
            }
        });


    }

    public void onToggleClicked(View view) {

        // включена ли кнопка
        boolean on = tb.isChecked();
        if (on) {
            // действия если включена
            Toast.makeText(this, "STARTED", Toast.LENGTH_SHORT).show();
            view.setBackgroundResource(R.color.bgdisable);
        } else {
            // действия, если выключена
            Toast.makeText(this, "STOPPED", Toast.LENGTH_SHORT).show();
            view.setBackgroundResource(R.color.bgenable);
        }
    }
}