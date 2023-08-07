package Data_classes;

public class AllResourcesAttribute {
    public String item, email,location, quantity, status;

    public AllResourcesAttribute(){}
    public AllResourcesAttribute(String item, String email, String location, String quantity, String status) {
        this.item = item;
        this.email = email;
        this.location = location;
        this.quantity = quantity;
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
