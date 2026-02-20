public class Animate implements Runnable {
    private Screen sc;

    public Animate(Screen sc) {
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
