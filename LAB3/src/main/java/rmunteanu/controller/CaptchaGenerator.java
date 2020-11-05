package rmunteanu.controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Random;

public class CaptchaGenerator{

    public String captcha;
    public Integer valueOfCaptcha;
    String filename = "D:\\FAC_MASTER\\AN1\\SEM1\\JAVA\\tema2\\target\\tema2-1.0-SNAPSHOT\\captcha\\image.png";

    CaptchaGenerator(){};
    void generateRandomMathString(){

        Random ran = new Random();
        Integer x = ran.nextInt(100);
        Integer y = ran.nextInt(100);
        valueOfCaptcha = x +y;
        captcha = x.toString() + "+" + y.toString() + "=";
        System.out.println(captcha);
    }

    public void CreateCaptcha(String captchaFunction){

        BufferedImage bufferedImage = new BufferedImage(150, 40, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        GradientPaint gp = new GradientPaint(0, 0,
                Color.green, 0, 40 / 5, Color.orange, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 150, 40);


        g2d.setColor(new Color(0, 120, 150));
        g2d.drawString(captchaFunction, 30, 30);

        try {
            ImageIO.write(bufferedImage, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
