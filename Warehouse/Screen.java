import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class Screen extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
    private DLList<Item> list = new DLList<Item>();
    private DLList<Item> options = new DLList<Item>();

    private boolean mouseDown = false;
    private double mouseX;
    private double mouseY;
    private int width = 1330;
    private int height = 750;
    private BufferedImage backgroundImg;
    private boolean gravityOn = false;
    private double gravity = .5;
    private double throwPower = .5;
    private boolean frictionOn = true;
    private boolean deleting = false;
    private double holdingOffSetX = 0, holdingOffSetY = 0;

    private Item holding = null;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JButton b11;
    private JButton b12;
    private JButton b13;
    private JButton b14;
    private JButton b15;

    public Screen() {
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        try {
            backgroundImg = ImageIO.read(new File("backgroundImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        options.add(new Item(1050, 50, 96, 90, "smallBox.png", true, .999));
        options.add(new Item(1050, 200, 200, 30, "pallet.png", true, .9999));
        options.add(new Item(1050, 250, 28, 18, "caution.png", true, .99995));
        options.add(new Item(1050, 300, 150, 140, "container.png", true, .998));
        options.add(new Item(1050, 450, 155, 200, "cabinet.png", true, .998));

        int offset = 10;

        b1 = new JButton();
        b1.setFont(new Font("Arial", Font.BOLD, 20));
        b1.setHorizontalAlignment(SwingConstants.CENTER);
        b1.setBounds(16 - 2 * offset, 650, 200 - offset, 30);
        b1.setText("Add Box");
        this.add(b1);
        b1.addActionListener(this);

        b2 = new JButton();
        b2.setFont(new Font("Arial", Font.BOLD, 20));
        b2.setHorizontalAlignment(SwingConstants.CENTER);
        b2.setBounds(228 - 3 * offset, 650, 200 - offset, 30);
        b2.setText("Add Pallet");
        this.add(b2);
        b2.addActionListener(this);

        b3 = new JButton();
        b3.setFont(new Font("Arial", Font.BOLD, 15));
        b3.setHorizontalAlignment(SwingConstants.CENTER);
        b3.setBounds(439 - 4 * offset, 650, 200 - offset, 30);
        b3.setText("Add Caution Sign");
        this.add(b3);
        b3.addActionListener(this);

        b4 = new JButton();
        b4.setFont(new Font("Arial", Font.BOLD, 20));
        b4.setHorizontalAlignment(SwingConstants.CENTER);
        b4.setBounds(650 - 5 * offset, 650, 200 - offset, 30);
        b4.setText("Add Container");
        this.add(b4);
        b4.addActionListener(this);

        b5 = new JButton();
        b5.setFont(new Font("Arial", Font.BOLD, 20));
        b5.setHorizontalAlignment(SwingConstants.CENTER);
        b5.setBounds(862 - 6 * offset, 650, 200 - offset, 30);
        b5.setText("Add Cabinet");
        this.add(b5);
        b5.addActionListener(this);

        b6 = new JButton();
        b6.setFont(new Font("Arial", Font.BOLD, 20));
        b6.setHorizontalAlignment(SwingConstants.CENTER);
        b6.setBounds(16 - 2 * offset, 600, 200 - offset, 30);
        b6.setText("Remove Box");
        this.add(b6);
        b6.addActionListener(this);

        b7 = new JButton();
        b7.setFont(new Font("Arial", Font.BOLD, 20));
        b7.setHorizontalAlignment(SwingConstants.CENTER);
        b7.setBounds(228 - 3 * offset, 600, 200 - offset, 30);
        b7.setText("Remove Pallet");
        this.add(b7);
        b7.addActionListener(this);

        b8 = new JButton();
        b8.setFont(new Font("Arial", Font.BOLD, 15));
        b8.setHorizontalAlignment(SwingConstants.CENTER);
        b8.setBounds(439 - 4 * offset, 600, 200 - offset, 30);
        b8.setText("Remove Caution Sign");
        this.add(b8);
        b8.addActionListener(this);

        b9 = new JButton();
        b9.setFont(new Font("Arial", Font.BOLD, 18));
        b9.setHorizontalAlignment(SwingConstants.CENTER);
        b9.setBounds(650 - 5 * offset, 600, 200 - offset, 30);
        b9.setText("Remove Container");
        this.add(b9);
        b9.addActionListener(this);

        b10 = new JButton();
        b10.setFont(new Font("Arial", Font.BOLD, 18));
        b10.setHorizontalAlignment(SwingConstants.CENTER);
        b10.setBounds(861 - 6 * offset, 600, 200 - offset, 30);
        b10.setText("Remove Cabinet");
        this.add(b10);
        b10.addActionListener(this);

        b11 = new JButton();
        b11.setFont(new Font("Arial", Font.BOLD, 20));
        b11.setHorizontalAlignment(SwingConstants.CENTER);
        b11.setBounds(228 - 3 * offset, 700, 200 - offset, 30);
        b11.setText("Clear");
        this.add(b11);
        b11.addActionListener(this);

        b12 = new JButton();
        b12.setFont(new Font("Arial", Font.BOLD, 15));
        b12.setHorizontalAlignment(SwingConstants.CENTER);
        b12.setBounds(439 - 4 * offset, 700, 200 - offset, 30);
        b12.setText("Gravity On/Off");
        this.add(b12);
        b12.addActionListener(this);

        b13 = new JButton();
        b13.setFont(new Font("Arial", Font.BOLD, 18));
        b13.setHorizontalAlignment(SwingConstants.CENTER);
        b13.setBounds(650 - 5 * offset, 700, 200 - offset, 30);
        b13.setText("Friction On/Off");
        this.add(b13);
        b13.addActionListener(this);

        b14 = new JButton();
        b14.setFont(new Font("Arial", Font.BOLD, 18));
        b14.setHorizontalAlignment(SwingConstants.CENTER);
        b14.setBounds(16 - 2 * offset, 700, 200 - offset, 30);
        b14.setText("Deleting On/Off");
        this.add(b14);
        b14.addActionListener(this);

        b15 = new JButton();
        b15.setFont(new Font("Arial", Font.BOLD, 18));
        b15.setHorizontalAlignment(SwingConstants.CENTER);
        b15.setBounds(861 - 6 * offset, 700, 200 - offset, 30);
        b15.setText("Randomize");
        this.add(b15);
        b15.addActionListener(this);

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);
        b7.setFocusable(false);
        b8.setFocusable(false);
        b9.setFocusable(false);
        b10.setFocusable(false);
        b11.setFocusable(false);
        b12.setFocusable(false);
        b13.setFocusable(false);
        b14.setFocusable(false);
        b15.setFocusable(false);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            double x = Math.random() * 800;
            double y = Math.random() * 400;
            list.add(new Item(x, y, 96, 90, "smallBox.png", true, .999));
        }
        if (e.getSource() == b2) {
            double x = Math.random() * 800;
            double y = Math.random() * 400;
            list.add(new Item(x, y, 200, 30, "pallet.png", true, .9999));
        }
        if (e.getSource() == b3) {
            double x = Math.random() * 800;
            double y = Math.random() * 400;
            list.add(new Item(x, y, 28, 18, "caution.png", true, .99995));

        }
        if (e.getSource() == b4) {
            double x = Math.random() * 800;
            double y = Math.random() * 400;
            list.add(new Item(x, y, 150, 140, "container.png", true, .998));
        }
        if (e.getSource() == b5) {
            double x = Math.random() * 800;
            double y = Math.random() * 400;
            list.add(new Item(x, y, 155, 200, "cabinet.png", true, .998));
        }

        if (e.getSource() == b6) {
            DLList<Integer> toRemove = new DLList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFile().equals("smallBox.png")) {
                    toRemove.add(i);
                }
            }
            if (toRemove.size() > 0) {
                int index = (int) (Math.random() * toRemove.size());
                list.remove((int) toRemove.get(index));
            }
        }
        if (e.getSource() == b7) {
            DLList<Integer> toRemove = new DLList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFile().equals("pallet.png")) {
                    toRemove.add(i);
                }
            }
            if (toRemove.size() > 0) {
                int index = (int) (Math.random() * toRemove.size());
                list.remove((int) toRemove.get(index));
            }
        }
        if (e.getSource() == b8) {
            DLList<Integer> toRemove = new DLList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFile().equals("caution.png")) {
                    toRemove.add(i);
                }
            }
            if (toRemove.size() > 0) {
                int index = (int) (Math.random() * toRemove.size());
                list.remove((int) toRemove.get(index));
            }
        }
        if (e.getSource() == b9) {
            DLList<Integer> toRemove = new DLList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFile().equals("container.png")) {
                    toRemove.add(i);
                }
            }
            if (toRemove.size() > 0) {
                int index = (int) (Math.random() * toRemove.size());
                list.remove((int) toRemove.get(index));
            }
        }
        if (e.getSource() == b10) {
            DLList<Integer> toRemove = new DLList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFile().equals("cabinet.png")) {
                    toRemove.add(i);
                }
            }
            if (toRemove.size() > 0) {
                int index = (int) (Math.random() * toRemove.size());
                list.remove((int) toRemove.get(index));
            }
        }

        if (e.getSource() == b11) {
            list.clear();
            repaint();
        }

        if (e.getSource() == b12) {
            if (!gravityOn) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setVelX(0);
                    list.get(i).setVelY(0);
                }
            }
            gravityOn = !gravityOn;
        }

        if (e.getSource() == b13) {
            frictionOn = !frictionOn;
        }

        if (e.getSource() == b14) {
            deleting = !deleting;
        }

        if (e.getSource() == b15) {
            for (int i = 0; i < list.size(); i++) {
                double x = Math.random() * 800;
                double y = Math.random() * 400;
                list.get(i).setX(x);
                list.get(i).setY(y);
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            // Space is pressed

            if (!gravityOn) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setVelX(0);
                    list.get(i).setVelY(0);
                }
            }
            gravityOn = !gravityOn;

        }
        if (e.getKeyCode() == 8) {
            list.clear();
            repaint();
        }
        if (e.getKeyCode() == 70) {
            frictionOn = !frictionOn;
        }
        if (e.getKeyCode() == 68) {
            deleting = !deleting;
        }

        // System.out.println(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mouseDown = true;

        mouseX = e.getX();
        mouseY = e.getY();
        if (deleting) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).detectPress((int) mouseX, (int) mouseY)) {
                    list.remove(i);
                }
            }
        } else {
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).detectPress((int) mouseX, (int) mouseY)) {
                    Item pressing = options.get(i);
                    list.add(new Item(pressing.getX(), pressing.getY(), pressing.getWidth(), pressing.getHeight(),
                            pressing.getFile(), true, pressing.getFriction()));
                }
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).detectPress((int) mouseX, (int) mouseY)) {
                    holding = list.get(i);
                    holdingOffSetX = holding.getX() - e.getX();
                    holdingOffSetY = holding.getY() - e.getY();
                }
            }
        }
        repaint();

    }

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;

        if (holding != null && gravityOn) {
            holding.setVelX((e.getX() - mouseX) * throwPower);
            holding.setVelY((e.getY() - mouseY) * throwPower);
        }
        holding = null;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

        if (mouseDown && holding != null) {
            holding.setX(e.getX() + holdingOffSetX);
            holding.setY(e.getY() + holdingOffSetY);
            holding.setVelX((e.getX() - mouseX));
            holding.setVelY((e.getY() - mouseY));
            // System.out.println(holding.getVelY());

        }
        if (mouseDown) {
            // repaint();
        }
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, null);
        for (int i = 0; i < options.size(); i++) {
            options.get(i).drawMe(g);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).drawMe(g);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 1, 15));
        if (gravityOn) {
            g.drawString("Gravity - ON", 1000, 700);
        } else {
            g.drawString("Gravity - OFF", 1000, 700);
        }
        if (frictionOn) {
            g.drawString("Friction - ON", 1000, 720);
        } else {
            g.drawString("Friction - OFF", 1000, 720);
        }
        if (deleting) {
            g.drawString("Deleting - ON", 1000, 740);
        } else {
            g.drawString("Deleting - OFF", 1000, 740);
        }
        g.drawString("When Deleting is on,", 1110, 680);
        g.drawString("clicking something will", 1110, 700);
        g.drawString("remove it. Otherwise,", 1110, 720);
        g.drawString("clicking an object picks it up.", 1110, 740);
    }

    public void animate() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (gravityOn) {
                for (int i = 0; i < list.size(); i++) {
                    Item item = list.get(i);
                    if (holding == null || !holding.equals(item)) {
                        item.setVelY(item.getVelY() + gravity);
                        move(item);
                        // System.out.println(item.getVelX());

                    }
                    // }else{
                    // item.setVelY(0);
                    // }

                }
                for (int i = 0; i < list.size(); i++) {
                    Item item = list.get(i);

                    for (int j = 0; j < list.size(); j++) {
                        if (i != j) {
                            Item other = list.get(j);
                            if (item.intersection(other)) {

                                if (item.getY() <= other.getY()) {

                                    // System.out.println(other.getVelY());
                                    item.setY(other.getY() - item.getHeight());
                                    item.setVelY(other.getVelY());

                                    if (frictionOn) {
                                        // item.setVelX((int) item.getVelX()*item.getFriction());
                                        if (holding == null || !holding.equals(other)) {
                                            item.setVelX(other.getVelX());
                                        } else {
                                            item.setVelX(other.getVelX() * .7);
                                        }
                                    }
                                }

                                // item.setVelX(item.getVelX()+other.getVelX());

                            }
                        }

                    }

                }

            }
            repaint();

        }
    }

    public void move(Item item) {
        // System.out.println("moving");
        if (item.getY() + item.getVelY() + item.getHeight() < 600) {
            item.setY(item.getY() + item.getVelY());
        } else {
            item.setVelY(0);
            if (frictionOn) {
                item.setVelX((int) item.getVelX() * item.getFriction());
            }
            item.setY(600 - item.getHeight());
        }

        item.setX(item.getX() + item.getVelX());
        // for(int i = 0; i<list.size(); i++){

        // if(list.get(i).intersection(item)){
        // Item other = list.get(i);
        // if(item.getY()<other.getY()){
        // item.setX(item.getX()+other.getVelX());
        // }
        // }
        // }

    }

}
