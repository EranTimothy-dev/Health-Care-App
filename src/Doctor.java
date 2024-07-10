import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Doctor {
    public int doctorId;
    public String doctorName;
    public String birthday;
    public String specialization;
    public String contactNumber;
    public ArrayList<Date> availabilities;
    public HashMap<Date, ArrayList<Appointment>> appointments = new HashMap<>();

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

    public int getSlots(Date date){
        int time = 5;
        ArrayList<Appointment> existingAppointments = this.appointments.get(date);
        if (existingAppointments == null){
            return time;//
        } else if (existingAppointments.size() == 5) {
            return -1;
        }
        return time + existingAppointments.size();
    }

    public void setAppointments(Appointment appointment, Date date){
        ArrayList<Appointment> currentAppointments = this.appointments.get(date);
        if (currentAppointments == null){
            ArrayList<Appointment> tempArraylist = new ArrayList<>();
            tempArraylist.add(appointment);
            this.appointments.put(date, tempArraylist);
        } else {
            currentAppointments.add(appointment);
            this.appointments.put(date, currentAppointments);
        }
        System.out.println("Appointment added successfully.\n");
    }


}
