public class Patient extends Person {
    private String patientId;

    public Patient(String name, String patientID, String birthdate, String contactNumber){
        super(name, birthdate, contactNumber);
        this.patientId = patientID;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }

    public boolean setPatientId(String patientId){
        char patientType = getPatientType();
        boolean isLetter = Character.isLetter(patientType);
        if (isLetter){
            this.patientId = patientId;
            return true;
        }
        System.out.println("Invalid Patient ID, patient type not found");
        return false;
    }

    public String getPatientId(){
        return this.patientId;
    }

}
