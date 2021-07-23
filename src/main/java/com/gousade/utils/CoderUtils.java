package com.gousade.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 进制转换工具类
 *
 * @author woxigousade
 * @date 2019/7/23
 */
@SuppressWarnings("all")
public class CoderUtils {
    public static void main(String[] args) throws IOException {
        byte[] sss = {40, 47, 45, 54, 41, 0x4C, 0x4C, 44, 41, 54, 41, 0x0D, 0x0A};
        System.out.println(ascii2String(sss));
        System.out.println(CoderUtils.string2ASCII4byte("@DEVICESTATUS<CR>"));
        byte[] bb = CoderUtils.string2ASCII4byte("@GETALLDATA<CR>");
        System.out.println(bytes2HexString(bb) + "~~~~~~");
        System.out.println(bytes2HexString("@DEVICESTATUS<CR>".getBytes()));

        String ssssss = "40474554414C4C444154410D0A";
        System.out.println(string2ASCII4HexString(ssssss, ssssss.length()));
        byte[] tt = {0x6C, 0x69, 0x73, 0x74, 0x5C, 0x30, 0x30, 0x30, 0x2E, 0x78, 0x6B, 0x6C};
        System.out.println(ascii2String(tt));
        String str = "list\000.xkl";
        System.out.println(string2ASCII4HexString(str, str.length()));
        System.out.println(string2ASCII4HexString("20", 2));
        System.out.println(ascii2Char(0x2b));
        System.out.println(intToHexStringFor8Byte(0));

        System.out.println(bytes2HexStringSplit("浙 AF12345".getBytes()));
        System.out.println(intToHexStringFor4Byte(21002));
        System.out.println(intToHexStringFor4ByteHighBef(21002));
        System.out.println(stringToUnicodeWithoutU("欢"));
        System.out.println(stringToUnicodeWithoutU("a"));
        System.out.println(stringToUnicodeWithoutU("1"));
    }

    public static char ascii2Char(int ASCII) {
        return (char) ASCII;
    }

    public static byte[] bytesO2HexBytes(byte[] bytes) {
        String s = bytes2HexString(bytes);
        return HexString2Bytes(s, s.length() / 2);
    }

