package database.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorVo {

    private  IntegerProperty id;
    private  StringProperty FName;
    private  StringProperty SName;
    private  StringProperty LName;
    private  StringProperty mobile;
    private  IntegerProperty Doc_pay;
    private int search ; 

    public DoctorVo(int search) {
        this.search=search;
    }
    
    
    public DoctorVo(int id, String FName, String SName, String LName, String mobile, int Doc_Pay) {
        this.id = new SimpleIntegerProperty(id);
        this.FName = new SimpleStringProperty(FName);
        this.LName = new SimpleStringProperty(LName);
        this.SName = new SimpleStringProperty(SName);
        this.mobile = new SimpleStringProperty(mobile);
        this.Doc_pay = new SimpleIntegerProperty(Doc_Pay);
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

    public int getDoc_pay() {
        return Doc_pay.get();
    }

    public int getId() {
        return id.get();
    }

    public int getSearch() {
        return search;
    }

    
    

}
