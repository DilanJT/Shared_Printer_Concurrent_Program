public class TonerTechnician extends Thread{
    ThreadGroup tonerTechThreadGroup;
    ServicePrinter tonerTechPrinter;
    String name;

    public TonerTechnician(String name, ServicePrinter printer, ThreadGroup threadGroup) {
        // todo: check if the name can be passed to the super class name
        super(threadGroup, name);
        this.tonerTechPrinter = printer;
        //TODO: recheck with the "technician" thread group
        this.tonerTechThreadGroup = threadGroup;
        this.name = name;
    }

    public TonerTechnician() {

    }
    @Override
    public void run() {

        int num_packs_used = 0;
        int attempts = 3;
        for(int i = 0; i < attempts; i++) {
            if(((LaserPrinter)tonerTechPrinter).isEligibleToReplaceToner()) {
                num_packs_used += 1;
            }
            tonerTechPrinter.replaceTonerCartridge();


            int randomTime = (int)(Math.random() * 1000);
            try {
                Thread.sleep(randomTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // TODO: try to get the name of the thread from the Thread class
        System.out.println("Toner Technician Finished, packs of the paper used : " + num_packs_used);
    }
}
