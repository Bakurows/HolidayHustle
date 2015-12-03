package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ser215.final_project.GameKeeper;
import ser215.final_project.HolidayHustle;

import java.util.Random;

/**
 * Created by Brian on 11/30/2015.
 */
public class PreGameScreen implements Screen {
    private HolidayHustle game;
    private final String[] numPlayers, characterChoices, computerPlayers;
    private GameKeeper gameKeeper;
    private String[] names, characterNames;
    private boolean[] iscomputerPlayer;
    private Stage stage;
    private Table table;
    private Skin skin;
    private SelectBox<String> numberOfPlayers;
    private SelectBox<String> playerOneCharacter, playerTwoCharacter, playerThreeCharacter, playerFourCharacter, playerFiveCharacter, playerSixCharacter;
    private SelectBox<String>[] computerPlayerBoxes; //computerPlayerOne, computerPlayerTwo, computerPlayerThree, computerPlayerFour, computerPlayerFive, computerPlayerSix;
    private TextField numberOfPlayersText;
    private TextField playerOneName, playerTwoName, playerThreeName, playerFourName, playerFiveName, playerSixName;
    private TextButton buttonPlay, buttonMenu;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public PreGameScreen(HolidayHustle game) {
        this.game = game;
        numPlayers = new String[]{"3", "4", "5", "6"};
        characterChoices = new String[] {"Frosty", "Jack", "Bugs", "Piper"};
        computerPlayers = new String [] {"Person", "Computer"};
        computerPlayerBoxes = new SelectBox[6];
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Add stuff here

        camera.update();
        renderer.setView(camera);
        renderer.render();

        if (numberOfPlayers.getSelected().equals("3")) {
            playerFourName.setDisabled(true);
            playerFiveName.setDisabled(true);
            playerSixName.setDisabled(true);
            playerFourName.setText("Player Four Name");
            playerFiveName.setText("Player Five Name");
            playerSixName.setText("Player Six Name");
            playerFourCharacter.setDisabled(true);
            playerFiveCharacter.setDisabled(true);
            playerSixCharacter.setDisabled(true);
            playerFourCharacter.setSelectedIndex(0);
            playerFiveCharacter.setSelectedIndex(0);
            playerSixCharacter.setSelectedIndex(0);
            computerPlayerBoxes[3].setDisabled(true);
            computerPlayerBoxes[4].setDisabled(true);
            computerPlayerBoxes[5].setDisabled(true);
            computerPlayerBoxes[3].setSelectedIndex(0);
            computerPlayerBoxes[4].setSelectedIndex(0);
            computerPlayerBoxes[5].setSelectedIndex(0);
        }else if (numberOfPlayers.getSelected().equals("4")) {
            playerFourName.setDisabled(false);
            playerFiveName.setDisabled(true);
            playerSixName.setDisabled(true);
            playerFiveName.setText("Player Five Name");
            playerSixName.setText("Player Six Name");
            playerFourCharacter.setDisabled(false);
            playerFiveCharacter.setDisabled(true);
            playerSixCharacter.setDisabled(true);
            playerFiveCharacter.setSelectedIndex(0);
            playerSixCharacter.setSelectedIndex(0);
            computerPlayerBoxes[3].setDisabled(false);
            computerPlayerBoxes[4].setDisabled(true);
            computerPlayerBoxes[5].setDisabled(true);
            computerPlayerBoxes[4].setSelectedIndex(0);
            computerPlayerBoxes[5].setSelectedIndex(0);
        }else if(numberOfPlayers.getSelected().equals("5")) {
            playerFourName.setDisabled(false);
            playerFiveName.setDisabled(false);
            playerSixName.setDisabled(true);
            playerSixName.setText("Player Six Name");
            playerFourCharacter.setDisabled(false);
            playerFiveCharacter.setDisabled(false);
            playerSixCharacter.setDisabled(true);
            playerSixCharacter.setSelectedIndex(0);
            computerPlayerBoxes[3].setDisabled(false);
            computerPlayerBoxes[4].setDisabled(false);
            computerPlayerBoxes[5].setDisabled(true);
            computerPlayerBoxes[5].setSelectedIndex(0);
        }else if(numberOfPlayers.getSelected().equals("6")){
            playerFourName.setDisabled(false);
            playerFiveName.setDisabled(false);
            playerSixName.setDisabled(false);
            playerFourCharacter.setDisabled(false);
            playerFiveCharacter.setDisabled(false);
            playerSixCharacter.setDisabled(false);
            computerPlayerBoxes[3].setDisabled(false);
            computerPlayerBoxes[4].setDisabled(false);
            computerPlayerBoxes[5].setDisabled(false);
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show() {
        Random rng = new Random();
        int rand = rng.nextInt(4);
        switch (rand) {
            case 0: map = new TmxMapLoader().load("map_summer.tmx");
                break;
            case 1: map = new TmxMapLoader().load("map_fall.tmx");
                break;
            case 2: map = new TmxMapLoader().load("map_winter.tmx");
                break;
            case 3: map = new TmxMapLoader().load("map_spring.tmx");
                break;
        }
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.left();
        table.pad(0,50,0,0);


        numberOfPlayers = new SelectBox<String>(skin);
        numberOfPlayers.setItems(numPlayers);
        numberOfPlayers.setWidth(110);
        numberOfPlayers.setPosition(Gdx.graphics.getWidth() / 2 - numberOfPlayers.getWidth() / 2, 810);

        numberOfPlayersText = new TextField("How Many Players Are Playing?", skin);
        numberOfPlayersText.setDisabled(true);
        numberOfPlayersText.setWidth(435);
        numberOfPlayersText.setPosition(Gdx.graphics.getWidth() / 2 - numberOfPlayersText.getWidth() / 2, 820 + numberOfPlayers.getHeight());

        //Player name fields for players to enter their names.
        playerOneName = new TextField("Player One Name", skin);
        playerOneName.setMaxLength(16);
        playerTwoName = new TextField("Player Two Name", skin);
        playerTwoName.setMaxLength(16);
        playerThreeName = new TextField("Player Three Name", skin);
        playerThreeName.setMaxLength(16);
        playerFourName = new TextField("Player Four Name", skin);
        playerFourName.setDisabled(true);
        playerFourName.setMaxLength(16);
        playerFiveName = new TextField("Player Five Name", skin);
        playerFiveName.setDisabled(true);
        playerFiveName.setMaxLength(16);
        playerSixName = new TextField("Player Six Name", skin);
        playerSixName.setDisabled(true);
        playerSixName.setMaxLength(16);

        //Player character fields for players to choose their characters.
        playerOneCharacter = new SelectBox<String>(skin);
        playerOneCharacter.setItems(characterChoices);
        playerOneCharacter.setWidth(90);

        playerTwoCharacter = new SelectBox<String>(skin);
        playerTwoCharacter.setItems(characterChoices);
        playerTwoCharacter.setWidth(90);
        //Temp
        playerTwoCharacter.setSelectedIndex(1);

        playerThreeCharacter = new SelectBox<String>(skin);
        playerThreeCharacter.setItems(characterChoices);
        playerThreeCharacter.setWidth(90);
        //Temp
        playerThreeCharacter.setSelectedIndex(2);

        playerFourCharacter = new SelectBox<String>(skin);
        playerFourCharacter.setItems(characterChoices);
        playerFourCharacter.setWidth(90);

        playerFiveCharacter = new SelectBox<String>(skin);
        playerFiveCharacter.setItems(characterChoices);
        playerFiveCharacter.setWidth(90);

        playerSixCharacter = new SelectBox<String>(skin);
        playerSixCharacter.setItems(characterChoices);
        playerSixCharacter.setWidth(90);

        //Select whether each player is computer or person
        computerPlayerBoxes[0] = new SelectBox<String>(skin);
        computerPlayerBoxes[0].setItems(computerPlayers);
        computerPlayerBoxes[0].setWidth(90);

        computerPlayerBoxes[1] = new SelectBox<String>(skin);
        computerPlayerBoxes[1].setItems(computerPlayers);
        computerPlayerBoxes[1].setWidth(90);

        computerPlayerBoxes[2] = new SelectBox<String>(skin);
        computerPlayerBoxes[2].setItems(computerPlayers);
        computerPlayerBoxes[2].setWidth(90);

        computerPlayerBoxes[3] = new SelectBox<String>(skin);
        computerPlayerBoxes[3].setItems(computerPlayers);
        computerPlayerBoxes[3].setWidth(90);

        computerPlayerBoxes[4] = new SelectBox<String>(skin);
        computerPlayerBoxes[4].setItems(computerPlayers);
        computerPlayerBoxes[4].setWidth(90);

        computerPlayerBoxes[5] = new SelectBox<String>(skin);
        computerPlayerBoxes[5].setItems(computerPlayers);
        computerPlayerBoxes[5].setWidth(90);

        //Play button to go to the game screen
        buttonPlay = new TextButton("Play!", skin);
        buttonPlay.setPosition(Gdx.graphics.getWidth() / 2 - buttonPlay.getWidth() / 2, 50);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                names = new String[Integer.parseInt(numberOfPlayers.getSelected())];
                characterNames = new String[Integer.parseInt(numberOfPlayers.getSelected())];
                iscomputerPlayer = new boolean[Integer.parseInt(numberOfPlayers.getSelected())];
                names[0] = playerOneName.getText();
                characterNames[0] = playerOneCharacter.getSelected();
                names[1] = playerTwoName.getText();
                characterNames[1] = playerTwoCharacter.getSelected();
                names[2] = playerThreeName.getText();
                characterNames[2] = playerThreeCharacter.getSelected();
                if (numberOfPlayers.getSelected().equals("4")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerFourCharacter.getSelected();
                }else if (numberOfPlayers.getSelected().equals("5")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerFourCharacter.getSelected();
                    names[4] = playerFiveName.getText();
                    characterNames[4] = playerFiveCharacter.getSelected();
                }else if (numberOfPlayers.getSelected().equals("6")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerFourCharacter.getSelected();
                    names[4] = playerFiveName.getText();
                    characterNames[4] = playerFiveCharacter.getSelected();
                    names[5] = playerSixName.getText();
                    characterNames[5] = playerSixCharacter.getSelected();
                }

                for (int i = 0; i < Integer.parseInt(numberOfPlayers.getSelected()); i++) {
                    if (computerPlayerBoxes[i].getSelected().equals("Person")) {
                        iscomputerPlayer[i] = false;
                    }else {
                        iscomputerPlayer[i] = true;
                    }
                }

                gameKeeper = new GameKeeper(Integer.parseInt(numberOfPlayers.getSelected()), names, iscomputerPlayer, characterNames);
                game.setScreen(new GameScreen(game, gameKeeper));
            }
        });
        //Menu button to return to the Menu
        buttonMenu = new TextButton("Menu", skin, "small");
        buttonMenu.setPosition(Gdx.graphics.getWidth() - buttonMenu.getWidth(), 0);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        //TODO Add sprites to show which character they have selected, and the current strength of that character
        //TODO Might be easy by adding CharacterType objects to the screen???


