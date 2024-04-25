import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    public static void dfsprintGraphNode(Node currNode, GraphPrinter gp){
        currNode.visit();

        if(currNode.getPastaBackup().isEmpty() && currNode.getPastaDestino().isEmpty()){
            gp.addln(String.valueOf(currNode.getId()));
        }

        for(Node aux : currNode.getPastaBackup()){
            gp.addln(String.valueOf(currNode.getId()) + " -> " + String.valueOf(aux.getId()) + " [arrowhead=normal, color=purple, style=solid, penwidth=2]");
            if(!aux.getVisited()){
                dfsprintGraphNode(aux, gp);
            }
        }

        for(Node aux : currNode.getPastaDestino()){
            gp.addln(String.valueOf(currNode.getId()) + " -> " + String.valueOf(aux.getId()) + " [arrowhead=normal, color=orange, style=solid, penwidth=2]");
            if(!aux.getVisited()){
                dfsprintGraphNode(aux, gp);
            }
        }
    } 

    public static void bfsprintGraphNode(Node currNode, GraphPrinter gp){

        Queue<Node> queue = new LinkedList<>();

        queue.offer(currNode);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            node.visit();

            if(node.getPastaBackup().isEmpty() && node.getPastaDestino().isEmpty()){
                gp.addln(String.valueOf(node.getId()));
            }

            for(Node aux : node.getPastaDestino()){
                gp.addln(String.valueOf(node.getId()) + " -> " + String.valueOf(aux.getId()) + " [arrowhead=normal, color=blue, style=solid, penwidth=2]");
                if(!aux.getVisited()) queue.offer(aux);
            }

            for(Node aux : node.getPastaBackup()){
                gp.addln(String.valueOf(node.getId()) + " -> " + String.valueOf(aux.getId()) + " [arrowhead=normal, color=red, style=solid, penwidth=2]");
                if(!aux.getVisited()) queue.offer(aux);
            }

        }

    }
}
