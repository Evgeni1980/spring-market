package ru.kremenia.market.core.converters;

import org.springframework.stereotype.Component;
import ru.kremenia.market.api.ProductDto;
import ru.kremenia.market.core.entities.Product;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public Product DtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }
}
