package br.com.onsservice.service;

import br.com.onsservice.domain.Empresa;
import br.com.onsservice.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public void salvarEmpresa(Empresa empresa) {
        empresaRepository.saveAndFlush(empresa);
    }

    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado"));
    }

    public void excluirEmpresaPorId(Long id) {
        empresaRepository.deleteById(id);
    }

    public void atualizarEmpresaPorId(Long id, Empresa empresa) {
        Empresa empresaEntity = empresaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empresa não encontrada")
        );
        Empresa empresaAtualizada = Empresa.builder()
                .id(empresaEntity.getId())
                .usuaria(empresa.getUsuaria() != null ? empresa.getUsuaria() : empresaEntity.getUsuaria())
                .cnpj(empresa.getCnpj() != null ? empresa.getCnpj() : empresaEntity.getCnpj())
                .codigoUsuaria(empresa.getCodigoUsuaria() != null ? empresa.getCodigoUsuaria() : empresaEntity.getCodigoUsuaria())
                .tributos(empresa.getTributos() >= 0 ? empresa.getTributos() : empresaEntity.getTributos())
                .valorTotal(empresa.getValorTotal() != null ? empresa.getValorTotal() : empresaEntity.getValorTotal())
        .build();

        this.empresaRepository.saveAndFlush(empresaAtualizada);
    }
}
