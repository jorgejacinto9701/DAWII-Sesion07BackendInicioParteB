package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Docente;
import com.empresa.entity.FiltroDocente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	
	//Query Methodo => El query se genera a apartir del nombre del método
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public abstract List<Docente> findByDni(String dni);
	public abstract List<Docente> findByNombreContaining(String dni);
	
	
	//JPQL es un query con las clases y atributos (entidades)
	//Query de la consulta del FrontEnd
	@Query("select d from Docente d where ( ?1 is '' or d.dni = ?1 ) and ( ?2 is '' or d.nombre like ?2 ) and ( ?3 is -1  or d.ubigeo.idUbigeo = ?3 ) ")
	public abstract List<Docente> listaDocentePorDniNombreUbigeo(String dni, String nombre,int idUbigeo);
	
	
	@Query("select d from Docente d where ( :#{#fil.dni} is '' or d.dni = :#{#fil.dni} ) and  ( :#{#fil.nombre} is '' or d.nombre like :#{#fil.nombre} ) and ( :#{#fil.idUbigeo} is 0  or d.ubigeo.idUbigeo = :#{#fil.idUbigeo} ) ")
	public abstract List<Docente> listaPorFiltro(@Param("fil")FiltroDocente filtro);
	
	
	
	
	
}


