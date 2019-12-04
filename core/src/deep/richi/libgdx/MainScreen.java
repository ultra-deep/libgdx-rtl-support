package deep.richi.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import deep.richi.libgdx.rtl.RtlController;
import deep.richi.libgdx.rtl.RtlFreeTypeFontGenerator;


/***
 * @author Richi on 10/28/20.
 */
public class MainScreen extends BaseScreen {

    private static final String EINSTEIN_TEXT = "اگر نتوانی چیزی را به سادگی برای کسی توضیح دهی، خودت هم آن چیز را به خوبی درک نکرده ای.\n (اینشتین)\n\n با آروزی پیشرفت حکومتها و کشورهای علمی مثل آمریکا و کشورهای اروپایی و ...";
    private static final String WRAP_FULL_TEST_SEQUENCE = "فرمول آب H2O است! آموزش زبان انگلیسی، مثل Hi, How are you آسان است. عمر سیاره (زمین) 4.6 ملیارد {سال} است. حرکت و شتاب باعث خم شدن فضا-زمان یعنی (space-time) میشوند.";
    private BitmapFont rtlBitmapFont;
    //==============================================================
    // METHODS
    //==============================================================
    public MainScreen() {
        rtlBitmapFont = this.createRtlBitmapFont();
    }
    @Override public void show() {
        super.show();

        Label einsteinLabel = new Label(EINSTEIN_TEXT, new Label.LabelStyle(rtlBitmapFont, Color.GOLD));
        einsteinLabel.setWrap(true);
        einsteinLabel.setWidth(900);
        einsteinLabel.setX(50);
        einsteinLabel.setY(450);
        stage.addActor(einsteinLabel);

        Label fullTestLabel = new Label(WRAP_FULL_TEST_SEQUENCE, new Label.LabelStyle(rtlBitmapFont, Color.WHITE));
        fullTestLabel.setWrap(true);
        fullTestLabel.setWidth(900);
        fullTestLabel.setX(100);
        fullTestLabel.setY(200);
        stage.addActor(fullTestLabel);

        TextArea.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = createRtlBitmapFontForTextField();
        style.fontColor = Color.BLACK;
        style.disabledFontColor = Color.LIGHT_GRAY;
        style.messageFontColor = Color.LIGHT_GRAY;
        TextField textField = new TextField("", style);
        textField.setMessageText("نام خود را وارد کنید...");
        textField.setDebug(true); // showing debug lines
        textField.setSize(SCREEN_WIDTH - 100, 80);
        changeBackgroundForFor(textField , Color.WHITE);
        textField.setAlignment(Align.center );
        textField.setPosition(20, 20);
        stage.addActor(textField);

    }
    //==============================================================
    // Privates
    //==============================================================
    private void changeBackgroundForFor(TextField textField, Color color) {
        Pixmap labelColor = new Pixmap((int) textField.getWidth(),(int) textField.getHeight(), Pixmap.Format.RGB888);
        labelColor.setColor(color);
        labelColor.fill();
        textField.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
    }
    private BitmapFont createRtlBitmapFont() {
        RtlFreeTypeFontGenerator generator = new RtlFreeTypeFontGenerator(Gdx.files.internal("fonts/Sarbaz.ttf"));
//        RtlFreeTypeFontGenerator generator = new RtlFreeTypeFontGenerator(Gdx.files.internal("fonts/IRANSansMobile_Medium.ttf"));
//        RtlFreeTypeFontGenerator generator = new RtlFreeTypeFontGenerator(Gdx.files.internal("fonts/Parvin-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters += RtlController.getInstance().getAllRtlChars();
        param.size = 40;
        param.color = Color.WHITE;
        param.borderColor = Color.RED;
        param.borderStraight = true;
        param.borderWidth = 1f;
        param.shadowColor = Color.DARK_GRAY;
        param.shadowOffsetX = +5;
        param.shadowOffsetY = +5;
        param.minFilter = Texture.TextureFilter.Nearest;
        param.magFilter = Texture.TextureFilter.Nearest;
        return generator.generateRtlFont(param);
    }
    private BitmapFont createRtlBitmapFontForTextField() {
        RtlFreeTypeFontGenerator generator = new RtlFreeTypeFontGenerator(Gdx.files.internal("fonts/IRANSansMobile_Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters += RtlController.getInstance().getAllRtlChars();
        param.size = 40;
        param.color = Color.WHITE;
        return generator.generateRtlFont(param);
    }
}
