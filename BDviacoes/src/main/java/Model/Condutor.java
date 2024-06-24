package Model;

public class Condutor extends Funcionario {
    private int id_onibus;
    private long cnh;
    public Condutor(int id, String nome, String endereco, String email, String telefone, int supervisor, int id_onibus, long cnh){
        super(id, nome, endereco, email, telefone, supervisor);
        this.id_onibus = id_onibus;
        this.cnh = cnh;
    }

    public Condutor(int id, int id_onibus, long cnh){
        super(id);
        this.id_onibus = id_onibus;
        this.cnh = cnh;
    }

    public int getId_onibus_condutor() { return this.id_onibus; }

    public long getCnh_condutor() { return this.cnh; }
}