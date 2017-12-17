package com.macormap.connectfiveducks;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
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

import java.util.Random;



/**
 * Created by carlo on 04/09/2017.
 */

public class GameFragment extends Fragment implements View.OnClickListener {

    GameFragment.OnGameListener mCallback;

  //  private Handler mHandlerGame = new Handler();

    private ConnectView   connectView;

    private FrameLayout   CurrentFrameDuckGate;

    private ImageView     CurrentFrameDuckNormal;
    private ImageView     CurrentFrameEyesDuck;
    private ImageView     CurrentFrameDuckLittleWinning;
    private ImageView     CurrentFrameDuckLittleLoosing;


    private FrameLayout   CurrentFrameBallonLittle;
    private TextView      R1p,R2p,R3p;
    private int numpF =0;
    private int numpO =0;
    private Random  m_r1 = new Random();

    private  Animation    animFadeInBaloon;
    private  Animation    animFadeOutBaloon;

    public interface OnGameListener {
        /** Called by MenuFragment when button click */
        public void onEndGame(int indWinner);
        public void onBackFromGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.game_layout, container, false);
        setTheClickListenerToButton(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        try { mCallback = (GameFragment.OnGameListener) context; }
        catch (ClassCastException e)
        { throw new ClassCastException(context.toString() + " must implement OnGameListener"); }
    }

    public void  startConnect5() {
        if (connectView == null) { connectView  = (ConnectView) getActivity().findViewById(R.id.connect5_view); }
        connectView.startC4();
        connectView.setParentGameFragment(this);
    }

    // set the correct position and dimension of the two buttons Yes-No
    public void setUpPositions() {
        connectView = (ConnectView) getActivity().findViewById(R.id.connect5_view);
        connectView.inizializza();

        int x1, y1, dimx, dimy; // local var to set the position and dim of menu buttons.

        int HFpupo = (int) (Varbase.hightScreen / 2.5f);

        int WFpupo = 200;
        switch (Varbase.LivelloH) {
            case 1 : WFpupo =(int) ((HFpupo * 228) / 296); break;
            case 2 : WFpupo =(int) ((HFpupo * 208) / 346); break;
            case 3 : WFpupo =(int) ((HFpupo * 220) / 296); break;
            case 4 : WFpupo =(int) ((HFpupo * 208) / 296); break;
            case 5 : WFpupo =(int) ((HFpupo * 208) / 296); break;
        }

        CurrentFrameDuckGate = (FrameLayout) getActivity().findViewById(R.id.frameLittleDuck);
        CurrentFrameDuckGate.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams
                btn_param = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        dimx = (int) (WFpupo);  // correct dimension of button
        x1 = (int) (Varbase.withScreen - dimx);
        dimy = (int) (HFpupo);
        y1 = (int) (Varbase.hightScreen - (dimy - 10 * Varbase.scaleY));
        btn_param.leftMargin = x1;
        btn_param.topMargin = y1;
        btn_param.width = dimx;
        btn_param.height = dimy;

        CurrentFrameDuckGate.setLayoutParams(btn_param);

        CurrentFrameDuckNormal = (ImageView) getActivity().findViewById(R.id.imageG1);
        CurrentFrameDuckNormal.setVisibility(View.VISIBLE);
        CurrentFrameDuckNormal.getLayoutParams().height  = HFpupo;
        CurrentFrameDuckNormal.getLayoutParams().width   = WFpupo;

        CurrentFrameEyesDuck = (ImageView) getActivity().findViewById(R.id.imageG2);
        CurrentFrameEyesDuck.setVisibility(View.INVISIBLE);
        CurrentFrameEyesDuck.getLayoutParams().height  = HFpupo;
        CurrentFrameEyesDuck.getLayoutParams().width   = WFpupo;

        CurrentFrameDuckLittleWinning = (ImageView) getActivity().findViewById(R.id.imageG3);
        CurrentFrameDuckLittleWinning.setVisibility(View.INVISIBLE);
        CurrentFrameDuckLittleWinning.getLayoutParams().height  = HFpupo;
        CurrentFrameDuckLittleWinning.getLayoutParams().width = WFpupo;

        CurrentFrameDuckLittleLoosing = (ImageView) getActivity().findViewById(R.id.imageG4);
        CurrentFrameDuckLittleLoosing.setVisibility(View.INVISIBLE);
        CurrentFrameDuckLittleLoosing.getLayoutParams().height  = HFpupo;
        CurrentFrameDuckLittleLoosing.getLayoutParams().width = WFpupo;

        // le scrittine gialle in alto
        ImageView starrina = (ImageView) getActivity().findViewById(R.id.imagestar);
        starrina.getLayoutParams().height  = (int) (30*Varbase.scaleY);
        starrina.getLayoutParams().width = (int) (30*Varbase.scaleX);
        starrina.setVisibility(View.INVISIBLE);
        TextView tx1 = (TextView) getActivity().findViewById(R.id.textptpartita);
        tx1.setText(Integer.toString(Varbase.PtIoLocPart)+" - "+Integer.toString(Varbase.PtLuiLocPart));
        TextView tx2 = (TextView) getActivity().findViewById(R.id.textptplayer);
        tx2.setText(Integer.toString(Varbase.PtAccumulati));
        tx2.setX(Varbase.withScreen-tx2.getWidth()-(60*Varbase.scaleX));
        tx2.setVisibility(View.INVISIBLE);
        impostafumettopiccolo();

        if (Varbase.Baloonff==1)  mettitestoFumettoPiccolo();
        else { CurrentFrameBallonLittle = (FrameLayout) getActivity().findViewById(R.id.baseFumettoPiccolo); CurrentFrameBallonLittle.setVisibility(View.INVISIBLE); }


// backbutton
        // set the correct position and dim for each button
        ImageButton localImgButton;
        localImgButton = (ImageButton) getActivity().findViewById(R.id.backbut_game);
        dimx = (int) (140*Varbase.scaleX);  // correct dimension of button
        x1   = (int) (10*Varbase.scaleX);
        dimy = (int) (80*Varbase.scaleY);
        y1   = (int) (Varbase.hightScreen-(dimy+4*Varbase.scaleY));

        btn_param = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        btn_param.leftMargin = x1;
        btn_param.topMargin  = y1;
        btn_param.width      = dimx;
        btn_param.height     = dimy;
        localImgButton.setLayoutParams(btn_param);

    }

