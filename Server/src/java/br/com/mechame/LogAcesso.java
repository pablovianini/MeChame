/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mechame;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "log_acesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogAcesso.findAll", query = "SELECT l FROM LogAcesso l"),
    @NamedQuery(name = "LogAcesso.findById", query = "SELECT l FROM LogAcesso l WHERE l.id = :id"),
    @NamedQuery(name = "LogAcesso.findByDataLog", query = "SELECT l FROM LogAcesso l WHERE l.dataLog = :dataLog"),
    @NamedQuery(name = "LogAcesso.findByTelefone", query = "SELECT l FROM LogAcesso l WHERE l.telefone = :telefone"),
    @NamedQuery(name = "LogAcesso.findByDescricao", query = "SELECT l FROM LogAcesso l WHERE l.descricao = :descricao")})
public class LogAcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_log")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLog;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefone")
    private long telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descricao")
    private String descricao;

    public LogAcesso() {
    }

    public LogAcesso(Long id) {
        this.id = id;
    }

    public LogAcesso(Long id, Date dataLog, long telefone, String descricao) {
        this.id = id;
        this.dataLog = dataLog;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLog() {
        return dataLog;
    }

    public void setDataLog(Date dataLog) {
        this.dataLog = dataLog;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof LogAcesso)) {
            return false;
        }
        LogAcesso other = (LogAcesso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.mechame.LogAcesso[ id=" + id + " ]";
    }
    
}
