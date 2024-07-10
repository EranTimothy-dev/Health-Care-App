public class Patient {
    private String name;
    private String birthdate;
    private String contact;
    private String patientId;

    public Patient(String patientName, String patientID, String birthdate, String contactNumber){
        this.name = patientName;
        this.birthdate = birthdate;
        this.patientId = patientID;
        this.contact = contactNumber;
    }

    public char getPatientType(){
        return this.patientId.charAt(0);
    }

    public void setName(String patientName){
        this.name = patientName;
    }

    public String getName(){
        return this.name;
    }

    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public String getBirthdate(){
        return this.birthdate;
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

    public boolean setContact(String contactNumber){
        if (contactNumber.length() == 10){
            this.contact = contactNumber;
            return true;
        }else {
            System.out.println("Invalid Contact Number");
            return false;
        }
    }

    public String getContact(){
        return this.contact;
    }
}
