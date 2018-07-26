package com.bookOrder.dao;

import java.sql.SQLException;

/**
 * Interface for BookDAOImpl class that has getBookId method
 */
public interface BookDAO {
    int getBookId(String bName) throws SQLException;

}
