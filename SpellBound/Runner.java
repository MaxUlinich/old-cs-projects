import javax.swing.JFrame;


public class Runner {
    public static void main(String[] args){
        
		MakeFont.makeFont();
		JFrame fr = new JFrame("SpellBound - Max Ulinich");
		Board b = new Board();
		
		fr.add(b);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
		b.animate();
		
    }
}
