package com.macormap.connectfiveducks;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class ConnectView extends View implements View.OnTouchListener {

    public static ConnectView Theconnect_view;

    private GameFragment gameFragment;


    private Bitmap mBmpModulo;
    private Bitmap mBmpPunta;
    private Bitmap mBmpPedinaRed;
    private Bitmap mBmpPedinaBlu;
    private Bitmap mBmpL1;
    private Bitmap mBmpL2;
    private Bitmap mBmpS1;
    private Bitmap mBmpS2;
    private final Rect mSrcRectmod   = new Rect();
    private final Rect mDstRectmod   = new Rect();
    private final Rect mSrcRectPunta = new Rect();
    private final Rect mDstRectPunta = new Rect();
    private final Rect mSrcRectL     = new Rect();
    private final Rect mDstRectL     = new Rect();
    private final Rect mSrcRectS     = new Rect();
    private final Rect mDstRectS     = new Rect();
    private final Rect mSrcRectPed   = new Rect();
    private final Rect mDstRectPed   = new Rect();
    private Paint mBmpPaint;
    private int startygriglia;
    private int w,h;
    private int wmod,hmod,wpunt,hpunt ,wl1,ws1,hs1, wped, hped;
    private int leftmod;
    public  GameStatus gameStatus;
    private int[][]   PosPedine;

    private final int        VUOTO=2;
    private final int        BLU=1;
    private final int        ROSSO=0;

    public boolean cancellopronto;
    public boolean chiudicancello;
    public boolean messaggioincorso=false;
    public long   TempoAdesso;
    public long   TempoFattoMossa;
    public int    indicemessaggio;


    public  int     pospedinadamettere=0;
    private boolean Thinking = false;
    private Random  m_r;
    private float   downx, downy;
    private int     dimmodgate;
    private int     offygate;

    // Fumetto piccolo
    private FrameLayout FrameG;
    private TextView R1,R2,R3;
    private ImageView FG;

    private int[] PosXwinPeace = new int[5];
    private int[] PosYwinPeace = new int[5];

    private int nump,maxnumpP;

    int codeScesaplayer, codeYscesa;

    private boolean infaseclick;

    long startTime = 0;



    Handler timerHandler = new Handler();
    Handler timerHandlerPedina = new Handler();



    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
         TempoAdesso=System.currentTimeMillis();
            if (!Varbase.toccame & cancellopronto ) { repensamossa(); };
            if (!Varbase.game_over & (Varbase.fasegiocoX==7)) {  timerHandler.postDelayed(this, 1000); }
        }
    };

    Runnable runnablepedina = new Runnable() {
        @Override
        public void run() {
            nump++;
            if (nump<=maxnumpP) {
                invalidate();
                timerHandlerPedina.postDelayed(this, 50);
            } else {
                PosPedine[codeYscesa][pospedinadamettere]=codeScesaplayer+1;
                gameStatus.dropPiece(codeScesaplayer, pospedinadamettere);
                cancellopronto = true;
                invalidate();
                controllaFinemossa();
            }
        }
    };

    public void stopparun() {
        timerHandler.removeCallbacks(timerRunnable);
        timerHandlerPedina.removeCallbacks(runnablepedina);
    }


    public void setParentGameFragment(GameFragment paramGameFragment){
        gameFragment = paramGameFragment;
    }

    public ConnectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Theconnect_view = this;

        mBmpModulo = getResBitmap(R.drawable.modul );
        mBmpPunta  = getResBitmap(R.drawable.punta);
        mBmpL1     = getResBitmap(R.drawable.leftmod);
        mBmpL2     = getResBitmap(R.drawable.rightmod);
        mBmpS1     = getResBitmap(R.drawable.l1);
        mBmpS2     = getResBitmap(R.drawable.l2);
        mBmpPedinaRed  = getResBitmap(R.drawable.ped_rossa);
        mBmpPedinaBlu  = getResBitmap(R.drawable.ped_blu );

        mSrcRectmod.set  (0, 0, mBmpModulo.getWidth() -1, mBmpModulo.getHeight() - 1);
        mSrcRectPunta.set(0, 0, mBmpPunta.getWidth()  -1, mBmpPunta.getHeight()  - 1);
        mSrcRectL.set    (0, 0, mBmpL1.getWidth()     -1, mBmpL1.getHeight()     - 1);
        mSrcRectS.set    (0, 0, mBmpS1.getWidth()     -1, mBmpS1.getHeight()     - 1);
        mSrcRectPed.set  (0, 0, mBmpPedinaRed.getWidth()     -1, mBmpPedinaRed.getHeight()     - 1);


        mBmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        PosPedine   = new int[7][8];
        gameStatus  = new GameStatus();
        m_r         = new Random();
        setOnTouchListener(this);
    }



    public void startC4() {
        infaseclick = false;
        startTime = System.currentTimeMillis();
        int dellayiniziale;
        dellayiniziale = 2000;
        timerHandler.postDelayed(timerRunnable, dellayiniziale);
    }

    public void inizializza() {
        int y1;
 //       String TAG = "Inizializza ";     Log.v(TAG, " Ora ");

        impostadim(Varbase.withScreen, Varbase.hightScreen);


    //    if (Varbase.game_over) resettapedine(); else  resettabasic();

        resettapedine(); /////////////////////////////////////////////////////////////////
        // questo solo dopo animazione
        y1 = 8  * hmod + startygriglia;
        getLayoutParams().height  = y1;
        cancellopronto = true;
    }

    public void trova4vincenti() {
        int loci; int locj;
        boolean fatto = false;

      // Horizzontal test and found 5 positions
         for ( int i = 6; i>=0; i--) {
           if (fatto) break;
             loci = 6-i;
             for ( int j = 3; j>=0; j--) {
              if (gameStatus.board[j][loci] == 2) continue;
              if (fatto) break;
                if ( (gameStatus.board[j][loci] == gameStatus.board[j+1][loci])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+2][loci])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+3][loci])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+4][loci])
                   )
                {
                  for (int k=0; k<5; k++) { PosXwinPeace[k] = i; PosYwinPeace[k] = j+k; }
//                    pvi1=i; pvi2=i; pvi3=i; pvi4=i; pvi5=i;
//                    pvj1=j; pvj2=j+1; pvj3=j+2; pvj4=j+3; pvj5=j+4;
                  fatto = true;
                }
              }
         }

        // Vertical
        for ( int i = 2; i>=0; i--) {
            loci = 6-i;
            if (fatto) break;
            for ( int j = 7; j>=0; j--) {
                if (gameStatus.board[j][loci] == 2) continue;
                if (fatto) break;
                if ( (gameStatus.board[j][loci] == gameStatus.board[j][loci-1])
                   & (gameStatus.board[j][loci] == gameStatus.board[j][loci-2])
                   & (gameStatus.board[j][loci] == gameStatus.board[j][loci-3])
                  & (gameStatus.board[j][loci] == gameStatus.board[j][loci-4])
                   ) {
                    for (int k=0; k<5; k++) { PosXwinPeace[k] = i+k; PosYwinPeace[k] = j; }
//                    pvi1=i; pvi2=i+1; pvi3=i+2; pvi4=i+3; pvi5=i+4;
//                    pvj1=j; pvj2=j; pvj3=j; pvj4=j; pvj5=j;
                    fatto = true;
                }
            }
         }