    /**
     * 十进制ascii转换16进制成字符串数组
     *
     * @param bytes
     * @return
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString(bytes[i] & 0xff));
        }
        return sb.toString();
    }

    /**
     * 十进制ascii转换16进制成字符串数组
     *
     * @param bytes
     * @return
     */
    public static String bytes2HexStringSplit(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String s = Integer.toHexString(bytes[i] & 0xff);
            while (s.length() < 2) {
                s = "0" + s;
            }
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static int char2ASCII4Int(char c) {
        return (int) c;
    }

    public static byte char2ASCII4byte(char c) {
        return (byte) c;
    }

    /**
     * 同理，ascii转换为char 直接int强制转换为char
     *
     * @param ascii
     * @return
     */
    public static char byteAsciiToChar(int ascii) {
        char ch = (char) ascii;
        return ch;
    }

    public static String ascii2String(int[] ASCIIs) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ASCIIs.length; i++) {
            sb.append((char) ascii2Char(ASCIIs[i]));
        }
        return sb.toString();
    }

    /**
     * 将16进制ASCII转换成字符串格式
     *
     * @param ASCIIs
     * @return
     */
    public static String ascii2String(byte[] ASCIIs) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ASCIIs.length; i++) {
            sb.append((char) ascii2Char(ASCIIs[i]));
        }
        return sb.toString().trim();
    }

    /**
     * 获取字节数组中给定的字节段
     *
     * @param data
     * @param begin
     * @param end
     * @return
     */
    public static byte[] getPartOfCodeFromBytes(byte[] data, int begin, int end) {
        byte[] subData = new byte[end - begin];
        System.arraycopy(data, begin, subData, 0, subData.length);
        return subData;
    }

    public static String ascii2String(String ASCIIs) {
        String[] ASCIIss = ASCIIs.split(",");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ASCIIss.length; i++) {
            sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));
        }
        return sb.toString();
    }

    public static int[] string2ASCII4Int(String s) {// 字符串转换为ASCII码
        if (s == null || "".equals(s)) {
            return null;
        }

        char[] chars = s.toCharArray();
        int[] asciiArray = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            asciiArray[i] = char2ASCII4Int(chars[i]);
        }
        return asciiArray;
    }

    public static byte[] string2ASCII4byte(String s) {// 字符串转换为ASCII码
        if (s == null || "".equals(s)) {
            return null;
        }

        char[] chars = s.toCharArray();
        byte[] asciiArray = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            asciiArray[i] = char2ASCII4byte(chars[i]);
        }
        return asciiArray;
    }

    public static String bytesToHexStringWithOutSpace(byte[] databyte) {
        if (databyte == null || databyte.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        for (int j = 0; j < databyte.length; j++) {
            sb.append(CoderUtils.hexByte2String(databyte[j]));
        }
        return sb.toString();
    }

    /**
     * 将两个16进制字节数组转换成int型
     *
     * @param data 高位在前低位在后
     * @return
     */
    public static int byte2IntForTwoBytesBE(byte[] data) {
        return Integer.parseInt(hexByte2String(data[0]) + hexByte2String(data[1]), 16);
    }

    /**
     * 将四个16进制字节数组转换成int型
     *
     * @param data 高位在前低位在后
     * @return
     */
    public static int byte2IntFor4BytesBE(byte[] data) {
        return Integer.parseInt(hexByte2String(data[0]) + hexByte2String(data[1]) + hexByte2String(data[2])
                + hexByte2String(data[3]), 16);
    }

    public static String string2HexString(String data) {
        int[] ss = string2ASCII(data);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss.length; i++) {
            sb.append(Integer.toHexString(ss[i]));
        }
        return sb.toString();
    }

    /**
     * ascii转换成字符串
     *
     * @param bytes
     * @return
     */
    public static byte[] byteO2String(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString(bytes[i] & 0xff));
        }
        return HexString2Bytes(sb.toString(), sb.length() / 2);
    }

    public static int char2ASCII(char c) {
        return (int) c;
    }

    public static int[] string2ASCII(String s) {// 字符串转换为ASCII码
        if (s == null || "".equals(s)) {
            return null;
        }

        char[] chars = s.toCharArray();
        int[] asciiArray = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            asciiArray[i] = char2ASCII(chars[i]);
        }
        return asciiArray;
    }

    public static byte[] string2byte(String s) {// 字符串转换为ASCII码
        if (s == null || "".equals(s)) {
            return null;
        }

        char[] chars = s.toCharArray();
        byte[] asciiArray = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            asciiArray[i] = (byte) chars[i];
        }
        return asciiArray;
    }

    public static String getIntArrayString(int[] intArray) {
        return getIntArrayString(intArray, ",");
    }

    public static String getIntArrayString(int[] intArray, String delimiter) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < intArray.length; i++) {
            sb.append(intArray[i]).append(delimiter);
        }
        return sb.toString();
    }

    public static String getASCII(int begin, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = begin; i < end; i++) {
            sb.append(i).append(":").append((char) i).append("/t");
            // sb.append((char) i).append("/t");
            if (i % 10 == 0) {
                sb.append("/n");
            }
        }
        return sb.toString();
    }

    public static String getCHASCII(int begin, int end) {
        return getASCII(19968, 40869);
    }

    public static void showASCII(int begin, int end) {
        for (int i = begin; i < end; i++) {
            // System.out.print(i + ":" + (char) i + "/t");
            System.out.print((char) i + "/t");
            if (i % 10 == 0) {
                System.out.println();
            }
        }
    }

    public static void showCHASCII() {
        showASCII(19968, 40869);
    }

    public static void showIntArray(int[] intArray) {
        showIntArray(intArray, ",");
    }

    public static void showIntArray(int[] intArray, String delimiter) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + delimiter);
        }
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
     * 0xD9}
     *
     * @param src String
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String src, int length) {
        byte[] ret = new byte[length];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < length; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
     *
     * @param src0 byte
     * @param src1 byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * 返回的都是低位在前高位在后
     *
     * @param v
     * @return
     */
    public static String intToHexStringFor4Byte(int v) {
        StringBuffer sb = new StringBuffer();
        String result = Integer.toHexString(v);
        while (result.length() < 4) {
            result = "0" + result;
        }
        sb.append(result.substring(2));
        sb.append(" ");
        sb.append(result.substring(0, 2));
        return sb.toString();
    }

    public static String intToHexStringFor8Byte(int v) {
        // 将每个字节取出来
        byte byte4 = (byte) (v & 0xff);
        byte byte3 = (byte) ((v & 0xff00) >> 8);
        byte byte2 = (byte) ((v & 0xff0000) >> 16);
        byte byte1 = (byte) ((v & 0xff000000) >> 24);

        // 拼装成 "高字节在后，低字节在前"的格式
        int realint = (byte1 & 0xff) << 0 | (byte2 & 0xff) << 8 | (byte3 & 0xff) << 16 | (byte4 & 0xff) << 24;
        String result = Integer.toHexString(realint);
        while (result.length() < 8) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * 返回的都是高位在前，低位在后
     *
     * @param v
     * @return
     */
    public static String intToHexStringFor4ByteHighBef(int v) {
        StringBuffer sb = new StringBuffer();
        String result = Integer.toHexString(v);
        while (result.length() < 4) {
            result = "0" + result;
        }
        sb.append(result.substring(0, 2));
        sb.append(" ");
        sb.append(result.substring(2));
        return sb.toString();
    }

    /**
     * 返回int型转化成16进制格式的字符串，长度不够2的前边补0；
     *
     * @param v
     * @return
     */
    public static String intToHexStringFor2Byte(int v) {
        String result = Integer.toHexString(v);
        if (result.length() > 2) {
            throw new RuntimeException("数字" + v + "大于255，不符合本方法格式！");
        }
        while (result.length() < 2) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * 将16进制的byte转换成16进制的字符串
     *
     * @param b
     * @return
     */
    public static String hexByte2String(byte b) {
        String s = Integer.toHexString(b);
        int length = s.length();
        while (s.length() < 2) {
            s = "0" + s;
        }
        if (length > 2) {
            s = s.substring(length - 2, length);
        }
        return s;
    }

    /**
     * 将一个16进制字节转换成int型
     *
     * @param data
     * @return
     */
    public static int byte2Int(byte data) {
        return Integer.parseInt(hexByte2String(data), 16);
    }

    public static int byteToInt(byte data) {
        return data & 0xFF;
    }

    public static List<Integer> bytes2IntList(byte[] datas) {
        List<Integer> list = new ArrayList<Integer>();
        for (byte b : datas) {
            list.add(byte2Int(b));
        }
        return list;
    }

    /**
     * 将两个16进制字节数组转换成int型
     *
     * @param data 低位在前高位在后
     * @return
     */
    public static int byte2IntForTwoBytes(byte[] data) {
        return Integer.parseInt(hexByte2String(data[1]) + hexByte2String(data[0]), 16);
    }

    /**
     * 将4个16进制字节数组转换成int型
     *
     * @param data 低位在前高位在后
     * @return
     */
    public static long byte2LongFor4Bytes(byte[] data) {
        return Long.parseLong(hexByte2String(data[3]) + hexByte2String(data[2]) + hexByte2String(data[1])
                + hexByte2String(data[0]), 16);
    }

    /**
     * 将4个16进制字节数组转换成int型
     *
     * @param data 低位在前高位在后
     * @return
     */
    public static long byte2LongFor4BytesHighBef(byte[] data) {
        return Long.parseLong(hexByte2String(data[0]) + hexByte2String(data[1]) + hexByte2String(data[2])
                + hexByte2String(data[3]), 16);
    }

    /**
     * 将4个16进制字节数组转换成int型
     *
     * @param data 高位在前低位在后
     * @return
     */
    public static int byte2IntFor4BytesHighBef(byte[] data) {
        return Integer.parseInt(hexByte2String(data[0]) + hexByte2String(data[1]) + hexByte2String(data[2])
                + hexByte2String(data[3]), 16);
    }

    /**
     * 将4个16进制字节数组转换成int型
     *
     * @param data 低位在前高位在后
     * @return
     */
    public static int byte2IntFor4Bytes(byte[] data) {
        return Integer.parseInt(hexByte2String(data[3]) + hexByte2String(data[2]) + hexByte2String(data[1])
                + hexByte2String(data[0]), 16);
    }

    /**
     * 将两个16进制字节数组转换成int型
     *
     * @param data 高位前低位在后
     * @return
     */
    public static int byte2IntForTwoBytesHighBef(byte[] data) {
        return Integer.parseInt(hexByte2String(data[0]) + hexByte2String(data[1]), 16);
    }

    /**
     * 将一个字符串转化成16进制的，用空格分开的，规定长度的字符串。（不够字节长度末尾补零）
     *
     * @param s        转化的字符串
     * @param byteSize 规定字节长度
     * @return
     */
    public static String string2ASCII4HexString(String s, int byteSize) {
        int wholeArray[] = new int[byteSize];
        int convertArray[] = CoderUtils.string2ASCII(s);
        if (convertArray != null) {
            System.arraycopy(convertArray, 0, wholeArray, 0, convertArray.length);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteSize; i++) {
            if (wholeArray[i] == 0) {
                sb.append("00");
            } else {
                sb.append(Integer.toHexString(wholeArray[i]));
            }
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 返回crc验证码
     *
     * @param pushMsg 用空格分开的字符串
     * @return
     */
    public static String getCRC(String pushMsg) {
        String subMsg = pushMsg.substring(6, pushMsg.length());
        String[] subMsgArray = subMsg.split(" ");
        int[] byteCRC = new int[subMsgArray.length];
        for (int i = 0; i < subMsgArray.length; i++) {
            byteCRC[i] = Integer.parseInt(subMsgArray[i], 16);
        }
        return CRC.CRC16(byteCRC);
    }

    public static void createFile(String filePathAndName, String fileContent) throws IOException {

        String filePath = filePathAndName;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) {
            myFilePath.createNewFile();
        }
        FileWriter resultFile = new FileWriter(myFilePath);
        PrintWriter myFile = new PrintWriter(resultFile);
        String strContent = fileContent;
        myFile.println(strContent);
        myFile.close();
        resultFile.close();
    }

    public static String bytesToHexString(byte[] databyte) {
        StringBuffer sb = new StringBuffer("");
        for (int j = 0; j < databyte.length; j++) {
            sb.append(CoderUtils.hexByte2String(databyte[j]));
            if (j != databyte.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String intList2HexString(List<Integer> subList) {
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < subList.size(); i++) {
            msg.append(Integer.toHexString(subList.get(i)));
        }
        return msg.toString();
    }

    public static byte[] intList2Byte(List<Integer> subList) {
        byte[] serialByte = new byte[subList.size()];
        for (int i = 0; i < subList.size(); i++) {
            serialByte[i] = subList.get(i).byteValue();
        }
        return serialByte;
    }

    /**
     * 字节转8位
     *
     * @param b
     * @return
     */
    public static String getByteToBit8(byte b) {
        String s = Integer.toBinaryString(b);
        if (s.length() < 8) {
            for (int i = 8 - s.length(); i > 0; i--) {
                s = "0" + s;
            }
        } else if (s.length() > 8) {
            s = s.substring(s.length() - 8, s.length());
        }
        return s;
    }

    /**
     * 字节转8位,且反转字符串，为了方便根据协议中的下标获取数据
     *
     * @param b
     * @return
     */
    public static String getByteToBit8Reverse(byte b) {
        return new StringBuilder(getByteToBit8(b)).reverse().toString();
    }

    public static int getBit(byte b, int index) {
        return getByteToBit8Reverse(b).charAt(index);
    }

    public static String stringToUnicodeWithoutU(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append(Integer.toHexString(c[i]));
        }
        return sb.toString();
    }

    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }
}
