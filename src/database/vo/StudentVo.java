package database.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentVo {

    private IntegerProperty id;
    private StringProperty FName;
    private StringProperty SName;
    private StringProperty LName;
    private StringProperty mobile;
    private StringProperty courses;
    private IntegerProperty paidlMo;
    
    private int ID_Search;

    public StudentVo(int ID_Search) {
        this.ID_Search=ID_Search;
    }

    public StudentVo(int id, String FName, String SName, String LName, String mobile,  Integer paidlMo /*,String courses*/) {
        this.id = new SimpleIntegerProperty(id);
        this.FName = new SimpleStringProperty(FName);
        this.SName = new SimpleStringProperty(SName);
        this.LName = new SimpleStringProperty(LName);
        this.mobile = new SimpleStringProperty(mobile);
        this.paidlMo = new SimpleIntegerProperty(id);
        //this.courses =new SimpleStringProperty(courses);
    }
     public StudentVo(int id, String FName, String SName, String LName, String mobile,  Integer paidlMo ,String courses) {
        this.id = new SimpleIntegerProperty(id);
        this.FName = new SimpleStringProperty(FName);
        this.SName = new SimpleStringProperty(SName);
        this.LName = new SimpleStringProperty(LName);
        this.mobile = new SimpleStringProperty(mobile);
        this.paidlMo = new SimpleIntegerProperty(id);
        this.courses =new SimpleStringProperty(courses);
    }

    public String getCourses() {
        return courses.get();
    }

    public String getFName() {
        return FName.get();
    }

    public String getSName() {
        return SName.get();
    }

    public String getLName() {
        return LName.get();
    }

    public String getMobile() {
        return mobile.get();
    }

    public int getPaidlMo() {
        return paidlMo.get();
    }

    public int getId() {
        return id.get();
    }

    public int getID_Search() {
        return ID_Search;
    }


}
