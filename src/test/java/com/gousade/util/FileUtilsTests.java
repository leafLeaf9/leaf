package com.gousade.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FileUtilsTests {
    @Test
    public void testReadFile() {
        String path = "C:\\Users\\Administrator\\Desktop\\tx_prop_required.png";
        byte[] bytes = FileUtils.readFile(path);
        System.out.println(Arrays.toString(bytes));
    }
}
