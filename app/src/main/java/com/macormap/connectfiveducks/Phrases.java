package com.macormap.connectfiveducks;

/**
 * Created by CarloMacor on 15/04/14.
 */
public class Phrases {


    private static String frase1(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1: resulta = "To Open This";  break ;
            case 2: resulta = "Gate ... You Must";  break ;
            case 3: resulta = "Win the Match !";  break ;
            default: resulta = ""; break;
        }
        return resulta;
    }

    private static String frase2(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1: resulta = "We'll Play 5 Turns";  break ;
            case 2: resulta = "Actually ..";  break ;
            case 3: resulta = ""+Integer.toString(Varbase.PtIoLocPart)+"    -    "+Integer.toString(Varbase.PtLuiLocPart);  break ;
            default: resulta = ""; break;
        }
        return resulta;
    }

    private static String frase3(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1: resulta = "If You Win";  break ;
            case 2: resulta = "";
            switch (pupo) {
                case 1 : case 2 : case 3 : case 4 : case 5 : resulta = "You'll Know"; break;
            }
             break ;
            case 3:
              switch (pupo) {
                  case 1 : resulta = "The Guard !"; break;
                  case 2 : resulta = "The Captain !"; break;
                  case 3 : resulta = "The 'Evil' !"; break;
                  case 4 : resulta = "The 'SpellMaster' !"; break;
                  case 5 : resulta = "The 'Main SpellMaster' !"; break;
                  case 6 : resulta = "The 'Invincible' !"; break;
                  case 7 : resulta = "Me Again :) !"; break;
              }
             break ;
            default: resulta = ""; break;
        }
        return resulta;
    }


    private static String frase4(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1:
                switch (Varbase.Lastwinner) {
                    case 0 : resulta = "You Win !"; break;
                    case 1 : resulta = "You Lost !"; break;
                    case 2 : resulta = "Draw Game !!"; break;
                 }
                break ;

            case 2:

                if (Varbase.isconnected) {
                    switch (Varbase.Lastwinner) {
                        case 0 : resulta = "So You Get"; break;
                        case 1 : resulta = "So You Lose"; break;
                        case 2 : resulta = "Bonus of"; break;
                    }
                } else { resulta = "No Internet Connected"; break; }
                break ;

            case 3:
                if (Varbase.isconnected) {
                  switch (Varbase.Lastwinner) {
                    case 0 : resulta = ""+Varbase.bonuslastgame + "  Points"; break;
                    case 1 : resulta = ""+Varbase.bonuslastgame + "  Points"; break;
                    case 2 : resulta = ""+Varbase.bonuslastgame + "  Points"; break;
                  }
                } else { resulta = "So ... No Bonus Point"; break; }
            break ;

            default: resulta = ""; break;
        }
        return resulta;
    }


    private static String frase5(int pupo, int fase, int rig) {
        String resulta = "" ;
        switch (rig) {
            case 1: resulta = "Total";  break ;
            case 2: resulta = "Points ";  break ;
            case 3: resulta = " "+Varbase.PtAccumulati;  break ;
            default: resulta = ""; break;
        }
        return resulta;
    }

    private static String frase6(int pupo, int fase, int rig) {
        String resulta = "a" ;
        if (Varbase.isconnected) {
          switch (rig) {
            case 1: resulta = "About the Match";  break ;
            case 2: resulta = "Actually ..";  break ;
            case 3: resulta = ""+Integer.toString(Varbase.PtIoLocPart)+"    -    "+Integer.toString(Varbase.PtLuiLocPart);  break ;
            default: resulta = ""; break;
          }
        } else {
            switch (rig) {
                case 1: resulta = "No Connection";  break ;
                case 2: resulta = "sorry no Updated";  break ;
                case 3: resulta = "Match result   "+Integer.toString(Varbase.PtIoLocPart)+"    -    "+Integer.toString(Varbase.PtLuiLocPart);  break ;
                default: resulta = ""; break;
            }
        }
        return resulta;
    }


    private static String frase7(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1: resulta = "The Match Is";  break ;
            case 2: resulta = "Completed";  break ;
            case 3: resulta = ""+Integer.toString(Varbase.PtIoLastPart)+"    -    "+Integer.toString(Varbase.PtLuiLastPart);  break ;
            default: resulta = ""; break;
        }
        return resulta;
    }

    private static String frase8(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1:
                switch (Varbase.LastBlockWinner) {
                case 0 : resulta = "You Win the Match!"; break;
                case 1 : resulta = "You Lost the Match!"; break;
                case 2 : resulta = "Draw Game !!"; break;
            }
            break ;
            case 2:
                switch (Varbase.LastBlockWinner) {
                  case 0 : resulta = "So You Get!"; break;
                  case 1 : resulta = "You Lose!"; break;
                  case 2 : resulta = ""; break;
                }
            break;
            case 3: resulta = ""+Varbase.bonuslastgame+" + "+Varbase.bonuslastblock+"  Points ";  break ;
            default: resulta = ""; break;
        }
        return resulta;
    }

    private static String frase9(int pupo, int fase, int rig) {
        String resulta = "a" ;
        switch (rig) {
            case 1: resulta = "You Will Continue";  break ;
            case 2: resulta = "To Play with";  break ;
            case 3:
                if (Varbase.LivelloH==Varbase.LastLivelloH) {resulta = "Me Again :) !";}
                else
                switch (Varbase.LivelloH) {
                    case 1 : resulta = "The Slave!"; break;
                    case 2 : resulta = "The Guard !"; break;
                    case 3 : resulta = "The Captain !"; break;
                    case 4 : resulta = "The 'Evil' !"; break;
                    case 5 : resulta = "The 'SpellMaster' !"; break;
                    case 6 : resulta = "The 'Main SpellMaster' !"; break;
                    case 7 : resulta = "The 'Invincible' !"; break;
                    case 8 : resulta = "Me Again :) !"; break;
                }

              break ;
            default: resulta = ""; break;
        }
        return resulta;
    }



    public static String getTextRow1(int pupo, int fase) {
        String resulta = "a" ;
        switch (fase) {
            case 1: resulta = frase1(pupo,fase, 1);  break ;
            case 2: resulta = frase2(pupo,fase, 1);  break ;
            case 3: resulta = frase3(pupo,fase, 1);  break ;

            case 4: resulta = frase4(pupo,fase, 1);  break ;
            case 5: resulta = frase5(pupo,fase, 1);  break ;
            case 6: resulta = frase6(pupo,fase, 1);  break ;

            case 7: resulta = frase7(pupo,fase, 1);  break ;
            case 8: resulta = frase8(pupo,fase, 1);  break ;
            case 9: resulta = frase9(pupo,fase, 1);  break ;

            default: resulta = "else_1_3";
        }
        return resulta;
    }

    public static String getTextRow2(int pupo, int fase) {
        String resulta = "a"  ;
        switch (fase) {
        case 1: resulta = frase1(pupo,fase,2);  break ;
        case 2: resulta = frase2(pupo,fase,2);  break ;
        case 3: resulta = frase3(pupo,fase,2);  break ;

        case 4: resulta = frase4(pupo,fase,2);  break ;
        case 5: resulta = frase5(pupo,fase,2);  break ;
        case 6: resulta = frase6(pupo,fase,2);  break ;

        case 7: resulta = frase7(pupo, fase, 2);  break ;
        case 8: resulta = frase8(pupo, fase, 2);  break ;
        case 9: resulta = frase9(pupo, fase, 2);  break;
        default: resulta = "else_2_3";
        }
        return resulta;
    }

    public static String getTextRow3(int pupo, int fase) {
        String resulta =  "a" ;
        switch (fase) {
            case 1: resulta = frase1(pupo,fase,3);  break ;
            case 2: resulta = frase2(pupo,fase,3);  break ;
            case 3: resulta = frase3(pupo,fase,3);  break ;

            case 4: resulta = frase4(pupo,fase,3);  break ;
            case 5: resulta = frase5(pupo,fase,3);  break ;
            case 6: resulta = frase6(pupo,fase,3);  break ;

            case 7: resulta = frase7(pupo,fase,3);  break ;
            case 8: resulta = frase8(pupo,fase,3);  break ;
            case 9: resulta = frase9(pupo,fase,3);  break ;


            default: resulta = "else_3_3";
        }
        return resulta;
    }


}
