package br.teste;

import android.graphics.Bitmap;

public class Contatos {

    private String nome;
    private String sobrenome;
    private String idade;

    private Bitmap imagem;

    public Contatos(String nome, String sobrenome, String idade, Bitmap imagem)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.imagem = imagem;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getNome()
    {
        return nome;
    }
    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }
    public String getSobrenome()
    {
        return sobrenome;
    }
    public void setIdade(String idade)
    {
        this.idade = idade;
    }
    public String getIdade()
    {
        return idade;
    }

    public void setImagem(){
        this.imagem = imagem;
    }
    public Bitmap getImagem(){
        return imagem;
    }
}
