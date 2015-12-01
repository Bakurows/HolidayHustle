package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ser215.final_project.HolidayHustle;

import java.util.Random;

/**
 * Created by Brian on 11/30/2015.
 */
public class SettingsScreen implements Screen {
    private HolidayHustle game;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonMenu;
    private BitmapFont fontStandard, fontLarge;
    private Label heading;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public SettingsScreen(HolidayHustle game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Add stuff here

        camera.update();
        renderer.setView(camera);
        renderer.render();

        //stage.setDebugAll(true);

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
        //Add stuff here

        //Renders the background map
        //Randomly selects a map
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

        atlas = new TextureAtlas("buttons/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //Creates font
        fontStandard = new BitmapFont(Gdx.files.internal("fonts/RegularFont.fnt"), false);

        fontLarge = new BitmapFont(Gdx.files.internal("fonts/LargeFont.fnt"), false);

        //Creating Buttons
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = - 1;
        textButtonStyle.font = fontStandard;
        textButtonStyle.fontColor = Color.BLACK;

        buttonMenu = new TextButton("MENU", textButtonStyle);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        buttonMenu.pad(5);

        //Creating heading
        Label.LabelStyle headingStyle = new Label.LabelStyle(fontLarge, Color.WHITE);
        heading = new Label("Settings", headingStyle);

        //Adding stuff to table - to draw on screen
        table.add(heading);
        table.getCell(heading).spaceBottom(100);
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
        atlas.dispose();
        skin.dispose();
        fontStandard.dispose();
        fontLarge.dispose();
    }
}
