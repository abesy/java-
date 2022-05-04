import java.awt.*;

public class MyDrawing implements Cloneable{
    private int x, y, w, h;
    private Color lineColor, fillColor;
    private int lineWidth;
    boolean isSelected;
    boolean shadow = false;
    MyDrawing Shadow;
    Shape region;//包含判定
    final int SIZE = 7;//選択表示の四角形の大きさ

    public MyDrawing(){
        x = y = 0;
        w = h = 40;
        lineColor = Color.black;
        fillColor = Color.white;
        lineWidth = 1;
        setRegion();
    }

    public void draw(Graphics g){
        if(isSelected){
            g.setColor(Color.black);
            g.fillRect(x+w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
            g.fillRect(x-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w/2-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
            g.fillRect(x-SIZE/2,y-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w-SIZE/2,y-SIZE/2,SIZE,SIZE);
            g.fillRect(x-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
            g.fillRect(x+w-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
        }
        if(shadow){
            Shadow = this.clone();
            Shadow.shadow = false;//影のshadow,isSelectedを無効化
            Shadow.isSelected = false;//無限ループを防ぐ
            Shadow.setFillColor(Color.black);
            Shadow.setLineColor(Color.black);
            Shadow.move(7,7);//影のズレの大きさを定義
            Shadow.draw(g);//影の描画
        }
    }

    public boolean getSelected(){
        return isSelected;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    public boolean contains(int x, int y){
        //MyDrawingsを継承する子クラス内で定義
    }

    public void setRegion(){
        ///MyDrawingsを継承する子クラス内で定義
    }


    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getW(){
        return this.w;
    }

    public int getH(){
        return this.h;
    }

    public int getLineWidth(){
        return this.lineWidth;
    }

    public Color getFillColor(){
        return this.fillColor;
    }

    public Color getLineColor(){
        return this.lineColor;
    }

    public void setLineColor(Color lineColor){
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }

    @Override
    public MyDrawing clone(){
        try{
            MyDrawing clone = (MyDrawing) super.clone();
            return clone;
        } catch(CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }
}
