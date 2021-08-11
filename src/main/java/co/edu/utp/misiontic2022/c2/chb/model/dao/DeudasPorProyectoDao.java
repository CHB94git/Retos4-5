package co.edu.utp.misiontic2022.c2.chb.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.chb.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.chb.util.JDBCUtilities;

public class DeudasPorProyectoDao {
    
    public List<DeudasPorProyectoVo> DeudasPorProyecto(Double limiteInferior) throws SQLException {
        List<DeudasPorProyectoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var query = "SELECT P.ID_PROYECTO ID, SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) VALOR"
                + " FROM PROYECTO P"
                + " JOIN COMPRA C ON (C.ID_PROYECTO = P.ID_PROYECTO)"
                + " JOIN MATERIALCONSTRUCCION MC ON (MC.ID_MATERIALCONSTRUCCION = C.ID_MATERIALCONSTRUCCION)" 
                + " WHERE C.PAGADO = 'No'"
                + " GROUP BY P.ID_PROYECTO"
                + " HAVING VALOR > (?)" 
                + " ORDER BY VALOR DESC";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, limiteInferior);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new DeudasPorProyectoVo();
                vo.setId(rset.getInt("ID"));
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
