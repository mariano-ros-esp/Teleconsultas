package guis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import arreglos.ArregloCitas;
import arreglos.ArregloPacientes;
import clases.Cita;
import clases.Paciente;

public class DlgRegistroCitas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigoPaciente;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtCosto;
    private JComboBox<String> cboEspecialidad;
    private JTextArea txtBoleta;

    private ArregloCitas ac = new ArregloCitas();
    private ArregloPacientes ap = new ArregloPacientes();

    public DlgRegistroCitas() {
        setTitle("Registro de Citas y Boletas");
        setBounds(100, 100, 500, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCodigo = new JLabel("Cód. Paciente:");
        lblCodigo.setBounds(20, 20, 100, 15);
        contentPanel.add(lblCodigo);

        txtCodigoPaciente = new JTextField();
        txtCodigoPaciente.setBounds(130, 15, 100, 20);
        contentPanel.add(txtCodigoPaciente);

        JLabel lblFecha = new JLabel("Fecha (DD/MM/AA):");
        lblFecha.setBounds(20, 50, 110, 15);
        contentPanel.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(130, 45, 100, 20);
        contentPanel.add(txtFecha);

        JLabel lblHora = new JLabel("Hora (HH:MM):");
        lblHora.setBounds(20, 80, 100, 15);
        contentPanel.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(130, 75, 100, 20);
        contentPanel.add(txtHora);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(20, 110, 100, 15);
        contentPanel.add(lblEspecialidad);

        cboEspecialidad = new JComboBox<String>();
        cboEspecialidad.addItem("Medicina General");
        cboEspecialidad.addItem("Pediatría");
        cboEspecialidad.addItem("Cardiología");
        cboEspecialidad.setBounds(130, 105, 140, 22);
        contentPanel.add(cboEspecialidad);

        JLabel lblCosto = new JLabel("Costo (S/):");
        lblCosto.setBounds(20, 140, 100, 15);
        contentPanel.add(lblCosto);

        txtCosto = new JTextField();
        txtCosto.setBounds(130, 135, 100, 20);
        contentPanel.add(txtCosto);

        JButton btnRegistrar = new JButton("Registrar Cita");
        btnRegistrar.setBounds(310, 15, 140, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarCita();
            }
        });
        contentPanel.add(btnRegistrar);

        JButton btnBoleta = new JButton("Generar Boleta");
        btnBoleta.setBounds(310, 60, 140, 30);
        btnBoleta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarBoleta();
            }
        });
        contentPanel.add(btnBoleta);

        JButton btnCancelar = new JButton("Cancelar Cita");
        btnCancelar.setBounds(310, 105, 140, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarCita();
            }
        });
        contentPanel.add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 430, 210);
        contentPanel.add(scrollPane);

        txtBoleta = new JTextArea();
        txtBoleta.setEditable(false);
        scrollPane.setViewportView(txtBoleta);
    }

    private void registrarCita() {
        try {
            String strCod = txtCodigoPaciente.getText().trim();
            String fec = txtFecha.getText().trim();
            String hor = txtHora.getText().trim();
            String strCosto = txtCosto.getText().trim();

            if (strCod.isEmpty() || fec.isEmpty() || hor.isEmpty() || strCosto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete los campos obligatorios");
                return;
            }

            int codPac = Integer.parseInt(strCod);
            double costo = Double.parseDouble(strCosto);
            String esp = cboEspecialidad.getSelectedItem().toString();
            
            Paciente p = ap.buscar(codPac);
            
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado");
                return;
            }

            for (int i = 0; i < ac.tamaño(); i++) {
                Cita c = ac.obtener(i);
                if (c.getEspecialidad().equals(esp) && 
                    c.getFecha().equals(fec) && 
                    c.getHora().equals(hor) && 
                    c.getEstado().equals("PROGRAMADA")) {
                    JOptionPane.showMessageDialog(this, "El médico no está disponible");
                    return;
                }
            }

            int numCita = ac.codigoCorrelativo();

            Cita nuevaCita = new Cita(numCita, codPac, fec, hor, esp, costo, "PROGRAMADA");
            ac.adicionar(nuevaCita);
            
            p.setEstado(1);
            ap.grabarDatos();

            JOptionPane.showMessageDialog(this, "Cita programada correctamente. Número asignado: " + numCita);
            
            txtCodigoPaciente.setText("");
            txtFecha.setText("");
            txtHora.setText("");
            txtCosto.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error. Ingrese valores válidos (números donde corresponde).");
        }
    }

    private void cancelarCita() {
        try {
            String input = JOptionPane.showInputDialog(this, "Ingrese el Número de Cita a cancelar:");
            if (input == null) return;

            int numCita = Integer.parseInt(input.trim());
            boolean encontrado = false;

            for (int i = 0; i < ac.tamaño(); i++) {
                Cita c = ac.obtener(i);
                if (c.getNumeroCita() == numCita) {
                    if (c.getEstado().equals("CANCELADA")) {
                        JOptionPane.showMessageDialog(this, "La cita ya está cancelada");
                        return;
                    }
                    c.setEstado("CANCELADA");
                    encontrado = true;
                    ac.grabarDatos();
                    JOptionPane.showMessageDialog(this, "Cita cancelada correctamente");
                    return;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Cita no encontrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de cita válido.");
        }
    }

    private void generarBoleta() {
        try {
            String input = JOptionPane.showInputDialog(this, "Ingrese el Número de Cita para emitir boleta:");
            if(input == null) return;
            
            int numCita = Integer.parseInt(input.trim());
            Cita citaEncontrada = ac.buscar(numCita);

            if (citaEncontrada != null) {
                String nombreCompleto = "Paciente Desconocido";
                Paciente p = ap.buscar(citaEncontrada.getCodigoPaciente());
                if (p != null) {
                    nombreCompleto = p.getNombres() + " " + p.getApellidos();
                }

                txtBoleta.setText("");
                txtBoleta.append("===========================================\n");
                txtBoleta.append("           BOLETA DE TELECONSULTA\n");
                txtBoleta.append("===========================================\n");
                txtBoleta.append("Nro Cita      : " + citaEncontrada.getNumeroCita() + "\n");
                txtBoleta.append("Paciente      : " + nombreCompleto + "\n");
                txtBoleta.append("Fecha         : " + citaEncontrada.getFecha() + "\n");
                txtBoleta.append("Hora          : " + citaEncontrada.getHora() + "\n");
                txtBoleta.append("Especialidad  : " + citaEncontrada.getEspecialidad() + "\n");
                txtBoleta.append("Estado        : " + citaEncontrada.getEstado() + "\n");
                txtBoleta.append("-------------------------------------------\n");
                txtBoleta.append("TOTAL A PAGAR : S/ " + citaEncontrada.getCosto() + "\n");
                txtBoleta.append("===========================================\n");
            } else {
                JOptionPane.showMessageDialog(this, "La cita ingresada no existe.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar la boleta.");
        }
    }
}