<%@ page import="java.sql.*" %>
<%
String name=request.getParameter("val");
if(name==null||name.trim().equals("")){
out.print("<p>Please enter name!</p>");
}else{
try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
PreparedStatement ps=con.prepareStatement("select * from Account where name like '"+name+"%'");
ResultSet rs=ps.executeQuery();

if(!rs.isBeforeFirst()) {
 out.println("<p>No Record Found!</p>");
}else{
out.print("<table border='1' cellpadding='2' width='100%'>");
out.print("<tr><th>Firstname</th><th>lastName</th><th>Bankname</th>
<th>Branch</th><th>Address</th><th>Phone</th></tr>");
while(rs.next()){
out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>
<td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getInt(6)+"</td>
</tr>");
}
out.print("</table>");
}//end of else for rs.isBeforeFirst
con.close();
}catch(Exception e){out.print(e);}
}//end of else
%>
// var request=new XMLHttpRequest();  
// function searchInfo(){  
// var name=document.vinform.name.value;  
// var url="index.jsp?val="+name;  
  
// try{  
// request.onreadystatechange=function(){  
// if(request.readyState==4){  
// var val=request.responseText;  
// document.getElementById('mylocation').innerHTML=val;  
// }  
// }//end of function  
// request.open("GET",url,true);  
// request.send();  
// }catch(e){alert("Unable to connect to server");}  
// }  

//     $(document).ready(function(){
//           $("#myInput").on("keyup", function() {
//             var value = $(this).val().toLowerCase();
//             $("#mytable tr").filter(function() {
//               $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
//             });
//           });
//         });
