<%@page import="java.sql.*" %>

<%
    int flightid = Integer.parseInt(request.getParameter("flightid"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection con = DriverManager.getConnection(url,"Coragui","iroochoo");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select f.flight_id, f.dept_datetime, f.arrival_datetime, f.flight_miles, t.trip_id, t.trip_miles from flights f, flights_trips ft, trips t where f.flight_id = ft.flight_id AND ft.trip_id = t.trip_id AND ft.flight_id ="+flightid);
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

