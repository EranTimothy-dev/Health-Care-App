import javax.print.Doc;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Controller {
    static Scanner scanner = new Scanner(System.in);
    static int userSelection;
    public static ArrayList<Doctor> doctors = new ArrayList<>();
    public static ArrayList<Patient> patients = new ArrayList<>();

    // Admin related methods
    public static void addDoctor(){
        Random random = new Random();
        while(true){
            System.out.print("Enter Doctors name: ");
            String docName = scanner.next();
            System.out.print("Enter Doctors birthday: ");
            String docBirthday = scanner.next();
            System.out.print("Enter Doctors specialization: ");
            String docSpecialization = scanner.next();
            System.out.print("Enter Doctors contact: ");
            String docContactNumber = scanner.next();
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
            System.out.print("Enter the Day you want to add Availability: ");
            int day = scanner.nextInt();
            System.out.print("Enter the Month you want to add Availability: ");
            int month = scanner.nextInt();
            System.out.print("Enter the Year you want to add Availability: ");
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
        System.out.print("Enter any value key to go back: ");
//        String leave =
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void addNewPatient(){
        System.out.print("Enter patient name: ");
        String name = scanner.next();
        System.out.print("Enter patient ID: ");
        String patientId = scanner.next();
        System.out.print("Enter patient birth date: ");
        String birthdate = scanner.next();
        System.out.print("Enter patient contact number: ");
        String contact = scanner.next();

        System.out.print("""
                    
                    Do you wish to proceed with this information? (Y/N) >""");
        String yesOrNo = scanner.next();
        if (Objects.equals(yesOrNo, "Y") || Objects.equals(yesOrNo, "y")){
            Patient patient = new Patient(name, patientId, birthdate, contact);
            patients.add(patient);
            System.out.println("Patient successfully registered!");
        } else {
            System.out.println("Patient was not registered.");
        }
    }


    // Appointment related methods
    private static Patient getPatientByID(String id){
        for (Patient patient: patients){
            if (Objects.equals(patient.getPatientId(), id)){
                return patient;
            }
        }
        return null;
    }

    private static Doctor getDoctorByID(int id){
        for (Doctor doctor: doctors){
            if (Objects.equals(doctor.doctorId, id)){
                return doctor;
            }
        }
        return null;
    }

    // only required to be accessed by method inside the class therefore it is private
    private static boolean checkDoctorAvailability(Doctor doctor,Date date){
        for (Date available: doctor.availabilities){
            if (date.equals(available)){
                return true;
            }
        }
        return false;
    }

    public static void bookAppointment(){
        System.out.print("Enter Doctor's Id you want to make an appointment: ");
        int docId = scanner.nextInt();
        System.out.print("Enter you patient's Id: ");
        String patientId = scanner.next();

        Patient selectedPatient = getPatientByID(patientId);
        Doctor selectedDoctor = getDoctorByID(docId);

        if(selectedPatient == null){
            System.out.println("In valid patient ID! patient details could not be retrieved.");
            return;
        }
        if (selectedDoctor == null){
            System.out.println("Invalid doctor ID! doctor details could not be retrieved.");
            return;
        } else {
            System.out.println("Details found.");
            System.out.println(" ");
        }
        System.out.print("Enter the Day you want to add Appointment: ");
        String day = scanner.next();
        System.out.print("Enter the Month you want to add Appointment: ");
        String month = scanner.next();
        System.out.print("Enter the Year you want to add Appointment: ");
        String year = scanner.next();
        System.out.println("Enter any notes you want to add about your appointment below (if not enter 'No Notes'): ");
        String notes = scanner.next();

        // Check doctor availability and available slots
        Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        boolean doctorAvailable = checkDoctorAvailability(selectedDoctor, appointmentDate);
        if (doctorAvailable){
            int slots = selectedDoctor.getSlots(appointmentDate);
            if (slots == -1){
                System.out.println("No more slots available for that date.");
            }else if (slots<=10){
                String appointmentTime = String.format("%d : 00 PM",slots);
                Appointment appointment = new Appointment(selectedDoctor, selectedPatient, notes, appointmentDate, appointmentTime);
                selectedDoctor.setAppointments(appointment, appointmentDate);
            }
        }else {
            System.out.println("Doctor not available on that date.");
        }
    }




}
