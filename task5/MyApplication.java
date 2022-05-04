import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.DefaultMenuLayout;

public class MyApplication extends JFrame implements ActionListener, ChangeListener{
    StateManager stateManager;
    MyCanvas canvas;
    private JMenuBar menuBar;
    private JMenu colorMenu,colorMenu2,lineMenu,FileMenu,AlphaMenu,bfMenu,layerMenu;
    private JMenuItem save, load;
    private JMenuItem redItem, blueItem, greenItem, blackItem, whiteItem, otherItem;
    private JMenuItem redItem2, blueItem2, greenItem2, blackItem2, whiteItem2, otherItem2;
    private JMenuItem lineItem1, lineItem2, lineItem3, lineItem4, lineItem5, lineItem6;
    private JMenuItem lineAlpha, fillAlpha;
    private JMenuItem setFront, setBack, BackMost, FrontMost;
    private JMenuItem layerAdd, layerRemove, layerRename, layerSwap;
    private JOptionPane jOptionPane;//入力ダイアログ用の
    private JRadioButton layer1, layer2, layer3, layer4;
    ButtonGroup layer;
    Mediator med;
    MyColorChooser colorChooser;
    SaveManager savemanager;
    Dimension canvasSize;
    LayerManager layerManager;
    private JPanel jp, lp;

