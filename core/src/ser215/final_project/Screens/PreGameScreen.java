package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private boolean[] isComputerPlayer;
    private Texture[] charTextures;
    private Image[] charImages;
    private Stage stage;
    private Table table;
    private Skin skin;
    private SelectBox<String> numberOfPlayers;
    private SelectBox<String>[] playerCharacters;
    private SelectBox<String>[] computerPlayerBoxes;
    private TextField numberOfPlayersText;
    private TextField playerOneName, playerTwoName, playerThreeName, playerFourName, playerFiveName, playerSixName;
    private TextButton buttonPlay, buttonMenu;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    //Single-arg constructor
    public PreGameScreen(HolidayHustle game) {
        this.game = game;
        numPlayers = new String[]{"3", "4", "5", "6"};
        characterChoices = new String[] {"Frosty", "Jack", "Bugs", "Piper"};
        computerPlayers = new String [] {"Person", "Computer"};
        playerCharacters = new SelectBox[6];
        computerPlayerBoxes = new SelectBox[6];
    }


    @Override
    public void render(float delta) {
        //Clears the screen and makes it white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Updates the camera and renderer
        camera.update();
        renderer.setView(camera);
        renderer.render();

        //Updates all TextField and SelectBox objects (Disables unneeded ones, Resets fields to standard names, resets selections to slot one - Only if number of players box is decreased)
        if (numberOfPlayers.getSelected().equals("3")) {
            playerFourName.setDisabled(true);
            playerFiveName.setDisabled(true);
            playerSixName.setDisabled(true);
            playerFourName.setText("Player Four Name");
            playerFiveName.setText("Player Five Name");
            playerSixName.setText("Player Six Name");
            playerCharacters[3].setDisabled(true);
            playerCharacters[4].setDisabled(true);
            playerCharacters[5].setDisabled(true);
            playerCharacters[3].setSelectedIndex(0);
            playerCharacters[4].setSelectedIndex(0);
            playerCharacters[5].setSelectedIndex(0);
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
            playerCharacters[3].setDisabled(false);
            playerCharacters[4].setDisabled(true);
            playerCharacters[5].setDisabled(true);
            playerCharacters[4].setSelectedIndex(0);
            playerCharacters[5].setSelectedIndex(0);
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
            playerCharacters[3].setDisabled(false);
            playerCharacters[4].setDisabled(false);
            playerCharacters[5].setDisabled(true);
            playerCharacters[5].setSelectedIndex(0);
            computerPlayerBoxes[3].setDisabled(false);
            computerPlayerBoxes[4].setDisabled(false);
            computerPlayerBoxes[5].setDisabled(true);
            computerPlayerBoxes[5].setSelectedIndex(0);
        }else if(numberOfPlayers.getSelected().equals("6")){
            playerFourName.setDisabled(false);
            playerFiveName.setDisabled(false);
            playerSixName.setDisabled(false);
            playerCharacters[3].setDisabled(false);
            playerCharacters[4].setDisabled(false);
            playerCharacters[5].setDisabled(false);
            computerPlayerBoxes[3].setDisabled(false);
            computerPlayerBoxes[4].setDisabled(false);
            computerPlayerBoxes[5].setDisabled(false);
        }

        //Updates the image showing the currently selected characters for each player
        for (int i = 0; i < 6; i++) {
            if (playerCharacters[i].getSelected().equals("Frosty")) {
                charTextures[i] = new Texture(Gdx.files.internal("Characters/Frosty_Medium.png"));
                charImages[i].setDrawable(new SpriteDrawable(new Sprite(charTextures[i])));
            }else if (playerCharacters[i].getSelected().equals("Jack")) {
                charTextures[i] = new Texture(Gdx.files.internal("Characters/Jack_Medium.png"));
                charImages[i].setDrawable(new SpriteDrawable(new Sprite(charTextures[i])));
            }else if (playerCharacters[i].getSelected().equals("Bugs")) {
                charTextures[i] = new Texture(Gdx.files.internal("Characters/Bugs_Medium.png"));
                charImages[i].setDrawable(new SpriteDrawable(new Sprite(charTextures[i])));
            }else if (playerCharacters[i].getSelected().equals("Piper")) {
                charTextures[i] = new Texture(Gdx.files.internal("Characters/Piper_Medium.png"));
                charImages[i].setDrawable(new SpriteDrawable(new Sprite(charTextures[i])));
            }
        }

        //Draws items from stage on screen
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //Adjusts the camera to show the background map correctly
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show() {
        //Randomly selects a map to render
        switch (MathUtils.random(3)) {
            case 0: map = new TmxMapLoader().load("map_summer.tmx");
                break;
            case 1: map = new TmxMapLoader().load("map_fall.tmx");
                break;
            case 2: map = new TmxMapLoader().load("map_winter.tmx");
                break;
            case 3: map = new TmxMapLoader().load("map_spring.tmx");
                break;
        }
        //Renders the map
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);

        //Creates stage - allows things (buttons, SelectBoxes, TextFields, etc.) to be drawn on screen
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Creating the table for formatting on-screen items
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.left();
        table.pad(0,50,0,0);

        //SelectBox to enter the number of players
        numberOfPlayers = new SelectBox<String>(skin);
        numberOfPlayers.setItems(numPlayers);
        numberOfPlayers.setWidth(110);
        numberOfPlayers.setPosition(Gdx.graphics.getWidth() / 2 - numberOfPlayers.getWidth() / 2, 810);

        //TextField asking the user how many players are playing
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

        //Creates Select Boxes for players to choose their characters.
        //One
        playerCharacters[0] = new SelectBox<String>(skin);
        playerCharacters[0].setItems(characterChoices);
        playerCharacters[0].setWidth(90);
        //Two
        playerCharacters[1] = new SelectBox<String>(skin);
        playerCharacters[1].setItems(characterChoices);
        playerCharacters[1].setWidth(90);
        //Three
        playerCharacters[2] = new SelectBox<String>(skin);
        playerCharacters[2].setItems(characterChoices);
        playerCharacters[2].setWidth(90);
        //Four
        playerCharacters[3] = new SelectBox<String>(skin);
        playerCharacters[3].setItems(characterChoices);
        playerCharacters[3].setWidth(90);
        //Five
        playerCharacters[4] = new SelectBox<String>(skin);
        playerCharacters[4].setItems(characterChoices);
        playerCharacters[4].setWidth(90);
        //Six
        playerCharacters[5] = new SelectBox<String>(skin);
        playerCharacters[5].setItems(characterChoices);
        playerCharacters[5].setWidth(90);

        //Creates SelectBoxes for whether each player is computer or person
        //One
        computerPlayerBoxes[0] = new SelectBox<String>(skin);
        computerPlayerBoxes[0].setItems(computerPlayers);
        computerPlayerBoxes[0].setWidth(90);
        //Two
        computerPlayerBoxes[1] = new SelectBox<String>(skin);
        computerPlayerBoxes[1].setItems(computerPlayers);
        computerPlayerBoxes[1].setWidth(90);
        //Three
        computerPlayerBoxes[2] = new SelectBox<String>(skin);
        computerPlayerBoxes[2].setItems(computerPlayers);
        computerPlayerBoxes[2].setWidth(90);
        //Four
        computerPlayerBoxes[3] = new SelectBox<String>(skin);
        computerPlayerBoxes[3].setItems(computerPlayers);
        computerPlayerBoxes[3].setWidth(90);
        //Five
        computerPlayerBoxes[4] = new SelectBox<String>(skin);
        computerPlayerBoxes[4].setItems(computerPlayers);
        computerPlayerBoxes[4].setWidth(90);
        //Six
        computerPlayerBoxes[5] = new SelectBox<String>(skin);
        computerPlayerBoxes[5].setItems(computerPlayers);
        computerPlayerBoxes[5].setWidth(90);

        //Play button to go to the game screen
        buttonPlay = new TextButton("Play!", skin);
        buttonPlay.setPosition(Gdx.graphics.getWidth() / 2 - buttonPlay.getWidth() / 2, 50);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Creates an array of the player names
                names = new String[Integer.parseInt(numberOfPlayers.getSelected())];
                //Creates an array of the character of each player
                characterNames = new String[Integer.parseInt(numberOfPlayers.getSelected())];
                //Creates an array of whether each player is a real person or a computer player
                isComputerPlayer = new boolean[Integer.parseInt(numberOfPlayers.getSelected())];

                //Assigns all values for the above three arrays
                names[0] = playerOneName.getText();
                characterNames[0] = playerCharacters[0].getSelected();
                names[1] = playerTwoName.getText();
                characterNames[1] = playerCharacters[1].getSelected();
                names[2] = playerThreeName.getText();
                characterNames[2] = playerCharacters[2].getSelected();
                if (numberOfPlayers.getSelected().equals("4")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerCharacters[3].getSelected();
                }else if (numberOfPlayers.getSelected().equals("5")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerCharacters[3].getSelected();
                    names[4] = playerFiveName.getText();
                    characterNames[4] = playerCharacters[4].getSelected();
                }else if (numberOfPlayers.getSelected().equals("6")) {
                    names[3] = playerFourName.getText();
                    characterNames[3] = playerCharacters[3].getSelected();
                    names[4] = playerFiveName.getText();
                    characterNames[4] = playerCharacters[4].getSelected();
                    names[5] = playerSixName.getText();
                    characterNames[5] = playerCharacters[5].getSelected();
                }
                for (int i = 0; i < Integer.parseInt(numberOfPlayers.getSelected()); i++) {
                    if (computerPlayerBoxes[i].getSelected().equals("Person")) {
                        isComputerPlayer[i] = false;
                    }else {
                        isComputerPlayer[i] = true;
                    }
                }

                //Creates the GameKeeper and passes it to the GameScreen constructor
                gameKeeper = new GameKeeper(Integer.parseInt(numberOfPlayers.getSelected()), names, isComputerPlayer, characterNames);
                game.setScreen(new GameScreen(game, gameKeeper));
                dispose();
            }
        });
        //Menu button to return to the Menu
        buttonMenu = new TextButton("Menu", skin, "small");
        buttonMenu.setPosition(Gdx.graphics.getWidth() - buttonMenu.getWidth(), 0);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        //Creates images to show which character a player has selected
        charTextures = new Texture[6];
        charImages = new Image[6];
        for (int i = 0; i < 6; i++) {
            charTextures[i] = new Texture(Gdx.files.internal("Characters/Frosty_Medium.png"));
            charImages[i] = new Image(charTextures[i]);
        }

        //Adding items to table for positioning
        //Player on stuff
        table.add(playerOneName);
        table.getCell(playerOneName).width(265);
        table.getCell(playerOneName).spaceBottom(25);
        table.getCell(playerOneName).spaceRight(35);
        table.add(playerCharacters[0]);
        table.getCell(playerCharacters[0]).spaceBottom(25);
        table.getCell(playerCharacters[0]).spaceRight(35);
        table.add(computerPlayerBoxes[0]);
        table.getCell(computerPlayerBoxes[0]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[0]).spaceRight(35);
        table.add(charImages[0]);

        table.row();
        //Player two stuff
        table.add(playerTwoName);
        table.getCell(playerTwoName).width(265);
        table.getCell(playerTwoName).spaceBottom(25);
        table.getCell(playerTwoName).spaceRight(35);
        table.add(playerCharacters[1]);
        table.getCell(playerCharacters[1]).spaceBottom(25);
        table.getCell(playerCharacters[1]).spaceRight(35);
        table.add(computerPlayerBoxes[1]);
        table.getCell(computerPlayerBoxes[1]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[1]).spaceRight(35);
        table.add(charImages[1]);

        table.row();
        //Player three stuff
        table.add(playerThreeName);
        table.getCell(playerThreeName).width(265);
        table.getCell(playerThreeName).spaceBottom(25);
        table.getCell(playerThreeName).spaceRight(35);
        table.add(playerCharacters[2]);
        table.getCell(playerCharacters[2]).spaceBottom(25);
        table.getCell(playerCharacters[2]).spaceRight(35);
        table.add(computerPlayerBoxes[2]);
        table.getCell(computerPlayerBoxes[2]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[2]).spaceRight(35);
        table.add(charImages[2]);

        table.row();
        //Player four stuff
        table.add(playerFourName);
        table.getCell(playerFourName).width(265);
        table.getCell(playerFourName).spaceBottom(25);
        table.getCell(playerFourName).spaceRight(35);
        table.add(playerCharacters[3]);
        table.getCell(playerCharacters[3]).spaceBottom(25);
        table.getCell(playerCharacters[3]).spaceRight(35);
        table.add(computerPlayerBoxes[3]);
        table.getCell(computerPlayerBoxes[3]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[3]).spaceRight(35);
        table.add(charImages[3]);

        table.row();
        //Player five stuff
        table.add(playerFiveName);
        table.getCell(playerFiveName).width(265);
        table.getCell(playerFiveName).spaceBottom(25);
        table.getCell(playerFiveName).spaceRight(35);
        table.add(playerCharacters[4]);
        table.getCell(playerCharacters[4]).spaceBottom(25);
        table.getCell(playerCharacters[4]).spaceRight(35);
        table.add(computerPlayerBoxes[4]);
        table.getCell(computerPlayerBoxes[4]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[4]).spaceRight(35);
        table.add(charImages[4]);

        table.row();
        //Player six stuff
        table.add(playerSixName);
        table.getCell(playerSixName).width(265);
        table.getCell(playerSixName).spaceRight(35);
        table.add(playerCharacters[5]);
        table.getCell(playerCharacters[5]).spaceRight(35);
        table.add(computerPlayerBoxes[5]);
        table.getCell(computerPlayerBoxes[5]).spaceBottom(25);
        table.getCell(computerPlayerBoxes[5]).spaceRight(35);
        table.add(charImages[5]);

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
        stage.dispose();
        for (int i = 0; i < 6; i++) {
            charTextures[i].dispose();
        }
        map.dispose();
        renderer.dispose();
        skin.dispose();
    }
}
