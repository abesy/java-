import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class LayerManager{
    Mediator med;
    MyCanvas canvas;
    MyLayer selectedLayer;
    JOptionPane jOptionPane;

    public LayerManager(MyCanvas canvas){
        this.canvas = canvas;
        this.med = canvas.mediator;
    }

    public void addLayer(){
        MyLayer newlayer = new MyLayer(); 
        med.layers.add(newlayer);
    }

    public Enumeration<MyLayer> layerElements(){
        return med.layers.elements();
    }

    public void addLayerButton(JPanel lp, ButtonGroup layer){
        int n = layer.getButtonCount() + 1;
        JRadioButton newButton = new JRadioButton("layer:"+ n);//新しいボタンを作成
        layer.add(newButton);//ボタングループに追加
        lp.add(newButton);//レイアウトに追加->Applicationにてコンポーネントに追加
        med.addLayer();
        //System.out.println("Layerを追加しました");
    }

    public void removeLayer(JPanel lp, ButtonGroup layer){//現在のレイアを消去し、一つ前のレイアを選択状態にする
        Enumeration<AbstractButton> bs = layer.getElements();
        int i = 0;
        while(bs.hasMoreElements()){
            AbstractButton jb = bs.nextElement();
            if(layer.getButtonCount() == 1){
                jOptionPane.showMessageDialog(null, "これ以上レイアは削除出来ません", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
            else if(jb.isSelected()){
                if(i == 0){//一番前のレイアを消去した時
                    layer.remove(jb);//現在選択状態のボタンをボタングループから削除
                    med.removeLayer();//現在のレイアを削除
                    med.setSelectLayer(i);//一つ後のレイアを選択
                    lp.remove(jb);//現在選択状態のボタンを削除
                }
                else{//2番目以降のレイアを選択したとき
                    layer.remove(jb);
                    med.removeLayer();//現在のレイアを削除
                    med.setSelectLayer(i-1);//一つ前のレイアを選択
                    lp.remove(jb);        
                }
                break;
            }
            i += 1;
        }
        Enumeration<AbstractButton> bs2 = layer.getElements();
        int j = 0;
        while(bs2.hasMoreElements()){
            AbstractButton jb2 = bs2.nextElement();
            if(i == 0 && j == 0){//一番前のボタンを削除したとき
                jb2.setSelected(true);
            }
            else if(j == i-1){//元々のボタンの一つ前のひとつ前のボタンを選択したとき
                jb2.setSelected(true);
            }
            else{
                jb2.setSelected(false);
            }
            j += 1;
        }
    }

    public void renameLayer(AbstractButton b){
        b.setText(jOptionPane.showInputDialog(null,"レイアの名称を入力してください","レイアの名称の変更",JOptionPane.PLAIN_MESSAGE));
    }

    public void layerSwap(ButtonGroup layer, int i, int j){
        if(i>j){//i<jに
            int j2 = i;
            i = j;
            j = j2;
        }
        med.layerSwap(i, j);
        String back = null;
        String front = null;
        int n = 0;
        Enumeration<AbstractButton> bs = layer.getElements();
        while(bs.hasMoreElements()){
            AbstractButton b = bs.nextElement();
            if(n == i){
                back = b.getText();
            }
            else if(n == j){
                front = b.getText();
            }
            n += 1;
        }
        n = 0;
        Enumeration<AbstractButton> bs2 = layer.getElements();
        while(bs2.hasMoreElements()){
            AbstractButton b2 = bs2.nextElement();
            if(n == i){
                b2.setText(front);
            }
            else if(n == j){
                b2.setText(back);
            }
            n += 1;
        }
    }

}
