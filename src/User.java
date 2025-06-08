public class User {
    private String firstName;
    private int id;

    public User(String firstName,int id) {
        this.firstName = firstName;
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }
    public int getId() {
        return id;
    }
}