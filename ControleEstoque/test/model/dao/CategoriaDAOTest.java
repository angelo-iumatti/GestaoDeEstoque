/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.dao;

import model.bean.Categoria;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author angelo.freitas
 */
public class CategoriaDAOTest {
    
    public CategoriaDAOTest() {
    }

    @Test
    public void listar(){
        CategoriaDAO dao = new CategoriaDAO();
        
        for(Categoria c: dao.findAll()){
            System.out.println("Descrição: "+ c.getDescricao());
        }
    }
    
}
