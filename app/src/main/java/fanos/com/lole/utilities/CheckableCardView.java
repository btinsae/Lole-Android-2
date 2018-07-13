package fanos.com.lole.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.widget.Checkable;

public class CheckableCardView extends CardView implements Checkable {

    private boolean isChecked = false;
    public CheckableCardView(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setChecked(boolean b) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
