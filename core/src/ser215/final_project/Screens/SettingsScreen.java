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
import ser215.final_project.HolidayHustle;

import java.util.Random;

/**
 * Created by Brian on 11/30/2015.
 */
public class SettingsScreen implements Screen {
    private HolidayHustle game;
    private Stage stage;
    private Skin skin;
    private Table table;
    private TextButton buttonMenu;
    private TextField gameHeading;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public SettingsScreen(HolidayHustle game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        //Clears the screen and makes it white
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Updates the camera and renderer
        camera.update();
        renderer.setView(camera);
        renderer.render();

        //Draws items from stage on screen and allows them to "act"
        stage.act(delta);
        stage.draw();
    }
    //gameKeeper = new GameKeeper(Integer.parseInt(numberOfPlayers.getSelected()), names, iscomputerPlayer, characterNames);

    @Override
    public void resize(int width, int height) {
        //Adjusts the camera to show the background map correctly
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show() {
        //Randomly selects a map to render in the background
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

        //Renders the background map
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(960 / 2, 960 / 2, 0);

        //Creates stage - allows things (buttons, SelectBoxes, TextFields, etc.) to be drawn on screen
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Creates table for easy formatting of on-screen items
        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //Creating Buttons
        buttonMenu = new TextButton("MENU", skin);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });
        buttonMenu.pad(5);

        //Creating heading
        gameHeading = new TextField("Holiday Hustle", skin, "large");
        gameHeading.setDisabled(true);
        gameHeading.setWidth(435);
        gameHeading.setPosition(Gdx.graphics.getWidth() / 2 - gameHeading.getWidth() / 2, 810);

        //Adding elements to the table, and the table to the stage
        table.add(gameHeading);
        table.getCell(gameHeading).minWidth(675);
        table.getCell(gameHeading).spaceBottom(100);
        table.row();
        table.add(buttonMenu);
        table.top();
        stage.addActor(table);
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
        skin.dispose();
        renderer.dispose();
        map.dispose();
        System.out.println("Doing Stuff");
    }
}
