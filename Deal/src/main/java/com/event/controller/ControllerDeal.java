package com.event.controller;

import com.event.model.ModelDeal;
import com.event.service.ServiceDeal;
import com.event.serviceimpl.ServiceDealImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/**
 * This controller class is used to forward the client request to database and get response from database
 * using doXXX() methods
 */
public class ControllerDeal extends HttpServlet{

 static final Logger log=Logger.getLogger(ControllerDeal.class);
    ServiceDeal serviceDeal=new ServiceDealImpl();
    ModelDeal deal=new ModelDeal();

    /**
     * This method is used to Insert the deal into database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see ServiceDeal
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("entered into doPost");

        /* Getting values from HTMl form */
        String dealName=req.getParameter("deal_name");
        log.info("dealName: "+dealName);

        String ownerName=req.getParameter("owner_name");
        log.info("ownerName: "+ownerName);

        String dealValue=req.getParameter("deal_value");
        log.info("dealValue: "+dealValue);

        Integer probability=Integer.parseInt(req.getParameter("probability"));
        log.info("probability: "+probability);

        String customerName=req.getParameter("customer_name");
        log.info("customerName: "+customerName);

        String customerContact=req.getParameter("customer_contact");
        log.info("customerContact: "+customerContact);

        /* Setting values and binding to modelDeal object */
        deal.setDealName(dealName);
        deal.setOwnerName(ownerName);
        deal.setDealValue(dealValue);
        deal.setProbability(probability);
        deal.setCustomerName(customerName);
        deal.setCustomerContact(customerContact);

        /* Calling createDeal method of ServiceDeal*/
        String mesg=serviceDeal.createDeal(deal);
        log.info(mesg);


    }


    /**
     * This method is used to Retrive the deal information from database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see ServiceDeal
     */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("entered into doGet");

       /* Getting values from HTMl form */
        String dealName=req.getParameter("deal_name");

        /* Calling retriveDeal method of ServiceDeal*/
        List list =serviceDeal.retriveDeal(dealName);
        log.info(list);

        /* to convert list to json */
       Gson gson = new Gson();
       String json = gson.toJson(list);
       log.info(json);
       PrintWriter out=resp.getWriter();
       out.println(json);
   }


    /**
     * This method is used to Update the deal in database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see ServiceDeal
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("entered into doPut");
        JSONObject dataJson;

        /* To read the json data getting from UI */
        dataJson = getJsonData(new BufferedReader(new InputStreamReader(req.getInputStream())).readLine());
        log.info(dataJson);

        /* Setting and Appending the data to ModelDeal object */
        try {
            int dealId = dataJson.getInt("deal_id");
            log.info(dealId);
            String dealValue=dataJson.getString("deal_value");
            log.info(dealValue);
            int probability=dataJson.getInt("probability");
            log.info(probability);
            deal.setDealId(dealId);
            deal.setDealValue(dealValue);
            deal.setProbability(probability);
            /* Calling updateDeal method of ServiceDeal */
            int i = serviceDeal.updateDeal(deal);
            log.info(i);
            PrintWriter out=resp.getWriter();
            out.println(i);
        } catch (JSONException e) {
            log.error("error caught in json update: "+e);
        }

    }

    /* Getting data from UI */
    private JSONObject getJsonData(String data){
        JSONObject json = null;
        try {
            json = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * This method is used to Delete the deal from database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @see ServiceDeal
     */
    @Override
        public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("entered into doDelete");

        /* Getting the values from UI and parsing string to int type */
        String id=req.getParameter("deal_id");
        log.info("deal id is: "+id);

        /* Accepting multiple delete values with comma seperated */
        String[] arr=id.split(",");
        for(int i=0;i<arr.length;i++){
            int id1=Integer.parseInt(arr[i]);
            /* Calling deleteDeal method of ServiceDeal*/
            int mesg=serviceDeal.deleteDeal(id1);
            PrintWriter out=resp.getWriter();
            out.println(mesg);
            log.info(mesg);
        }

    }


    }


