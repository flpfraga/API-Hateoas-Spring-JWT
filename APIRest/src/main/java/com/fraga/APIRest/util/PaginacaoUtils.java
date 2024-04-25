package com.fraga.APIRest.util;

import com.fraga.APIRest.data.enums.EDirecaoOrdenacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginacaoUtils {
    
    private PaginacaoUtils () throws Exception {throw new Exception("Este método não pode ser implementado");}
    
    public static Pageable gerarPaginacao(Integer pagina, Integer tamanho){
        int page = pagina == null ? 0 : pagina;
        int size = tamanho == null ? 20 : tamanho;

        if (page < 0 || size < 0){
            throw new IllegalArgumentException("Os parâmetros de paginação devem ser maiores que 0");
        }
        return PageRequest.of(page, size);
        
    }

    public static Pageable gerarPaginacaoOrdenada(Integer pagina, Integer tamanho, String ordenarPor){
        int page = pagina == null ? 0 : pagina;
        int size = tamanho == null ? 20 : tamanho;
        if (page < 0 || size < 0){
            throw new IllegalArgumentException("Os parâmetros de paginação devem ser maiores que 0");
        }

        return PageRequest.of(page, size, Sort.by(ordenarPor));
    }


    public static Pageable gerarPaginacaoOrdenada(Integer pagina, Integer tamanho, String ordenarPor, String direcao){
        int page = pagina == null ? 0 : pagina;
        int size = tamanho == null ? 20 : tamanho;
        if (page < 0 || size < 0){
            throw new IllegalArgumentException("Os parâmetros de paginação devem ser maiores que 0");
        }

        if(EDirecaoOrdenacao.ASC.getDirecao().equalsIgnoreCase(direcao)){
            return PageRequest.of(page, size, Sort.by(ordenarPor).ascending());
        }
        return PageRequest.of(page, size, Sort.by(ordenarPor).descending());
    }

}
