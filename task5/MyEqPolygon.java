import java.awt.*;
import java.lang.Math;

public class MyEqPolygon extends MyDrawing{
    int n;
    boolean Selected;
    public MyEqPolygon(int xpt, int ypt, int n){
        super();
        setLocation(xpt,ypt);
        this.n = n;
    }
    public MyEqPolygon(int xpt, int ypt, int w, int h,int n){
        this(xpt,ypt,n);
        setSize(Math.min(w,h),Math.min(w,h));
    }
    public MyEqPolygon(int xpt, int ypt, int w, int h,Color lineColor, Color fillColor, int n){
        this(xpt,ypt,w,h,n);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }
    public void draw(Graphics g){

        int x = getX();
        int y = getY();
        int w = Math.min(getW(),getH());
        x += w/2;
        y += w/2;
        super.setSize(w,w);
        super.draw(g);

        int xpoint[] = new int[n];
        int ypoint[] = new int[n];

        double theta = 0;

        for(int i = 0; i<n; i++){
            int dx = (int) (w/2 * Math.cos(theta));
            xpoint[i] = x + dx;
            int dy = (int) (w/2 * Math.sin(theta));
            ypoint[i] = y + dy;
            theta += 2*Math.PI/n;
        }

        setRegion();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillPolygon(xpoint,ypoint,n);
        g2.setColor(getLineColor());
        g2.drawPolygon(xpoint,ypoint,n);
    }

    public boolean contains(int x,int y){
        return region.contains(x,y);
    }

    public void setRegion(){
        int x = getX();
        int y = getY();
        int w = Math.min(getW(),getH());
        x += w/2;
        y += w/2;
        
        int xpoint[] = new int[n];
        int ypoint[] = new int[n];

        double theta = 0;

        for(int i = 0; i<n; i++){
            int dx = (int) (w/2 * Math.cos(theta));
            xpoint[i] = x + dx;
            int dy = (int) (w/2 * Math.sin(theta));
            ypoint[i] = y + dy;
            theta += 2*Math.PI/n;
        }
        region = new Polygon(xpoint,ypoint,n);
    }
}
