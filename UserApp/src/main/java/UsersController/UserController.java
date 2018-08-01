package UsersController;

import UsersModels.UserPojo;
import UsersService.UserService;
import UsersServiceImpl.UserServiceImpl;
import UsersUtils.MyException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Usercontroller is a servlets which accepts the request and sends the responce back to the user Ui
 * userController extends httpservlets
 */

/**
 * <h1>User Registration Application..!</h1>
 * The User Registration Application program implements an application that
 * simply register a new user and displays  to the standard output.
 * <p>
 * The application intended to perform basic operation of insertion deletion and retrival of user
 *
 * @author Mahesh Ravirala
 * @version 1.0
 * @since 2018-07-21
 */

@WebServlet("/userController")
public class UserController extends HttpServlet {


    static final Logger log = Logger.getLogger(UserController.class);

    //dopost to post the data to the database

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int insertStatus = 0;
        PrintWriter out = res.getWriter();

        //Creation instance of user pojo and setting input parameters to the object
        UserPojo userPojo = new UserPojo();

        //Reading the input parameters of json object
        String userName = req.getParameter("userName");
        String userEmailAddress = req.getParameter("email");
        String userRole = req.getParameter("role");
        String userPhoneNumber = req.getParameter("mobile");
        String password = req.getParameter("pass");


        //Creation date is now of local date
        LocalDate creationDate = LocalDate.now();

        //setting values to pojo object
        userPojo.setUserName(userName);
        userPojo.setUserEmailAddress(userEmailAddress);
        userPojo.setUserRole(userRole);
        userPojo.setUserPhoneNumber(userPhoneNumber);
        userPojo.setUserCreationDate(creationDate);
        userPojo.setPassword(password);
        log.info(userPojo);

        // creation of instance of UserserviceImpl
        UserService us = new UserServiceImpl();

        try {
            //calling service create method
            insertStatus = us.createUser(userPojo);
            out.print(insertStatus > 0 ? "success" : "failed");
        } catch (Exception e) {
            log.error(e + "Failed to creat User");
        }
    }

    //do get method to retrieve the data of user
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();
        String retriveType=null;
        //creating the instance of gson object
        Gson gson = new Gson();
        log.info("in do get method");
        //reading the type of retrieve
        try {
            retriveType = req.getParameter("retriveBy");
            log.info("type of retrieve:"+retriveType);
            if(retriveType==null || retriveType=="")
                throw new MyException("retriveBy can not be null");
        }catch (MyException ude){
            log.info("MyException "+ude);
        }

        log.info("inside controller " + retriveType);
        log.info(retriveType);

        //creating the list of users
        List<UserPojo> listOfUsers = null;

        if (retriveType.equalsIgnoreCase("userId")) {
            log.info("entered into userId type");

            //getting userid from the request
            String uid = req.getParameter("type");
            int userid = Integer.parseInt(uid);

            UserService us = null;
            log.info("userid=" + userid);
            try {

                us = new UserServiceImpl();

                //returns the list of users based on user id
                listOfUsers = us.getUserById(userid);

                log.info("after retrival" + listOfUsers);

            } catch (Exception e) {

                log.error(e + "Failed to get the user details,Please Try agin");
            }

        } else if (retriveType.equalsIgnoreCase("role")) {


            // UserPojo userPojo=new UserPojo();


            //getting userid from the request
            String userRole = req.getParameter("type");
            log.info(userRole);

            UserService us = null;

            try {
                us = new UserServiceImpl();
                //returns the list of users based on the role of user
                listOfUsers = us.getUserByRole(userRole);
                log.info("successfully retrieved..!" + listOfUsers);

            } catch (Exception e) {

                log.error(e + "Failed to get the user details,Please Try agin");
            }


        } else {
            try {
                throw new Exception("Please Enter the valid type of retrive");
            } catch (Exception e) {
                log.error(e);
            }
        }
        //returning the json object to the browser
        out.print(gson.toJson(listOfUsers));

    }

}
