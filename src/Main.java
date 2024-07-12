import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    //    public static ArrayList<Doctor> doctors = new ArrayList<>();


    public static void PatientMenu() throws InterruptedException {
        while(true){
            try {
                System.out.println("""
                                
                                Enter 1 to view doctors
                                Enter 2 to register patient
                                Enter 3 to view a selected doctor's bookings
                                Enter 4 to book an appointment
                                Enter 5 to exit""");
                System.out.print("Enter input: ");
                int userSelection = scanner.nextInt();
                scanner.nextLine();
                if(userSelection <= 0 || userSelection > 5){
                    System.out.println("Invalid Input! Please check and try again...");
                } else if (userSelection == 5) {
                    System.out.println("Redirecting to Main Menu...");
                    TimeUnit.SECONDS.sleep(2);
                    break;
                } else if (userSelection == 1) {
                    Controller.viewDoctors();
                } else if (userSelection == 2) {
                    Controller.addNewPatient();
                } else if (userSelection == 3) {
                    Controller.viewAppointments();
                } else {
                    Controller.bookAppointment();
                }
            } catch (Exception e) {
                System.out.println("Invalid Input!");
                //scanner.nextLine(); // Clear input buffer
            }
        }
    }


    public static void HospitalAdministratorMenu() throws InterruptedException {
        while(true){
            try {
                System.out.println("""
                                
                                Enter 1 to add a doctor
                                Enter 2 to add a doctor availability
                                Enter 3 to exit to Main Menu""");
                System.out.print("Enter input: ");
                int adminSelection = scanner.nextInt();
                scanner.nextLine();
                if (adminSelection <= 0 || adminSelection > 3){
                    System.out.println("Invalid Input! Please check and try again...");
                } else if (adminSelection == 3) {
                    System.out.println("Redirecting to Main Menu...");
                    TimeUnit.SECONDS.sleep(2);
                    break;
                } else if (adminSelection == 1) {
                    Controller.addDoctor();
                } else {
                    Controller.addDoctorAvailability();
                }
            } catch (Exception e) {
                System.out.println("Invalid Input!");
                //scanner.next();// Clear input buffer
            }
        }
    }


    public static int MainMenu(){
        int authorityLevel;
        while(true) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("""
                        
                        If you are a hospital administrator please enter 1.
                        If you are a patient please enter 2.
                        If you want to exit please enter 3.""");
                System.out.print("Enter input: ");
                authorityLevel = scanner.nextInt(10);
                if (authorityLevel <= 0 || authorityLevel > 3) {
                    System.out.println("Invalid Input! Please check and try again...");
                } else if (authorityLevel == 3) {
                    System.out.println("Exiting... Have a nice day");
                    //System.exit(0);
                    break;
                }else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
        }
        return authorityLevel;
    }


    public static void main(String[] args) throws InterruptedException {

        // test data
        Doctor sampleDoc = new Doctor(223,"Saman Kumara","22.05.1987","Gynocologist","077-333-9900");
        Patient samplePatient = new Patient( "Alice Johnson","T-12", "24/04/2003", "555-123-4567");
        Controller.doctors.add(sampleDoc);
        Controller.patients.add(samplePatient);


        while (true){
            int authority = MainMenu();
            if (Objects.equals(authority,1)){
                HospitalAdministratorMenu();
            } else if (authority == 2) {
                PatientMenu();
            } else if (authority == 3) {
                System.exit(0);
            }
        }
    }
}