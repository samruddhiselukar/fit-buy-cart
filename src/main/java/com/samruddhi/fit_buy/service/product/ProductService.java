package com.samruddhi.fit_buy.service.product;

import com.samruddhi.fit_buy.exceptions.ProductNotFoundException;
import com.samruddhi.fit_buy.model.Category;
import com.samruddhi.fit_buy.model.Product;
import com.samruddhi.fit_buy.repository.CategoryRepository;
import com.samruddhi.fit_buy.repository.ProductRepository;
import com.samruddhi.fit_buy.request.AddProductRequest;
import com.samruddhi.fit_buy.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        //check if the category is found in the db
        //if yes - set it as a new product in the category
        //if no then save it as a new category
        //then set it as a new product in that category

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName()); //make sure the curresponding constructor is init
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);

        return productRepository.save(createProduct(request, category));

    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()-> {throw new ProductNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProductById(UpdateProductRequest request, Long productId) {
            return productRepository.findById(productId)
                    .map(existingProduct-> updateExistingProduct(existingProduct, request))
                    .map(productRepository :: save)
                    .orElseThrow(()->new ProductNotFoundException("Product not Found!"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
                existingProduct.setName(request.getName());
                existingProduct.setBrand(request.getBrand());
                existingProduct.setPrice(request.getPrice());
                existingProduct.setInventory(request.getInventory());
                existingProduct.setDescription(request.getDescription());

                Category category = categoryRepository.findByName(request.getCategory().getName());
                existingProduct.setCategory(category);
                return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
