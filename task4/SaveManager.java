import java.io.*;   // For write/read Object
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;
import javax.swing.JFileChooser;

public class SaveManager extends MyDrawing
{
    //transient protected Line2D.Double from, to;  // Serialize対応でないフィールドにtransientをつける
    Mediator med;
    Vector<MyDrawing> v;
    JFileChooser fc = new JFileChooser();

    public SaveManager(Mediator mediator){
        med = mediator;
    }

    public void loadFile(){
        try {
            int returnVal = fc.showOpenDialog(null);  // ファイルロード用のダイアログを開く
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fin);
                med.drawings = (Vector)in.readObject();
                fin.close();
                System.out.println("load_successful");
            }
        } catch (Exception ex) {
        }
    }

    public void saveFile(){
        try {
            int returnVal = fc.showSaveDialog(null);
            
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
                ObjectOutputStream out = new ObjectOutputStream(fout);
                out.writeObject(med.drawings);
                out.flush();
                fout.close();
                System.out.println("save_successful");   
            }
        } catch (Exception ex) {
        }
    }

}
