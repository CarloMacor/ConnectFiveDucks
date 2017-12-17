package com.macormap.connectfiveducks;

class GameStatus {
//    public static final int winPlaces = 69, maxPieces = 42, Empty = 2;
    public static final int winPlaces = 76;
    public static final int Empty = 2;
    public static boolean[][][] map;
    private static int HorPos = 8;
    private static int VertPos = 7;
    public static final int maxPieces = HorPos*VertPos; // 56
    public int[][]  board = new int[HorPos][VertPos];
    public int[][]  score = new int[2][winPlaces];
    public int      numPieces;
    public int      winneris;

    public GameStatus() {
        // Initialize the map
        winneris    = 2;
        int i, j, k, count = 0;
        if (map == null) {
            map = new boolean[HorPos][VertPos][winPlaces];

            // inizialize all to false;
            for (i = 0; i < HorPos; i++)
                for (j = 0; j < VertPos; j++)
                    for (k = 0; k < winPlaces; k++)
                        map[i][j][k] = false;

            // Set the horizontal win positions  // 28 winnng positions
            for (i = 0; i < VertPos; i++)
                for (j = 0; j < 4; j++) {
                    for (k = 0; k < 5; k++) { map[j + k][i][count] = true;}
                    count++;
                }

            // Set the vertical win positions   // 24 winning positions
            for (i = 0; i < HorPos; i++)
                for (j = 0; j < 3; j++) {
                    for (k = 0; k < 5; k++) { map[i][j + k][count] = true;}
                    count++;
                }

            // Set the forward diagonal win positions
            for (i = 0; i < 3; i++)
                for (j = 0; j < 4; j++) {
                    for (k = 0; k < 5; k++) { map[j + k][i + k][count] = true;}
                    count++;
                }

            // Set the backward diagonal win positions
            for (i = 0; i < 3; i++)
                for (j = 7; j >= 4; j--) {
                    for (k = 0; k < 5; k++) { map[j - k][i + k][count] = true;}
                    count++;
                }
        }
        // Initialize the board
        for (i = 0; i < HorPos; i++)
            for (j = 0; j < VertPos; j++)
                board[i][j] = Empty;

        // Initialize the scores
        for (i = 0; i < 2; i++)
            for (j = 0; j < winPlaces; j++)
                score[i][j] = 1;

        numPieces = 0;
    }

    public GameStatus(GameStatus gameStatus) {
        // Copy the board
        for (int i = 0; i < HorPos; i++)
            for (int j = 0; j < VertPos; j++)
                board[i][j] = gameStatus.board[i][j];

        // Copy the scores
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < winPlaces; j++)
                score[i][j] = gameStatus.score[i][j];

        numPieces = gameStatus.numPieces;
    }

    public boolean isWinner(int player) {
        for (int i = 0; i < winPlaces; i++)
            if (score[player][i] == 32)  // 2 ^ 5 ( connect 5 ! )
                return true;
        return false;
    }



    public boolean isTie() {
        return (numPieces == maxPieces);
    }

    public int dropPiece(int player, int xPos) {
        int yPos = 0;
        while ((board[xPos][yPos] != Empty) && (++yPos < VertPos));

        // The column is full
        if (yPos == VertPos)
            return -1;

        // The move is OK
        board[xPos][yPos] = player;
        numPieces++;
        updateScore(player, xPos, yPos);

        return yPos;
    }

    public int evaluate(int player, int level, int depth, int alpha,
                        int beta) {
        int goodness, best, maxab = alpha;

        if (level != depth) {
            best = -30000;
            for(int i = 0; i < HorPos; i++) {
                GameStatus momStatus = new GameStatus(this);
                if (momStatus.dropPiece(getOtherPlayer(player), i) < 0)
                    continue;

                if (momStatus.isWinner(getOtherPlayer(player)))
                    goodness = 25000 - depth;
                else
                    goodness = momStatus.evaluate(getOtherPlayer(player),
                            level, depth + 1, -beta, -maxab);
                if (goodness > best) {
                    best = goodness;
                    if (best > maxab)
                        maxab = best;
                }
                if (best > beta)
                    break;
            }

            // What's good for the other player is bad for this one
            return -best;
        }

        return (calcScore(player) - calcScore(getOtherPlayer(player)));
    }

    private int calcScore(int player) {
        int s = 0;
        for (int i = 0; i < winPlaces; i++)
            s += score[player][i];
        return s;
    }

    private void updateScore(int player, int x, int y) {
        for (int i = 0; i < winPlaces; i++)
            if (map[x][y][i]) {
                score[player][i] <<= 1;
                score[getOtherPlayer(player)][i] = 0;
            }
    }

    private int getOtherPlayer(int player) {
        return (1 - player);
    }
}
