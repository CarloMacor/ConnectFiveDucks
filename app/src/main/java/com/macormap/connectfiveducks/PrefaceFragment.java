package com.macormap.connectfiveducks;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by carlo on 04/09/2017.
 */

public class PrefaceFragment extends Fragment {

    PrefaceFragment.OnEndPrefaceListener mCallback;

    TextView row1,row2,row3;
    FrameLayout baloon;
    private FrameLayout   CurrentFrameDuckGate;
    private int stepBaloon;

    private Handler mHandlerPreface = new Handler();

    public interface OnEndPrefaceListener {
        /** Called by MenuFragment when button click */
        public void onEndPreface();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.preface_layout, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (PrefaceFragment.OnEndPrefaceListener) context; }
        catch (ClassCastException e)
        { throw new ClassCastException(context.toString() + " must implement OnButtonRulesListener"); }
    }


    // set the correct position and dimension of the two buttons Yes-No
    public void setUpPositions() {
        row1   = (TextView) getActivity().findViewById(R.id.FgrandeR1);
        row2   = (TextView) getActivity().findViewById(R.id.FgrandeR2);
        row3   = (TextView) getActivity().findViewById(R.id.FgrandeR3);
        baloon = (FrameLayout) getActivity().findViewById(R.id.baseFumettoGrande);

        FrameLayout.LayoutParams locParLay = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        locParLay.leftMargin = (int)((Varbase.withScreen-400*Varbase.scaleX)/2);
        locParLay.topMargin  = (int)(20*Varbase.scaleY);
        locParLay.width      = (int)(400*Varbase.scaleX);
        locParLay.height     = (int)(360*Varbase.scaleY);
        baloon.setLayoutParams(locParLay);

        row1.setY(60*Varbase.scaleY);
        row2.setY(110*Varbase.scaleY);
        row3.setY(160*Varbase.scaleY);

        // a seconda del livello cambiare qui le immagini
        ImageView myimager;
        CurrentFrameDuckGate = (FrameLayout) getActivity().findViewById(R.id.frameDuck);     CurrentFrameDuckGate.setVisibility(View.VISIBLE);
        myimager = (ImageView) getActivity().findViewById(R.id.imageG1);          myimager.setVisibility(View.GONE);
        myimager = (ImageView) getActivity().findViewById(R.id.imageG2);          myimager.setVisibility(View.GONE);
        myimager = (ImageView) getActivity().findViewById(R.id.imageG3);          myimager.setVisibility(View.GONE);
        myimager = (ImageView) getActivity().findViewById(R.id.imageG4);          myimager.setVisibility(View.GONE);
        myimager = (ImageView) getActivity().findViewById(R.id.imageG5);          myimager.setVisibility(View.GONE);

       switch (Varbase.LivelloH) {
           case 1 : myimager = (ImageView) getActivity().findViewById(R.id.imageG1); myimager.setVisibility(View.VISIBLE);   break;
           case 2 : myimager = (ImageView) getActivity().findViewById(R.id.imageG2); myimager.setVisibility(View.VISIBLE);   break;
           case 3 : myimager = (ImageView) getActivity().findViewById(R.id.imageG3); myimager.setVisibility(View.VISIBLE);   break;
           case 4 : myimager = (ImageView) getActivity().findViewById(R.id.imageG4); myimager.setVisibility(View.VISIBLE);   break;
           case 5 : myimager = (ImageView) getActivity().findViewById(R.id.imageG5); myimager.setVisibility(View.VISIBLE);   break;

       }

        // set dimension Duck
        int x1,y1,dimx,dimy; // local var to set the position and dim of menu buttons.
        dimx = (int) (370*Varbase.scaleX);  // correct dimension of button
        x1   = (int) (Varbase.withScreen-dimx);
        dimy = (int) (470*Varbase.scaleY);
        y1   = (int) (Varbase.hightScreen-(dimy-10*Varbase.scaleY) );

        CurrentFrameDuckGate = (FrameLayout) getActivity().findViewById(R.id.frameDuck);     CurrentFrameDuckGate.setVisibility(View.VISIBLE);

        FrameLayout.LayoutParams btn_param = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        CurrentFrameDuckGate.setLayoutParams(btn_param);
    }

    // start animation of button and title of Menu
    public void animationPreface() {
        stepBaloon = 0; mHandlerPreface.post(runnableFumettoGrande);
    }

    Runnable runnableFumettoGrande = new Runnable() {
        @Override
        public void run() {
            stepBaloon++;
            if (stepBaloon<4) {
                setBigBaloon(stepBaloon);
                mHandlerPreface.postDelayed(this, 1000);
            }
            else  { closePreface(); }
        }
    };


    private void closePreface() {
        mHandlerPreface.removeCallbacks(runnableFumettoGrande);


        Animation animationPG = new AlphaAnimation(1.0f, 0.0f);  animationPG.setDuration(100);  animationPG.setFillAfter(true);
        baloon.startAnimation(animationPG);
        CurrentFrameDuckGate.startAnimation(animationPG);
        mHandlerPreface.postDelayed(runnable_closePreface, 200);
    }

    Runnable runnable_closePreface = new Runnable() {
        @Override
        public void run() {
            PreCloseFrag();
            mCallback.onEndPreface();
        }
    };

    private void setBigBaloon(int ff){
        String st1; String st2; String st3;
        if (Varbase.neverstartgame)  {
            st1 =  Phrases.getTextRow1(Varbase.LivelloH, ff);   st2 =  Phrases.getTextRow2(Varbase.LivelloH, ff);     st3 =  Phrases.getTextRow3(Varbase.LivelloH, ff);
        }
        else {
            if (Varbase.appenafinitoblocco)
            { st1 =  Phrases.getTextRow1(Varbase.LivelloH, ff+6); st2 =  Phrases.getTextRow2(Varbase.LivelloH, ff+6);   st3 =  Phrases.getTextRow3(Varbase.LivelloH, ff+6);}
            else
            { st1 =  Phrases.getTextRow1(Varbase.LivelloH, ff+3); st2 =  Phrases.getTextRow2(Varbase.LivelloH, ff+3);   st3 =  Phrases.getTextRow3(Varbase.LivelloH, ff+3);}
        }
        row1.setText(st1);    row1.invalidate();
        row2.setText(st2);    row2.invalidate();
        row3.setText(st3);    row3.invalidate();
    }

    private void PreCloseFrag() {
        baloon.clearAnimation();
        CurrentFrameDuckGate.clearAnimation();
        mHandlerPreface.removeCallbacksAndMessages(null);
        mHandlerPreface = null;
    }



}
