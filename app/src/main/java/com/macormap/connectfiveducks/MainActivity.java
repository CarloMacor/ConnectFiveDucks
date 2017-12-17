package com.macormap.connectfiveducks;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements MenuFragment.OnButtonMenuListener,
                   ExitFragment.OnButtonExitListener,
                   RulesFragment.OnButtonRulesListener,
                   SetupFragment.OnButtonSetupListener,
                   PointsFragment.OnButtonPointsListener,
                   PrefaceFragment.OnEndPrefaceListener,
                   GameFragment.OnGameListener
{
    public static final Handler mHandler = new Handler();

    private SoundPlayer  soundPlayer = new SoundPlayer(this);


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            startMenuPanel();
        } else { return super.onKeyDown(keyCode, event);}
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        Varbase.reinizia();
        Varbase.setDimensionsScreen(this);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        fromSplashToMenu();
     }

    private void fromSplashToMenu() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startFirstTimeMenu();
            }
        };
        mHandler.postDelayed(runnable,1000);

    }

    private void startFirstTimeMenu() {
        mHandler.removeCallbacksAndMessages(null);
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, menuFragment).commit();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
        getSupportFragmentManager().executePendingTransactions();
        menuFragment.setUpPositions();
        menuFragment.animationMenu();
    }

    private void startMenuPanel() {
        Varbase.fasegiocoX = 0;
        MenuFragment menuFragment = new MenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, menuFragment);
        transaction.commit();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
        getSupportFragmentManager().executePendingTransactions();
        menuFragment.setUpPositions();
        menuFragment.animationMenu();
    }

    private void startGame() {
        Varbase.fasegiocoX = 7;
        GameFragment gameFragment = new GameFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, gameFragment);
        transaction.commitNow();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
       // getSupportFragmentManager().executePendingTransactions();


        gameFragment.setUpPositions();
        gameFragment.setUpImagesDucks();
        gameFragment.startConnect5();
        gameFragment.animationGame();

    }

    private void startPrefacePanel() {
        PrefaceFragment prefaceFragment = new PrefaceFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, prefaceFragment);
        transaction.commit();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
        getSupportFragmentManager().executePendingTransactions();
        prefaceFragment.setUpPositions();
        prefaceFragment.animationPreface();
    }

    private void startPointsPanel() {
        PointsFragment pointsFragment = new PointsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, pointsFragment);
        transaction.commit();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
        getSupportFragmentManager().executePendingTransactions();
        pointsFragment.setUpPositions();
        pointsFragment.animationPoints();
    }

    private void startSetupPanel() {
        Varbase.fasegiocoX = 1;
        SetupFragment setupFragment = new SetupFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, setupFragment);
//        transaction.commit();
        transaction.commitNow();

        // next line needed to be sure that the commit is completed before setup the correct position of buttons
  //      getSupportFragmentManager().executePendingTransactions();
        setupFragment.setUpPositions();
        setupFragment.animationSetup();
    }

    private void startRulesPanel() {
        Varbase.fasegiocoX = 2;
        RulesFragment rulesFragment = new RulesFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, rulesFragment);
        transaction.commitNow();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
//        getSupportFragmentManager().executePendingTransactions();
        rulesFragment.setUpPositions();
        rulesFragment.animationRules();
    }

    private void startExitPanel() {
        ExitFragment exitFragment = new ExitFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, exitFragment);
        transaction.commit();
        // next line needed to be sure that the commit is completed before setup the correct position of buttons
        getSupportFragmentManager().executePendingTransactions();
        exitFragment.setUpPositions();
        exitFragment.animationExit();
    }

    // implementation of callBack from MenuFragment
    public void onButtonMenuTouch(int butIndex) {
        switch (butIndex) {
//            case 1 : startPrefacePanel(); break;
            case 1 : startGame(); break;
            case 2 : startSetupPanel(); break;
            case 3 : startRulesPanel(); break;
            case 4 : startExitPanel(); break;
        }
    }

    public void onButtonExitTouch(int butIndex) {
        switch (butIndex) {
            case 10 :  exitGameFinally(); break;
            case 11 :
                switch (Varbase.fasegiocoX) {
                    case 7 : startMenuPanel(); break;
                    case 1 : startSetupPanel(); break;
                    case 2 : startRulesPanel(); break;
                    default: startMenuPanel(); break;
                }
            break;
        }
    }

    // implementation of callBack from MenuFragment
    public void onButtonRulesTouch(int butIndex) {
        switch (butIndex) {
            case 0 :  startMenuPanel(); break;
        }
    }

    // implementation of callBack from MenuFragment
    public void onButtonSetupTouch(int butIndex) {
        switch (butIndex) {
            case 0 :  startMenuPanel(); break;
        }
    }

    // implementation of callBack from MenuFragment
    public void onButtonPointsTouch(int butIndex) {
        switch (butIndex) {
            case 0 :  startMenuPanel(); break;
        }
    }

    public void onEndPreface() {
    //    startMenuPanel();
        startGame();
    }

    public void onEndGame(int indWinner) {
        startMenuPanel();
    }

    public void onBackFromGame() {
        startMenuPanel();
    }

    public void exitGameFinally() {
        // chiudi tutte le animazioni ecc....
//        writeFileToInternalStorage(VarB.nomefilesetup);
        this.finish();
    }

}
