package org.ppsonj.naughtymeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.anastr.speedviewlib.Gauge;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        Gauge gauge = findViewById(R.id.speedView);
        gauge.setSpeedAt(50);
    }
}
