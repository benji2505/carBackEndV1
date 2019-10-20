package com.daermann.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long>, PagingAndSortingRepository<Car, Long> {

	List<Car> findByBrand(@Param("brand") String brand);
	
	List<Car> findByColor(@Param("color") String color);
	
	List<Car> findByYear(@Param("year") int year);
	
	List<Car> findByBrandAndModel(String brand, String model);
	
	List<Car> findByBrandOrColor(String brand, String color);
	
	List<Car> findByBrandOrderByYearAsc(@Param("brand")String brand);
	
//	@Query("select c from Car c where c.brand =?1")
//	List<Car> findByBrand(String brand);
	
	@Query("select c from Car c where c.brand like %?1")	//example for advanced query with like
	List<Car> findByBrandEndsWith(String brand);
}
