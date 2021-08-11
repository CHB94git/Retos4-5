package co.edu.utp.misiontic2022.c2.chb.model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.chb.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.chb.util.JDBCUtilities;

public class ProyectoBancoDao {
    
    public List<ProyectoBancoVo> FinanciadosConavi(String banco) throws SQLException {
        List<ProyectoBancoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var query = "SELECT P.ID_PROYECTO ID, P.CONSTRUCTORA, P.CIUDAD, P.CLASIFICACION, T.ESTRATO, L.NOMBRE || ' ' || L.PRIMER_APELLIDO || ' ' || L.SEGUNDO_APELLIDO LIDER"
                + " FROM PROYECTO P"
                + " JOIN TIPO T ON (T.ID_TIPO = P.ID_TIPO)"
                + " JOIN LIDER L ON (L.ID_LIDER = P.ID_LIDER)" 
                + " WHERE P.BANCO_VINCULADO IN (?)" 
                + " ORDER BY P.FECHA_INICIO DESC, P.CIUDAD , P.CONSTRUCTORA ASC";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, banco);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ProyectoBancoVo();
                vo.setId(rset.getInt("ID"));
                vo.setConstructora(rset.getString("CONSTRUCTORA"));
                vo.setCiudad(rset.getString("CIUDAD"));
                vo.setClasificacion(rset.getString("CLASIFICACION"));
                vo.setEstrato(rset.getInt("ESTRATO"));
                vo.setLider(rset.getString("LIDER"));
                
                respuesta.add(vo);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;
    }
}
