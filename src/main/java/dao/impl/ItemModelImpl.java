package dao.impl;

import DB.DBConnection;
import Dto.CustomerDto;
import Dto.ItemDto;
import dao.ItemModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemModelImpl implements ItemModel {


    @Override
    public boolean saveItem(ItemDto dto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO ITEM VALUES('"+dto.getCode()+"','"+dto.getDes()+"','"+dto.getPrice()+"',"+dto.getQty()+")";

            Statement stm = connection.createStatement();
            return stm.executeUpdate(sql)>0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from ITEM WHERE code= ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,code);
        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ITEM SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, dto.getDes());
            pstm.setDouble(2, dto.getPrice());
            pstm.setInt(3, dto.getQty());
            pstm.setString(4, dto.getCode());

            return pstm.executeUpdate() > 0;
        }
    }


    @Override
    public List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
       String sql ="SELECT* FROM ITEM";
       PreparedStatement pstm =DBConnection.getInstance().getConnection().prepareStatement(sql);
       ResultSet resultSet =pstm.executeQuery();
       List<ItemDto> list = new ArrayList<>();

       while (resultSet.next()){
           list.add(new ItemDto(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getDouble(3),
                   resultSet.getInt(4)
           ));
       }
        return list;
    }
}
