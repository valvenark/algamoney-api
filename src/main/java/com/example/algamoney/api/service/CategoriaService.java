package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria categoriaSalva = this.categoriaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (categoriaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
        categoriaRepository.save(categoriaSalva);
        return categoriaRepository.save(categoriaSalva);
    }
}
