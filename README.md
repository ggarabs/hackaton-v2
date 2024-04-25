# Desafio Hackathon 2024 - Mackenzie

# Equipe
Nome: dream team include

Código: HIPER-155

## Tópicos
- [Historia](#história)
    - [Modelo de Dados](#modelo-de-dados)
    - [Exemplo de Dados](#exemplo-de-dados)
- [Nossa Solução](#nossa-solução)
- [Classes](#classes)
    - [Main](#main)
    - [Node](#node)
    - [GraphJava](#graphjava)
    - [GraphPrinterJava](#graphprinterjava)
    

## História
Eu enquanto Colaborador H Hiperstream, necessito de um sistema que com base no modelo de dados sugerido seja capaz de devolver um desenho de diagrama que mostre o Fluxo da informação com base em duas pastas diferentes. 
O Diagrama não precisa necessariamente seguir o do exemplo, 

### Modelo de Dados

| Campo | Descrição |
| --- | --- |
| ID | Identificador do cadastro |
| Nome | Nome da Aplicação |
| PastaOrigem | Pasta onde a aplicação espera a chegada de um arquivo |
| PasteDestino | Pasta onde a aplicação gera seu resulta após o processamento |
| PastaBackup | Local onde a aplicação copia o arquivo de origem  assim que processar |
#### Exemplo de dados:

| ID | Nome | PastaOrigem | PastaDestino | PastaBackup |
| --- | --- | --- | --- | --- |
| 1 | Aplicacao1 | C:\EntradaA\ | C:\Entrada2\ | C:\Guarda\ |
| 2 | Aplicacao2 | C:\Entrada2\ | C:\Aplicacao2Dest\ | C:\Voa |
| 3 | Aplicacao3 | C:\Voa |  | C:\Guarda\ |
| 4 | Aplicacao4 | C:\Entrada2\ |  | C:\Guarda\ |
| 5 | Aplicacao5 | C:\monitorada\3k\ | C:\monitorada\ds\ | C:\monitorada\Gd\ |
| 6 | Aplicacao6 | C:\Aplicacao2Dest\ |  |  |

## Nossa solução
Para resolver o desafio, o grupo desenvolveu um programa em Java que lê um arquivo CSV e transforma cada linha da base de dados em um nó. Cada nó possui seus atributos como ID, nome, pasta origem, pasta destino e pasta backup e, a partir disso, um grafo é criado, tendo as conexões entre os nós geradas pelos relacionamentos entre as pastas de origem e de backup. Por fim, usamos a biblioteca graphviz para gerar uma representação visual do grafo criado, representando a organização dos dados.

## Classes

### Main
Representa a classe principal do projeto. É onde ocorre a leitura do arquivo CSV e a criação do grafo a partir da base de dados disponibilizada no arquivo lido.

### Node
Representa um nó da estrutura do grafo. Contém os atributos ID, nome, origem, destino e backup, juntamente com métodos para adicionar conexões a outros nós.

### Graph.java


### GraphPrinter.java

## Explicando o código (Class Main)

### main
Inicia a execução do programa, chama o método 'readFile' que recebe o arquivo csv como parâmetro.

```
public static void main(String[] args) {
    readFile("./../baseparateste.csv");
}
```

### readFile
É o método responsável por ler o arquivo CSV pelo caminho 'filePath' e processar os seus dados

#### Processamento do CSV
O arquivo CSV é lido através de um 'FileReader', um 'Scanner' percorre cada conteúdo do arquivo, dois mapas são criados para armazenar os nós de backup e destino, uma lista auxiliar 'auxList' é criada para armazenar temporariamente os nós lidos do arquivo, a variável 'initialNode' é inicializada como nula e o cabeçalho do arquivo é ignorado.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/33f79228-4cff-40e9-a661-3c52d412ebde)

#### Processamento das linhas
Loop para processar cada linha do arquivo. Cada linha é lida, processada e os dados são armazenados em objetos Node.
Os nós são adicionados às listas de backup e destino correspondentes nos mapas.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/2752e5ab-d3f6-41d8-9d50-73bc32a2970e)

#### Conexão entre os nós
Percorre os nós na lista auxiliar e conecta os nós de backup e destino aos seus respectivos nós pais, conforme especificado nos mapas.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/90a1a39c-cb9f-4853-89a5-a56b0372fe71)

#### Impressão dos nós
Para testar se o grafo estava criando as conexões corretamente, este bloco imprime os detalhes de cada nó, incluindo seu ID, nome e nós de origem, destino e backup associados.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/86a342e0-8fc3-4f73-b2b9-d5407f08683d)

#### Mensagem de erro
Se o arquivo não for encontrado, apresenta uma mensagem de erro representando a exceção 'FileNotFoundException'.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/333d6d1d-a2d9-48c9-93c4-212729a9a282)

## Explicando o código (Class Tree) -- (deixando só para garantir, mas acho que n vms precisar mais dela)

### Primeiros passos
No trecho abaixo, temos a declaração do atributo root de tipo Node. Depois, ocorre a criação de dois construtores da classe, um recebe o root como parâmetro e retorna esse root e o outro não recebe nada e retorna NULL. Em seguida, temos a criação do método GET (para consulta) e do método SET (para alterar) para o atributo root. Por fim, temos o método 'isEmpty' que serve para retornar 'root ==  null' caso a árvore esteja vazia.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/aff5fc38-ae54-4b45-93a1-e184de3c79c1)

### insert(Node node)
Verifica se a árvore está vazia. Se estiver, define o nó inserido como raiz. Se não estiver, chama o método 'insert(Node root, Node node)' para iniciar a inserção a partir da raiz.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/a9953d97-79d0-4543-a3c0-1650e80c6825)

### insert(Node root, Node node)
Tenta inserir o novo nó de duas formas: Se o destino do nó raiz for igual à origem do novo nó, então o novo nó é adicionado como um destino. Se o backup do nó raiz for igual à origem do novo nó o novo nó é adicionado como um backup. Se não, o percorre recursivamente as pastas de destino e de backup da raiz e chama-se novamente para cada nó filho, até encontrar uma conexão ou percorrer toda a árvore.

## Explicando o código (Class Node)

### Atributos da classe
No trecho abaixo, são declarados os métodos id (tipo int), nome (tipo String), origem (tipo String), destino (tipo String) e backup (tipo String) que representam as características do nó. Também criamos três listas de Node para representar as conexões entre os nós: pastaOrigem, pastaDestino e pastaBackup.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/24e307d9-b694-40e4-8bc1-60609ae0d755)

### Construtores da classe
Temos dois construtores: um com parâmetros e um padrão. O construtor com parâmetros recebe os atributos ID, nome, origem, destino e backup e retorna esses atributos e as listas pastaDestino, pastaBackup e pastaOrigem. Já o construtor sem parâmetros (padrão) chama o construtor com parâmetros com valores padrão.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/3b3e49e7-3ba7-4550-b0e0-602e9f166253)

### Getters e Setters
Em seguida, temos os métodos GET e SET de todos os atributos da classe. Eles servem, respectivamente, para consultar e atualizar os atributos declarados.

```java
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

    public boolean getVisited(){
        return this.visited;
    }

    public void setId(int newID){
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

    public ArrayList<Node> getPastaOrigem(){
        return pastaOrigem;
    }

    public ArrayList<Node> getPastaDestino() {
        return pastaDestino;
    }

    public ArrayList<Node> getPastaBackup() {
        return pastaBackup;
    }
```

### Adicionando conexões
Depois, são criados os métodos para adicionar nós às listas de conexões.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/8132ebff-51b3-4d0f-b59c-0533b207c09d)

### Obtendo as listas de conexões
Então, chegamos aos métodos para obter as listas de conexões.

![image](https://github.com/jcampolim/hackaton-dream-team/assets/93957694/e044e1ad-cc95-4a17-8c2d-cd0198f178f0)

### Método toString
Sobrescreve o método toString para trazer uma representação em forma de String do objeto Node.

```java
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
```