public abstract class Person {
    public String name;
    public String birthday;
    public String contactNumber;

    public Person(String name, String birthday, String contactNumber){
        this.name = name;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setBirthday(String birthdate){
        this.birthday = birthdate;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public boolean setContactNumber(String contact){
        if (contact.length() == 10){
            this.contactNumber = contact;
            return true;
        }
        return false;
    }

    public String getContactNumber(){
        return this.contactNumber;
    }

}
