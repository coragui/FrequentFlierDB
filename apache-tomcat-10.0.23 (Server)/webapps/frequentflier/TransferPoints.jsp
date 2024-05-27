<%@page import="java.sql.*" %>

<%
    int spid = Integer.parseInt(request.getParameter("spid"));
    int dpid = Integer.parseInt(request.getParameter("dpid"));
    int npoints = Integer.parseInt(request.getParameter("npoints"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection con = DriverManager.getConnection(url,"Coragui","iroochoo");
    Statement stmt = con.createStatement();
    
    stmt.executeQuery("update point_accounts set total_points = total_points - "+npoints+" where passid = "+spid);
    stmt.executeQuery("update point_accounts set total_points = total_points + "+npoints+" where passid = "+dpid);

    String output = "Success";
    

    con.close();
    out.print(output);
%>