// diagonali

        for ( int i = 6; i>=4; i--) {
            loci = 6-i;
            if (fatto) break;
            for ( int j = 7; j>=4; j--) {
                if (gameStatus.board[j][loci] == 2) continue;
                if (fatto) break;
                if (gameStatus.board[j][loci]==2) continue;
                if ( (gameStatus.board[j][loci] == gameStatus.board[j-1][loci+1])
                   & (gameStatus.board[j][loci] == gameStatus.board[j-2][loci+2])
                   & (gameStatus.board[j][loci] == gameStatus.board[j-3][loci+3])
                   & (gameStatus.board[j][loci] == gameStatus.board[j-4][loci+4])
                   ) {
                    for (int k=0; k<5; k++) { PosXwinPeace[k] = i-k; PosYwinPeace[k] = j-k; }

//                    pvi1=i; pvi2=i-1; pvi3=i-2; pvi4=i-3; pvi5=i-4;
//                    pvj1=j; pvj2=j-1; pvj3=j-2; pvj4=j-3; pvj5=j-4;
                    fatto = true;
                }
                if (fatto) break;
            }
            if (fatto) break;
        }

        for ( int i = 6; i>=4; i--) {
            loci = 6-i;
            if (fatto) break;
              for ( int j = 0; j<=3; j++) {
                  if (gameStatus.board[j][loci] == 2) continue;
                if (fatto) break;
                if (gameStatus.board[j][loci]==2) continue;
                if ( (gameStatus.board[j][loci] == gameStatus.board[j+1][loci+1])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+2][loci+2])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+3][loci+3])
                   & (gameStatus.board[j][loci] == gameStatus.board[j+4][loci+4])
                        ) {
                    for (int k=0; k<5; k++) { PosXwinPeace[k] = i-k; PosYwinPeace[k] = j+k; }

//                    pvi1=i; pvi2=i-1; pvi3=i-2; pvi4=i-3;  pvi5=i-4;
//                    pvj1=j; pvj2=j+1; pvj3=j+2; pvj4=j+3;  pvj5=j+4;
                    fatto = true;
                }
                if (fatto) break;
            }
            if (fatto) break;
        }

         if (!fatto) {
             for (int k=0; k<5; k++) { PosXwinPeace[k] = -1; PosYwinPeace[k] = -1; }
//             pvi1=-1; pvi2=-1; pvi3=-1; pvi4=-1;  pvi5=-1; pvj1=-1; pvj2=-1; pvj3=-1; pvj4=-1;  pvj5=-1;
         }
        invalidate();
     }


    public int coldix ( float xer) {
      int resulta =-1;
      int locx1,locx2;
        for ( int i=0; i<8; i++) {
         locx1 = i * wmod + leftmod;
         locx2 = locx1 + wmod - 1;
         if ((xer>locx1) & (xer<locx2)) {
          resulta = i;
         }
       }
      return resulta;
    }

    public void impostadim (int lw , int lh) {
      w = lw; h = lh;
        wmod    = (int) ((w-4) / 9);         hmod = wmod;        wpunt = wmod;
        leftmod = (int) (wmod /2)+3;
        hpunt   = (int) ( (hmod * mBmpPunta.getHeight() ) / mBmpModulo.getHeight() );
        wl1     = (int) ( (wmod * mBmpL1.getWidth()     ) / mBmpModulo.getWidth() );
        ws1     = (int) ( (wmod * mBmpS1.getWidth()     ) / mBmpModulo.getWidth() );
        hs1     = (int) ( (hmod * mBmpS1.getHeight()    ) / mBmpModulo.getHeight() );
//        private final Rect mDstRectPed   = new Rect();
        wped    = (int) ( (wmod * mBmpPedinaRed.getWidth()    ) / mBmpModulo.getWidth() );
        hped    = (int) ( (hmod * mBmpPedinaRed.getHeight()    ) / mBmpModulo.getHeight() );
        startygriglia = (int) (hmod*1.5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        disegnacancello(canvas);
    }




    public void disegnacancello(Canvas canvas){
        int x1,x2,y1,y2;


        if (!cancellopronto) {
            x1 =  pospedinadamettere  * wmod + leftmod + (( wmod- wped ) /2);             x2 = x1 + wped-1;
            float rap = (float)((nump*1.0f) /(maxnumpP*1.0f));
     //       String TAG = "Rap: ";  Log.v(TAG, " rapporto = " + rap+ " "+ nump+ " "+maxnumpP+" Arrivo :"+codeYscesa);
            float offlocaly=1;
            if (codeScesaplayer==0) {
               offlocaly = (rap*(codeYscesa+1)-1.0f);
            } else {
               offlocaly = (rap*(codeYscesa+3)-3.0f);
            }

                y1 = (int) (offlocaly  * hmod + startygriglia);       y2 = y1 + hped - 1;
            mDstRectmod.set(x1, y1, x2, y2);
            if (codeScesaplayer==0)  canvas.drawBitmap(mBmpPedinaRed, mSrcRectmod, mDstRectmod, mBmpPaint);
                              else   canvas.drawBitmap(mBmpPedinaBlu, mSrcRectmod, mDstRectmod, mBmpPaint);
        }


        for ( int i=0; i<8; i++) {
            for ( int j=0; j<7; j++) {
                x1 = i  * wmod + leftmod;             x2 = x1 + wmod-1;
                y1 = j  * hmod + startygriglia;       y2 = y1 + hmod - 1;
                mDstRectmod.set(x1, y1, x2, y2);
                canvas.drawBitmap(mBmpModulo, mSrcRectmod, mDstRectmod, mBmpPaint);

                x1 = (int) x1+ ( ( wmod- wped ) /2) ;       x2 = x1 + wped - 1;
                y1 = (int) y1+ ( ( hmod -hped ) /2) ;       y2 = y1 + hped - 1;
                mDstRectPed.set(x1, y1, x2, y2);


            if (gameStatus.board[i][6 - j] == ROSSO) {
                 canvas.drawBitmap(mBmpPedinaRed, mSrcRectmod, mDstRectPed, mBmpPaint);
            }
            if (gameStatus.board[i][6-j]==BLU)    {
                 canvas.drawBitmap(mBmpPedinaBlu, mSrcRectmod, mDstRectPed, mBmpPaint);
            }

            }
        }




        x1 = leftmod-wl1;  x2 = x1+wl1-1;
        for ( int j=0; j<7; j++) {
            y1 = j  * hmod + startygriglia;  y2 = y1 + hmod - 1;
            mDstRectL.set(x1, y1, x2, y2);           canvas.drawBitmap(mBmpL1, mSrcRectL, mDstRectL, mBmpPaint);
        }
        x1 = leftmod-wl1;  x2 = x1+wl1-1;
        y1 = 7  * hmod + startygriglia;  y2 = y1 + hs1 - 1;
        mDstRectL.set(x1, y1, x2, y2);           canvas.drawBitmap(mBmpS1, mSrcRectS, mDstRectL, mBmpPaint);

        x1 = 8  * wmod+leftmod; x2 = x1+wl1-1;
        for ( int j=0; j<7; j++) {
            y1 = j  * hmod + startygriglia;  y2 = y1 + hmod - 1;
            mDstRectL.set(x1, y1, x2, y2);           canvas.drawBitmap(mBmpL2, mSrcRectL, mDstRectL, mBmpPaint);
        }
        x2 = x1+wl1-1;
        y1 = 7  * hmod + startygriglia;
        y2 = y1 + hs1 - 1;
        mDstRectL.set(x1, y1, x2, y2);           canvas.drawBitmap(mBmpS2, mSrcRectS, mDstRectL, mBmpPaint);


        for ( int i=0; i<8; i++) {
            x1 = i  * wmod+leftmod;  x2 = x1+wmod-1;
            y1 = 7  * hmod  + startygriglia ;
            y2 = y1 + hpunt - 1;
            mDstRectPunta.set(x1, y1, x2, y2);       canvas.drawBitmap(mBmpPunta, mSrcRectPunta, mDstRectPunta, mBmpPaint);
        }


        // se devo evidenziare le vincenti
        if (PosXwinPeace[0]>=0)  {
            Paint cpaint = new Paint();
            cpaint.setColor(Color.YELLOW);
            cpaint.setStyle(Paint.Style.STROKE);
            cpaint.setStrokeWidth(8);
            float radius = wmod/2;

            for (int k=0; k<5; k++) {
              y1 = (int) (PosXwinPeace[k]   * wmod + startygriglia + hmod/2);
              x1 = (int) ((PosYwinPeace[k]) * hmod + startygriglia - wmod/2);
              canvas.drawCircle(x1, y1, radius, cpaint);
            }
/*
            y1 = (int) (pvi2  * wmod + startygriglia + hmod/2);    x1 = (int) ((pvj2)  * hmod + startygriglia - wmod/2);     canvas.drawCircle(x1, y1, radius, cpaint);
            y1 = (int) (pvi3  * wmod + startygriglia + hmod/2);    x1 = (int) ((pvj3)  * hmod + startygriglia - wmod/2);     canvas.drawCircle(x1, y1, radius, cpaint);
            y1 = (int) (pvi4  * wmod + startygriglia + hmod/2);    x1 = (int) ((pvj4)  * hmod + startygriglia - wmod/2);     canvas.drawCircle(x1, y1, radius, cpaint);
            y1 = (int) (pvi5  * wmod + startygriglia + hmod/2);    x1 = (int) ((pvj5)  * hmod + startygriglia - wmod/2);     canvas.drawCircle(x1, y1, radius, cpaint);
*/
        }

       if ((Varbase.toccame) & (cancellopronto) & (!Varbase.game_over )) {
          if (infaseclick) {
              y1 = -1  * hmod + startygriglia;
              y1 = (int) y1+ ( ( hmod -hped ) /2) ;       y2 = y1 + hped - 1;
              x1 = (int) (downx-wped/2);                  x2 = x1 + wped-1;
              mDstRectPed.set(x1, y1, x2, y2);
              canvas.drawBitmap(mBmpPedinaRed, mSrcRectmod, mDstRectPed, mBmpPaint);
          }
           else {

              y1 = -1  * hmod + startygriglia;
              x1 = (int) ( ( wmod- wped ) /2) ;     x2 = x1 + wped - 1;
              y1 = (int) y1+ ( ( hmod -hped ) /2) ;       y2 = y1 + hped - 1;
              mDstRectPed.set(x1, y1, x2, y2);
              canvas.drawBitmap(mBmpPedinaRed, mSrcRectmod, mDstRectPed, mBmpPaint);
          }
       }
    }



    private Bitmap getResBitmap(int bmpResId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inDither = false;
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, bmpResId, opts);
        if (bmp == null && isInEditMode()) {
            // BitmapFactory.decodeResource doesn't work from the rendering
            // library in Eclipse's Graphical Layout Editor. Use this workaround instead.
            Drawable d = res.getDrawable(bmpResId);
            int w = d.getIntrinsicWidth();
            int h = d.getIntrinsicHeight();
            bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            d.setBounds(0, 0, w - 1, h - 1);
            d.draw(c);
        }
        return bmp;
    }


    public void resettapedine() {
        Varbase.game_over  = false;
        Varbase.primamossa = true;
        gameStatus.winneris = 2;
        for (int i = 0; i < 7; i++) { for (int j =0; j< 8; j++) {   PosPedine[i][j]=0;  gameStatus.board[j][i]=2;  }};
        // Inizializa il punteggio
        for (int i = 0; i < 2; i++) { for (int j = 0; j < gameStatus.winPlaces; j++) gameStatus.score[i][j] = 1; }
        gameStatus.numPieces = 0;
        int totpt = Varbase.PtPareggi+Varbase.PtIoLocPart+Varbase.PtLuiLocPart;
//        String TAG = "Tot Pt: "; Log.v(TAG, " Pt = " + totpt);
        Varbase.toccame = true;
        if ((totpt % 2) == 0) { Varbase.toccame = true; } else { Varbase.toccame = false; }
        resettabasic();
    }

    public void resettabasic() {
        for (int k=0; k<5; k++) { PosXwinPeace[k] = -1; PosYwinPeace[k] = -1; }
    }





    public void movimentocadePedina(int codeP, int pospedy) {
        // codeP Player 0 ,1        in c4 ( 1 o 2)
        nump =0; codeScesaplayer=codeP; codeYscesa=pospedy; maxnumpP =8;


        timerHandlerPedina.post(runnablepedina);
//        VarB.mainparent.mHandler.post(runnable);

    }

    private void controllaFinemossa() {
        boolean pieno=true;
           if (gameStatus.isWinner(0)) {Varbase.game_over=true; gameStatus.winneris = 0; FinePartita(0); }
           if (gameStatus.isWinner(1)) {Varbase.game_over=true; gameStatus.winneris = 1; FinePartita(1); }
        // se pieno game_over=true;
        for ( int i = 6; i>=0; i--) {
            for ( int j = 7; j>=0; j--) {
                if (PosPedine[i][j]==0) {
                    Log.v("Test ",Integer.toString(i)+" "+Integer.toString(j));
                    pieno=false; break;}
            }
            if (!pieno) {    break;}
        }
        Thinking = false;
         if (pieno) {Varbase.game_over=true; FinePartita(2); };
    }

    public boolean lasciapedina() {
        boolean risulta=false;

        if (!Varbase.game_over) {
            if (Varbase.toccame) {
                for ( int i = 6; i>=0; i--) {
                    if (gameStatus.board[pospedinadamettere][6-i]==2) {
                        cancellopronto = false;
 //                       if (VarB.primamossa) { VarB.mainparent.inizia_fumettoPiccolo(); VarB.mainparent.numpF=1000; } // VarB.mainparent.numpF=9;
                        Varbase.primamossa = false;
                        Varbase.toccame = false;

                        movimentocadePedina(0, i);
                        SoundPlayer.TheSoundPlayer.playSuond_DropPeace();
                        risulta=true; break;
                    }
                }
            }
            else
            {
                for ( int i = 6; i>=0; i--) {
                    if (gameStatus.board[pospedinadamettere][6-i]==2) {
                        cancellopronto = false;
 //                       if (VarB.primamossa) { VarB.mainparent.inizia_fumettoPiccolo(); VarB.mainparent.numpF=1000; } // else VarB.mainparent.numpF=9;
                        Varbase.primamossa = false;
                        Varbase.toccame = true;


                        movimentocadePedina(1,i);
                        SoundPlayer.TheSoundPlayer.playSuond_DropPeace();
                        risulta=true; break;
                    }
                }
            }


        }
        return  risulta;
    }


    private void FinePartita(int indWinner) {
        gameFragment.impostapupovincente(indWinner);
        trova4vincenti();
        gameFragment.mettifumettovincente();

        if (indWinner==0) {SoundPlayer.TheSoundPlayer.suonaperdelui();  Varbase.PtIoLocPart++;   Varbase.Lastwinner = 0; }
        if (indWinner==1) {SoundPlayer.TheSoundPlayer.suonavincelui();  Varbase.PtLuiLocPart++;  Varbase.Lastwinner = 1;}
        if (indWinner==2) { Varbase.PtPareggi++; Varbase.Lastwinner = 2;}

        controllafinebloccoda5(); // vediamo se siamo 3-2 o qlacosaltro...
     //   Varbase.saveFileInfodata(contex);
    }


    public void controllafinebloccoda5() {
        int totpart2 = Varbase.PtIoLocPart+Varbase.PtLuiLocPart;
        Varbase.appenafinitoblocco = false;
        Varbase.PtIoLastPart = Varbase.PtIoLocPart;
        Varbase.PtLuiLastPart = Varbase.PtLuiLocPart;
        Varbase.LastLivelloH = Varbase.LivelloH;


        if (totpart2>=5) {
            // guarda chi vimce
            Varbase.appenafinitoblocco = true;
            Varbase.PtIoLastPart = Varbase.PtIoLocPart;
            Varbase.PtLuiLastPart = Varbase.PtLuiLocPart;
            Varbase.LastLivelloH = Varbase.LivelloH;

            if (Varbase.PtIoLocPart>Varbase.PtLuiLocPart) { Varbase.bonuslastblock = 20*Varbase.LivelloH;} else {Varbase.bonuslastblock =0;}

            Varbase.PtAccumulati = Varbase.PtAccumulati+Varbase.bonuslastblock;

            if (Varbase.PtIoLocPart>Varbase.PtLuiLocPart) Varbase.LastBlockWinner=0; else Varbase.LastBlockWinner=1;
            Varbase.aggiornastorico();
       //     Varbase.salvaLostorico();
            if (Varbase.PtIoLocPart>Varbase.PtLuiLocPart) {Varbase.LivelloH++; if (Varbase.LivelloH>5) Varbase.LivelloH=5; }
            if ((Varbase.PtIoLocPart+1)<Varbase.PtLuiLocPart) {Varbase.LivelloH--; if (Varbase.LivelloH<1) Varbase.LivelloH=1; }

            // riazzera partita
            Varbase.PtIoLocPart  = 0;            Varbase.PtLuiLocPart = 0;            Varbase.PtPareggi    = 0;
            // salva info e partita dalla procedura chiamante e dopo di qui
        }
    }




    private void ProvaSu(int player,int level ) {

// togli partita facile
        if (gameStatus.numPieces == 1) {
            // String TAG = "Partita Facile: ";            Log.v(TAG, " = ");
            if (gameStatus.board[3][0]!=2) {      //       Log.v(TAG, " Lo sapevo era 0 ! ");
                if (!Varbase.toccame) {
                   int casuale = Math.abs(m_r.nextInt(100));
                   if (casuale<50) pospedinadamettere = 4; else pospedinadamettere = 2;
//                   Log.v(TAG, " Lo sapevo era 0 ! casuale :"+ casuale);
                  return;
                }
            }
            if (gameStatus.board[2][0]!=2) {
                if (!Varbase.toccame) { pospedinadamettere = 3;
//                   Log.v(TAG, " Lo sapevo era 0 ! casuale :"+ casuale);
                    return;
                }
            }
            if (gameStatus.board[4][0]!=2) {
                if (!Varbase.toccame) { pospedinadamettere = 3;
//                   Log.v(TAG, " Lo sapevo era 0 ! casuale :"+ casuale);
                    return;
                }
            }
        }

        int bestXPos = -1, goodness = 0, bestWorst = -30000;  int numOfEqual = 0;
        // Simulate a drop in each of the columns
        for (int i = 0; i < 8; i++) {
            GameStatus tempState = new GameStatus(gameStatus);
            // If column is full, move on
            if (tempState.dropPiece(player, i) < 0) continue;
            // If this drop wins the game, then cool
            if (tempState.isWinner(player)) { bestWorst = 25000;  bestXPos = i; }
            // Otherwise, look ahead to see how good it is
            else  goodness = tempState.evaluate(player, level, 1, -30000, -bestWorst);
            // If this move looks better than previous moves, remember it
            if (goodness > bestWorst) { bestWorst = goodness; bestXPos = i; numOfEqual = 1; }

            // If two moves are equally good, make a random choice
            if (goodness == bestWorst) {
                numOfEqual++;
                if (Math.abs(m_r.nextInt()) % 10000 <  (10000 / numOfEqual))
                    bestXPos = i;
            }
        }
        // Drop the piece in the best column
        if (bestXPos >= 0) {  pospedinadamettere = bestXPos; }
    }



    public void repensamossa() {


        if ((!Varbase.game_over) & (!Thinking)) {
            Thinking = true;
//            if (Varbase.toccame)  { ProvaSu(0,Varbase.LivelloH);}
            if (!Varbase.toccame){ ProvaSu(1,Varbase.LivelloH);}
            lasciapedina();
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        downx = event.getX();  int rescol = coldix(downx);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (Varbase.toccame) {
                 infaseclick = true;
                 invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (Varbase.toccame) {
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                 infaseclick = false;
                 invalidate();
                 if (Varbase.toccame) { if (rescol >= 0) { if (cancellopronto) { pospedinadamettere = rescol; lasciapedina();} } }
                break;
        }
        return true;
    }




}
