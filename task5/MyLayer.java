import java.util.Vector;
import java.io.Serializable;

public class MyLayer implements Serializable{
    private Vector<MyDrawing> drawings;

    public MyLayer(){
        drawings = new Vector<MyDrawing>();
    }

    public Vector<MyDrawing> getDrawings(){
        return drawings;
    }
    
    public void addDrawing(MyDrawing d){
        drawings.add(d);
    }
}