
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Screen extends JPanel implements KeyListener, MouseListener, ActionListener {

    private MyHashTable<Location, GridObject> grid;
    private Image touristImgRight;
    private Image touristImgLeft;

    // private Thread t1;
    private int screenSize = 1000;
    public int numSquares = 200; // per side
    private int squareSize = 30;

    public Tourist player;
    private int playerX = (screenSize - squareSize) / 2;
    private int playerY = (screenSize - squareSize) / 2;

    private GridObject water, road, dirt, grass, tree, rock, mountain, cactus, sol, gg, rush, needle;
    private Info solInfo, ggInfo, rushInfo, needleInfo;
    // public boolean upArrowPressed, downArrowPressed,
    // rightArrowPressed,leftArrowPressed = false;
    private boolean gridLinesOn;
    private boolean movingRight;

    private Thread mapThread;
    private Map map;

    private DLList<Car> cars;
    private DLList<Bear> bears;
    private DLList<Whale> whales;

    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton saveButton;
    private JButton linesButton;

    private BufferedImage carImg;
    private BufferedImage bearImg;
    private BufferedImage whaleImg;

    public Screen() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        this.setLayout(null);
        // Buttons

        zoomInButton = new JButton();
        zoomInButton.setFont(new Font("Arial", Font.BOLD, 20));
        zoomInButton.setHorizontalAlignment(SwingConstants.CENTER);
        zoomInButton.setBounds(screenSize + 5, 650, 290, 30);
        zoomInButton.setText("Zoom In");
        this.add(zoomInButton);
        zoomInButton.addActionListener(this);
        zoomInButton.setFocusable(false);

        zoomOutButton = new JButton();
        zoomOutButton.setFont(new Font("Arial", Font.BOLD, 20));
        zoomOutButton.setHorizontalAlignment(SwingConstants.CENTER);
        zoomOutButton.setBounds(screenSize + 5, 700, 290, 30);
        zoomOutButton.setText("Zoom Out");
        this.add(zoomOutButton);
        zoomOutButton.addActionListener(this);
        zoomOutButton.setFocusable(false);

        saveButton = new JButton();
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setBounds(screenSize + 5, 600, 290, 30);
        saveButton.setText("Save");
        this.add(saveButton);
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);

        linesButton = new JButton();
        linesButton.setFont(new Font("Arial", Font.BOLD, 20));
        linesButton.setHorizontalAlignment(SwingConstants.CENTER);
        linesButton.setBounds(screenSize + 5, 550, 290, 30);
        linesButton.setText("GridLines On/Off");
        this.add(linesButton);
        linesButton.addActionListener(this);
        linesButton.setFocusable(false);

        gridLinesOn = true;
        movingRight = true;
        touristImgRight = new ImageIcon("touristR.gif").getImage();
        touristImgLeft = new ImageIcon("touristL.gif").getImage();

        grid = new MyHashTable<Location, GridObject>();
        water = new GridObject("water", Color.BLUE, squareSize);
        road = new GridObject("road", Color.black, squareSize);
        dirt = new GridObject("dirt", new Color(212, 162, 89), squareSize);
        grass = new GridObject("grass", new Color(138, 194, 123), squareSize);
        tree = new GridObject("tree", "tree.png", squareSize);
        mountain = new GridObject("mountain", "mountain.png", squareSize);
        rock = new GridObject("rock", "rock.png", squareSize);
        cactus = new GridObject("cactus", "cactus.png", squareSize);
        // GridObject bush = new GridObject("bush", "bush.png");

        sol = new GridObject("Statue Of Liberty", "sol.png", squareSize);
        gg = new GridObject("Golden Gate Bridge", "bridge.png", squareSize);
        rush = new GridObject("Mount Rushmore", "rush.png", squareSize);
        needle = new GridObject("Space Needle", "needle.png", squareSize);

        solInfo = new Info("solBig.png", "The Statue of Liberty, a gift from France,",
                "symbolizes freedom and hope, welcoming", "millions to New York Harbor since 1886.");
        ggInfo = new Info("ggBig.png", "The Golden Gate Bridge, completed in 1937,",
                "spans California's Golden Gate Strait,", "attracting visitors worldwide.");
        rushInfo = new Info("rushBig.png", "Mount Rushmore, completed in 1941, features",
                "carved faces of four U.S. presidents,", "symbolizing American history and patriotism.");
        needleInfo = new Info("needleBig.png", "The Space Needle, built in 1962",
                "for Seattle's World Fair, stands as a", "symbol of innovation and modern design.");

        // Set up grid

        for (int r = 0; r < numSquares; r++) {
            for (int c = 0; c < numSquares; c++) {
                grid.put(new Location(r, c), null);
            }
        }
        uploadImage();

        if (grid.get(new Location(91, 176)).size() == 3) {
            grid.get(new Location(91, 176)).remove(2);
        }
        grid.put(new Location(91, 176), sol);

        if (grid.get(new Location(107, 9)).size() == 3) {
            grid.get(new Location(107, 9)).remove(2);
        }
        grid.put(new Location(107, 9), gg);

        if (grid.get(new Location(77, 60)).size() == 3) {
            grid.get(new Location(77, 60)).remove(2);
        }
        grid.put(new Location(77, 60), rush);

        if (grid.get(new Location(62, 14)).size() == 3) {
            grid.get(new Location(62, 14)).remove(2);
        }
        grid.put(new Location(62, 14), needle);

        try {
            carImg = ImageIO.read(new File("car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bearImg = ImageIO.read(new File("bear.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            whaleImg = ImageIO.read(new File("whale.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // t1.start();

        Thread t2 = new Thread(new Animate(this));
        t2.start();

        // make the default tourist
        player = new Tourist(numSquares / 2, numSquares / 2);
        // load the old tourist
        String saveFileName = "TouristSave.dat";
        try {
            // Load the data file to read in
            FileInputStream fis = new FileInputStream(saveFileName);
            // Create a data stream to read in
            ObjectInputStream in = new ObjectInputStream(fis);
            // Read in the object from file
            // Casting to generics to produce unchecked type warning
            player = (Tourist) in.readObject();
            // Close all your data stream
            fis.close();
            in.close();
        } catch (FileNotFoundException ex) {
            player = new Tourist(numSquares / 2, numSquares / 2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (grid.get(new Location(player.getRow(), player.getCol())).size() == 3) {
            grid.get(new Location(player.getRow(), player.getCol())).remove(2);
        }

        map = new Map(0, 0, "map4.png", player, numSquares, squareSize, screenSize);

        cars = new DLList<Car>();
        saveFileName = "CarSave.dat";
        try {
            // Load the data file to read in
            FileInputStream fis = new FileInputStream(saveFileName);
            // Create a data stream to read in
            ObjectInputStream in = new ObjectInputStream(fis);
            // Read in the object from file
            // Casting to generics to produce unchecked type warning
            cars = (DLList<Car>) in.readObject();
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).setTable(grid);
                Thread t3 = new Thread(cars.get(i));
                t3.start();
            }
            // Close all your data stream
            fis.close();
            in.close();
        } catch (FileNotFoundException ex) {
            for (int i = 0; i < 10; i++) {
                Car car = new Car(grid, 100, 100, squareSize);
                cars.add(car);
                Thread t3 = new Thread(car);
                t3.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        bears = new DLList<Bear>();
        saveFileName = "BearSave.dat";
        try {
            // Load the data file to read in
            FileInputStream fis = new FileInputStream(saveFileName);
            // Create a data stream to read in
            ObjectInputStream in = new ObjectInputStream(fis);
            // Read in the object from file
            // Casting to generics to produce unchecked type warning
            bears = (DLList<Bear>) in.readObject();
            for (int i = 0; i < bears.size(); i++) {
                bears.get(i).setTable(grid);
                Thread t3 = new Thread(bears.get(i));
                t3.start();
            }
            // Close all your data stream
            fis.close();
            in.close();
        } catch (FileNotFoundException ex) {
            for (int i = 0; i < 3; i++) {
                Bear bear = new Bear(grid, 80, 100, squareSize);
                bears.add(bear);
                Thread t3 = new Thread(bear);
                t3.start();
            }
            for (int i = 0; i < 3; i++) {
                Bear bear = new Bear(grid, 90, 120, squareSize);
                bears.add(bear);
                Thread t3 = new Thread(bear);
                t3.start();
            }
            for (int i = 0; i < 3; i++) {
                Bear bear = new Bear(grid, 69, 112, squareSize);
                bears.add(bear);
                Thread t3 = new Thread(bear);
                t3.start();
            }
            for (int i = 0; i < 3; i++) {
                Bear bear = new Bear(grid, 96, 134, squareSize);
                bears.add(bear);
                Thread t3 = new Thread(bear);
                t3.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        whales = new DLList<Whale>();
        saveFileName = "WhaleSave.dat";
        try {
            // Load the data file to read in
            FileInputStream fis = new FileInputStream(saveFileName);
            // Create a data stream to read in
            ObjectInputStream in = new ObjectInputStream(fis);
            // Read in the object from file
            // Casting to generics to produce unchecked type warning
            whales = (DLList<Whale>) in.readObject();
            for (int i = 0; i < whales.size(); i++) {
                whales.get(i).setTable(grid);
                Thread t3 = new Thread(whales.get(i));
                t3.start();
            }
            // Close all your data stream
            fis.close();
            in.close();
        } catch (FileNotFoundException ex) {
            for (int i = 0; i < 10; i++) {
                Whale whale = new Whale(grid, 163, 94, squareSize, numSquares);
                whales.add(whale);
                Thread t3 = new Thread(whale);
                t3.start();
            }
            Whale whale = new Whale(grid, 77, 129, squareSize, numSquares);
            whales.add(whale);
            Thread t3 = new Thread(whale);
            t3.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(screenSize + 305, screenSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < numSquares; r++) {
            if (Math.abs(r - player.getRow()) <= screenSize / squareSize / 2) {
                for (int c = 0; c < numSquares; c++) {
                    if (Math.abs(c - player.getCol()) <= screenSize / squareSize / 2) {
                        DLList<GridObject> items = grid.get(new Location(r, c));
                        if (items != null) {
                            for (int i = 0; i < items.size(); i++) {
                                if (items.get(i) != null) {
                                    items.get(i).drawMe(g, squareSize * (c - player.getCol()) + playerX,
                                            squareSize * (r - player.getRow()) + playerY);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (Math.abs(car.getRow() - player.getRow()) <= screenSize / squareSize / 2
                    && Math.abs(car.getCol() - player.getCol()) <= screenSize / squareSize / 2) {
                g.drawImage(carImg, (car.getCol() - player.getCol()) * squareSize + playerX,
                        (car.getRow() - player.getRow()) * squareSize + playerY, squareSize, squareSize, null);
            }
        }

        for (int i = 0; i < bears.size(); i++) {
            Bear bear = bears.get(i);
            if (Math.abs(bear.getRow() - player.getRow()) <= screenSize / squareSize / 2
                    && Math.abs(bear.getCol() - player.getCol()) <= screenSize / squareSize / 2) {
                g.drawImage(bearImg, (bear.getCol() - player.getCol()) * squareSize + playerX,
                        (bear.getRow() - player.getRow()) * squareSize + playerY, squareSize, squareSize, null);
            }
        }

        for (int i = 0; i < whales.size(); i++) {
            Whale whale = whales.get(i);
            if (Math.abs(whale.getRow() - player.getRow()) <= screenSize / squareSize / 2
                    && Math.abs(whale.getCol() - player.getCol()) <= screenSize / squareSize / 2) {
                g.drawImage(whaleImg, (whale.getCol() - player.getCol()) * squareSize + playerX,
                        (whale.getRow() - player.getRow()) * squareSize + playerY, squareSize, squareSize, null);
            }
        }

        // 91, 176
        if (player.getRow() == 90 || player.getRow() == 91 || player.getRow() == 92) {
            if (player.getCol() == 176 || player.getCol() == 177 || player.getCol() == 175) {
                solInfo.drawMe(1000, 5, g);
            }
        }
        // 107, 9
        if (player.getRow() == 106 || player.getRow() == 107 || player.getRow() == 108) {
            if (player.getCol() == 8 || player.getCol() == 9 || player.getCol() == 10) {
                ggInfo.drawMe(1000, 5, g);
            }
        }
        // 62,14
        if (player.getRow() == 61 || player.getRow() == 62 || player.getRow() == 63) {
            if (player.getCol() == 13 || player.getCol() == 14 || player.getCol() == 15) {
                needleInfo.drawMe(1000, 5, g);
            }
        }
        // 77,60
        if (player.getRow() == 76 || player.getRow() == 77 || player.getRow() == 78) {
            if (player.getCol() == 59 || player.getCol() == 60 || player.getCol() == 61) {
                rushInfo.drawMe(1000, 5, g);
            }
        }

        drawTourist(player, g);
        map.drawMe(g);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Press Space to Open/Close Mini-Map", 1020, 500);
        g.drawString("Click on Mini-Map to Teleport", 1020, 530);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            // move up
            // upArrowPressed=true;
            moveUp();

        }
        if (e.getKeyCode() == 40) {
            // down
            // downArrowPressed=true;
            moveDown();

        }
        if (e.getKeyCode() == 39) {
            // right
            // rightArrowPressed=true;
            moveRight();

        }
        if (e.getKeyCode() == 37) {
            // left
            // leftArrowPressed=true;
            moveLeft();

        }
        if (e.getKeyCode() == 32) {
            map.changeDirection();
            if (mapThread != null) {
                mapThread.interrupt();
            }
            mapThread = new Thread(map);
            mapThread.start();

        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        // getting row/col from main grid
        // System.out.println("Row: " + ((e.getY()-playerY)/squareSize+
        // player.getRow()));
        // System.out.println("Col: " + ((e.getX()-playerX)/squareSize+
        // player.getCol()));

    }

    public void mousePressed(MouseEvent e) {
        if (map.getMouseCol(e.getX(), e.getY()) > -1) {
            // if(map.getMouseRow(e.getX(),e.getY())>-1){
            int row = map.getMouseRow(e.getX(), e.getY());
            int col = map.getMouseCol(e.getX(), e.getY());

            if (!grid.get(new Location(row, col)).get(1).equals(water) && grid.get(new Location(row, col)).size() < 3) {
                player.setRow(row);
                player.setCol(col);
            }

            //

        }
        // System.out.println(player.getRow()+ ", " + player.getCol());

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zoomInButton) {
            squareSize++;
            updateSquareSize();

        } else if (e.getSource() == zoomOutButton && squareSize > 1) {
            squareSize--;
            updateSquareSize();
        }

        else if (e.getSource() == saveButton) {
            try {
                String saveFileName = "TouristSave.dat";
                // Create a data file to write
                FileOutputStream fos = new FileOutputStream(saveFileName);
                // Create a stream to write
                ObjectOutputStream out = new ObjectOutputStream(fos);
                // Write object from stream
                out.writeObject(player);
                // Close all your data stream
                fos.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                String saveFileName = "CarSave.dat";
                // Create a data file to write
                FileOutputStream fos = new FileOutputStream(saveFileName);
                // Create a stream to write
                ObjectOutputStream out = new ObjectOutputStream(fos);
                // Write object from stream
                out.writeObject(cars);
                // Close all your data stream
                fos.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                String saveFileName = "BearSave.dat";
                // Create a data file to write
                FileOutputStream fos = new FileOutputStream(saveFileName);
                // Create a stream to write
                ObjectOutputStream out = new ObjectOutputStream(fos);
                // Write object from stream
                out.writeObject(bears);
                // Close all your data stream
                fos.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                String saveFileName = "WhaleSave.dat";
                // Create a data file to write
                FileOutputStream fos = new FileOutputStream(saveFileName);
                // Create a stream to write
                ObjectOutputStream out = new ObjectOutputStream(fos);
                // Write object from stream
                out.writeObject(whales);
                // Close all your data stream
                fos.close();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == linesButton) {
            gridLinesOn = !gridLinesOn;
            changeGridLines();
        }

    }

    public void uploadImage() {
        BufferedImage tempImg;
        try {
            tempImg = ImageIO.read(new File("map4.png"));
            for (int r = 0; r < numSquares; r++) {
                for (int c = 0; c < numSquares; c++) {

                    int pixel = tempImg.getRGB(c * 1000 / numSquares + 1000 / numSquares / 2,
                            r * 1000 / numSquares + 1000 / numSquares / 2);

                    Color tempColor = new Color(pixel, true);

                    double lowestDist = colorDist(dirt.getColor(), tempColor);
                    GridObject o = dirt;
                    if (colorDist(road.getColor(), tempColor) < lowestDist) {
                        o = road;
                        lowestDist = colorDist(road.getColor(), tempColor);
                    }

                    if (colorDist(grass.getColor(), tempColor) < lowestDist) {
                        o = grass;
                        lowestDist = colorDist(grass.getColor(), tempColor);
                    }

                    if (colorDist(water.getColor(), tempColor) < lowestDist) {
                        o = water;
                        lowestDist = colorDist(water.getColor(), tempColor);
                    }

                    if (o.equals(road)) {
                        grid.put(new Location(r, c), new GridObject(o.getName(), Color.black, squareSize));

                    } else if (o.equals(water)) {
                        grid.put(new Location(r, c), new GridObject(o.getName(), o.getColor(), squareSize));
                    } else {
                        grid.put(new Location(r, c), new GridObject(o.getName(), tempColor, squareSize));
                        if (o.getName().equals("grass")) {
                            // if(grid.get(new Location(r-1,c-1)).size()<3){
                            int num = (int) (Math.random() * 60);
                            if (num == 2) {
                                grid.put(new Location(r, c), tree);

                            }
                            if (num == 3) {
                                grid.put(new Location(r, c), rock);

                            }
                            // }
                        }
                        if (o.getName().equals("dirt")) {
                            // if(grid.get(new Location(r-1,c-1)).size()<3){
                            int num = (int) (Math.random() * 60);
                            if (num == 2) {
                                grid.put(new Location(r, c), mountain);

                            }

                            if (num == 3) {
                                grid.put(new Location(r, c), cactus);

                            }
                            // }
                        }

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    public void drawTourist(Tourist t, Graphics g) {
        // g.setColor(Color.pink);
        // g.fillOval(playerX,playerY,squareSize,squareSize);
        if (movingRight) {
            g.drawImage(touristImgRight, playerX, playerY, squareSize, squareSize, null);
        } else {
            g.drawImage(touristImgLeft, playerX, playerY, squareSize, squareSize, null);
        }
    }

    public MyHashTable<Location, GridObject> getGrid() {
        return grid;
    }

    private double colorDist(Color c1, Color c2) {
        return (Math.sqrt(Math.pow((c1.getRed() - c2.getRed()), 2) + Math.pow((c1.getGreen() - c2.getGreen()), 2)
                + Math.pow((c1.getBlue() - c2.getBlue()), 2)));
    }

    private void updateSquareSize() {

        playerX = (screenSize - squareSize) / 2;
        playerY = (screenSize - squareSize) / 2;

        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).setSquareSize(squareSize);
        }
        for (int i = 0; i < bears.size(); i++) {
            bears.get(i).setSquareSize(squareSize);
        }
        for (int i = 0; i < whales.size(); i++) {
            whales.get(i).setSquareSize(squareSize);
        }
        map.setSquareSize(squareSize);
        for (int r = 0; r < numSquares; r++) {

            for (int c = 0; c < numSquares; c++) {

                DLList<GridObject> items = grid.get(new Location(r, c));
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i) != null) {
                            items.get(i).setSquareSize(squareSize);
                        }
                    }
                }

            }

        }

    }

    public void moveUp() {
        int r = player.getRow();
        int c = player.getCol();
        if (r > 0 && !grid.get(new Location(r - 1, c)).get(1).getName().equals("water")
                && grid.get(new Location(r - 1, c)).size() < 3) {
            player.setRow(r - 1);
        }
    }

    public void moveDown() {
        int r = player.getRow();
        int c = player.getCol();
        if (r < numSquares - 1 && !grid.get(new Location(r + 1, c)).get(1).getName().equals("water")
                && grid.get(new Location(r + 1, c)).size() < 3) {
            player.setRow(r + 1);
        }
    }

    public void moveLeft() {
        int r = player.getRow();
        int c = player.getCol();
        if (c > 0 && !grid.get(new Location(r, c - 1)).get(1).getName().equals("water")
                && grid.get(new Location(r, c - 1)).size() < 3) {
            player.setCol(c - 1);
        }
        movingRight = false;
    }

    public void moveRight() {
        int r = player.getRow();
        int c = player.getCol();
        if (c < numSquares - 1 && !grid.get(new Location(r, c + 1)).get(1).getName().equals("water")
                && grid.get(new Location(r, c + 1)).size() < 3) {
            player.setCol(c + 1);
        }
        movingRight = true;
    }

    public void changeGridLines() {
        for (int r = 0; r < numSquares; r++) {
            for (int c = 0; c < numSquares; c++) {
                for (int i = 0; i < grid.get(new Location(r, c)).size(); i++) {
                    if (grid.get(new Location(r, c)).get(i) != null) {
                        grid.get(new Location(r, c)).get(i).changeGridLines(gridLinesOn);
                    }
                }

            }
        }
    }

}