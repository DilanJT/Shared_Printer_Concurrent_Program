

public class LaserPrinter implements ServicePrinter{

    private String printerID;
    private int paperLevel;
    private int tonerLevel;
    private int documentsPrinted;

    private int tonersReplaced;

    private int papersRefilled;

    ThreadGroup studentGroup;
    ThreadGroup technicianGroup;

    //private boolean eligibleToRefill;
    public LaserPrinter(String printerID, int paperLevel, int tonerLevel, int documentsPrinted){
        this.printerID = printerID;
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.documentsPrinted = documentsPrinted;
    }

    public LaserPrinter(String printerID, int paperLevel, int tonerLevel, int documentsPrinted, ThreadGroup sT, ThreadGroup tT) {
        this.printerID = printerID;
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.documentsPrinted = documentsPrinted;
        this.studentGroup = sT;
        this.technicianGroup = tT;
    }


    public LaserPrinter() {}

    @Override
    public synchronized void printDocument(Document document) {
        System.out.println("------------------- printDocument() -------------------------");
        int numPages = document.getNumberOfPages();

        // numPages >= paperLevel || numPages >= tonerLevel
        while(!isEligibleToPrint(document)) {

            try {
                System.out.println(Thread.currentThread().getName() +
                        " waiting till toner is replaced or papers are refilled");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(isEligibleToPrint(document)) {
            paperLevel = this.paperLevel - numPages;
            tonerLevel = this.tonerLevel - numPages;
            documentsPrinted++;

            System.out.println(Thread.currentThread().getName() + " printed the " + document);
        }
        System.out.println("printDocument() : " + this);
        System.out.println("------------------- END printDocument() -------------------------\n");
        notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {

        //boolean replaceAttempted = false;
        while(!isEligibleToReplaceToner()) {
//            if(replaceAttempted) return;

            if(studentGroup.activeCount() == 0) {
//                replaceAttempted = true;
                return;
            }

            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }





        }
        System.out.println("------------------- replaceTonerCartridge() -------------------------");
        this.tonerLevel = Full_Toner_Level;
        tonersReplaced ++;
        System.out.println(Thread.currentThread().getName() + " replaced the toner. ");
        System.out.println("replaceTonerCartridge() : " + this);
        System.out.println("------------------- END replaceTonerCartridge() -------------------------\n");

        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {

        while(!isEligibleToRefill()) {

            if(studentGroup.activeCount() == 0) return;

            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("------------------- refillPaper() -------------------------");
        paperLevel = this.paperLevel + SheetsPerPack;
        papersRefilled ++;
        System.out.println(Thread.currentThread().getName() + " refilled the paper tray. ");
        System.out.println("refillPaper() " + this.toString());
        System.out.println("------------------- END refillPaper() -------------------------\n");

        notifyAll();
    }

    public boolean isEligibleToRefill(){

        return (paperLevel + SheetsPerPack) <= Full_Paper_Tray;
    }

    public boolean isEligibleToReplaceToner() {

        return this.tonerLevel < Minimum_Toner_Level;
    }

    public boolean isEligibleToPrint(Document document) {
        int num_docs = document.getNumberOfPages();
        return num_docs < this.paperLevel && num_docs < this.tonerLevel;
    }

    public int getTonersReplaced() {
        return tonersReplaced;
    }

    public int getPapersRefilled() {
        return papersRefilled;
    }

    @Override
    public String toString() {
        return "LaserPrinter[ "  +
                "PrinterID: " + this.printerID      + ", " +
                "PaperLevel: "   + this.paperLevel  + ", " +
                "TonerLevel: "  + this.tonerLevel + ", " +
                "Documents Printed : " + this.documentsPrinted +
                "]";
    }


}
