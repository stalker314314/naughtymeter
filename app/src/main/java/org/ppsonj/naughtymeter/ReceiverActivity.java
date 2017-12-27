package org.ppsonj.naughtymeter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.ppsonj.naughtymeter.view.SmileView;

public class ReceiverActivity extends AppCompatActivity {

    private Gauge mGauge;

    private TextView mReceivingName;

    private SmileView mSmileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mReceivingName = findViewById(R.id.txtReceivingName);
        mGauge = findViewById(R.id.speedView);
        mGauge.setSpeedAt(50);
        mSmileView = findViewById(R.id.smileView);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("streaming");

        database.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mReceivingName.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // screw cancel
            }
        });

        database.child("value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int speed = dataSnapshot.getValue(Integer.class);
                mGauge.setSpeedAt(speed);
                mSmileView.setVavlue(speed/10);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mGauge.setSpeedAt(50);
            }
        });
    }
}
