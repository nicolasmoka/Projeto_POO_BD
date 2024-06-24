package DAO;

import Model.Guiche;

import java.util.ArrayList;

import java.sql.SQLException;

public class GuicheDAO extends ConnectionDAO {
    boolean sucesso = false;

    public boolean insertGuiche(Guiche guiche){
        connectToDB();

        String sql = "INSERT INTO guiche VALUES (?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, guiche.getResponsavel());
            pst.setInt(2, guiche.getNumero());
            pst.setString(3, guiche.getCidade());
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

    public boolean updateResponsavelGuiche(int num_guiche, int responsavel){
        connectToDB();
        String sql = "UPDATE guiche SET responsavel=? WHERE numero=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, responsavel);
            pst.setInt(2, num_guiche);
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

    public boolean deleteGuiche(int num_guiche){
        connectToDB();
        String sql = "DELETE FROM guiche WHERE numero=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_guiche);
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

    public ArrayList<String> cidadesComGuiches(){
        connectToDB();
        ArrayList<String> cidades = new ArrayList<>();

        String sql = "SELECT DISTINCT cidade FROM guiche";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("cidade"));
                cidades.add(rs.getString("cidade"));
            }
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

        return cidades;
    }

    public ArrayList<Guiche> selectGuiche(){
        connectToDB();
        ArrayList<Guiche> guiches = new ArrayList<>();
        String sql = "SELECT g.*, f.nome_funcionario, f.telefone, f.email " +
                "FROM guiche g " +
                "JOIN funcionario f " +
                "ON g.responsavel = f.idfuncionario " +
                "ORDER BY g.cidade";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de guichês: ");

            while (rs.next()) {
                Guiche guicheAux = new Guiche(rs.getInt("responsavel"),
                        rs.getInt("numero"),rs.getString("cidade"));

                System.out.println("Número = " + rs.getInt("numero"));
                System.out.println("Cidade = " + rs.getString("cidade"));
                System.out.println("Responsável = " + rs.getString("nome_funcionario"));
                System.out.println("Telefone = " + rs.getString("telefone"));
                System.out.println("E-mail = " + rs.getString("email"));
                System.out.println("--------------------------------");

                guiches.add(guicheAux);
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
        return guiches;
    }
}
