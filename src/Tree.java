import java.util.ArrayList;

public class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() { this(null); }

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }

    public boolean isEmpty() { return root == null; }

    public boolean insert(Node node) {
        if(isEmpty()) {
            this.root = node;
            return true;
        } else {
            return insert(root, node);
        }
    }

    private boolean insert(Node root, Node node) {
        if(root.getDestino() != null && root.getDestino().equalsIgnoreCase(node.getOrigem())) {
            root.addDestino(node);
            return true;
        } else if(root.getBackup() != null && root.getBackup().equalsIgnoreCase(node.getOrigem())) {
            root.addBackup(node);
            return true;
        } else {
            ArrayList<Node> destinos = root.getPastaDestino();
            ArrayList<Node> backups = root.getPastaBackup();
            for(Node destino : destinos){
                insert(destino, node);
            }

            for(Node backup : backups){
                insert(backup, node);
            }

            return true;
        }

//        return false;
    }
}
