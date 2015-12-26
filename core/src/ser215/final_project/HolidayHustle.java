package ser215.final_project;

import com.badlogic.gdx.Game;
import ser215.final_project.Screens.MenuScreen;

public class HolidayHustle extends Game{

	@Override
	public void create () {
		this.setScreen(new MenuScreen(this));
	}


}
