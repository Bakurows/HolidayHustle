package ser215.final_project.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ser215.final_project.HolidayHustle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new HolidayHustle(), config);
        config.width = 960;
        config.height = 960;
        config.resizable = false;
	}
}
