import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.MemoryImageSource;
import java.util.Calendar;
import java.io.*;

class WindowInt extends JFrame implements ActionListener, KeyListener {

    private String strInput = "";
    private String tempStrInput = "not_set";
    private String lastButtonClicked = "reset";
    JButton add = new JButton("+");
    JButton subtract = new JButton("-");
    JButton mul = new JButton("*");
    JButton div = new JButton("/");
    JButton exp = new JButton("Exp. to");
    JButton rt = new JButton("Root to");
    JButton eq = new JButton("=");
    JButton ca = new JButton("CA");
    JLabel Jtxt1 = new JLabel();
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b0 = new JButton("0");
    JButton bDot = new JButton(".");
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu apprnce=new JMenu("Appearance");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem jLAF=new JMenuItem("Java Look And Feel");
    JMenuItem mLAF=new JMenuItem("Motif Look And Feel");
    JMenuItem nLAF=new JMenuItem("Nimbus Look and Feel");
    JMenuItem wLAF=new JMenuItem("Windows Look And Feel");
    SystemTray tray = SystemTray.getSystemTray();
    PopupMenu popup = new PopupMenu();
    MenuItem exitItem = new MenuItem("Exit");
    MenuItem minimise = new MenuItem("Hide");
    MenuItem maximise = new MenuItem("Show");

