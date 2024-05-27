<%@page import="java.sql.*" %>

<%
    int pid = Integer.parseInt(request.getParameter("pid"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection con = DriverManager.getConnection(url,"Coragui","iroochoo");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("Select * from Flights where passid ="+pid);
    String output = "";
    
    while(rs.next()){
        
        output += rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+"#";
    }
    
    con.close();
    out.print(output);
%>
