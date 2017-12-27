package org.ppsonj.naughtymeter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SenderActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        mName = findViewById(R.id.txtName);
        mDatabase = FirebaseDatabase.getInstance().getReference("streaming");

        findViewById(R.id.btnPushName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("name").setValue(mName.getText().toString());
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mDatabase.child("value").setValue(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // in swing, there were abstract classes for listeners, how come?
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // in swing, there were abstract classes for listeners, how come?
            }
        });
    }
}
