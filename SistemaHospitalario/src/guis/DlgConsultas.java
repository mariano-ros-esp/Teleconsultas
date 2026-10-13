package guis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// Importamos las clases y arreglos
import arreglos.ArregloCitas;
import arreglos.ArregloPacientes;
import clases.Cita;
import clases.Paciente;

public class DlgConsultas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigoPaciente;
    private JTextArea txtResultados;

    private ArregloPacientes ap = new ArregloPacientes();
    private ArregloCitas ac = new ArregloCitas();

    public DlgConsultas() {
        setTitle("Consulta de Pacientes y Citas");
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodigo = new JLabel("Ingrese Cód. Paciente:");
        lblCodigo.setBounds(20, 20, 150, 15);
        contentPanel.add(lblCodigo);

        txtCodigoPaciente = new JTextField();
        txtCodigoPaciente.setBounds(170, 15, 100, 20);
        contentPanel.add(txtCodigoPaciente);

        JButton btnConsultar = new JButton("Buscar");
        btnConsultar.setBounds(290, 10, 100, 30);
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarConsulta();
            }
        });
        contentPanel.add(btnConsultar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 390, 280);
        contentPanel.add(scrollPane);

        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        scrollPane.setViewportView(txtResultados);
    }

    private void realizarConsulta() {
        try {
            int codPac = Integer.parseInt(txtCodigoPaciente.getText());
            Paciente pEncontrado = null;
            
            // Buscar paciente en el arreglo
            for (int i = 0; i < ap.tamaño(); i++) {
                if (ap.obtener(i).getCodigo() == codPac) {
                    pEncontrado = ap.obtener(i);
                    break;
                }
            }

            if (pEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
                txtResultados.setText("");
                return;
            }

            // Mostrar datos del paciente
            txtResultados.setText("=== DATOS DEL PACIENTE ===\n");
            txtResultados.append("Código    : " + pEncontrado.getCodigo() + "\n");
            txtResultados.append("Nombres   : " + pEncontrado.getNombres() + " " + pEncontrado.getApellidos() + "\n");
            txtResultados.append("DNI       : " + pEncontrado.getDni() + "\n");
            txtResultados.append("Edad      : " + pEncontrado.getEdad() + "\n");
            txtResultados.append("Estado    : " + (pEncontrado.getEstado() == 0 ? "Registrado" : "Atendido/Internado") + "\n\n");

            // Buscar historial de citas de ese paciente
            txtResultados.append("=== HISTORIAL DE CITAS ===\n");
            boolean tieneCitas = false;
            for (int i = 0; i < ac.tamaño(); i++) {
                Cita c = ac.obtener(i);
                if (c.getCodigoPaciente() == codPac) {
                    tieneCitas = true;
                    txtResultados.append("- Nro Cita: " + c.getNumeroCita() + 
                                         " | Fecha: " + c.getFecha() + 
                                         " | Especialidad: " + c.getEspecialidad() + "\n");
                }
            }

            if (!tieneCitas) {
                txtResultados.append("El paciente no tiene citas registradas.\n");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un código numérico válido.");
        }
    }
}