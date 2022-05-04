import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame implements ActionListener{
    StateManager stateManager;
    MyCanvas canvas;
    private JMenuBar menuBar;
    private JMenu colorMenu,colorMenu2,lineMenu,FileMenu;
    private JMenuItem save, load;
    private JMenuItem redItem, blueItem, greenItem, blackItem, whiteItem, otherItem;
    private JMenuItem redItem2, blueItem2, greenItem2, blackItem2, whiteItem2, otherItem2;
    private JMenuItem lineItem1, lineItem2, lineItem3, lineItem4, lineItem5;    
    Mediator med;
    MyColorChooser colorChooser;
    SaveManager savemanager;

    public MyApplication(){
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        this.med = canvas.mediator;
        savemanager = new SaveManager(med);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
    
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

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp,BorderLayout.NORTH);
        getContentPane().add(canvas,BorderLayout.CENTER);

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

        lineMenu = new JMenu("lineWidth");
        lineItem1 = new JMenuItem("1");
        lineItem2 = new JMenuItem("2");
        lineItem3 = new JMenuItem("3");
        lineItem4 = new JMenuItem("4");
        lineItem5 = new JMenuItem("5");
        lineMenu.add(lineItem1);
        lineMenu.add(lineItem2);
        lineMenu.add(lineItem3);
        lineMenu.add(lineItem4);
        lineMenu.add(lineItem5);
        lineItem1.addActionListener(this);
        lineItem2.addActionListener(this);
        lineItem3.addActionListener(this);
        lineItem4.addActionListener(this);
        lineItem5.addActionListener(this);
        menuBar.add(lineMenu);



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
        else if(e.getSource()== otherItem2){
            med.Fill = false;
            if(colorChooser == null){
                colorChooser = new MyColorChooser();}
            colorChooser.med = this.med;
        }
        else if(e.getSource()== lineItem1){
            med.setLineWidth(1);
        }
        else if(e.getSource()== lineItem2){
            med.setLineWidth(2);
        }
        else if(e.getSource()== lineItem3){
            med.setLineWidth(3);
        }
        else if(e.getSource()== lineItem4){
            med.setLineWidth(4);
        }
        else if(e.getSource()== lineItem5){
        }

        canvas.repaint();
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}
