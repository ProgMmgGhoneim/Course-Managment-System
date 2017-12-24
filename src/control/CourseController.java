/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Check.validation;
import database.dao.CourseDao;
import static database.dao.Dao.getconnection;
import database.dao.DoctorDao;
import database.dao.StudentDao;
import database.vo.CourseVo;
import database.vo.DoctorVo;
import database.vo.StudentVo;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mustafa
 */
public class CourseController implements Initializable {

    @FXML
    private TextField cours_name;
    @FXML
    private TextField curse_price;
    @FXML
    private TextField course_hour;
    @FXML
    private ComboBox<?> DOC_COMBO;
    @FXML
    private Button Add_bt;
    @FXML
    private Button Update_bt;
    @FXML
    private TextField course_sea;
    @FXML
    private Button delet_bt;
    @FXML
    private TableColumn<CourseVo, Integer> chourtable;
    @FXML
    private TableColumn<CourseVo, String> cnametable;
    @FXML
    private TableColumn<CourseVo, Integer> cpricetable;
    @FXML
    private TableView<CourseVo> tableview;
    @FXML
    private TableColumn<CourseVo, Integer> doctable;
    @FXML
    private Button cousearch;

    public ObservableList<CourseVo> data = FXCollections.observableArrayList();
    public ObservableList option = FXCollections.observableArrayList();
    private ObservableList<CourseVo> filteredData = FXCollections.observableArrayList();

