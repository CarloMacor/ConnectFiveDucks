package com.macormap.connectfiveducks;

import android.app.Activity;
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
 * Created by Carlo Macor on 03/09/2017.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {
    OnButtonMenuListener mCallback;

    public interface OnButtonMenuListener {
        /** Called by MenuFragment when button click */
        public void onButtonMenuTouch(int butIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu_layout, container, false);
        setTheClickListenerToButton(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (OnButtonMenuListener) context; }
           catch (ClassCastException e)
            { throw new ClassCastException(context.toString() + " must implement OnButtonMenuListener"); }
    }


    @Override
    // if Menu button clicked -> in MainActivity start the CallBack "onButtonMenuTouch"
    // with relative index of button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_play:  PreCloseFrag(); mCallback.onButtonMenuTouch(1); break;
            case R.id.but_setup: PreCloseFrag(); mCallback.onButtonMenuTouch(2); break;
            case R.id.but_rules: PreCloseFrag(); mCallback.onButtonMenuTouch(3); break;
            case R.id.but_exit:  PreCloseFrag(); mCallback.onButtonMenuTouch(4); break;
        }
    }


    // set the onClick for each button of menu
    private void setTheClickListenerToButton(View view) {
        ImageButton imgbtn = null;
        imgbtn = (ImageButton) view.findViewById(R.id.but_play);
        imgbtn.setOnClickListener(this);
        imgbtn = (ImageButton) view.findViewById(R.id.but_setup);
        imgbtn.setOnClickListener(this);
        imgbtn = (ImageButton) view.findViewById(R.id.but_rules);
        imgbtn.setOnClickListener(this);
        imgbtn = (ImageButton) view.findViewById(R.id.but_exit);
        imgbtn.setOnClickListener(this);
    }

    // set the correct position and dimension of buttons and Title of Menu
    public void setUpPositions() {

        RelativeLayout.LayoutParams btn_param, img_param; // used for all buttons and title
        ImageButton localImgButton;  // used for all Buttons
        ImageView   localImageView;
        // get info about dimension of current device screen
        DisplayMetrics  dimScreen ;
        dimScreen    =  getActivity().getApplicationContext().getResources().getDisplayMetrics();
        int   w      = (int) (dimScreen.widthPixels);
        int   h      = (int) (dimScreen.heightPixels);
        float scaleX = (float)(w/480.0f);    // all dimension are relative at a screen of 480 px With
        float scaleY = (float)(h/800.0f);    // all dimension are relative at a screen of 800 px Height

        int x1,y1,dimx,dimy; // local var to set the position and dim of menu buttons.
        // set the correct position and dim for each button
        // 5^ button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exit);
        dimx = (int) (280*scaleX);  // correct dimension of button
        x1   = (int) ((w-dimx)/2);  // to be centered
        dimy = (int) (80*scaleY);
        y1   = (int) (620*scaleY);

        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

        // 4^ button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_rules);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        y1   = (int) (540*scaleY);  // note : x1,dimx,dimy  remain the same previous value
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

        // 3^ button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_setup);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        y1   = (int) (460*scaleY);  // note : x1,dimx,dimy  remain the same previous value
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

        // 2^ button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_play);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        y1   = (int) (380*scaleY);  // note : x1,dimx,dimy  remain the same previous value
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);



        // Title 1 row
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view);
        img_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dimx = (int) (420*scaleX);  // correct dimension of Title 1^ row
        x1   = (int) ((w-dimx)/2);  // to be centered
        dimy = (int) (120*scaleY);
        y1   = (int) (60*scaleY);
        img_param.leftMargin = x1;
        img_param.topMargin  = y1;
        img_param.width      = dimx;
        img_param.height     = dimy;
        localImageView.setLayoutParams(img_param);
        // Title 2 row
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view2);
        img_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dimx = (int) (300*scaleX);  // correct dimension of Title 1^ row
        x1   = (int) ((w-dimx)/2);  // to be centered
        dimy = (int) (120*scaleY);
        y1   = (int) (200*scaleY);
        img_param.leftMargin = x1;
        img_param.topMargin  = y1;
        img_param.width      = dimx;
        img_param.height     = dimy;
        localImageView.setLayoutParams(img_param);

    }

    // start animation of button and title of Menu
    public void animationMenu() {
        int initoffset  = 30;
        int offsetStart = 40;
        // load animations and set relative dalay
        Animation animTitle   = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.title_anim);
        Animation animButMenu1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.menubut_enter);
        animButMenu1.setStartOffset(initoffset);
        Animation animButMenu2 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.menubut_enter);
        animButMenu2.setStartOffset(initoffset+offsetStart*1);
        Animation animButMenu3 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.menubut_enter);
        animButMenu3.setStartOffset(initoffset+offsetStart*2);
        Animation animButMenu4 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.menubut_enter);
        animButMenu4.setStartOffset(initoffset+offsetStart*3);

        ImageButton localImgButton;
        ImageView   localImageView;

        // start animation to Title
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view);
        localImageView.startAnimation(animTitle);
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view2);
        localImageView.startAnimation(animTitle);

        // start animation to Buttons
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_play);
        localImgButton.startAnimation(animButMenu1);
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_setup);
        localImgButton.startAnimation(animButMenu2);
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_rules);
        localImgButton.startAnimation(animButMenu3);
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exit);
        localImgButton.startAnimation(animButMenu4);

    }


    private void PreCloseFrag() {
        ImageButton localImgButton;
        ImageView   localImageView;
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view);
        localImageView.clearAnimation();
        localImageView = (ImageView) getActivity().findViewById(R.id.idtitolo_view2);
        localImageView.clearAnimation();
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_play);
        localImgButton.clearAnimation();
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_setup);
        localImgButton.clearAnimation();
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_rules);
        localImgButton.clearAnimation();
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_exit);
        localImgButton.clearAnimation();
    }


}
