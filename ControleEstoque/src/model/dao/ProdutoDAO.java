package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.bean.Categoria;
import model.bean.Produto;

public class ProdutoDAO {

    private Connection con = null;

    public ProdutoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Produto produto) {

        String sql = "INSERT INTO produto (descricao, qtd, categoria_id) VALUES (?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setInt(3, produto.getCategoria().getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //Fazer a consulta entre as duas tabelas usando composição
    public List<Produto> findAll() {
        String sql = "SELECT * FROM lista_estoque;";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            //adicionando os dados no banco na Lista
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                //setar os dois objetos de consulta Produto e Categoria
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("pdesc"));
                produto.setQtd(rs.getInt("qtd"));

                Categoria categoria = new Categoria();
                categoria.setDescricao(rs.getString("cdesc"));

                //composição dos objetos
                produto.setCategoria(categoria);

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public ResultSet encontrarId(Categoria categoria) {
        String sql = "SELECT id FROM categoria WHERE descricao = \"?\"";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String ct = null;

        try {
            stmt = con.prepareStatement(sql);
            categoria.setId(rs.getInt("id"));
            stmt.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return null;
    }

    public List<Categoria> findByCategoria(int categoria) {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn == null) {
                throw new SQLException("Falha na conexão com o banco de dados");
            }
            String sql = "SELECT * FROM produto WHERE categoria_id LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + categoria + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categoria cate = new Categoria();
                cate.setId(rs.getInt("id"));
                categorias.add(cate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao filtrar podcasts: " + e.getMessage());
        }
        return categorias;
    }

    public boolean alterar(Produto produto) {

        String sql = "UPDATE produto SET descricao=?, qtd=?, categoria_id=? WHERE id=?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setInt(3, produto.getCategoria().getId());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluir(Produto produto) {

        String sql = "DELETE FROM produto WHERE id=?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
