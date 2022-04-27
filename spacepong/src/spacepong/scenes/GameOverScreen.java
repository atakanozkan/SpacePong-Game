package spacepong.scenes;

import spacepong.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverScreen extends JPanel  {
    private JButton goMenu;
    private int score;
    private BufferedImage background;
    public GameOverScreen(){
        setLayout(null);
        background= new spacepong.game.Image("background.jpg").getImage();
        score= GamePlayScreen.getScore();
        goMenu= new JButton("MAIN MENU");
        goMenu.setBackground(Color.cyan);
        goMenu.setBounds(415,400,200,30);
        add(goMenu);
        goMenu.addActionListener(e ->Game.switchPanels(new StartMenuScreen()));
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
        g.setColor(Color.red);
        g.setFont(new Font("ITALIC", Font.ITALIC, 60));
        g.drawString("GAME OVER",322,300);
        // SHOW SCORE
        g.setColor(Color.white);
        g.setFont(new Font("ITALIC", Font.ITALIC, 30));
        g.drawString("Your score: "+score,415,360);
    }

}
