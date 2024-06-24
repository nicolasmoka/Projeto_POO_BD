package Model;

public class Onibus {
    private int id;
    private String placa;
    private String marca;
    private String modelo;

    public Onibus (int id, String placa, String marca, String modelo) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getId() { return this.id; }

    public String getPlaca() { return this.placa; }

    public String getMarca() { return this.marca; }

    public String getModelo() { return this.modelo; }
}