    public MyApplication(){
        super("My Paint Application");
        
        //canvasSizeの設定 最初に呼び出される。
        JTextField width = new JTextField();
        JTextField hight = new JTextField();
        Object[] message = {"width:", width, "hight:", hight};
        int option = JOptionPane.showConfirmDialog(null, message, "New Canvas", JOptionPane.INFORMATION_MESSAGE);


        canvas = new MyCanvas();
        canvas.setBackground(Color.white);
        canvasSize = new Dimension(Integer.parseInt(width.getText()),Integer.parseInt(hight.getText()));
        canvas.setMaximumSize(canvasSize);
        canvas.setPreferredSize(canvasSize);

        this.med = canvas.mediator;
        savemanager = new SaveManager(med);

        layerManager = new LayerManager(canvas);

        jp = new JPanel();
        jp.setLayout(new FlowLayout());

        lp = new JPanel();
        lp.setLayout(new FlowLayout());
    
        stateManager = new StateManager(canvas);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //各種ボタンの設定
        selectButton selectButton = new selectButton(stateManager);
        jp.add(selectButton);
        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        EqPolygonButton eqPolygonButton = new EqPolygonButton(stateManager);
        jp.add(eqPolygonButton);
        HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
        jp.add(hendecagonalButton);
        //shadowボタン
        ShadowButton shadowButton = new ShadowButton(stateManager);
        jp.add(shadowButton);
        //resizeボタン
        resizeButton resizeButton = new resizeButton(stateManager);
        jp.add(resizeButton);
        //削除ボタン
        RemoveButton removeButton = new RemoveButton(stateManager);
        jp.add(removeButton);
        //copy(cut)&pasteのボタン
        CopyButton copyButton = new CopyButton(stateManager);
        jp.add(copyButton);
        CutButton cutButton = new CutButton(stateManager);
        jp.add(cutButton);
        PasteButton pasteButton = new PasteButton(stateManager);
        jp.add(pasteButton);

        layer1 = new JRadioButton("Layer:1",true);
        layer2 = new JRadioButton("Layer:2");
        layer3 = new JRadioButton("Layer:3");
        layer4 = new JRadioButton("Layer:4");
        layer1.addChangeListener(this);
        layer2.addChangeListener(this);
        layer3.addChangeListener(this);
        layer4.addChangeListener(this);
        layer = new ButtonGroup();
        layer.add(layer1);
        layer.add(layer2);
        layer.add(layer3);
        layer.add(layer4);
        lp.add(layer1);
        lp.add(layer2);
        lp.add(layer3);
        lp.add(layer4);

        jp.setMaximumSize(new Dimension(10000,selectButton.getMaximumSize().height+10));
        lp.setMaximumSize(new Dimension(10000,selectButton.getMaximumSize().height+10));

        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        getContentPane().add(jp);
        getContentPane().add(canvas);
        getContentPane().add(lp);
        //layerManager = new LayerManager(this);


        canvas.addMouseListener(new MouseAdapter(){
            //現在のmouseDown処理を呼び出し
            public void mousePressed(MouseEvent e){
                stateManager.mouseDown(e.getX(),e.getY());
                canvas.repaint();
            }
            //MouseReleasedの呼び出し
            public void mouseReleased(MouseEvent e){
                stateManager.mouseUp(e.getX(),e.getY());
                canvas.repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter(){
            //mouseDragの呼び出し
            public void mouseDragged(MouseEvent e){
                stateManager.mouseDrag(e.getX(),e.getY());
                canvas.repaint();
            }
        });

        //save/loadの実装
        FileMenu = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        FileMenu.add(save);
        FileMenu.add(load);
        save.addActionListener(this);
        load.addActionListener(this);
        menuBar.add(FileMenu);

        //塗色変更の通知
        colorMenu = new JMenu("FillColor");
        redItem = new JMenuItem("Red");
        blueItem = new JMenuItem("Blue");
        greenItem = new JMenuItem("Green");
        blackItem = new JMenuItem("Black");
        whiteItem = new JMenuItem("White");
        otherItem = new JMenuItem("other color");
        colorMenu.add(redItem);
        colorMenu.add(blueItem);
        colorMenu.add(greenItem);
        colorMenu.add(blackItem);
        colorMenu.add(whiteItem);
        colorMenu.add(otherItem);
        redItem.addActionListener(this);
        blueItem.addActionListener(this);
        greenItem.addActionListener(this);
        blackItem.addActionListener(this);
        whiteItem.addActionListener(this);
        otherItem.addActionListener(this);
        menuBar.add(colorMenu);

        //線属性の変更の通知
        colorMenu2 = new JMenu("LineColor");
        redItem2 = new JMenuItem("Red");
        blueItem2 = new JMenuItem("Blue");
        greenItem2 = new JMenuItem("Green");
        blackItem2 = new JMenuItem("Black");
        whiteItem2 = new JMenuItem("White");
        otherItem2 = new JMenuItem("other color");
        colorMenu2.add(redItem2);
        colorMenu2.add(blueItem2);
        colorMenu2.add(greenItem2);
        colorMenu2.add(blackItem2);
        colorMenu2.add(whiteItem2);
        colorMenu2.add(otherItem2);
        redItem2.addActionListener(this);
        blueItem2.addActionListener(this);
        greenItem2.addActionListener(this);
        blackItem2.addActionListener(this);
        whiteItem2.addActionListener(this);
        otherItem2.addActionListener(this);
        menuBar.add(colorMenu2);

        //線の太さの変更
        lineMenu = new JMenu("lineWidth");
        lineItem1 = new JMenuItem("1");
        lineItem2 = new JMenuItem("2");
        lineItem3 = new JMenuItem("3");
        lineItem4 = new JMenuItem("4");
        lineItem5 = new JMenuItem("5");
        lineItem6 = new JMenuItem("other Width");
        lineMenu.add(lineItem1);
        lineMenu.add(lineItem2);
        lineMenu.add(lineItem3);
        lineMenu.add(lineItem4);
        lineMenu.add(lineItem5);
        lineMenu.add(lineItem6);
        lineItem1.addActionListener(this);
        lineItem2.addActionListener(this);
        lineItem3.addActionListener(this);
        lineItem4.addActionListener(this);
        lineItem5.addActionListener(this);
        lineItem6.addActionListener(this);
        menuBar.add(lineMenu);

        //透明度の変更
        AlphaMenu = new JMenu("透明度");
        lineAlpha = new JMenuItem("輪郭");
        fillAlpha = new JMenuItem("塗りつぶし");
        AlphaMenu.add(lineAlpha);
        AlphaMenu.add(fillAlpha);
        lineAlpha.addActionListener(this);
        fillAlpha.addActionListener(this);
        menuBar.add(AlphaMenu);

        bfMenu = new JMenu("重なり順序の変更");
        setFront = new JMenuItem("前面に移動");
        setBack = new JMenuItem("背面に移動");
        FrontMost = new JMenuItem("最前面に移動");
        BackMost = new JMenuItem("最背面に移動");
        bfMenu.add(setFront);
        bfMenu.add(setBack);
        bfMenu.add(FrontMost);
        bfMenu.add(BackMost);
        setFront.addActionListener(this);
        setBack.addActionListener(this);
        FrontMost.addActionListener(this);
        BackMost.addActionListener(this);
        menuBar.add(bfMenu);

        //レイアの追加削除など
        layerMenu = new JMenu("layer");
        layerAdd = new JMenuItem("layerの追加");
        layerRemove = new JMenuItem("layerの削除");
        layerRename = new JMenuItem("layerの名称変更");
        layerSwap = new JMenuItem("layerの入れ替え");
        layerMenu.add(layerAdd);
        layerMenu.add(layerRemove);
        layerMenu.add(layerRename);
        layerMenu.add(layerSwap);
        layerAdd.addActionListener(this);
        layerRemove.addActionListener(this);
        layerRename.addActionListener(this);
        layerSwap.addActionListener(this);
        menuBar.add(layerMenu);

        this.addWindowListener(new WindowAdapter(){
            //ウインドウを閉じたら終了
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public Dimension getPerferredSize(){
        return new Dimension(300,400);
    }

    public void stateChanged(ChangeEvent e){//選択されているボタンからレイアを決定
        med.clearSelected();
        Enumeration<AbstractButton> bs = layer.getElements();
        int n = 0;
        while(bs.hasMoreElements()){
            AbstractButton b = bs.nextElement();
            if(b.isSelected()){
                med.setSelectLayer(n);
            }
            n += 1;
        }
        canvas.repaint();
    }

    //色変更のメソッド
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == save){
            savemanager.saveFile();
        }
        else if(e.getSource() == load){
            savemanager.loadFile();
            repaint();
        }
        else if(e.getSource() == redItem){
            med.setFillColor(Color.red);
        }
        else if(e.getSource() == redItem2){
            med.setLineColor(Color.red);
        }
        else if(e.getSource()==blueItem){
            med.setFillColor(Color.blue);
        }
        else if(e.getSource() == blueItem2){
            med.setLineColor(Color.blue);
        }
        else if(e.getSource() == greenItem){
            med.setFillColor(Color.green);
        }
        else if(e.getSource() == greenItem2){
            med.setLineColor(Color.green);
        }
        else if(e.getSource() == blackItem){
            med.setFillColor(Color.black);
        }
        else if(e.getSource() == blackItem2){
            med.setLineColor(Color.black);
        }
        else if(e.getSource() == whiteItem){
            med.setFillColor(Color.white);
        }
        else if(e.getSource() == whiteItem2){
            med.setLineColor(Color.white);
        }
        else if(e.getSource()== otherItem){
            med.Fill = true;
            if(colorChooser == null){
                colorChooser = new MyColorChooser();}
            colorChooser.med = this.med;
        }
        else if(e.getSource() == otherItem2){
            med.Fill = false;
            if(colorChooser == null){
                colorChooser = new MyColorChooser();}
            colorChooser.med = this.med;
        }
        else if(e.getSource() == lineItem1){
            med.setLineWidth(1);
        }
        else if(e.getSource() == lineItem2){
            med.setLineWidth(2);
        }
        else if(e.getSource() == lineItem3){
            med.setLineWidth(3);
        }
        else if(e.getSource()== lineItem4){
            med.setLineWidth(4);
        }
        else if(e.getSource()== lineItem5){
            med.setLineWidth(5);
        }
        else if(e.getSource()== lineItem6){
            med.setLineWidth(Integer.parseInt(jOptionPane.showInputDialog(null,"数値を入力してください","線の太さの変更",JOptionPane.PLAIN_MESSAGE)));
        }
        else if(e.getSource()== lineAlpha){
            med.setLineAlpha(Integer.parseInt(jOptionPane.showInputDialog(null,"数値を入力してください","透明度の変更",JOptionPane.PLAIN_MESSAGE)));
        }
        else if(e.getSource()== fillAlpha){
            med.setFillAlpha(Integer.parseInt(jOptionPane.showInputDialog(null,"数値を入力してください","透明度の変更",JOptionPane.PLAIN_MESSAGE)));
        }
        else if(e.getSource()== setFront){
            med.SetSelectedFront();
        }
        else if(e.getSource()== setBack){
            med.SetSelectedBack();
        }
        else if(e.getSource()== FrontMost){
            med.SetFrontMost();
        }
        else if(e.getSource()== BackMost){
            med.SetBackMost();
        }
        else if(e.getSource()== layerAdd){
            layerManager.addLayerButton(lp, layer);
            revalidate();
        }
        else if(e.getSource()== layerRemove){
            layerManager.removeLayer(lp, layer);
            revalidate();
        }
        else if(e.getSource()== layerRename){
            Enumeration<AbstractButton> bs = layer.getElements();
            while(bs.hasMoreElements()){
                AbstractButton b = bs.nextElement();
                if(b.isSelected()){
                    layerManager.renameLayer(b);
                }
            }
            revalidate();
        }
        else if(e.getSource()== layerSwap){
            if(med.layers.size()<2){
                jOptionPane.showMessageDialog(null, "レイアは一つしかありません", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JTextField back = new JTextField();
                JTextField front = new JTextField();
                Object[] message = {"layer:", back, "layer:", front};
                int option = JOptionPane.showConfirmDialog(null, message, "レイアの入れ替え", JOptionPane.INFORMATION_MESSAGE);
                layerManager.layerSwap(layer, Integer.parseInt(back.getText())-1, Integer.parseInt(front.getText())-1);
                revalidate();
            }
        }
        canvas.repaint();
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}
