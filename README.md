# Back-end projeto com Java

Inicialmente para execução da aplicação será necessário rodar o docker que sobe o RabbitMQ e PostgresSQL seguinte os seguintes passos:
``` txt
1º passo: Entrar no diretório do docker

cd .\docker\

2º passo: Executar o comando para subir o contâiner

docker compose up
```

## Main da aplicação
---
Este código é uma aplicação Java que utiliza o framework Spring Boot.

A anotação `@SpringBootApplication` indica que esta classe é a classe principal da aplicação e que deve ser utilizada para iniciar a aplicação.

A anotação `@EnableCaching` habilita o uso de cache na aplicação. Isso pode ser útil para melhorar o desempenho da aplicação, pois permite que os dados sejam armazenados em memória para serem acessados mais rapidamente em vez de buscá-los diretamente em uma fonte de dados externa, como um banco de dados.

A classe `JogoApplication` implementa a interface `CommandLineRunner`, o que significa que ela pode ser executada como uma linha de comando. Isso é possível graças ao método `run`, que é chamado pelo Spring Boot quando a aplicação é iniciada.


## Models da aplicação
---
 - ### Perfil

    É definida uma classe chamada `Perfil` que representa um perfil de usuário em algum tipo de sistema. A classe implementa a interface `Serializable`, o que significa que pode ser convertida em uma sequência de bytes para fins de persistência ou transmissão pela rede.

    A anotação `@Entity` indica que a classe Perfil é uma entidade `JPA (Java Persistence API)`. Isso significa que ela pode ser mapeada para uma tabela no banco de dados e que os objetos da classe `Perfil` podem ser persistidos no banco de dados.

    As anotações `@Data`, `@NoArgsConstructor` e `@AllArgsConstructor` são do projeto `Lombok` e geram automaticamente métodos de acesso aos campos da classe, um construtor sem argumentos e um construtor que aceita todos os argumentos, respectivamente. A anotação `@Builder` gera um `builder` para a classe `Perfil`, o que facilita a criação de objetos da classe.

    A classe `Perfil` possui quatro campos: id, apelido, bio e email. O campo id é gerado pelo banco de dados e é usado como a chave primária da tabela. O campo apelido representa o apelido do usuário. O campo bio representa uma breve descrição do usuário. O campo email é único e representa o endereço de e-mail do usuário.

## Configurações da aplicação
---
Este código é uma configuração para o framework `Spring MVC (Model-View-Controller)`.

A anotação `@Configuration` indica que esta classe é um arquivo de configuração do Spring e que os métodos nesta classe serão usados para configurar o Spring.

A anotação `@EnableWebMvc` habilita o uso do Spring MVC na aplicação.

A classe `WebSecurity` implementa a interface `WebMvcConfigurer`, o que significa que pode ser usada para personalizar a configuração do Spring MVC.

O método `addCorsMappings` é usado para configurar o suporte a `CORS (Cross-Origin Resource Sharing)`. CORS é um mecanismo que permite que uma página da web acesse recursos de outro domínio. O método `addMapping` adiciona uma configuração de mapeamento para todos os caminhos ("/**") e permite que os métodos `HTTP GET, POST e PUT` sejam usados para acessar os recursos mapeados.

## Repositories da aplicação
---
É definida uma interface chamada `PerfilRepository` que extende a interface `JpaRepository do Spring Data JPA`.

A anotação `@Repository` indica que a interface PerfilRepository é um repositório, ou seja, é um componente que é responsável por acessar uma fonte de dados e recuperar os dados que são solicitados.

A interface `PerfilRepository` é parametrizada com dois tipos: `Perfil e Long`. O primeiro tipo indica que o repositório é para a entidade Perfil e o segundo tipo indica que a chave primária da entidade Perfil é um tipo Long.

A interface PerfilRepository possui um método chamado `findByEmail`, que retorna uma lista opcional de perfis que possuem o email especificado. Pelo fato do email se tratar de um atributo do tipo unique só irá trazer um único registro, mas pelo uso do `QueryMethodName` do JPA ele implementa uma lista como default.

## Implementação do RabbitMQ
---
Este código é um consumidor de mensagens do `RabbitMQ`, um `middleware` de mensagens orientado ao evento. O código cria uma conexão com o servidor do `RabbitMQ`, cria um canal na conexão e declara uma fila chamada `"QUEUE_RESPONSE"`.

Em seguida, o código define um `callback` que é chamado toda vez que uma mensagem é entregue na fila. O `callback` extrai os dados da mensagem e os usa para fazer uma chamada `HTTP POST` para uma `API REST` local usando o template do `Spring RestTemplate`. Em seguida, o `callback` confirma a entrega da mensagem para o `RabbitMQ`.

## Services da aplicação
---

- ### Perfil

    Esta é uma interface que define um conjunto de métodos que podem ser usados para realizar operações em um repositório de perfis. A interface é usada para separar a lógica de negócios da implementação de acesso a dados.

    A interface possui os seguintes métodos:

    - `createPerfil`: cria um novo perfil a partir de um objeto PerfilDTO.

    - `updatePerfil`: atualiza um perfil existente a partir de um objeto PerfilDTO.

    - `perfilExist`: verifica se um perfil com o email especificado existe.

    - `findByEmail`: retorna uma lista de perfis com o email especificado.

    Este código é uma implementação da interface PerfilService. A implementação contém a lógica de negócios que é usada para realizar operações em um repositório de perfis.

    A classe é marcada com a anotação `@Service`, o que indica que ela é um bean do Spring e pode ser injetada em outras classes usando a anotação `@Autowired`. A classe também usa as anotações `@Cacheable` e `@CacheEvict` para habilitar o cache de perfis no repositório.

    O método `createPerfil` cria um novo perfil a partir de um objeto PerfilDTO e o salva no repositório usando o método `save` do Spring Data JPA.
   
    O método `updatePerfil` atualiza um perfil existente no repositório usando o método `findById` do Spring Data JPA para recuperar o perfil e o método `save` para salvar as alterações.
    
    O método `perfilExist` verifica se um perfil com o email especificado existe no repositório usando o método `findByEmail`.
    
    O método `findByEmail` recupera uma lista de perfis com o email especificado usando o método `findByEmail` e habilita o cache para a lista de perfis.

## Controllers da aplicação
---
Esse código é um controller de uma aplicação `Spring MVC`. O `controller` é uma classe que faz a ligação entre a camada de visualização `(front-end)` e a camada de lógica de negócios `(back-end)`.

O controller possui vários métodos mapeados para diferentes rotas `HTTP`. Cada rota é mapeada para um método específico, que é invocado quando uma requisição `HTTP` é feita para essa rota.

O `@RestController` e o `@RequestMapping` são anotações do Spring que indicam que essa classe é um controller e que todos os métodos nela presentes são mapeados para rotas que começam com `"api/jogo"`.

Cada método possui uma anotação que indica o tipo de requisição HTTP que deve ser feita para invocar o método. Por exemplo, o método `criarPerfil` é mapeado para uma rota que é acessada com uma requisição `HTTP POST`. O método `buscarPerfil` é mapeado para uma rota que é acessada com uma requisição `HTTP POST`.

Cada método também possui um parâmetro com a anotação `@RequestBody`. Isso significa que os dados enviados na requisição HTTP serão convertidos para o tipo do parâmetro do método.

Os métodos desse controller invocam os métodos de serviço `(PerfilService, PerguntaService, RegistroService e RespostaService)` para realizar as operações de negócio.