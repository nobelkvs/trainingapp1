package com.groups.dao;

import com.groups.model.Group;
import com.groups.utils.MysqlConnection;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @var SQL_CREATE_GROUP holds the query to insert details into the table
 * @var SQL_DELETE_GROUPS_BY_GID holds the delete query to delete based on group id
 * @var SQL_GROUP_BY_GROUP_NAME holds the query to retrieve the data
 */
public class GroupDaoImp implements GroupDao {

    static final Logger log = Logger.getLogger(GroupDaoImp.class);

    private static final String SQL_CREATE_GROUP = "INSERT INTO Groups(GroupName,Owner,SendAs,Feedback) values(?,?,?,?)";
    private static final String SQL_DELETE_GROUPS_BY_GID = "DELETE FROM Groups WHERE gid=?";
    private static final String SQL_GROUP_BY_GROUP_NAME = "SELECT * FROM Groups WHERE GroupName=?";

    MysqlConnection c = new MysqlConnection();

    /**
     * CreateGroupDao for creating the group and inserting the values into the database.
     *
     * @param group
     * @return insertStatus
     * @throws SQLException
     */
    @Override
    public int CreateGroup(Group group) throws SQLException {
        //get the connection
        Connection con = c.getConnect();
        log.info("Connection" + con);
        int insertStatus = 0;

        try {

            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(SQL_CREATE_GROUP);
            ps.setString(1, group.getGroupName());
            ps.setString(2, group.getOwner());
            ps.setString(3, group.getSendAs());
            ps.setString(4, group.getFeedback());

            insertStatus = ps.executeUpdate();
            log.info(insertStatus);
            con.commit();
        } catch (SQLException e) {
            log.info("creation group catch", e);
        }
        return insertStatus;
    }

    /**
     * RetrieveGroupDetails accepts the input groupName and returns the Group details for that groupName.
     *
     * @param groupName
     * @return list
     * @throws SQLException
     */
    @Override
    public List<Group> RetrieveGroupDetails(String groupName) throws SQLException {
        List<Group> list = new ArrayList<Group>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //get the connection
        Connection con = null;
        try {
            con = c.getConnect();
        } catch (Exception e) {
            log.info("Not connected in dao", e);
        }

        try {

            ps = con.prepareStatement(SQL_GROUP_BY_GROUP_NAME);
            ps.setString(1, groupName);
            log.info(groupName);
            rs = ps.executeQuery();
            //Retrieving group details
            while (rs.next()) {
                Group group = new Group();
                group.setGid(rs.getInt(1));
                group.setGroupName(rs.getString(2));
                group.setOwner(rs.getString(3));
                group.setSendAs(rs.getString(4));
                group.setFeedback(rs.getString(5));
                list.add(group);
            }
        } catch (Exception e) {
            log.info(e);
        }
        log.info(list);
        return list;
    }

    /**
     * deleteGroupDao accepts the input gid array and it deletes based on the gids given by user.
     *
     * @param gid
     * @return deleteStatus
     */
    @Override
    public int deleteGroup(String[] gid) {
        //get the connection
        int deleteStatus = 0;
        Connection con = null;
        try {
            con = c.getConnect();
        } catch (Exception e) {
            log.info("Not connected in dao", e);
        }
        try {
            //Deleting based on array of inputs hence iterating the loop
            for (int i = 0; i < gid.length; i++) {
                PreparedStatement ps = con.prepareStatement(SQL_DELETE_GROUPS_BY_GID);
                ps.setInt(1, Integer.parseInt(gid[i]));
                deleteStatus = ps.executeUpdate();
            }
            log.info(deleteStatus);
        } catch (SQLException e) {
            // e.printStackTrace();
            log.info("deleteDao", e);
        }


        return deleteStatus;
    }
}
