package com.macormap.connectfiveducks;


import android.content.Context;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Varbase {
    // per le animazioni in azione

    // dimensions of screen and rap with original images
    public static int   withScreen,hightScreen;
    public static float scaleX,scaleY;

    public static boolean neverstartgame = true;
    public static boolean maiapertoplay = true;




    public static Varbase TheVarbase;

    private Context mContext;




    // var used , but not need to save in file
    public static int     dimmodgate, offygate;
    public static boolean chiudicancello,cancellopronto;
    public static boolean game_over;
    public static boolean primamossa;



// saved in file "nameFileInfodata"
    public static int   LivelloH;
    public static boolean   SoundOnOff;
    public static boolean   ChatOnOff;
    public static int   LinguaCod;
    public static int   PtIoLocPart;
    public static int   PtPareggi;
    public static int   PtLuiLocPart;
    public static int   PtAccumulati;
    public static int   StarAccumulate;
    public static int   fasegiocoX;
    public static int   Baloonff;
// saved in file "Histordata"  END

    public static final String fileNamelocMatch = "locmatch";
    public static final String nomefilestorico = "Histordata";
    public static final String nameFileInfodata = "infodata";



    public static boolean  toccame;
    public static boolean appenafinitoblocco;
    public static int     PtIoLastPart;
    public static int     PtLuiLastPart;
    public static int     LastPtBonusBlocco;
    public static int     LastPtBonusPartita;
    public static int     LastLivelloH;
    public static int     LastBlockWinner;

    public static int maxstoricdata = 100;
    public static int[] Storicdata1 = new int[maxstoricdata];
    public static int[] Storicdata2 = new int[maxstoricdata];
    public static int[] Storicdata3 = new int[maxstoricdata];
    public static int[] Storicdata4 = new int[maxstoricdata];



    public static int Lastwinner;
    public static int bonuslastgame;
    public static int bonuslastblock;

    public static boolean intoccoFG;
    public static int maxfrasi = 20;
    public static boolean isconnected;

    public Varbase(Context context) {
      TheVarbase = this;
      this.mContext = context;

     maxfrasi = 20;
     isconnected = false;
     intoccoFG = false;

     LivelloH   = 1;
     SoundOnOff = true;
     ChatOnOff  = true;
     LinguaCod  = 0;
     fasegiocoX =0;
     game_over   = true;
        Baloonff = 1;
     dimmodgate = 103;
     offygate = 0;
     chiudicancello = true;
     cancellopronto = true;
     primamossa     = true;




     PtIoLocPart  = 0;
     PtLuiLocPart = 0;
     PtAccumulati = 0;
        PtPareggi = 0;
        toccame = true;
        appenafinitoblocco = false;
        PtIoLastPart = 0;
        PtLuiLastPart = 0;

        LastPtBonusBlocco  = 0;
        LastPtBonusPartita = 0;
        LastLivelloH = 1;
        LastBlockWinner = 0;

        Lastwinner =0;
        bonuslastgame =0;
        bonuslastblock =0;

    }

    public static void setDimensionsScreen(Context context) {

        DisplayMetrics dimScreen ;
        dimScreen       =  context.getApplicationContext().getResources().getDisplayMetrics();
        withScreen      = (int) (dimScreen.widthPixels);
        hightScreen     = (int) (dimScreen.heightPixels);
        scaleX = (float)(withScreen/480.0f);    // all dimension are relative at a screen of 480 px With
        scaleY = (float)(hightScreen/800.0f);   // all dimension are relative at a screen of 800 px Height
    }


  public static void reinizia() {
      LivelloH   = 1;
      SoundOnOff = true;
      ChatOnOff  = true;
      LinguaCod  = 0;
      fasegiocoX  = 0;
      Baloonff = 0;
      dimmodgate = 103;
      offygate = 0;
      chiudicancello = true;
      cancellopronto = true;
      primamossa     = true;

      PtAccumulati = 0;
      PtIoLocPart  = 0;
      PtLuiLocPart = 0;
      PtPareggi = 0;
      toccame = true;
      appenafinitoblocco = false;
      PtIoLastPart = 0;
      PtLuiLastPart = 0;

      LastPtBonusBlocco  = 0;
      LastPtBonusPartita = 0;
      LastLivelloH  = 1;
      maxstoricdata = 100;

      Lastwinner =0;
      bonuslastgame =0;
      bonuslastblock =0;

  }




    public static void saveFileInfodata(Context  context) {
      String eol = System.getProperty("line.separator");
      BufferedWriter writer = null;
      try {
          writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(nameFileInfodata, Context.MODE_PRIVATE)));
          writer.write("LevelH" + eol);            writer.write(Integer.toString(LivelloH) + eol);
          writer.write("SoundOnOff" + eol);        writer.write(Boolean.toString(SoundOnOff) + eol);
          writer.write("MusicOnOff" + eol);        writer.write(Boolean.toString(ChatOnOff) + eol);
          writer.write("LanguageCod" + eol);       writer.write(Integer.toString(LinguaCod) + eol);
          writer.write("MyPtLocGame" + eol);       writer.write(Integer.toString(PtIoLocPart) + eol);
          writer.write("OppPtLocGame" + eol);      writer.write(Integer.toString(PtLuiLocPart) + eol);
          writer.write("PtDraw" + eol);            writer.write(Integer.toString(PtPareggi) + eol);
          writer.write("PtAll" + eol);             writer.write(Integer.toString(PtAccumulati) + eol);
          writer.write("StarAll" + eol);           writer.write(Integer.toString(StarAccumulate) + eol);
          writer.write("baloonOnOff" + eol);       writer.write(Integer.toString(Baloonff) + eol);
      } catch (Exception e) { e.printStackTrace(); }
      finally { if (writer != null) { try { writer.close();
      } catch (IOException e) { e.printStackTrace(); } } }
    }

    private void openFileSetup(){
//        String TAG = "leggi file: ";
      String eol = System.getProperty("line.separator");
      BufferedReader input = null;
      try {
          input = new BufferedReader(new InputStreamReader(mContext.openFileInput(nameFileInfodata)));
          String linecod , line;
          if (input != null) {
              if ((linecod = input.readLine()) != null) { line = input.readLine(); LivelloH         = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); SoundOnOff       = Boolean.parseBoolean(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); ChatOnOff      = Boolean.parseBoolean(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); LinguaCod        = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); PtIoLocPart      = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); PtLuiLocPart     = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); PtPareggi        = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); PtAccumulati     = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); StarAccumulate   = Integer.parseInt(line);   }
              if ((linecod = input.readLine()) != null) { line = input.readLine(); Baloonff     = Integer.parseInt(line);   }
          }
      } catch (Exception e) { e.printStackTrace();}
      finally { if (input != null) { try { input.close(); } catch (IOException e) { e.printStackTrace(); } } }
  }

