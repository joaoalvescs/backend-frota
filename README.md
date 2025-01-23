
# CRUD Frota

A empresa XWZ precisa de um sistema simples para gerenciar veículos de uma frota, classificando-os em duas categorias: carros e motos. Os veículos possuem informações comuns, mas também características específicas de cada tipo.



## Executar backend

Para instalar o projeto e suas depedências, execute:

```bash
  mvn install
```

Para executar o projeto e suas depedências, execute:

```bash
  mvn spring-boot:run
```

Para executar a aplicação em um browser, digite o endereço:

```bash
  http://localhost:8080/veiculo/all
```


## Rotas da API Veículos

#### Consultar todos veículos.

```http
  GET /veiculo/all
```

#### Criar um veículo.

```http
  POST /veiculo/atualizar/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `modelo`      | `string` | **Obrigatório**.  |
| `fabricante`      | `string` | **Obrigatório**.  |
| `ano`      | `string` | **Obrigatório**.  |
| `preço`      | `int` | **Obrigatório**.  |

#### Atualizar um veículo.

```http
  PUT /veiculo/criar/{id}
```

| Parametro | Tipo    | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `modelo`      | `string` | **Obrigatório**.  |
| `fabricante`      | `string` | **Obrigatório**.  |
| `ano`      | `string` | **Obrigatório**.  |
| `preço`      | `int` | **Obrigatório**.  |

#### Apagar um veículo.

```http
  DELETE /veiculo/apagar/${id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Obrigatório**. |


#### Filtra veículos por modelo, fabricante ou ano.

```http
  GET /veiculo/all
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `modelo`      | `string` | **Não obrigatório**.  |
| `fabricante`      | `string` | **Não obrigatório**.  |
| `ano`      | `string` | **Não obrigatório**.  |
