package deep.richi.libgdx.rtl;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.CharArray;
import com.badlogic.gdx.utils.StringBuilder;


/**
 * @author Richi on 10/28/19.
 */
public class RtlController {
    private static final RtlGlyph ALL_CHARS[] = RtlGlyph.values();
    public static final MixedChars MIXED_CHARS[] = new MixedChars[] {
            new MixedChars(RtlGlyph.LA).isMixOf(RtlGlyph.LAM , RtlGlyph.A__SIMPLE),
            new MixedChars(RtlGlyph.LA__HAMZE_TOP).isMixOf(RtlGlyph.LAM , RtlGlyph.A__HAMZE_TOP),
            new MixedChars(RtlGlyph.LA__HAMZE_BOTTOM).isMixOf(RtlGlyph.LAM , RtlGlyph.A__HAMZE_BOTTOM),
            new MixedChars(RtlGlyph.LA__HAT_HOLDER).isMixOf(RtlGlyph.LAM , RtlGlyph.A__HAT_HOLDER),
    };
    private static RtlController mInstance = null;
    private static final char SPACE_CHAR = 32;
    private boolean mIsEndLastCharacter;
    private boolean mJustUseEnglishDigit;
    //==============================================================
    // METHODS
    //==============================================================
    public CharArray getAllRtlChars() {
        CharArray array = new CharArray();
        for (RtlGlyph glyph : ALL_CHARS)
        {
            for (char character : glyph.getAllLetters().items)
            {
                if (!array.contains((char) character))  array.add(character);
            }
        }
        return array;
    }
    public StringBuilder getRtl(CharSequence text) {
        return getRtl(text, false);
    }
    public StringBuilder getRtl(CharSequence text , boolean justUseEnglishDigit) {
        mIsEndLastCharacter = true;
        mJustUseEnglishDigit = justUseEnglishDigit;
        StringBuilder rt = new StringBuilder();
        char rtlChar = 0;
        char currentChar = 0;
        char previousChar = 0;
        char nextChar = 0;
        boolean isPersianContext = false;
        StringBuilder persianSequence = new StringBuilder();
//        StringBuilder signeSequence = new StringBuilder();
        for (int i = 0; i < text.length(); i++)
        {
            currentChar = getCharAt(text , i);
            previousChar = getCharAt(text , i-1);
            nextChar = getCharAt(text , i+1);

            rtlChar = getRtlChar(previousChar,currentChar,nextChar);
            if (isMixedChar(rtlChar))
            {
                persianSequence.setCharAt(persianSequence.length - 1, rtlChar);
            }
            else
            {
                if (isEnglishLetter(rtlChar) || (!isRtl(rtlChar) && persianSequence.length == 0))
                {
                    if (persianSequence.length != 0)
                    {
                        rt.append(persianSequence);
                        persianSequence.delete(0, persianSequence.length);
                    }
                    rt.append(rtlChar);
                }
                else
                {
                    // fixing digits and parenthesis and brackets before rtl  specification
                    if(i >0 && rt.length == i && !isContainEnglishLetter(rt))
                    {
                        char c;
                        for (int j = 0; j < rt.length; j++)
                        {
                            c = rt.charAt(j);
                            c = convertEnglishDigitToPersianIfIsDigit(c);
                            c = fixParenthesisAndBracketsRtl(c);
                            rt.setCharAt(j,c);
                        }
                    }

                    // add current rtl character to template sequence
                    rtlChar = convertEnglishDigitToPersianIfIsDigit(rtlChar);
                    rtlChar = fixParenthesisAndBracketsRtl(rtlChar);
                    persianSequence.append(rtlChar);
                }
            }
        }

        if (persianSequence.length != 0)
        {
            rt.append(persianSequence);
        }

        // fix text length if exist 'Mixed characters'
        for (int i = rt.length; i < text.length(); i++)
        {
//            rt.append('\u0000');
            rt.append(' ');
        }
        return rt;
    }
    /**
     * reverse the glyphs for rendering rtl languages (Persian/Arabic)
     * @param run
     */
    public void reverse(GlyphLayout.GlyphRun run) {
        Array<BitmapFont.Glyph> originGlyphs = new Array<>();
        Array<BitmapFont.Glyph> persianGlyphs = new Array<>();
//        Array<BitmapFont.Glyph> englishGlyphs = new Array<>();

        for (BitmapFont.Glyph glyph : run.glyphs) originGlyphs.add(glyph);
        run.glyphs.clear();
        char c;
        int persianSequenceOffset = 0;
        BitmapFont.Glyph g;
        int englishOffset = -1;
        for (int i = 0; i < originGlyphs.size ; i++)
        {
            g = originGlyphs.get(i);
            c = (char) g.id;
            if (isEnglishLetter(c)  || isSeparatorCharacter_and_isEnglishSequence(c , persianGlyphs) || isDigit_persian_or_english(c))
            {
                if(!persianGlyphs.isEmpty())
                {
                    englishOffset = appendPersian(englishOffset,run,persianGlyphs);
                }
                englishOffset++;
                run.glyphs.insert(englishOffset,g);
            }
            else // if  c  is Persian/Arabic letter then :
            {
                persianGlyphs.add(g);
            }
        }

        if(!persianGlyphs.isEmpty())
        {
            appendPersian(englishOffset, run, persianGlyphs);
        }
    }
    //==============================================================
    // Privates
    //==============================================================
    private char convertEnglishDigitToPersianIfIsDigit( char rtlChar) {
        if(!mJustUseEnglishDigit && isEnglishDigit(rtlChar))
            return RtlGlyph.getPersianDigit(rtlChar);
        return rtlChar;
    }
    private char fixParenthesisAndBracketsRtl(char rtlChar) {
        switch (rtlChar)
        {
            case ')' : return '('; case '(' : return ')';
            case ']' : return '['; case '[' : return ']';
            case '}' : return '{'; case '{' : return '}';
            case '>' : return '<'; case '<' : return '>';
        }
        return rtlChar;
    }
    private int appendPersian(int englishOffset, GlyphLayout.GlyphRun run, Array<BitmapFont.Glyph> persianGlyphs) {
//        int j = 0;
//        if(englishOffset == run.glyphs.size-1) j=run.glyphs.size;
//    else
        if(englishOffset > 0 && run.glyphs.get(englishOffset).id == SPACE_CHAR)
        {
            run.glyphs.insert(0,run.glyphs.get(englishOffset));
            run.glyphs.removeIndex(englishOffset+1);
        }

        for (int i = 0; i < persianGlyphs.size; i++)
        {
            run.glyphs.insert(0,persianGlyphs.get(i));
        }
        persianGlyphs.clear();
        return -1;
    }
    private boolean isSeparatorCharacter_and_isEnglishSequence(char c, Array<BitmapFont.Glyph> persianGlyphs) {
        return (!isRtl(c) && persianGlyphs.isEmpty());
    }
    private void convertEnglishDigitToPersian(StringBuilder text) {
        for (int i = 0; i < text.length; i++)
        {
            if (isEnglishDigit(text.charAt(i)))
            {
                text.chars[i] = RtlGlyph.getPersianDigit(text.charAt(i));
            }
        }
    }
    private void convertEnglishParenthesisPersian(StringBuilder text) {
        if (text.length == 1)
        {
            for (int i = 0; i < text.length; i++)
            {
                if (text.charAt(i) == '(')
                {
                    text.chars[i] = ')';
                }
                else if (text.charAt(i) == ')')
                {
                    text.chars[i] = '(';
                }
            }
        }
    }
    private boolean isContainEnglishLetter(StringBuilder textContainEnglishLetterAndMaybePersianDigits) {
        for (int i = 0; i < textContainEnglishLetterAndMaybePersianDigits.length; i++)
        {
            if (isEnglishLetter(textContainEnglishLetterAndMaybePersianDigits.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isPersianDigit(char c) {
        return (c >= RtlGlyph.PERSIAN_DIGIT_0.getPrimary() && c <= RtlGlyph.PERSIAN_DIGIT_9.getPrimary()) || (c >= RtlGlyph.PERSIAN_DIGIT__0.getPrimary() && c <= RtlGlyph.PERSIAN_DIGIT__9.getPrimary());
    }
    private boolean isEnglishLetter(char c) {
        return c>=65 && c<= 90 || c>=97 && c<=122;
    }
    private boolean isEnglishDigit(char rtlChar) {
        return rtlChar >= 48 && rtlChar <58;
    }
    private boolean isMixedChar(char rtlChar) {
        for (MixedChars mixedChar: MIXED_CHARS)
        {
            if(mixedChar.mixedRtlChar.getPrimary() == rtlChar) return true;
            if(mixedChar.mixedRtlChar.getStartChar() == rtlChar) return true;
            if(mixedChar.mixedRtlChar.getCenterChar() == rtlChar) return true;
            if(mixedChar.mixedRtlChar.getEndChar() == rtlChar) return true;
        }
        return false;
    }
    private char getRtlChar(char previousChar ,char currentChar ,char nextChar ) {

        if(!isRtl(nextChar)) nextChar = 0;
        if(!isRtl(previousChar)) previousChar = 0;
        RtlGlyph nextGlyph = findRtlGlyphOf(nextChar);
        RtlGlyph curGlyph = findRtlGlyphOf(currentChar);
        char rt = currentChar;
        if (curGlyph != null)
        {
            if(previousChar == 0 && nextChar == 0)
            {
                rt = ((char) curGlyph.getPrimary());
            }
            else if (previousChar !=0 && nextChar == 0)
            {
                if(isMixer(currentChar) && isMixable(previousChar))
                {
                    rt = (getMergeOf(previousChar , currentChar));
                }
                else
                {
                    if(this.mIsEndLastCharacter)
                    {
                        rt = ((char) curGlyph.getPrimary());
                    }
                    else
                    {

                        rt = ((char) curGlyph.getEndChar());
                    }
                }
            }
            else if (previousChar ==0 && nextChar != 0)
            {
//                if(this.mIsEndLastCharacter)
//                {
//                    rt = ((char) glyph.getStartChar());
//                }
//                else
                {

                    rt = ((char) curGlyph.getStartChar());
                }
            }
            else // if (beforeChar !=0 && afterChar != 0)
            {
                if(isMixer(currentChar) && isMixable(previousChar))
                {
                    rt = (getMergeOf(previousChar , currentChar));
                }
                else
                {

                    if(this.mIsEndLastCharacter)
                    {
                        rt = ((char) curGlyph.getStartChar());
                    }
                    else
                    {
                        if(nextGlyph != null && (nextGlyph.isFourCharacter() || nextGlyph.isTowCharacter()))
                        {
                            rt = ((char) curGlyph.getCenterChar());
                        }
                        else
                        {
                            rt = ((char) curGlyph.getEndChar());
                        }
                    }
                }
            }
            if (!(isMixer(nextChar) && isMixable(currentChar)))
            {
                this.mIsEndLastCharacter = !curGlyph.isFourCharacter();
            }
        }
        else
        {
            this.mIsEndLastCharacter = true;
        }
        return rt;
    }
    private RtlGlyph findRtlGlyphOf(char curChar) {
        for (RtlGlyph rtlGlyph : ALL_CHARS)
        {
            if (rtlGlyph.getPrimary() == curChar)
            {
                return rtlGlyph;
            }
        }
        return null;
    }
    private char getCharAt(CharSequence text, int position) {
        if (position >= 0 && position < text.length())
        {
            return text.charAt(position);
        }
        return 0;
    }
    /**
     * L and A is mergable with them (L is mergable   and  A is merger for example :   ﻻ    is the merge of    ل    and   ا   )
     * @param primaryChar
     * @return
     */
    private boolean isMixable(char primaryChar) {
        for (RtlGlyph rtlGlyph : RtlGlyph.MIXABLE)
        {
            if (rtlGlyph.getPrimary() == primaryChar)
            {
                return true;
            }
        }
        return false;
    }
    /**
     * L and A is mixable in Persian and Arabic language (L is mixable   and  A is mixer for example :   ﻻ    is the merge of    ل    and   ا   )
     * @param primaryCharacter check for merger (ths char must be primary character and WAS NOT converted to rtl)
     * @return true if the character was merger like ا and آ and ... (families)
     */
    private boolean isMixer(char primaryCharacter) {

        for (RtlGlyph rtlGlyph : RtlGlyph.MIXERS)
        {
            if (rtlGlyph.getPrimary() == primaryCharacter)
            {
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param beforePrimaryChar
     * @param curPrimaryChar
     * @return
     */
    private char getMergeOf(char beforePrimaryChar, char curPrimaryChar) {
        for (MixedChars mixedChar : MIXED_CHARS)
        {
            if (mixedChar.primaryMixedChars[0].getPrimary() == beforePrimaryChar && mixedChar.primaryMixedChars[1].getPrimary() == curPrimaryChar)
            {
                if (mIsEndLastCharacter)
                {
                    return (char) mixedChar.mixedRtlChar.getPrimary();
                }
                else
                {
                    return (char) mixedChar.mixedRtlChar.getEndChar();
                }
            }
        }
        return 0;
    }
    private boolean isRtl(char rtlCharacter) {
        return rtlCharacter > 255 && rtlCharacter <66000; // all character larger than 66000 is not in range of rtl languages.
    }
    private boolean isDigit_persian_or_english(char c) {
        return isPersianDigit(c) || isEnglishDigit(c);
    }
    //==============================================================
    // classes and structures
    //==============================================================
    protected static class MixedChars {
        public RtlGlyph[] primaryMixedChars;
        /**
         * ﻻ <br />  ﻼ
         * ﻵ <br />  ﻶ
         * ﻷ <br />  ﻸ
         * ﻹ <br />  ﻺ
         */
        public RtlGlyph mixedRtlChar;
        public MixedChars(RtlGlyph mixedRtlChar) {
            this.mixedRtlChar = mixedRtlChar;
        }
        public MixedChars isMixOf(RtlGlyph... primaryMixedChars) {
            this.primaryMixedChars = primaryMixedChars;
            return this;
        }
    }
    //==============================================================
    // Statics
    //==============================================================
    public synchronized static RtlController getInstance() {
        if (mInstance == null)
        {
            mInstance = new RtlController();
        }
        return mInstance;
    }
}
