import java.util.ArrayList;
import java.util.Stack;

class ArestaPonderada {
    int v;
    int peso;

    public ArestaPonderada(int v, int peso) {
        this.v = v;
        this.peso = peso;
    }

    public int getV() {
        return v;
    }

    public int getPeso() {
        return peso;
    }
}

class Grafo {

    int V;//numero de vertices
    ArrayList<ArrayList<ArestaPonderada>> adj;  //lista adjacente

    Grafo(int v){

        this.V = v;
        adj = new ArrayList<ArrayList<ArestaPonderada>>();

        for(int i=0;i<V;i++){
            adj.add(new ArrayList<ArestaPonderada>());
        }
    }

    void addEdge(int u, int v, int peso)
    {
        ArestaPonderada node = new ArestaPonderada(v, peso);
        adj.get(u).add(node);
    }

    void ordemTopologica(int v, boolean[]visitados, Stack<Integer> stack){

        visitados[v] = true;//visita o vertice inicial

        for(int i=0;i<adj.get(v).size();i++){
            ArestaPonderada arestaPonderada = adj.get(v).get(i);

            if(!visitados[arestaPonderada.v]){//recursao para visitar os vertices a partir das arestas
                ordemTopologica(arestaPonderada.v,visitados,stack);
            }
        }

        stack.push(v);
    }

}
