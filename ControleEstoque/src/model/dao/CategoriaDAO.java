/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import model.bean.Categoria;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author angelo.freitas
 */
public class CategoriaDAO {

    //Fazendo a conexão
    private Connection con = null;

    public CategoriaDAO() {
        con = ConnectionFactory.getConnection();
    }

    //CRUD
    public boolean save(Categoria categoria) {

        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //listar todos os dados do banco de dados
    public List<Categoria> findAll() {
        String sql = "SELECT * FROM categoria";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categoria> categorias = new ArrayList<>();

        try {
            //adicionando os dados no banco na Lista
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return categorias;
    }

    public boolean update(Categoria categoria) {

        String sql = "UPDATE categoria SET descricao = ? WHERE id = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Categoria categoria) {

        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    

    public void listar(){
        CategoriaDAO dao = new CategoriaDAO();
        
        for(Categoria c: dao.findAll()){
            System.out.println("Descrição: "+ c.getDescricao());
        }
    }

}
