import java.awt.*;
import java.lang.Math;

public class MyHendecagonal extends MyDrawing{
    public MyHendecagonal(int xpt, int ypt){
        super();
        setLocation(xpt,ypt);
    }
    public MyHendecagonal(int xpt, int ypt, int w){
        this(xpt,ypt);
        setSize(w,1);
    }
    public MyHendecagonal(int xpt, int ypt, int w, Color lineColor, Color fillColor){
        this(xpt,ypt,w);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }
    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        int n = 11;

        int xpoint[] = new int[n];
        int ypoint[] = new int[n];

        //中心にx,yを持ってくる
        x += w/2;
        y += w/2;
        double theta = 0;

        for(int i = 0; i<n; i++){
            int dx = (int) (w * Math.cos(theta));
            xpoint[i] = x + dx;
            int dy = (int) (w * Math.sin(theta));
            ypoint[i] = y + dy;
            theta += 2*Math.PI/n;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillPolygon(xpoint,ypoint,n);
        g2.setColor(getLineColor());
        g2.drawPolygon(xpoint,ypoint,n);
    }
}
