public class PrintingSystem {
    public static void main(String[] args) {
        ServicePrinter laserPrinter = new LaserPrinter("FP001", 10, 50, 0 );

        ThreadGroup studentGroup = new ThreadGroup("student");
        ThreadGroup technicianGroup = new ThreadGroup("technician");

        Thread paperTechnician = new PaperTechnician("PaperTechnician", laserPrinter, technicianGroup);
        Thread tonerTechnician = new TonerTechnician("TonerTechnician", laserPrinter, technicianGroup);
        Thread student1 = new Student("Student01", laserPrinter, studentGroup, paperTechnician, tonerTechnician);
        Thread student2 = new Student("Student02", laserPrinter, studentGroup, paperTechnician, tonerTechnician);
        Thread student3 = new Student("Student03", laserPrinter, studentGroup, paperTechnician, tonerTechnician);
        Thread student4 = new Student("Student04", laserPrinter, studentGroup, paperTechnician, tonerTechnician);



        student1.start();
        student2.start();
        student3.start();
        student4.start();
        paperTechnician.start();
        tonerTechnician.start();


        try {

            student1.join();
            student2.join();
            student3.join();
            student4.join();
            paperTechnician.join();
            tonerTechnician.join();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=============== PRINTER STATUS AT THE END ==================");
        System.out.println("Printer Status :: " + laserPrinter);


    }
}
