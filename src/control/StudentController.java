package control;

import Check.validation;
import static database.dao.Dao.getconnection;
import database.dao.DoctorDao;
import database.dao.StudentDao;
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

public class StudentController implements Initializable {

    @FXML
    private TextField ID;
    @FXML
    private TextField FName;
    @FXML
    private TextField Sname;
    @FXML
    private TextField Lname;
    @FXML
    private TextField Mobile;
    @FXML
    private TextField Total_Pay;
    @FXML
    private TextField Paai_mony;
    @FXML
    private Button Add_bt;
    @FXML
    private Button UPdate_bt;
    @FXML
    private TextField Search_ID;
    @FXML
    private Button search_bt;
    @FXML
    private Button delet_bt;
    @FXML
    private ComboBox<?> course_combobox;
    @FXML
    private TableColumn<StudentVo, Integer> idtable;
    @FXML
    private TableColumn<StudentVo, String> fnametable;
    @FXML
    private TableColumn<StudentVo, String> lnametable;
    @FXML
    private TableColumn<StudentVo, String> snametable;
    @FXML
    private TableView<StudentVo> tableview;
    @FXML
    private TableColumn<StudentVo, Integer> mobiletable;
    @FXML
    private TableColumn<StudentVo, String> coursetable;
    @FXML
    private TableColumn<StudentVo, Integer> paidtable;

    StudentVo studentvo = null;
    Connection con = null;
    ResultSet rs = null;

