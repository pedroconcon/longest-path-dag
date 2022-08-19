import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner sc = new Scanner(new FileReader("test/teste-1/teste.txt"));
        //Scanner sc = new Scanner(new FileReader("test/teste-2/teste2.txt"));
        Scanner sc = new Scanner(new FileReader("test/teste-3/teste3.txt"));

        String qtdVertices = sc.nextLine();
        Grafo grafo = new Grafo(Integer.valueOf(qtdVertices));

        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            String[] datas = line.split(" ");

            grafo.addEdge(Integer.valueOf(datas[0]),Integer.valueOf(datas[1]),Integer.valueOf(datas[2]));
        }

        Stack<Integer> pilha = new Stack<>();
        boolean[] visitados = new boolean[grafo.V];

        for(int i=0;i < grafo.V;i++){
            if(visitados[i] == false) grafo.ordemTopologica(i,visitados,pilha);
        }

        int[] dist = new int[grafo.V];
        List<List<Integer>> caminhos = new ArrayList<>(grafo.V);
        for (int i = 0; i < grafo.V; i++)  {
            caminhos.add(new ArrayList<>());
        }

        while (pilha.isEmpty() == false){

            int u = pilha.peek();
            pilha.pop();

            for (int i = 0; i<grafo.adj.get(u).size(); i++){
                ArestaPonderada node = grafo.adj.get(u).get(i);
                if (dist[node.getV()] < (dist[u] + node.getPeso())) {

                    dist[node.getV()] = dist[u] + node.getPeso();
                    listarCaminhos(caminhos, u, node);
                }
            }
        }

        List<Integer> list = Arrays.stream(dist).boxed().collect(Collectors.toList());
        Integer maiorCaminho = Collections.max(list);
        Integer indexMaiorCaminho = list.indexOf(maiorCaminho);

        System.out.println();
        System.out.println("#############################################################");
        System.out.println("O maior caminho desse grafo é: " + maiorCaminho);
        System.out.print("A sequencia do caminho é: ");
        for (int i=0;i<caminhos.get(indexMaiorCaminho).size();i++) {
            System.out.print(caminhos.get(indexMaiorCaminho).get(i));
            if(i < caminhos.get(indexMaiorCaminho).size() - 1){
                System.out.print("=>");
            }
        }
        System.out.println();
        System.out.println("#############################################################");
        System.out.println();
    }

    private static void listarCaminhos(List<List<Integer>> caminhos, int u, ArestaPonderada node) {
        if(caminhos.get(u).isEmpty()){
            caminhos.get(node.getV()).add(u);
            caminhos.get(node.getV()).add(node.getV());
        }else{
            caminhos.get(node.getV()).removeAll(caminhos.get(node.getV()));
            caminhos.get(node.getV()).addAll(caminhos.get(u));
            caminhos.get(node.getV()).add(node.getV());
        }
    }
}
