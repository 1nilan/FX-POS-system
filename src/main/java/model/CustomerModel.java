package model;

import Dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerModel {

    boolean saveCustomer (CustomerDto dto);
    boolean deleteCustomer (String id);
    boolean updateCustomer (CustomerDto dto);
    List<CustomerDto> getAllCustomers () throws SQLException, ClassNotFoundException;


}
