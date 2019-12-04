package deep.richi.libgdx;

import com.badlogic.gdx.Game;


/***
 * @author Richi on 10/28/19.
 * Launch the game and show scenes
 */
public class GameLauncher extends Game {
    @Override public void create() {
        super.setScreen(new MainScreen());
    }
    @Override public void dispose() {
        super.getScreen().dispose();
    }
}
