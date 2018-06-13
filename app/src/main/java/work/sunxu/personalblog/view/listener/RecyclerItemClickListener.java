package work.sunxu.personalblog.view.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * class description here
 *
 * @author sunxu
 * @since 2018-06-13
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;

    public interface  OnItemClickListener{
         void onItemClick(View view, int position);
    }


    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mOnItemClickListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }


    @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mOnItemClickListener != null && mGestureDetector.onTouchEvent(e)) {
            mOnItemClickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }


    @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }


    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
