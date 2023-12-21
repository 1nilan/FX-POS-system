package controller;

import Dto.CustomerDto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.CustomerModel;
import dao.impl.CustomerModelImpl;
import Dto.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnUpdate;
    public JFXButton btnSave;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colDelete;
    public TableView<CustomerTm> tblCustomer;
    public AnchorPane cusPane;

    private CustomerModel customerModel = new CustomerModelImpl();

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomers();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtId.setEditable(false);
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
        }
    }
    private void loadCustomers() {
        try {
            List<CustomerDto> list = customerModel.getAllCustomers();
            ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

            for (CustomerDto dto:list) {
                Button btn = new Button("Delete");

                tmList.add(new CustomerTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
                        btn
                ));
                btn.setOnAction(actionEvent -> {
                deleteCustomers(dto.getId());
                });
            }

            tblCustomer.setItems(tmList);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCustomers(String id) {
        try {
            boolean deleted = customerModel.deleteCustomer(id);

            if(deleted==true){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted :)").show();
                loadCustomers();
            }else{
                new Alert(Alert.AlertType.ERROR,"ERROR  :(").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = customerModel.updateCustomer(new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText())));

            if (isUpdate==true){
                new Alert(Alert.AlertType.INFORMATION,"Customer Update...!").show();
                loadCustomers();


            }else{
                new Alert(Alert.AlertType.ERROR,"ERROR :( ").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = new CustomerDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        boolean isSaved = customerModel.saveCustomer(customerDto);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Customer saved!").show();
            loadCustomers();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }



    public void reloadButtonOnction(ActionEvent actionEvent) {

    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cusPane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
