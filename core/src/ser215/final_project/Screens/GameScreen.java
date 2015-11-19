package ser215.final_project.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ser215.final_project.HolidayHustle;

/**
 * Created by Brian on 11/17/2015.
 */
public class GameScreen implements Screen {
    private HolidayHustle game;

    private Texture board;


    public GameScreen(HolidayHustle game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(board, 0, 0);
        game.batch.end();
        //Add stuff here


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        board = new Texture(Gdx.files.internal("board.jpg"));
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
}
