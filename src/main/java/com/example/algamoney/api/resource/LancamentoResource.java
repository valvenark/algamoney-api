package com.example.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.service.LancamentoService;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> listarPeloCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo).orElse(null);

		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		lancamentoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public Lancamento atualizar(@Valid @PathVariable Long codigo, @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalvo = lancamentoService.atualizar(codigo, lancamento);
		return lancamentoRepository.save(lancamentoSalvo);
	}

	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}