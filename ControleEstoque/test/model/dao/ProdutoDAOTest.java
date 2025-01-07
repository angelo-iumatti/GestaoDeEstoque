/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.bean.Produto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author angelo.freitas
 */
public class ProdutoDAOTest {
    
    private Connection con = null;
    
    public ProdutoDAOTest() {
        con = ConnectionFactory.getConnection();
    }

    //Para inserir chave estrangeira 
    @Test
    @Ignore
    public void inserir() {
        Categoria categoria = new Categoria();
        categoria.setId(2);
        
        
        Produto produto = new Produto();
        produto.setDescricao("Feijão");
        produto.setQtd(20);
        produto.setCategoria(categoria);
        
        ProdutoDAO dao = new ProdutoDAO();
        
        if (dao.save(produto)){
            System.out.println("Salvo com sucesso");
        }else{
            fail("Erro ao salvar");
        }
    }
    
    @Test
    @Ignore
    public void listar(){
        ProdutoDAO dao = new ProdutoDAO();
        
        for(Produto p: dao.findAll()){
            System.out.println("Descrição Produto: "+p.getDescricao()+" - Descrição Categoria: "+p.getCategoria().getDescricao());
        }
    }
    
  
 
    
    @Test
    public ResultSet pegarId(String Roupas){
        Categoria cate = new Categoria();
        cate.setDescricao(Roupas);
        
        
        String sql = "SELECT id FROM categoria WHERE descricao = \"?\"";
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        
        try {
            stmt = con.prepareStatement(sql);            
            cate.setId(rs.getInt("id"));
            stmt.executeUpdate();
            System.out.println(rs.getInt("id"));
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }      
        return null;  
    }
    
    
}
