import java.awt.*;

public class MyOval extends MyDrawing{
    public MyOval(int xpt, int ypt){
        super();
        setLocation(xpt,ypt);
    }
    public MyOval(int xpt, int ypt, int w, int h){
        this(xpt,ypt);
        setSize(w,h);
    }
    public MyOval(int xpt, int ypt, int w, int h, Color lineColor, Color fillColor){
        this(xpt,ypt,w,h);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }
    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();
        
        x -= w/2;
        y -= h/2;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillOval(x,y,w,h);
        g2.setColor(getLineColor());
        g2.drawOval(x,y,w,h);
    }

}
