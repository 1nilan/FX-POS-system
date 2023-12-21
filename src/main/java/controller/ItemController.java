package controller;

import Dto.ItemDto;
import Dto.tm.ItemTm;
import com.jfoenix.controls.JFXTextField;
import dao.ItemModel;
import dao.impl.ItemModelImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemController {
    public AnchorPane itempane;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public Button btnBack;
    public TableView tblItem;
    public TableColumn colCode;

    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colOption;
    public TableColumn colDescription;


    private ItemModel itemModel = new ItemModelImpl();


    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItem();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((ItemTm) newValue);
        });

    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            txtCode.setEditable(false);
            txtCode.setText(newValue.getCode());
            txtDescription.setText(newValue.getDes());
            txtPrice.setText(String.valueOf(newValue.getPrice()));
            txtQty.setText(String.valueOf(newValue.getQty()));

        }
    }


    private void loadItem(){
        try {
            List<ItemDto> list = itemModel.getAllItem();
            ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

            for (ItemDto dto:list) {
                Button btn = new Button("Delete");

                tmList.add(new ItemTm(
                        dto.getCode(),
                        dto.getDes(),
                        dto.getPrice(),
                        dto.getQty(),
                        btn
                ));
                btn.setOnAction(actionEvent -> {
                    deleteItem(dto.getCode());
                });
            }

            tblItem.setItems(tmList);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteItem(String code) {

        try {
            boolean deleted = itemModel.deleteItem(code);

            if(deleted==true){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted :)").show();
                loadItem();
            }else{
                new Alert(Alert.AlertType.ERROR,"ERROR  :(").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) itempane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateButtonOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = itemModel.updateItem(new ItemDto(txtCode.getText(), txtDescription.getText(),Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText())));

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Item Update...!").show();
                loadItem();


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

        ItemDto dto = new ItemDto(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText())
        );

        boolean isSaved = itemModel.saveItem(dto);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION,"Item Added..!").show();
            loadItem();
        }else {
            new Alert(Alert.AlertType.ERROR,"ERROR :(").show();
        }
    }
}
