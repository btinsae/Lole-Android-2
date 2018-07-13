package fanos.com.lole.utilities;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Birhane on 2/20/2018.
 */

public class RecyclerViewDecorator extends RecyclerView.ItemDecoration {
private Drawable dividerDrawable;

    public RecyclerViewDecorator(Drawable dividerDrawable) {
        this.dividerDrawable = dividerDrawable;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        if (view = MY_VIEW_TYPE) {
//            outRect.set(0, 0, 0, mHeightDp);
//        } else {
//            outRect.setEmpty();
//        }
        if (parent.getChildAdapterPosition(view)==0){
            return;
        }
        outRect.top=dividerDrawable.getIntrinsicHeight();
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
      //  if (mode == Mode.SHOW_ITEMS) {

            int left = parent.getPaddingLeft()+144;
            int right = parent.getWidth() - parent.getPaddingRight();
            int childCount = parent.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + dividerDrawable.getIntrinsicHeight();

                dividerDrawable.setBounds(left, top, right, bottom);

                if ((parent.getChildAdapterPosition(child) == parent.getAdapter().getItemCount() - 1) && parent.getBottom() < bottom) { // this prevent a parent to hide the last item's divider
                    parent.setPadding(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getPaddingRight(), bottom - parent.getBottom());
                }

                dividerDrawable.draw(c);
            }
   // }


    }
}
