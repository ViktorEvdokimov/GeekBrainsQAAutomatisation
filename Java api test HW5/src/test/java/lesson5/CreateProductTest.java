package lesson5;

import com.github.javafaker.Faker;
import db.model.Products;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;


public class CreateProductTest {

    static ProductService productService;
    static db.dao.ProductsMapper productMapper;
    static SqlSession session;
    Product product = null;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        productMapper = session.getMapper(db.dao.ProductsMapper.class);
    }

    @AfterAll
    static void afterAll(){
        session.close();
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        Products product = productMapper.selectByPrimaryKey((long) id);
        assertThat(product != null, CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void updateProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        Product responseProduct =  response.body();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        responseProduct.setPrice(50000);
        productService.modifyProduct(responseProduct);
        Products product = productMapper.selectByPrimaryKey(Long.valueOf(responseProduct.getId()));
        assertThat(product.getPrice() == 50000, CoreMatchers.is(true));
    }

    @Test
    void deleteProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        Product responseProduct =  response.body();
        Products product = productMapper.selectByPrimaryKey(Long.valueOf(responseProduct.getId()));
        assertThat(product != null, CoreMatchers.is(true));
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        productService.deleteProduct(responseProduct.getId());
        product = productMapper.selectByPrimaryKey(Long.valueOf(responseProduct.getId()));
        assertThat(product == null, CoreMatchers.is(true));
    }



}
