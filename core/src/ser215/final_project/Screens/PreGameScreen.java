package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ser215.final_project.HolidayHustle;

/**
 * Created by Brian on 11/30/2015.
 */
public class PreGameScreen implements Screen {
    private HolidayHustle game;
    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private Skin skin;
    private SelectBox numberOfPlayers;

    public PreGameScreen(HolidayHustle game) {
        this.game = game;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Add stuff here

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        //atlas = new TextureAtlas("")

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
