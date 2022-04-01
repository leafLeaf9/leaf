package com.gousade.util;

public class BitUtils {
    /**
     * Set the specified bit to 1
     *
     * @param originalValue raw int value
     * @param bitIndex      bit index
     * @return final value
     */
    public static int setSpecifiedBitTo1(int originalValue, int bitIndex) {
        return originalValue | 1 << bitIndex;
    }

    /**
     * Set the specified bit to 0
     *
     * @param originalValue raw int value
     * @param bitIndex      bit index
     * @return final value
     */
    public static int setSpecifiedBitTo0(int originalValue, int bitIndex) {
        return originalValue & ~(1 << bitIndex);
    }

    /**
     * Invert the specified bit
     *
     * @param originalValue raw int value
     * @param bitIndex      bit index
     * @return final value
     */
    public static int reverseSpecifiedBit(int originalValue, int bitIndex) {
        return originalValue ^ 1 << bitIndex;
    }

    /**
     * Get the value of the specified bit
     *
     * @param originalValue raw int value
     * @param bitIndex      bit index
     * @return final value
     */
    public static int getSpecifiedBitValue(int originalValue, int bitIndex) {
        return originalValue >> bitIndex & 1;
    }
}
