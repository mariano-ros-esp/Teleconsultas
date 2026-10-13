package guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Importamos las clases que creamos antes
import arreglos.ArregloPacientes;
import clases.Paciente;

public class DlgMantenimientoPaciente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDni;
    private JTextField txtEdad;
    private JTable tblPacientes;
    private DefaultTableModel modelo;
    
    // Instanciamos el arreglo para manejar los datos
    private ArregloPacientes ap = new ArregloPacientes();

    // Constructor de la ventana
    public DlgMantenimientoPaciente() {
        setTitle("Mantenimiento de Pacientes");
        setBounds(100, 100, 650, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(20, 20, 70, 15);
        contentPanel.add(lblNombres);
        
        txtNombres = new JTextField();
        txtNombres.setBounds(100, 15, 150, 20);
        contentPanel.add(txtNombres);
        txtNombres.setColumns(10);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 50, 70, 15);
        contentPanel.add(lblApellidos);
        
        txtApellidos = new JTextField();
        txtApellidos.setBounds(100, 45, 150, 20);
        contentPanel.add(txtApellidos);
        txtApellidos.setColumns(10);
        
        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(280, 20, 40, 15);
        contentPanel.add(lblDni);
        
        txtDni = new JTextField();
        txtDni.setBounds(330, 15, 100, 20);
        contentPanel.add(txtDni);
        txtDni.setColumns(10);
        
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(280, 50, 40, 15);
        contentPanel.add(lblEdad);
        
        txtEdad = new JTextField();
        txtEdad.setBounds(330, 45, 50, 20);
        contentPanel.add(txtEdad);
        txtEdad.setColumns(10);
        
        // Botones de Acción
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí va la lógica para agregar un paciente
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
        
        // Configuración de la Tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 150, 580, 220);
        contentPanel.add(scrollPane);
        
        tblPacientes = new JTable();
        tblPacientes.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblPacientes);
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("DNI");
        modelo.addColumn("Edad");
        modelo.addColumn("Estado");
        tblPacientes.setModel(modelo);
        
        // Cargamos los datos en la tabla al abrir la ventana
        listarPacientes();
    }
    
    // Método para agregar un paciente al ArrayList y actualizar la tabla
    private void adicionarPaciente() {
        try {
            // Generamos el código automáticamente
            int cod = ap.codigoCorrelativo();
            String nom = txtNombres.getText();
            String ape = txtApellidos.getText();
            String dni = txtDni.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            int est = 0; // 0 = Registrado por defecto
            
            // Creamos el objeto paciente
            Paciente p = new Paciente(cod, nom, ape, dni, edad, est);
            
            // Lo añadimos al arreglo (esto también lo guarda en el .txt)
            ap.adicionar(p);
            
            // Limpiamos las cajas y actualizamos la tabla
            limpiarCajas();
            listarPacientes();
            
        } catch (Exception ex) {
            System.out.println("Error al ingresar los datos: " + ex.getMessage());
        }
    }
    
    // Método para mostrar los datos del ArrayList en la tabla visual
    private void listarPacientes() {
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar
        for (int i = 0; i < ap.tamaño(); i++) {
            Paciente p = ap.obtener(i);
            Object[] fila = { p.getCodigo(), p.getNombres(), p.getApellidos(), 
                              p.getDni(), p.getEdad(), (p.getEstado() == 0 ? "Registrado" : "Atendido") };
            modelo.addRow(fila);
        }
    }
    
    // Método para limpiar las cajas de texto
    private void limpiarCajas() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        txtNombres.requestFocus();
    }
    private void consultarPaciente() {
        try {
            int codigoBuscado = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(this, "Ingrese el Código del Paciente a buscar:"));
            Paciente p = buscarPorCodigo(codigoBuscado);
            
            if (p != null) {
                txtNombres.setText(p.getNombres());
                txtApellidos.setText(p.getApellidos());
                txtDni.setText(p.getDni());
                txtEdad.setText(String.valueOf(p.getEdad()));
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente encontrado.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente NO encontrado.");
                limpiarCajas();
            }
        } catch (Exception e) {
            System.out.println("Error en consulta: " + e.getMessage());
        }
    }

    private Paciente buscarPorCodigo(int codigo) {
        for (int i = 0; i < ap.tamaño(); i++) {
            if (ap.obtener(i).getCodigo() == codigo) {
                return ap.obtener(i);
            }
        }
        return null;
    }

    private void modificarPaciente() {
        try {
            int fila = tblPacientes.getSelectedRow();
            if (fila == -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un paciente de la tabla para modificar.");
                return;
            }
            
            int cod = Integer.parseInt(tblPacientes.getValueAt(fila, 0).toString());
            Paciente p = buscarPorCodigo(cod);
            
            if (p != null) {
                p.setNombres(txtNombres.getText());
                p.setApellidos(txtApellidos.getText());
                p.setDni(txtDni.getText());
                p.setEdad(Integer.parseInt(txtEdad.getText()));
                
                ap.grabarDatos();
                
                listarPacientes();
                limpiarCajas();
                javax.swing.JOptionPane.showMessageDialog(this, "Paciente modificado correctamente.");
            }
        } catch (Exception e) {
             javax.swing.JOptionPane.showMessageDialog(this, "Error al modificar. Verifique los datos.");
        }
    }

    private void eliminarPaciente() {
        int fila = tblPacientes.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un paciente de la tabla para eliminar.");
            return;
        }
        
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este paciente?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            modelo.removeRow(fila);
            javax.swing.JOptionPane.showMessageDialog(this, "Paciente eliminado de la vista.");
        }
    }
}
