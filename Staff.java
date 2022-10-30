public class Staff {
    
    public static void main(String[] args){
        // User interface after logged into MOBLIMA as staff
        System.out.println("Logged in");

    }

    public Staff(String email, String password, Cineplex workplace){
        this.email = email;
        this.password = password;
        this.workplace = workplace;
    }

    private String email;
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    private String password; //don't think a get and set applies here

    private Cineplex workplace;
    public Cineplex getWorkplace(){return workplace;}
    public void setWorkplace(Cineplex workplace){this.workplace = workplace;}
}