    public void setUpImagesDucks() {
        switch (Varbase.LivelloH) {
            case 1 :
                CurrentFrameDuckNormal.setImageResource(R.drawable.gobbo1);
                CurrentFrameEyesDuck.setImageResource(R.drawable.gobbo2);
                CurrentFrameDuckLittleLoosing.setImageResource(R.drawable.gobbo4);
                CurrentFrameDuckLittleWinning.setImageResource(R.drawable.gobbo3);
                break;
            case 2 :
                CurrentFrameDuckNormal.setImageResource(R.drawable.guardia1);
                CurrentFrameEyesDuck.setImageResource(R.drawable.guardia2);
                CurrentFrameDuckLittleLoosing.setImageResource(R.drawable.guardia4);
                CurrentFrameDuckLittleWinning.setImageResource(R.drawable.guardia3);
                break;
            case 3 :
                CurrentFrameDuckNormal.setImageResource(R.drawable.capitana1);
                CurrentFrameEyesDuck.setImageResource(R.drawable.capitana2);
                CurrentFrameDuckLittleLoosing.setImageResource(R.drawable.capitana4);
                CurrentFrameDuckLittleWinning.setImageResource(R.drawable.capitana3);
                break;
            case 4 :
                CurrentFrameDuckNormal.setImageResource(R.drawable.cattivo1);
                CurrentFrameEyesDuck.setImageResource(R.drawable.cattivo2);
                CurrentFrameDuckLittleLoosing.setImageResource(R.drawable.cattivo4);
                CurrentFrameDuckLittleWinning.setImageResource(R.drawable.cattivo3);
                break;
            case 5 :
                CurrentFrameDuckNormal.setImageResource(R.drawable.stregone1);
                CurrentFrameEyesDuck.setImageResource(R.drawable.stregone2);
                CurrentFrameDuckLittleLoosing.setImageResource(R.drawable.stregone4);
                CurrentFrameDuckLittleWinning.setImageResource(R.drawable.stregone3);
                break;
        }
    }

