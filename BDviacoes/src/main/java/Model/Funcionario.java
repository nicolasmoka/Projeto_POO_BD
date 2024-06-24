package Model;

public class Funcionario {
    protected int id;
    protected String nome;
    protected String endereco;
    protected String email;
    protected String telefone;
    protected int supervisor;

    public Funcionario (int id) { this.id = id; }
    public Funcionario (int id, String nome, String endereco, String email, String telefone, int supervisor) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.supervisor = supervisor;
    }

    public int getId() { return this.id; }

    public String getNome_funcionario() { return this.nome; }

    public String getEndereco() { return this.endereco; }

    public String getEmail() { return this.email; }

    public String getTelefone() { return this.telefone; }

    public int getSupervisor() { return this.supervisor; }
}
