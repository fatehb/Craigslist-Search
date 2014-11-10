import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.regex.Matcher;

public class GetURLImage {

    URL url = null;
    File outputImageFile = null;
    static int Icounter=0;
    public static BufferedImage image = null;

    public static void fetchImageFromURL (URL url) {
        try {

            image = ImageIO.read(url);
        } catch (IOException e) {
        }

    } // fetchImageFromURL

    // Create a URL from the specified address, open a connection to it,
    // and then display information about the URL.


    public static void storeImage(String i) throws MalformedURLException, IOException    {

        URL url = new URL(i);
        Matcher matcher;
        if(i.matches(".*jpg$")||i.matches(".*JPG$")){

            File outputImageFile = new File("/Users/ruixu/Desktop/image"+i.substring(i.length()-8,i.length()-4)+".jpg");
            image = ImageIO.read(url.openStream());
            ImageIO.write(image, "jpg", outputImageFile);
        }
        else if(i.matches(".*gif$")||i.matches(".*GIF$")){
            image = ImageIO.read(url.openStream());
            File outputImageFile = new File("/Users/ruixu/Desktop/image"+i.substring(i.length()-8,i.length()-4)+".gif");
            ImageIO.write(image, "gif", outputImageFile);
        }
        else if(i.matches(".*jpeg$")||i.matches(".*JEPG$")){
            image = ImageIO.read(url.openStream());
            File outputImageFile = new File("/Users/ruixu/Desktop/image"+i.substring(i.length()-8,i.length()-4)+".jpeg");
            ImageIO.write(image, "jpeg", outputImageFile);
        }

    }// main

} // GetURLImage
