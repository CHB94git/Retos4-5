package co.edu.utp.misiontic2022.c2.chb.view;

import co.edu.utp.misiontic2022.c2.chb.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ProyectoBancoVo;

public class ReportesView {

    private ReportesController controller;

    public ReportesView() {
        controller = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        var respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco) {
        try {
            System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO " + repitaCaracter('=', 37));
            if (banco != null && !banco.isBlank()) {
                System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", "ID", "CONSTRUCTORA", "CIUDAD",
                        "CLASIFICACION", "ESTRATO", "LIDER"));
                System.out.println(repitaCaracter('-', 105));

                var listaConavi = controller.listarProyectosFinanciadoConavi(banco);
                for (ProyectoBancoVo proyectoBancoVo : listaConavi) {
                    System.out.println(String.format("%3d %-25s %-20s %-15s %7d %-30s", proyectoBancoVo.getId(),
                            proyectoBancoVo.getConstructora(), proyectoBancoVo.getCiudad(),
                            proyectoBancoVo.getClasificacion(), proyectoBancoVo.getEstrato(),
                            proyectoBancoVo.getLider()));
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {

        try {
            System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO " + repitaCaracter('=', 1));
            if (limiteInferior != null) {
                System.out.println(String.format("%3s %15s", "ID", "VALOR  "));
                System.out.println(repitaCaracter('-', 29));

                var lista = controller.listarDeudasPorProyectos(limiteInferior);
                for (DeudasPorProyectoVo deudasPorProyectoVo : lista) {
                    System.out.println(
                            String.format("%3d %,15.1f", deudasPorProyectoVo.getId(), deudasPorProyectoVo.getValor()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void lideresQueMasGastan() {

        try {
            System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES " + repitaCaracter('=', 7));
            System.out.println(String.format("%-25s %15s", "LIDER", "VALOR  "));
            System.out.println(repitaCaracter('-', 41));

            var lista = controller.listarLideresMasCompradores();
            for (ComprasDeLiderVo comprasDeLiderVo : lista) {
                System.out.println(
                        String.format("%-25s %,15.1f", comprasDeLiderVo.getLider(), comprasDeLiderVo.getValor()));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }
}