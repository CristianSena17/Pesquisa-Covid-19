package data_structures;
import java.io.*;   
import java.util.*;



import interfaces.functional.comparation;

////////////////////////////////////////////////
public class tree<T extends Comparable<T>> {
  private node<T> root; // raiz
  
  public tree() { root=null; } // inicializa arvore

  public void inserir(T v) {
    node<T> nodevo = new node<T>(); // cria um nodevo Nó
    nodevo.item = v; // atribui o valor recebido ao item de dados do Nó
    nodevo.dir = null;
    nodevo.esq = null;

    if (root == null) root = nodevo;
    else  { // se nao for a raiz
      node<T> atual = root;
      node<T> anterior;
      while(true) {
        anterior = atual;
        int cmp = v.compareTo(atual.item);
        if (cmp <= 0) { // ir para esquerda
          atual = atual.esq;
          if (atual == null) {
            anterior.esq = nodevo;
            return;
          } 
        }  // fim da condição ir a esquerda
        else { // ir para direita
           atual = atual.dir;
           if (atual == null) {
             anterior.dir = nodevo;
             return;
           }
        } // fim da condição ir a direita
      } // fim do laço while
    } // fim do else não raiz

  }

  public T buscar(Object chave, comparation criterion) {
    int cont=1;
    if (root == null){
      System.out.println("Numero de comparacoes na Arvore: 1");
      return null; // se arvore vazia
    } 
     
    
    node<T> atual = root;  // começa a procurar desde raiz
    int cmp = criterion.compare(atual.item, chave); 
    cont++;
    while (cmp != 0) { // enquanto nao encontrou
      cont+=2;
      if(cmp < 0){
        atual = atual.esq; // caminha para esquerda

      }else{
        atual = atual.dir; // caminha para direita
        
      }
      cont++;
      if (atual == null){
        System.out.printf("Numero de comparacoes na Arvore: %d\n",cont);
        return null; // encontrou uma folha -> sai
      }
      cont++;
      cmp = criterion.compare(atual.item, chave);
    } // fim laço while
    System.out.printf("Numero de comparacoes na Arvore: %d\n",cont);
    return atual.item; // terminodeu o laço while e chegou aqui é pq encontrou item
  }



  public boolean remover(T v) {
    if (root == null) return false; // se arvore vazia

    node<T> atual = root;
    node<T> pai = root;
    boolean filho_esq = true;

    // ****** Buscando o valor **********
    int cmp = v.compareTo(atual.item);
    while (cmp != 0) { // enquanto nao encontrou
      pai = atual;
      if(cmp < 0 ) { // caminha para esquerda
        atual = atual.esq;
        filho_esq = true; // é filho a esquerda? sim
      }
      else { // caminha para direita
        atual = atual.dir; 
        filho_esq = false; // é filho a esquerda? NAO
      }
      if (atual == null) return false; // encontrou uma folha -> sai
    } // fim laço while de busca do valor

    // **************************************************************
    // se chegou aqui quer dizer que encontrou o valor (v)
    // "atual": contem a referencia ao node a ser eliminado
    // "pai": contem a referencia para o pai do node a ser eliminado
    // "filho_esq": é verdadeiro se atual é filho a esquerda do pai
    // **************************************************************

    // Se nao possui nenhum filho (é uma folha), elimine-o
    if (atual.esq == null && atual.dir == null) {
      if (atual == root ) root = null; // se raiz
      else if (filho_esq) pai.esq = null; // se for filho a esquerda do pai
           else pai.dir = null; // se for filho a direita do pai
    }

    // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
    else if (atual.dir == null) {
       if (atual == root) root = atual.esq; // se raiz
       else if (filho_esq) pai.esq = atual.esq; // se for filho a esquerda do pai
            else pai.dir = atual.esq; // se for filho a direita do pai
    }
    
    // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
    else if (atual.esq == null) {
       if (atual == root) root = atual.dir; // se raiz
       else if (filho_esq) pai.esq = atual.dir; // se for filho a esquerda do pai
            else pai.dir = atual.dir; // se for  filho a direita do pai
    }

    // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
    else {
      node<T> sucessor = node_sucessor(atual);
      // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do node que deseja-se remover
      if (atual == root) root = sucessor; // se raiz
      else if(filho_esq) pai.esq = sucessor; // se for filho a esquerda do pai
           else pai.dir = sucessor; // se for filho a direita do pai
      sucessor.esq = atual.esq; // acertando o ponteiro a esquerda do sucessor agora que ele assumiu 
                                // a posição correta na arvore   
    }

    return true;
  }
  
