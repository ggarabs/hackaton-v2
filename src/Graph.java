public class Graph {
    public static void dfsprintGraphNode(Node currNode, GraphPrinter gp){
        currNode.visit();

        if(currNode.getPastaBackup().isEmpty() && currNode.getPastaDestino().isEmpty()){
            gp.addln(String.valueOf(currNode.getId()));
        }

        for(Node aux : currNode.getPastaBackup()){
            gp.addln(String.valueOf(currNode.getId()) + " -> " + String.valueOf(aux.getId()));
            if(!aux.getVisited()){
                dfsprintGraphNode(aux, gp);
            }
        }

        for(Node aux : currNode.getPastaDestino()){
            gp.addln(String.valueOf(currNode.getId()) + " -> " + String.valueOf(aux.getId()));
            if(!aux.getVisited()){
                dfsprintGraphNode(aux, gp);
            }
        }
    }
}
