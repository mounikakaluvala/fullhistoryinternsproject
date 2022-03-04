package com.fullcreative.interns;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/SampleServlet")
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		Datastore datastore = DatastoreOptions.newBuilder().setProjectId("fullhistoryinternsproject").build().getService();
		
		//Custom Key entity save
		
		
		   KeyFactory keyFactory = datastore.newKeyFactory().setKind("Person"); 
		   Key key = keyFactory.newKey("mounika@gmail.com");
		   Entity entity =Entity.newBuilder(key)
		   .set("name", "mounika")
		   .set("age", 51) 
		  .set("favorite_food", "pizza").build(); 
		   datastore.put(entity);
		  System.out.println(datastore);
		 
	
		Query<Entity> query = Query.newEntityQueryBuilder()
			    .setKind("Person")
			    .setFilter(PropertyFilter.eq("favorite_food", "pizza"))
			    .build();
		
			QueryResults<Entity> results = datastore.run(query);
			while (results.hasNext()) {
			  Entity currentEntity = results.next();
			  response.getWriter().append("<br>");
			  response.getWriter().append(currentEntity.getString("name") + " you are  invited to a pizza party");
			  response.getWriter().append("<br>");
			}
		
			// here is the
//			Query<Entity> query =
//				    Query.newEntityQueryBuilder()
//				        .setKind("Person")
//				        .setFilter(
//				            CompositeFilter.and(
//				            		PropertyFilter.eq("favorite_food", "pizza"), PropertyFilter.eq("name", "mounika")))
//				        .build();
//			
//			QueryResults<Entity> results = datastore.run(query);
//			
//			while (results.hasNext()) {
//				  Entity currentEntity = results.next();
//				  response.getWriter().append(currentEntity.getString("name") + " you are  invited to a pizza party");
//				  response.getWriter().append("</br>");
//				}
			
			
		
//		  KeyFactory keyFactory = datastore.newKeyFactory().setKind("Person");
//		  Key key= keyFactory.newKey("mounika@gmail.com");
//		Entity entity = datastore.get(key); if
//		  (entity != null) { entity = Entity.newBuilder(entity).set("favorite_food",
//		  "dosa").build(); datastore.update(entity); }
//		  
//		  Entity entity2 = datastore.get(key);
//		  
//		  response.getWriter().append(entity2.getString("favorite_food") +" is favorite food of mounika now");
//		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
