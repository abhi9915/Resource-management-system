package Data_classes;


public class Resources_Attributes {
    public String name;
    public String quantity;
    public String cat_name;

    public Resources_Attributes(){}
    public Resources_Attributes(String name, String quantity, String cat_name) {
        this.name = name;
        this.quantity = quantity;
        this.cat_name = cat_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}

