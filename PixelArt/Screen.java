import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class Screen extends JPanel implements MouseListener,MouseMotionListener,ActionListener{

    private int mouseX=0,mouseY=0;
    private boolean mouseDown;
    
    private Square[][] grid;
    private ArrayList<Square> needToRepaint = new ArrayList<Square>();
    private int amountOfSquares = 20;
    private int gridSize = 700;
    private int squareSize = gridSize/amountOfSquares;
    
    private Color activeColor = Color.black;
    
    private Square[][] colorGrid;
    private int amountOfColors =50; //per side
    private int colorGridSize = 200;
    private int colorSquareSize = colorGridSize/amountOfColors;
    
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;


    private BufferedImage myImage;
    private BufferedImage gridImg;

    private BufferedImage colorPickerImg;
    private BufferedImage fillToolImg;
    

    private Graphics gridG;

    public boolean fillTool;
    public boolean colorPicker;

    public Color gridColor = Color.black;

    public Screen(){
        this.setLayout(null);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        // gridImg = (BufferedImage)(createImage(gridSize,gridSize));
        // gridG = gridImg.createGraphics();
        gridImg = new BufferedImage(gridSize+colorGridSize+1, gridSize+1, BufferedImage.TYPE_INT_ARGB);
        gridG = gridImg.createGraphics();
        

        try {
            colorPickerImg = ImageIO.read(new File("colorpicker.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fillToolImg = ImageIO.read(new File("filltool.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        b1 = new JButton("Save as png");
        b1.setBounds(gridSize, 3*colorGridSize, 200, 30); //sets the location and size
        b1.addActionListener(this); //add the listener
        this.add(b1); //add to JPanel

        b2 = new JButton("Fill Canvas");
        b2.setBounds(gridSize, 3*colorGridSize+20, 200, 30); //sets the location and size
        b2.addActionListener(this); //add the listener
        this.add(b2); //add to JPanel

        b6 = new JButton("Clear Canvas");
        b6.setBounds(gridSize, 3*colorGridSize+40, 200, 30); //sets the location and size
        b6.addActionListener(this); //add the listener
        this.add(b6); //add to JPanel

        b3 = new JButton("Eraser");
        b3.setBounds(gridSize, 3*colorGridSize+60, 200, 30); //sets the location and size
        b3.addActionListener(this); //add the listener
        this.add(b3); //add to JPanel

        b4 = new JButton("Upload Saved Image");
        b4.setBounds(gridSize, 3*colorGridSize+80, 200, 30); //sets the location and size
        b4.addActionListener(this); //add the listener
        this.add(b4); //add to JPanel

        b5 = new JButton("Fill Tool");
        b5.setBounds(gridSize, 3*colorGridSize+100, 200, 30); //sets the location and size
        b5.addActionListener(this); //add the listener
        this.add(b5); //add to JPanel

        b7 = new JButton("Color Picker");
        b7.setBounds(gridSize, 3*colorGridSize+120, 200, 30); //sets the location and size
        b7.addActionListener(this); //add the listener
        this.add(b7); //add to JPanel



        

        grid = new Square[amountOfSquares][amountOfSquares];
        for(int r = 0; r < grid.length; r++){
            for(int c = 0; c<grid[r].length; c++){
                grid[r][c] = new Square(c*squareSize, r*squareSize, squareSize);
                needToRepaint.add(grid[r][c]);
                updateGridImage();
                repaint();    
            }
        }
        createColorGrid();
        

    }
    

   
    public void mousePressed(MouseEvent e){
        


        mouseDown=true;
       
        mouseX=e.getX();
        mouseY=e.getY();
        if(colorPicker){
            activeColor = grid[(int)(mouseY/squareSize)][(int)(mouseX/squareSize)].getColor();
            colorPicker = false;
        }

        if(fillTool){
            fillArea2(mouseX, mouseY);
            repaint();
        }

        if(!fillTool){
                if(mouseX>0 && mouseX<gridSize && mouseY > 0 && mouseY < gridSize){    
                grid[(int)(mouseY/squareSize)][(int)(mouseX/squareSize)].setColor(activeColor);
                needToRepaint.add(grid[(int)(mouseY/squareSize)][(int)(mouseX/squareSize)]);
                updateGridImage();
                repaint();
            }
        }
            if(mouseX>gridSize && mouseX<gridSize + colorGridSize && mouseY>0 && mouseY<3*colorGridSize){
                activeColor = getColor(mouseX-gridSize, mouseY);
            }
        
        

    
           
    }
    
    public void mouseReleased(MouseEvent e) {
        mouseDown=false;
        //chooseGridColor();
        repaint();

        
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e){
        mouseX=e.getX();
        mouseY=e.getY();

        if(mouseDown && !fillTool){
            
            if(mouseX>0 && mouseX<gridSize && mouseY > 0 && mouseY < gridSize){
                
                grid[(int)(mouseY/squareSize)][(int)(mouseX/squareSize)].setColor(activeColor);
                needToRepaint.add(grid[(int)(mouseY/squareSize)][(int)(mouseX/squareSize)]);
                updateGridImage();
                
            }
        }
        repaint();
    }
    public void mouseMoved(MouseEvent e){
        mouseX=e.getX();
        mouseY=e.getY();
        repaint();
    }


    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b1){
            createImage();
        }
        if(e.getSource() == b2){
            for(Square[] row : grid){
                for(Square square : row){
                    square.setColor(activeColor);
                    needToRepaint.add(square);
                }
            }
            updateGridImage();
            repaint();
        }
        if(e.getSource() == b3){
            activeColor = Color.white;
        }
        if(e.getSource() == b4){
            uploadImage();
        }

        if(e.getSource() == b5){
            fillTool = !fillTool;
        }

        if(e.getSource() == b6){
            for(Square[] row : grid){
                for(Square square : row){
                    square.setColor(Color.WHITE);
                    needToRepaint.add(square);
                }
            }
            updateGridImage();
            repaint();
        }

        if(e.getSource() == b7){
            colorPicker = ! colorPicker;
        }
    }



    public Dimension getPreferredSize(){
        return new Dimension(1000,800);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(gridImg,0,0,null);
        if(colorPicker){
            g.drawImage(colorPickerImg,mouseX,mouseY-20,null);
        }else if(fillTool){
            g.drawImage(fillToolImg, mouseX-30, mouseY-20, null);
        }
        
        g.setColor(Color.black);
        //g.drawString("Fill tool does not work on an uploaded image",100,725);
        
    }
    public Color getColor(int x, int y){
        if(y<colorGridSize){
            return new Color(x*255/amountOfColors/colorSquareSize,y*255/amountOfColors/colorSquareSize,0);
        }
        else if(y<2*colorGridSize){
            return new Color(x*255/amountOfColors/colorSquareSize,0,y/2*255/amountOfColors/colorSquareSize);
        }else{
            return new Color(x*(255/amountOfColors)/colorSquareSize,x*(255/amountOfColors)/colorSquareSize,x*255/amountOfColors/colorSquareSize);
        }
        
    }
    public void createColorGrid(){
        colorGrid = new Square[amountOfColors*3][amountOfColors];
        for(int r = 0; r < colorGrid.length; r++){
            for(int c = 0; c<colorGrid[r].length; c++){
                colorGrid[r][c] = new Square(gridSize+c*colorSquareSize, r*colorSquareSize, colorSquareSize);
                colorGrid[r][c].setColor(getColor(c*colorSquareSize, r*colorSquareSize));
                needToRepaint.add(colorGrid[r][c]);
                
            }
        }
        updateGridImage();
        repaint();
        
    }
    public void uploadImage(){
        BufferedImage tempImg;
        try {
            tempImg = ImageIO.read(new File("myimage.png"));
            for(Square[] row : grid){
                for(Square square : row){
                        int pixel = tempImg.getRGB(square.getX()+squareSize/2,square.getY()+squareSize/2);
                         Color tempColor = new Color(pixel, true);
                        square.setColor(tempColor);
                        needToRepaint.add(square);
                        updateGridImage();
                       
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        repaint();
    }
    public void fillRow(int row, int cIndex, Color replaceColor){
        while(true){
            cIndex--;
            if(cIndex<0 || grid[row][cIndex].getColor()!=replaceColor){
                cIndex++;
                break;
            }
        }

        while(true){
            grid[row][cIndex].setColor(activeColor);
            needToRepaint.add(grid[row][cIndex]);
            cIndex++;
            if(cIndex>amountOfSquares-1 || grid[row][cIndex].getColor()!=replaceColor){
                updateGridImage();
                break;
            }
        }
    }
    public void fillArea(int startX, int startY){
        int rIndex = startY/squareSize;
        int cIndex = startX/squareSize;
        if(rIndex<grid.length && cIndex<grid.length){
            Color replaceColor = grid[rIndex][cIndex].getColor();
        while(true){
            rIndex--;
            if( rIndex<0 || grid[rIndex][cIndex].getColor()!=replaceColor ){
                rIndex++;
                break;
            }
        }

        while(true){
            fillRow(rIndex,cIndex,replaceColor);
            rIndex++;
            if(rIndex>amountOfSquares-1 || grid[rIndex][cIndex].getColor()!=replaceColor){
                break;
            }
            
        }
        }
        
    }
    public void chooseGridColor(){
        
        
        int count = 0;

        if(gridColor.equals(Color.black)){
            for(Square[] row : grid){
                for(Square square : row){
                    if(square.getColor().getRed()<20 && square.getColor().getBlue()<20 && square.getColor().getGreen()<20){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        if(count > amountOfSquares*amountOfSquares/2){
            gridColor = Color.white;
        }else{
            gridColor = Color.black;
        }

        for(Square[] row : grid){
            for(Square square : row){
                needToRepaint.add(square);
            }
        }
        updateGridImage();
        
    }




    public void searchForAreaToFill(int cIndex, int rIndex, Color targetColor){
        if(cIndex >= 0 && cIndex < grid.length && rIndex>=0 && rIndex < grid.length){
            if(grid[rIndex][cIndex].getColor() == targetColor){
                grid[rIndex][cIndex].setColor(activeColor);
                needToRepaint.add(grid[rIndex][cIndex]);
                searchForAreaToFill(cIndex+1,rIndex,targetColor);
                searchForAreaToFill(cIndex-1,rIndex,targetColor);
                searchForAreaToFill(cIndex,rIndex+1,targetColor);
                searchForAreaToFill(cIndex,rIndex-1,targetColor);
            }
        }
        
    }
    public void fillArea2(int x, int y){
        int cIndex = x/squareSize;
        int rIndex = y/squareSize;
        if(cIndex >= 0 && cIndex < grid.length && rIndex>=0 && rIndex < grid.length){
            Color targetColor = grid[rIndex][cIndex].getColor();
            if(targetColor != activeColor){
                searchForAreaToFill(cIndex, rIndex, targetColor);
            }
            updateGridImage();
            repaint();
        }
    }
    
    public void updateGridImage(){
        for(Square square : needToRepaint){
             //if(square.getX()<=gridSize){
                square.drawMe(gridG,gridColor);
            //}else{
            //    square.drawMe(gridG,null);
            //}
            
        }
        needToRepaint.clear();
    }

    public void createImage(){
        myImage = (BufferedImage)(createImage(gridSize,gridSize));
        Graphics imageG = myImage.createGraphics();
        for(Square[] row : grid){
            for(Square square : row){
                if(square.getColor() != Color.white){
                    square.drawMe(imageG, null);
                }   
            }
        }

        if (myImage != null) {
                try {
                    File outputfile = new File("myimage.png");
                    ImageIO.write(myImage, "png", outputfile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }

    }
    
    
}
