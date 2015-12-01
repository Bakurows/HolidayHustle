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
public class GameScreen implements Screen, InputProcessor {
    private HolidayHustle game;
    private GameInstance gameKeeper;
    private Player demoPlayer;  //TODO Remove this
    private SpriteBatch sb;
    private Sprite sprite;
    //For the game map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private LocalDate tempDate;
    //For buttons
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonRoll, buttonExit;
    private BitmapFont fontStandard, fontLarge, fontBold;


    public GameScreen(HolidayHustle game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        sb.begin();
        sprite.setPosition(GameInstance.getCoords(demoPlayer.getBoardLocation())[0],GameInstance.getCoords(demoPlayer.getBoardLocation())[1]);
        //sprite.draw(sb);

        gameKeeper.getPlayersList()[0].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[0].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[0].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[0].getCharacter().getCharacterSprite().draw(sb);

        gameKeeper.getPlayersList()[1].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[1].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[1].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[1].getCharacter().getCharacterSprite().draw(sb);

        gameKeeper.getPlayersList()[2].getCharacter().getCharacterSprite().setPosition(GameInstance.getCoords(gameKeeper.getPlayersList()[2].getBoardLocation())[0], GameInstance.getCoords(gameKeeper.getPlayersList()[2].getBoardLocation())[1]);
        gameKeeper.getPlayersList()[2].getCharacter().getCharacterSprite().draw(sb);
        sb.end();

        //stage.setDebugAll(true);
        stage.act(delta);
        stage.draw();

        sb.begin();
        fontBold.setColor(Color.BLACK);
        fontBold.draw(sb, gameKeeper.getPlayersList()[0].getName() + "'s Turn!" + gameKeeper.getCurrentPlayerTurn(), 34, Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show() {

        //Selects the map based on current season - not exact, uses approximated day
        if (LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 9, 22)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 12, 21))) {
            map = new TmxMapLoader().load("map_fall.tmx");
        }else if(LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 12, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue() + 1, 1, 1))) {
            map = new TmxMapLoader().load("map_winter.tmx");
        }else if(LocalDate.now().isAfter(tempDate.of(Year.now().getValue() - 1, 12, 31)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 3, 20))) {
            map = new TmxMapLoader().load("map_winter.tmx");
        }else if(LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 3, 19)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 6, 21))) {
            map = new TmxMapLoader().load("map_spring.tmx");
        }else if(LocalDate.now().isAfter(tempDate.of(Year.now().getValue(), 6, 20)) && LocalDate.now().isBefore(tempDate.of(Year.now().getValue(), 9, 23))) {
            map = new TmxMapLoader().load("map_summer.tmx");
        }


        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);

        Gdx.input.setInputProcessor(this);

        sb = new SpriteBatch();
        demoPlayer = new Player();
        gameKeeper = new GameInstance();
        sprite = new Sprite(demoPlayer.getCharacter().getCharacterImage());

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("buttons/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //Creates font
        fontStandard = new BitmapFont(Gdx.files.internal("fonts/RegularFont.fnt"), false);
        fontLarge = new BitmapFont(Gdx.files.internal("fonts/LargeFont.fnt"), false);
        fontBold = new BitmapFont(Gdx.files.internal("fonts/BoldFont.fnt"), false);

        //Creating Buttons
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = - 1;
        textButtonStyle.font = fontStandard;
        textButtonStyle.fontColor = Color.BLACK;

        buttonRoll = new TextButton("ROLL", textButtonStyle);
        buttonRoll.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameKeeper.nextTurn();
            }
        });
        buttonRoll.pad(1);
        //buttonRoll.getLabel().setFontScale(0.75f, 0.75f);

        buttonExit = new TextButton("MAIN\nMENU", textButtonStyle);
        //buttonExit = new TextButton("EXIT", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        buttonExit.pad(1);
        buttonExit.getLabel().setFontScale(0.455f, 0.455f);
        //buttonExit.getLabel().setFontScale(0.5f, 0.5f);

        //Adding stuff to table - to draw on screen
        table.add(buttonRoll);
        table.getCell(buttonRoll).spaceBottom(5);
        table.row();
        table.add(buttonExit);
        table.getCell(buttonExit).spaceTop(821);
        //table.getCell(buttonExit).spaceTop(846);
        table.getCell(buttonExit).right();
        table.top();
        table.right();
        stage.addActor(table);
    }

    @Override
    public void hide() {
        //Add stuff here
    }

    @Override
    public void pause() {
        //Add stuff here
    }

    @Override
    public void resume() {
        //Add stuff here
    }

    @Override
    public void dispose() {
        //Add stuff here
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        fontStandard.dispose();
        fontLarge.dispose();
        sb.dispose();
    }

    @Override public boolean keyDown(int keycode) {
        return false;
    }

    @Override public boolean keyUp(int keycode) {
        /*if(keycode == Input.Keys.NUM_1)
            demoPlayer.increaseBoardLocation(1);
        if(keycode == Input.Keys.NUM_2)
            demoPlayer.increaseBoardLocation(2);
        if(keycode == Input.Keys.NUM_3)
            demoPlayer.increaseBoardLocation(3);
        if(keycode == Input.Keys.NUM_4)
            demoPlayer.increaseBoardLocation(4);
        if(keycode == Input.Keys.NUM_5)
            demoPlayer.increaseBoardLocation(5);
        if(keycode == Input.Keys.NUM_6)
            demoPlayer.increaseBoardLocation(6);*/


        if(keycode == Input.Keys.ENTER)
            gameKeeper.nextTurn();

        if(keycode == Input.Keys.NUMPAD_1)
            gameKeeper.movePlayer(0,1);
        if(keycode == Input.Keys.NUMPAD_2)
            gameKeeper.movePlayer(0,2);
        if(keycode == Input.Keys.NUMPAD_3)
            gameKeeper.movePlayer(0,4);

        if(keycode == Input.Keys.NUMPAD_4)
            gameKeeper.movePlayer(1,1);
        if(keycode == Input.Keys.NUMPAD_5)
            gameKeeper.movePlayer(1,2);
        if(keycode == Input.Keys.NUMPAD_6)
            gameKeeper.movePlayer(1,4);

        if(keycode == Input.Keys.NUMPAD_7)
            gameKeeper.movePlayer(2,1);
        if(keycode == Input.Keys.NUMPAD_8)
            gameKeeper.movePlayer(2,2);
        if(keycode == Input.Keys.NUMPAD_9)
            gameKeeper.movePlayer(2,4);

        if(keycode == Input.Keys.NUM_1)
            gameKeeper.movePlayer(0,1);
        if(keycode == Input.Keys.NUM_2)
            gameKeeper.movePlayer(0,2);
        if(keycode == Input.Keys.NUM_3)
            gameKeeper.movePlayer(0,4);

        if(keycode == Input.Keys.NUM_4)
            gameKeeper.movePlayer(1,1);
        if(keycode == Input.Keys.NUM_5)
            gameKeeper.movePlayer(1,2);
        if(keycode == Input.Keys.NUM_6)
            gameKeeper.movePlayer(1,4);

        if(keycode == Input.Keys.NUM_7)
            gameKeeper.movePlayer(2,1);
        if(keycode == Input.Keys.NUM_8)
            gameKeeper.movePlayer(2,2);
        if(keycode == Input.Keys.NUM_9)
            gameKeeper.movePlayer(2,4);
        return false;
    }

    @Override public boolean keyTyped(char character) {

        return false;
    }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
        Vector3 position = camera.unproject(clickCoordinates);
        sprite.setPosition(position.x, position.y);
        return true;*/
        return false;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override public boolean scrolled(int amount) {
        return false;
    }
}
