import java.sql.*;

public class AccessDB
{
	private Connection con;
	private Statement stm;
	private String drv = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String url;

	/**
	 * JDBCAccess Constructor
	 * @param  file [Filename of the access database file to be used, must in the same directory]
	 */
	public AccessDB(String file) throws Exception
	{
		this.url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ="+System.getProperty("user.dir")+"\\"+file+".accdb";
	}

/**
 * Opens a connection to the access database file
 */
	public void openCon() throws Exception
	{
		Class.forName(drv);
		this.con = DriverManager.getConnection(url,"","");
		this.stm = con.createStatement();
	}
 /**
  * Closes the connection to the access file
  */
	public void closeCon() throws SQLException
	{
		con.close();
	}

	/**
	 * Formats the array of parameters to an acceptable string format for access
	 * @param  param [The array to be formatted]
	 * @return       [Returns the acceptable format]
	 */
	public String preformat(String[] params)
	{
		String param = "";
		for(int i = 0; i<params.length; i++)
		{
			param += params[i];
			if(!(i+1 == params.length))
			{
				param += ", ";
			}
		}
		return param;
	}
	/**
	 * [_preformat description]
	 * @param  param [description]
	 * @return       [description]
	 */
	public String _preformat(String[] params)
	{
		String param = "";
		for(int i = 0; i<params.length; i++)
		{
			param += "'"+params[i]+"'";
			if(!(i+1 == params.length))
			{
				param += ", ";
			}
		}
		return param;
	}
	/**
	 * Select function that minimizes and returns a ResultSet
	 * @param   [description]
	 * @param   [description]
	 * @return  [description]
	 */
	public ResultSet select(String[] param, String table) throws SQLException
	{
		return stm.executeQuery("SELECT "+preformat(param)+" FROM "+table);
	}


	/**
	 * [select description]
	 * @param   [description]
	 * @param   [description]
	 * @param   [description]
	 * @return  [description]
	 */
	public ResultSet select(String param, String table, String where) throws SQLException
	{
		return stm.executeQuery("SELECT "+_preformat(param)+" FROM "+table+" WHERE "+where);
	}

	/**
	 * [insert description]
	 * @param  [description]
	 * @param  [description]
	 * @param  [description]
	 */
	public void insert(String table, Stringp[] vars, String[] params) throws SQLException
	{
		String param = preformat
		stm.executeUpdate("INSERT INTO " + table +"("+_preformat(vars)+") VALUES ("+params+")");
	}

	/**
	 * [delete description]
	 * @param  [description]
	 * @param  [description]
	 */
	public void delete(String[] table, String where) throws SQLException
	{
		stm.executeUpdate("DELETE FROM "+table+" WHERE "+where);
	}
}
