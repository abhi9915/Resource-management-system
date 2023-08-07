package Data_classes;

public class Request_Attributes {
    public String quantity;
    public String category;
    public String item;
    public String location;
    public String email;
    public String key;
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public Request_Attributes(){}

    public Request_Attributes(String quantity, String category, String item, String location, String email,String key)
    {
        this.quantity=quantity;
        this.category=category;
        this.item=item;
        this.location=location;
        this.email=email;
        status="no";
        this.key=key;
    }

}
