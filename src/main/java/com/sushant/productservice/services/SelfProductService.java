package com.sushant.productservice.services;

import com.sushant.productservice.dtos.GenericProductDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Category;
import com.sushant.productservice.models.Product;
import com.sushant.productservice.repositories.CategoryRepository;
import com.sushant.productservice.repositories.ProductRepository;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<GenericProductDto> getAllProducts() {
        return productRepository.findByIdNotNullOrderByIdAsc().stream()
                .map(prod -> new GenericProductDto(prod.getId(), prod.getTitle(), prod.getDescription(), prod.getImageUrl(), prod.getCategory().getName(), prod.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Product not present in DB!!");
        } else {
            return product.stream().map(prod -> new GenericProductDto(prod.getId(), prod.getTitle(), prod.getDescription(), prod.getImageUrl(), prod.getCategory().getName(), prod.getPrice())).findFirst().get();
        }
    }

    @Override
    public Product addNewProduct(Product product) {
        //Add createdDate and updateDate here
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(product.getCategory().getName());
                    return newCategory;
                });
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateIsDeletedAndTitleAndDescriptionAndImageUrlAndPriceById(product.isDeleted(), product.getTitle(),
                product.getDescription(), product.getImageUrl(), product.getPrice(), product.getId());
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }


    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void postToKafka() {
        KafkaTemplate kafkaTemplate = kafkaTemplate();
        for(int i = 0; i<100;i++) {
            String topicKey = "OddKey";
            if(i%2==0) {
                topicKey = "EvenKey";
            }
            kafkaTemplate.send("email", String.valueOf(i), "Hello, this is test message"+i+" at "+LocalDateTime.now());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Value("${kafka.bootstrap.address}")
    private String kafkaBootstrapAddress;
    public ProducerFactory<String, String> producerFactory() {
        HashMap<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
