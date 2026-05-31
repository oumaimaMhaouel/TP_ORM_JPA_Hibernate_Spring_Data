package com.example.tp_orm_jpa_hibernate_spring_data;

import com.example.tp_orm_jpa_hibernate_spring_data.entities.Product;
import com.example.tp_orm_jpa_hibernate_spring_data.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TpOrmJpaHibernateSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpOrmJpaHibernateSpringDataApplication.class, args);
	}

/*	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			Product product=new Product(null,"test",120L,12);
			Product product2=Product.builder().name("p2").price(123L).quantity(12).build();
			productRepository.save(product);
			productRepository.save(product2);
			List<Product> products=productRepository.findAll();
			products.forEach(p->System.out.println(p.toString()));
		};
	}*/

}
