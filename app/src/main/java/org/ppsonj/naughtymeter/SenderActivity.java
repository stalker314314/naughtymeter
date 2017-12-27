package org.ppsonj.naughtymeter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SenderActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private AutoCompleteTextView acName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        acName = findViewById(R.id.acName);
        acName.setCompletionHint("kids name");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_singlechoice, getResources().getStringArray(R.array.kids));
        acName.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("streaming");

        findViewById(R.id.btnPushName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("name").setValue(acName.getText().toString());
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
