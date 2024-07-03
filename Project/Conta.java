import java.security.KeyStore;
// Estamos importando a biblioteca Scanner para ler dados do usuário
import java.util.Scanner;
// Estamos implementando a classe Conta, do qual vamos herdar Poupança, etc
class Conta {
  private
    float vlrSaldo; // Atributo privado para controlar saldo das contas
  protected
    String txtNome;
    String txtNumConta;

// Método público usado para mostrar um menu de opções ao usuário
 public int imprimirMenu(){
  // Estamos instanciando (declarando) o objeto "ler" da classe "Scanner"
  Scanner ler = new Scanner(System.in);
  // Estamos imprimindo o menu na tela
  System.out.println("\n\n BEM VINDO AO NOSSO BANCO \n");
  System.out.println("  1-Consultar Saldo");
  System.out.println("  2-Depositar");
  System.out.println("  3-Sacar");
  System.out.println("  5-Sair");
  System.out.print("\n Digite uma das opcoes acima: ");
  // Estamos retornando o valor do tipo inteiro "nextInt" informado pelo usuário
  return ler.nextInt();
  }
  // Método acessor para retornar o valor do saldo que é do tipo private fora da classe dentro do main
  public float getSaldo(){
   // Aqui seria implementado toda a implementação de segurança
   return(vlrSaldo);
  }

  // Poderiamos disponibilizar também um método mutante para alterar o valor do saldo fora da classe
  // Mas, por segurança, vamos deixar ele comentado e assim não prejudicar a integridade do programa
//public void getSaldo(float novoValor){
//   // Aqui seria implementado toda a implementação de segurança, validações e etc
//   vlrSaldo = novoValor;
//  } 

// Método público para inicializar uma conta com os dados do cliente
  public void criarConta(){
    // System.in na linha a seguir significa o dispositivo de saída padrão do S.O.
    Scanner ler = new Scanner(System.in);
    System.out.println(" Informe o nome do Correntista: ");
    txtNome = ler.nextLine();
    System.out.println(" Informe o numero da conta");
    txtNumConta = ler.nextLine();
    System.out.println(" Informe o valor inicial do saldo: ");
    vlrSaldo = ler.nextFloat(); // "vlrSaldo" = Atributo da classe Conta - "ler" = Objeto - "nextFloat() = Método"
  }
  public void consultarSaldo(){
    System.out.println("\n Consultar de Saldo");
    System.out.println(" Nome do Cliente: -> " + txtNome);
    System.out.println(" Numero da Conta: -> " + txtNumConta);
    System.out.printf(" Valor do Saldo: -> R$ %.2f%n", vlrSaldo);
  }
  // Estamos implementando o método para depositar valores na conta
  public boolean depositarValor(float quanto){
    if (quanto <=0){ // Se o valor depositado for inválido
      return false;
    }
    else{ // Se estiver ok..
      vlrSaldo += quanto;
      System.out.println("\n Valor depositado com Sucesso!!!");
      return true;
    }
  }

// Implementar aqui o método sacar com cuidado para não sacar valor acima do saldo disponível
 public boolean sacarValor(float howMuch){
    if (howMuch <=0){ // Se o valor saque for inválido
      System.out.println("Valor de saque inváldio.");
      System.out.println("\n");
      return (false);
    }
    else if(howMuch > vlrSaldo){
      System.out.printf("\n Saldo insuficiente para o saque.");
      System.out.printf("\n");
      return false;
    }
    else{ // Se estiver ok..
      vlrSaldo = vlrSaldo - howMuch;
      System.out.print("\n Saque realizado com sucesso.");
      System.out.print("\n");
      return true;
    }
  }

// Estamos implementando o método principal (main())
  public static void main(String[] args){
  // Estamos instanciando o objeto "ler" da classe "Scanner"
    Scanner ler = new Scanner(System.in);
    float valor; // Declarando uma variável local para manipular o valor saldo.
    int resposta; // Declarando uma variável local para solicitar a opção do usuário
    Conta minhaConta = null; // Instanciando o objeto minhaConta da classe Conta

    System.out.println("\n \n BEM VINDO AO NOSSO BANCO \n");
    System.out.println("  1-Conta Corrente");
    System.out.println("  2-Conta Poupanca");
    System.out.println("  3-Conta Universitaria");
    System.out.println("  4-Conta Salario");
    System.out.println("  5-Sair");
    System.out.print("\n Digite uma das opcoes acima: ");
    resposta = ler.nextInt();
    if (resposta == 1){
       minhaConta = new Conta(); // Estamos instanciando o objeto minhaConta da Classe Raiz Conta
       minhaConta.criarConta(); // Estamos chamando o método criarConta da classe Conta
    }
   else if (resposta == 2){
       minhaConta = new Poupanca(); // Estamos instanciando o objeto minhaConta da subclasse Poupança --Descendente de conta-- Progenitora de Universitária
       minhaConta.criarConta(); // Estamos chamando o método criarConta da classe Conta
    }
    else if (resposta == 3){
        minhaConta = new Universitaria(); // Estamos instanciando o objeto minhaConta da  Classe Folha Universitária
        minhaConta.criarConta(); // Estamos chamando o método criarConta da classe Conta
    }
    else if (resposta == 4){
        minhaConta = new Salario(); // Estamos instanciando o objeto minhaConta da Classe Folha Salario
        minhaConta.criarConta(); // Estamos chamando o método criarConta da classe Conta
    }
    do {
       resposta = minhaConta.imprimirMenu();
    if (resposta == 1){
      minhaConta.consultarSaldo();
    }
    else if (resposta == 2){
      System.out.println(" Informe o valor a ser depositado: ");
      if (minhaConta.depositarValor(valor = ler.nextFloat())){
         System.out.printf(" Novo Saldo: -> R$ %.2f%n", minhaConta.getSaldo());
      }
    }
    else if(resposta == 3){
      System.out.println(" Informe o valor do saque: ");
      valor = ler.nextFloat();
      if(minhaConta.sacarValor(valor)){
         System.out.printf(" Novo Saldo: -> R$ %.2f%n", minhaConta.getSaldo());
         }
      else{
         System.out.printf(" O Saldo continua: -> R$ %.2f%n", minhaConta.getSaldo());
      }
    }
    else {
      System.out.println("\n Obrigado por usar o nosso banco!! \n\n");
    }
    }
    while (resposta < 5); // Fechando o comando de repetição "do"
  } // Estamos fechando o método principal "main"
// Implementação da subclasse Poupança (filha da classe Conta) - Herança
// Regra do cliente: Se a conta for do tipo Poupança, haverá limite de saque de 500 reais por mês
}  // Estamos fechando a classe Conta
class Poupanca extends Conta {
   protected float limiteSaqueMes = 500;
   protected float vlrTotalSaquesJaRealizados = 0;
   protected boolean aindaPodeSacar(float valor) {
       return(valor + vlrTotalSaquesJaRealizados <= limiteSaqueMes);
    }

