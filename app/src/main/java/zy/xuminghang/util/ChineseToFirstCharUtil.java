package zy.xuminghang.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/12/21 0021.
 */

public class ChineseToFirstCharUtil {
    static final int GB_SP_DIFF = 160;
    static final int[] secPosValueList = { 1601, 1637, 1833, 2078, 2274, 2302,
            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
            4086, 4390, 4558, 4684, 4925, 5249, 5600 };
    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z' };

    public static String getSpells(String characters) {
        StringBuffer buffer = new StringBuffer();
        characters = characters.toLowerCase();
        for (int i = 0; i < characters.length(); i++) {

            char ch = characters.charAt(i);
            if ((ch >> 7) == 0) {
                // 判断是否为汉字，如果左移7为为0就不是汉字，否则是汉字
                buffer .append(ch);
            } else {
                char spell = getFirstLetter(ch);
                buffer.append(String.valueOf(spell));
            }
        }
        return buffer.toString();
    }

    // 获取一个汉字的首字母
    public static Character getFirstLetter(char ch) {

        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
            return null;
        } else {
            return convert(uniCode);
        }
    }

    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */
    static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i]
                    && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }


    /**
     * 取单个字符的拼音声母
     *
     * @param c
     *            //要转换的单个汉字
     * @return String 拼音声母
     */
    private static String getPYChar(String c) {
        byte[] array = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short) (array[0] - '\0' + 256) * 256
                + ((short) (array[1] - '\0' + 256));
        if (i < 0xB0A1)
            return "*";
        if (i < 0xB0C5)
            return "a";
        if (i < 0xB2C1)
            return "b";
        if (i < 0xB4EE)
            return "c";
        if (i < 0xB6EA)
            return "d";
        if (i < 0xB7A2)
            return "e";
        if (i < 0xB8C1)
            return "f";
        if (i < 0xB9FE)
            return "g";
        if (i < 0xBBF7)
            return "h";
        if (i < 0xBFA6)
            return "j";
        if (i < 0xC0AC)
            return "k";
        if (i < 0xC2E8)
            return "l";
        if (i < 0xC4C3)
            return "m";
        if (i < 0xC5B6)
            return "n";
        if (i < 0xC5BE)
            return "o";
        if (i < 0xC6DA)
            return "p";
        if (i < 0xC8BB)
            return "q";
        if (i < 0xC8F6)
            return "r";
        if (i < 0xCBFA)
            return "s";
        if (i < 0xCDDA)
            return "t";
        if (i < 0xCEF4)
            return "w";
        if (i < 0xD1B9)
            return "x";
        if (i < 0xD4D1)
            return "y";
        if (i < 0xD7FA)
            return "z";
        return "*";
    }


}
