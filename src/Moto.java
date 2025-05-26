public class Moto extends Vehiculo implements VehiculoConPlaca {
    private String placa;

    public Moto(Persona persona, String color, String placa) {
        super(persona, color);
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
