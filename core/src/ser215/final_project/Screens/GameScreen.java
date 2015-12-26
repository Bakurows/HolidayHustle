package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ser215.final_project.GameKeeper;
import ser215.final_project.HolidayHustle;
import ser215.final_project.PlayingCard;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * Created by Brian on 11/17/2015.
 */
public class GameScreen implements Screen {
    private HolidayHustle game;
    private GameKeeper gameKeeper;
    //For the game map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private LocalDate tempDate;
    private String activeMap;
    //For all other on screen items
    private SpriteBatch sb;
    private Stage stage;
    private Skin skin;
    private Table table, tableTwo, tableThree;
    private TextButton buttonRoll, buttonMenu, buttonShowCards;
    private TextButton confirmTarget;
    private TextField currentPlayerTurn;
    private TextField whichPlayerPrompt;
    private SelectBox<String> playerNames;

    //Two-arg constructor
    public GameScreen(HolidayHustle game, GameKeeper theGameKeeper) {
        this.game = game;
        this.gameKeeper = theGameKeeper;
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

        //Draws all players on the game board
        sb.begin();
        for (int i = 0; i < gameKeeper.getNumberPlayers(); i++) {
            gameKeeper.getPlayersList()[i].getCharacter().getCharacterSprite().setPosition(GameKeeper.getCoords(gameKeeper.getPlayersList()[i].getBoardLocation(), activeMap)[0], GameKeeper.getCoords(gameKeeper.getPlayersList()[i].getBoardLocation(), activeMap)[1]);
            gameKeeper.getPlayersList()[i].getCharacter().getCharacterSprite().draw(sb);
        }
        sb.end();

        //Displays the current players turn
        currentPlayerTurn.setText(gameKeeper.getPlayersList()[gameKeeper.getCurrentPlayerTurn()].getName() + "'s Turn!");

        //Automatic Turn for computer Players - Doesn't use any cards
        if (gameKeeper.getPlayersList()[gameKeeper.getCurrentPlayerTurn()].isComputerPlayer()) {
            gameKeeper.nextTurn();
        }

        //Determines if someone has won. If so, goes to GameOver screen
        if (gameKeeper.hasWon()) {
            game.setScreen(new GameOverScreen(game, gameKeeper.getWinningPlayer()));
            dispose();
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
        //Selects the map based on current season - not exact, uses approximated day
        if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 9, 22)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 12, 21))) {
            map = new TmxMapLoader().load("map_fall.tmx");
            activeMap = "Fall";
            gameKeeper.setLastBoardLocation(115);
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 12, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue() + 1, 1, 1))) {
            map = new TmxMapLoader().load("map_winter.tmx");
            activeMap = "Winter";
            gameKeeper.setLastBoardLocation(103);
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue() - 1, 12, 31)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 3, 20))) {
            map = new TmxMapLoader().load("map_winter.tmx");
            activeMap = "Winter";
            gameKeeper.setLastBoardLocation(103);
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 3, 19)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 6, 21))) {
            map = new TmxMapLoader().load("map_spring.tmx");
            activeMap = "Spring";
            gameKeeper.setLastBoardLocation(115);
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 6, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 9, 23))) {
            map = new TmxMapLoader().load("map_summer.tmx");
            activeMap = "Summer";
            gameKeeper.setLastBoardLocation(115);
        }
        //Sets the active map in the GameKeeper object
        gameKeeper.setActiveMap(activeMap);

        //Renders the map
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);

        //SpriteBatch for rendering the character on screen
        sb = new SpriteBatch();

        //Creates stage - allows things (buttons, SelectBoxes, TextFields, etc.) to be drawn on screen
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Creates table for easy formatting of on-screen items
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Table for the cards
        tableTwo = new Table(skin);
        tableTwo.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Table for the selecting player to use cards on
        tableThree = new Table(skin);
        tableThree.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        //TextField displaying current players turn
        currentPlayerTurn = new TextField("", skin);
        currentPlayerTurn.setDisabled(true);
        currentPlayerTurn.setWidth(360);
        currentPlayerTurn.setPosition(Gdx.graphics.getWidth() / 2 - currentPlayerTurn.getWidth() / 2 + 155, Gdx.graphics.getHeight() - currentPlayerTurn.getHeight());

        //Creating Buttons
        buttonRoll = new TextButton("ROLL", skin);
        buttonRoll.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tableTwo.reset();
                tableThree.reset();
                //TODO need to find a way to reverse variable toggle below
                gameKeeper.nextTurn();
            }
        });
        buttonRoll.pad(1);
        //Button to show cards in a players hand
        buttonShowCards = new TextButton("SHOW CARDS", skin);
        buttonShowCards.addListener(new ClickListener() {
            boolean toggle = false; //Changes state when clicked

            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggle = !toggle;
                if (toggle) {
                    ArrayList<PlayingCard> hand = gameKeeper.getPlayerHand();
                    final int handSize = hand.size();
                    final ImageButton[] cardButtons = new ImageButton[hand.size()];
                    for (int i = 0; i < hand.size(); i++) {
                        final PlayingCard card = hand.get(i); // Get card from hand
                        cardButtons[i] = new ImageButton(card.getImage().getDrawable()); // Make button for card
                        cardButtons[i].addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                for (int i = 0; i < handSize; i++) {
                                    cardButtons[i].setTouchable(Touchable.disabled);
                                }
                                if (card.getType().equals("battle_win") || card.getType().equals("add_strength")) {
                                    gameKeeper.playCard(card, gameKeeper.getPlayersList()[gameKeeper.getCurrentPlayerTurn()]);
                                } else {
                                    //TextField asking the player which player to play the selected card on
                                    whichPlayerPrompt = new TextField("Which player do you want to use this card on?", skin);
                                    whichPlayerPrompt.setDisabled(true);
                                    whichPlayerPrompt.setWidth(500);

                                    //SelectBox for player to select player to play card on
                                    playerNames = new SelectBox<>(skin);
                                    playerNames.setItems(gameKeeper.getPlayerNames());
                                    playerNames.setWidth(265);

                                    //Button to play the selected card on the selected target
                                    confirmTarget = new TextButton("Play card on target!", skin);
                                    confirmTarget.setWidth(360);
                                    confirmTarget.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            gameKeeper.playCard(card, gameKeeper.getPlayerWithName(playerNames.getSelected()));
                                            tableThree.reset();
                                        }
                                    });
                                    tableThree.add(whichPlayerPrompt);
                                    tableThree.row();
                                    tableThree.add(playerNames);
                                    tableThree.row();
                                    tableThree.add(confirmTarget);
                                }
                            }
                        });
                        cardButtons[i].pad(1);
                        if (hand.size() <= 6) {
                            tableTwo.add(cardButtons[i]).size(card.getCardTexture().getWidth() / 2, card.getCardTexture().getHeight() / 2); // Basic implementation, change later
                            if ((i + 1) % 3 == 0)
                                tableTwo.row();
                        } else if (hand.size() > 6 && hand.size() <= 20) {
                            tableTwo.add(cardButtons[i]).size(card.getCardTexture().getWidth() / 3, card.getCardTexture().getHeight() / 3); // Basic implementation, change later
                            if ((i + 1) % 5 == 0)
                                tableTwo.row();
                        } else {
                            tableTwo.add(cardButtons[i]).size(card.getCardTexture().getWidth() / 4, card.getCardTexture().getHeight() / 4); // Basic implementation, change later
                            if ((i + 1) % 6 == 0)
                                tableTwo.row();
                        }
                    }
                } else {
                    // Removes cards display
                    tableTwo.reset();
                    tableThree.reset();
                }
            }
        });
        buttonShowCards.pad(1);
        //Menu button
        buttonMenu = new TextButton("MENU", skin);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });
        buttonMenu.pad(5);
        buttonMenu.getLabel().setFontScale(0.40f, 0.40f);

        //Adding elements to the table, and the table to the stage
        table.add(buttonShowCards);
        table.getCell(buttonShowCards).spaceRight(410);
        table.add(buttonRoll);
        table.row();
        table.add();
        table.add(buttonMenu);
        table.getCell(buttonMenu).spaceTop(846);
        table.getCell(buttonMenu).right();
        table.top();
        table.right();
        stage.addActor(tableTwo);
        stage.addActor(tableThree);
        stage.addActor(table);
        stage.addActor(currentPlayerTurn);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        sb.dispose();
        map.dispose();
        renderer.dispose();
    }
}