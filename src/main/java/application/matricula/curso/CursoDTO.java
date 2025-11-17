package application.curso;

import java.time.LocalDateTime;

public record CursoDTO(long id, String nome, String descricao, int cargaHoraria, String status, LocalDateTime dataCriacao) {
    public CursoDTO(Curso dados) {
        this(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getCargaHoraria(), dados.getStatus(), dados.getDataCriacao());
    }    
}
