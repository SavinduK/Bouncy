/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ball.game1;

import java.awt.Color;
import processing.core.PApplet;

/**
 *
 * @author savindu
 */
public class cell {
   private Color color;
   private int X;
   private int Y;

    public cell(Color color, int X, int Y) {
        this.color = color;
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }
      public void draw(PApplet app) {
        app.fill(color.getRGB());
        app.rect(X*30, Y*30,30,30);
    }
       public void drawball(PApplet app) {
        app.fill(color.getRGB());
        app.circle(X*30+15, Y*30+15,30);
    }
}
