package com.gousade.utils;

import com.gousade.pojo.SliderCaptchaDto;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @author woxigousade
 * @date 2021/6/3
 */
@Slf4j
public class ImageUtil {

    private static final int BOLD = 2;

    private static final String PNG = "png";

    public static SliderCaptchaDto getSliderCaptcha(File backgroundImageFile, File templateImageFile) throws IOException {
        BufferedImage templateImage = ImageIO.read(templateImageFile);
        int templateWidth = templateImage.getWidth();
        int templateHeight = templateImage.getHeight();
        BufferedImage backgroundImage = ImageIO.read(backgroundImageFile);
        int backgroundWidth = backgroundImage.getWidth();
        int backgroundHeight = backgroundImage.getHeight();
        // 随机生成抠图坐标X,Y
        // X轴距离右端targetWidth Y轴距离底部targetHeight以上
        Random random = new Random();
        int randomX = random.nextInt(backgroundWidth / 2 - templateWidth) + backgroundWidth / 2;
        int randomY = random.nextInt(backgroundHeight - templateHeight);
        log.info("原图大小为{}x{},随机生成的坐标为({},{})", backgroundWidth, backgroundHeight, randomX, randomY);
        BufferedImage sliderImage = new BufferedImage(templateWidth, templateHeight, templateImage.getType());
        clipImageByTemplate(backgroundImage, templateImage, sliderImage, randomX, randomY);
        Graphics2D graphics = sliderImage.createGraphics();
        // 设置抗锯齿属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(BOLD, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.drawImage(sliderImage, 0, 0, null);
        graphics.dispose();
        return SliderCaptchaDto.builder()
                .backgroundImage(toDataUri(backgroundImage, PNG))
                .sliderImage(toDataUri(sliderImage, PNG))
                .randomX(randomX)
                .randomY(randomY)
                .backgroundImageWidth(backgroundWidth)
                .build();
    }

    /**
     * @param backgroundImage 原图
     * @param templateImage   模板形状图
     * @param sliderImage     裁剪获得的滑块
     * @param x               随机x值
     * @param y               随机y值
     */
    private static void clipImageByTemplate(BufferedImage backgroundImage, BufferedImage templateImage, BufferedImage sliderImage,
                                            int x, int y) {
        // 临时数组遍历用于高斯模糊存周边像素值
        int[][] matrixArray = new int[3][3];
        int[] pixels = new int[9];
        int templateWidth = templateImage.getWidth();
        int templateHeight = templateImage.getHeight();
        //spreadImage(backgroundImage, /*templateImage, x, y,*/ 19);
        //这里把原图的样式复制到滑块图片中，对原图缺口处做模糊处理并添加灰色描边
        for (int i = 0; i < templateWidth; i++) {
            for (int j = 0; j < templateHeight; j++) {
                int rgb = templateImage.getRGB(i, j);
                if (rgb < 0) {
                    sliderImage.setRGB(i, j, backgroundImage.getRGB(x + i, y + j));
                    //缺口区域高斯模糊
                    readPixel(backgroundImage, x + i, y + j, pixels);
                    fillMatrix(matrixArray, pixels);
                    backgroundImage.setRGB(x + i, y + j, avgMatrix(matrixArray));
                }
                // 防止数组越界判断
                if (i == (templateWidth - 1) || j == (templateHeight - 1)) {
                    continue;
                }
                int rightRgb = templateImage.getRGB(i + 1, j);
                int downRgb = templateImage.getRGB(i, j + 1);
                // 描边处理，,取带像素和无像素的界点，判断该点是不是临界轮廓点,如果是设置该坐标像素是白色
                if ((rgb >= 0 && rightRgb < 0) || (rgb < 0 && rightRgb >= 0) || (rgb >= 0 && downRgb < 0)
                        || (rgb < 0 && downRgb >= 0)) {
                    //sliderImage.setRGB(i, j, newImage.getRGB(i, j));
                    backgroundImage.setRGB(x + i, y + j, Color.lightGray.getRGB());
                }
            }
        }
    }

    private static void readPixel(BufferedImage image, int x, int y, int[] pixels) {
        int xStart = x - 1;
        int yStart = y - 1;
        int current = 0;
        for (int i = xStart; i < 3 + xStart; i++)
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;
                } else if (tx >= image.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                } else if (ty >= image.getHeight()) {
                    ty = y;
                }
                pixels[current++] = image.getRGB(tx, ty);
            }
    }

    private static void fillMatrix(int[][] matrix, int[] values) {
        int filled = 0;
        for (int[] x : matrix) {
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int[] x : matrix) {
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();
    }

    public static BufferedImage spreadImage(BufferedImage backgroundImage, BufferedImage templateImage,
                                            int randomX, int randomY, int radius) {
        int x, y, quantum, x_distance, y_distance;

        BufferedImage resultImage = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Random random = new Random(System.currentTimeMillis());
        quantum = radius;
        for (y = randomY; y < randomY + templateImage.getHeight(); y++) {
            for (x = randomX; x < randomX + templateImage.getWidth(); x++) {
                do {
                    x_distance = (int) (((2 * (double) radius + 1) * random.nextDouble()) - quantum);
                    y_distance = (int) (((2 * (double) radius + 1) * random.nextDouble()) - quantum);
                } while ((x + x_distance < randomX || y + y_distance < randomY) || x + x_distance > randomX + templateImage.getWidth()
                        || y + y_distance > templateImage.getHeight());
                try {
                    resultImage.setRGB(x, y, backgroundImage.getRGB(x + x_distance, y + y_distance));
                } catch (Exception e) {
                    log.error("毛玻璃化图片时发生异常", e);
                }
            }
        }
        return resultImage;
    }

    public static BufferedImage spreadImage(BufferedImage image, int radius) {
        int x, y, quantum, x_distance, y_distance;

        BufferedImage bimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Random rand = new Random(System.currentTimeMillis());

        quantum = radius;
        for (y = 0; y < image.getHeight(); y++) {
            for (x = 0; x < image.getWidth(); x++) {
                do {
                    x_distance = (int) (((2 * (double) radius + 1) * rand.nextDouble()) - quantum);
                    y_distance = (int) (((2 * (double) radius + 1) * rand.nextDouble()) - quantum);
                } while ((x + x_distance < 0 || y + y_distance < 0) || x + x_distance > image.getWidth()
                        || y + y_distance > image.getHeight());
                try {
                    bimg.setRGB(x, y, image.getRGB(x + x_distance, y + y_distance));
                } catch (Exception e) {
                    log.error("毛玻璃化图片时发生异常", e);
                }
            }
        }
        return bimg;
    }

    public static String toDataUri(BufferedImage image, String format) {
        return String.format("data:image/%s;base64,%s", format, Base64.getEncoder().encodeToString(toBytes(image, format)));
    }

    public static byte[] toBytes(BufferedImage image, String format) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, format, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }
}
