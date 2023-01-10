public class LaserPrinter implements ServicePrinter{

    private final String printerID;
    private int paperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    public LaserPrinter(String printerID, int paperLevel, int tonerLevel, int documentsPrinted){
        this.printerID = printerID;
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.documentsPrinted = documentsPrinted;
    }

    public LaserPrinter(String printerID) {
        this.printerID = printerID;
    }

    @Override
    public synchronized void printDocument(Document document) {
        int numPages = document.getNumberOfPages();
        // Assuming it can print a 10 page doc from bother toner and the paper level greater than 10
        if(numPages < paperLevel & numPages < tonerLevel) {
            paperLevel = this.paperLevel - numPages;
            tonerLevel = this.tonerLevel - numPages;
        }
    }

    @Override
    public void replaceTonerCartridge() {
        if(this.tonerLevel < Minimum_Toner_Level) {
            this.tonerLevel = Full_Toner_Level;
        }else {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void refillPaper() {
        // TODO : check for the divisibility by 50 from the paperLevel
        int tempPaperLevel = this.paperLevel + SheetsPerPack;
        if(tempPaperLevel > Full_Paper_Tray) {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            paperLevel = this.paperLevel + SheetsPerPack;
        }
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
