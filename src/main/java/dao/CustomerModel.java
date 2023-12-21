package dao;

import Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerModel {

    boolean saveCustomer (CustomerDto dto);
    boolean deleteCustomer (String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer (CustomerDto dto) throws SQLException, ClassNotFoundException;
    List<CustomerDto> getAllCustomers () throws SQLException, ClassNotFoundException;


}
