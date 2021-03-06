package Dao;


	

	import java.sql.CallableStatement;
import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Types;

import Model.Jogos;


	
	
	public class GerarTabelasDao implements IAtividadeDao {

		private Connection c;
		
		public GerarTabelasDao() {
			GenericDao gDao = new GenericDao();
			c = gDao.getConnection();
		}
		
		public Jogos consultaJogos() throws SQLException {
			
			Jogos jg = new Jogos();
			String sqlProc="{call sp_jogos}";
			CallableStatement cs=c.prepareCall(sqlProc);
		       cs.execute();
			StringBuffer sql = new StringBuffer(); 
			sql.append("SELECT jg.codigotimea, jg.codigotimeb, jg.golstimea, jg.golstimeb,jg.data ");
					sql.append("FROM jogos jg ");
			
			sql.append("WHERE jg.data = ?");
			PreparedStatement ps = c.prepareStatement(sql.toString());
			ps.setDate(1, jg.getData());
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				jg.setCodigotimea(rs.getInt("codigotimea"));
				jg.setCodigotimeb(rs.getInt("codigotimeb"));
				jg.setGolstimea(rs.getInt("golstimea"));
				jg.setGolstimeb(rs.getInt("golstimeb"));
				jg.setData(rs.getDate("Data"));
				
				
			}
			rs.close();
			ps.close();
			return jg;
		}
      
		
}