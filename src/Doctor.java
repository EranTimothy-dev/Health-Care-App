import java.util.ArrayList;
import java.util.Date;

public class Doctor {
    public int doctorId;
    public String doctorName;
    public String birthday;
    public String specialization;
    public String contactNumber;
    public ArrayList<Date> availabilities;

    public Doctor(int id,String Name, String birthday, String specialization, String contactNumber){
        this.doctorName = Name;
        this.doctorId = id;
        this.birthday = birthday;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        availabilities = new ArrayList<>();// each doctor has his own availability array
    }


    public boolean isPhysician(){
        return this.specialization.endsWith("Physician") || this.specialization.endsWith("physician");
    }

    public void setDoctorAvailability(Date availability ) {
        this.availabilities.add(availability);
    }
}
