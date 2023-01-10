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
    public void printDocument(Document document) {

    }

    @Override
    public void replaceTonerCartridge() {

    }

    @Override
    public void refillPaper() {

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
