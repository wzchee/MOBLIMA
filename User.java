public class User {
    
    public static void main(String[] args){
        // User interface after logged into MOBLIMA as user

    }

    public User(String email, String password, int age, String name, String mobileNumber){
        // constructor
        this.email = email;
        this.password = password;
        this.age = age;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    private String email;
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    private String password; //don't think a get and set applies here

    private int age;
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}

    private String name;
    public String getName(){return name;}
    public void setName(int name){this.name = name;}

    private String mobileNumber;
    public String getMobileNumber(){return mobileNumber;}
    public void setMobileNumber(String mobileNumber){this.mobileNumber = mobileNumber;}
}
