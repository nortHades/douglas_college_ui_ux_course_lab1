package com.example.viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomTouchListener implements View.OnTouchListener{
    GestureDetector gestureDetector;
    Context context;

    public CustomTouchListener(Context context) {
        this.context = context;
        gestureDetector = new GestureDetector(context,new CustomGestureListener());
    }


    public class CustomGestureListener
        extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            onDoubleClick();
            return true;
        }

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            onSingleClick();
            return true;
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            float SWIPE_DIST_THRESHOLD = 10;
            float SWIPE_VEL_THRESHOLD = 20;

            float xOff = e2.getX() - e1.getX();
            float yOff = e2.getY() - e1.getY();

            if (Math.abs(xOff) > Math.abs(yOff)
                && Math.abs(xOff) > SWIPE_DIST_THRESHOLD
                && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe has occurred
                if (xOff > 0){
                    onRightSwipe();
                }else{
                    onLeftSwipe();
                }

            }else if(Math.abs(yOff) > Math.abs(xOff)
                    && Math.abs(yOff) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD
            ){
                //vertical swipe has occurred
                if(yOff > 0){
                    onDownSwipe();
                }else{
                    onUpSwipe();
                }
            }


            return true;
        }



        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            onLongClick();
        }
    }

    public void onDownSwipe() {
        Log.d("GESTURE DEMO", "Down Swipe gesture detected");
    }

    public void onUpSwipe() {
        Log.d("GESTURE DEMO", "Up Swipe gesture detected");
    }

    public void onLeftSwipe() {
        Log.d("GESTURE DEMO", "Left Swipe gesture detected");
    }
    public void onRightSwipe() {
        Log.d("GESTURE DEMO", "Right Swipe gesture detected");
    }
    public void onSingleClick() {
        Log.d("GESTURE DEMO", "Single click gesture detected");
    }

    public void onLongClick() {
        Log.d("GESTURE DEMO", "Long click gesture detected");
    }

    public void onDoubleClick() {
        Log.d("GESTURE DEMO","Double click gesture detected");
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
