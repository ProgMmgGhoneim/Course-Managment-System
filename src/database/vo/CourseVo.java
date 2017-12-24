/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class CourseVo {
    private StringProperty Course_Name;
    private IntegerProperty Course_Price;
    private IntegerProperty Course_Hour;
    private IntegerProperty Doc_id;
    
    private String courseSearch;

    public CourseVo(String courseSearch) {
        this.courseSearch = courseSearch;
    }

    public CourseVo(String Course_Name, Integer Course_Hour , Integer Course_Price, Integer Doc_id) {
        this.Course_Name = new SimpleStringProperty(Course_Name);
        this.Course_Price = new SimpleIntegerProperty(Course_Price);
        this.Course_Hour = new SimpleIntegerProperty(Course_Hour);
        this.Doc_id = new SimpleIntegerProperty(Doc_id);
    }

    public String getCourse_Name() {
        return Course_Name.get();
    }

    public Integer getCourse_Price() {
        return Course_Price.get();
    }

    public Integer getCourse_Hour() {
        return Course_Hour.get();
    }

    public Integer getDoc_id() {
        return Doc_id.get();
    }

    public String getCourseSearch() {
        return courseSearch;
    }
    
    
   
    
    
}
