public class PaperTechnician extends Thread{

    ThreadGroup paperTechThreadGroup;
    ServicePrinter paperTechPrinter;
    String name;

    public PaperTechnician(ThreadGroup threadGroup, ServicePrinter printer, String name) {
        // todo: check if the name can be passed to the super class name
        super();
        this.paperTechPrinter = printer;
        //TODO: recheck with the "technician" thread group
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
            if(((LaserPrinter)paperTechPrinter).isEligibleToRefill()) {
                num_packs_used += 1;
            }
            paperTechPrinter.refillPaper();


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
