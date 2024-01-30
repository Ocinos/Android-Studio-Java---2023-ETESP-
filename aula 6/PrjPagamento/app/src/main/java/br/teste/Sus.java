package br.teste;

public class Sus {
    private String nome;
    private int horas;
    private int pagamento;
    private int total;

    public Sus(String nome, int horas, int pagamento, int total){
        this.nome = nome;
        this.horas = horas;
        this.pagamento = pagamento;
        this.total = total;
    };

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setHoras(int horas){
        this.horas = horas;
    }
    public void setPagamento(int pagamento){
        this.pagamento = pagamento;
    }

    public String getNome(){
        return nome;
    }
    public int getHoras(){
        return horas;
    }
    public int getPagamento(){
        return pagamento;
    }
   public void setTotal(int total){
        this.total = total;
   }
   public int getTotal(){
        return total;
   }


}
