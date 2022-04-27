package spacepong.scenes;
import spacepong.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StartMenuScreen extends JPanel {
    private JButton exit;
    private JButton playGame;
    private BufferedImage background;
    public StartMenuScreen(){
        setLayout(null);
        background= new spacepong.game.Image("background.jpg").getImage();
        playGame= new JButton("PLAY GAME");
        playGame.setBackground(Color.ORANGE);
        exit= new JButton("QUIT GAME");
        exit.setBackground(Color.ORANGE);
        playGame.setBounds(400,300,200,30);
        exit.setBounds(400,400,200,30);
        add(playGame);
        add(exit);
        playGame.addActionListener(e -> Game.switchPanels(new GamePlayScreen()));
        exit.addActionListener(e -> System.exit(0));
        setVisible(true);
        setFocusable(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        //BACKGROUND
    	super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawImage(background,0,0,1024,768,this);
        //GAME OVER
        g.setColor(Color.cyan);
        g.setFont(new Font("ITALIC", Font.ITALIC, 60));
        g.drawString("SPACE PONG",300,220);
    }
}
