/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame.restful;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo
 */
@MappedSuperclass
@Table(name = "lembrete")
@XmlRootElement
public class Lembrete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "raio")
    private double raio;
    @Column(name = "precisao")
    private Boolean precisao;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "diasSemana")
    private Integer diasSemana;
    @OneToMany(mappedBy = "idLembrete")
    private Collection<Localizacao> localizacaoCollection;

    public Lembrete() {
    }

    public Lembrete(Long id) {
        this.id = id;
    }

    public Lembrete(Long id, String titulo, double raio) {
        this.id = id;
        this.titulo = titulo;
        this.raio = raio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getPrecisao() {
        return precisao;
    }

    public void setPrecisao(Boolean precisao) {
        this.precisao = precisao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(Integer diasSemana) {
        this.diasSemana = diasSemana;
    }

    @XmlTransient
    public Collection<Localizacao> getLocalizacaoCollection() {
        return localizacaoCollection;
    }

    public void setLocalizacaoCollection(Collection<Localizacao> localizacaoCollection) {
        this.localizacaoCollection = localizacaoCollection;
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
        return "br.com.mechame.restful.Lembrete[ id=" + id + " ]";
    }
    
}
