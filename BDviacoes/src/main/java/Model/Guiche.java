package Model;

public class Guiche {
    private int responsavel;
    private int numero;
    private String cidade;

    public Guiche (int responsavel, int numero, String cidade){
        this.responsavel = responsavel;
        this.numero = numero;
        this.cidade = cidade;
    }

    public int getResponsavel() { return this.responsavel; }
    public int getNumero() { return this.numero; }
    public String getCidade() { return this.cidade; }
}