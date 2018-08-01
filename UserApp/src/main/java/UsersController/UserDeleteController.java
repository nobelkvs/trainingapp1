package UsersController;

import UsersService.UserService;
import UsersServiceImpl.UserServiceImpl;
import UsersUtils.MyException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteController extends HttpServlet {

    //doDelete for deleting the user by user id
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final Logger log = Logger.getLogger(UserController.class);
        PrintWriter out = res.getWriter();


        int uid = Integer.parseInt(req.getParameter("userId"));
        String adminPassword = req.getParameter("admin_Password");
        log.info("userid in conroller" + uid);
        UserService us = null;

        //validation password
        if (adminPassword == null | adminPassword == "") {

            try {
                //This is a new Userdefined exception
                throw new MyException("Admin Password Should not be empty or null");
            } catch (MyException ude) {
                log.info("Please Enter the admin Password..!"+ude);
            }
        } else if (adminPassword.equalsIgnoreCase("agile123")) {

            try {

                us = new UserServiceImpl();
                int deleteStatus = us.removeUser(uid);

                out.print(deleteStatus > 0 ? "success" : "failed");

            } catch (Exception e) {

                log.error(e + "Failed to Delete ,Please Try agin");
            }


        } else {
            log.info("Please Enter the Correct Password..!");
            out.print("wrongPassword");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

}
