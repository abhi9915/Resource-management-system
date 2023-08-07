package Data_classes;

public class My_resources_model {
    public String id;
    public String location;
    public String email;
    public String item;

    public My_resources_model(String id, String location, String email, String item) {
        this.id = id;
        this.location = location;
        this.email = email;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


}
