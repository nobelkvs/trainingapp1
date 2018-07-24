package com.lms.Controller;

import com.google.gson.Gson;
import com.lms.Model.LmsModel;
import com.lms.Service.LmsServiceImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ControllerServlet extends HttpServlet {

     /*This logging allows you to report and persist error and warning
   messages as well as info messages
   */

    static final Logger log = Logger.getLogger(ControllerServlet.class);

    //This dopost is used to create the leave for the user

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();
        log.info("servletcontroller");
        Integer s1 = Integer.valueOf(req.getParameter("EmpId"));
        String s2 = req.getParameter("FromDate");
        String s3 = req.getParameter("ToDate");
        String s4 = req.getParameter("LeaveType");
        String s5 = req.getParameter("Comments");
        Integer No_Of_Days = Integer.valueOf(req.getParameter("NoOfDays"));

        LmsModel lms = new LmsModel();
        lms.setEmpId(s1);
        lms.setFromDate(s2);
        lms.setToDate(s3);
        lms.setLeaveType(s4);
        lms.setComments(s5);
        lms.setNo_Of_Days(No_Of_Days);
        LmsServiceImpl lsi = new LmsServiceImpl();
        int status = lsi.createLmsService(lms);
         /*Service Class(i.e LmsServiceImpl) method  returns the result of creation.
         by passing model Object
        */
        if (status == 1)
            out.print("Success");
        else
            out.print("try again");
        out.close();
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
/**this doGet method is used to retrieve the employee leaves
 * Gson object generates JSON data from Java objects.
 */

        Gson gson = new Gson();

        int EmpId = Integer.parseInt(req.getParameter("EmpId"));
        log.info(EmpId);
        List<LmsModel> ls = new ArrayList<>();
        PrintWriter out = res.getWriter();
        LmsServiceImpl lm = new LmsServiceImpl();
        try {

            ls = lm.retrieveByEmpId(EmpId);
            log.info(ls);
            String json = gson.toJson(ls);
            out.println(json);
            log.info(json);
        } catch (Exception e) {
            log.error("ERROR" + e);
        }

    }

    /*this doDelete method is used to delete the employee leave by passing the empid of the employee.
     * */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int deleteStatus = 0;
        String EmpId = req.getParameter("EmpId");
        PrintWriter out = res.getWriter();
        log.info(EmpId);
        String[] array = EmpId.split(",");
        for (int i = 0; i < array.length; i++) {
            int EmpId1 = Integer.parseInt(array[i]);
            LmsServiceImpl tsi = new LmsServiceImpl();
            try {

                deleteStatus = tsi.deleteLmsService(EmpId1);

                if (deleteStatus >= 1) {

                    log.info("deleted:" + deleteStatus);
                    log.info(deleteStatus);

                } else {

                    log.info("Not deleted:" + deleteStatus);
                }

            } catch (Exception e) {
                out.print(e);
                log.error(e);
            }
        }

 /*protected void doPut(HttpServletRequest req,HttpServletResponse res)throws IOException{

            //for updating the parameters
            int updateStatus = 0;
            int EmpId1 = Integer.parseInt(req.getParameter("EmpId"));
            String FromDate = req.getParameter("FromDate");
            String ToDate = req.getParameter("ToDate");

            LmsModel lms = new LmsModel();
            lms.setEmpId(EmpId1);
            lms.setFromDate(FromDate);
            lms.setToDate(ToDate);
            // PrintWriter out = res.getWriter();

            //creating obj for LmsServiceImplementation

            LmsServiceImpl tsi = new LmsServiceImpl();

            try {
                updateStatus = tsi.updateLmsService(lms);

                if (updateStatus >= 1) {
                    log.info("updated:" + updateStatus);

                } else
                    log.info("Not updated:" + updateStatus);


            } catch (Exception e) {

                e.printStackTrace();
            }
        }
*/
        //protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}