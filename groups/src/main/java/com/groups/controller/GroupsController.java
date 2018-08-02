package com.groups.controller;

import com.google.gson.Gson;
import com.groups.model.Group;
import com.groups.service.GroupService;
import com.groups.service.GroupServiceImp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Groups Controller have create, delete, retrieve functionalities
 */
public class GroupsController extends HttpServlet {
    int insertStatus = 0;
    Logger log = Logger.getLogger(GroupsController.class);

    GroupService groupService = new GroupServiceImp();

    /**
     * doPost method for create a group based on the input fields
     *
     * @param req for taking the request from the web
     * @param res for giving the response
     * @return insertStatus for return the status of post method
     * @throws ServletException
     * @throws IOException
     * @see com.groups.service
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Getting the data from the request
        PrintWriter out = res.getWriter();
        String groupName = req.getParameter("groupName");
        String owner = req.getParameter("owner");
        String sendAs = req.getParameter("sendAs");
        String feedback = req.getParameter("feedback");
        //Binding data to the model object
        Group group = new Group();
        group.setGroupName(groupName);
        group.setOwner(owner);
        group.setSendAs(sendAs);
        group.setFeedback(feedback);

        try {
            // calling create group service
            insertStatus = groupService.CreateGroup(group);

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("insert catch", e);
        }

        log.info((insertStatus < 1) ? "Group Not Created" : "Group Successfully Created");

        out.print(insertStatus);

    }

    /**
     * doGet method for Retrieving the data based on GroupName
     *
     * @param req
     * @param resp
     * @return list
     * @throws ServletException
     * @throws IOException
     * @var list holds the retrieved data
     * @see com.groups.service
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Getting the data from the request
        String groupName = req.getParameter("GroupName");
        List<Group> list = new ArrayList<Group>();
        try {
            // calling retrieve group service
            list = groupService.RetrieveGroupDetails(groupName);
        } catch (SQLException e) {
            log.info("doget method exception", e);
        }
        log.info(list);
        // Gson is to convert the entire retrieved list to Gson string
        Gson gson = new Gson();
        String str = gson.toJson(list);
        log.info(str);
        PrintWriter out = resp.getWriter();
        out.print(str);

    }

    /**
     * doDelete method is to delete the groups based on GroupId
     *
     * @param req
     * @param res
     * @return deletestatus
     * @throws ServletException
     * @throws IOException
     * @see com.groups.service
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Getting the data from the request
        String gid1 = req.getParameter("gid");
        //Splitting the array of details of ID's based on ","
        String[] gid = gid1.split(",");
        int deletestatus = 0;
        try {
            //calling the delete group service
            deletestatus = groupService.deleteGroup(gid);
        } catch (SQLException e) {
          //  e.printStackTrace();
            log.info(e);
        }

        log.info((deletestatus < 1) ? "Group Not Deleted" : "Group Successfully Deleted");
        PrintWriter out = res.getWriter();
        out.print(deletestatus);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
    }

}
