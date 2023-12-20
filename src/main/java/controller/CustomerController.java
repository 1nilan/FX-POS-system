package controller;

import Dto.CustomerDto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerModel;
import model.CustomerModelImpl;
import Dto.tm.CustomerTm;

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

    private CustomerModel customerModel = new CustomerModelImpl();

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomers();
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
            }

            tblCustomer.setItems(tmList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
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
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }



    public void reloadButtonOnction(ActionEvent actionEvent) {
    }
}
