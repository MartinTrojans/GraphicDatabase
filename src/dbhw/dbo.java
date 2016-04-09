package dbhw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbo {
	
	public int[] getLions(){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT t.X, t.Y FROM lion, TABLE(SDO_UTIL.GETVERTICES(lion.location)) t";
		
		List<Integer> res = new ArrayList<Integer>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				res.add(result.getInt("X"));
				res.add(result.getInt("Y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		int[] finalRes = new int[res.size()];
		
		for (int i=0; i < res.size(); i++){
			finalRes[i] = res.get(i);
		}
		
		return finalRes;
	}
	
	public int[] getRegions(){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT t.X, t.Y FROM region, TABLE(SDO_UTIL.GETVERTICES(region.rshape)) t";
		
		List<Integer> res = new ArrayList<Integer>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				res.add(result.getInt("X"));
				res.add(result.getInt("Y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] finalRes = new int[res.size()];
		
		for (int i=0; i < res.size(); i++){
			finalRes[i] = res.get(i);
		}
		
		return finalRes;
	}
	
	public int[] getPonds(){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT t.X, t.Y FROM pond, TABLE(SDO_UTIL.GETVERTICES(pond.pshape)) t";
		
		List<Integer> res = new ArrayList<Integer>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				res.add(result.getInt("X"));
				res.add(result.getInt("Y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] finalRes = new int[res.size()];
		
		for (int i=0; i < res.size(); i++){
			finalRes[i] = res.get(i);
		}
		
		return finalRes;
	}
	
	public String getSelectedRegion(int x, int y){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT rid FROM region WHERE SDO_CONTAINS(rshape, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL),NULL,NULL)) = \'TRUE\'";
		
		String res = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			pre.setInt(1, x);
			pre.setInt(2, y);
			result = pre.executeQuery();
			while(result.next()){
				res = result.getString("rid");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return res;
	}
	
	public int[] getSelectedLions(String rid){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT t.x, t.y FROM" + 
						"(SELECT location from lion, region WHERE SDO_CONTAINS(rshape, location)=\'TRUE\' AND rid=?) newl," + 
						"TABLE(SDO_UTIL.GETVERTICES(newl.location)) t";
		
		List<Integer> res = new ArrayList<Integer>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			pre.setString(1, rid);
			result = pre.executeQuery();
			while(result.next()){
				res.add(result.getInt("X"));
				res.add(result.getInt("Y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		int[] finalRes = new int[res.size()];
		
		for (int i=0; i < res.size(); i++){
			finalRes[i] = res.get(i);
		}
		
		return finalRes;
	}
	
	public int[] getSelectedPonds(String rid){
		String url = "jdbc:oracle:" + "thin:@localhost:1521:xe";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		String username = "system";
		String password = "123";
		String sql = "SELECT t.x, t.y FROM" + 
						"(SELECT pshape from pond, region WHERE SDO_CONTAINS(rshape, pshape)=\'TRUE\' AND rid=?) newp," + 
						"TABLE(SDO_UTIL.GETVERTICES(newp.pshape)) t";
		
		List<Integer> res = new ArrayList<Integer>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, username, password);
			pre = con.prepareStatement(sql);
			pre.setString(1, rid);
			result = pre.executeQuery();
			while(result.next()){
				res.add(result.getInt("X"));
				res.add(result.getInt("Y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (pre != null)
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		int[] finalRes = new int[res.size()];
		
		for (int i=0; i < res.size(); i++){
			finalRes[i] = res.get(i);
		}
		
		return finalRes;
	}
}
