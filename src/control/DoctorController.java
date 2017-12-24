/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Check.validation;
import database.dao.AdminDao;
import static database.dao.Dao.getconnection;
import database.dao.DoctorDao;
import database.vo.AdminVo;
import database.vo.DoctorVo;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class DoctorController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField secondname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField docpay;
    @FXML
    private TextField mobilenum;
    @FXML
    private Button add_bt;
    @FXML
    private Button update_bt;
    @FXML
    private TextField sea_phonenum;

    @FXML
    private Button delet_bt;
    @FXML
    private TableView<DoctorVo> table;
    @FXML
    private TableColumn<DoctorVo, Integer> coursenametable;
    @FXML
    private TableColumn<DoctorVo, String> fnametable;
    @FXML
    private TableColumn<DoctorVo, String> mobtable;
    @FXML
    private TableColumn<DoctorVo, String> lnametable;
    @FXML
    private TableColumn<DoctorVo, String> snametable;
    @FXML
    private TableColumn<DoctorVo, String> doctable;
    @FXML
    private TableColumn<DoctorVo, Integer> IDtable;
    @FXML
    private Button doc_sea;

    public ObservableList<DoctorVo> data = FXCollections.observableArrayList();

    int idnumber;
    int Docpay;
    DoctorVo doctorvo = null;
    Connection con = null;
    DoctorVo doctorVo = null;
    ResultSet rs = null;

    void clear() {
        firstname.setText("");
        secondname.setText("");
        lastname.setText("");
        id.setText("");
        docpay.setText("");
        mobilenum.setText("");

    }

    void insertTable() {
        
        table.getItems().clear();
        
        try {
            con = getconnection();
            String sql = "select * from doctor ";

            PreparedStatement ps = con.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                data.add(new DoctorVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (Exception ex) {

        }

        IDtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnametable.setCellValueFactory(new PropertyValueFactory<>("FName"));
        snametable.setCellValueFactory(new PropertyValueFactory<>("SName"));
        lnametable.setCellValueFactory(new PropertyValueFactory<>("LName"));
        mobtable.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        doctable.setCellValueFactory(new PropertyValueFactory<>("Doc_pay"));
        table.setItems(null);
        table.setItems(data);
        table.getItems().sorted();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        insertTable();
//        FilteredList<DoctorVo> filteredData = new FilteredList<>(data, p -> true);
////        table.getItems().clear();
//
//        sea_phonenum.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            filteredData.setPredicate(new Predicate<DoctorVo>() {
//                @Override
//                public boolean test(DoctorVo DoctorVo) {
//                    // If filter text is empty, display all persons.
//                    if (newValue == null || newValue.isEmpty()) {
//                        return true;
//                    }
//                    String lowerCaseFilter = newValue.toLowerCase();
//                    String id = String.valueOf(DoctorVo.getId());
//                    if (id.contains(lowerCaseFilter)) {
//                        return true;
//
//                    }
//
//                    return false; // Does not match.
//                }
//            });
//        });
//        SortedList<DoctorVo> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(table.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        table.setItems(sortedData);

    }

    @FXML
    private void ADD(MouseEvent event) throws Exception {
        validation valid = new Check.validation();

        if (validation.isEmpty(firstname.getText(), secondname.getText(), lastname.getText(), mobilenum.getText(), id.getText(), docpay.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter  Value ");
            return;
        }

        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher id_validation = p.matcher(id.getText());
        Matcher mobile_validation = p.matcher(mobilenum.getText());
        Matcher Doc_validation = p.matcher(docpay.getText());
        Matcher fname_validation = p2.matcher(firstname.getText());
        Matcher lname_validation = p2.matcher(lastname.getText());
        Matcher sname_validation = p2.matcher(secondname.getText());
        boolean c = fname_validation.find();
        boolean c2 = lname_validation.find();
        boolean c3 = sname_validation.find();

        boolean b = id_validation.find();
        boolean b2 = mobile_validation.find();
        boolean b3 = Doc_validation.find();

        if (b || b2 || b3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            id.setText("");
            docpay.setText("");
            mobilenum.setText("");
            if (c || c2 || c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                firstname.setText("");
                secondname.setText("");
                lastname.setText("");
            }
            return;
        } else if (c || c2 || c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            firstname.setText("");
            secondname.setText("");
            lastname.setText("");
            if (b || b2 || b3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

                id.setText("");
                docpay.setText("");
                mobilenum.setText("");
                return;
            }

            return;
        }

        int idnumber = Integer.parseInt(id.getText());
        int Docpay = Integer.parseInt(docpay.getText());

        DoctorVo doctorvo = new DoctorVo(idnumber, firstname.getText(), secondname.getText(), lastname.getText(), mobilenum.getText(), Docpay);
        try {
            int inseret = new DoctorDao().inseret(doctorvo);
            if (inseret == 1) {
                JOptionPane.showMessageDialog(null, "ADD Successfully");
                clear();
                insertTable();
  
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ADD Failure  , please Try again ?"+ex.getMessage());
            clear();
            mobilenum.setText("");

        }

    }

    @FXML
    private void Update(MouseEvent event) throws Exception {

        validation valid = new Check.validation();

        if (valid.isEmpty(firstname.getText(), secondname.getText(), lastname.getText(), mobilenum.getText(), id.getText(), docpay.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        }

        Pattern p = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");
        Matcher id_validation = p.matcher(id.getText());
        Matcher mobile_validation = p.matcher(mobilenum.getText());
        Matcher Doc_validation = p.matcher(docpay.getText());
        Matcher fname_validation = p2.matcher(firstname.getText());
        Matcher lname_validation = p2.matcher(lastname.getText());
        Matcher sname_validation = p2.matcher(secondname.getText());
        boolean c = fname_validation.find();
        boolean c2 = lname_validation.find();
        boolean c3 = sname_validation.find();

        boolean b = id_validation.find();
        boolean b2 = mobile_validation.find();
        boolean b3 = Doc_validation.find();

        if (b || b2 || b3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");
            id.setText("");
            docpay.setText("");
            mobilenum.setText("");
            if (c || c2 || c3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
                firstname.setText("");
                secondname.setText("");
                lastname.setText("");
            }
            return;
        } else if (c || c2 || c3) {
            JOptionPane.showMessageDialog(null, "Please ,Enter string Value ");
            firstname.setText("");
            secondname.setText("");
            lastname.setText("");
            if (b || b2 || b3) {
                JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

                id.setText("");
                docpay.setText("");
                mobilenum.setText("");
                return;
            }

            return;
        }

        int idnumber = Integer.parseInt(id.getText());
        int Docpay = Integer.parseInt(docpay.getText());

        DoctorVo doctorvo = new DoctorVo(idnumber, firstname.getText(), secondname.getText(), lastname.getText(), mobilenum.getText(), Docpay);
        try {
            int update = new DoctorDao().update(doctorvo);
            
            if (update == 1) {

                JOptionPane.showMessageDialog(null, "update Successfully");
                clear();
                insertTable();

            }else{
                JOptionPane.showMessageDialog(null, "the value dosnot exist to make update");
                clear();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "please try again");

        }

    }
      @FXML
    void search(MouseEvent event) {
        int id =Integer.parseInt(sea_phonenum.getText());
         table.getItems().clear();
        
        try {
            con = getconnection();
            String sql = "select * from doctor where ID =? ";

            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                data.add(new DoctorVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

            }
        } catch (Exception ex) {

        }

        IDtable.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnametable.setCellValueFactory(new PropertyValueFactory<>("FName"));
        snametable.setCellValueFactory(new PropertyValueFactory<>("SName"));
        lnametable.setCellValueFactory(new PropertyValueFactory<>("LName"));
        mobtable.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        doctable.setCellValueFactory(new PropertyValueFactory<>("Doc_pay"));
        table.setItems(null);
        table.setItems(data);
        table.getItems().sorted();

    }

    @FXML
    private void Delet(MouseEvent event) {

        validation valid = new Check.validation();

        if (validation.isEmpty(sea_phonenum.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;
        }
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher searchid_validation = p.matcher(sea_phonenum.getText());
        boolean c = searchid_validation.find();
        if (c) {
            JOptionPane.showMessageDialog(null, "Please ,Enter number Value ");

            sea_phonenum.setText("");
            return;
        }
        int search = Integer.parseInt(sea_phonenum.getText());
        //Deletrelation(search);
        DoctorVo doctorvo = new DoctorVo(search);
        try {
            new DoctorDao().delet(doctorvo);
            //JOptionPane.showMessageDialog(null, "delet Successfully");
            sea_phonenum.setText("");
            insertTable();

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "please try again");
            sea_phonenum.setText("");

        }
    }
//    void Deletrelation(int ID_Student) {
//        Connection con;
//        try {
//            con = getconnection();
//
//            String sql = "DELETE FROM course WHERE Doc_ID= ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, ID_Student);
//            ps.execute();
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//            JOptionPane.showMessageDialog(null, " error in delet to relation ");
//        }
//
//    }
    
}


