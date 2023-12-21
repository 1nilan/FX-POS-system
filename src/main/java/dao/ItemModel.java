package dao;

import Dto.CustomerDto;
import Dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemModel {

    boolean saveItem (ItemDto dto);
    boolean deleteItem (String code) throws SQLException, ClassNotFoundException;
    boolean updateItem (ItemDto dto) throws SQLException, ClassNotFoundException;
    List<ItemDto> getAllItem () throws SQLException, ClassNotFoundException;


}
