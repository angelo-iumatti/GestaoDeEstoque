/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.view;

import javax.swing.JOptionPane;
import model.bean.Categoria;
import model.dao.CategoriaDAO;
import org.junit.Test;

/**
 *
 * @author angelo.freitas
 */
public class CadastroCategoriaUITest {
    
    public CadastroCategoriaUITest() {
    }

    @Test
    public void testSomeMethod() {
        Categoria categoria = new Categoria();
        CategoriaDAO dao = new CategoriaDAO();
        boolean status;
        String descricao = "Teste";
        
        categoria.setDescricao(descricao);
        status = dao.save(categoria);
        if(status == false){
             JOptionPane.showMessageDialog(null, "Erro de conexão");
        }else{
            System.out.println("Categoria Cadastrada com sucesso");
        }
    }
    
}
