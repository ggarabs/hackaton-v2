import java.util.ArrayList;

public class Node {
    private int id;
    private String nome;
    private String origem;
    private String destino;
    private String backup;
    private boolean visited;

    private ArrayList<Node> pastaOrigem;
    private ArrayList<Node> pastaDestino;
    private ArrayList<Node> pastaBackup;

    public Node(int ID, String nome, String origem, String destino, String backup) {
        this.id = ID;
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.backup = backup;
        this.visited = false;
        this.pastaDestino = new ArrayList<>();
        this.pastaBackup = new ArrayList<>();
        this.pastaOrigem = new ArrayList<>();
    }

    public Node() { this(-1, null, null, null, null); }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getOrigem() {
        return this.origem;
    }

    public String getDestino() {
        return this.destino;
    }

    public String getBackup() {
        return this.backup;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setId(int newID) {
        this.id = newID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public void visit() {
        this.visited = true;
    }

    public void addOrigem(Node node) {
        this.pastaOrigem.add(node);
    }

    public void addDestino(Node node) {
        this.pastaDestino.add(node);
    }

    public void addBackup(Node node) {
        this.pastaBackup.add(node);
    }

    public ArrayList<Node> getPastaOrigem() {
        return pastaOrigem;
    }

    public ArrayList<Node> getPastaDestino() {
        return pastaDestino;
    }

    public ArrayList<Node> getPastaBackup() {
        return pastaBackup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + id + "\n")
          .append("Nome: " + nome + "\n")
          .append("Origem: " + origem + "\n")
          .append("Destino: " + destino + "\n")
          .append("Backup: " + backup + "\n");
        return sb.toString();
    }
}