    StudentVo studentvo = null;
    Connection con = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        table_insert();
        // combobox
        try {
            con = getconnection();
            String sql1 = "select ID from doctor ";
             ResultSet rs2 = null;
            PreparedStatement ps = con.prepareCall(sql1);
            rs2 = ps.executeQuery();

            while (rs2.next()) {

                option.add(rs2.getString("ID"));

            }
        } catch (Exception ex) {

        }
        DOC_COMBO.getItems().addAll(option);
        ///search
    }
    @FXML
    private void ADD(MouseEvent event) {validation valid = new Check.validation();
            boolean x = DOC_COMBO.getSelectionModel().isEmpty();
        if (validation.isEmpty(cours_name.getText(), course_hour.getText(), curse_price.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please ,Enter  Value ");
            return;
        }
        else if (x) {
            JOptionPane.showMessageDialog(null, "   Ooooops\n You Must Choice Value");
            return;
        }
        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher cournameValidation = p2.matcher(cours_name.getText());
        Matcher coursrPriceVaild = p.matcher(curse_price.getText());
        Matcher coursrHourVaild = p.matcher(course_hour.getText());
        boolean c = cournameValidation.find();
        boolean c2 = coursrPriceVaild.find();
        boolean c3 = coursrHourVaild.find();

      
        if (c2||c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            course_hour.setText("");
            curse_price.setText("");
            if (c ) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                clear();
            }
            return;
        } else if (c ) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            cours_name.setText("");
            if (c2||c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
                clear();
                return;
            }

            return;
        }
        int courseHour = Integer.parseInt(course_hour.getText());
        int courseprice = Integer.parseInt(curse_price.getText());
        int Doc = Integer.parseInt(DOC_COMBO.getValue().toString());

        CourseVo courseVo = new CourseVo(cours_name.getText(), courseHour, courseprice, Doc);
        try {
            int inseret = new CourseDao().inseret(courseVo);
            if (inseret == 1) {

                JOptionPane.showMessageDialog(null, "ADD Successfully");
                clear();
                table_insert();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ADD Failure  , please Try again ?");
            clear();
        }
    }

    @FXML
    private void Update(MouseEvent event) {
            boolean x = DOC_COMBO.getSelectionModel().isEmpty();
        if (validation.isEmpty(cours_name.getText(), course_hour.getText(), curse_price.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please ,Enter  Value ");
            return;
        }
        else if (x) {
            JOptionPane.showMessageDialog(null, "   Ooooops\n You Must Choice Value");
            return;
        }
        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher cournameValidation = p2.matcher(cours_name.getText());
        Matcher coursrPriceVaild = p.matcher(curse_price.getText());
        Matcher coursrHourVaild = p.matcher(course_hour.getText());
        boolean c = cournameValidation.find();
        boolean c2 = coursrPriceVaild.find();
        boolean c3 = coursrHourVaild.find();

      
        if (c2||c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            course_hour.setText("");
            curse_price.setText("");
            if (c ) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                clear();
            }
            return;
        } else if (c ) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            cours_name.setText("");
            if (c2||c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
                clear();
                return;
            }

            return;
        }
        
        int courseHour = Integer.parseInt(course_hour.getText());
        int courseprice = Integer.parseInt(curse_price.getText());
        int Doc = Integer.parseInt(DOC_COMBO.getValue().toString());

        CourseVo courseVo = new CourseVo(cours_name.getText(), courseHour, courseprice, Doc);
        try {
            int update = new CourseDao().update(courseVo);
            if (update == 1) {
                JOptionPane.showMessageDialog(null, "update Successfully");
                clear();
                table_insert();
            }else{
             JOptionPane.showMessageDialog(null, "the value dosnot exist to make update");
             clear();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ADD Failure  , please Try again ?");

        }
    }
        @FXML
    void search(MouseEvent event) {
        String text = course_sea.getText();
        tableview.getItems().clear();
        Connection con = null;
        DoctorVo doctorVo = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            con = getconnection();
            String sql = "select * from course where Course_Name=? ";

            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, text);
            rs = ps.executeQuery();

            while (rs.next()) {

                data.add(new CourseVo(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));

            }
        } catch (Exception ex) {

        }

        cnametable.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));
        cpricetable.setCellValueFactory(new PropertyValueFactory<>("Course_Price"));
        chourtable.setCellValueFactory(new PropertyValueFactory<>("Course_Hour"));
        doctable.setCellValueFactory(new PropertyValueFactory<>("Doc_id"));
        tableview.setItems(null);
        tableview.setItems(data);
        

    }

    @FXML
    private void Delet(MouseEvent event) throws Exception {
        if (validation.isEmpty(course_sea.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please ,Enter  Value ");
            return;
        }
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher courseaValidation = p2.matcher(course_sea.getText());
        boolean c = courseaValidation.find();
       if (c ) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            course_sea.setText("");
            return;
       }
        Deletrelation(course_sea.getText());
        Deletrelation2(course_sea.getText());
        CourseVo coursevo = new CourseVo(course_sea.getText());
        try {
            new CourseDao().delet(coursevo);
            clear();
            table_insert();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "please try again"+ex.getMessage());
            course_sea.setText("");

        }
    }

    void table_insert() {

        tableview.getItems().clear();
        Connection con = null;
        DoctorVo doctorVo = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            con = getconnection();
            String sql = "select * from course ";

            PreparedStatement ps = con.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                data.add(new CourseVo(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));

            }
        } catch (Exception ex) {

        }

        cnametable.setCellValueFactory(new PropertyValueFactory<>("Course_Name"));
        cpricetable.setCellValueFactory(new PropertyValueFactory<>("Course_Price"));
        chourtable.setCellValueFactory(new PropertyValueFactory<>("Course_Hour"));
        doctable.setCellValueFactory(new PropertyValueFactory<>("Doc_id"));
        tableview.setItems(null);
        tableview.setItems(data);
    }

    void clear() {

        cours_name.setText("");
        course_hour.setText("");
        curse_price.setText("");
        course_sea.setText("");

    }

    void Deletrelation(String ID_Student) {
        try {
            con = getconnection();
            String sql = "DELETE FROM relation_course_student WHERE cou= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ID_Student);
            ps.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, " error in delet to relation ");
        }

    }
    void Deletrelation2(String ID_Student) {
        try {
            con = getconnection();
            String sql = "DELETE FROM student where course =? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ID_Student);
            ps.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, " error in delet to relation ");
        }

    }
   
}
