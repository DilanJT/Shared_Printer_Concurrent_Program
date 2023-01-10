public class Student extends Thread{
    ThreadGroup stuThreadGroup;
    Printer stuPrinter;
    String stuName;

    public Student(String name, Printer printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.stuName = name;
        this.stuPrinter = printer;
        // TODO: recheck with the "student" thread group
        this.stuThreadGroup = threadGroup;
    }

    public Student() {
        super();
    }


    @Override
    public void run() {
        Document[] documents = new Document[5];
        documents[0] = new Document("SD01", "Computer science pracitice report", 20);
        documents[1] = new Document("SD02", "Database Systems", 10);
        documents[3] = new Document("SD03", "Algo report", 15);
        documents[4] = new Document("SD04", "SDGP report", 100);
        //documents[5] = new Document("SD05", "OOP report", 5);
        int totalNumPages = 0;
        for(int i = 0; i < 5; i++) {
            totalNumPages += documents[i].getNumberOfPages();
            stuPrinter.printDocument(documents[i]);
            int randomTime = (int)(Math.random() * 1000);
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(Thread.currentThread().getName() +
                " finished printing: " + documents.length + " Documents, " + totalNumPages);

    }
}
