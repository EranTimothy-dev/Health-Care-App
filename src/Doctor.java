import java.util.*;

public class Doctor extends Person {
    private final int doctorId;
    private String specialization;
    private final ArrayList<Date> availabilities;
    private final HashMap<Date, ArrayList<Appointment>> appointments = new HashMap<>();


    public Doctor(int id,String name, String birthday, String specialization, String contactNumber){
        super(name,birthday,contactNumber);
        this.doctorId = id;
        this.specialization = specialization;
        availabilities = new ArrayList<>();// each doctor has his own availability array
    }


    public int getDoctorId(){
        return this.doctorId;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public String getSpecialization(){
        return this.specialization;
    }

    public void setDoctorAvailability(Date availability) {
        this.availabilities.add(availability);
    }

    public ArrayList<Date> getAvailabilities(){
        return this.availabilities;
    }

    public int getSlots(Date date){
        ArrayList<Appointment> existingAppointments = this.appointments.get(date);
        int NUMBER_OF_SLOTS = 5;
        if (existingAppointments == null){
            return NUMBER_OF_SLOTS;
        } else if (existingAppointments.size() == 5) {
            return -1;
        }
        return NUMBER_OF_SLOTS + existingAppointments.size();
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