  // O sucessor é o Nó mais a esquerda da subarvore a direita do node que foi passado como parametro do metodo
  public node<T> node_sucessor(node<T> apaga) { // O parametro é a referencia para o node que deseja-se apagar
     node<T> paidosucessor = apaga;
     node<T> sucessor = apaga;
     node<T> atual = apaga.dir; // vai para a subarvore a direita

     while (atual != null) { // enquanto nao chegar node Nó mais a esquerda
       paidosucessor = sucessor;
       sucessor = atual;
       atual = atual.esq; // caminha para a esquerda
     } 
     // *********************************************************************************
     // quando sair do while "sucessor" será o Nó mais a esquerda da subarvore a direita
     // "paidosucessor" será o o pai de sucessor e "apaga" o Nó que deverá ser eliminado
     // *********************************************************************************
     if (sucessor != apaga.dir) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
       paidosucessor.esq = sucessor.dir; // pai herda os filhos do sucessor que sempre serão a direita
       // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre será o
       // Nó mais a esquerda da subarvore a direita do Nó apaga.
       // lembrando também que sucessor sempre será o filho a esquerda do pai

       sucessor.dir = apaga.dir; // guardando a referencia a direita do sucessor para 
                                 // quando ele assumir a posição correta na arvore
     }
     return sucessor;
  }
  
  public void caminhar() {
    System.out.print("\n Exibindo em ordem: ");
    inoderder(root);
    System.out.print("\n Exibindo em pos-ordem: ");
    posOrder(root);
    System.out.print("\n Exibindo em pre-ordem: ");
    preOrder(root);
    System.out.print("\n Altura da arvore: " + altura(root));
    System.out.print("\n Quantidade de folhas: " + folhas(root));
    System.out.print("\n Quantidade de Nós: " + contarnodes(root));
    if (root != null ) {  // se arvore nao esta vazia
       System.out.print("\n Valor minimo: " + min().item);
       System.out.println("\n Valor maximo: " + max().item);
    }
  }

  public void inoderder(node<T> atual) {
    if (atual != null) {
      inoderder(atual.esq);
      System.out.print(atual.item + " ");
      inoderder(atual.dir);
    }
  }
  
  public void preOrder(node<T> atual) {
    if (atual != null) {
      System.out.print(atual.item + " ");
      preOrder(atual.esq);
      preOrder(atual.dir);
    }
  }
  
  public void posOrder(node<T> atual) {
    if (atual != null) {
      posOrder(atual.esq);
      posOrder(atual.dir);
      System.out.print(atual.item + " ");
    }
  }  
  
  public int altura(node<T> atual) {
     if(atual == null || (atual.esq == null && atual.dir == null))
       return 0;
     else {
   	if (altura(atual.esq) > altura(atual.dir))
   	   return ( 1 + altura(atual.esq) );
   	else
	   return ( 1 + altura(atual.dir) );
     }
  }
  
  public int folhas(node<T> atual) {
    if(atual == null) return 0;
    if(atual.esq == null && atual.dir == null) return 1;
    return folhas(atual.esq) + folhas(atual.dir);
  }
  
  public int contarnodes(node<T> atual) {
   if(atual == null)  return 0;
   else return ( 1 + contarnodes(atual.esq) + contarnodes(atual.dir));
  }

  public node<T> min() {
    node<T> atual = root;
    node<T> anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.esq;
    }
    return anterior;
  }

  public node<T> max() {
    node<T> atual = root;
    node<T> anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.dir;
    }
    return anterior;
  }

}