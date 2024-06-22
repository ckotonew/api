package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

//O Record e um recurso que permite representar uma classe imutavel, contendo apenas atributos,
//construtor e metodos de leitura, de uma maneira muito simples e enxuta.

public record DadosCadastroMedico(
        @NotBlank(message = "Nome � obrigat�rio")
        String nome,

        @NotBlank(message = "Email � obrigat�rio")
        @Email
        String email,

        @NotBlank(message = "Telefone � obrigat�rio")
        String telefone,

        @NotBlank(message = "CRM � obrigat�rio")
        @Pattern(regexp = "\\d{4,6}") //Digito de 4 a 6
        String crm,

        @NotNull(message = "Especialidade � obrigat�ria")
        Especialidade especialidade,

        @NotNull(message = "Endere�o � obrigat�rio")
        @Valid //Para validar outro DTO
        DadosEndereco endereco) {
}
