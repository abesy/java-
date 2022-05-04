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
    public MyEqPolygon(int xpt, int ypt, int w, int n){
        this(xpt,ypt,n);
        setSize(w,1);
    }
    public MyEqPolygon(int xpt, int ypt, int w, Color lineColor, Color fillColor, int n){
        this(xpt,ypt,w,n);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }
    public void draw(Graphics g){
        if(super.shadow){
            this.Selected = super.isSelected;
            super.isSelected = false;
            super.draw(g);
            super.isSelected = this.Selected;
        }

        int x = getX();
        int y = getY();
        int w = getW();

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

        if(super.isSelected){
            g.setColor(Color.black);
            g.fillRect(x-SIZE/2,y-w/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x-w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
            g.fillRect(x-SIZE/2,y+w/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
            g.fillRect(x-w/2-SIZE/2,y-w/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w/2-SIZE/2,y-w/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x-w/2-SIZE/2,y+w/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w/2-SIZE/2,y+w/2-SIZE/2,SIZE,SIZE);
        }
    }

    public boolean contains(int x,int y){
        return region.contains(x,y);
    }

    public void setRegion(){
        int x = getX();
        int y = getY();
        int w = getW();
        
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
