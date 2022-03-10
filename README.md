## Projeto - Sistema de Controle de Empréstimo e Devolução de Livros
### Centro Paula Souza - Fatec Ferraz - Curso de Análise e Desenvolvimento de Sistemas
### Disciplina – Laboratório de Engenharia de Software – Prof. PATRICIA SARNO MENDES

- Diego Fernandes Lins
- Vinicius Santos
- Nathan

### Objetivo
Este projeto tem como objetivo implementar uma aplicação de reservas de hotel onde um usuario conseguirá se cadastrar no hotel e reservar um quarto da sua propria casa.
### Processo de Desenvolvimento
O processo de desenvolvimento segue uma adaptação do Scrum. Cada interação tem a duração de 4 aulas. Ao final da interação o
código é avaliado pelo time junto com o professor na atividade de revisão da sprint. Em seguida a reunião de restrospectiva do sprint é
realizada pelo grupo e uma ata é publicada no Teams.
###Tecnologias
- ASP NET CORE
- Angular
- SQL Server
!()
### Backlog do produto
- REQ01 – Cadastrar novos usuarios
Como – Por uma tela de cadastro
Quando – Um novo usuario quiser se cadastrar
De maneira que – seja possível acessar o siste,a
- REQ01CT01 – Fazer Login
Dado: que o usuario já tenha se cadastrado
Quando: o usuario quiser se logar na sua conta
Então: o sistema valida os dados do usuario (user e senha) e apresenta a tela inicial

### 1. Planejamento da Sprint
Durante a fase de planejamento as funcionalidades nesta interação são selecionadas do backlog do produto.
### 2. Estratégia de desenvolvimento.
Na primeira interação a meta é criar um baseline (base de sustentação) da arquitetura do sistema a fim de definir como o código será
organizado nas próximas interações. A arquitetura se desenvolve a partir de um exame dos requisitos mais significativos (aqueles que
têm grande impacto na arquitetura do sistema) e de uma avaliação de risco. A estabilidade da arquitetura é avaliada através de um ou
mais protótipos de arquitetura. O projeto do “Sistema de Controle de Empréstimo de Livros” deve se utilizar de uma arquitetura que
permita flexibilidade na configuração do sistema de persistência (mudança do sistema de gerenciamento de banco de dados) e
manutenções na interface de usuário com poucos efeitos colaterais. A arquitetura selecionada para atender esta necessidade é a
arquitetura MVC.
A estratégia de construção e integração do software será ascendente na hierarquia de controle, ou seja, da base de dados (backend) para
a interface de interação homem máquina (frontend).
### Modelo de Domínio
![modelo de dominio](https://user-images.githubusercontent.com/14267502/84425324-bd55a580-abf7-11ea-99c7-f427b80fb7cc.png)
