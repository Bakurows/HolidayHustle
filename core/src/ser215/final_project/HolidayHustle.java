package ser215.final_project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ser215.final_project.Screens.GameScreen;
import ser215.final_project.Screens.MenuScreen;

public class HolidayHustle extends Game{

    public SpriteBatch batch;

	@Override
	public void create () {
        batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}


}
