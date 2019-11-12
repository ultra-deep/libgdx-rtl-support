package deep.richi.libgdx.rtl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import deep.richi.libgdx.BaseScreen;
import deep.richi.libgdx.GameLauncher;


/***
 * @author Richi on 10/28/20.
 */
public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = BaseScreen.SCREEN_WIDTH;
        config.height = BaseScreen.SCREEN_HEIGHT;
        new LwjglApplication(new GameLauncher(), config);
    }
}
