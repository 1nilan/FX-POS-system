package dao;

import Dto.OrderDto;

import java.sql.SQLException;

public interface OrderModel {
    boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteOrder (String id) throws SQLException, ClassNotFoundException;
    boolean getLastOrder() throws SQLException, ClassNotFoundException;
}
