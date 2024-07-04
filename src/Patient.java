public class Patient {
    public String name;
    public String birthday;
    public long contact;
    public String patientId;

    public Patient(String patientName, String birthday,String patientID, long contactNumber){
        this.name = patientName;
        this.birthday = birthday;
        this.patientId = patientID;
        this.contact = contactNumber;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }
}
