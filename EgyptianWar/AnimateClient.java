

public class AnimateClient implements Runnable {
    private ClientScreen sc;

    public AnimateClient(ClientScreen sc) {
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
