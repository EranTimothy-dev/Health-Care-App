import java.util.*;
import java.util.concurrent.TimeUnit;


public class Controller {
    static Scanner scanner = new Scanner(System.in);
    static int userSelection;
    public static ArrayList<Doctor> doctors = new ArrayList<>();

    // Admin related methods
    public static void addDoctor(){
        String docName;
        String docContactNumber;
        String docBirthday;
        String docSpecialization;
        Random random = new Random();
        while(true){
            System.out.print("Enter Doctors name: ");
            docName = scanner.next();
            System.out.print("Enter Doctors birthday: ");
            docBirthday = scanner.next();
            System.out.print("Enter Doctors specialization: ");
            docSpecialization = scanner.next();
            System.out.print("Enter Doctors contact: ");
            docContactNumber = scanner.next();
            System.out.print("""
                    
                    Do you wish to proceed with this information? (Y/N) >""");
            String yesOrNo = scanner.next();
            if (Objects.equals(yesOrNo, "Y") || Objects.equals(yesOrNo, "y")){
                // Generates positive random number by making the sign bit 0 through bitwise AND operation
                int docId = random.nextInt() & Integer.MAX_VALUE; //random int & 01111111 11111111 11111111 11111111
                Doctor doc = new Doctor(docId,docName,docBirthday,docSpecialization,docContactNumber);
                doctors.add(doc);
                System.out.println("Doctor successfully added!");
            } else {
                System.out.println("Doctor was not added to the system.");
            }
            System.out.print("Do you want to add another doctor? (Y/N) >");
            String addAgain = scanner.next();
            if(Objects.equals(addAgain, "Y") || Objects.equals(addAgain, "y")){
                System.out.println("\n");
            }else {
                break;
            }
        }
    }

    public static void addDoctorAvailability() {
        while (true) {
            System.out.print("Enter Doctors ID to add availability: ");
            userSelection = scanner.nextInt();
            Doctor selectedDoctor = null;
            for (Doctor doc : doctors) {
                if (doc.doctorId == userSelection) {
                    selectedDoctor = doc;
                }
            }
            // If doctor doesn't exist
            if (selectedDoctor == null) {
                System.out.println("Invalid Doctor ID! Doctor not found.");
                break;
            }
            // If doctor does exist set the availability
            System.out.println("Enter the Day you want to add Availability: ");
            int day = scanner.nextInt();
            System.out.println("Enter the Month you want to add Availability: ");
            int month = scanner.nextInt();
            System.out.println("Enter the Year you want to add Availability: ");
            int year = scanner.nextInt();

            Date availableDate = new Date(year, month, day);
            selectedDoctor.setDoctorAvailability(availableDate);
            System.out.println("Availability added successfully.");
            System.out.print("Do you want to add another availability(Y/N): ");
            String yesOrNo = scanner.next();
            if (Objects.equals(yesOrNo, "Y") || Objects.equals(yesOrNo, "y")) {
                System.out.println(" ");
            } else if (Objects.equals(yesOrNo, "N") || Objects.equals(yesOrNo, "n")) {
                System.out.println("Returning to Main Menu");
                break;
            }
        }
    }




    // Patient related methods
    public static void viewDoctors() throws InterruptedException {
        int i = 1;
        System.out.println("""
                Getting Doctor Details...
                """);
        TimeUnit.SECONDS.sleep(2);
        for(Doctor doctorInfo : doctors){
            System.out.println(i + ". " + doctorInfo.doctorName + "(" + doctorInfo.doctorId + ")" + ": " + doctorInfo.specialization + ",\n\t and has a availability of " + doctorInfo.availabilities);
            i++;
        }
        System.out.print("Press Enter key to exit: ");
        String leave = scanner.nextLine();
        scanner.nextLine();
    }





}
