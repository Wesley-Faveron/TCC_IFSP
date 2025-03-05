# CidConSP

CidConSP é um sistema para o relato e gestão de ocorrências urbanas, permitindo que cidadãos denunciem problemas como buracos nas ruas, postes com lâmpadas queimadas, áreas alagadas, árvores caídas, entre outros. Essas informações são enviadas para órgãos públicos competentes, como a prefeitura, corpo de bombeiros e polícia.

## 📋 Requisitos

### Pré-requisitos para rodar o projeto:

- **Java 21** (conforme especificado no `pom.xml`)
- **Maven** (para gerenciar dependências)
- **Banco de Dados SQL Server**
- **Spring Boot 3.4.1** (definido no `pom.xml`)

## 🚀 Configuração do Banco de Dados

Antes de iniciar o sistema, é necessário configurar o banco de dados com as tabelas e inserções iniciais.

### 📂 Configuração do `application.properties`
O projeto utiliza SQL Server como banco de dados. No arquivo `application.properties`, as configurações padrão são:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=TCC;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

> 🔴 **IMPORTANTE:** Substitua `SUA_SENHA` pela senha correta do banco antes de executar a aplicação.

### 📌 Executar Script SQL

Para que o sistema funcione corretamente, algumas tabelas e dados iniciais precisam ser criados no banco. Execute o seguinte script SQL antes de iniciar a aplicação:

```sql

-- Inserindo órgãos públicos
INSERT INTO TCC.dbo.orgao_publico (api_endpoint, contato, nome)
VALUES 
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '100', 'Violência Sexual Contra Crianças e Adolescentes'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '115', 'Serviços da Prestadora de Água e Esgoto'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '136', 'Serviço Único de Saúde'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '142', 'Comunicação para Portadores de Necessidades Especiais'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '181', 'Narcodenúncia'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '190', 'Polícia Militar'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '191', 'Polícia Rodoviária Federal'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '192', 'SAMU'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '193', 'Corpo de Bombeiros'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '194', 'Polícia Federal'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '197', 'Polícia Civil'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '198', 'Polícia Rodoviária Estadual'),
('https://7303603a-f7f9-4583-92af-86732a2eecd7-00-26zvspdd7dnbl.spock.replit.dev/upload/', '199', 'Defesa Civil');

-- Inserindo categorias de ocorrências
INSERT INTO TCC.dbo.categoria (nome, id_orgao_publico)
VALUES 
('Crime',                                 6),
('Acidente Grave',                        8),
('Incêndio',                              9),
('Acidente com Produto Químico',          9),
('Salvamento Aquático',                   9),
('Desabamento',                           9),
('Denúncia Anônima',                     11),
('Investigação Criminal',                11),
('Informação sobre Criminoso Procurado', 11),
('Desastre Natural',                     13),
('Enchente',                             13),
('Deslizamento de Terra',                13),
('Terremoto',                            13);


-- Inserindo status de ocorrência
INSERT INTO TCC.dbo.status_ocorrencia
(descricao)
VALUES
(N'cancelado'),
(N'enviado'),
(N'não enviado'),
(N'processando');

```

> 💡 **Dica:** Você pode salvar esse script como `setup.sql` e rodá-lo diretamente no SQL Server Management Studio.

## ▶️ Como executar o projeto

### 1️⃣ Clone o repositório
```sh
git clone https://github.com/seuusuario/CidConSP.git
cd CidConSP
```

### 2️⃣ Configure o banco de dados
- Certifique-se de que o **SQL Server** está instalado e rodando.
- Crie o banco de dados com o nome `TCC`.
- Execute o script SQL mencionado acima.

### 3️⃣ Configure o arquivo `application.properties`
Abra o arquivo `src/main/resources/application.properties` e ajuste as credenciais do banco de dados:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=TCC;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=SUA_SENHA
```

> ⚠️ **IMPORTANTE:** Substitua `SUA_SENHA` pela senha correta do seu banco.

### 4️⃣ Compile o projeto com Maven
```sh
mvn clean install
```

### 5️⃣ Execute a aplicação
```sh
mvn spring-boot:run
```

### 6️⃣ Acesse a aplicação no navegador
Após a execução, acesse:
```
http://localhost:8080
```

Agora seu sistema está pronto para ser utilizado! 🚀

## 📌 Funcionalidades

- 📍 Cadastro e acompanhamento de ocorrências
- 🖼️ Envio de mídias (fotos e vídeos) para complementar os relatos
- 🔄 Integração com órgãos públicos para notificação de problemas

## 📄 Licença

Este projeto é open-source e está sob a licença MIT.

