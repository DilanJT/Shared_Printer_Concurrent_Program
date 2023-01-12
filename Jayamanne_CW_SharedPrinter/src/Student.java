public class Student extends Thread{
    ThreadGroup stuThreadGroup;
    Printer stuPrinter;
    String stuName;

    Thread paperTechnician;
    Thread tonerTechnician;

    public Student(String name, Printer printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.stuName = name;
        this.stuPrinter = printer;
        // TODO: recheck with the "student" thread group
        this.stuThreadGroup = threadGroup;
    }

    public Student(String name, Printer printer, ThreadGroup threadGroup, Thread paperTechnician, Thread tonerTechnician) {
        super(threadGroup, name);
        this.stuName = name;
        this.stuPrinter = printer;
        // TODO: recheck with the "student" thread group
        this.stuThreadGroup = threadGroup;
        this.tonerTechnician = tonerTechnician;
        this.paperTechnician = paperTechnician;
    }

    public Student() {
        super();
    }


    @Override
    public void run() {
        Document[] documents = new Document[5];
        documents[0] = new Document(this.stuName, "Computer science pracitice report", 5);
        documents[1] = new Document(this.stuName, "Database Systems", 5);
        documents[2] = new Document(this.stuName, "Algo report", 5);
        documents[3] = new Document(this.stuName, "SDGP report", 5);
        documents[4] = new Document(this.stuName, "OOP report", 5);
        int totalNumPages = 0;
        for (Document document : documents) {
            if (document != null) {


                System.out.println(Thread.currentThread().getName() + " trying to print the " + document + " \n");
                /*
                 reason
                 - if all the students have encountered not enough toner level to print,
                 then all the students will wait till the tonerTechnician replace the toner and notify.
                 - then only the students can get the change to print the documents without waiting in the waiting state.
                 */
//                try {
//                    tonerTechnician.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

                /*
                - if the the student cannot print the docs cus of not enough toner level and paper level
                 */
                if (!((LaserPrinter) stuPrinter).isEligibleToPrint(document)) {
//                    try {
//                        paperTechnician.join();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }

//                    if(((LaserPrinter)stuPrinter).getPaperLevel() < ((LaserPrinter)stuPrinter).getMaxPaperLevel()) {
//                        try {
//                            paperTechnician.join();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }

//                    if(((LaserPrinter)stuPrinter).getTonerLevel() < ((LaserPrinter)stuPrinter).getFullTonerLevel()){
//                        try {
//                            tonerTechnician.join();
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                }
                stuPrinter.printDocument(document);
                totalNumPages += document.getNumberOfPages();

                int randomTime = (int) (Math.random() * 1000);
                try {
                    Thread.sleep(randomTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println(Thread.currentThread().getName() +
                " finished printing: " + documents.length + " Documents, " + totalNumPages +". \n");

    }
}
