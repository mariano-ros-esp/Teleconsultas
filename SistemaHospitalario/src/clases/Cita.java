package clases;

public class Cita {
    private int numeroCita;
    private int codigoPaciente;
    private String fecha;
    private String hora;
    private String especialidad;
    private double costo;
    private String estado;

    public Cita(int numeroCita, int codigoPaciente, String fecha, String hora, String especialidad, double costo) {
        this.numeroCita = numeroCita;
        this.codigoPaciente = codigoPaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.especialidad = especialidad;
        this.costo = costo;
        this.estado = "PROGRAMADA";
    }

    public Cita(int numeroCita, int codigoPaciente, String fecha, String hora, String especialidad, double costo, String estado) {
        this.numeroCita = numeroCita;
        this.codigoPaciente = codigoPaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.especialidad = especialidad;
        this.costo = costo;
        this.estado = estado;
    }

    public int getNumeroCita() { return numeroCita; }
    public void setNumeroCita(int numeroCita) { this.numeroCita = numeroCita; }

    public int getCodigoPaciente() { return codigoPaciente; }
    public void setCodigoPaciente(int codigoPaciente) { this.codigoPaciente = codigoPaciente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}