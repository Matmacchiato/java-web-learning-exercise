package cn.anntek.springbootverificationcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeUtil {
    public static final int AUTHCODE_LENGTH = 5;
    public static final int SINGLECODE_WIDTH = 10;
    public static final int SINGLECODE_HEIGHT = 15;
    public static final int SINGLECODE_GAP = 5;
    public static final int PADDING_TOP_BOT = 10;
    public static final int PADDING_LEFT_RIGHT = 10;
    public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP) + PADDING_LEFT_RIGHT;
    public static final int IMG_HEIGHT = SINGLECODE_HEIGHT + PADDING_TOP_BOT;
    public static final String[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    static Random random = new Random();

    public static String getVerificationCode() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < AUTHCODE_LENGTH; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public static BufferedImage getVerificationImage(String authCode) {
        BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = img.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont((new Font("Arial", Font.PLAIN, SINGLECODE_HEIGHT + 5)));
        char c;
        for (int i = 0; i < authCode.toCharArray().length; i++) {
            c = authCode.charAt(i);
            g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP / 2 + PADDING_LEFT_RIGHT / 2, IMG_HEIGHT - PADDING_TOP_BOT / 2);
            ;
        }
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(IMG_HEIGHT);
            int y = random.nextInt(IMG_HEIGHT);
            int x2 = random.nextInt(IMG_WIDTH);
            int y2 = random.nextInt(IMG_HEIGHT);
            g.drawLine(x, y, x2, y2);
        }

        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(IMG_WIDTH);
            int y = random.nextInt(IMG_HEIGHT);
            g.drawOval(x, y, 2, 2);
        }
        return img;


    }
}