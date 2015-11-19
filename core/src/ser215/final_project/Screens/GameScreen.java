package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ser215.final_project.GameInstance;
import ser215.final_project.HolidayHustle;
import ser215.final_project.Player;

/**
 * Created by Brian on 11/17/2015.
 */
public class GameScreen implements Screen, InputProcessor {
    private HolidayHustle game;

    private GameInstance gameKeeper;
    private Player demoPlayer;

    private SpriteBatch sb;
    private Sprite sprite;

    //private Texture board;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;


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

        //game.batch.begin();
        //game.batch.draw(board, 0, 0);
        //game.batch.end();
        //Add stuff here
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("map_fall.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2,0);

        Gdx.input.setInputProcessor(this);

        sb = new SpriteBatch();
        demoPlayer = new Player();
        gameKeeper = new GameInstance();
        sprite = new Sprite(demoPlayer.getCharacter().getCharacterImage());


        //board = new Texture(Gdx.files.internal("board.jpg"));
        //Add stuff here
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
    }

    @Override public boolean keyDown(int keycode) {
        return false;
    }

    @Override public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.NUM_1)
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
            demoPlayer.increaseBoardLocation(6);


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
