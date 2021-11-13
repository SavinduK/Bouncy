
package ball.game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import java.util.Random;
import java.util.Vector;
import processing.core.*;

/**
 *
 * @author savindu
 */
public class BallGame1 extends PApplet {
  private Color color_pad = new Color(30,30,200);
  private Color color_ball = new Color(200,40,40);
  private Color color_block = new Color(240,240,0);
  Random random = new Random();
   private cell pad0;
   private cell pad1; 
   private cell pad2 ;
   private cell pad3 ;
   private cell pad4;
   private cell ball;
   Vector<cell>blocks;
    public static void main(String[] args) {
       PApplet.main(new String[]{"ball.game1.BallGame1"});
    }
   
    @Override
    public void settings(){
       size(600,680) ;
    }
    
    
    
    
    @Override
    public void setup(){
       initgame(); 
    }
     int score; 
     boolean keylock = false;
     long ltime;
     int pad_dir = 0;//1 right 2 left//
     boolean gameover = false;
     long gameovertime;
    @Override
    public void draw(){
      if(gameover){
          gameover();
          
        if(millis()-gameovertime >2000){
          gameover = false;
          initgame();
      }  
         return; 
      }
      
       
      
       if (keyPressed) {
            if (!keylock) {
                  if (keyCode == KeyEvent.VK_LEFT) {
                    pad_dir = 2;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    pad_dir = 1;
                }
                System.out.println(pad_dir);
            }
            keylock = true;
        } 
       else {
            keylock = false;
        }
     if (millis() - ltime > 200) {
            ltime = millis();
             drawframe();
             if(pad_dir == 1){
                 int xp = pad2.getX();
                 pad0.setX(xp-1);
                 pad1.setX(xp+0);
                 pad2.setX(xp+1);
                 pad3.setX(xp+2);
                 pad4.setX(xp+3);
                 
             }
             else if(pad_dir==2){
                 int Xp = pad2.getX();
                 pad0.setX(Xp+1);
                 pad1.setX(Xp-0);
                 pad2.setX(Xp-1);
                 pad3.setX(Xp-2);
                 pad4.setX(Xp-3);
             }
             pad_dir = 0;
            
             
              moveball();
              logic();
           
        }
             for(cell b : blocks){
                 b.draw(this);
             }
         
            
             pad0.draw(this);
             pad1.draw(this);
             pad2.draw(this);
             pad3.draw(this);
             pad4.draw(this);
             ball.drawball(this);
             textFont(new PFont(new Font("Agency FB", Font.BOLD, 30), true));
             text("score "+score,100,650);
    }
    
    public void initgame(){
       int z = random.nextInt(10)+4;
       pad0 =new cell(color_pad,5,19);
       pad1 = new cell(color_pad,6,19);
       pad2 = new cell(color_pad,7,19);
       pad3 = new cell(color_pad,8,19);
       pad4 = new cell(color_pad,9,19);
       ball = new cell(color_ball,z,0);
       score = 0;
       blocks = new Vector<>();
       createblocks();
    }
   
       
       int x =-1;
       int y =1;
    public void moveball(){
      int bx = ball.getX();
       int by = ball.getY();
        int r =random.nextInt(5);
       bx = bx + x;
       by = by + y;
       if(bx==0){
           x=1;
           if(r == 2){
               by = by +1;
           }
           else if (r==3){
               by = by-1;
           }
       }
       else if(bx==19){
           x=-1;
            if(r == 2){
               by = by +1;
           }
           else if (r==3){
               by = by-1;
           }
       }
       else if(by==0){
          y = 1;
         
          if(r==2){
              bx = bx +1;
          }
          else if (r==4){
              bx = bx-1;
          }
       }
       else if(by == 18){
           if(bx== pad0.getX()){
               y = -1;
                if(r==2){
              bx = bx +1;
          }
          else if (r==1){
              bx = bx-1;
          }
           }
           
           else if(bx==pad1.getX()){
               y = -1;
                if(r==2){
              bx = bx +1;
          }
          else if (r==1){
              bx = bx-1;
          }
           }
           else if(bx == pad2.getX()){
               y = -1;
                if(r==3){
              bx = bx +1;
          }
          else if (r==4){
              bx = bx-1;
          }
           }
           else if(bx == pad3.getX()){
               y = -1;
                if(r==2){
              bx = bx +1;
          }
          else if (r==4){
              bx = bx-1;
          }
           }
           else if (bx == pad4.getX()){
               y = -1;
                if(r==2){
              bx = bx +1;
          }
          else if (r==4){
              bx = bx-1;
          }
           }
           else if(bx == pad0.getX() -1){
               y = -1;
           }
           else if(bx == pad4.getX() + 1){
               y = -1;
           }
           
       }
       else if(by == 19){
           gameover = true;
           gameovertime = millis();
       }
       else if(by == -1){
           y=1;
           bx = bx +2;
       }
       ball.setX(bx);
       ball.setY(by);
       
       
    }
   
    
    
    
    
    public void drawframe(){
        background(200,200,200);
      stroke(60,250,30);
       for(int k =0;k<600;k+=30){
           line(k,0,k,600);
       }
          
       for(int p =0;p<600;p+=30){
           line(0,p,600,p);
       }
       line(0,600,600,600);   
    }

    private void gameover() {
        background(0,0,0);
        fill(190, 40, 60);
        textFont(new PFont(new Font("Agency FB", Font.BOLD, 55), true));
        text("GAME OVER!", 200, 300);
    }

    private void createblocks() {
       while(blocks.size()<20){
           int X = random.nextInt(19);
           int Y = random.nextInt(12);
           boolean onball = false;
           if(ball.getX() == X && ball.getY() == Y){
               onball = true;
           }
           if(!onball){
               blocks.add(new cell(color_block,X,Y));
           }
       }
    }

    private void logic() {
        for(int i =0;i<blocks.size();i++) {
            if(blocks.get(i).getX()== ball.getX() && blocks.get(i).getY()==ball.getY()){
                blocks.removeElementAt(i);
                createblocks();
                score = score + 3;
            }
        } 
    }
       
         
  
    
    
    
}
