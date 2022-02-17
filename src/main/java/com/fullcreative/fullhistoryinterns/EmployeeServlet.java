package com.fullcreative.fullhistoryinterns;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");
    response.getOutputStream().println("<h1>Employee</h1>");
  

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("Employee");
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
      String name = (String) entity.getProperty("name");
      long age = (long)entity.getProperty("age");
      
      response.getOutputStream().println(" Name "+name);
      response.getOutputStream().println("<br>");
      response.getOutputStream().println(" age "+age);
      response.getOutputStream().println("<br>");
      
      System.out.println(name);
      System.out.println(age);
      
      
      
    }

    response.getOutputStream().println("<p><a href=\"/\">Back</a></p>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity employeeEntity = new Entity("Employee");
    employeeEntity.setProperty("name", name);
    employeeEntity.setProperty("age", age);
    
    datastore.put(employeeEntity);

    // Redirect to /message. The request will be routed to the doGet() function above.
    response.sendRedirect("/employee");
  }
}