
<%@page import="java.sql.*" %>

<%
    int pid = Integer.parseInt(request.getParameter("pid"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection con = DriverManager.getConnection(url,"Coragui","iroochoo");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select award_id from redemption_history where passid = "+pid);
    ResultSetMetaData rsmd = rs.getMetaData();
    int col = rsmd.getColumnCount();
    
    String output = "";
    
    while(rs.next()){
        for(int i = 1; i <= col;i++){
            output += rs.getObject(i);
            if(i != col){
                output += ",";
            }else{
                output += "#";
            }
        }
    }
    
    con.close();
    out.print(output);
%>
