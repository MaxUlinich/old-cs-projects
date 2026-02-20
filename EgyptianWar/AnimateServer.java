import javax.swing.JPanel;

public class AnimateServer implements Runnable {
    private JPanel sc;

    public AnimateServer(ServerScreen sc) {
        this.sc = sc;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sc.repaint();
        }
    }
}
