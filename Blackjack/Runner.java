import javax.swing.JFrame;


public class Runner {
    public static void main(String[] args){
        
		MakeFont.makeFont();
		JFrame fr = new JFrame("BlackJackV2 - Max Ulinich");
		Table sc = new Table();
		
		fr.add(sc);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
		
		
    }
}
