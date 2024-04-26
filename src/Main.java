import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        readFile("./../baseparateste.csv");
    }

    public static void readFile(String filePath) {
        // Try reading the file path
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner scanner = new Scanner(fileReader);
            Map<String, ArrayList<Node>> mapBackup = new HashMap<>();
            Map<String, ArrayList<Node>> mapDestiny = new HashMap<>();
            ArrayList<Node> auxList = new ArrayList<>();
            Node initialNode = null;

            // Eliminating the csv's header
            String line;
            if (scanner.hasNextLine()) {
                line = scanner.nextLine().strip();
            }

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().strip().toUpperCase().replace(";;", "; ;").concat(" ");
                System.out.println(line);
                String[] tokens = line.split(";");
                
                Node currNode = new Node(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4]);

                auxList.add(currNode);

                if (mapDestiny.containsKey(tokens[3])) {
                    ArrayList<Node> list = mapDestiny.get(tokens[3]);
                    list.add(currNode);
                } else {
                    ArrayList<Node> newList = new ArrayList<>();
                    newList.add(currNode);
                    mapDestiny.put(tokens[3], newList);
                }

                if (mapBackup.containsKey(tokens[4].strip())) {
                    ArrayList<Node> list = mapBackup.get(tokens[4].strip());
                    list.add(currNode);
                } else {
                    ArrayList<Node> newList = new ArrayList<>();
                    newList.add(currNode);
                    mapBackup.put(tokens[4].strip(), newList);
                }
            }

            for (Node currNode : auxList) {
                if (initialNode == null)
                    initialNode = currNode;

                if (mapBackup.get(currNode.getOrigem()) != null) {
                    for (Node parentNode : mapBackup.get(currNode.getOrigem())) {
                        parentNode.addBackup(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }

                if (mapDestiny.get(currNode.getOrigem()) != null) {
                    for (Node parentNode : mapDestiny.get(currNode.getOrigem())) {
                        parentNode.addDestino(currNode);
                        currNode.addOrigem(parentNode);
                    }
                }
            }

            for (Node aux : auxList) {
                System.out.printf("ID: %d\n", aux.getId());
                System.out.printf("Nome: %s\n", aux.getNome());
                if (!aux.getPastaOrigem().isEmpty()) {
                    System.out.printf("Nós da origem: ");
                    for (Node sons : aux.getPastaOrigem())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                if (!aux.getPastaDestino().isEmpty()) {
                    System.out.printf("Nós destino: ");
                    for (Node sons : aux.getPastaDestino())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                if (!aux.getPastaBackup().isEmpty()) {
                    System.out.printf("Nós de backup: ");
                    for (Node sons : aux.getPastaBackup())
                        System.out.printf("ID: %d; ", sons.getId());
                }
                System.out.println();
                System.out.println();
            }

            scanner.close();

            GraphPrinter gp = new GraphPrinter("AdjGraph");

            gp.addln("concentrate=true");
            gp.addln("edge[arrowhead=normal]");
            gp.addln("node[shape=box]");

            for(Node aux : auxList){
                if(!aux.getVisited()) Graph.dfsprintGraphNode(aux, gp);
            }

            gp.print();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
