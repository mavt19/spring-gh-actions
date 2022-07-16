package com.bns.gh.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bns.gh.models.dto.ProductDto;
import com.bns.gh.models.entities.Product;
import com.bns.gh.models.repositories.ProductRepository;
import com.bns.gh.services.impl.ProductServiceImpl;
import com.bns.gh.utils.AppUtils;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
//	@BeforeEach
//	public void setUp() {
//		// TODO Auto-generated method stub
//		
//		productService = new ProductServiceImpl(productRepository);
//	}
	@Test
	void testFindAll() {
		//GIVEN
		
		//WHEN
//		//THEN
		
//        List<ProductDto> employees = productService.findAll();
//
//        Assertions.assertThat(employees.size()).isGreaterThan(0);
		
        List<Product> users = new ArrayList<>();
        users.add(new Product(1L, "me@example.org", 12, 22.3));

        when(productRepository.findAll()).thenReturn(users);

        List<ProductDto> expected = productService.findAll();

        assertEquals(expected.size(), users.size());
        verify(productRepository).findAll();
	}

//	@Test
//	void testFindAllPageable() {
//	    MedicationEntity aspirin = MedicationEntity.create("Aspirin");
//	    MedicationEntity omeprazole = MedicationEntity.create("Omeprazole");
//	    when(repository.findAllByPartialName(any(), any())).thenReturn(new PageImpl<>(List.of(aspirin, omeprazole)));
//	}

	@Test
	void testFindById() {
		var id = 1L;
	    var entity = new Product(id, "me@example.org", 12, 22.3);
	    when(productRepository.findById(any())).thenReturn(Optional.of(entity));

	    Optional<ProductDto> result = productService.findById(id);
	    assertThat(result).contains(new ProductDto(id, "me@example.org", 12, 22.3));
	    verify(productRepository).findById(id);
	}

	@Test
	void testFindProductInRange() {
		
        List<Product> users = List.of(new Product(2L, "product2", 12, 30.3), new Product(3L, "product3", 12, 50.3));

        when(productRepository.findByPriceBetween(anyDouble(), anyDouble())).thenReturn(users);

        List<ProductDto> expected = productService.findProductInRange(anyDouble(), anyDouble());
        assertEquals(expected.size(), users.size());
        verify(productRepository).findByPriceBetween(anyDouble(),anyDouble());
	}

	@Test
	void testSave() {
        Product user = new Product();
        user.setName("Test Name");

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(user);

        ProductDto created = productService.save(AppUtils.productToProductDto(user));

        assertThat(created.getName()).isSameAs(user.getName());
        verify(productRepository).save(user);
	}

	@Test
	void testUpdate() {
		Product user = new Product();
        user.setId(89L);
        user.setName("Test Name");

        ProductDto newUser = new ProductDto();
        newUser.setId(user.getId());
        newUser.setName("New Test Name");

        when(productRepository.findById(user.getId())).thenReturn(Optional.of(user));
        productService.update(newUser, user.getId());
        
        verify(productRepository).save(AppUtils.productDtoToProduct(newUser));
        verify(productRepository).findById(user.getId());
	}

	@Test
	void testDeleteById() {

        productService.deleteById(anyLong());
        verify(productRepository).deleteById(anyLong());
	}

}
