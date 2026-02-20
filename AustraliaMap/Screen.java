
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.File;

public class Screen extends JPanel implements ActionListener {
    private WGraph<Location> graph;
    private ShortestPath<Location> shortestPath;
    private BufferedImage mapImg;

    private JButton b1;
    private JTextField t1;
    private JTextField t2;
    private JTextArea a1;

    private Location start;
    private Location end;
    private DLList<Location> pathList;

    private Car car;
    private Thread carThread;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        

        b1 = new JButton();

        Thread thread1 = new Thread(new Animate(this));
        thread1.start();

        try {
            mapImg = ImageIO.read(new File("map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Location l1 = new Location("Sydney", "syd", 616, 465);
        Location l2 = new Location("Melbourne", "mel", 516, 535);
        Location l3 = new Location("Brisbane", "bne", 643, 333);
        Location l4 = new Location("Boulder", "bld", 138, 415);
        Location l5 = new Location("Perth", "per", 54, 426);
        Location l6 = new Location("Adelaide", "adl", 415, 483);
        Location l7 = new Location("Geraldton", "ger", 31, 362);
        Location l8 = new Location("Broome", "bme", 157, 181);
        Location l9 = new Location("Darwin", "drw", 284, 102);
        Location l10 = new Location("Newcastle", "ncl", 629, 441);
        Location l11 = new Location("Karratha", "kta", 68, 231);
        Location l12 = new Location("Cairns", "cns", 522, 161);
        Location l13 = new Location("Townsville", "tsv", 541, 212);
        Location l14 = new Location("Toowoomba", "tba", 618, 327);
        Location l15 = new Location("Katherine", "kth", 305, 138);
        Location l16 = new Location("Ballarat", "bal", 489, 520);
        Location l17 = new Location("Bendigo", "ben", 491, 480);
        Location l18 = new Location("Alice Springs", "asp", 321, 285);
        Location l19 = new Location("Albany", "alb", 79, 486);
        Location l20 = new Location("Kings Canyon", "kgc", 286, 304);

        double scale = 443 / Math.sqrt(Math.pow((l1.getX() - l2.getX()), 2) + Math.pow((l1.getY() - l2.getY()), 2));
        graph = new WGraph<Location>(scale);

        graph.addData(l1);
        graph.addData(l2);
        graph.addData(l3);
        graph.addData(l4);
        graph.addData(l5);
        graph.addData(l6);
        graph.addData(l7);
        graph.addData(l8);
        graph.addData(l9);
        graph.addData(l10);
        graph.addData(l11);
        graph.addData(l12);
        graph.addData(l13);
        graph.addData(l14);
        graph.addData(l15);
        graph.addData(l16);
        graph.addData(l17);
        graph.addData(l18);
        graph.addData(l19);
        graph.addData(l20);

        
        graph.addEdge(l1, l2);
        graph.addEdge(l1, l3);
        graph.addEdge(l3, l2);
        graph.addEdge(l4, l3);
        graph.addEdge(l1, l10);
        graph.addEdge(l2, l16);
        graph.addEdge(l16, l17);
        graph.addEdge(l17, l16);
        graph.addEdge(l17, l1);
        graph.addEdge(l17, l13);
        graph.addEdge(l13, l12);
        graph.addEdge(l13, l18);
        graph.addEdge(l18, l20);
        graph.addEdge(l4, l19);
        graph.addEdge(l19, l5);
        graph.addEdge(l5, l4);
        graph.addEdge(l5, l7);
        graph.addEdge(l7, l11);
        graph.addEdge(l15, l9);
        graph.addEdge(l15, l8);
        graph.addEdge(l7, l8);
        graph.addEdge(l9, l20);
        graph.addEdge(l6, l17);
        graph.addEdge(l6, l14);
        graph.addEdge(l14, l13);
        graph.addEdge(l12, l18);
        graph.addEdge(l12, l6);
        graph.addEdge(l11, l4);
        graph.addEdge(l20, l4);
        graph.addEdge(l20, l6);
        graph.addEdge(l11, l18);
        graph.addEdge(l15, l13);

        shortestPath = new ShortestPath<Location>(graph);

        b1 = new JButton();
        b1.setFont(new Font("Arial", Font.BOLD, 15));
        b1.setHorizontalAlignment(SwingConstants.CENTER);
        b1.setBounds(715, 655, 200, 30);
        b1.setText("Get Directions");
        this.add(b1);
        b1.addActionListener(this);

        t1 = new JTextField();
        t1.setFont(new Font("Arial", Font.PLAIN, 15));
        t1.setHorizontalAlignment(SwingConstants.LEFT);
        t1.setBounds(715, 620, 95, 30);
        t1.setText("");
        t1.setFocusable(true);
        this.add(t1);

        t2 = new JTextField();
        t2.setFont(new Font("Arial", Font.PLAIN, 15));
        t2.setHorizontalAlignment(SwingConstants.LEFT);
        t2.setBounds(820, 620, 95, 30);
        t2.setText("");
        t2.setFocusable(true);
        this.add(t2);

        a1 = new JTextArea();
        a1.setFont(new Font("Arial", Font.PLAIN, 15));
        a1.setBounds(20, 520, 320, 170);
        a1.setEditable(false);
        this.add(a1);

    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 700);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapImg, 0, 0, null);
        graph.drawMe(g);

        g.setFont(new Font("Arial", 0, 15));
        for (int i = 0; i < graph.keySet().size(); i++) {
            g.drawString(graph.keySet().toDLList().get(i).toString(), 730, 30 + 25 * i);
        }

        g.drawString("Start:", 720, 615);
        g.drawString("End:", 825, 615);

        
        a1.setText("");
        if (pathList != null) {
            String toDraw = "";
            if (pathList.size() > 1) {
                toDraw = "First ";
            }
            int distance = 0;
            for (int i = 0; i < pathList.size() - 1; i++) {
                Location current = pathList.get(i);
                Location next = pathList.get(i + 1);
                if (i != 0) {
                    toDraw += "Then ";
                }
                toDraw += "take " + current.getName() + " to " + next.getName() + " - " + graph.getWeight(current, next) + "mi" + "\n";
                distance += graph.getWeight(current, next);
                g.setColor(Color.red);
                g.drawLine(current.getX(), current.getY(), next.getX(), next.getY());
            }
            toDraw += "The total distance is " + distance + " miles";
            a1.setText(toDraw);
        }

        if (car != null) {
            car.drawMe(g);
        }

    }

    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            start = null;
            end = null;

            if (t1.getText() != null) {
                for (int i = 0; i < graph.keySet().size(); i++) {
                    Location l = graph.keySet().toDLList().get(i);
                    if (l.getAbr().equalsIgnoreCase(t1.getText())) {
                        start = l;
                    }
                }
            }

            if (t2.getText() != null) {
                for (int i = 0; i < graph.keySet().size(); i++) {
                    Location l = graph.keySet().toDLList().get(i);
                    if (l.getAbr().equalsIgnoreCase(t2.getText())) {
                        end = l;
                    }
                }
            }
            pathList = null;
            if (start != null && end != null) {
                pathList = shortestPath.getShortestDLList(start, end);
                if (start != end) {
                    car = new Car(pathList);
                    carThread = new Thread(car);
                    carThread.start();
                } else {
                    car = null;
                }
            }
            repaint();
        }
    }
}
