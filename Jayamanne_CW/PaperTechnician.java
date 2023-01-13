public class PaperTechnician extends Thread{

    ThreadGroup paperTechThreadGroup;
    ServicePrinter paperTechPrinter;
    String name;

    public PaperTechnician(String name, ServicePrinter printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.paperTechPrinter = printer;
        this.paperTechThreadGroup = threadGroup;
        this.name = name;
    }

    public PaperTechnician() {

    }
    @Override
    public void run() {

        int num_packs_used = 0;
        int attempts = 3;
        for(int i = 0; i < attempts; i++) {

            paperTechPrinter.refillPaper();
            num_packs_used = ((LaserPrinter)paperTechPrinter).getPapersRefilled();


            int randomTime = (int)(Math.random() * 1000);
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Paper Technician Finished, packs of the paper used : " + num_packs_used);
    }
}
