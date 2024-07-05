public class Patient {
    public String name;
    public String birthdate;
    public String contact;
    public String patientId;

    public Patient(String patientName, String patientID, String birthdate, String contactNumber){
        this.name = patientName;
        this.birthdate = birthdate;
        this.patientId = patientID;
        this.contact = contactNumber;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }
}
