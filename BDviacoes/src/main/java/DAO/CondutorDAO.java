package DAO;

import Model.Condutor;

import java.util.ArrayList;

import java.sql.SQLException;

public class CondutorDAO extends ConnectionDAO {
    boolean sucesso = false;

    public boolean insertCondutor(Condutor condutor){
        connectToDB();

        String sql = "INSERT INTO condutor VALUES (?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, condutor.getId_onibus_condutor());
            pst.setLong(2, condutor.getCnh_condutor());
            pst.setInt(3, condutor.getId());
            pst.execute();
            sucesso = true;
        }catch (SQLException exc){
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

    public boolean deleteCondutor(int idfuncionario, int id_onibus){
        connectToDB();
        String sql = "DELETE FROM condutor WHERE idfuncionario=? AND id_onibus=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idfuncionario);
            pst.setInt(2, id_onibus);
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

    public ArrayList<Condutor> selectCondutor(){
        connectToDB();
        ArrayList<Condutor> condutores = new ArrayList<>();
        String sql = "SELECT emp.*, sup.nome_funcionario AS nome_supervisor, c.cnh_condutor, o.* " +
                "FROM condutor c " +
                "JOIN funcionario emp " +
                "ON c.idfuncionario = emp.idfuncionario " +
                "JOIN funcionario sup " +
                "ON emp.supervisor = sup.idfuncionario " +
                "JOIN onibus o " +
                "ON c.id_onibus = o.id " +
                "ORDER BY emp.nome_funcionario ASC";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de condutores + ônibus:");

            while (rs.next()) {
                Condutor condutorAux = new Condutor(rs.getInt("idfuncionario"), rs.getString("nome_funcionario"),
                        rs.getString("endereco"), rs.getString("email"), rs.getString("telefone"),
                        rs.getInt("supervisor"), rs.getInt("id"),rs.getLong("cnh_condutor"));

                System.out.println("Nome = " + condutorAux.getNome_funcionario());
                System.out.println("Endereço = " + condutorAux.getEndereco());
                System.out.println("E-mail = " + condutorAux.getEmail());
                System.out.println("Telefone = " + condutorAux.getTelefone());
                System.out.println("Número da CNH = " + condutorAux.getCnh_condutor());
                System.out.println("Nome do supervisor = " + rs.getString("nome_supervisor"));
                System.out.println("Placa do ônibus = " + rs.getString("placa"));
                System.out.println("Marca do ônibus = " + rs.getString("marca"));
                System.out.println("Modelo do ônibus = " + rs.getString("modelo"));
                System.out.println("--------------------------------");

                condutores.add(condutorAux);
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
        return condutores;
    }
}
