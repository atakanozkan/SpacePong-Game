package spacepong.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Image {
    private BufferedImage image;

    public Image(String filename) {
        try {
        	String separator= System.getProperty("file.separator");
            Path  path= Paths.get("");
            image = ImageIO.read(new File(path.toAbsolutePath()+separator+"src"+separator+
            		"spacepong"+separator+"assets"+separator+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getImage(){
        return image;
    }
}
