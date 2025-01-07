package model.dao;

import model.bean.User;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class UserDAO {
    
    public User authenticate(String username, String password) {
        User user = null;
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn == null) {
                throw new SQLException("Falha na conexão com o banco de dados");
            }
            String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao autenticar usuário: " + e.getMessage());
        }
        return user;
    }

    public void save(User user) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn == null) {
                throw new SQLException("Falha na conexão com o banco de dados");
            }
            String sql = "INSERT INTO usuario (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + e.getMessage());
        }
    }
}