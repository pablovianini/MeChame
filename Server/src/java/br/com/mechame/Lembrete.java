/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mechame;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "lembrete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lembrete.findAll", query = "SELECT l FROM Lembrete l"),
    @NamedQuery(name = "Lembrete.findById", query = "SELECT l FROM Lembrete l WHERE l.id = :id"),
    @NamedQuery(name = "Lembrete.findByIdTelefone", query = "SELECT l FROM Lembrete l WHERE l.idTelefone = :idTelefone"),
    @NamedQuery(name = "Lembrete.findByTitulo", query = "SELECT l FROM Lembrete l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Lembrete.findByRaio", query = "SELECT l FROM Lembrete l WHERE l.raio = :raio"),
    @NamedQuery(name = "Lembrete.findByLatitude", query = "SELECT l FROM Lembrete l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Lembrete.findByLongitude", query = "SELECT l FROM Lembrete l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "Lembrete.findByAtivo", query = "SELECT l FROM Lembrete l WHERE l.ativo = :ativo")})
public class Lembrete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTelefone")
    private long idTelefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "raio")
    private double raio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "ativo")
    private Boolean ativo;

    public Lembrete() {
    }

    public Lembrete(Long id) {
        this.id = id;
    }

    public Lembrete(Long id, long idTelefone, String titulo, double raio, double latitude, double longitude) {
        this.id = id;
        this.idTelefone = idTelefone;
        this.titulo = titulo;
        this.raio = raio;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
        if (!(object instanceof Lembrete)) {
            return false;
        }
        Lembrete other = (Lembrete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.mechame.Lembrete[ id=" + id + " ]";
    }
    
}
