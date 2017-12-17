package com.macormap.connectfiveducks;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by carlo on 03/09/2017.
 */

public class PointsFragment extends Fragment implements View.OnClickListener {

    PointsFragment.OnButtonPointsListener mCallback;

    public interface OnButtonPointsListener {
        /** Called by MenuFragment when button click */
        public void onButtonPointsTouch(int butIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.points_layout, container, false);
        setTheClickListenerToButton(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (PointsFragment.OnButtonPointsListener) context; }
        catch (ClassCastException e)
        { throw new ClassCastException(context.toString() + " must implement OnButtonPointsListener"); }
    }

    @Override
    // if a button is clicked -> in MainActivity start the CallBack "onButtonMenuTouch"
    // with relative index of button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbut_points:  mCallback.onButtonPointsTouch(0); break;
        }
    }

    // set the onClick for the two buttons choise of to exit or not
    private void setTheClickListenerToButton(View view) {
        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.backbut_points);
        imgbtn.setOnClickListener(this);
    }


    // set the correct position and dimension of the two buttons Yes-No
    public void setUpPositions() {

        RelativeLayout.LayoutParams btn_param, img_param; // used for all buttons
        ImageButton localImgButton;  // used for all Buttons
        // get info about dimension of current device screen
        DisplayMetrics dimScreen ;
        dimScreen    =  getActivity().getApplicationContext().getResources().getDisplayMetrics();
        int   w      = (int) (dimScreen.widthPixels);
        int   h      = (int) (dimScreen.heightPixels);
        float scaleX = (float)(w/480.0f);    // all dimension are relative at a screen of 480 px With
        float scaleY = (float)(h/800.0f);    // all dimension are relative at a screen of 800 px Height

        int x1,y1,dimx,dimy; // local var to set the position and dim of menu buttons.
        // set the correct position and dim for each button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_points);
        dimx = (int) (140*scaleX);  // correct dimension of button
        x1   = (int) (10*scaleX);
        dimy = (int) (80*scaleY);
        y1   = (int) (h-(dimy+4*scaleY));

        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);
    }

    // start animation of button and title of Menu
    public void animationPoints() {
        // load animations and set relative dalay
        Animation animButMenu1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.backbut_anim);
        ImageButton localImgButton;
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_points);
        localImgButton.startAnimation(animButMenu1);
    }


}
