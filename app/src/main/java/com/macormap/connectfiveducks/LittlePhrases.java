package com.macormap.connectfiveducks;

/**
 * Created by CarloMacor on 19/04/14.
 */
public class LittlePhrases {


    public static String fraseprimamossa(int pupo, int rander, int rig) {
        String resulta = "" ;
       // pupo=1 sono io   se = 0 e' il papero
        if (pupo==0) {
         switch (rander) {
             case 1 :
                 switch (rig) { case 1: resulta = "";  break ;
                                case 2: resulta = "I Move !";  break ;
                                case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
             case 2 :
                 switch (rig) { case 1: resulta = "The First Move";  break ;
                                case 2: resulta = "It's mine  :) ";  break ;
                                case 3: resulta = "Ahh Ahh";  break ; default: resulta = ""; break;  }  break;
             case 3 :
                 switch (rig) { case 1: resulta = "I Move First";  break ;
                                case 2: resulta = "Good";  break ;
                                case 3: resulta = "for me";  break ; default: resulta = ""; break;  }  break;
             case 4 :
                 switch (rig) { case 1: resulta = "I do ";  break ;
                                case 2: resulta = "the first";  break ;
                                case 3: resulta = "Move";  break ; default: resulta = ""; break;  }  break;
             case 5 :
                 switch (rig) { case 1: resulta = "Great !";  break ;
                                case 2: resulta = "I Move";  break ;
                                case 3: resulta = "first";  break ; default: resulta = ""; break;  }  break;
             default :
              switch (rig) { case 1: resulta = "";  break ; case 2: resulta = "I Move !";  break ;  case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
         }
        } else {
            switch (rander) {
            case 1 :
                switch (rig) { case 1: resulta = "";  break ;
                    case 2: resulta = "You Move !";  break ;
                    case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
            case 2 :
                switch (rig) { case 1: resulta = "The First Move";  break ;
                    case 2: resulta = "Is your  :)";  break ;
                    case 3: resulta = " Ach :( ";  break ; default: resulta = ""; break;  }  break;
            case 3 :
                switch (rig) { case 1: resulta = "You Move First";  break ;
                    case 2: resulta = "Good";  break ;
                    case 3: resulta = "for You";  break ; default: resulta = ""; break;  }  break;
            case 4 :
                switch (rig) { case 1: resulta = "You do";  break ;
                    case 2: resulta = "the first";  break ;
                    case 3: resulta = "Move";  break ; default: resulta = ""; break;  }  break;
            case 5 :
                switch (rig) { case 1: resulta = "#@##!!@# !";  break ;
                    case 2: resulta = "You Move";  break ;
                    case 3: resulta = "first";  break ; default: resulta = ""; break;  }  break;
            default :
                switch (rig) { case 1: resulta = "";  break ; case 2: resulta = "You Move !";  break ;  case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
            }
        }
        return resulta;
    }



    public static String frasequalsiasimossa(int pupo, int rander, int rig) {
        String resulta = "" ;
        switch (rander) {
            case 0 :
                switch (rig) {
                    case 1: resulta = "You move !";  break ;
                    case 2: resulta = "touch the";  break ;
                    case 3: resulta = "Gate please!";  break ; default: resulta = ""; break;  }  break;
            case 1 :
                switch (rig) {
                    case 1: resulta = "I'm not so strong";  break ;
                    case 2: resulta = "but it's not so";  break ;
                    case 3: resulta = "easy to win with me";  break ; default: resulta = ""; break;  }  break;
            case 2 :
                switch (rig) {
                    case 1: resulta = "Sometime";  break ;
                    case 2: resulta = "I do a good move";  break ;
                    case 3: resulta = "Ahh Ahh";  break ; default: resulta = ""; break;  }  break;
            case 3 :
                switch (rig) {
                    case 1: resulta = "Sometime";  break ;
                    case 2: resulta = "I make mistakes !";  break ;
                    case 3: resulta = "##@@#!!";  break ; default: resulta = ""; break;  }  break;
            case 4 :
                switch (rig) {
                    case 1: resulta = "Don't suppose";  break ;
                    case 2: resulta = "that's easy";  break ;
                    case 3: resulta = "Win";  break ; default: resulta = ""; break;  }  break;
            case 5 :
                switch (rig) {
                    case 1: resulta = "It's possible";  break ;
                    case 2: resulta = "that I'll";  break ;
                    case 3: resulta = "Win";  break ; default: resulta = ""; break;  }  break;
            case 6 :
                switch (rig) {
                    case 1: resulta = "I'm waiting";  break ;
                    case 2: resulta = "your";  break ;
                    case 3: resulta = "best move :)";  break ; default: resulta = ""; break;  }  break;
            case 7 :
                switch (rig) {
                    case 1: resulta = "Surprice me";  break ;
                    case 2: resulta = "with your";  break ;
                    case 3: resulta = "moves !";  break ; default: resulta = ""; break;  }  break;
            case 8 :
                switch (rig) {
                    case 1: resulta = "Nice to";  break ;
                    case 2: resulta = "play with";  break ;
                    case 3: resulta = "You";  break ; default: resulta = ""; break;  }  break;
            case 9 :
                switch (rig) {
                    case 1: resulta = "I know";  break ;
                    case 2: resulta = "Player very";  break ;
                    case 3: resulta = "Strong";  break ; default: resulta = ""; break;  }  break;
            case 10 :
                switch (rig) {
                    case 1: resulta = "I Think ";  break ;
                    case 2: resulta = "You Have";  break ;
                    case 3: resulta = "Talent";  break ; default: resulta = ""; break;  }  break;
            case 11 :
                switch (rig) {
                    case 1: resulta = "You can";  break ;
                    case 2: resulta = "Became a ";  break ;
                    case 3: resulta = "Strong Player";  break ; default: resulta = ""; break;  }  break;
            case 12 :
                switch (rig) {
                    case 1: resulta = "This Game";  break ;
                    case 2: resulta = "is for";  break ;
                    case 3: resulta = "Talent";  break ; default: resulta = ""; break;  }  break;
            case 13 :
                switch (rig) {
                    case 1: resulta = "You Can Win";  break ;
                    case 2: resulta = "but";  break ;
                    case 3: resulta = "It's not easy";  break ; default: resulta = ""; break;  }  break;
            case 14 :
                switch (rig) {
                    case 1: resulta = "You Can make";  break ;
                    case 2: resulta = "more points";  break ;
                    case 3: resulta = "if you Win";  break ; default: resulta = ""; break;  }  break;
            case 15 :
                switch (rig) {
                    case 1: resulta = "If You Lose";  break ;
                    case 2: resulta = "You Lose Points";  break ;
                    case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
            case 16 :
                switch (rig) {
                    case 1: resulta = "If You Win";  break ;
                    case 2: resulta = "You can Pass";  break ;
                    case 3: resulta = "The Gate";  break ; default: resulta = ""; break;  }  break;
            case 17 :
                switch (rig) {
                    case 1: resulta = "I Hate";  break ;
                    case 2: resulta = "Lose";  break ;
                    case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
            case 18 :
                switch (rig) {
                    case 1: resulta = "Do You";  break ;
                    case 2: resulta = "Know the";  break ;
                    case 3: resulta = "SpellMaster ?";  break ; default: resulta = ""; break;  }  break;
            case 19 :
                switch (rig) {
                    case 1: resulta = "SpellMaster";  break ;
                    case 2: resulta = "is";  break ;
                    case 3: resulta = "Invincible!";  break ; default: resulta = ""; break;  }  break;
            case 20 :
                switch (rig) {
                    case 1: resulta = "If You Play";  break ;
                    case 2: resulta = "Well You'll Play";  break ;
                    case 3: resulta = "with SpellMaster!";  break ; default: resulta = ""; break;  }  break;
            default :
                switch (rig) { case 1: resulta = "";  break ; case 2: resulta = "";  break ;  case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
        }
        return resulta;
    }


    public static String frasegameover(int pupo, int rander, int rig) {
        String resulta = "" ;
        switch (pupo) {
            case 1 :
                switch (rander) {
                    case 0 :
                    case 1 :
                    case 2 :
                        switch (rig) {
                            case 1: resulta = "";  break ;
                            case 2: resulta = "I Win !";  break ;
                            case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
                    default:
                    switch (rig) {
                        case 1: resulta = "";  break ;
                        case 2: resulta = "I Win !";  break ;
                        case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
                }
                 break;

            case 0 :
                switch (rander) {
                    case 0 :
                    case 1 :
                    case 2 :
                        switch (rig) {
                            case 1: resulta = "You Win !";  break ;
                            case 2: resulta = "##@@#@";  break ;
                            case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
                    default:
                        switch (rig) {
                            case 1: resulta = "You Win !";  break ;
                            case 2: resulta = "@@@@@@";  break ;
                            case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;
                }
                break;

            default :
               switch (rander) {
                default:
                    switch (rig) {
                        case 1: resulta = " ";  break ;
                        case 2: resulta = "Draw !";  break ;
                        case 3: resulta = "";  break ; default: resulta = ""; break;  }  break;

                }
            break;
        }


        return resulta;
    }


}
