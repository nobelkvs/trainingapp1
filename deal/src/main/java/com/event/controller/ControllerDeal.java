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


public class ControllerDeal extends HttpServlet{
/*
*  log4j instance
 */
 static final Logger log=Logger.getLogger(ControllerDeal.class);
    ServiceDeal serviceDeal=new ServiceDealImpl();
    ModelDeal deal=new ModelDeal();


    /*
    *create deal method
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("entered into doPost");
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


        deal.setDealName(dealName);
        deal.setOwnerName(ownerName);
        deal.setDealValue(dealValue);
        deal.setProbability(probability);
        deal.setCustomerName(customerName);
        deal.setCustomerContact(customerContact);


        String mesg=serviceDeal.createDeal(deal);
        log.info(mesg);


    }
    /*
     *Retrive deal method
     */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("entered into doGet");
        //retriving by deal name

        String dealName=req.getParameter("deal_name");
        List list =serviceDeal.retriveDeal(dealName);
        log.info(list);
       Gson gson = new Gson();
       String json = gson.toJson(list);
       log.info(json);
       PrintWriter out=resp.getWriter();
       out.println(json);
   }
    /*
     *Update deal method
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("entered into doPut");
        JSONObject dataJson;

        dataJson = getJsonData(new BufferedReader(new InputStreamReader(req.getInputStream())).readLine());
        log.info(dataJson);

        /*
         *getting json data and appending to object
         */
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
            int i = serviceDeal.updateDeal(deal);
            log.info(i);
            PrintWriter out=resp.getWriter();
            out.println(i);
        } catch (JSONException e) {
            log.error("error caught in json update: "+e);
            e.printStackTrace();
        }

    }
    private JSONObject getJsonData(String data){
        JSONObject json = null;
        try {
            json = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    /*
     *Delete deal method
     */
    @Override
        public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("entered into doDelete");
        //parsing from string to int type
        String id=req.getParameter("deal_id");
        log.info("deal id is: "+id);
        //multiple values with comma seperated
        String[] arr=id.split(",");
        for(int i=0;i<arr.length;i++){
            int id1=Integer.parseInt(arr[i]);
            int mesg=serviceDeal.deleteDeal(id1);
            PrintWriter out=resp.getWriter();
            out.println(mesg);
            log.info(mesg);
        }

    }


    }


