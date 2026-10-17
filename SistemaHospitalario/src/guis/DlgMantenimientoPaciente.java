package guis;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import arreglos.ArregloPacientes;
import clases.Paciente;

public class DlgMantenimientoPaciente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigo;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDni;
    private JTextField txtEdad;
    private JTextField txtTelefono;
    private JTable tblPacientes;
    private DefaultTableModel modelo;
    
    private ArregloPacientes ap = new ArregloPacientes();

    public DlgMantenimientoPaciente() {
        setTitle("Mantenimiento de Pacientes");
        setBounds(100, 100, 650, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 70, 15);
        contentPanel.add(lblCodigo);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 15, 150, 20);
        contentPanel.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(20, 50, 70, 15);
        contentPanel.add(lblNombres);
        
        txtNombres = new JTextField();
        txtNombres.setBounds(100, 45, 150, 20);
        contentPanel.add(txtNombres);
        txtNombres.setColumns(10);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 80, 70, 15);
        contentPanel.add(lblApellidos);
        
        txtApellidos = new JTextField();
        txtApellidos.setBounds(100, 75, 150, 20);
        contentPanel.add(txtApellidos);
        txtApellidos.setColumns(10);
        
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(280, 20, 60, 15);
        contentPanel.add(lblDni);
        
        txtDni = new JTextField();
        txtDni.setBounds(340, 15, 100, 20);
        contentPanel.add(txtDni);
        txtDni.setColumns(10);
        
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(280, 50, 60, 15);
        contentPanel.add(lblEdad);
        
        txtEdad = new JTextField();
        txtEdad.setBounds(340, 45, 50, 20);
        contentPanel.add(txtEdad);
        txtEdad.setColumns(10);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(280, 80, 60, 15);
        contentPanel.add(lblTelefono);
        
        txtTelefono = new JTextField();
        txtTelefono.setBounds(340, 75, 100, 20);
        contentPanel.add(txtTelefono);
        txtTelefono.setColumns(10);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarPaciente();
            }
        });
        btnAdicionar.setBounds(480, 15, 120, 25);
        contentPanel.add(btnAdicionar);
        
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPaciente();
            }
        });
        btnConsultar.setBounds(480, 45, 120, 25);
        contentPanel.add(btnConsultar);
        
        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarPaciente();
            }
        });
        btnModificar.setBounds(480, 75, 120, 25);
        contentPanel.add(btnModificar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPaciente();
            }
        });
        btnEliminar.setBounds(480, 105, 120, 25);
        contentPanel.add(btnEliminar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 150, 580, 220);
        contentPanel.add(scrollPane);
        
        tblPacientes = new JTable();
        tblPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tblPacientes.getSelectedRow();
                if (fila != -1) {
                    txtCodigo.setText(tblPacientes.getValueAt(fila, 0).toString());
                    txtNombres.setText(tblPacientes.getValueAt(fila, 1).toString());
                    txtApellidos.setText(tblPacientes.getValueAt(fila, 2).toString());
                    txtDni.setText(tblPacientes.getValueAt(fila, 3).toString());
                    txtEdad.setText(tblPacientes.getValueAt(fila, 4).toString());
                    txtTelefono.setText(tblPacientes.getValueAt(fila, 5).toString());
                }
            }
        });
        tblPacientes.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblPacientes);
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("DNI");
        modelo.addColumn("Edad");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Estado");
        tblPacientes.setModel(modelo);
        
        txtCodigo.setText(String.valueOf(ap.codigoCorrelativo()));
        listarPacientes();
    }
    
    private void adicionarPaciente() {
        try {
            String strCod = txtCodigo.getText().trim();
            String nom = txtNombres.getText().trim();
            String ape = txtApellidos.getText().trim();
            String dni = txtDni.getText().trim();
            String strEdad = txtEdad.getText().trim();
            String tel = txtTelefono.getText().trim();

            if (strCod.isEmpty() || nom.isEmpty() || ape.isEmpty() || dni.isEmpty() || strEdad.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete los campos obligatorios");
                return;
            }

            int cod = Integer.parseInt(strCod);
            int edad = Integer.parseInt(strEdad);
            int est = 0;

            if (ap.buscar(cod) != null || ap.buscarPorDni(dni) != null) {
                JOptionPane.showMessageDialog(this, "El paciente ya está registrado");
                return;
            }
            
            Paciente p = new Paciente(cod, nom, ape, dni, edad, tel, est);
            ap.adicionar(p);
            
            JOptionPane.showMessageDialog(this, "Paciente registrado correctamente");
            limpiarCajas();
            listarPacientes();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos. Verifique que Código y Edad sean numéricos.");
        }
    }
    
    private void listarPacientes() {
        modelo.setRowCount(0);
        for (int i = 0; i < ap.tamaño(); i++) {
            Paciente p = ap.obtener(i);
            Object[] fila = { p.getCodigo(), p.getNombres(), p.getApellidos(), 
                              p.getDni(), p.getEdad(), p.getTelefono(), 
                              (p.getEstado() == 0 ? "Registrado" : "Atendido") };
            modelo.addRow(fila);
        }
    }
    
    private void limpiarCajas() {
        txtCodigo.setText(String.valueOf(ap.codigoCorrelativo()));
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        txtNombres.requestFocus();
    }

    private void consultarPaciente() {
        try {
            String input = JOptionPane.showInputDialog(this, "Ingrese el Código del Paciente a buscar:");
            if (input == null) return;

            int codigoBuscado = Integer.parseInt(input.trim());
            Paciente p = ap.buscar(codigoBuscado);
            
            if (p != null) {
                txtCodigo.setText(String.valueOf(p.getCodigo()));
                txtNombres.setText(p.getNombres());
                txtApellidos.setText(p.getApellidos());
                txtDni.setText(p.getDni());
                txtEdad.setText(String.valueOf(p.getEdad()));
                txtTelefono.setText(p.getTelefono());
                JOptionPane.showMessageDialog(this, "Paciente encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Paciente NO encontrado.");
                limpiarCajas();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido.");
        }
    }

    private void modificarPaciente() {
        try {
            String strCod = txtCodigo.getText().trim();
            String nom = txtNombres.getText().trim();
            String ape = txtApellidos.getText().trim();
            String dni = txtDni.getText().trim();
            String strEdad = txtEdad.getText().trim();
            String tel = txtTelefono.getText().trim();

            if (strCod.isEmpty() || nom.isEmpty() || ape.isEmpty() || dni.isEmpty() || strEdad.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete los campos obligatorios");
                return;
            }

            int cod = Integer.parseInt(strCod);
            Paciente p = ap.buscar(cod);
            
            if (p != null) {
                p.setNombres(nom);
                p.setApellidos(ape);
                p.setDni(dni);
                p.setEdad(Integer.parseInt(strEdad));
                p.setTelefono(tel);
                
                ap.grabarDatos();
                
                listarPacientes();
                limpiarCajas();
                JOptionPane.showMessageDialog(this, "Paciente modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Paciente NO encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar. Verifique los datos.");
        }
    }

    private void eliminarPaciente() {
        try {
            int cod = Integer.parseInt(txtCodigo.getText().trim());
            Paciente p = ap.buscar(cod);

            if (p == null) {
                int fila = tblPacientes.getSelectedRow();
                if (fila != -1) {
                    cod = Integer.parseInt(tblPacientes.getValueAt(fila, 0).toString());
                    p = ap.buscar(cod);
                }
            }

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Seleccione o ingrese un código de paciente válido para eliminar.");
                return;
            }
            
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                ap.eliminar(p);
                listarPacientes();
                limpiarCajas();
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente de la tabla para eliminar.");
        }
    }
}
