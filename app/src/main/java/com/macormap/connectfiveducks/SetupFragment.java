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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by carlo on 03/09/2017.
 */

public class SetupFragment extends Fragment implements View.OnClickListener {


    SetupFragment.OnButtonSetupListener mCallback;

    public interface OnButtonSetupListener {
        /** Called by MenuFragment when button click */
        public void onButtonSetupTouch(int butIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setup_layout, container, false);
        setTheClickListenerToButton(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (SetupFragment.OnButtonSetupListener) context; }
        catch (ClassCastException e)
        { throw new ClassCastException(context.toString() + " must implement OnButtonSetupListener"); }
        Log.v("setupFragment ", "onAttach : ");
    }

    @Override
    // if a button is clicked -> in MainActivity start the CallBack "onButtonMenuTouch"
    // with relative index of button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbut_setup:        PreCloseFrag();  mCallback.onButtonSetupTouch(0); break;
            case R.id.but_setup_soundoff:   changeSoundOn(); break;
            case R.id.but_setup_choise1:    changeMuppets(1); break;
            case R.id.but_setup_choise2:    changeMuppets(2); break;
            case R.id.but_setup_choise3:    changeMuppets(3); break;
            case R.id.but_setup_choise4:    changeMuppets(4); break;
            case R.id.but_setup_choise5:    changeMuppets(5); break;
        }
    }


    private void changeSoundOn() {
       Varbase.SoundOnOff = ! Varbase.SoundOnOff;
// salvare lo stato
        setimgbutSound();
    }

    private void setimgbutSound() {
        ImageButton imgbtn = (ImageButton) getActivity().findViewById(R.id.but_setup_soundoff);
        if  (imgbtn !=null) {
            if (Varbase.SoundOnOff) { imgbtn.setBackgroundResource(R.drawable.but_sound_state); }
                               else { imgbtn.setBackgroundResource(R.drawable.but_sound_off_state); }
        }
    }

    private void changeMuppets(int ind) {
        Varbase.LivelloH = ind;
        setTheCheck();
    }

    // set the onClick for the two buttons choise of to exit or not
    private void setTheClickListenerToButton(View view) {
        ImageButton imgbtn = null;
        imgbtn = (ImageButton) view.findViewById(R.id.backbut_setup);
        imgbtn.setOnClickListener(this);
        imgbtn = (ImageButton) view.findViewById(R.id.but_setup_soundoff);
        imgbtn.setOnClickListener(this);
        CheckBox loccheckbox;
        loccheckbox = (CheckBox) view.findViewById(R.id.but_setup_choise1);
        loccheckbox.setOnClickListener(this);
        loccheckbox = (CheckBox) view.findViewById(R.id.but_setup_choise2);
        loccheckbox.setOnClickListener(this);
        loccheckbox = (CheckBox) view.findViewById(R.id.but_setup_choise3);
        loccheckbox.setOnClickListener(this);
        loccheckbox = (CheckBox) view.findViewById(R.id.but_setup_choise4);
        loccheckbox.setOnClickListener(this);
        loccheckbox = (CheckBox) view.findViewById(R.id.but_setup_choise5);
        loccheckbox.setOnClickListener(this);
    }


    // set the correct position and dimension of the two buttons Yes-No
    public void setUpPositions() {

        RelativeLayout.LayoutParams btn_param, img_param; // used for all buttons
        ImageButton localImgButton;  // used for all Buttons

        // backButton
        int x1,y1,dimx,dimy; // local var to set the position and dim of menu buttons.
        // set the correct position and dim for each button
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_setup);
        dimx = (int) (140*Varbase.scaleX);  // correct dimension of button
        x1   = (int) (10*Varbase.scaleX);
        dimy = (int) (80*Varbase.scaleY);
        y1   = (int) (Varbase.hightScreen-(dimy+4*Varbase.scaleY));

        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

        //
        localImgButton = (ImageButton) getActivity().findViewById(R.id.but_setup_soundoff);
        dimx = (int) (320*Varbase.scaleX);  // correct dimension of button
        x1   = (int) ((Varbase.withScreen-dimx)/2);  // centered
        dimy = (int) (80*Varbase.scaleY);
        y1   = (int) ((Varbase.hightScreen*6.5) / 9);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

        CheckBox loccheckbox;
        // radio buttons
        int offsetCheckY = 75;
        int iniyYcheck   = 110;
        loccheckbox = (CheckBox) getActivity().findViewById(R.id.but_setup_choise1);
        dimx = (int) (40*Varbase.scaleX);
        x1   = (int) (140*Varbase.scaleX);
        dimy = (int) (40*Varbase.scaleY);
        y1   = (int) ((iniyYcheck+offsetCheckY*0)*Varbase.scaleY);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        loccheckbox.setLayoutParams(btn_param);

        loccheckbox = (CheckBox) getActivity().findViewById(R.id.but_setup_choise2);
        y1   = (int) ((iniyYcheck+offsetCheckY*1)*Varbase.scaleY);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        loccheckbox.setLayoutParams(btn_param);

        loccheckbox = (CheckBox) getActivity().findViewById(R.id.but_setup_choise3);
        y1   = (int) ((iniyYcheck+offsetCheckY*2)*Varbase.scaleY);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        loccheckbox.setLayoutParams(btn_param);

        loccheckbox = (CheckBox) getActivity().findViewById(R.id.but_setup_choise4);
        y1   = (int) ((iniyYcheck+offsetCheckY*3)*Varbase.scaleY);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        loccheckbox.setLayoutParams(btn_param);

        loccheckbox = (CheckBox) getActivity().findViewById(R.id.but_setup_choise5);
        y1   = (int) ((iniyYcheck+offsetCheckY*4)*Varbase.scaleY);
        btn_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        loccheckbox.setLayoutParams(btn_param);


        setTheCheck();

       setimgbutSound();
    }

    public void setTheCheck() {
        CheckBox loccheckbox1 = (CheckBox) getActivity().findViewById(R.id.but_setup_choise1);
        CheckBox loccheckbox2 = (CheckBox) getActivity().findViewById(R.id.but_setup_choise2);
        CheckBox loccheckbox3 = (CheckBox) getActivity().findViewById(R.id.but_setup_choise3);
        CheckBox loccheckbox4 = (CheckBox) getActivity().findViewById(R.id.but_setup_choise4);
        CheckBox loccheckbox5 = (CheckBox) getActivity().findViewById(R.id.but_setup_choise5);
        loccheckbox1.setChecked(false);
        loccheckbox2.setChecked(false);
        loccheckbox3.setChecked(false);
        loccheckbox4.setChecked(false);
        loccheckbox5.setChecked(false);

        switch (Varbase.LivelloH) {
            case 1 : loccheckbox1.setChecked(true); break;
            case 2 : loccheckbox2.setChecked(true); break;
            case 3 : loccheckbox3.setChecked(true); break;
            case 4 : loccheckbox4.setChecked(true); break;
            case 5 : loccheckbox5.setChecked(true); break;
        }


    }


    public void animationSetup() {
        Animation animButMenu1 = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.backbut_anim);
        ImageButton localImgButton;
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_setup);
        localImgButton.startAnimation(animButMenu1);
    }

    private void PreCloseFrag() {
        ImageButton localImgButton;
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_setup);
        localImgButton.clearAnimation();
    }




}