        //Adding items to table for positioning
        table.add(playerOneName);
        table.getCell(playerOneName).width(265);
        table.getCell(playerOneName).spaceBottom(25);
        table.getCell(playerOneName).spaceRight(35);
        table.add(playerOneCharacter);
        table.getCell(playerOneCharacter).spaceBottom(25);
        table.getCell(playerOneCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[0]);
        table.getCell(computerPlayerBoxes[0]).spaceBottom(25);

        table.row();

        table.add(playerTwoName);
        table.getCell(playerTwoName).width(265);
        table.getCell(playerTwoName).spaceBottom(25);
        table.getCell(playerTwoName).spaceRight(35);
        table.add(playerTwoCharacter);
        table.getCell(playerTwoCharacter).spaceBottom(25);
        table.getCell(playerTwoCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[1]);
        table.getCell(computerPlayerBoxes[1]).spaceBottom(25);

        table.row();

        table.add(playerThreeName);
        table.getCell(playerThreeName).width(265);
        table.getCell(playerThreeName).spaceBottom(25);
        table.getCell(playerThreeName).spaceRight(35);
        table.add(playerThreeCharacter);
        table.getCell(playerThreeCharacter).spaceBottom(25);
        table.getCell(playerThreeCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[2]);
        table.getCell(computerPlayerBoxes[2]).spaceBottom(25);

