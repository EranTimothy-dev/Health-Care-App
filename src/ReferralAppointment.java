import java.util.Date;

public class ReferralAppointment extends Appointment {

     private Doctor referralDoctor;
     private String notes;
     private String referralDoctorNotes;

     public ReferralAppointment(Doctor doctor, Doctor referralDoctor ,Patient patient, Date date, String time, String note){
         super(doctor,patient,date,time);
         this.referralDoctor = referralDoctor;
         this.notes = note;
         this.referralDoctorNotes = "";
     }

     public void setReferralDoctor(Doctor doctor){
         this.referralDoctor = doctor;
     }

     public Doctor getReferralDoctor(){
         return this.referralDoctor;
     }

     public void setNotes(String notes){
         this.notes = notes;
     }

     public String getNotes(){
         return this.notes;
     }

     public void setReferralDoctorNotes(String notes){
         this.referralDoctorNotes = notes;
     }

     public void setReferralDoctorNotes(String[] notesArray){
         String concatenatedNote = "";
         for (String note : notesArray){
             concatenatedNote = note + " ";
         }
         this.referralDoctorNotes = concatenatedNote;
     }

     public String getReferralDoctorNotes(){
         return this.referralDoctorNotes;
     }

}
