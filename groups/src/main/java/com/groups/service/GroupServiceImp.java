package com.groups.service;

import com.groups.dao.GroupDao;
import com.groups.dao.GroupDaoImp;
import com.groups.model.Group;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * GroupServiceImp implements Groupservice interface, it has CreateGroupService, RetrieveGroupDetails, deleteGroupService methods
 */
public class GroupServiceImp implements GroupService {
    Logger log = Logger.getLogger(GroupServiceImp.class);
    GroupDao groupDao = new GroupDaoImp();
    int status = 0;

    /**
     * CreateGroupService accepts the groupObject from the controller and passes it to the CreateDao and get the status.
     *
     * @param groupObj
     * @return
     * @throws SQLException
     */
    @Override
    public int CreateGroup(Group groupObj) throws SQLException {
        status = groupDao.CreateGroup(groupObj);
        log.info(status);
        return status;
    }

    /**
     * RetrieveGroupDetails accepts the groupName from the controller and passes it to the RetrieveGroupDetails DAO method and get the list
     *
     * @param groupName
     * @return
     * @throws SQLException
     */
    @Override
    public List<Group> RetrieveGroupDetails(String groupName) throws SQLException {
        List<Group> list = null;
        try {
            list = groupDao.RetrieveGroupDetails(groupName);
            log.info(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("retrievegroupdetails", e);

        }
        return list;
    }

    /**
     * deleteGroupService accepts the array of gid and passes it to the deleteGroupDao and get the status and return it to controller.
     *
     * @param gid
     * @return
     */
    @Override
    public int deleteGroup(String[] gid) {
        status = groupDao.deleteGroup(gid);
        log.info(status);
        return status;
    }
}