        table.row();

        table.add(playerFourName);
        table.getCell(playerFourName).width(265);
        table.getCell(playerFourName).spaceBottom(25);
        table.getCell(playerFourName).spaceRight(35);
        table.add(playerFourCharacter);
        table.getCell(playerFourCharacter).spaceBottom(25);
        table.getCell(playerFourCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[3]);
        table.getCell(computerPlayerBoxes[3]).spaceBottom(25);

        table.row();

        table.add(playerFiveName);
        table.getCell(playerFiveName).width(265);
        table.getCell(playerFiveName).spaceBottom(25);
        table.getCell(playerFiveName).spaceRight(35);
        table.add(playerFiveCharacter);
        table.getCell(playerFiveCharacter).spaceBottom(25);
        table.getCell(playerFiveCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[4]);
        table.getCell(computerPlayerBoxes[4]).spaceBottom(25);

        table.row();

        table.add(playerSixName);
        table.getCell(playerSixName).width(265);
        table.getCell(playerSixName).spaceRight(35);
        table.add(playerSixCharacter);
        table.getCell(playerSixCharacter).spaceRight(35);
        table.add(computerPlayerBoxes[5]);
        table.getCell(computerPlayerBoxes[5]).spaceBottom(25);

        stage.addActor(table);

        stage.addActor(numberOfPlayersText);
        stage.addActor(numberOfPlayers);
        stage.addActor(buttonPlay);
        stage.addActor(buttonMenu);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
