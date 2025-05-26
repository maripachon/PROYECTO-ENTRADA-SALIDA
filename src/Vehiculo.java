import java.time.LocalDateTime;

public abstract class Vehiculo {
    protected Persona persona;
    protected String color;
    protected LocalDateTime horaIngreso;
    protected LocalDateTime horaSalida;

    public Vehiculo(Persona persona, String color) {
        this.persona = persona;
        this.color = color;
        this.horaIngreso = LocalDateTime.now();
        this.horaSalida = null;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getColor() {
        return color;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void registrarSalida() {
        this.horaSalida = LocalDateTime.now();
    }
}
