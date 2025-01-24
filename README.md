# Sistema de Gerenciamento de Inventário

Este é um sistema para gerenciar itens de um setor de uma universidade, utilizando Java, SQLite e JavaFX. Ele permite adicionar, excluir, buscar e movimentar itens, além de visualizar dados na interface gráfica.

## Requisitos

Antes de compilar e executar o projeto, certifique-se de que você possui os seguintes itens instalados:

- **Java JDK 17** ou superior
- **JavaFX SDK** (compatível com a versão do JDK)
- **SQLite JDBC Driver**
- Um IDE como IntelliJ IDEA, Eclipse ou VS Code com suporte para Java

## Estrutura do Projeto

O projeto está organizado nos seguintes pacotes:

- **`br.ufrn.imd.modelo`**: Classes que representam os dados do sistema.
- **`br.ufrn.imd.dao`**: Classes responsáveis por acessar e manipular os dados no banco SQLite.
- **`br.ufrn.imd.visao`**: Arquivos FXML e classes relacionadas à interface gráfica.
- **`br.ufrn.imd.controlador`**: Controladores responsáveis por gerenciar a interação entre a interface gráfica e a lógica do sistema.

## Como Compilar

### Usando a Linha de Comando

1. **Baixe o JavaFX SDK**:
    - Acesse o [site oficial do JavaFX](https://gluonhq.com/products/javafx/) e baixe o SDK.
    - Extraia o SDK para uma pasta no seu sistema.

2. **Compile o Código-Fonte**:
    - Navegue até o diretório raiz do projeto onde estão os arquivos `.java`.
    - Execute o seguinte comando (substitua `<path-to-javafx-lib>` pelo caminho da pasta `lib` do JavaFX SDK):

      ```bash
      javac --module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml -d out $(find . -name "*.java")
      ```

3. **Execute o Programa**:
    - Use o seguinte comando para iniciar a aplicação:

      ```bash
      java --module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml -cp out br.ufrn.imd.visao.MainApp
      ```

### Usando uma IDE (IntelliJ IDEA ou Eclipse)

1. **Abra o Projeto**:
    - Importe o projeto na IDE de sua escolha.

2. **Configure o JavaFX**:
    - Adicione o caminho para o JavaFX SDK na configuração de execução do projeto:
        - Em IntelliJ IDEA: Vá para **Run > Edit Configurations**, adicione `--module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml` no campo de opções de VM.
        - Em Eclipse: Configure o caminho do JavaFX na aba de argumentos da configuração de execução.

3. **Execute a Classe Principal**:
    - Certifique-se de que a classe principal configurada é `br.ufrn.imd.visao.MainApp` e execute o projeto.

## Banco de Dados SQLite

O sistema utiliza um banco de dados SQLite para armazenar os dados dos itens e setores. Certifique-se de que o arquivo de banco de dados está localizado no diretório correto, conforme especificado no código do DAO.

## Funcionalidades

- **Adicionar/Excluir Itens**: Gerencie os itens cadastrados no sistema.
- **Buscar Itens**: Localize itens por setor, sala, tipo ou nome.
- **Movimentar Itens**: Altere a localização de itens entre salas ou setores.
- **Interface Gráfica**: Interaja com o sistema através de uma interface amigável feita com JavaFX.

## Problemas Comuns

- **Erro ao Carregar FXML**: Certifique-se de que os arquivos FXML estão no local correto e incluídos no caminho de compilação.
- **Erro com JavaFX**: Confirme que o JavaFX SDK está configurado corretamente na sua IDE ou linha de comando.



## Drive com video e relatatório

- **LINK**: https://drive.google.com/drive/folders/1uPrT3nDxzpYFoEup-DkBSHKOU2Kli-23?usp=sharing
- **Participantes**: Vinicius Barbosa Ventura Mergulhão