    public ObservableList<StudentVo> data = FXCollections.observableArrayList();
    public ObservableList option = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table_insert();
        // combobox
        try {
            ResultSet rs2 = null;
            con = getconnection();
            String sql1 = "select Course_Name from course ";

            PreparedStatement ps = con.prepareCall(sql1);
            rs2 = ps.executeQuery();

            while (rs2.next()) {

                option.add(rs2.getString("Course_Name"));

            }
        } catch (Exception ex) {

        }
        course_combobox.getItems().addAll(option);
//        ///search
//        FilteredList<StudentVo> filteredData = new FilteredList<>(data, p -> true);
//
//        Search_ID.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            filteredData.setPredicate(new Predicate<StudentVo>() {
//                @Override
//                public boolean test(StudentVo StudentVo) {
//                    // If filter text is empty, display all persons.
//                    if (newValue == null || newValue.isEmpty()) {
//                        return true;
//                    }
//                    String lowerCaseFilter = newValue.toLowerCase();
//                    String id = String.valueOf(StudentVo.getId());
//                    if (id.contains(lowerCaseFilter)) {
//                        return true;
//
//                    }
//
//                    return false; // Does not match.
//                }
//            });
//        });
//        SortedList<StudentVo> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        tableview.setItems(sortedData);

    }

    @FXML
    private void ADD(MouseEvent event) {

        ////////validation///////////////
        boolean x = course_combobox.getSelectionModel().isEmpty();
        if (validation.isEmpty(ID.getText(), FName.getText(), Sname.getText(), Lname.getText(), Total_Pay.getText(), Paai_mony.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        } else if (x) {
            JOptionPane.showMessageDialog(null, "   Ooooops\n You Must Choice Value");
            return;
        }

        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher id_validation = p.matcher(ID.getText());
        Matcher mobile_validation = p.matcher(Mobile.getText());
        Matcher Doc_validation = p.matcher(Paai_mony.getText());
        Matcher fname_validation = p2.matcher(FName.getText());
        Matcher lname_validation = p2.matcher(Lname.getText());
        Matcher sname_validation = p2.matcher(Sname.getText());
        boolean c = fname_validation.find();
        boolean c2 = lname_validation.find();
        boolean c3 = sname_validation.find();

        boolean b = id_validation.find();
        boolean b2 = mobile_validation.find();
        boolean b3 = Doc_validation.find();

        if (b || b2 || b3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            ID.setText("");
            Paai_mony.setText("");
            Mobile.setText("");
            if (c || c2 || c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                FName.setText("");
                Lname.setText("");
                Sname.setText("");
            }
            return;
        } else if (c || c2 || c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            FName.setText("");
            Sname.setText("");
            Lname.setText("");
            if (b || b2 || b3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

                ID.setText("");
                Paai_mony.setText("");
                Mobile.setText("");
                return;
            }

            return;
        }
/////end of validation////////////////

        int idnumber = Integer.parseInt(ID.getText());
        int Docpay = Integer.parseInt(Paai_mony.getText());

        StudentVo studentvo = new StudentVo(idnumber, FName.getText(), Sname.getText(), Lname.getText(), Mobile.getText(), Docpay,course_combobox.getValue().toString());
        try {
            int insert = new StudentDao().inseret(studentvo);
            if (insert == 1) {
                //// insert in relation 
                insertInRelation(idnumber, (String) course_combobox.getValue());
                JOptionPane.showMessageDialog(null, "add Successfully");
                clear();
                table_insert();

            }
        } catch (Exception ex) {
            try {

                insertInRelation(idnumber, (String) course_combobox.getValue());
                clear();
            } catch (Exception ex2) {
                JOptionPane.showMessageDialog(null, "you already take this course");

            }
        }
    }

    @FXML
    private void Update(MouseEvent event) {
                ////////validation///////////////
        boolean x = course_combobox.getSelectionModel().isEmpty();
        if (validation.isEmpty(ID.getText(), FName.getText(), Sname.getText(), Lname.getText(), Total_Pay.getText(), Paai_mony.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        } else if (x) {
            JOptionPane.showMessageDialog(null, "   Ooooops\n You Must Choice Value");
            return;
        }

        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher id_validation = p.matcher(ID.getText());
        Matcher mobile_validation = p.matcher(Mobile.getText());
        Matcher Doc_validation = p.matcher(Paai_mony.getText());
        Matcher fname_validation = p2.matcher(FName.getText());
        Matcher lname_validation = p2.matcher(Lname.getText());
        Matcher sname_validation = p2.matcher(Sname.getText());
        boolean c = fname_validation.find();
        boolean c2 = lname_validation.find();
        boolean c3 = sname_validation.find();

        boolean b = id_validation.find();
        boolean b2 = mobile_validation.find();
        boolean b3 = Doc_validation.find();

        if (b || b2 || b3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            ID.setText("");
            Paai_mony.setText("");
            Mobile.setText("");
            if (c || c2 || c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                FName.setText("");
                Lname.setText("");
                Sname.setText("");
            }
            return;
        } else if (c || c2 || c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            FName.setText("");
            Sname.setText("");
            Lname.setText("");
            if (b || b2 || b3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

                ID.setText("");
                Paai_mony.setText("");
                Mobile.setText("");
                return;
            }

            return;
        }
/////end of validation////////////////

        
        int idnumber = Integer.parseInt(ID.getText());
        int Docpay = Integer.parseInt(Paai_mony.getText());

        StudentVo studentvo = new StudentVo(idnumber, FName.getText(), Sname.getText(), Lname.getText(), Mobile.getText(), Docpay,course_combobox.getValue().toString());
        try {
            int update = new StudentDao().update(studentvo);
            if (update == 1) {

                JOptionPane.showMessageDialog(null, "update Successfully");
                clear();
                table_insert();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "please try again");

        }

    }

    @FXML
    private void Search(MouseEvent event) {
        
         if (validation.isEmpty(Search_ID.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        }
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher searchid_validation = p.matcher(Search_ID.getText());
        boolean c = searchid_validation.find();
        if (c) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

            Search_ID.setText("");
            return;
        }

        int id =Integer.parseInt(Search_ID.getText());
         tableview.getItems().clear();
        try {
            con = getconnection();
            String sql = "select * from student where ID=? ";

            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new StudentVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7)));

            }
        } catch (Exception ex) {

        }
        idtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnametable.setCellValueFactory(new PropertyValueFactory<>("FName"));
        snametable.setCellValueFactory(new PropertyValueFactory<>("SName"));
        lnametable.setCellValueFactory(new PropertyValueFactory<>("LName"));
        mobiletable.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        paidtable.setCellValueFactory(new PropertyValueFactory<>("paidlMo"));
        coursetable.setCellValueFactory(new PropertyValueFactory<>("courses"));
        tableview.setItems(null);
        tableview.setItems(data);
        
    }

    @FXML
    private void Delet(MouseEvent event) {
        if (validation.isEmpty(Search_ID.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        }
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher searchid_validation = p.matcher(Search_ID.getText());
        boolean c = searchid_validation.find();
        if (c) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

            Search_ID.setText("");
            return;
        }

        int search = Integer.parseInt(Search_ID.getText());
        Deletrelation(search);
        StudentVo studentvo = new StudentVo(search);
        try {
            new StudentDao().delet(studentvo);
            JOptionPane.showMessageDialog(null, "delet Successfully");
            Search_ID.setText("");

            table_insert();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "please try again");
            Search_ID.setText("");

        }
    }

    void table_insert() {

        tableview.getItems().clear();
        try {
            con = getconnection();
            String sql = "select * from student ";

            PreparedStatement ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                data.add(new StudentVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7)));

            }
        } catch (Exception ex) {

        }
        idtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnametable.setCellValueFactory(new PropertyValueFactory<>("FName"));
        snametable.setCellValueFactory(new PropertyValueFactory<>("SName"));
        lnametable.setCellValueFactory(new PropertyValueFactory<>("LName"));
        mobiletable.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        paidtable.setCellValueFactory(new PropertyValueFactory<>("paidlMo"));
        coursetable.setCellValueFactory(new PropertyValueFactory<>("courses"));
        tableview.setItems(null);
        tableview.setItems(data);

    }
    @FXML
    void Comboboxvalue(MouseEvent event) {
        
       
        try {
            con = getconnection();
            String sql = "select price from course where Course_Name=?";

            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, course_combobox.getValue().toString());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Total_Pay.setText(rs.getString("price"));
                
                
            }
        } catch (Exception ex) {

        }

    }

    void clear() {

        ID.setText("");
        Paai_mony.setText("");
        Mobile.setText("");
        FName.setText("");
        Sname.setText("");
        Lname.setText("");
        Total_Pay.setText("");

    }

    void insertInRelation(int Id, String coursename) {

        int count = 0;
        try {
            con = getconnection();
            String sql = "insert into relation_course_student (cou,stu) values(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coursename);
            ps.setInt(2, Id);
            count = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "in inseret to relation ");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, " error in inseret to relation ");

        }

    }

    void Deletrelation(int ID_Student) {
        Connection con;
        try {
            con = getconnection();

            String sql = "DELETE FROM relation_course_student WHERE stu= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID_Student);
            ps.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            JOptionPane.showMessageDialog(null, " error in delet to relation ");
        }

    }

}
