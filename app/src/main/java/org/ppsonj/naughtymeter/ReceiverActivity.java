package org.ppsonj.naughtymeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ReceiverActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Gauge mGauge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mGauge = findViewById(R.id.speedView);
        mGauge.setSpeedAt(50);
        mDatabase = FirebaseDatabase.getInstance().getReference("streaming");

        mDatabase.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ((TextView)findViewById(R.id.txtReceivingName)).setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // screw cancel
            }
        });

        mDatabase.child("value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mGauge.setSpeedAt(dataSnapshot.getValue(Integer.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mGauge.setSpeedAt(50);
            }
        });
    }
}
