public class Carro extends Vehiculo implements VehiculoConPlaca {
    private String placa;

    public Carro(Persona persona, String color, String placa) {
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
