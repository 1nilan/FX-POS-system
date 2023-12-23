package dao.impl;

import DB.DBConnection;
import Dto.OrderDetailDto;
import Dto.OrderDto;
import dao.OrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderImpl implements OrderModel {

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO orders VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getOrderId());
            pstm.setString(2, dto.getDate());
            pstm.setString(3, dto.getCustId());

            if (pstm.executeUpdate() > 0) {

                    return true;
                
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean getLastOrder() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderdetail VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        OrderDetailDto[] list;
        for (OrderDetailDto dto:list) {
            pstm.setString(1,dto.getOrderId());
            pstm.setString(2,dto.getItemCode());
            pstm.setInt(3,dto.getQty());
            pstm.setDouble(4,dto.getUnitPrice());
            if (!(pstm.executeUpdate()>0)){
                return false;
            }
        }
        return true;
    }

    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE from orders WHERE id= ?";

        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

    }
}
