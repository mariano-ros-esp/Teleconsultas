package clases;

public class Cita {
    private int numeroCita; // Correlativo, ej. 100001
    private int codigoPaciente;
    private String fecha;
    private String hora;
    private String especialidad;
    private double costo;

    public Cita(int numeroCita, int codigoPaciente, String fecha, String hora, String especialidad, double costo) {
        this.numeroCita = numeroCita;
        this.codigoPaciente = codigoPaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.especialidad = especialidad;
        this.costo = costo;
    }

    // Getters para obtener los datos luego
    public int getNumeroCita() { return numeroCita; }
    public int getCodigoPaciente() { return codigoPaciente; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEspecialidad() { return especialidad; }
    public double getCosto() { return costo; }
}