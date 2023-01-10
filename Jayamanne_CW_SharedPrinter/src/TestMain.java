import java.awt.print.Paper;

public class TestMain {
    public static void main(String[] args) {
//        Runnable r1 = new Student();
//        Thread t = new Thread(r1);
//        t.start();

        Thread t2 = new PaperTechnician();
        t2.start();

    }
}
