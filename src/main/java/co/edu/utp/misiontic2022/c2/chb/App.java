package co.edu.utp.misiontic2022.c2.chb;

import co.edu.utp.misiontic2022.c2.chb.view.GUI;
//import co.edu.utp.misiontic2022.c2.chb.view.ReportesView;

public class App 
{
    public static void main( String[] args )
    {
        //var reportesView = new ReportesView();
        //var banco = "Conavi";
        //reportesView.proyectosFinanciadosPorBanco(banco);

        //var reportesView = new ReportesView();
        //var limiteInferior = 50_000d;
        //reportesView.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);

        //var reportesView = new ReportesView();
        //reportesView. lideresQueMasGastan();

        var formularioConsultas = new GUI();
        formularioConsultas.setVisible(true);
    }
}
