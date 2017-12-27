package org.ppsonj.naughtymeter.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import org.ppsonj.naughtymeter.R;

public class SmileView extends android.support.v7.widget.AppCompatImageView {

    public SmileView(Context context,
            @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setVavlue(int value) {
        switch (value) {
            case 0:
                setImageResource(R.drawable.image_part_003);
                break;
            case 1:
                setImageResource(R.drawable.image_part_009);
                break;
            case 2:
                setImageResource(R.drawable.image_part_006);
                break;
            case 3:
                setImageResource(R.drawable.image_part_007);
                break;
            case 4:
                setImageResource(R.drawable.image_part_008);
                break;
            case 5:
                setImageResource(R.drawable.image_part_001);
                break;
            case 6:
                setImageResource(R.drawable.image_part_004);
                break;
            case 7:
                setImageResource(R.drawable.image_part_005);
                break;
            default:
                setImageResource(R.drawable.image_part_002);

        }
    }
}
