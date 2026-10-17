package arreglos;

import java.io.*;
import java.util.ArrayList;
import clases.Paciente;

public class ArregloPacientes {
    private ArrayList<Paciente> lista;
    private String archivo = "pacientes.txt";

    public ArregloPacientes() {
        lista = new ArrayList<Paciente>();
        cargarDatos();
    }

    public void adicionar(Paciente p) {
        lista.add(p);
        grabarDatos();
    }

    public int tamaño() {
        return lista.size();
    }

    public Paciente obtener(int i) {
        return lista.get(i);
    }

    public Paciente buscar(int codigo) {
        for (int i = 0; i < tamaño(); i++) {
            if (obtener(i).getCodigo() == codigo)
                return obtener(i);
        }
        return null;
    }

    public Paciente buscarPorDni(String dni) {
        for (int i = 0; i < tamaño(); i++) {
            if (obtener(i).getDni().equals(dni))
                return obtener(i);
        }
        return null;
    }

    public void eliminar(Paciente p) {
        lista.remove(p);
        grabarDatos();
    }

    public int codigoCorrelativo() {
        if (tamaño() == 0)
            return 202610001;
        else
            return obtener(tamaño() - 1).getCodigo() + 1;
    }

    public void grabarDatos() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            for (int i = 0; i < tamaño(); i++) {
                Paciente p = obtener(i);
                pw.println(p.getCodigo() + ";" + p.getNombres() + ";" + 
                           p.getApellidos() + ";" + p.getDni() + ";" + 
                           p.getEdad() + ";" + p.getTelefono() + ";" + p.getEstado());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al grabar el archivo: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] s = linea.split(";");
                int cod = Integer.parseInt(s[0]);
                String nom = s[1];
                String ape = s[2];
                String dni = s[3];
                int edad = Integer.parseInt(s[4]);
                String tel = "";
                int est = 0;
                if (s.length == 6) {
                    est = Integer.parseInt(s[5]);
                } else {
                    tel = s[5];
                    est = Integer.parseInt(s[6]);
                }
                lista.add(new Paciente(cod, nom, ape, dni, edad, tel, est));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No se encontró el archivo o está vacío, se creará uno nuevo.");
        }
    }
}