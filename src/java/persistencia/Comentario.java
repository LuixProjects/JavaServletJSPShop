/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author False
 */
@Entity
@NamedQueries({

    @NamedQuery(name= "Comentario.buscarComentario",
            query= "Select r from Comentario r where r.objeto.nombre = :objeto"),

})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String texto;

    
    @ManyToOne(cascade={CascadeType.REMOVE})
    private Items objeto;
    
    @ManyToOne
    private Usuarios usuarioComentador;

    public Items getObjeto() {
        return objeto;
    }

    public void setObjeto(Items objeto) {
        this.objeto = objeto;
    }

    public Usuarios getUsuarioComentador() {
        return usuarioComentador;
    }
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setUsuarioComentador(Usuarios usuarioComentador) {
        this.usuarioComentador = usuarioComentador;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Comentario[ id=" + id + " ]";
    }
    
}
