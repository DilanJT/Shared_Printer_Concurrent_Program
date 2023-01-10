public class PrintingSystem {
    public static void main(String[] args) {
        ServicePrinter laserPrinter = new LaserPrinter("FP001", 100, 100, 0 );

        ThreadGroup studentGroup = new ThreadGroup("student");
        ThreadGroup technicianGroup = new ThreadGroup("technician");

        Thread student1 = new Student("Student01", laserPrinter, studentGroup);
        Thread student2 = new Student("Student02", laserPrinter, studentGroup);
        Thread student3 = new Student("Student03", laserPrinter, studentGroup);
        Thread student4 = new Student("Student04", laserPrinter, studentGroup);
        Thread paperTechnician = new PaperTechnician("PaperTechnician", laserPrinter, technicianGroup);
        Thread tonerTechnician = new TonerTechnician("TonerTechnician", laserPrinter, technicianGroup);

        student1.start();
        student2.start();
        student3.start();
        student4.start();

        paperTechnician.start();
        tonerTechnician.start();

    }
}
