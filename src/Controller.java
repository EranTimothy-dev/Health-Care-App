import java.util.*;
import java.util.concurrent.TimeUnit;


public class Controller {
    static Scanner scanner = new Scanner(System.in);
    public static final ArrayList<Doctor> doctors = new ArrayList<>();
    public static final ArrayList<Patient> patients = new ArrayList<>();

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
                int docId = random.nextInt() & Integer.MAX_VALUE;
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
            scanner = new Scanner(System.in);
            System.out.print("Enter Doctors ID to add availability: ");
            int docID = scanner.nextInt();
//            scanner.nextLine();
            Doctor selectedDoctor = null;
            for (Doctor doc : doctors) {
                if (doc.getDoctorId() == docID) {
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
            System.out.println(i + ". " + doctorInfo.getName() + "(" + doctorInfo.getDoctorId() + ")" + ": " + doctorInfo.getSpecialization() + ",\n\t and has a availability of " + doctorInfo.getAvailabilities());
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
            if (Objects.equals(doctor.getDoctorId(), id)){
                return doctor;
            }
        }
        return null;
    }

    // only required to be accessed by method inside the class therefore it is private
    private static boolean checkDoctorAvailability(Doctor doctor,Date date){
        for (Date available: doctor.getAvailabilities()){
            if (date.equals(available)){
                return true;
            }
        }
        return false;
    }

    public static void bookAppointment(){
        scanner = new Scanner(System.in);
        System.out.print("Enter Doctor's Id you want to make an appointment: ");
        int docId = scanner.nextInt();
        //scanner.nextLine();
        System.out.print("Enter patient's Id: ");
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

        // Get appointment date
        System.out.print("Enter the Day you want to add Appointment: ");
        String day = scanner.next();
        System.out.print("Enter the Month you want to add Appointment: ");
        String month = scanner.next();
        System.out.print("Enter the Year you want to add Appointment: ");
        String year = scanner.next();
        Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        // Get appointment notes
        System.out.println("Enter any notes you want to add about your appointment below (if not enter 'No Notes'): ");
        String notes = scanner.next();

        // Check doctor availability and available slots
        boolean doctorAvailable = checkDoctorAvailability(selectedDoctor, appointmentDate);
        if (doctorAvailable){
            int slots = selectedDoctor.getSlots(appointmentDate);
            if (slots == -1){
                System.out.println("No more slots available for that date.");
            }else if (slots<=10){
                while (true){
                    // Get appointment type
                    scanner = new Scanner(System.in);
                    System.out.print("Is your appointment a general or referral appointment? (G/R): ");
                    String appointmentType = scanner.next();
                    String appointmentTime = String.format("%d : 00 PM",slots);

                    if (Objects.equals(appointmentType, "G")){
                        GeneralAppointment tempAppointment = new GeneralAppointment(selectedDoctor, selectedPatient, appointmentDate, appointmentTime, notes);
//                      // upcast GeneralAppointment to its parent class 'Appointment'
                        Appointment appointment = (Appointment) tempAppointment;
                        selectedDoctor.setAppointments(appointment, appointmentDate);
                        break;
                    } else if (Objects.equals(appointmentType, "R")) {
                        while (true){
                            System.out.print("Enter Referral Doctors ID: ");
                            int referralDoctorId = scanner.nextInt();
                            System.out.println("Enter Referral Doctors notes: ");
                            String referralNotes = scanner.next();

                            Doctor referralDoctor = getDoctorByID(referralDoctorId);
                            if (referralDoctor != null){
                                ReferralAppointment tempAppointment = new ReferralAppointment(selectedDoctor, referralDoctor, selectedPatient, appointmentDate, appointmentTime, notes);
                                tempAppointment.setReferralDoctorNotes(referralNotes);
                                // upcast ReferralAppointment to its parent class 'Appointment' abd save upcasted object in a new variable 'appointment'
                                Appointment appointment = (Appointment) tempAppointment;
                                selectedDoctor.setAppointments(appointment, appointmentDate);
                                break;
                            } else {
                                System.out.println("Invalid ID! No doctor found.");
                            }
                        }
                        break;
                    }else {
                        System.out.println("Invalid input! Please enter 'G' for general appointment and 'R' for referral appointment");
                    }
                }

            }
        }else {
            System.out.println("Doctor not available on that date.");
        }
    }

    public static void viewAppointments(){
        scanner = new Scanner(System.in);
        System.out.print("Enter Doctors ID you want to view appointment: ");
        int DoctorId = scanner.nextInt();
        //scanner.nextLine();

        Doctor selectedDoc = getDoctorByID(DoctorId);
        if (selectedDoc != null){
            int i = 1;
            for (Map.Entry<Date,ArrayList<Appointment>> date : selectedDoc.getAppointments().entrySet()){
                System.out.println(i + ". Appointments on " + date.getKey() + ": ");
                for (Appointment appointments : date.getValue()){
                    System.out.printf("""
                             - Patient ID: %s
                             - Patient Name: %s
                             - Appointment Time: %s %n
                             
                             """
                            , appointments.getPatient().getPatientId()
                            , appointments.getPatient().getName()
                            , appointments.getTime());
                }
                i++;

            }
            return;
        }
        System.out.println("Invalid Doctor ID! Doctor does not exist.");


    }



}
