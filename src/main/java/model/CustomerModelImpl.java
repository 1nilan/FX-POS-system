package model;

import DB.DBConnection;
import Dto.CustomerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerModelImpl implements CustomerModel{


    @Override
    public boolean saveCustomer(CustomerDto dto) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "INSERT INTO CUSTOMER VALUES('"+dto.getId()+"','"+dto.getName()+"','"+dto.getAddress()+"',"+dto.getSalary()+")";

            Statement stm = connection.createStatement();
            return stm.executeUpdate(sql)>0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        String sql = "DELETE from CUSTOMER WHERE id='"+id+"'";
        return false;

    }

    @Override
    public boolean updateCustomer(CustomerDto dto) {
        return false;
    }

    @Override
    public List<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
       String sql ="SELECT* FROM customer";
       PreparedStatement pstm =DBConnection.getInstance().getConnection().prepareStatement(sql);
       ResultSet resultSet =pstm.executeQuery();
       List<CustomerDto> list = new ArrayList<>();

       while (resultSet.next()){
           list.add(new CustomerDto(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getDouble(4)
           ));
       }
        return list;
    }
}
