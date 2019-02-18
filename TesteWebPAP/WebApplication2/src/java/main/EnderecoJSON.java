/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;

/**
 *
 * @author Ingo
 */
public class EnderecoJSON {

    String n;
    String rua;
    String bairro;
    String cidade;
    String estado;
    String lat;
    String lon;
    String cep;
    
    public EnderecoJSON() {
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    
    public String getLink()throws Exception{
        if(n==null||rua==null||cidade==null){
            throw new Exception("Dados insuficientes para criar um link "+new Date());
        }
        String link = "https://testenodered99.mybluemix.net/Local?url=https://maps.googleapis.com/maps/api/geocode/json?address=";
        link +=n+"+"+rua+","+cidade;
        if(estado != null){
            link+=","+estado;
        }
        if(cep != null){
            link+=","+cep;
        }
        String resp="";
        for(int i=0;i<link.length();i++){
            if(link.charAt(i) == ' '){
                resp+="%20";
            }else{
                resp+=link.charAt(i);
            }
        }
        System.out.println("url sem espaco "+resp);
        String txtUTF8 = new String(resp.getBytes("UTF-8"));
        return txtUTF8;
    }

}
