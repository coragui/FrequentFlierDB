
<%@page import="java.sql.*" %>

<%
    int pid = Integer.parseInt(request.getParameter("pid"));
    int awardid = Integer.parseInt(request.getParameter("awardid"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection con = DriverManager.getConnection(url,"Coragui","iroochoo");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select a.a_description, a.points_required ,h.redemption_date, h.center_id from awards a, redemption_history h where a.award_id = h.award_id AND h.passid ="+pid+"AND h.award_id = "+ awardid);
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

