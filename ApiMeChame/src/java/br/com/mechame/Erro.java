/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mechame;

/**
 *
 * @author Pablo
 */
public abstract class Erro {
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    private String mensagem;
    
}
