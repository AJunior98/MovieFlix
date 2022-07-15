# Projeto TechMovies - Implementação de Dominio, ORM e consultas personalizadas no banco de dados

O sistema MovieFlix consiste em um banco de filmes, os quais podem ser listados e avaliados pelos usuários. Usuários podem ser visitantes (VISITOR) e membros (MEMBER). Apenas usuários membros podem inserir avaliações no sistema.

Ao acessar o sistema, o usuário deve fazer seu login. Apenas usuários logados podem navegar nos filmes. Logo após fazer o login, o usuário vai para a listagem de filmes, que mostra os filmes de forma paginada, ordenados alfabeticamente por título. O usuário pode filtrar os filmes por gênero.

Ao selecionar um filme da listagem, é mostrada uma página de detalhes, onde é possível ver todas informações do filme, e também suas avaliações. Se o usuário for MEMBER, ele pode ainda registrar uma avaliação nessa tela.

Um usuário possui nome, email e senha, sendo que o email é seu nome de usuário. Cada filme possui um título, subtítulo, uma imagem, ano de lançamento, sinopse, e um gênero. Os usuários membros podem registrar avaliações para os filmes. Um mesmo usuário membro pode deixar mais de uma avaliação para o mesmo filme.

## Modelo conceitual

![image](https://user-images.githubusercontent.com/100853329/178622738-80b68bb6-cf33-44db-8178-14513b89022b.png)

# Casos de uso
## Efetuar login
- [IN] O usuário anônimo informa seu email e senha
- [OUT] O sistema informa um token válido
## Listar filmes
- [OUT] O sistema apresenta uma listagem dos nomes de todos gêneros, bem como uma listagem paginada com título, subtítulo, ano e imagem dos filmes, ordenada alfabeticamente por título.
- [IN] O usuário visitante ou membro seleciona, opcionalmente, um gênero.
- [OUT] O sistema apresenta a listagem atualizada, restringindo somente ao gênero selecionado.
## Visualizar detalhes do filme
- [IN] O usuário visitante ou membro seleciona um filme
- [OUT] O sistema informa título, subtítulo, ano, imagem e sinopse do filme, e também uma listagem dos textos das avaliações daquele filme juntamente com nome do usuário que fez cada avaliação.
- [IN] O usuário membro informa, opcionalmente, um texto para avaliação do filme.
- [OUT] O sistema apresenta os dados atualizados, já aparecendo também a avaliação feita pelo usuário.

# Validação
- O sistema apresenta uma mensagem de que não é permitido texto vazio na avaliação 

## Dados utilizados no projeto
Seed utilizado no projeto

```
INSERT INTO tb_user (name, email, password) VALUES ('Bob', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_genre (name) VALUES ('Comédia');
INSERT INTO tb_genre (name) VALUES ('Terror');
INSERT INTO tb_genre (name) VALUES ('Drama');

INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Bob Esponja', 'O Incrível Resgate', 2020,  'https://image.tmdb.org/t/p/w533_and_h300_bestv2/wu1uilmhM4TdluKi2ytfz8gidHf.jpg', 'Onde está Gary? Segundo Bob Esponja, Gary foi "caracolstrado" pelo temível Rei Poseidon e levado para a cidade perdida de Atlantic City. Junto a Patrick Estrela, ele sai em uma missão de resgate ao querido amigo, e nesta jornada os dois vão conhecer novos personagens e viver inimagináveis aventuras.', 1);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('O Orfanato', null, 2007, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/2AlVaQDH67RgulE2AqXBSPr2POF.jpg', 'Laura (Belén Rueda) passou os anos mais felizes de sua vida em um orfanato, onde recebeu os cuidados de uma equipe e de outros companheiros órfãos, a quem considerava como se fossem seus irmãos e irmãs verdadeiros. Agora, 30 anos depois, ela retornou ao local com seu marido Carlos (Fernando Cayo) e seu filho Simón (Roger Príncep), de 7 anos. Ela deseja restaurar e reabrir o orfanato, que está abandonado há vários anos. O local logo desperta a imaginação de Simón, que passa a criar contos fantásticos. Entretanto à medida que os contos ficam mais estranhos Laura começa a desconfiar que há algo à espreita na casa.', 2);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('O Labirinto do Fauno', null, 2006, 'https://image.tmdb.org/t/p/w500_and_h282_face/oXMfT5OM6HAgQ9sGANB8cs1ifCG.jpg', 'Em 1944, na Espanha, a jovem Ofélia e sua mãe doente chegam ao posto do novo marido de sua mãe, um sádico oficial do exército que está tentando reprimir uma guerrilheira. Enquanto explorava um labirinto antigo, Ofélia encontra o Pan fauno, que diz que a menina é uma lendária princesa perdida e que ela precisa completar três tarefas perigosas a fim de se tornar imortal.', 3);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Your Name', null, 2016, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/wqZapHpXyZEaCkpsLVszmEQcDIy.jpg', 'Mitsuha é a filha do prefeito de uma pequena cidade, mas sonha em tentar a sorte em Tóquio. Taki trabalha em um restaurante em Tóquio e deseja largar o seu emprego. Os dois não se conhecem, mas estão conectados pelas imagens de seus sonhos.', 3);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Código de Conduta', null , 2009, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/mwlLjL3jTDmTdLWe2PyUVqYQTuK.jpg', 'Quando um dos suspeitos do assassinato de sua mulher e filha é solto, Clyde quer vingança e decide fazer justiça com as próprias mãos. Clyde é preso e dentro da cadeia organiza uma matança para desmascarar o sistema judicial corrupto.', 3);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('A Voz do Silêncio', 'Koe no Katachi', 2016, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/5lAMQMWpXMsirvtLLvW7cJgEPkU.jpg', 'Nishimiya Shouko é uma estudante com deficiência auditiva. Durante o ensino fundamental, após se transferir para uma nova escola, Shouko passa a ser alvo de bullying e em pouco tempo precisa se transferir. O que ela não esperava é que alguns anos depois, Ishida Shouya, um dos valentões que tanto a fez sofrer no passado surgisse de novo em sua vida com um novo propósito.', 3);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Kingsman', 'Serviço Secreto', 2014, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/qzUIOTk0E3F1zjvYjcBRTKUTgf9.jpg','Eggsy (Taron Egerton) é um jovem com problemas de disciplina que parece perto de se tornar um criminoso. Determinado dia, ele entra em contato com Harry (Colin Firth), que lhe apresenta à agência de espionagem Kingsman. O jovem se une a um time de recrutas em busca de uma vaga na agência. Ao mesmo tempo, Harry tenta impedir a ascensão do vilão Valentine (Samuel L. Jackson). Adaptação da série de quadrinhos criada por Mark Millar e Dave Gibbons.',1);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Sonic', 'O Filme', 2020, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/diFNHa3SXaGSSFovGatNWxLz2tn.jpg','Sonic, o porco-espinho azul mais famoso do mundo, se junta com os seus amigos para derrotar o terrível Doutor Eggman, um cientista louco que planeja dominar o mundo, e o Doutor Robotnik, responsável por aprisionar animais inocentes em robôs.',1);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Uma Noite de Crime', 'Anarquia', 2014, 'https://image.tmdb.org/t/p/w500_and_h282_face/ecD9hT8odHzFCDeGDy4N2IKh0LN.jpg', 'O governo dos Estados Unidos sanciona uma lei em que os assassinatos são permitidos durante uma noite, para que os cidadãos liberem seus instintos violentos. Cinco desconhecidos se unem para tentar sobreviver a essa verdadeira noite de terror.', 2);
INSERT INTO tb_movie (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('O Segredo da Cabana', null, 2012, 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/5iiVfPS6LsAqmVQVOzhyCHhCFgU.jpg', 'Cinco amigos fazem uma pausa em uma cabana remota, onde conseguem mais do que esperavam, descobrindo a verdade atrás da cabana na floresta.', 2);

INSERT INTO tb_review (text, movie_id, user_id) VALUES ('Meh, filme OK', 1, 1);
INSERT INTO tb_review (text, movie_id, user_id) VALUES ('Gostei e recomendo!', 1, 1);
INSERT INTO tb_review (text, movie_id, user_id) VALUES ('Que Filme!!!', 2, 1);

```
## Arquivos de configuração utilizados
## application.properties
Aqui configuramos em qual ambiente queremos rodar a aplicação (teste, homologação ou produção) e também é feito a atribuição do "false" para que o acesso ao banco seja finalizado na camada de serviço entregando somente a resposta para o controlador Rest.
```
spring.profiles.active=test

spring.jpa.open-in-view=false
```
## application-test.properties
Configuração do banco H2
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
## application-dev.properties
Configuração do banco para homologação e produção (Postgresql)
```
spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql
spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.url=jdbc:postgresql://localhost:5432/dspesquisa
spring.datasource.username=postgres
spring.datasource.password=1234567

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```
## application-prod.properties
Configuração do banco de produção

```
spring.datasource.url=${DATABASE_URL}

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```
## Postman
Abaixo a collection utilizada no Postman caso deseje testar as funcionalidades:
https://www.getpostman.com/collections/77944912e2cd2a288c68

