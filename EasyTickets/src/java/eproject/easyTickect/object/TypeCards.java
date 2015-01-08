/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.object;

/**
 *
 * @author Trang
 */
public class TypeCards {

    private int id;
    private String typecard;

    public TypeCards() {
    }

    public TypeCards(int id, String typecard) {
        this.id = id;
        this.typecard = typecard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypecard() {
        return typecard;
    }

    public void setTypecard(String typecard) {
        this.typecard = typecard;
    }

}
