package com.groups.service;

import com.groups.model.Group;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface GroupService have CreateService, RetrieveService, deleteService abstract methods
 */
public interface GroupService {

    int CreateGroup(Group groupObj) throws SQLException;

    List<Group> RetrieveGroupDetails(String groupName) throws SQLException;

    int deleteGroup(String[] gid) throws SQLException;
}
