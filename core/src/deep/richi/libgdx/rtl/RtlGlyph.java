package deep.richi.libgdx.rtl;

import com.badlogic.gdx.utils.CharArray;


/**
 * Persian/Arabic alphabet letters.
 * @author Richi on 10/28/19.
 */
enum RtlGlyph {

    //==============================================================
    // Persian and Arabic digits characters + some marks
    //==============================================================
    /** Primary char : ٠ */ PERSIAN_DIGIT__0(0x660),
    /** Primary char : ۱ */ PERSIAN_DIGIT__1(0x661),
    /** Primary char : ٢ */ PERSIAN_DIGIT__2(0x662),
    /** Primary char : ٣ */ PERSIAN_DIGIT__3(0x663),
    /** Primary char : ٤ */ PERSIAN_DIGIT__4(0x664),
    /** Primary char : ٥ */ PERSIAN_DIGIT__5(0x665),
    /** Primary char : ٦ */ PERSIAN_DIGIT__6(0x666),
    /** Primary char : ٧ */ PERSIAN_DIGIT__7(0x667),
    /** Primary char : ٨ */ PERSIAN_DIGIT__8(0x668),
    /** Primary char : ٩ */ PERSIAN_DIGIT__9(0x669),
    //----------------------------------------------------- Another persian/Arabic digits
    /** Primary char : ٠ */ PERSIAN_DIGIT_0(0x6f0),
    /** Primary char : ۱ */ PERSIAN_DIGIT_1(0x6f1),
    /** Primary char : ٢ */ PERSIAN_DIGIT_2(0x6f2),
    /** Primary char : ٣ */ PERSIAN_DIGIT_3(0x6f3),
    /** Primary char : ٤ */ PERSIAN_DIGIT_4(0x6f4),
    /** Primary char : ٥ */ PERSIAN_DIGIT_5(0x6f5),
    /** Primary char : ٦ */ PERSIAN_DIGIT_6(0x6f6),
    /** Primary char : ٧ */ PERSIAN_DIGIT_7(0x6f7),
    /** Primary char : ٨ */ PERSIAN_DIGIT_8(0x6f8),
    /** Primary char : ٩ */ PERSIAN_DIGIT_9(0x6f9),
    //==============================================================
    // Persian and Arabic signs characters
    //==============================================================
    /** Primary char : _ */ ARABIC_TATWEEL(0x640),
    /** Primary char : an */ ARABIC_FATHATAN____AN(0x64b),
    /** Primary char : on */ ARABIC_DAMMATAN____ON(0x64c),
    /** Primary char : en */ ARABIC_KASRATAN____EN(0x64d),
    /** Primary char : a  */ ARABIC_FATHA____A(0x64e),
    /** Primary char : o  */ ARABIC_DAMMA____O(0x64f),
    /** Primary char : e  */ ARABIC_KASRA____E(0x650),
    /** Primary char :    */ ARABIC_SHADDA____TASHDID(0x651),
    /** Primary char :    */ ARABIC_SUKUN(0x652),
    /** Primary char :    */ ARABIC_MADDAH_ABOVE(0x653),
    /** Primary char :    */ ARABIC_HAMZA_ABOVE(0x654),
    /** Primary char :    */ ARABIC_SUBSCRIP_ALEF____MINI_ALEF(0x656),
    /** Primary char : ٪ */ PERCENT(0x66a),
    /** Primary char : ٫ */ DECIMAL_POINT(0x66b),
    /** Primary char : ٬ */ APOSTROPHE(0x66c),
    /** Primary char : ٭ */ STAR(0x66d),
    /** Primary char : ؟ */ PERSIAN_QUESTION(0x61f),
    /** Primary char : ، */ COMMA(0x60c),
    /** Primary char : ‌ */ VIRTUAL_SPACE(0x200c),
    /** Primary char : ء ‌*/ HAMZE(0x621 , 0xfe80 ,0x621),
    //==============================================================
    // Persian and Arabic A characters (mixer/merger characters)
    //==============================================================
    /** Primary char : ا <br /> End char : ﺎ */ A__SIMPLE(0x627, 0xfe8e, 0xfe8d),
    /** Primary char : آ <br /> End char : ﺂ */ A__HAT_HOLDER(0x622, 0xfe82, 0xfe81),
    /** Primary char : أ <br /> End char : ﺄ */  A__HAMZE_TOP(0x623, 0xfe84, 0xfe83),
    /** Primary char : إ <br /> End char : ﺈ */ A__HAMZE_BOTTOM(0x625, 0xfe88 , 0xfe87),
    /** Primary char : إ <br /> End char : ﺈ */ A__TANVIN_TOP(0x671, 0xfb51, 0xfb50),
    //==============================================================
    // Persian and Arabic merged (LA) characters (mixed via Alef families)
    //==============================================================
    /** Primary char : ﻻ <br /> End char : ﻼ */ LA(0xfefb, 0xfefc , 0xfefb),
    /** Primary char : ﻵ <br /> End char : ﻶ */ LA__HAT_HOLDER(0xfef5, 0xfef6, 0xfef5),
    /** Primary char : ﻷ <br /> End char : ﻸ */ LA__HAMZE_TOP(0xfef7, 0xfef8,0xfef7),
    /** Primary char : ﻹ <br /> End char : ﻺ */ LA__HAMZE_BOTTOM(0xfef9, 0xfefa,0xfef9),
    //==============================================================
    // Persian and Arabic 2-characters (Primary & End)
    //==============================================================
    /** Primary char : ى <br /> End char : ﻰ <br /> like موسی عیسی</>*/ A__MAKASAR(0x649, 0xfef0, 0xfeef),
    /** Primary char : د <br /> End char : ﺪ */ DAL(0x62f, 0xfeaa, 0xfea9),
    /** Primary char : ذ <br /> End char : ﺬ */ ZAL(0x630, 0xfeac, 0xfeab),
    /** Primary char : ر <br /> End char : ﺮ */ R(0x631, 0xfeae , 0xfead),
    /** Primary char : ز <br /> End char : ﺰ */ Z(0x632, 0xfeb0 , 0xfeaf),
    /** Primary char : ژ <br /> End char : ﮋ */ JH(0x698, 0xfb8b , 0xfb8a),
    /** Primary char : و <br /> End char : ﻮ */ V(0x648, 0xfeee , 0xfeed),
    /** Primary char : ؤ <br /> End char : ﺆ */ V__HAMZE(0x624, 0xfe86 , 0xfe85),
    /** Primary char : ة <br /> End char : ﺔ */ T__FEMALE(0x629, 0xfe94 , 0xfe93),
    /** Primary char : ة <br /> End char : ﺔ */ H__WITH_YEH_ABOVE(0x6c0,0xfba5 , 0xfba4),
    //==============================================================
    // Persian and Arabic 4-characters (Primary & Start Center End)
    //==============================================================
    /** Primary char : ئ <br /> Start char : ﺋ <br /> Center char : ﺌ <br /> End char : ﺊ */ A__MOKASAR_HAMZE(0x626, 0xfe8b, 0xfe8c, 0xfe8a, 0xfe89),
    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Primary char : ب <br /> Start char : ﺑ <br /> Center char : ﺒ <br /> End char : ﺐ */ B(0x628, 0xfe91, 0xfe92, 0xfe90, 0xfe8f),
    /** Primary char : پ <br /> Start char : ﭘ <br /> Center char : ﭙ <br /> End char : ﭗ */ P(0x67e, 0xfb58, 0xfb59, 0xfb57, 0xfb56),
    /** Primary char : ت <br /> Start char : ﺗ <br /> Center char : ﺘ <br /> End char : ﺖ */ T__TOW_DOT(0x62a, 0xfe97, 0xfe98, 0xfe96, 0xfe95),
    /** Primary char : ث <br /> Start char : ﺛ <br /> Center char : ﺜ <br /> End char : ﺚ */ TH(0x62b, 0xfe9b, 0xfe9c, 0xfe9a , 0xfe99),
    /** Primary char : ج <br /> Start char : ﺟ <br /> Center char : ﺜ <br /> End char : ﺞ */ JIM(0x62c, 0xfe9f, 0xfea0, 0xfe9e,0xfe9d),
    /** Primary char : چ <br /> Start char : ﭼ <br /> Center char : ﭽ <br /> End char : ﭻ */ CH(0x686, 0xfb7c, 0xfb7d, 0xfb7b , 0xfb7a ),
    /** Primary char : ح <br /> Start char : ﺣ <br /> Center char : ﺤ <br /> End char : ﺢ */ H__JIMI(0x62d, 0xfea3, 0xfea4, 0xfea2, 0xfea1),
    /** Primary char : خ <br /> Start char : ﺧ <br /> Center char : ﺨ <br /> End char : ﺦ */ KH(0x62e, 0xfea7, 0xfea8, 0xfea6, 0xfea5),
    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Primary char : س <br /> Start char : ﺳ <br /> Center char : ﺴ <br /> End char : ﺲ */ S(0x633, 0xfeb3, 0xfeb4, 0xfeb2, 0xfeb1),
    /** Primary char : ش <br /> Start char : ﹻ <br /> Center char : ﺸ <br /> End char : ﺶ */ SH(0x634, 0xfeb7, 0xfeb8, 0xfeb6 , 0xfeb5),
    /** Primary char : ص <br /> Start char : ﺻ <br /> Center char : ﺼ <br /> End char : ﺺ */ SAD(0x635, 0xfebb, 0xfebc, 0xfeba , 0xfeb9),
    /** Primary char : ض <br /> Start char : ﺿ <br /> Center char : ﻀ <br /> End char : ﺾ */ THAD(0x636, 0xfebf, 0xfec0, 0xfebe , 0xfebd),
    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Primary char : ط <br /> Start char : ﻃ <br /> Center char : ﻄ <br /> End char : ﻂ */ TAA(0x637, 0xfec3, 0xfec4, 0xfec2 , 0xfec1),
    /** Primary char : ظ <br /> Start char : ﻇ <br /> Center char : ﻈ <br /> End char : ﻆ */ ZAA(0x638, 0xfec7, 0xfec8, 0xfec6, 0xfec6),
    /** Primary char : ع <br /> Start char : ﻋ <br /> Center char : ﻌ <br /> End char : ﻊ */ EIN(0x639, 0xfecb, 0xfecc, 0xfeca , 0xfec9),
    /** Primary char : غ <br /> Start char : ﻓ <br /> Center char : ﻔ <br /> End char : ﻎ */ GHEIN(0x63a, 0xfecf, 0xfed0, 0xfece , 0xfecd),
    /** Primary char : ف <br /> Start char : ﻓ <br /> Center char : ﻔ <br /> End char : ﻒ */ F(0x641, 0xfed3, 0xfed4, 0xfed2, 0xfed1),
    /** Primary char : ق <br /> Start char : ﻗ <br /> Center char : ﻘ <br /> End char : ﻖ */ GH(0x642, 0xfed7, 0xfed8, 0xfed6, 0xfed5),
    /** Primary char : ك <br /> Start char : ﻛ <br /> Center char : ﻜ <br /> End char : ﻚ */ KAF_HAMZE(0x643, 0xfedb, 0xfedc, 0xfeda, 0xfed9),
    /** Primary char : ک <br /> Start char : ﮐ <br /> Center char : ﮑ <br /> End char : ﮏ */ KAF(0x6a9, 0xfb90, 0xfb91, 0xfb8f, 0xfb8e),
    /** Primary char : گ <br /> Start char : ﮔ <br /> Center char : ﮕ <br /> End char : ﮓ */ GAF(0x6af, 0xfb94, 0xfb95, 0xfb93, 0xfb92 ),
    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Primary char : ل <br /> Start char : ﻟ <br /> Center char : ﻠ <br /> End char : ﻞ */ LAM(0x644, 0xfedf, 0xfee0, 0xfede, 0xfedd),
    /** Primary char : م <br /> Start char : ﻣ <br /> Center char : ﻤ <br /> End char : ﻢ */ MIM(0x645, 0xfee3, 0xfee4, 0xfee2, 0xfee1),
    /** Primary char : ن <br /> Start char : ﻧ <br /> Center char : ﻨ <br /> End char : ﻦ */ NOON(0x646, 0xfee7, 0xfee8, 0xfee6, 0xfee5),
    /** Primary char : ه <br /> Start char : ﻫ <br /> Center char : ﻬ <br /> End char : ﻪ */ H__EYES(0x647, 0xfeeb, 0xfeec, 0xfeea, 0xfee9),
    //------------------------------------------------------------------------------------------------------------------------------------------------------------
    /** Primary char : ی <br /> Start char : ﯾ <br /> Center char : ﯿ <br /> End char :  */ Y(0x6cc, 0xfbfe, 0xfbff, 0xfbfd , 0xfbfc),
    /** Primary char : ی <br /> Start char : ﯾ <br /> Center char : ﯿ <br /> End char :  */ Y_ARABIC___2_DOT(0x64a, 0xfef3, 0xfef4,  0xfef2, 0xfef1),
    ;
    //==============================================================
    // Fields and Const
    //==============================================================
    /**  The mixed characters (ex ) ﻻ ﻵ ﻷ  ﻹ */ public static final RtlGlyph MIXED_CHARACTER[] = new RtlGlyph[] {LA,LA__HAMZE_BOTTOM,LA__HAMZE_TOP,LA__HAT_HOLDER};
    /** The mixer characters (Alef families) */ public static final RtlGlyph MIXERS[] = new RtlGlyph[] {A__HAMZE_TOP,A__HAMZE_BOTTOM,A__HAT_HOLDER ,A__SIMPLE };
    /** The mixable characters (Just {@link RtlGlyph#LAM} for now...) */ public static final RtlGlyph MIXABLE[] = new RtlGlyph[] {LAM};
    //--------------------------
    /** Individual and primary character in normal strings */ private final int primaryChar;
    /** The starter character is the first character if the next character is sticky. */ private final int startChar;
    /** The center character is the middle character if the previous and next character is sticky */ private final int centerChar;
    /** The end character is the last character of word if the previous character is sticky */ private final int endChar;
    /** The isolated character is the last character of word if the previous and next character is sticky */ private final int isolateChar;
    //==============================================================
    // constructors
    //==============================================================
    /***
     * This constructor provided for 4-letter characters.
     * @param primaryChar see {@link RtlGlyph#primaryChar}
     * @param startChar see {@link RtlGlyph#startChar}
     * @param centerChar see {@link RtlGlyph#centerChar}
     * @param endChar see {@link RtlGlyph#endChar}
     *
     */
    RtlGlyph(int primaryChar, int startChar, int centerChar, int endChar, int isolateChar ) {
        this.primaryChar = primaryChar;
        this.startChar = startChar;
        this.centerChar = centerChar;
        this.endChar = endChar;
        this.isolateChar = isolateChar;
    }
    /**
     * This constructor provided for 1-letter characters (Individual characters).
     * @param primaryChar see {@link RtlGlyph#primaryChar}
     */
    RtlGlyph(int primaryChar) {
        this(primaryChar,0,0,0, 0);
    }
    /**
     * This constructor provided for 2-letter characters (Individual characters).
     * @param primaryChar see {@link RtlGlyph#primaryChar}
     * @param endChar see {@link RtlGlyph#endChar}
     */
    RtlGlyph(int primaryChar, int endChar , int isolateChar) {
        this(primaryChar, 0, 0, endChar , isolateChar);
    }
    //==============================================================
    // getter and setter
    //==============================================================
    /**
     * The primary (Individual) letter, see {@link RtlGlyph#primaryChar}
     * @return {@link RtlGlyph#primaryChar} as int
     */
    public int getPrimaryChar() {
        return primaryChar;
    }
    /**
     * The start of word letter, see {@link RtlGlyph#startChar}
     * @return {@link RtlGlyph#startChar} as int
     */
    public int getStartChar() {
        if(startChar == 0) return primaryChar;
        return startChar;
    }
    /**
     * The central (middle) letter, see {@link RtlGlyph#centerChar}
     * @return {@link RtlGlyph#centerChar} as int
     */
    public int getCenterChar() {
        if (centerChar == 0)
        {
            if(endChar == 0)  return primaryChar; else return endChar;
        }
        return centerChar;
    }
    /**
     * The end of word letter, see {@link RtlGlyph#endChar}
     * @return {@link RtlGlyph#endChar} as int
     */
    public int getEndChar() {
        if(endChar ==0) return primaryChar;
        return endChar;
    }
    /**
     * The isolated letter, see {@link RtlGlyph#isolateChar}
     * @return {@link RtlGlyph#endChar} as int
     */
    public int getIsolateChar() {
        return isolateChar;
    }
    //==============================================================
    // METHODS
    //==============================================================
    /** @return An array contains of {@link RtlGlyph#primaryChar} and {@link RtlGlyph#startChar} and {@link RtlGlyph#centerChar} and {@link RtlGlyph#endChar} if exist. */
    public CharArray getAllLetters() {
        CharArray rt = new CharArray();
        rt.add((char) primaryChar);
        if(startChar != 0) { rt.add((char) startChar); }
        if(centerChar != 0) { rt.add((char) centerChar); }
        if(endChar != 0) { rt.add((char) endChar); }
        return rt;
    }
    /** @return true if the letter have just one characters. */
    public boolean isIndividualCharacter() {
        return startChar == 0 && centerChar ==0 && endChar ==0;
    }
    /** @return true if the letter have 2 characters. */
    public boolean isTowCharacter() {
        return startChar == 0 && centerChar ==0 && endChar != 0;
    }
    /** @return true if the letter have 4 characters. */
    public boolean isFourCharacter() {
        return startChar != 0 && centerChar !=0 && endChar != 0;
    }
    /** @return converted English digit letter to Persian/Arabic digit letter */
    public static char getPersianDigit(char englishDigit) {
        return (char)(englishDigit + 1776 - 48);
    }
}

