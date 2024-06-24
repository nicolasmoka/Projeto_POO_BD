package DAO;

import Model.Onibus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OnibusDAO extends ConnectionDAO {
    boolean sucesso = false;

    public boolean insertOnibus(Onibus onibus){
        connectToDB();

        String sql = "INSERT INTO onibus VALUES (?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,onibus.getId());
            pst.setString(2, onibus.getPlaca());
            pst.setString(3, onibus.getMarca());
            pst.setString(4, onibus.getModelo());
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

    public boolean updatePlacaOnibus(int id_onibus, String placa){
        connectToDB();
        String sql = "UPDATE onibus SET placa=? WHERE id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, placa);
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

    public boolean deleteOnibus(int id_onibus){
        connectToDB();
        String sql = "DELETE FROM onibus WHERE id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id_onibus);
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

    public HashMap<String,Integer> contMarcas(){
        HashMap<String,Integer> marcas = new HashMap<>();
        connectToDB();
        String sql = "SELECT count(marca) AS quantidade, marca FROM onibus GROUP BY marca";

        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()){
                marcas.put(rs.getString("marca"), rs.getInt("quantidade"));

                System.out.println(rs.getInt("quantidade") + " x " + rs.getString("marca"));
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

        return marcas;
    }

    public ArrayList<Onibus> selectOnibus(){
        ArrayList<Onibus>onibus = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM onibus";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de onibus: ");

            while (rs.next()) {

                Onibus onibusAux = new Onibus(rs.getInt("id"), rs.getString("placa"),
                rs.getString("marca"), rs.getString("modelo"));

                System.out.println("Identificação = " + onibusAux.getId());
                System.out.println("Placa = " + onibusAux.getPlaca());
                System.out.println("Marca = " + onibusAux.getMarca());
                System.out.println("Modelo = " + onibusAux.getModelo());
                System.out.println("--------------------------------");

                onibus.add(onibusAux);
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
        return onibus;
    }
}