    private void mettitestoFumettoPiccolo() {
        String locr1p="",locr2p="",locr3p=""; int locpupo; int rr;
        if (Varbase.toccame) locpupo=1; else locpupo=0;
        if (Varbase.primamossa) {
            rr = m_r1.nextInt(5)+1;
            locr1p = LittlePhrases.fraseprimamossa(locpupo,rr,1);
            locr2p = LittlePhrases.fraseprimamossa(locpupo,rr,2);
            locr3p = LittlePhrases.fraseprimamossa(locpupo,rr,3);
        } else
        {
            rr = m_r1.nextInt(Varbase.maxfrasi)+1;

            if (Varbase.game_over) { locr1p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 1);
                                     locr2p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 2);
                                     locr3p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 3);}
            else {
                locr1p = LittlePhrases.frasequalsiasimossa(locpupo, rr, 1);
                locr2p = LittlePhrases.frasequalsiasimossa(locpupo, rr, 2);
                locr3p = LittlePhrases.frasequalsiasimossa(locpupo, rr, 3);}
        }

        R1p.setText(locr1p); R1p.invalidate();  R2p.setText(locr2p); R2p.invalidate();  R3p.setText(locr3p); R3p.invalidate();
    }

    private void impostafumettopiccolo() {
        CurrentFrameBallonLittle = (FrameLayout) getActivity().findViewById(R.id.baseFumettoPiccolo);
        R1p = (TextView) getActivity().findViewById(R.id.FpiccoloR1);
        R2p = (TextView) getActivity().findViewById(R.id.FpiccoloR2);
        R3p = (TextView) getActivity().findViewById(R.id.FpiccoloR3);

        R1p.setText(""); R1p.invalidate();  R2p.setText(""); R2p.invalidate();  R3p.setText(""); R3p.invalidate();


        int wG = (int) (Varbase.withScreen /1.8);
        int hG = (int) (wG/2);
        float lG =   ((Varbase.withScreen-wG)/8);
        float tG =   (Varbase.hightScreen-hG*2);
        int HF = (int) (hG);
        int LF = (int) (wG);

        //       Log.v(TAG, "w = " +wG);   Log.v(TAG, "h = " +hG);    Log.v(TAG, "l = " + lG);
        CurrentFrameBallonLittle.getLayoutParams().height  = HF;        CurrentFrameBallonLittle.getLayoutParams().width   = LF;
        CurrentFrameBallonLittle.setX(lG);                              CurrentFrameBallonLittle.setY(tG);
        int treottavi = (int)((HF*3)/8);
        R1p.setY((int)(treottavi-HF/5));        R2p.setY(treottavi);        R3p.setY((int)(treottavi+HF/5));
        R1p.setX((int) (-LF / 10));             R2p.setX((int) (-LF / 10)); R3p.setX((int) (-LF / 10));



    }

    // start animation of button and title of Menu
    public void animationGame() {
        animFadeInBaloon  = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.baloon_fadein);
        animFadeOutBaloon = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.baloon_fadeout);
        inizia_fumettoPiccolo();
        numpO =0;
        MainActivity.mHandler.post(runnableOcchiPupo);
