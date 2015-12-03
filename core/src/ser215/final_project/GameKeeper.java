package ser215.final_project;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.time.LocalDate;

/**
 * Created by Brian on 11/16/2015.
 */
public class GameKeeper {
    private int numberPlayers;
    //private int[] playerLocations;
    private LocalDate currentDate;
    private Player[] playersList;
    private int currentPlayerTurn;
    private PlayingCard[] playingCards;
    private int lastBoardLocation;//Need to change for correct value


    //Default Constructor
    public GameKeeper() {
        this.numberPlayers = 3;
        //playerLocations = new int[] {0, 0, 0};
        this.currentDate = this.currentDate.now();
        this.playersList = new Player[3];
        this.playersList[0] = new Player();
        this.playersList[1] = new Player();
        this.playersList[2] = new Player();
        this.currentPlayerTurn = 0;
    }

    //X parameter constructor
    public GameKeeper(int numberPlayers, String[] playerNames, boolean[] computerPlayer, String[] playerCharacters) {
        this.numberPlayers = numberPlayers;
        this.currentDate = this.currentDate.now();
        this.playersList = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            this.playersList[i] = new Player(playerNames[i], computerPlayer[i] , playerCharacters[i]);
        }
        /*for (int i = 0; i < numberPlayers; i++) {
            //playerLocations[i] = 0;
        }*/
        this.currentPlayerTurn = 0;
    }


    //Accessor Methods
    public int getNumberPlayers() {
        return numberPlayers;
    }

    /*public int[] getPlayerLocations() {
        return playerLocations;
    }*/

    /*public int getCurrentPlayerLocation() {
        return this.playerLocations[this.currentPlayerTurn];
    }*/

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public Player[] getPlayersList() {
        return playersList;
    }

    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }


    //Mutator Methods
    public void changePlayerLocation(int currentPlayer, int spaceMoved) {
        this.playersList[currentPlayer].increaseBoardLocation(spaceMoved);
    }

    public void incrementPlayerTurn() {
        //System.out.println("Current Player: " + this.currentPlayerTurn);
        this.currentPlayerTurn = ((this.currentPlayerTurn + 1) % getNumberPlayers());
        //System.out.println("Current Player: " + this.currentPlayerTurn);
    }

    public void setLastBoardLocation(int lastBoardLocation) {
        this.lastBoardLocation = lastBoardLocation;
    }

    //Other Methods
    //Begins the next players turn
    public void nextTurn() {
        int dieRoll = this.playersList[this.currentPlayerTurn].rollDie();
        System.out.println(this.playersList[this.currentPlayerTurn].getName() + " " + this.playersList[this.currentPlayerTurn].getBoardLocation() + ": " + dieRoll);
        movePlayer(this.currentPlayerTurn, dieRoll);

        incrementPlayerTurn();
    }

    //Moves a player
    public void movePlayer(int playerToMove, int dieRoll) {
        if (!(this.playersList[playerToMove].getBoardLocation() + dieRoll > lastBoardLocation) && (this.playersList[playerToMove].getBoardLocation() + dieRoll < (lastBoardLocation - 1) ||
                this.playersList[playerToMove].getBoardLocation() + dieRoll == lastBoardLocation)){
            if (otherPlayerPresent(this.playersList[playerToMove].getBoardLocation() + dieRoll)) {
                if (this.playersList[this.currentPlayerTurn].winBattle(this.playersList[getPlayerNumberAtLocation(this.playersList[playerToMove].getBoardLocation() + dieRoll)])) {
                    moveLosingDefender(getPlayerNumberAtLocation(this.playersList[playerToMove].getBoardLocation() + dieRoll));
                    changePlayerLocation(playerToMove, dieRoll);
                }else {
                    movePlayer(playerToMove, dieRoll - 1);
                }
            }else {
                changePlayerLocation(playerToMove, dieRoll);
            }
        }else {
            //Add code to let player know that their roll doesn't allow any moves
        }
    }

    //Determines if another player is present on the spot a player is going to
    public boolean otherPlayerPresent(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return true;
        }
        return false;
    }

    //Moves the defender if he/she loses
    public void moveLosingDefender (int playerNumber) {
        //THIS IS MOVING THE LOSING DEFENDER BACK 2 BECAUSE IT MOVES BACK ONE AND RUNS INTO THE ATTACKER. ONLY IF THE ATTACKER IS RIGHT BEHIND THOUGH??? THIS IS WHY ORIGINALLY HAD MIN MOVE 2
        movePlayer(playerNumber, -1);       //-1 is back one space for losing battle
    }

    public int getPlayerNumberAtLocation(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return i;
        }
        return 0;
    }

    //NEED TO ADD METHOD(s) FOR VARIOUS GAME BOARD EVENTS SUCH AS LOSE TURN,

    public void calculatePlayerPositions() {
        for (int i = 0; i < numberPlayers; i++) {
            //Add code to get the correct location to draw the players on the board
        }
    }

    public static float[] getCoords(int boardLocation, String activeMap) {
        float[] coords = new float[2];

        if (activeMap.equals("Fall") || activeMap.equals("Spring") || activeMap.equals("Summer")) {
            switch (boardLocation) {
                case 0:
                    coords[0] = 64;
                    coords[1] = 864;
                    break;
                case 1:
                    coords[0] = 64;
                    coords[1] = 832;
                    break;
                case 2:
                    coords[0] = 64;
                    coords[1] = 800;
                    break;
                case 3:
                    coords[0] = 64;
                    coords[1] = 768;
                    break;
                case 4:
                    coords[0] = 64;
                    coords[1] = 736;
                    break;
                case 5:
                    coords[0] = 64;
                    coords[1] = 704;
                    break;
                case 6:
                    coords[0] = 64;
                    coords[1] = 672;
                    break;
                case 7:
                    coords[0] = 64;
                    coords[1] = 640;
                    break;
                case 8:
                    coords[0] = 64;
                    coords[1] = 608;
                    break;
                case 9:
                    coords[0] = 64;
                    coords[1] = 576;
                    break;
                case 10:
                    coords[0] = 64;
                    coords[1] = 544;
                    break;
                case 11:
                    coords[0] = 64;
                    coords[1] = 512;
                    break;
                case 12:
                    coords[0] = 64;
                    coords[1] = 480;
                    break;
                case 13:
                    coords[0] = 64;
                    coords[1] = 448;
                    break;
                case 14:
                    coords[0] = 64;
                    coords[1] = 416;
                    break;
                case 15:
                    coords[0] = 64;
                    coords[1] = 384;
                    break;
                case 16:
                    coords[0] = 64;
                    coords[1] = 352;
                    break;
                case 17:
                    coords[0] = 64;
                    coords[1] = 320;
                    break;
                case 18:
                    coords[0] = 64;
                    coords[1] = 288;
                    break;
                case 19:
                    coords[0] = 64;
                    coords[1] = 256;
                    break;
                case 20:
                    coords[0] = 64;
                    coords[1] = 224;
                    break;
                case 21:
                    coords[0] = 64;
                    coords[1] = 192;
                    break;
                case 22:
                    coords[0] = 64;
                    coords[1] = 160;
                    break;
                case 23:
                    coords[0] = 64;
                    coords[1] = 128;
                    break;
                case 24:
                    coords[0] = 64;
                    coords[1] = 96;
                    break;
                case 25:
                    coords[0] = 96;
                    coords[1] = 96;
                    break;
                case 26:
                    coords[0] = 128;
                    coords[1] = 96;
                    break;
                case 27:
                    coords[0] = 160;
                    coords[1] = 96;
                    break;
                case 28:
                    coords[0] = 192;
                    coords[1] = 96;
                    break;
                case 29:
                    coords[0] = 224;
                    coords[1] = 96;
                    break;
                case 30:
                    coords[0] = 256;
                    coords[1] = 96;
                    break;
                case 31:
                    coords[0] = 288;
                    coords[1] = 96;
                    break;
                case 32:
                    coords[0] = 320;
                    coords[1] = 96;
                    break;
                case 33:
                    coords[0] = 352;
                    coords[1] = 96;
                    break;
                case 34:
                    coords[0] = 384;
                    coords[1] = 96;
                    break;
                case 35:
                    coords[0] = 416;
                    coords[1] = 96;
                    break;
                case 36:
                    coords[0] = 448;
                    coords[1] = 96;
                    break;
                case 37:
                    coords[0] = 480;
                    coords[1] = 96;
                    break;
                case 38:
                    coords[0] = 480;
                    coords[1] = 128;
                    break;
                case 39:
                    coords[0] = 480;
                    coords[1] = 160;
                    break;
                case 40:
                    coords[0] = 480;
                    coords[1] = 192;
                    break;
                case 41:
                    coords[0] = 480;
                    coords[1] = 224;
                    break;
                case 42:
                    coords[0] = 480;
                    coords[1] = 256;
                    break;
                case 43:
                    coords[0] = 480;
                    coords[1] = 288;
                    break;
                case 44:
                    coords[0] = 512;
                    coords[1] = 288;
                    break;
                case 45:
                    coords[0] = 544;
                    coords[1] = 288;
                    break;
                case 46:
                    coords[0] = 576;
                    coords[1] = 288;
                    break;
                case 47:
                    coords[0] = 608;
                    coords[1] = 288;
                    break;
                case 48:
                    coords[0] = 608;
                    coords[1] = 320;
                    break;
                case 49:
                    coords[0] = 608;
                    coords[1] = 352;
                    break;
                case 50:
                    coords[0] = 608;
                    coords[1] = 384;
                    break;
                case 51:
                    coords[0] = 576;
                    coords[1] = 384;
                    break;
                case 52:
                    coords[0] = 544;
                    coords[1] = 384;
                    break;
                case 53:
                    coords[0] = 512;
                    coords[1] = 384;
                    break;
                case 54:
                    coords[0] = 480;
                    coords[1] = 384;
                    break;
                case 55:
                    coords[0] = 480;
                    coords[1] = 384;
                    break;
                case 56:
                    coords[0] = 480;
                    coords[1] = 416;
                    break;
                case 57:
                    coords[0] = 480;
                    coords[1] = 448;
                    break;
                case 58:
                    coords[0] = 480;
                    coords[1] = 480;
                    break;
                case 59:
                    coords[0] = 480;
                    coords[1] = 512;
                    break;
                case 60:
                    coords[0] = 480;
                    coords[1] = 544;
                    break;
                case 61:
                    coords[0] = 480;
                    coords[1] = 576;
                    break;
                case 62:
                    coords[0] = 480;
                    coords[1] = 608;
                    break;
                case 63:
                    coords[0] = 480;
                    coords[1] = 640;
                    break;
                case 64:
                    coords[0] = 480;
                    coords[1] = 672;
                    break;
                case 65:
                    coords[0] = 512;
                    coords[1] = 672;
                    break;
                case 66:
                    coords[0] = 512;
                    coords[1] = 704;
                    break;
                case 67:
                    coords[0] = 544;
                    coords[1] = 704;
                    break;
                case 68:
                    coords[0] = 576;
                    coords[1] = 704;
                    break;
                case 69:
                    coords[0] = 608;
                    coords[1] = 704;
                    break;
                case 70:
                    coords[0] = 640;
                    coords[1] = 704;
                    break;
                case 71:
                    coords[0] = 640;
                    coords[1] = 672;
                    break;
                case 72:
                    coords[0] = 640;
                    coords[1] = 640;
                    break;
                case 73:
                    coords[0] = 640;
                    coords[1] = 608;
                    break;
                case 74:
                    coords[0] = 640;
                    coords[1] = 576;
                    break;
                case 75:
                    coords[0] = 640;
                    coords[1] = 544;
                    break;
                case 76:
                    coords[0] = 640;
                    coords[1] = 512;
                    break;
                case 77:
                    coords[0] = 672;
                    coords[1] = 512;
                    break;
                case 78:
                    coords[0] = 704;
                    coords[1] = 512;
                    break;
                case 79:
                    coords[0] = 736;
                    coords[1] = 512;
                    break;
                case 80:
                    coords[0] = 768;
                    coords[1] = 512;
                    break;
                case 81:
                    coords[0] = 768;
                    coords[1] = 544;
                    break;
                case 82:
                    coords[0] = 768;
                    coords[1] = 576;
                    break;
                case 83:
                    coords[0] = 768;
                    coords[1] = 608;
                    break;
                case 84:
                    coords[0] = 768;
                    coords[1] = 640;
                    break;
                case 85:
                    coords[0] = 768;
                    coords[1] = 672;
                    break;
                case 86:
                    coords[0] = 768;
                    coords[1] = 704;
                    break;
                case 87:
                    coords[0] = 768;
                    coords[1] = 736;
                    break;
                case 88:
                    coords[0] = 768;
                    coords[1] = 768;
                    break;
                case 89:
                    coords[0] = 768;
                    coords[1] = 800;
                    break;
                case 90:
                    coords[0] = 800;
                    coords[1] = 800;
                    break;
                case 91:
                    coords[0] = 832;
                    coords[1] = 800;
                    break;
                case 92:
                    coords[0] = 864;
                    coords[1] = 800;
                    break;
                case 93:
                    coords[0] = 864;
                    coords[1] = 768;
                    break;
                case 94:
                    coords[0] = 864;
                    coords[1] = 736;
                    break;
                case 95:
                    coords[0] = 864;
                    coords[1] = 704;
                    break;
                case 96:
                    coords[0] = 864;
                    coords[1] = 672;
                    break;
                case 97:
                    coords[0] = 864;
                    coords[1] = 640;
                    break;
                case 98:
                    coords[0] = 864;
                    coords[1] = 608;
                    break;
                case 99:
                    coords[0] = 864;
                    coords[1] = 576;
                    break;
                case 100:
                    coords[0] = 864;
                    coords[1] = 544;
                    break;
                case 101:
                    coords[0] = 864;
                    coords[1] = 512;
                    break;
                case 102:
                    coords[0] = 864;
                    coords[1] = 480;
                    break;
                case 103:
                    coords[0] = 864;
                    coords[1] = 448;
                    break;
                case 104:
                    coords[0] = 864;
                    coords[1] = 416;
                    break;
                case 105:
                    coords[0] = 864;
                    coords[1] = 384;
                    break;
                case 106:
                    coords[0] = 864;
                    coords[1] = 352;
                    break;
                case 107:
                    coords[0] = 864;
                    coords[1] = 320;
                    break;
                case 108:
                    coords[0] = 864;
                    coords[1] = 288;
                    break;
                case 109:
                    coords[0] = 864;
                    coords[1] = 256;
                    break;
                case 110:
                    coords[0] = 864;
                    coords[1] = 224;
                    break;
                case 111:
                    coords[0] = 864;
                    coords[1] = 192;
                    break;
                case 112:
                    coords[0] = 864;
                    coords[1] = 160;
                    break;
                case 113:
                    coords[0] = 864;
                    coords[1] = 128;
                    break;
                case 114:
                    coords[0] = 864;
                    coords[1] = 96;
                    break;
                case 115:
                    coords[0] = 864;
                    coords[1] = 64;
                    break;
                case 116:
                    coords[0] = 864;
                    coords[1] = 32;
                    break;
                default:
                    coords[0] = 960 / 2 - 64;
                    coords[1] = 960 / 2;
                    //Temp Comment
            }
        }else if (activeMap.equals("Winter")) {
            switch (boardLocation) {
                case 0: coords[0] = 64; coords[1] = 864;
                    break;
                case 1: coords[0] = 64; coords[1] = 832;
                    break;
                case 2: coords[0] = 64; coords[1] = 800;
                    break;
                case 3: coords[0] = 64; coords[1] = 768;
                    break;
                case 4: coords[0] = 64; coords[1] = 736;
                    break;
                case 5: coords[0] = 64; coords[1] = 704;
                    break;
                case 6: coords[0] = 64; coords[1] = 672;
                    break;
                case 7: coords[0] = 64; coords[1] = 640;
                    break;
                case 8: coords[0] = 64; coords[1] = 608;
                    break;
                case 9: coords[0] = 64; coords[1] = 576;
                    break;
                case 10: coords[0] = 64; coords[1] = 544;
                    break;
                case 11: coords[0] = 64; coords[1] = 512;
                    break;
                case 12: coords[0] = 64; coords[1] = 480;
                    break;
                case 13: coords[0] = 64; coords[1] = 448;
                    break;
                case 14: coords[0] = 64; coords[1] = 416;
                    break;
                case 15: coords[0] = 64; coords[1] = 384;
                    break;
                case 16: coords[0] = 64; coords[1] = 352;
                    break;
                case 17: coords[0] = 64; coords[1] = 320;
                    break;
                case 18: coords[0] = 64; coords[1] = 288;
                    break;
                case 19: coords[0] = 96; coords[1] = 288;
                    break;
                case 20: coords[0] = 128; coords[1] = 288;
                    break;
                case 21: coords[0] = 160; coords[1] = 288;
                    break;
                case 22: coords[0] = 192; coords[1] = 288;
                    break;
                case 23: coords[0] = 224; coords[1] = 288;
                    break;
                case 24: coords[0] = 256; coords[1] = 288;
                    break;
                case 25: coords[0] = 288; coords[1] = 288;
                    break;
                case 26: coords[0] = 320; coords[1] = 288;
                    break;
                case 27: coords[0] = 352; coords[1] = 288;
                    break;
                case 28: coords[0] = 384; coords[1] = 288;
                    break;
                case 29: coords[0] = 416; coords[1] = 288;
                    break;
                case 30: coords[0] = 448; coords[1] = 288;
                    break;
                case 31: coords[0] = 480; coords[1] = 288;
                    break;
                case 32: coords[0] = 512; coords[1] = 288;
                    break;
                case 33: coords[0] = 544; coords[1] = 288;
                    break;
                case 34: coords[0] = 576; coords[1] = 288;
                    break;
                case 35: coords[0] = 608; coords[1] = 288;
                    break;
                case 36: coords[0] = 608; coords[1] = 320;
                    break;
                case 37: coords[0] = 608; coords[1] = 352;
                    break;
                case 38: coords[0] = 608; coords[1] = 384;
                    break;
                case 39: coords[0] = 576; coords[1] = 384;
                    break;
                case 40: coords[0] = 544; coords[1] = 384;
                    break;
                case 41: coords[0] = 512; coords[1] = 384;
                    break;
                case 42: coords[0] = 480; coords[1] = 384;
                    break;
                case 43: coords[0] = 480; coords[1] = 384;
                    break;
                case 44: coords[0] = 480; coords[1] = 416;
                    break;
                case 45: coords[0] = 480; coords[1] = 448;
                    break;
                case 46: coords[0] = 480; coords[1] = 480;
                    break;
                case 47: coords[0] = 480; coords[1] = 512;
                    break;
                case 48: coords[0] = 480; coords[1] = 544;
                    break;
                case 49: coords[0] = 480; coords[1] = 576;
                    break;
                case 50: coords[0] = 480; coords[1] = 608;
                    break;
                case 51: coords[0] = 480; coords[1] = 640;
                    break;
                case 52: coords[0] = 480; coords[1] = 672;
                    break;
                case 53: coords[0] = 512; coords[1] = 672;
                    break;
                case 54: coords[0] = 512; coords[1] = 704;
                    break;
                case 55: coords[0] = 544; coords[1] = 704;
                    break;
                case 56: coords[0] = 576; coords[1] = 704;
                    break;
                case 57: coords[0] = 608; coords[1] = 704;
                    break;
                case 58: coords[0] = 640; coords[1] = 704;
                    break;
                case 59: coords[0] = 640; coords[1] = 672;
                    break;
                case 60: coords[0] = 640; coords[1] = 640;
                    break;
                case 61: coords[0] = 640; coords[1] = 608;
                    break;
                case 62: coords[0] = 640; coords[1] = 576;
                    break;
                case 63: coords[0] = 640; coords[1] = 544;
                    break;
                case 64: coords[0] = 640; coords[1] = 512;
                    break;
                case 65: coords[0] = 672; coords[1] = 512;
                    break;
                case 66: coords[0] = 704; coords[1] = 512;
                    break;
                case 67: coords[0] = 736; coords[1] = 512;
                    break;
                case 68: coords[0] = 768; coords[1] = 512;
                    break;
                case 69: coords[0] = 768; coords[1] = 544;
                    break;
                case 70: coords[0] = 768; coords[1] = 576;
                    break;
                case 71: coords[0] = 768; coords[1] = 608;
                    break;
                case 72: coords[0] = 768; coords[1] = 640;
                    break;
                case 73: coords[0] = 768; coords[1] = 672;
                    break;
                case 74: coords[0] = 768; coords[1] = 704;
                    break;
                case 75: coords[0] = 768; coords[1] = 736;
                    break;
                case 76: coords[0] = 768; coords[1] = 768;
                    break;
                case 77: coords[0] = 768; coords[1] = 800;
                    break;
                case 78: coords[0] = 800; coords[1] = 800;
                    break;
                case 79: coords[0] = 832; coords[1] = 800;
                    break;
                case 80: coords[0] = 864; coords[1] = 800;
                    break;
                case 81: coords[0] = 864; coords[1] = 768;
                    break;
                case 82: coords[0] = 864; coords[1] = 736;
                    break;
                case 83: coords[0] = 864; coords[1] = 704;
                    break;
                case 84: coords[0] = 864; coords[1] = 672;
                    break;
                case 85: coords[0] = 864; coords[1] = 640;
                    break;
                case 86: coords[0] = 864; coords[1] = 608;
                    break;
                case 87: coords[0] = 864; coords[1] = 576;
                    break;
                case 88: coords[0] = 864; coords[1] = 544;
                    break;
                case 89: coords[0] = 864; coords[1] = 512;
                    break;
                case 90: coords[0] = 864; coords[1] = 480;
                    break;
                case 91: coords[0] = 864; coords[1] = 448;
                    break;
                case 92: coords[0] = 864; coords[1] = 416;
                    break;
                case 93: coords[0] = 864; coords[1] = 384;
                    break;
                case 94: coords[0] = 864; coords[1] = 352;
                    break;
                case 95: coords[0] = 864; coords[1] = 320;
                    break;
                case 96: coords[0] = 864; coords[1] = 288;
                    break;
                case 97: coords[0] = 864; coords[1] = 256;
                    break;
                case 98: coords[0] = 864; coords[1] = 224;
                    break;
                case 99: coords[0] = 864; coords[1] = 192;
                    break;
                case 100: coords[0] = 864; coords[1] = 160;
                    break;
                case 101: coords[0] = 864; coords[1] = 128;
                    break;
                case 102: coords[0] = 864; coords[1] = 96;
                    break;
                case 103: coords[0] = 864; coords[1] = 64;
                    break;
                case 104: coords[0] = 864; coords[1] = 32;
                    break;
                default:
                    coords[0] = 960 / 2 - 64;
                    coords[1] = 960 / 2;
            }
        }


        return coords;
    }
}
