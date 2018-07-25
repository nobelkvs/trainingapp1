package com.bookOrder.dao;

import java.sql.SQLException;

public interface BookDAO {
    int getBookId(String bName) throws SQLException;

}
