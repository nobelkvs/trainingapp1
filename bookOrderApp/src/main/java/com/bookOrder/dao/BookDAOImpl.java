package com.bookOrder.dao;

import com.bookOrder.utils.MysqlConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAOImpl implements BookDAO {
    static final Logger log = Logger.getLogger(BookDAOImpl.class);

    private static final String SQL_GET_BOOK_ID = "SELECT book_id FROM book WHERE book_name=?";

    MysqlConnection c = new MysqlConnection();

    @Override
    public int getBookId(String bName) throws SQLException {

        log.info("inside get book dao---");

        Connection conn = null;
        int result = 0;
        ResultSet rs = null;

        try {
            conn = c.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error while connecting:" + e);
        }

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(SQL_GET_BOOK_ID);

            ps.setString(1, bName);

            rs = ps.executeQuery();

            while (rs.next()) {

                result = rs.getInt(1);
                log.info("returned id from book table is..." + result);
            }

        } catch (Exception e) {
            log.error("error while getting book id is---" + e);
            e.printStackTrace();
        }
        return result;

    }
}
