/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author angelo.freitas
 */
public class Categoria {
    //atributos
    private int id;
    private String descricao;
    
    //construtor vazio
    public Categoria() {
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
    
    //getters e seters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
    
    
    
}
