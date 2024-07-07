package com.sushant.productservice.repositories;

import com.sushant.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContaining(String word);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    void deleteById(Long id);

    Product save(Product product);

    List<Product> findByIdNotNullOrderByIdAsc();

    @Transactional
    @Modifying
    @Query("""
            update Product p set p.isDeleted = ?1, p.title = ?2, p.description = ?3, p.imageUrl = ?4, p.price = ?5
            where p.id = ?6""")
    Product updateIsDeletedAndTitleAndDescriptionAndImageUrlAndPriceById(boolean isDeleted, String title, String description, String imageUrl, double price, Long id);


}
