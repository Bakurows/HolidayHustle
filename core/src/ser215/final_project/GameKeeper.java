package ser215.final_project;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;

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
    private String activeMap;


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
        this.activeMap = "Fall";
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
        this.activeMap = "Fall";
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

    public void setActiveMap(String activeMap) {
        this.activeMap = activeMap;
    }

    public void setLastBoardLocation(int lastBoardLocation) {
        this.lastBoardLocation = lastBoardLocation;
    }

    //Other Methods

    //Begins the next players turn
    public void nextTurn() {
        if (this.playersList[this.currentPlayerTurn].getTurnSkips() > 0) {
            this.playersList[this.currentPlayerTurn].incrementTurnSkips(-1);
        }else {
            int dieRoll = this.playersList[this.currentPlayerTurn].rollDie();
            if (this.playersList[currentPlayerTurn].getBoardLocation() == this.lastBoardLocation)
                this.playersList[currentPlayerTurn].increaseBoardLocation(1);
            movePlayer(this.currentPlayerTurn, dieRoll);
            System.out.println(this.playersList[this.currentPlayerTurn].getName() + " " + this.playersList[this.currentPlayerTurn].getBoardLocation() + ": " + dieRoll);
        }

        incrementPlayerTurn();
    }

    //Moves a player
    public void movePlayer(int playerToMove, int dieRoll) {
        if (!(this.playersList[playerToMove].getBoardLocation() + dieRoll > lastBoardLocation) && (this.playersList[playerToMove].getBoardLocation() + dieRoll < (lastBoardLocation) ||
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
        }
        performBoardEvent(this.playersList[this.currentPlayerTurn].getBoardLocation());
        //TODO insert game board event method call
    }

    public void movePlayerAlternate(int playerToMove, int dieRoll) {
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
        }
        performBoardEvent(this.playersList[this.currentPlayerTurn].getBoardLocation());
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

    //Returns the player number (based of turn order) at a given board location
    public int getPlayerNumberAtLocation(int boardLocation) {
        for (int i = 0; i < this.numberPlayers; i++) {
            if (this.playersList[i].getBoardLocation() == boardLocation)
                return i;
        }
        return 0;
    }

    //NEED TO ADD METHOD(s) FOR VARIOUS GAME BOARD EVENTS SUCH AS LOSE TURN

    //Checks if the board location a player has landed on has an effect. If so, performs the appropriate effect
    /**
     *Summer:
     *Wasp: Push you back 2 spaces and lower strength by 1
     *Weeds: Get stuck in weeds for 1 turn, or skunk sprays you - gain instant win, random - High chance weeds, very low chance skunk 99:1
     *Strawberry Patch: Boost strength by 1 or boost next roll by 2
     *Fall:
     *Leaves: Trip and fall, go back 1 space, 50% chance to lose 1 turn
     *Eye Monster: Run in fear, go back 3 spaces
     *Portal: First one teleports to second, second does nothing if landed on (Same)
     *Skeleton: Doot doot attack lowers strength by 3 (Same)
     *Lava: Lava burn, run back 1 space and recover for 1 turn
     *Winter:
     *Eggnog: Increases strength by 1 (Same)
     *Priest: Prays for you and you get a random card, move forward 1 space
     *Green Yeti: Takes a card from you and pushes you back 2 spaces (Same)
     *Anchor: Just a barrier, forces you to take ice path (Same)
     *Spring:
     *Butterfly: Boosts you forward from 1 to 3 spaces
     *Wasp: Stings you so you can’t move for 1 turn or run and go back 2 spaces, randomly chooses
     *Leprechaun: Increase strength by 2-3 and receive random card
     */
    public void performBoardEvent(int boardLocation) {
        boardLocation++;

        if (activeMap.equals("Fall")) {
            if (boardLocation == 9 || boardLocation == 18 || boardLocation == 24 || boardLocation == 37 || boardLocation == 41 || boardLocation == 45 ||
                    boardLocation == 50 || boardLocation == 55 || boardLocation == 67 || boardLocation == 76) {
                //Current player goes back 1 space
                movePlayerAlternate(this.currentPlayerTurn, -1);
                int temp = MathUtils.random(1);
                switch (temp) {
                    case 0: //nothing
                        break;
                    case 1: //lose 1 turn
                        this.playersList[this.currentPlayerTurn].incrementTurnSkips(1);
                        break;
                }
            } else if (boardLocation == 88 || boardLocation == 89 || boardLocation == 94 || boardLocation == 100 || boardLocation == 102 ||
                    boardLocation == 104 || boardLocation == 109 || boardLocation == 111 || boardLocation == 113) {
                //Player goes back 1 space and loses 1 turn
                movePlayerAlternate(this.currentPlayerTurn, -1);
                this.playersList[this.currentPlayerTurn].incrementTurnSkips(1);
            } else if (boardLocation == 33) {
                //Player moves to board location 52... will make it possible for multiple players on one spot
                movePlayerAlternate(this.currentPlayerTurn, 19);
            } else if (boardLocation == 13) {
                //Player goes back 3 spaces
                movePlayerAlternate(this.currentPlayerTurn, -3);
            } else if (boardLocation == 43 || boardLocation == 57) {
                //Player loses 3 strength
                this.playersList[this.currentPlayerTurn].increaseStrengthGain(-3);
            }
        }else if (activeMap.equals("Winter")) {
            if (boardLocation == 5 || boardLocation == 11 || boardLocation == 15 || boardLocation == 18 || boardLocation == 24 || boardLocation == 32 || boardLocation == 33
                    || boardLocation == 46 || boardLocation == 56 || boardLocation == 62 || boardLocation == 66 || boardLocation == 79 || boardLocation == 83 || boardLocation == 84
                    || boardLocation == 90 || boardLocation == 96 || boardLocation == 97 || boardLocation == 101) {
                //increase Player strength by 1
                this.playersList[this.currentPlayerTurn].increaseStrengthGain(1);
            }else if (boardLocation == 6 || boardLocation == 44 || boardLocation == 60 || boardLocation == 92) {
                //TODO Gain random card
                //move forward 1 space
                movePlayerAlternate(this.currentPlayerTurn, 1);
            }else if (boardLocation == 9 || boardLocation == 50 || boardLocation == 75 || boardLocation == 86 || boardLocation == 99) {
                //TODO Lose random card
                //go back 2 spaces
                movePlayerAlternate(this.currentPlayerTurn, -2);
            }
        }else if (activeMap.equals("Spring")) {
            if (boardLocation == 4 || boardLocation == 8 || boardLocation == 11 || boardLocation == 21 || boardLocation == 23 || boardLocation == 24 || boardLocation == 30 || boardLocation == 34
                    || boardLocation == 42 || boardLocation == 46 || boardLocation == 58 || boardLocation == 61 || boardLocation == 67 || boardLocation == 75 || boardLocation == 85 || boardLocation == 92
                    || boardLocation == 101 || boardLocation == 106 || boardLocation == 113 || boardLocation == 114) {
                //move forward 1-3 spaces
                movePlayerAlternate(this.currentPlayerTurn, MathUtils.random(2) + 1);
            }else if (boardLocation == 6 || boardLocation == 7 || boardLocation == 12 || boardLocation == 13 || boardLocation == 15 || boardLocation == 19 || boardLocation == 27 || boardLocation == 37
                    || boardLocation == 50 || boardLocation == 64 || boardLocation == 78 || boardLocation == 82 || boardLocation == 90 || boardLocation == 96 || boardLocation == 99 || boardLocation == 102
                    || boardLocation == 109) {
                int temp = MathUtils.random(1);
                switch (temp) {
                    case 0: //lose 1 turn
                        this.playersList[this.currentPlayerTurn].incrementTurnSkips(1);
                        break;
                    case 1: //go back 2 spaces
                        movePlayerAlternate(this.currentPlayerTurn, -2);
                        break;
                }
            }else if (boardLocation == 10 || boardLocation == 40 || boardLocation == 72) {
                //gain 2-3 strength
                this.playersList[this.currentPlayerTurn].increaseStrengthGain(MathUtils.random(1) + 2);
                //TODO receive random card
            }
        }else if (activeMap.equals("Summer")) {
            if (boardLocation == 4 || boardLocation == 13 || boardLocation == 19 || boardLocation == 29 || boardLocation == 43 || boardLocation == 50 || boardLocation == 58 || boardLocation == 64
                    || boardLocation == 71 || boardLocation == 79 || boardLocation == 87 || boardLocation == 95 || boardLocation == 104 || boardLocation == 110 || boardLocation == 114) {
                //go back 2 spaces
                movePlayerAlternate(this.currentPlayerTurn, -2);
                //lose 1 strength
                this.playersList[this.currentPlayerTurn].increaseStrengthGain(-1);
            }else if (boardLocation == 6 || boardLocation == 16 || boardLocation == 22 || boardLocation == 23 || boardLocation == 24 || boardLocation == 38 || boardLocation == 39 || boardLocation == 56
                    || boardLocation == 62 || boardLocation == 89 || boardLocation == 90 || boardLocation == 98 || boardLocation == 106 || boardLocation == 109 || boardLocation == 112) {
                int temp = MathUtils.random(99) + 1;
                if (temp == 32) {
                    //gain instant win
                    this.playersList[this.currentPlayerTurn].gainInstantWin();
                }else {
                    //lose 1 turn
                    this.playersList[this.currentPlayerTurn].incrementTurnSkips(1);
                }
            }else if (boardLocation == 7 || boardLocation == 9 || boardLocation == 10 || boardLocation == 17 || boardLocation == 27 || boardLocation == 30 || boardLocation == 33 || boardLocation == 41
                    || boardLocation == 60 || boardLocation == 68 || boardLocation == 73 || boardLocation == 83 || boardLocation == 92 || boardLocation == 102) {
                int temp = MathUtils.random(1);
                switch (temp) {
                    case 0: //gain 1 strength
                        this.playersList[this.currentPlayerTurn].increaseStrengthGain(1);
                        break;
                    case 1: //boost next roll by 2
                        this.playersList[this.currentPlayerTurn].increaseRollBoost(2);
                        break;
                }
            }
        }
    }

    //Returns the coordinates to draw the players characters on the board
    public static float[] getCoords(int boardLocation, String activeMap) {
        float[] coords = new float[2];

        if (activeMap.equals("Fall") || activeMap.equals("Spring") || activeMap.equals("Summer")) {
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
                case 19: coords[0] = 64; coords[1] = 256;
                    break;
                case 20: coords[0] = 64; coords[1] = 224;
                    break;
                case 21: coords[0] = 64; coords[1] = 192;
                    break;
                case 22: coords[0] = 64; coords[1] = 160;
                    break;
                case 23: coords[0] = 64; coords[1] = 128;
                    break;
                case 24: coords[0] = 64; coords[1] = 96;
                    break;
                case 25: coords[0] = 96; coords[1] = 96;
                    break;
                case 26: coords[0] = 128; coords[1] = 96;
                    break;
                case 27: coords[0] = 160; coords[1] = 96;
                    break;
                case 28: coords[0] = 192; coords[1] = 96;
                    break;
                case 29: coords[0] = 224; coords[1] = 96;
                    break;
                case 30: coords[0] = 256; coords[1] = 96;
                    break;
                case 31: coords[0] = 288; coords[1] = 96;
                    break;
                case 32: coords[0] = 320; coords[1] = 96;
                    break;
                case 33: coords[0] = 352; coords[1] = 96;
                    break;
                case 34: coords[0] = 384; coords[1] = 96;
                    break;
                case 35: coords[0] = 416; coords[1] = 96;
                    break;
                case 36: coords[0] = 448; coords[1] = 96;
                    break;
                case 37: coords[0] = 480; coords[1] = 96;
                    break;
                case 38: coords[0] = 480; coords[1] = 128;
                    break;
                case 39: coords[0] = 480; coords[1] = 160;
                    break;
                case 40: coords[0] = 480; coords[1] = 192;
                    break;
                case 41: coords[0] = 480; coords[1] = 224;
                    break;
                case 42: coords[0] = 480; coords[1] = 256;
                    break;
                case 43: coords[0] = 480; coords[1] = 288;
                    break;
                case 44: coords[0] = 512; coords[1] = 288;
                    break;
                case 45: coords[0] = 544; coords[1] = 288;
                    break;
                case 46: coords[0] = 576; coords[1] = 288;
                    break;
                case 47: coords[0] = 608; coords[1] = 288;
                    break;
                case 48: coords[0] = 608; coords[1] = 320;
                    break;
                case 49: coords[0] = 608; coords[1] = 352;
                    break;
                case 50: coords[0] = 608; coords[1] = 384;
                    break;
                case 51: coords[0] = 576; coords[1] = 384;
                    break;
                case 52: coords[0] = 544; coords[1] = 384;
                    break;
                case 53: coords[0] = 512; coords[1] = 384;
                    break;
                case 54: coords[0] = 480; coords[1] = 384;
                    break;
                case 55: coords[0] = 480; coords[1] = 416;
                    break;
                case 56: coords[0] = 480; coords[1] = 448;
                    break;
                case 57: coords[0] = 480; coords[1] = 480;
                    break;
                case 58: coords[0] = 480; coords[1] = 512;
                    break;
                case 59: coords[0] = 480; coords[1] = 544;
                    break;
                case 60: coords[0] = 480; coords[1] = 576;
                    break;
                case 61: coords[0] = 480; coords[1] = 608;
                    break;
                case 62: coords[0] = 480; coords[1] = 640;
                    break;
                case 63: coords[0] = 480; coords[1] = 672;
                    break;
                case 64: coords[0] = 512; coords[1] = 672;
                    break;
                case 65: coords[0] = 512; coords[1] = 704;
                    break;
                case 66: coords[0] = 544; coords[1] = 704;
                    break;
                case 67: coords[0] = 576; coords[1] = 704;
                    break;
                case 68: coords[0] = 608; coords[1] = 704;
                    break;
                case 69: coords[0] = 640; coords[1] = 704;
                    break;
                case 70: coords[0] = 640; coords[1] = 672;
                    break;
                case 71: coords[0] = 640; coords[1] = 640;
                    break;
                case 72: coords[0] = 640; coords[1] = 608;
                    break;
                case 73: coords[0] = 640; coords[1] = 576;
                    break;
                case 74: coords[0] = 640; coords[1] = 544;
                    break;
                case 75: coords[0] = 640; coords[1] = 512;
                    break;
                case 76: coords[0] = 672; coords[1] = 512;
                    break;
                case 77: coords[0] = 704; coords[1] = 512;
                    break;
                case 78: coords[0] = 736; coords[1] = 512;
                    break;
                case 79: coords[0] = 768; coords[1] = 512;
                    break;
                case 80: coords[0] = 768; coords[1] = 544;
                    break;
                case 81: coords[0] = 768; coords[1] = 576;
                    break;
                case 82: coords[0] = 768; coords[1] = 608;
                    break;
                case 83: coords[0] = 768; coords[1] = 640;
                    break;
                case 84: coords[0] = 768; coords[1] = 672;
                    break;
                case 85: coords[0] = 768; coords[1] = 704;
                    break;
                case 86: coords[0] = 768; coords[1] = 736;
                    break;
                case 87: coords[0] = 768; coords[1] = 768;
                    break;
                case 88: coords[0] = 768; coords[1] = 800;
                    break;
                case 89: coords[0] = 800; coords[1] = 800;
                    break;
                case 90: coords[0] = 832; coords[1] = 800;
                    break;
                case 91: coords[0] = 864; coords[1] = 800;
                    break;
                case 92: coords[0] = 864; coords[1] = 768;
                    break;
                case 93: coords[0] = 864; coords[1] = 736;
                    break;
                case 94: coords[0] = 864; coords[1] = 704;
                    break;
                case 95: coords[0] = 864; coords[1] = 672;
                    break;
                case 96: coords[0] = 864; coords[1] = 640;
                    break;
                case 97: coords[0] = 864; coords[1] = 608;
                    break;
                case 98: coords[0] = 864; coords[1] = 576;
                    break;
                case 99: coords[0] = 864; coords[1] = 544;
                    break;
                case 100: coords[0] = 864; coords[1] = 512;
                    break;
                case 101: coords[0] = 864; coords[1] = 480;
                    break;
                case 102: coords[0] = 864; coords[1] = 448;
                    break;
                case 103: coords[0] = 864; coords[1] = 416;
                    break;
                case 104: coords[0] = 864; coords[1] = 384;
                    break;
                case 105: coords[0] = 864; coords[1] = 352;
                    break;
                case 106: coords[0] = 864; coords[1] = 320;
                    break;
                case 107: coords[0] = 864; coords[1] = 288;
                    break;
                case 108: coords[0] = 864; coords[1] = 256;
                    break;
                case 109: coords[0] = 864; coords[1] = 224;
                    break;
                case 110: coords[0] = 864; coords[1] = 192;
                    break;
                case 111: coords[0] = 864; coords[1] = 160;
                    break;
                case 112: coords[0] = 864; coords[1] = 128;
                    break;
                case 113: coords[0] = 864; coords[1] = 96;
                    break;
                case 114: coords[0] = 864; coords[1] = 64;
                    break;
                case 115: coords[0] = 864; coords[1] = 32;
                    break;
            }
            if (boardLocation > 115) {
                coords[0] = -64;
                coords[1] = -64;
            }else if (boardLocation < 0) {
                coords[0] = 64;
                coords[1] = 864;
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
                case 43: coords[0] = 480; coords[1] = 416;
                    break;
                case 44: coords[0] = 480; coords[1] = 448;
                    break;
                case 45: coords[0] = 480; coords[1] = 480;
                    break;
                case 46: coords[0] = 480; coords[1] = 512;
                    break;
                case 47: coords[0] = 480; coords[1] = 544;
                    break;
                case 48: coords[0] = 480; coords[1] = 576;
                    break;
                case 49: coords[0] = 480; coords[1] = 608;
                    break;
                case 50: coords[0] = 480; coords[1] = 640;
                    break;
                case 51: coords[0] = 480; coords[1] = 672;
                    break;
                case 52: coords[0] = 512; coords[1] = 672;
                    break;
                case 53: coords[0] = 512; coords[1] = 704;
                    break;
                case 54: coords[0] = 544; coords[1] = 704;
                    break;
                case 55: coords[0] = 576; coords[1] = 704;
                    break;
                case 56: coords[0] = 608; coords[1] = 704;
                    break;
                case 57: coords[0] = 640; coords[1] = 704;
                    break;
                case 58: coords[0] = 640; coords[1] = 672;
                    break;
                case 59: coords[0] = 640; coords[1] = 640;
                    break;
                case 60: coords[0] = 640; coords[1] = 608;
                    break;
                case 61: coords[0] = 640; coords[1] = 576;
                    break;
                case 62: coords[0] = 640; coords[1] = 544;
                    break;
                case 63: coords[0] = 640; coords[1] = 512;
                    break;
                case 64: coords[0] = 672; coords[1] = 512;
                    break;
                case 65: coords[0] = 704; coords[1] = 512;
                    break;
                case 66: coords[0] = 736; coords[1] = 512;
                    break;
                case 67: coords[0] = 768; coords[1] = 512;
                    break;
                case 68: coords[0] = 768; coords[1] = 544;
                    break;
                case 69: coords[0] = 768; coords[1] = 576;
                    break;
                case 70: coords[0] = 768; coords[1] = 608;
                    break;
                case 71: coords[0] = 768; coords[1] = 640;
                    break;
                case 72: coords[0] = 768; coords[1] = 672;
                    break;
                case 73: coords[0] = 768; coords[1] = 704;
                    break;
                case 74: coords[0] = 768; coords[1] = 736;
                    break;
                case 75: coords[0] = 768; coords[1] = 768;
                    break;
                case 76: coords[0] = 768; coords[1] = 800;
                    break;
                case 77: coords[0] = 800; coords[1] = 800;
                    break;
                case 78: coords[0] = 832; coords[1] = 800;
                    break;
                case 79: coords[0] = 864; coords[1] = 800;
                    break;
                case 80: coords[0] = 864; coords[1] = 768;
                    break;
                case 81: coords[0] = 864; coords[1] = 736;
                    break;
                case 82: coords[0] = 864; coords[1] = 704;
                    break;
                case 83: coords[0] = 864; coords[1] = 672;
                    break;
                case 84: coords[0] = 864; coords[1] = 640;
                    break;
                case 85: coords[0] = 864; coords[1] = 608;
                    break;
                case 86: coords[0] = 864; coords[1] = 576;
                    break;
                case 87: coords[0] = 864; coords[1] = 544;
                    break;
                case 88: coords[0] = 864; coords[1] = 512;
                    break;
                case 89: coords[0] = 864; coords[1] = 480;
                    break;
                case 90: coords[0] = 864; coords[1] = 448;
                    break;
                case 91: coords[0] = 864; coords[1] = 416;
                    break;
                case 92: coords[0] = 864; coords[1] = 384;
                    break;
                case 93: coords[0] = 864; coords[1] = 352;
                    break;
                case 94: coords[0] = 864; coords[1] = 320;
                    break;
                case 95: coords[0] = 864; coords[1] = 288;
                    break;
                case 96: coords[0] = 864; coords[1] = 256;
                    break;
                case 97: coords[0] = 864; coords[1] = 224;
                    break;
                case 98: coords[0] = 864; coords[1] = 192;
                    break;
                case 99: coords[0] = 864; coords[1] = 160;
                    break;
                case 100: coords[0] = 864; coords[1] = 128;
                    break;
                case 101: coords[0] = 864; coords[1] = 96;
                    break;
                case 102: coords[0] = 864; coords[1] = 64;
                    break;
                case 103: coords[0] = 864; coords[1] = 32;
                    break;
            }
            if (boardLocation > 103) {
                coords[0] = -64;
                coords[1] = -64;
            }else if (boardLocation < 0) {
                coords[0] = 64;
                coords[1] = 864;
            }
        }


        return coords;
    }
}