/*
    public void readPartitaAttuale() {
        // myC4view
//        String TAG = "leggi file: ";
        String eol = System.getProperty("line.separator");
        BufferedReader input = null;
        try {
            String  line;
            input = new BufferedReader(new InputStreamReader(mContext.openFileInput(fileNamelocMatch)));
            for (int i = 0; i < 6; i++) { for (int j =0; j< 7; j++) {
                if ((line = input.readLine()) != null)   connect_view.Theconnect_view.S4.board[j][i]  = Integer.parseInt(line);
            }};
            if ((line = input.readLine()) != null) {if (Integer.parseInt(line)==0) {toccame=true;} else{toccame=false;}  }

            for (int i = 0; i < 69; i++) { for (int j =0; j< 2; j++) {
                if ((line = input.readLine()) != null)   connect_view.Theconnect_view.S4.score[j][i]  = Integer.parseInt(line);
            }};

            if ((line = input.readLine()) != null) {connect_view.Theconnect_view.S4.numPieces = Integer.parseInt(line); }
            if ((line = input.readLine()) != null) {connect_view.Theconnect_view.S4.winneris = Integer.parseInt(line); }

            for (int i = 0; i < 6; i++) {
                for (int j =0; j< 7; j++) {
                    for (int m =0; m< 69; m++) {
                        if ((line = input.readLine()) != null) {connect_view.Theconnect_view.S4.map[j][i][m] = Boolean.parseBoolean(line); }

                        //                       writer.write(Boolean.toString(myC4view.S4.map[j][i][m]) + eol);
                    }
                }
            };

            if (connect_view.Theconnect_view.S4.winneris!=2) {connect_view.Theconnect_view.resettapedine();}

        } catch (Exception e) { e.printStackTrace();}
        finally { if (input != null) { try { input.close(); } catch (IOException e) { e.printStackTrace(); } } }
    }

*/

