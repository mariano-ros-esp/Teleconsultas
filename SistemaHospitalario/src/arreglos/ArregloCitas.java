package arreglos;

import java.io.*;
import java.util.ArrayList;
import clases.Cita;

public class ArregloCitas {
    private ArrayList<Cita> lista;
    private String archivo = "citas.txt";

    public ArregloCitas() {
        lista = new ArrayList<Cita>();
        cargarDatos();
    }

    public void adicionar(Cita c) {
        lista.add(c);
        grabarDatos();
    }

    public int tamaño() {
        return lista.size();
    }

    public Cita obtener(int i) {
        return lista.get(i);
    }

    public Cita buscar(int numeroCita) {
        for (int i = 0; i < tamaño(); i++) {
            if (obtener(i).getNumeroCita() == numeroCita)
                return obtener(i);
        }
        return null;
    }

    public int codigoCorrelativo() {
        if (tamaño() == 0) return 100001;
        else return obtener(tamaño() - 1).getNumeroCita() + 1;
    }

    public void grabarDatos() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            for (int i = 0; i < tamaño(); i++) {
                Cita c = obtener(i);
                pw.println(c.getNumeroCita() + ";" + c.getCodigoPaciente() + ";" + 
                           c.getFecha() + ";" + c.getHora() + ";" + 
                           c.getEspecialidad() + ";" + c.getCosto() + ";" + c.getEstado());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al grabar citas: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] s = linea.split(";");
                int num = Integer.parseInt(s[0]);
                int codP = Integer.parseInt(s[1]);
                String fec = s[2];
                String hor = s[3];
                String esp = s[4];
                double costo = Double.parseDouble(s[5]);
                String est = "PROGRAMADA";
                if (s.length >= 7) {
                    est = s[6];
                }
                lista.add(new Cita(num, codP, fec, hor, esp, costo, est));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No se encontró el archivo citas.txt, se creará uno nuevo.");
        }
    }
}