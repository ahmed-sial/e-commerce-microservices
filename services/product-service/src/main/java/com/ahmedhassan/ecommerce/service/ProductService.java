package com.ahmedhassan.ecommerce.service;

import com.ahmedhassan.ecommerce.dto.*;
import com.ahmedhassan.ecommerce.dto.category.CategoryRequestDto;
import com.ahmedhassan.ecommerce.dto.category.CategoryResponseDto;
import com.ahmedhassan.ecommerce.dto.product.*;
import com.ahmedhassan.ecommerce.exception.category.CategoryAlreadyExistsException;
import com.ahmedhassan.ecommerce.exception.category.CategoryNotFoundException;
import com.ahmedhassan.ecommerce.exception.product.InsufficientProductStockQuantityException;
import com.ahmedhassan.ecommerce.exception.product.ProductAlreadyExistsWithNameException;
import com.ahmedhassan.ecommerce.exception.product.ProductNotFoundException;
import com.ahmedhassan.ecommerce.exception.product.ProductPurchaseException;
import com.ahmedhassan.ecommerce.mapper.CategoryMapper;
import com.ahmedhassan.ecommerce.mapper.ProductMapper;
import com.ahmedhassan.ecommerce.model.Category;
import com.ahmedhassan.ecommerce.repository.CategoryRepository;
import com.ahmedhassan.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public ProductResponseDto createProduct(@NonNull ProductRequestDto dto) {
        var category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        var existsByName = productRepository.existsByName(dto.name());
        if (existsByName) {
            throw new ProductAlreadyExistsWithNameException("Product already exists with the name");
        }
        var product = productMapper.toProduct(dto, category);
        var savedProduct = productRepository.save(product);
        return productMapper.toProductResponseDto(savedProduct);
    }

    @Transactional
    public List<ProductPurchaseResponseDto> purchaseProducts(
            @NonNull List<ProductPurchaseRequestDto> dto
    ) {
        var productIds = dto
                .stream()
                .map(ProductPurchaseRequestDto::productId)
                .toList();
        var savedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (savedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("One or more of the products does not exist");
        }
        dto = dto
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequestDto::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponseDto>();
        for (int i = 0; i < savedProducts.size(); i++) {
            var product = savedProducts.get(i);
            var request = dto.get(i);
            if (product.getAvailableQuantity() < request.orderQuantity()) {
                throw new InsufficientProductStockQuantityException("Insufficient product stock quantity");
            }
            var newQuantity = product.getAvailableQuantity() - request.orderQuantity();
            product.setAvailableQuantity(newQuantity);
            productRepository.save(product);
            var mappedProduct = productMapper.toProductPurchaseResponseDto(product, newQuantity);
            purchasedProducts.add(mappedProduct);
        }
        return purchasedProducts;
    }

    public ProductResponseDto findProductById(UUID id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toProductResponseDto(product);
    }

    public PagedResponse<ProductResponseDto> findAll(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        var products = productRepository.findAll(pageable);
        return productMapper.toPagedProductResponseDto(products);
    }

    public ProductResponseDto updateProduct(UUID id, @NonNull ProductUpdateDto dto) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (dto.price() != null && !dto.price().equals(product.getPrice()))
            product.setPrice(dto.price());
        if (dto.availableQuantity() != null && !dto.availableQuantity().equals(product.getAvailableQuantity()))
            product.setAvailableQuantity(dto.availableQuantity());
        productRepository.save(product);
        return productMapper.toProductResponseDto(product);
    }

    public void deleteProduct(UUID id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    public CategoryResponseDto createCategory(@NonNull CategoryRequestDto dto) {
        var categoryExists = categoryRepository.existsByName(dto.name());
        if (categoryExists) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        var slug = dto.name().toLowerCase().replace(" ", "_");
        var category = Category
                .builder()
                .name(dto.name())
                .description(dto.name())
                .slug(slug)
                .products(null)
                .build();
        var savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(savedCategory);
    }

    public CategoryResponseDto findCategoryById(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return categoryMapper.toCategoryResponseDto(category);
    }

    public void deleteCategory(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

}
