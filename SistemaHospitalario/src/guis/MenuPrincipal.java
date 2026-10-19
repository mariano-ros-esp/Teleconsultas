package guis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuPrincipal() {
        setTitle("Sistema de Teleconsultas - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("SISTEMA DE TELECONSULTAS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(10, 21, 414, 30);
        contentPane.add(lblTitulo);

        JButton btnMantenimiento = new JButton("1. Mantenimiento");
        btnMantenimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DlgMantenimientoPaciente dlg = new DlgMantenimientoPaciente();
            	dlg.setVisible(true);
            }
        });
        btnMantenimiento.setBounds(130, 75, 170, 35);
        contentPane.add(btnMantenimiento);

        JButton btnRegistro = new JButton("2. Registro de Citas");
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgRegistroCitas dlg = new DlgRegistroCitas();
                dlg.setVisible(true);
            }
        });
        btnRegistro.setBounds(130, 130, 170, 35);
        contentPane.add(btnRegistro);

        JButton btnConsulta = new JButton("3. Consultas");
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgConsultas dlg = new DlgConsultas();
                dlg.setVisible(true);
            }
        });
        btnConsulta.setBounds(130, 185, 170, 35);
        contentPane.add(btnConsulta);

        JButton btnReporte = new JButton("4. Reportes");
        btnReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DlgReportes dlg = new DlgReportes();
                dlg.setVisible(true);
            }
        });
        btnReporte.setBounds(130, 240, 170, 35);
        contentPane.add(btnReporte);
    }
}