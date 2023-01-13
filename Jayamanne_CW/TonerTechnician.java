public class TonerTechnician extends Thread{
    ThreadGroup tonerTechThreadGroup;
    ServicePrinter tonerTechPrinter;
    String name;

    public TonerTechnician(String name, ServicePrinter printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.tonerTechPrinter = printer;
        this.tonerTechThreadGroup = threadGroup;
        this.name = name;
    }

    public TonerTechnician() {

    }
    @Override
    public void run() {

        int num_toners_replaced = 0;
        int attempts = 3;
        for (int i = 0; i < attempts; i++) {

            tonerTechPrinter.replaceTonerCartridge();

            num_toners_replaced = ((LaserPrinter)tonerTechPrinter).getTonersReplaced();


            int randomTime = (int) (Math.random() * 1000);
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Toner Technician Finished, cartridges replaced: " + num_toners_replaced + " \n");
    }
}
