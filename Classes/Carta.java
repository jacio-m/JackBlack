package Classes;

public class Carta {
        String valor; 
        String tipo; 

        Carta(String valor, String tipo){ //construtor padrão de objetos como vimos em sala (quando criado (instanciado) a carta, vai ser logo atribuido um valor e um tipo à ela)
            this.valor = valor;
            this.tipo = tipo;
        }

        public String toString(){ //para mostrar nossas cartas, usamos esse metodo para sobrescrever os objetos (pq quando vc tenta mostrar um objeto diretamente, ele mostra um monte de coisa e informacoes importantes do programa)
            return valor + " de " + tipo;
        }

        public int getValor(){
            if("AJQK".contains(valor)){
                if (valor == "A"){
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(valor);      
        }

        public boolean eAs(){
            return valor == "A";
        }

        public String getLocalImagem(){
            return "./cartas/" + toString() + ".png";
        }
    }