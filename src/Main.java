import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static int userSelection;
//    public static ArrayList<Doctor> doctors = new ArrayList<>();


    public static void PatientMenu() throws InterruptedException {
        while(true){
            System.out.println("""
                            
                            Enter 1 to view doctors
                            Enter 2 to register patient
                            Enter 3 to view a selected doctor's bookings
                            Enter 4 to book an appointment
                            Enter 5 to exit""");
            System.out.print("Enter input: ");
            userSelection = scanner.nextInt();
            if(userSelection <= 0 || userSelection > 5){
                System.out.println("Invalid Input! Please check and try again...");
            } else if (userSelection == 5) {
                System.out.println("Redirecting to Main Menu...");
                TimeUnit.SECONDS.sleep(2);
                break;
            } else if (userSelection == 1) {
                Controller.viewDoctors();
            }
        }
    }


    public static void HospitalAdministratorMenu() throws InterruptedException {
        while(true){
            System.out.println("""
                            
                            Enter 1 to add a doctor
                            Enter 2 to add a doctor availability
                            Enter 3 to exit to Main Menu""");
            System.out.print("Enter input: ");
            userSelection = scanner.nextInt();
            if (userSelection <= 0 || userSelection > 3){
                System.out.println("Invalid Input! Please check and try again...");
            } else if (userSelection == 3) {
                System.out.println("Redirecting to Main Menu...");
                TimeUnit.SECONDS.sleep(2);
                break;
            } else if (userSelection == 1) {
                Controller.addDoctor();
            } else {
                Controller.addDoctorAvailability();
            }
        }
    }


    public static int MainMenu(){
        int authorityLevel;
        while(true) {
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
        }
        return authorityLevel;
    }


    public static void main(String[] args) throws InterruptedException {
        while (true){
//            Main m = new Main();
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