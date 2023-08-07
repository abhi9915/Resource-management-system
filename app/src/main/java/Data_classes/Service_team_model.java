package Data_classes;

public class Service_team_model {
    public String approved;
    public String email;
    public String username,password;

    public Service_team_model(String approved, String email, String username, String password) {
        this.approved = approved;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
