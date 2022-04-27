package spacepong.game;
import spacepong.scenes.GameOverScreen;
import spacepong.scenes.StartMenuScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {
    static JFrame frame;
    static JPanel panel;
    BufferedImage icon;
    public Game(){
        icon = new Image("icon.jpg").getImage();
        CardLayout cardLayout= new CardLayout();
        panel= new JPanel();
        panel.setLayout(cardLayout);
        frame= new JFrame();
        frame.setIconImage(icon);
        frame.add(panel);
        frame.setTitle("SPACE PONG");
        frame.pack();
        switchPanels(new StartMenuScreen());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(40,3,1024,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void switchPanels(JPanel temp){
        panel.removeAll();
        panel.add(temp);
        panel.getComponent(0);
        panel.setFocusTraversalKeysEnabled(false);
        temp.requestFocusInWindow();
        panel.revalidate();
        panel.repaint();
        panel.updateUI();
    }
}
