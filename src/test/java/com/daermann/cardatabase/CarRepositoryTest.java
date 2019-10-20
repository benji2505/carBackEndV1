package com.daermann.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.daermann.cardatabase.domain.Car;
import com.daermann.cardatabase.domain.CarRepository;
import com.daermann.cardatabase.domain.Owner;
import com.daermann.cardatabase.domain.OwnerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private CarRepository repository;
	@Autowired
	private OwnerRepository orepository;
	
	Owner owner1 = new Owner("John","Johnson");

	
	@Test
	public void saveCar() {
		
		orepository.save(owner1);
		Car car = new Car("Tesla", "Model X", "White", "ABC-1234", owner1, 2017, 86000);
		entityManager.persistAndFlush(car);
		assertThat(car.getId()).isNotNull();
	}
	
	@Test
	public void deleteCars() {
		orepository.save(owner1);
		entityManager.persistAndFlush(new Car("Tesla", "Model X", "Red", "def-5678", owner1, 2016, 86000));
		entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007", owner1, 2015, 24500));
		repository.deleteAll();
		assertThat(repository.findAll()).isEmpty();
	}
	
}