    java.net.URL url = WindowInt.class.getResource("AppIcon.gif");
    TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(url), "Calculator", popup);
    public boolean bln1 = true;

     boolean cursorInvisible=false;
     //Some Look And Feel provide their own titlebar and the native titlebar has to be made invisible.
     boolean lastNativeTitleBarShown=true;

    WindowInt() throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        super("Calculator");
        this.setLayout(null);


        tray.add(icon);
        icon.displayMessage("Calculator", "Application Started.", TrayIcon.MessageType.INFO);
        popup.add(exitItem);
        popup.add(maximise);
        popup.add(minimise);
        maximise.setEnabled(false);
        exitItem.addActionListener(this);
        maximise.addActionListener(this);
        minimise.addActionListener(this);
        Container cnt = this.getContentPane();
        cnt.setLayout(null);
        cnt.add(add);
        cnt.add(this.b1);
        cnt.add(this.b2);
        cnt.add(this.b3);
        cnt.add(this.Jtxt1);
        cnt.add(this.b4);
        cnt.add(this.b5);
        cnt.add(this.b6);
        cnt.add(this.b7);
        cnt.add(this.b8);
        cnt.add(this.b9);
        cnt.add(this.b0);
        cnt.add(this.bDot);
        cnt.add(this.add);
        cnt.add(this.subtract);
        cnt.add(this.mul);
        cnt.add(this.div);
        cnt.add(this.exp);
        cnt.add(this.rt);
        cnt.add(this.eq);
        cnt.add(this.ca);
        cnt.add(this.menu);
        menu.add(file);
        menu.add(apprnce);
        file.add(exit);
        apprnce.add(jLAF);
        apprnce.add(mLAF);
        apprnce.add(this.nLAF);
        apprnce.add(wLAF);
        exit.addActionListener(this);
        exit.setToolTipText("Closes the application");
        jLAF.addActionListener(this);
        mLAF.addActionListener(this);
        nLAF.addActionListener(this);
        wLAF.addActionListener(this);

        menu.setBounds(1, 1, 280, 25);

        b1.setBounds(100 + 20 + 10, 60, 45, 45);
        b2.setBounds(150 + 20 + 10, 60, 45, 45);
        b3.setBounds(200 + 20 + 10, 60, 45, 45);
        b4.setBounds(100 + 20 + 10, 110, 45, 45);
        b5.setBounds(150 + 20 + 10, 110, 45, 45);
        b6.setBounds(200 + 20 + 10, 110, 45, 45);
        b7.setBounds(100 + 20 + 10, 160, 45, 45);
        b8.setBounds(150 + 20 + 10, 160, 45, 45);
        b9.setBounds(200 + 20 + 10, 160, 45, 45);
        b0.setBounds(150 + 20 + 10, 210, 45, 45);
        bDot.setBounds(200 + 20 + 10, 210, 45, 45);
        add.setBounds(50 + 20 + 10, 60, 45, 45);
        subtract.setBounds(50 + 20 + 10, 110, 45, 45);
        mul.setBounds(50 + 20 + 10, 160, 45, 45);
        div.setBounds(50 + 20 + 10, 210, 45, 45);
        exp.setBounds(0, 60, 75, 45);
        rt.setBounds(0, 110, 75, 45);
        eq.setBounds(130, 210, 45, 45);
        ca.setBounds(20, 160, 55, 45);


        Jtxt1.addKeyListener(this);

        //Add keyListener to all Components
        b1.addKeyListener(this);
        b2.addKeyListener(this);
        b3.addKeyListener(this);
        b4.addKeyListener(this);
        b5.addKeyListener(this);
        b6.addKeyListener(this);
        b7.addKeyListener(this);
        b8.addKeyListener(this);
        b9.addKeyListener(this);
        b0.addKeyListener(this);
        bDot.addKeyListener(this);
        add.addKeyListener(this);
        subtract.addKeyListener(this);
        mul.addKeyListener(this);
        div.addKeyListener(this);
        exp.addKeyListener(this);
        rt.addKeyListener(this);
        eq.addKeyListener(this);
        ca.addKeyListener(this);

        //Remove default Enter and Space key bindings on all buttons except eq 
	b1.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b1.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b2.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b2.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b3.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b3.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b4.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b4.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b5.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b5.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b6.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b6.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b7.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b7.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b8.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b8.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b9.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b9.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	b0.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	b0.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	bDot.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	bDot.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	add.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	add.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	subtract.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	subtract.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	mul.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	mul.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	div.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	div.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	exp.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	exp.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	rt.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	rt.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	ca.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
                            "none");
	ca.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");
	//Enter key binding should not be removed for eq.
	eq.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                            "none");



        Jtxt1.setBounds(100, 35, 146, 20);
        Jtxt1.setOpaque(true);
        Jtxt1.setBackground(new Color(255, 255, 255));
        Jtxt1.setText(strInput);
        this.setBounds(500, 100, 285, 300);
        this.setVisible(true);
        this.setResizable(false);
        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                if (strInput.equals("cleared")) {
                    strInput = "1";
                } else {
                    strInput += "1";
                }
                Jtxt1.setText(strInput);


            }
        });
        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                if (strInput.equals("cleared")) {
                    strInput = "2";
                } else {
                    strInput += "2";
                }
                Jtxt1.setText(strInput);



            }
        });
        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "3";
                } else {
                    strInput += "3";
                }
                Jtxt1.setText(strInput);


            }
        });
        b4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "4";
                } else {
                    strInput += "4";
                }
                Jtxt1.setText(strInput);


            }
        });
        b5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "5";
                } else {
                    strInput += "5";
                }
                Jtxt1.setText(strInput);


            }
        });
        b6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "6";
                } else {
                    strInput += "6";
                }
                Jtxt1.setText(strInput);


            }
        });
        b7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "7";
                } else {
                    strInput += "7";
                }
                Jtxt1.setText(strInput);


            }
        });
        b8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "8";
                } else {
                    strInput += "8";
                }
                Jtxt1.setText(strInput);


            }
        });
        b9.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "9";
                } else {
                    strInput += "9";
                }
                Jtxt1.setText(strInput);


            }
        });
        b0.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")) {
                    strInput = "0";
                } else {
                    strInput += "0";
                }
                Jtxt1.setText(strInput);
            }
        });
        bDot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                if (strInput.equals("cleared")||strInput.isEmpty()) {
                    strInput = "0";
                }
                
                while (!strInput.contains(".")) {
                    strInput += ".";
                    Jtxt1.setText(strInput);
                    
                }


            }
        });
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();

                try {
                    addNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }

                lastButtonClicked = "add";
            }
        });
        subtract.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
                try {
                    subNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }

                lastButtonClicked = "sub";
            }
        });
        mul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
                try {
                    mulNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }
                lastButtonClicked = "mul";
            }
        });
        div.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
                try {
                    divNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }
                lastButtonClicked = "div";
            }
        });
        exp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
                try {
                    expNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }
                lastButtonClicked = "exp";
            }
        });
        rt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
                try {
                    rtNo(Double.parseDouble(strInput));
                } catch (NumberFormatException numberFormatException) {
                }
                lastButtonClicked = "rt";
            }
        });
        eq.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                clearStage();
            }
        });
        ca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                reset();
                Jtxt1.setText(strInput);
            }
        });

        System.out.println(this.requestFocusInWindow());
        this.addKeyListener(this);
    }

    private void addNo(double dblStrInput) {

        System.out.println("cleared?" + strInput.equals("cleared"));

        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                dbltempStrInput += dblStrInput;
                strInput = Double.toString(dbltempStrInput);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
            
        }

    }

    private void subNo(double dblStrInput) {
        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                dbltempStrInput -= dblStrInput;
                strInput = Double.toString(dbltempStrInput);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
        }

    }

    private void mulNo(double dblStrInput) {
        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                dbltempStrInput *= dblStrInput;
                strInput = Double.toString(dbltempStrInput);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
        }

    }

    private void divNo(double dblStrInput) {
        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                dbltempStrInput /= dblStrInput;
                strInput = Double.toString(dbltempStrInput);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
        }

    }

    private void expNo(double dblStrInput) {
        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                double f = Math.pow(dbltempStrInput, dblStrInput);
                strInput = Double.toString(f);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
        }

    }

    private void rtNo(double dblStrInput) {
        if (!strInput.equals("cleared")) {
            if (tempStrInput.equals("not_set")) {
                tempStrInput = strInput;
            } else {
                System.out.println("Entered");
                double dbltempStrInput = Double.parseDouble(tempStrInput);
                double f = Math.pow(dbltempStrInput, 1 / dblStrInput);
                strInput = Double.toString(f);
                Jtxt1.setText(strInput);
                tempStrInput = strInput;
            }
            strInput = "cleared";
        }

    }
    //Checks whether any mathematical operation is completed before user clicks another operator.If not,this method finishes the first operation before going into the next.

    private void clearStage() {
        if (lastButtonClicked.equals("reset")) {
        }
        if (lastButtonClicked.equals("add")) {
            try {
                addNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        if (lastButtonClicked.equals("sub")) {
            try {
                subNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        if (lastButtonClicked.equals("mul")) {
            try {
                mulNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        if (lastButtonClicked.equals("div")) {
            try {
                divNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        if (lastButtonClicked.equals("exp")) {
            try {
                expNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }
        if (lastButtonClicked.equals("rt")) {
            try {
                rtNo(Double.parseDouble(strInput));
            } catch (NumberFormatException numberFormatException) {
            }
        }

    }
    //Resets the calculator

    private void reset() {
        tempStrInput = "not_set";
        strInput = "cleared";
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            bln1 = false;
            System.exit(0);
        }
        if (e.getActionCommand().equalsIgnoreCase("Show")) {
            this.setVisible(true);
            maximise.setEnabled(false);
            minimise.setEnabled(true);
        }
        if (e.getActionCommand().equalsIgnoreCase("Hide")) {
            this.setVisible(false);
            maximise.setEnabled(true);
            minimise.setEnabled(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("Java Look And Feel")){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    try {
                UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
                updateUI(false);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(WindowInt.class.getName()).log(Level.SEVERE, null, ex);
            } 
                }
            });

           
        }
        if(e.getActionCommand().equalsIgnoreCase("Motif Look And Feel")){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    try {
                UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(WindowInt.class.getName()).log(Level.SEVERE, null, ex);
            }
                    updateUI(true);
                }
            });
            
        }
        if(e.getActionCommand().equalsIgnoreCase("Nimbus Look And Feel")){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                try {
                UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(WindowInt.class.getName()).log(Level.SEVERE, null, ex);
            }
                updateUI(true);
                }

            });
            
        }
        if(e.getActionCommand().equalsIgnoreCase("Windows Look And Feel")){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                try {
                UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null,"This Look And Feel is not Supported!","Error: Unsupported Look And Feel",JOptionPane.WARNING_MESSAGE);
            }
                updateUI(true);
                }

            });
        }

    }

    public void updateUI(boolean showNativeTitleBar){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        if(lastNativeTitleBarShown==showNativeTitleBar){
            SwingUtilities.updateComponentTreeUI(this);
        }else{

        if(!showNativeTitleBar){
        this.dispose();
        this.setUndecorated(true);
        SwingUtilities.updateComponentTreeUI(this);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setVisible(true);

        }else{
        this.dispose();
        this.setUndecorated(false);
        SwingUtilities.updateComponentTreeUI(this);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setVisible(true);

        }
        }
        this.setCursor(Cursor.getDefaultCursor());

        lastNativeTitleBarShown=showNativeTitleBar;
        }

    public void keyTyped(KeyEvent evt) {

        if(evt.getKeyChar()=='h'||evt.getKeyChar()=='H'){
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");

        if(!cursorInvisible){
        this.setCursor(transparentCursor);
        cursorInvisible=true;
        }else{
            this.setCursor(Cursor.getDefaultCursor());
            cursorInvisible=false;
        }

        }

        /*System.out.println("a");*/

        if(evt.getKeyChar()=='1'){
            b1.doClick();
        }else if(evt.getKeyChar()=='2'){
            b2.doClick();
        }else if(evt.getKeyChar()=='3'){
            b3.doClick();
        }else if(evt.getKeyChar()=='4'){
            b4.doClick();
        }else if(evt.getKeyChar()=='5'){
            b5.doClick();
        }else if(evt.getKeyChar()=='6'){
            b6.doClick();
        }else if(evt.getKeyChar()=='7'){
            b7.doClick();
        }else if(evt.getKeyChar()=='8'){
            b8.doClick();
        }else if(evt.getKeyChar()=='9'){
            b9.doClick();
        }else if(evt.getKeyChar()=='0'){
            b0.doClick();
        }else if(evt.getKeyChar()=='.'){
            bDot.doClick();
        }else if(evt.getKeyChar()=='+'){
            add.doClick();
        }else if(evt.getKeyChar()=='-'){
            subtract.doClick();
        }else if(evt.getKeyChar()=='*'){
            mul.doClick();
        }else if(evt.getKeyChar()=='/'){
            div.doClick();
        }else if(evt.getKeyChar()=='r'){
            rt.doClick();
        }else if(evt.getKeyChar()=='e'){
            exp.doClick();
        }else if(evt.getKeyChar()=='c'){
            ca.doClick();
        }else if(evt.getKeyChar()=='\n'){
            eq.doClick();

        }

    }

    public void keyPressed(KeyEvent evt) {
        /*System.out.println("b");*/


    }

    public void keyReleased(KeyEvent evt) {

        /*System.out.println("C");*/



    }

}

/**
 * 
 * 
 * @author Mayank Verma
 * @version 2
 */
public class GUI_Calculator {

    /**
     * Constructor for objects of class GUI_Calculator
     */
    public GUI_Calculator() {
    }


    /**
     * main method, application starts here.
     * 
     * @param args execution arguments
     */
    public static void main(String[] args) throws AWTException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, FileNotFoundException, IOException {
        //Thread.currentThread().setName("main");


        WindowInt a;
        System.out.println(System.getProperty("user.dir"));
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        WindowInt.setDefaultLookAndFeelDecorated(true);
        a = new WindowInt();

        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

