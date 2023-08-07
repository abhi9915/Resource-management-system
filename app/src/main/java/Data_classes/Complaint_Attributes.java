package Data_classes;

import android.widget.EditText;

public class Complaint_Attributes {

    public String description;
    public String category;
    public String item;
    public String email;
    public String dept;
    public String key;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int w,x,y,z;

    Complaint_Attributes(){}
    public Complaint_Attributes(String description, String category, String item, String email, String dept,int w, int x, int y, int z,String key)
    {
        this.description=description;
        this.category=category;
        this.item=item;
        this.email=email;
        this.dept=dept;
        this.key = key;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
