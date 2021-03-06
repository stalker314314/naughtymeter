package org.ppsonj.naughtymeter;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.ppsonj.naughtymeter.view.SmileView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ReceiverActivity extends AppCompatActivity {

    private Gauge mGauge;

    private TextView mReceivingName;

    private SmileView mSmileView;

    private KonfettiView konfettiView;

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mContentView = findViewById(R.id.mainView);
        konfettiView = findViewById(R.id.viewKonfetti);
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
                mSmileView.setVavlue(speed / 10);
                doKonfetti(speed);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mGauge.setSpeedAt(50);
            }
        });
    }

    private void doKonfetti(int speed) {
        if (speed == 100) {

            // do konfetti
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.RECT, Shape.CIRCLE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .stream(300, 5000L);

            // flash background
            final AnimationDrawable drawable = new AnimationDrawable();
            final Handler handler = new Handler();
            drawable.addFrame(new ColorDrawable(Color.RED), 400);
            drawable.addFrame(new ColorDrawable(Color.GREEN), 400);
            drawable.setOneShot(false);
            final Drawable background = mContentView.getBackground();
            mContentView.setBackgroundDrawable(drawable);
            drawable.start();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawable.stop();
                    mContentView.setBackgroundDrawable(background);
                }
            }, 6000);
        }
    }
}
