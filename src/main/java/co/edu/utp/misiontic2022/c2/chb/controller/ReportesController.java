package co.edu.utp.misiontic2022.c2.chb.controller;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.chb.model.dao.ComprasDeLiderDao;
import co.edu.utp.misiontic2022.c2.chb.model.dao.DeudasPorProyectoDao;
import co.edu.utp.misiontic2022.c2.chb.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ProyectoBancoVo;

public class ReportesController {
 
    public ReportesController(){
        bancoDao = new ProyectoBancoDao();
        deudasProyectosDao = new DeudasPorProyectoDao();
        masCompradoresDao = new ComprasDeLiderDao();
    }

    private ProyectoBancoDao bancoDao;

    public List<ProyectoBancoVo> listarProyectosFinanciadoConavi(String banco) throws SQLException{
        return bancoDao.FinanciadosConavi(banco);
    }

    private DeudasPorProyectoDao deudasProyectosDao;

    public List<DeudasPorProyectoVo> listarDeudasPorProyectos(Double limiteInferior) throws SQLException{
        return deudasProyectosDao.DeudasPorProyecto(limiteInferior);
    }

    private ComprasDeLiderDao masCompradoresDao;

    public List<ComprasDeLiderVo> listarLideresMasCompradores() throws SQLException{
        return masCompradoresDao.masCompras();
    }
}
