package com.groups.dao;

import com.groups.model.Group;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface GroupDao have CreateDao, RetrieveDao, deleteDao abstract methods
 */
public interface GroupDao {

    int CreateGroup(Group group) throws SQLException;

    List<Group> RetrieveGroupDetails(String groupName) throws SQLException, IOException;

    int deleteGroup(String[] gid);
}
