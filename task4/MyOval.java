import java.awt.*;
import java.awt.geom.Area;

public class MyOval extends MyDrawing{
    Polygon region;
    Graphics2D g2;
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
        super.draw(g);

        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();
        
        setRegion();
        g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillOval(x,y,w,h);
        g2.setColor(getLineColor());
        g2.drawOval(x,y,w,h);
    }

    public boolean contains(int x,int y){
        return region.contains(x,y);
    }

    public void setRegion(){
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();
        int n = 30;
        int xpoint[] = new int[n];
        int ypoint[] = new int[n];

        x += w/2;
        y += h/2;
        double theta = 0;
        for(int i = 0; i<n; i++){
            int dx = (int) (w/2 * Math.cos(theta));
            xpoint[i] = x + dx;
            int dy = (int) (h/2 * Math.sin(theta));
            ypoint[i] = y + dy;
            theta += 2*Math.PI/n;
        }

        region = new Polygon(xpoint,ypoint,n);
    }
}