/*
    public void writePartitaAttuale() {
        String eol = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(mContext.openFileOutput(fileNamelocMatch, Context.MODE_PRIVATE)));
            for (int i = 0; i < 6; i++) { for (int j =0; j< 7; j++) { writer.write(Integer.toString(connect_view.Theconnect_view.S4.board[j][i]) + eol);  }};
            if (toccame) { writer.write(Integer.toString(0) + eol);} else {  writer.write(Integer.toString(1) + eol);}


            for (int i = 0; i < 69; i++) { for (int j =0; j<2; j++) { writer.write(Integer.toString(connect_view.Theconnect_view.S4.score[j][i]) + eol);  }};

            writer.write(Integer.toString(connect_view.Theconnect_view.S4.numPieces) + eol);
            writer.write(Integer.toString(connect_view.Theconnect_view.S4.winneris) + eol);

            for (int i = 0; i < 6; i++) {
                for (int j =0; j< 7; j++) {
                    for (int m =0; m< 69; m++) {
                        writer.write(Boolean.toString(connect_view.Theconnect_view.S4.map[j][i][m]) + eol);
                    }
                }
            };
        } catch (Exception e) { e.printStackTrace(); }
        finally { if (writer != null) { try { writer.close();
            //          String TAG = "salva Partita: ";  Log.v(TAG, " qui = " );
        } catch (IOException e) { e.printStackTrace(); } } }
    }
*/

    public static void aggiornastorico() {
        for ( int i = 98; i>=0; i--) {
            Storicdata1[i+1] = Storicdata1[i];
            Storicdata2[i+1] = Storicdata2[i];
            Storicdata3[i+1] = Storicdata3[i];
            Storicdata4[i+1] = Storicdata4[i];
        }
        Storicdata1[0] = LastLivelloH;
        Storicdata2[0] = PtIoLastPart;
        Storicdata3[0] = PtLuiLastPart;
        Storicdata4[0] = PtAccumulati;
    }

    public static void salvaLostorico(Context  context) {
        String eol = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(nomefilestorico,  Context.MODE_PRIVATE))); // 1 perche appende
            writer.write(Integer.toString(maxstoricdata) + eol);
            for ( int i = 0; i<100; i++) {
                writer.write(Integer.toString(Storicdata1[i]) + eol);
                writer.write(Integer.toString(Storicdata2[i]) + eol);
                writer.write(Integer.toString(Storicdata3[i]) + eol);
                writer.write(Integer.toString(Storicdata4[i]) + eol);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally { if (writer != null) { try { writer.close();
//            String TAG = "salva Storico: ";  Log.v(TAG, " qui = " );
        } catch (IOException e) { e.printStackTrace(); } } }
    }

    private void buttalostorico() {
        String eol = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(mContext.openFileOutput(nomefilestorico,  Context.MODE_PRIVATE))); // 1 perche appende
        } catch (Exception e) { e.printStackTrace(); }
        finally { if (writer != null) { try { writer.close();
        } catch (IOException e) { e.printStackTrace(); } } }
    }

    public void leggiLostorico() {
//        String TAG = "Apri Storico: ";
        BufferedReader input = null;
        try {
            String  line;
            input = new BufferedReader(new InputStreamReader(mContext.openFileInput(nomefilestorico)));

            if ((line = input.readLine()) != null) {maxstoricdata = Integer.parseInt(line); }
            for ( int i = 0; i<100; i++) {
                if ((line = input.readLine()) != null) {Storicdata1[i] = Integer.parseInt(line); }
                if ((line = input.readLine()) != null) {Storicdata2[i] = Integer.parseInt(line); }
                if ((line = input.readLine()) != null) {Storicdata3[i] = Integer.parseInt(line); }
                if ((line = input.readLine()) != null) {Storicdata4[i] = Integer.parseInt(line); }

//                if (VarB.Storicdata1[i]>0)   Log.v(TAG, " = "+ i+ "  "+VarB.Storicdata1[i]+ "  "+VarB.Storicdata2[i]+ "  "+VarB.Storicdata3[i]+ "  "+VarB.Storicdata4[i] );
            }
        } catch (Exception e) { e.printStackTrace();}
        finally { if (input != null) { try { input.close(); } catch (IOException e) { e.printStackTrace(); } } }
    }



}
