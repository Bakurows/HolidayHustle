package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ser215.final_project.GameInstance;
import ser215.final_project.HolidayHustle;
import ser215.final_project.Player;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by Brian on 11/17/2015.
 */
public class GameScreen implements Screen {
    private HolidayHustle game;
    private GameInstance gameKeeper;
    private SpriteBatch sb;
    //For the game map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private LocalDate tempDate;
    //For buttons
    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton buttonRoll, buttonMenu, buttonShowCards;
    private BitmapFont fontBold;


    public GameScreen(HolidayHustle game) {
        this.game = game;
    }

    /*public GameScreen(HolidayHustle game) {
        this.game = game;

        gameKeeper = new GameInstance();
    }*/

    @Override
    public void render(float delta) {
        //Clears the screen and makes it white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Updates the camera and renderer
        camera.update();
        renderer.setView(camera);
        renderer.render();

        sb.begin();
        gameKeeper.getPlayersList()[0].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[0].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[0].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[0].getCharacter().getCharacterSprite().draw(sb);

        gameKeeper.getPlayersList()[1].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[1].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[1].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[1].getCharacter().getCharacterSprite().draw(sb);

        gameKeeper.getPlayersList()[2].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[2].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[2].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[2].getCharacter().getCharacterSprite().draw(sb);
        sb.end();

        //Displays the current players turn
        sb.begin();
        fontBold.setColor(Color.BLACK);
        fontBold.draw(sb, gameKeeper.getPlayersList()[0].getName() + "'s Turn!" + gameKeeper.getCurrentPlayerTurn(), 34, Gdx.graphics.getHeight());
        sb.end();

        //Draws items from stage on screen and allows them to "act"
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
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 12, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue() + 1, 1, 1))) {
            map = new TmxMapLoader().load("map_winter.tmx");
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue() - 1, 12, 31)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 3, 20))) {
            map = new TmxMapLoader().load("map_winter.tmx");
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 3, 19)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 6, 21))) {
            map = new TmxMapLoader().load("map_spring.tmx");
        } else if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 6, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 9, 23))) {
            map = new TmxMapLoader().load("map_summer.tmx");
        }

        //Renders the map
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);

        sb = new SpriteBatch();
        gameKeeper = new GameInstance();

        //Creates stage - allows things (buttons, SelectBoxes, TextFields, etc.) to be drawn on screen
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Creates table for easy formatting of on-screen items
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Creates font
        fontBold = new BitmapFont(Gdx.files.internal("fonts/BoldFont.fnt"), false);

        //Creating Buttons
        buttonRoll = new TextButton("ROLL", skin);
        buttonRoll.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameKeeper.nextTurn();
            }
        });
        buttonRoll.pad(1);
        
        buttonShowCards = new TextButton("SHOW CARDS", skin);
        buttonShowCards.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		//Show cards in current players hand
        	}
        });
        buttonShowCards.pad(1);

        buttonMenu = new TextButton("MENU", skin);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        buttonMenu.pad(5);
        buttonMenu.getLabel().setFontScale(0.40f, 0.40f);

        //Adding elements to the table, and the table to the stage
        table.add(buttonRoll);
        table.getCell(buttonRoll).spaceBottom(5);
        table.row();
        table.add(buttonShowCards);
        table.row();
        table.add(buttonMenu);
        table.getCell(buttonMenu).spaceTop(846);
        table.getCell(buttonMenu).right();
        table.top();
        table.right();
        stage.addActor(table);
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