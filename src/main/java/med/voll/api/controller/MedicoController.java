package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key") //SpringDoc
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    //Utilizar a classe ResponseEntity, do Spring, para personalizar os retornos dos metodos de uma classe Controller;
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        //201 Requisi��o foi processada e o novo recurso foi criado
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        //Traz todos os registros
        //return repository.findAll(paginacao).map(DadosListagemMedico::new);

        //Criar um metodo para trazer somente os ativos
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page); //OK - 200 requisicao for processada com sucesso.
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); //OK - 200 requisicao for processada com sucesso.
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        //Delete fisico
        //repository.deleteById(id);

        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build(); //noContent - 204 que se refere a requisicao processada e sem conteudo
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

/* Modelo json - Post
    {
    "nome": "Rodrigo Ferreira",
    "email": "rodrigo.ferreira@voll.med",
    "crm": "123456",
    "especialidade": "ORTOPEDIA",
    "endereco": {
        "logradouro": "rua 1",
        "bairro": "bairro",
        "cep": "12345678",
        "cidade": "Brasilia",
        "uf": "DF",
        "numero": "1",
        "complemento": "complemento"
        }
    }

    Modelo de chamada - Get
    size = quantidade de registros
    page = numero da pagina
    sort = ordenacao / acrescentar desc para ordem descrecente

    Sobrepoe o default
    @PageableDefault(size = 10, sort = {"nome"})

    http://localhost:8080/medicos
    http://localhost:8080/medicos?size=1&page=1
    http://localhost:8080/medicos?sort=nome
    http://localhost:8080/medicos?sort=nome,desc

    Utilizando parametro na url, neste caso id 7
    http://localhost:8080/medicos/7


    O protocolo HTTP (Hypertext Transfer Protocol, RFC 2616)  o protocolo responsavel por fazer a
    comunicacao entre o cliente, que normalmente e um browser, e o servidor. Dessa forma, a cada
    requisicao feita pelo cliente, o servidor responde se ele obteve sucesso ou nao. Se nao
    obtiver sucesso, na maioria das vezes, a resposta do servidor sera uma sequrncia numorica
    acompanhada por uma mensagem. Se nao soubermos o que significa o codigo de resposta,
    dificilmente saberemos qual o problema que esta acontecendo, por esse motivo e muito importante
    saber quais sao os codigos HTTP e o que significam.

    Categoria de codigos
    Os codigos HTTP (ou HTTPS) possuem tres digitos, sendo que o primeiro digito significa a
    classificacao dentro das possoveis cinco categorias.

    1XX: Informativo e a solicitacao foi aceita ou o processo continua em andamento;

    2XX: Confirmacao e a acao foi concluida ou entendida;

    3XX: Redirecionamento e indica que algo mais precisa ser feito ou precisou ser feito para completar a solicitacao;

    4XX: Erro do cliente e indica que a solicitacao nao pode ser concluida ou contem a sintaxe incorreta;

    5XX: Erro no servidor e o servidor falhou ao concluir a solicitacao.

    Principais codigos de erro
    Como dito anteriormente, conhecer os principais codigos de erro HTTP vai te ajudar a identificar problemas
    em suas aplicacoes, alem de permitir que voce entenda melhor a comunicacao do seu navegador com o servidor
    da aplicacao que esta tentando acessar.

    Error 403
    O codigo 403 e o erro Proibido. Significa que o servidor entendeu a requisicao do cliente, mas se recusa
    a processa-la, pois o cliente nao possui autorizacao para isso.

    Error 404
    Quando voce digita uma URL e recebe a mensagem Error 404, significa que essa URL nao te levou a lugar nenhum.
    Pode ser que a aplicacao nao exista mais, a URL mudou ou voce digitou a URL errada.

    Error 500
    E um erro menos comum, mas de vez em quando ele aparece. Esse erro significa que ha um problema com alguma
    das bases que faz uma aplicacao rodar. Esse erro pode ser, basicamente, no servidor que mantem a aplicacao
    no ar ou na comunicacao com o sistema de arquivos, que fornece a infraestrutura para a aplicacao.

    Error 503
    O erro 503 significa que o servico acessado esta temporariamente indisponivel. Causas comuns sao um servidor
    em manutencao ou sobrecarregado. Ataques maliciosos, como o DDoS, causam bastante esse problema.

    Uma dica final: dificilmente conseguimos guardar em nossa cabeca o que cada codigo significa, portanto,
    existem sites na internet que possuem todos os codigos e os significados para que possamos consultar
    quando necessario.

    Existem dois sites bem conhecidos e utilizados por pessoas desenvolvedoras, um para cada preferencia:
    se voce gosta de gatos, pode utilizar o HTTP Cats; ja, se prefere cachorros, utilize o HTTP Dogs.
 */
}
