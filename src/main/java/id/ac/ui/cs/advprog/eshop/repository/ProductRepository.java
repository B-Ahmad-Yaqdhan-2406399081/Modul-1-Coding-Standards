package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product edit(String id, Product editedProduct) {
        int oldProductIndex = findIndexById(id);

        editedProduct.setProductId(id);
        productData.set(oldProductIndex, editedProduct);
        return editedProduct;
    }

    public Product findById(String id) {
        return productData.get(findIndexById(id));
    }

    public int findIndexById(String id) {
        return IntStream.range(0, productData.size())
                .filter(index -> productData.get(index).getProductId().equals(id))
                .findFirst()
                .getAsInt();
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
