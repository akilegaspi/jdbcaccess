import java.sql.*;
import java.io.File;

public class AccessDB
{
	private Connection con;
	private Statement stm;
	private String drv = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String url;
	public AccessDB(String file) throws Exception
	{
		this.url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ="+System.getProperty("user.dir")+"\\"+file+".accdb";
	}

	public void openCon() throws Exception
	{
		Class.forName(drv);
		this.con = DriverManager.getConnection(url,"","");
		this.stm = con.createStatement();
	}

	public void closeCon() throws SQLException
	{
		con.close();
	}

	public ResultSet select(String param, String table) throws SQLException
	{
		return stm.executeQuery("SELECT "+param+" FROM "+table);
	}

	public ResultSet select(String param, String table, String where) throws SQLException
	{
		return stm.executeQuery("SELECT "+param+" FROM "+table+" WHERE "+where);
	}

	public void insert(String table, String vars, String params) throws SQLException
	{
		stm.executeUpdate("INSERT INTO " + table +"("+vars+") VALUES ("+params+")");
	}

	public void delete(String table, String where) throws SQLException
	{
		stm.executeUpdate("DELETE FROM "+table+" WHERE "+where);
	}
}