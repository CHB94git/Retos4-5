package co.edu.utp.misiontic2022.c2.chb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.chb.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.chb.model.vo.ProyectoBancoVo;

public class GUI extends JFrame {

    private JTable tabla;
    private ReportesController controller;

    public GUI() {
        controller = new ReportesController();
        initUI();
        setSize(1000, 650);
        setLocationRelativeTo(null);
        
    }

    private void initUI() {
        setTitle("GUI Consultas Reto 5 By CristianHB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var panel = new JPanel();
        getContentPane().add(panel, BorderLayout.PAGE_END);
        panel.setBackground(Color.LIGHT_GRAY);
        

        var btnBanco = new JButton("Proyectos Financiados por Conavi");
        btnBanco.addActionListener(e -> cargarTablaProyectosFinanciadosConavi());
        panel.add(btnBanco);
        btnBanco.setBackground(Color.BLACK);
        btnBanco.setForeground(Color.WHITE);

        var btnLideres = new JButton("Líderes que más gastan");
        btnLideres.addActionListener(e -> cargarTablaLideresMasGastan());
        panel.add(btnLideres);
        btnLideres.setBackground(Color.BLACK);
        btnLideres.setForeground(Color.WHITE);


        var btnDeudasProyectos = new JButton("Deudas por proyectos");
        btnDeudasProyectos.addActionListener(e -> cargarTablaDeudasProyectos());
        panel.add(btnDeudasProyectos);
        btnDeudasProyectos.setBackground(Color.BLACK);
        btnDeudasProyectos.setForeground(Color.WHITE);


        tabla = new JTable();
        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
        tabla.setBackground(Color.LIGHT_GRAY);
    }

    private void cargarTablaProyectosFinanciadosConavi() {
        try {
            var tableModel = new BancoTableModel();

            var lista = controller.listarProyectosFinanciadoConavi("Conavi");

            tableModel.setData(lista);

            tabla.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class BancoTableModel extends AbstractTableModel {
        private List<ProyectoBancoVo> banco;

        public BancoTableModel() {
            banco = new ArrayList<>();
        }

        public void setData(List<ProyectoBancoVo> data) {
            banco = data;
            fireTableDataChanged();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                case 4:
                    return Integer.class;
                default:
                    return String.class;

            }
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID";
                case 1:
                    return "Constructora";
                case 2:
                    return "Ciudad";
                case 3:
                    return "Clasificación";
                case 4:
                    return "Estrato";
                case 5:
                    return "Líder";

            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return banco.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var bank = banco.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return bank.getId();
                case 1:
                    return bank.getConstructora();
                case 2:
                    return bank.getCiudad();
                case 3:
                    return bank.getClasificacion();
                case 4:
                    return bank.getEstrato();
                case 5:
                    return bank.getLider();
            }
            return null;
        }
    }

    private void cargarTablaLideresMasGastan() {
        try {
            var tableModel = new LiderTableModel();

            var lista = controller.listarLideresMasCompradores();

            tableModel.setData(lista);

            tabla.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class LiderTableModel extends AbstractTableModel {

        private List<ComprasDeLiderVo> lideres;

        public LiderTableModel() {
            lideres = new ArrayList<>();
        }

        public void setData(List<ComprasDeLiderVo> data) {
            lideres = data;
            fireTableDataChanged();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Double.class;
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Líder";
                case 1:
                    return "Valor";

            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return lideres.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var lider = lideres.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return lider.getLider();
                case 1:
                    return lider.getValor();

            }
            return null;
        }
    }


    private void cargarTablaDeudasProyectos() {
        try {
            var tableModel = new DeudasProyectosTableModel();

            var lista = controller.listarDeudasPorProyectos(50_000d);

            tableModel.setData(lista);

            tabla.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class DeudasProyectosTableModel extends AbstractTableModel {

        private List<DeudasPorProyectoVo> deudas;

        public DeudasProyectosTableModel() {
            deudas = new ArrayList<>();
        }

        public void setData(List<DeudasPorProyectoVo> data) {
            deudas = data;
            fireTableDataChanged();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Double.class;
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID Proyecto";
                case 1:
                    return "Valor";

            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return deudas.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var deudaProyecto = deudas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return deudaProyecto.getId();
                case 1:
                    return deudaProyecto.getValor();
            }
            return null;
        }
    }

}
