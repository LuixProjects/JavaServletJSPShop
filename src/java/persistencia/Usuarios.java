/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author False
 */
@Entity
@NamedQueries({

    @NamedQuery(name= "Usuarios.comprobarLogin",
            query = "Select r from Usuarios r Where r.nombre = :name and r.contrasenna = :contra"),
    @NamedQuery(name="Usuarios.findByName",
                query="SELECT r FROM Usuarios r WHERE r.nombre = :name"),
    @NamedQuery(name="Usuarios.registrarUsuario",
                query="SELECT r FROM Usuarios r WHERE r.nombre = :name or r.correo = :corr"),
    @NamedQuery(name="Usuarios.findAll",
                query="SELECT r FROM Usuarios r"),
    @NamedQuery(name="Usuarios.existirCorreo",
            query="SELECT r FROM Usuarios r WHERE r.correo = :corr"),

})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String correo;
    private String contrasenna;
    
    @ManyToMany
    private List<Roles> roles;
    
    
    
    
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Usuarios[ id=" + id + " ]";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasenna
     */
    public String getContrasenna() {
        return contrasenna;
    }

    /**
     * @param contrasenna the contrasenna to set
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    /**
     * @return the roles
     */
    public List<Roles> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
    
}
