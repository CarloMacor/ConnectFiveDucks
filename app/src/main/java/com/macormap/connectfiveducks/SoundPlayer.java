package com.macormap.connectfiveducks;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by carlo on 22/08/2017.
 */

public class SoundPlayer {

    public static SoundPlayer TheSoundPlayer;

    private Context mContext;

    private MediaPlayer soundPlayer;


    public SoundPlayer(Context context) {
        mContext = context;
        TheSoundPlayer = this;
    }

    public void playSuond_DropPeace() {
        if (Varbase.SoundOnOff) {
            if(soundPlayer != null) {soundPlayer.stop(); soundPlayer.release(); soundPlayer = null;}
            if(soundPlayer == null) {
                try { soundPlayer = MediaPlayer.create(mContext, R.raw.cadepedina); soundPlayer.start(); } catch (Exception e) { e.printStackTrace(); }
            } else { soundPlayer.stop(); soundPlayer.release(); soundPlayer = null; }
        }
    }


    public void suonafumettopiccolo() {
        if (Varbase.SoundOnOff) {
            if(soundPlayer != null) {soundPlayer.stop(); soundPlayer.release(); soundPlayer = null;}
            if(soundPlayer == null) {
                try { soundPlayer = MediaPlayer.create(mContext, R.raw.fumettopiccolo); soundPlayer.start(); } catch (Exception e) { e.printStackTrace(); }
            } else { soundPlayer.stop(); soundPlayer.release(); soundPlayer = null; }
        }
    }

    public void suonavincelui() {
        if (Varbase.SoundOnOff) {
            if(soundPlayer != null) {soundPlayer.stop(); soundPlayer.release(); soundPlayer = null;}
            if(soundPlayer == null) {
                try { soundPlayer = MediaPlayer.create(mContext, R.raw.vincelui); soundPlayer.start(); } catch (Exception e) { e.printStackTrace(); }
            } else { soundPlayer.stop(); soundPlayer.release(); soundPlayer = null; }
        }
    }

    public void suonaperdelui() {
        if (Varbase.SoundOnOff) {
            if(soundPlayer != null) {soundPlayer.stop(); soundPlayer.release(); soundPlayer = null;}
            if(soundPlayer == null) {
                try { soundPlayer = MediaPlayer.create(mContext, R.raw.perdelui); soundPlayer.start(); } catch (Exception e) { e.printStackTrace(); }
            } else { soundPlayer.stop(); soundPlayer.release(); soundPlayer = null; }
        }
    }



}