//        mHandlerGame.post(runnableOcchiPupo);
    }

    private void StopAnimations() {
         MainActivity.mHandler.removeCallbacks(runnableOcchiPupo);
         MainActivity.mHandler.removeCallbacks(runnableFumettoPiccolo);
//        mHandlerGame.removeCallbacksAndMessages(null);
//        mHandlerGame = null;
    }

    private void inizia_fumettoPiccolo() {
        numpF =0;
        if (Varbase.ChatOnOff) {
            MainActivity.mHandler.postDelayed(runnableFumettoPiccolo, 1000);
//            mHandlerGame.postDelayed(runnableFumettoPiccolo,1000);
        }
        else
        {  CurrentFrameBallonLittle.setVisibility(View.INVISIBLE); }
    }

    Runnable runnableOcchiPupo = new Runnable() {
        @Override
        public void run() {
            if (numpO<3) {
                numpO++;
                MainActivity.mHandler.postDelayed(this, 1000);
//                mHandlerGame.postDelayed(this, 1000);
            } else
            {
                if (!Varbase.game_over) {
                    if (CurrentFrameEyesDuck != null) {  CurrentFrameEyesDuck.setVisibility(View.VISIBLE);
                                                         CurrentFrameEyesDuck.startAnimation(animFadeOutBaloon); } }
                if (!Varbase.game_over)  {
                    int rr = m_r1.nextInt() % 5;
                    numpO = - rr;
                    MainActivity.mHandler.postDelayed(this, 1000);
//                    mHandlerGame.postDelayed(this, 1000);
                }
            }
        }
    };

    Runnable runnableFumettoPiccolo = new Runnable() {
        @Override
        public void run() {
            if (Varbase.primamossa) {
                CurrentFrameBallonLittle.startAnimation(animFadeInBaloon);
                mettitestoFumettoPiccolo();
                Varbase.primamossa = false;
                MainActivity.mHandler.postDelayed(this, 1000);
//                mHandlerGame.postDelayed(this, 1000);
                numpF = 0;
            }  else
            {
                if (numpF<10) {
                    numpF++;
                    if (numpF==7) { CurrentFrameBallonLittle.startAnimation(animFadeOutBaloon); }
                    MainActivity.mHandler.postDelayed(this, 1000);
//                    mHandlerGame.postDelayed(this, 1000);
                }  else
                {
                        if (!Varbase.game_over) {
                            if (Varbase.ChatOnOff) {
                                numpF = - m_r1.nextInt(5) +1;
                                CurrentFrameBallonLittle.startAnimation(animFadeInBaloon);
                                mettitestoFumettoPiccolo();
                                MainActivity.mHandler.postDelayed(this, 1000);
//                                mHandlerGame.postDelayed(this, 1000);
                            }
                        } else
                        {
                            CurrentFrameBallonLittle.startAnimation(animFadeInBaloon);
                            mettitestoFumettoPiccolo();
                        }
                }
            }
        }
    };


    public void impostapupovincente(int vincitore) {

        CurrentFrameDuckNormal.setVisibility(View.INVISIBLE);
        switch (vincitore) {
            case 0  :
                CurrentFrameDuckLittleLoosing.setVisibility(View.INVISIBLE);
                CurrentFrameDuckLittleWinning.setVisibility(View.VISIBLE);
                break;
            case 1  :
                CurrentFrameDuckLittleLoosing.setVisibility(View.VISIBLE);
                CurrentFrameDuckLittleWinning.setVisibility(View.INVISIBLE);
                break;
            default:
                CurrentFrameDuckNormal.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void mettifumettovincente() {
        Random m_r1 = new Random();
//        mHandler.removeCallbacks(runnableFumettoPiccolo);
        CurrentFrameBallonLittle.setVisibility(View.VISIBLE);
        CurrentFrameBallonLittle.setAlpha(1.0f);
        CurrentFrameBallonLittle.invalidate();
        String locr1p="",locr2p="",locr3p=""; int locpupo; int rr;
        rr = m_r1.nextInt(7)+1;
        locr1p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 1);
        locr2p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 2);
        locr3p = LittlePhrases.frasegameover(connectView.gameStatus.winneris, rr, 3);

        R1p.setText(locr1p); R1p.invalidate();  R2p.setText(locr2p); R2p.invalidate();  R3p.setText(locr3p); R3p.invalidate();
  //      numpF = 100;
    }

    // set the onClick for the two buttons choise of to exit or not
    private void setTheClickListenerToButton(View view) {
        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.backbut_game);
        imgbtn.setOnClickListener(this);
    }

    @Override
    // if a button is clicked -> in MainActivity start the CallBack "onButtonMenuTouch"
    // with relative index of button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbut_game:
                StopAnimations();
                connectView.stopparun();
                mCallback.onBackFromGame(); break;
        }
    }


}
