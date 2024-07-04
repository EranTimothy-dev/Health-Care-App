public class Patient {
    public String name;
    public String birthdate;
    public long contact;
    public String patientId;

    public Patient(String patientName, String patientID, String birthdate, long contactNumber){
        this.name = patientName;
        this.birthdate = birthdate;
        this.patientId = patientID;
        this.contact = contactNumber;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }
}
