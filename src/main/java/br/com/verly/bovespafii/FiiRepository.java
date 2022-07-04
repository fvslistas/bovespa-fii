package br.com.verly.bovespafii;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface FiiRepository extends PagingAndSortingRepository<Fii, Long> {

	Fii findByCodigo(@Param("codigo") String codigo);

}