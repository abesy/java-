import java.awt.*;

public class MyRectangle extends MyDrawing{
    public MyRectangle(int xpt, int ypt){
        super();
        setLocation(xpt,ypt);
    }
    public MyRectangle(int xpt, int ypt, int w, int h){
        this(xpt,ypt);
        setSize(w,h);
    }
    public MyRectangle(int xpt, int ypt, int w, int h, Color lineColor, Color fillColor){
        this(xpt,ypt,w,h);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }

    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

    if(w < 0){
        x += w;
        w *= -1;
    }
    if(h < 0){
        y += h;
        h *= -1;
    }

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(getLineWidth()));
    g2.setColor(getFillColor());
    g2.fillRect(x,y,w,h);
    g2.setColor(getLineColor());
    g2.drawRect(x,y,w,h);
}
}
