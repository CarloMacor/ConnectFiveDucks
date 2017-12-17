package com.macormap.connectfiveducks;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by carlo on 03/09/2017.
 */

public class ExitFragment extends Fragment implements View.OnClickListener {

    ExitFragment.OnButtonExitListener mCallback;

    public interface OnButtonExitListener {
        /** Called by MenuFragment when button click */
        public void onButtonExitTouch(int butIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.exit_layout, container, false);
        setTheClickListenerToButton(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (ExitFragment.OnButtonExitListener) context; }
        catch (ClassCastException e)
        { throw new ClassCastException(context.toString() + " must implement OnButtonExitListener"); }
    }

    @Override
    // if a button is clicked -> in MainActivity start the CallBack "onButtonMenuTouch"
    // with relative index of button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_exityes:  mCallback.onButtonExitTouch(10); break;
            case R.id.but_exitno:   mCallback.onButtonExitTouch(11); break;
        }
    }

    // set the onClick for the two buttons choise of to exit or not
    private void setTheClickListenerToButton(View view) {
        ImageButton imgbtn = null;
        imgbtn = (ImageButton) view.findViewById(R.id.but_exityes);
        if (imgbtn!=null) { imgbtn.setOnClickListener(this); }
        imgbtn = (ImageButton) view.findViewById(R.id.but_exitno);
        if (imgbtn!=null) { imgbtn.setOnClickListener(this); }
    }

    // set the correct position and dimension of the two buttons Yes-No
    public void setUpPositions() {
        RelativeLayout.LayoutParams btn_param, img_param; // used for all buttons
        ImageButton localImgButton;  // used for all Buttons
        ImageView   localImageView;

        int x1,y1,dimx,dimy; // local var to set the position and dim of menu buttons.
        // set the correct position and dim for each button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exityes);
        dimx = (int) (110*Varbase.scaleX);  // correct dimension of button
        x1   = (int) (120*Varbase.scaleX);
        dimy = (int) (60*Varbase.scaleY);
        y1   = (int) (390*Varbase.scaleY);

        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        if (localImgButton != null) { localImgButton.setLayoutParams(btn_param); }

        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exitno);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        x1   = (int) (270*Varbase.scaleX);  // note : x1,dimx,dimy  remain the same previous value
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        if (localImgButton != null) { localImgButton.setLayoutParams(btn_param); }

    }

    // start animation of button and title of Menu
    public void animationExit() {
        int initoffset  = 40;
        // load animations and set relative dalay
        Animation animButMenu1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.menubut_enter);
        animButMenu1.setStartOffset(initoffset);
        ImageButton localImgButton;
        // start animation to two Buttons ( start in the same time )
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exityes);
        if (localImgButton != null) { localImgButton.startAnimation(animButMenu1); }
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exitno);
        if (localImgButton != null) { localImgButton.startAnimation(animButMenu1); }
    }






}
