package ser215.final_project.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import ser215.final_project.GameInstance;
import ser215.final_project.HolidayHustle;
import ser215.final_project.Player;

/**
 * Created by Brian on 11/17/2015.
 */
public class MenuScreen implements Screen {
    private HolidayHustle game;

    public MenuScreen(HolidayHustle game) {
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




/*
    @Override
    public void render(float delta) {
        //Add stuff here
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
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
 */