package med.voll.api.domain.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
/*
    O padrao DTO (Data Transfer Object) e um padrao de arquitetura que era bastante utilizado antigamente
    em aplicacaes Java distribuidas (arquitetura cliente/servidor) para representar os dados que eram
    enviados e recebidos entre as aplicacoes cliente e servidor.

    O padrao DTO pode (e deve) ser utilizado quando nao queremos expor todos os atributos de alguma entidade
    do nosso projeto, situacao igual a dos salarios dos funcionarios mostrado no exemplo de codigo anterior.
    Alem disso, com a flexibilidade e a opcao de filtrar quais dados serao transmitidos, podemos poupar
    tempo de processamento.
 */
}
