package co.edu.utp.misiontic2022.c2.chb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.chb.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.chb.util.JDBCUtilities;


public class ComprasDeLiderDao {
    
    public List<ComprasDeLiderVo> masCompras() throws SQLException  {
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var query = "SELECT L.NOMBRE || ' ' || L.PRIMER_APELLIDO || ' ' || L.SEGUNDO_APELLIDO LIDER, SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) VALOR"
                + " FROM LIDER L"
                + " JOIN PROYECTO P ON (P.ID_LIDER = L.ID_LIDER)"
                + " JOIN MATERIALCONSTRUCCION MC ON (MC.ID_MATERIALCONSTRUCCION = C.ID_MATERIALCONSTRUCCION)" 
                + " JOIN COMPRA C ON (C.ID_PROYECTO = P.ID_PROYECTO)"
                + " GROUP BY LIDER"  
                + " ORDER BY VALOR DESC"
                + " LIMIT 10";
            stmt = conn.prepareStatement(query);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ComprasDeLiderVo();
                vo.setLider(rset.getString("LIDER"));
                vo.setValor(rset.getDouble("VALOR"));
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
