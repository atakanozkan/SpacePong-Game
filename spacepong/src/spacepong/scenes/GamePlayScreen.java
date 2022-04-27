package spacepong.scenes;

import spacepong.game.Game;
import spacepong.game.Image;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GamePlayScreen extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    private boolean gameStarted;
    private final int delay=20;
    private double ballVelocityX=-400;
    private double ballVelocityY=-100;
    private int ballPositionX=10;
    private int ballPositionY=10;
    private int paddleX= 412;
    private double timeArc=360;
    private int lives=3;
    private int level =1;
    private static int score;
    BufferedImage background;
    BufferedImage meteorite_small;
    BufferedImage star_small;
    BufferedImage ufoship_small;
    BufferedImage heart;
    private int starPosX;
    private int starPosY;
    private int ufoshipPosX;
    private int ufoshipPosy;
    private int meteorPosX;
    private int meteorPosY;
    Random random;
    public GamePlayScreen(){
        setFocusable(true);
        random= new Random();
        starPosX=random.nextInt(800)+100;
        starPosY=random.nextInt(500)+40;
        meteorPosX= random.nextInt(800)+100;
        meteorPosY=random.nextInt(500)+40;
        ufoshipPosX=random.nextInt(800)+100;
        ufoshipPosy= random.nextInt(500)+40;
        score=0;
        heart= new spacepong.game.Image("heart.jpg").getImage();
        background= new spacepong.game.Image("background.jpg").getImage();
        meteorite_small= new spacepong.game.Image("meteorite_small.png").getImage();
        star_small= new spacepong.game.Image("star_small.png").getImage();
        ufoship_small= new Image("ufoship_small.png").getImage();
        addKeyListener(this);
        timer = new Timer(delay,this);
        timer.start();
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0,1024,768,this);
        //BORDER
        g.setColor(Color.BLACK);
        g.fillRect(0,0,3,768);
        g.fillRect(0,0,1024,3);
        g.fillRect(1021,0,3,768);
        //image
        //LİFE BALL
        g.setColor(Color.GREEN);
        for(int a=0;a<lives;a++){
            g.drawImage(heart,15+a*25,80,20,20,this);
            //g.fillOval(15+a*15,80,10,10);
        }

        //score table
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g.drawString("Score: "+Integer.toString(score),10,70);
        // level table 
        g.drawString("Level: "+level, 10, 35);
        //time
        g.setColor(Color.RED);
        g.fillArc(920,20,60,60,0,(int)timeArc);
        //BALL
        g.setColor(Color.CYAN);
        g.fillOval(ballPositionX,ballPositionY,10,10);
        //PADDLE
        g.setColor(Color.WHITE);
        g.fillRect(paddleX,700,120,10);
        // STAR
        g.drawImage(star_small,starPosX,starPosY,40,40,this);
        // METEOR
        g.drawImage(meteorite_small,meteorPosX,meteorPosY,40,40,this);
        // UFO
        g.drawImage(ufoship_small,ufoshipPosX,ufoshipPosy,40,40,this);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(gameStarted){
            if(timeArc<=0){
            	level++;
                timeArc=360;
                ballVelocityY+=ballVelocityY*(0.5);
                ballVelocityX+=ballVelocityX*(0.5);
            }
            timeArc-=0.19;
            if(ballPositionY>768){
                lives--;
                gameStarted=false;
                ballPositionX=10;
                ballPositionY=10;
                paddleX= 412;
                timeArc=360;
            }
            starIntersectsBall();
            meteorIntersectsBall();
            ufoIntersectsBall();
            paddleHitBall();
            if(lives<=0){
                Game.switchPanels(new GameOverScreen());
            }
            ballMove();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(paddleX>=900){paddleX=900;}
            else {paddleX=paddleX+30;}
            gameStarted=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(paddleX<=3){paddleX=3;}
            else{paddleX=paddleX-30;}
            gameStarted=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void ballMove(){
        double time =delay/1000f;
        ballPositionX+=ballVelocityX*time;
        ballPositionY+=(int)(time*(ballVelocityY+(0.5*9.8*time)));
        ballVelocityY=(ballVelocityY+(9.8*time));
        if(ballPositionX<=0 || ballPositionX>1010){ballVelocityX=-ballVelocityX;}
        if(ballPositionY<=0){ballVelocityY=-ballVelocityY;}

    }
    public void starIntersectsBall(){
        if(new Rectangle(starPosX,starPosY,40,40).intersects(new Rectangle(ballPositionX,ballPositionY,10,10))){
            score+=5;
            starPosX=random.nextInt(800)+100;
            starPosY=random.nextInt(500)+40;
        }
    }
    public void meteorIntersectsBall(){
        if(new Rectangle(meteorPosX,meteorPosY,40,40).intersects(new Rectangle(ballPositionX,ballPositionY,10,10))){
            ballVelocityY+=ballVelocityY*(0.2);
            ballVelocityX+=ballVelocityX*(0.2);
            meteorPosX=random.nextInt(800)+100;
            meteorPosY=random.nextInt(500)+40;
        }
    }
    public void ufoIntersectsBall(){
        if(new Rectangle(ufoshipPosX,ufoshipPosy,40,40).intersects(new Rectangle(ballPositionX,ballPositionY,10,10))){
            lives--;
            ufoshipPosX=random.nextInt(800)+100;
            ufoshipPosy=random.nextInt(500)+40;
        }
    }
    public void paddleHitBall(){
        if(new Rectangle(ballPositionX,ballPositionY,10,10).intersects(new Rectangle(paddleX,700,120,10))){
            ballVelocityY=-ballVelocityY;
            score++;
        }
    }
    public static int getScore(){
        return score;
    }
}
