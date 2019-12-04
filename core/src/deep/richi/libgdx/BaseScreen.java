package deep.richi.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


/**
 * @author Richi on 10/28/20.
 */
public abstract class BaseScreen extends ScreenAdapter {
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 768;
    protected Stage stage;
    //==============================================================
    // Overrides
    //==============================================================v
    @Override public void show() {
        stage = new Stage(new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);
    }
    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.4f, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
    @Override public void dispose() {
        stage.dispose();
    }
}