   // Estamos sobrescrevendo o método sacar (polimorfismo de sobrescrita) para atender o pedido do cliente
   // Capitulo 06, página 34 do livro
   public boolean sacarValor(float howMuch){  // Não mudou assinatura e mudou a ação -- sobrescrita
   // Código malicioso a seguir CUIDADO!!!
      if (aindaPodeSacar(howMuch)){
         if(super.sacarValor(howMuch)) { // Chamando o método da classe pai -- Super impede recursividade pois essa não é a intenção
             vlrTotalSaquesJaRealizados = vlrTotalSaquesJaRealizados + howMuch;
            return(true); 
         }
         else{
            return (false);
         }
      }
      else{
         System.out.println("Valor limite de saque diario EXCEDIDO");
         return false;
      }

    } // Estamos fechando o método sobrescrito sacarValor
} // Estamos fechando a classe Poupança (filha da classe Conta)


// Implementar a classe Universitária
// Terá as mesmas regras de poupança, porém, com limite de 2 saques
class Universitaria extends Poupanca{
  private
    int limiteSaqueMes = 2;
    int saqueFeito = 0;

    private boolean check(){
      return saqueFeito < limiteSaqueMes;
    }

    public boolean sacarValor(float vlr){
      if (check()){
        if (super.sacarValor(vlr)){
          saqueFeito ++;
          return true;
        }
        else{
          return false;
        }
      }
      else{
        System.out.println("Limite de saques mensais EXCEDIDO!!");
        return false;
      }
    }

}

// Implementar a conta salário
// Terá as mesmas regras da conta normal, porém, com limite de um único deposito por mês
class Salario extends Conta{
  private
    int deposito = 1;
    int depositoFeito = 0;
  protected boolean limiteDeposito(){
    return depositoFeito < deposito;
  }

  public boolean depositarValor(float vlr){
    if (limiteDeposito()){
      if (super.depositarValor(vlr)){
        depositoFeito++;
        return true;
      }
      else{
        return false;
      }

    }
    else{
      System.out.println("Limite de deposito mensais EXCEDIDO!!!");
      return false;
    }
  }
}