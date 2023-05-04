package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.http.PedidoClient;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import br.com.alurafood.pagamentos.util.StatusRequisicaoEnum;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PedidoClient pedido;

    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        PagamentoDto dto = modelMapper.map(pagamento, PagamentoDto.class);
        dto.setItens(pedido.obterItensDoPedido(pagamento.getPedidoId()).getItens());
        return dto;
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id, StatusRequisicaoEnum statusRequisicao){
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        pagamento.setStatus(getStatusPagamento(statusRequisicao));
        repository.save(pagamento);

        if (Status.CONFIRMADO.equals(pagamento.getStatus())) {
            pedido.atualizaPagamento(pagamento.getPedidoId());
        }
    }

    private Status getStatusPagamento(StatusRequisicaoEnum statusRequisicao) {
        if (statusRequisicao != null && StatusRequisicaoEnum.SUCESSO.equals(statusRequisicao)) {
            return Status.CONFIRMADO;
        } else {
            return Status.CONFIRMADO_SEM_INTEGRACAO;
        }
    }


}
