package DAO;

import Model.Funcionario;

import java.sql.SQLException;

import java.util.ArrayList;

public class FuncionarioDAO extends ConnectionDAO{
    boolean sucesso  = false;

    public boolean insertFuncionario(Funcionario funcionario){
        connectToDB();

        String sql = "INSERT INTO funcionario VALUES (?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,funcionario.getId());
            pst.setString(2, funcionario.getNome_funcionario());
            pst.setString(3, funcionario.getEndereco());
            pst.setString(4, funcionario.getEmail());
            pst.setString(5, funcionario.getTelefone());
            pst.setInt(6, funcionario.getSupervisor());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    //update nome_funcionario, endereco, email, telefone
    public boolean updateFuncionario(int idfuncionario, String data, int tipo){
        connectToDB();
        String sql;
        if (tipo == 1) // 1 para mudar o nome
            sql = "UPDATE funcionario SET nome_funcionario=? WHERE idfuncionario=?";
        else if (tipo == 2) // 2 para mudar o endereco
            sql = "UPDATE funcionario SET endereco=? WHERE idfuncionario=?";
        else if (tipo == 3) // 3 para mudar o e-mail
            sql = "UPDATE funcionario SET email=? WHERE idfuncionario=?";
        else // >= 4 para mudar o telefone
            sql = "UPDATE funcionario SET telefone=? WHERE idfuncionario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, data);
            pst.setInt(2, idfuncionario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    // update supervisor
    public boolean updateFuncionario(int idfuncionario, int supervisor){
        connectToDB();
        String sql = "UPDATE funcionario SET supervisor=? WHERE idfuncionario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, supervisor);
            pst.setInt(2, idfuncionario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deleteFuncionario(int idfuncionario){
        connectToDB();
        String sql = "DELETE FROM funcionario WHERE idfuncionario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idfuncionario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Funcionario> selectFuncionario(){
        ArrayList<Funcionario>funcionarios =new ArrayList<>();
        connectToDB();
        String sql = "SELECT emp.*, sup.nome_funcionario AS nome_supervisor " +
                "FROM funcionario emp, funcionario sup WHERE emp.supervisor = sup.idfuncionario " +
                "ORDER BY emp.nome_funcionario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de funcionarios: ");

            while (rs.next()) {

                Funcionario funcionarioAux = new Funcionario(rs.getInt("idfuncionario"),
                        rs.getString("nome_funcionario"), rs.getString("endereco"),
                        rs.getString("email"), rs.getString("telefone"),
                        rs.getInt("supervisor"));

                System.out.println("Identificação = " + funcionarioAux.getId());
                System.out.println("Nome = " + funcionarioAux.getNome_funcionario());
                System.out.println("Endereco = " + funcionarioAux.getEndereco());
                System.out.println("E-mail = " + funcionarioAux.getEmail());
                System.out.println("Telefone = " + funcionarioAux.getTelefone());
                System.out.println("Id supervisor = " + funcionarioAux.getSupervisor());
                System.out.println("Nome supervisor = " + rs.getString("nome_supervisor"));
                System.out.println("--------------------------------");

                funcionarios.add(funcionarioAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return funcionarios;
    }

    public Funcionario pesquisaPorIdNome(int id, String nome) {
        connectToDB();
        Funcionario funcionario = null;
        String sql = "SELECT emp.endereco, emp.email, emp.telefone, emp.supervisor, sup.nome_funcionario AS nome_supervisor " +
                "FROM funcionario emp, funcionario sup WHERE emp.supervisor = sup.idfuncionario " +
                "AND emp.idfuncionario=? AND emp.nome_funcionario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario(id,
                        nome, rs.getString("endereco"),
                        rs.getString("email"), rs.getString("telefone"),
                        rs.getInt("supervisor"));
                System.out.println("endereco = " + funcionario.getEndereco());
                System.out.println("email = " + funcionario.getEmail());
                System.out.println("telefone = " + funcionario.getTelefone());
                System.out.println("Id supervisor = " + funcionario.getSupervisor());
                System.out.println("Nome supervisor = " + rs.getString("nome_supervisor"));
                System.out.println("--------------------------------");
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return funcionario;
    }
}
