import java.util.*;
import java.sql.*;
public class AdaptersData {
	public Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/adapter", "root", "1");
	}
	public void addAdapter(Adapters adapter) throws Exception {
		Connection con = getConnection();
		PreparedStatement pr = con.prepareStatement("INSERT into adapters "+ "(id_adapter, title, description, count) " + "values(?, ?, ?, ?)");
		pr.setInt(1, adapter.getIdAdapter());
		pr.setString(2, adapter.getTitle());
		pr.setString(3, adapter.getDescription());
		pr.setInt(4, adapter.getCount());
		pr.executeUpdate();
		con.close();
	}
	public List<Adapters> getAdaptersByName(String name) throws Exception{
		List<Adapters> adapters = new ArrayList<Adapters>();
		Connection con = getConnection();
		PreparedStatement pr = con.prepareStatement("Select id_adapter, title, description, count "
				+ "from adapters " + "where title=?");
		pr.setString(1, name);
		ResultSet rs = pr.executeQuery();
		Adapters adaptersData = null;
		while (rs.next()) {
			adaptersData = new Adapters(rs.getInt(1), name, rs.getString(3), rs.getInt(4));
			adapters.add(adaptersData);
		}
		rs.close();
		con.close();
		return adapters;
	}
	public List<String> getAdaptersName() throws Exception {
		List<String> adapterTitle = new ArrayList<String>();
		Connection con = getConnection();
		PreparedStatement pr = con.prepareStatement("Select title from adapters");
		ResultSet rs = pr.executeQuery();
		while (rs.next()) {
			adapterTitle.add(rs.getString(1));
		}
		rs.close();
		con.close();
		return adapterTitle;
	}
	public List<Adapters> getAdapters() throws Exception {
		List<Adapters> adapter = new ArrayList<Adapters>();
		Connection con = getConnection();
		PreparedStatement pr = con.prepareStatement("Select * from adapters");
		ResultSet rs = pr.executeQuery();
		Adapters adapterQuery = null;
		while(rs.next()) {
			adapterQuery = new Adapters(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			adapter.add(adapterQuery);
		}
		rs.close();
		con.close();
		return adapter;
	}
	public void changeAdapter(Adapters adapter) throws Exception {
		Connection con = getConnection();
		PreparedStatement pr = con.prepareStatement("Update adapters " + "set count=? " + "where title=?");
		pr.setInt(1, adapter.getCount());
		pr.setString(2, adapter.getTitle());
		pr.executeUpdate();
		con.close();
	}
	public void removeProduct(String name) throws Exception {
		// Получение соединения с БД
		Connection con = getConnection();
		// Подготовка SQL-запроса
		PreparedStatement st = con.prepareStatement(
		"Delete from adapters " +
		"Where title = ?");
		// Указание значений параметров запроса
		st.setString(1, name);
		// Выполнение запроса
		st.executeUpdate();
		con.close();
		}
	/*public static void main (String [] args) {
		AdaptersData data = new AdaptersData();
		Scanner in = new  Scanner(System.in);
		String name = in.nextLine();
		try {
			System.out.println(data.getAdaptersByName(name));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
