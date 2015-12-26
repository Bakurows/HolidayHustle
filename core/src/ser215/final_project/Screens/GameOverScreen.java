package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ser215.final_project.HolidayHustle;

/**
 * Created by Brian on 12/25/2015.
 */
public class GameOverScreen implements Screen {
    HolidayHustle game;
    private String winningPlayer;
    private TextField congratulation, winner;
    private TextButton buttonMenu;
    private Stage stage;
    private Skin skin;
    private Table table;
    //For the game map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    //Two-arg Constructor
    public GameOverScreen(HolidayHustle game, String winningPlayer) {
        this.game = game;
        this.winningPlayer = winningPlayer;
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
        //Selecting a random map
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

        //Creating the stage for actors to go on
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        //Creating tables for formatting on-screen items
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

        //Creating the Text fields displaying the winner and congrats.
        congratulation = new TextField("Congratulation, you have won!", skin, "regular");
        winner = new TextField(winningPlayer, skin, "regular");

        //Adding elements to the table, and the table to the stage
        table.add(congratulation);
        table.getCell(congratulation).width(700);
        table.row();
        table.add(winner);
        table.getCell(winner).width(265);
        table.getCell(winner).spaceBottom(100);
        table.row();
        table.add(buttonMenu);
        stage.addActor(table);
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
        renderer.dispose();
        map.dispose();
        skin.dispose();
    }
}
