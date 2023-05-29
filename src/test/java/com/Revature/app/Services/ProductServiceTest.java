package com.Revature.app.Services;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.Revature.app.daos.ProductDAO;
import com.Revature.app.services.ProductsService;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Optional;
import com.Revature.app.models.Product;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
   private ProductDAO productDAO;
   // @Mock
   private ProductsService productService;

   @Before
   public void setUp() {
        // Initialize the Mockito framework
        MockitoAnnotations.openMocks(this);
        // Create a new instance of the UserService class with the mocked dependencies
        productService = new ProductsService(productDAO);
    }

   @Test
   public void getALLTest() {
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String id2 = "3ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String id3 = "3ea3b385-ec7c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    // Create a list of reviews to be returned by the mock
    List<Product> mockProduct = new ArrayList<>();
    mockProduct.add(new Product(id, name, Description, price, stock, category_id));
    mockProduct.add(new Product(id2, name, Description, price, stock, category_id));
    mockProduct.add(new Product(id3, name, Description, price, stock, category_id));

    when(productDAO.grabAllAvailableProductsOptional()).thenReturn(Optional.of(mockProduct));

    Optional<List<Product>> result = productService.getAll();

    assertTrue(result.isPresent());
    assertEquals(mockProduct, result.get());

    verify(productDAO).grabAllAvailableProductsOptional();
   }
   @Test
   public void getProductByIdTest() {
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    // Create a list of reviews to be returned by the mock
    Product mockProduct = new Product(id, name, Description, price, stock, category_id);

    when(productDAO.getProductById(id)).thenReturn(Optional.of(mockProduct));

    Optional<Product> result = productService.getProductById(id);

    assertTrue(result.isPresent());
    assertEquals(mockProduct, result.get());

    verify(productDAO).getProductById(id);
   }
   @Test
   public void getByNameTest(){
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String id2 = "3ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String name2 = "TestProduct2";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    // Create a list of reviews to be returned by the mock
    List<Product> mockProduct = new ArrayList<>();
    mockProduct.add(new Product(id, name, Description, price, stock, category_id));
    mockProduct.add(new Product(id2, name2, Description, price, stock, category_id));

    when(productDAO.searchByName(name)).thenReturn(Optional.of(mockProduct));

    Optional<List<Product>> result = productService.getByName(name);

    assertTrue(result.isPresent());
    assertEquals(mockProduct, result.get());

    verify(productDAO).searchByName(name);
   }
   @Test
   public void getByCategoryTest() {
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String id2 = "3ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String name2 = "TestProduct2";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    // Create a list of reviews to be returned by the mock
    List<Product> mockProduct = new ArrayList<>();
    mockProduct.add(new Product(id, name, Description, price, stock, category_id));
    mockProduct.add(new Product(id2, name2, Description, price, stock, category_id));

    when(productDAO.searchByCategory(category_id)).thenReturn(Optional.of(mockProduct));

    Optional<List<Product>> result = productService.getByCategory(category_id);

    assertTrue(result.isPresent());
    assertEquals(mockProduct, result.get());

    verify(productDAO).searchByCategory(category_id);
   }
   @Test
   public void getByPriceTest() {
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String id2 = "3ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String name2 = "TestProduct2";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(10.0);
    BigDecimal price2 = new BigDecimal(50.0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    // Create a list of reviews to be returned by the mock
    List<Product> mockProduct = new ArrayList<>();
    mockProduct.add(new Product(id, name, Description, price, stock, category_id));
    mockProduct.add(new Product(id2, name2, Description, price2, stock, category_id));

    when(productDAO.grabAllProductBy(price, price2)).thenReturn(Optional.of(mockProduct));

    Optional<List<Product>> result = productService.getByPrice(price, price2);

    assertTrue(result.isPresent());
    assertEquals(mockProduct, result.get());

    verify(productDAO).grabAllProductBy(price, price2);

   }
   @Test
   public void updateProductTest(){
    String id = "2ea3b385-ec1c-423a-a530-5550a397f8c5" ;
    String name = "TestProduct";
    String Description = "Test Description";
    BigDecimal price = new BigDecimal(10.0);
    Integer stock = 25;
    String category_id = "2e45b385-ec1c-423a-a530-5550a399f8c5";

    Product mockProduct = new Product(id, name, Description, price, stock, category_id);
    productService.updateProduct(mockProduct);
    verify(productDAO).updateProduct(mockProduct);
  }
    
}
