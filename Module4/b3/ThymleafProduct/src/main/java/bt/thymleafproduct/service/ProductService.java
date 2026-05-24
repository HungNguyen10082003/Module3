package bt.thymleafproduct.service;

import bt.thymleafproduct.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {
	private final List<ProductModel> products = Collections.synchronizedList(new ArrayList<>());
	private final AtomicInteger idGenerator = new AtomicInteger(0);

	public ProductService() {

		save(new ProductModel(0, "Laptop A", 1200.0, "Laptop cấu hình cao", "BrandX"));
		save(new ProductModel(0, "Điện thoại B", 600.0, "Điện thoại thông minh", "BrandY"));
		save(new ProductModel(0, "Tablet C", 350.0, "Máy tính bảng tiện dụng", "BrandZ"));
	}

	public List<ProductModel> findAll() {
		return new ArrayList<>(products);
	}

	public ProductModel findById(int id) {
		Optional<ProductModel> p = products.stream().filter(prod -> prod.getId() == id).findFirst();
		return p.orElse(null);
	}

	public void save(ProductModel product) {
		int id = idGenerator.incrementAndGet();
		product.setId(id);
		products.add(product);
	}

	public void update(int id, ProductModel product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				product.setId(id);
				products.set(i, product);
				return;
			}
		}
	}

	public void delete(int id) {
		products.removeIf(p -> p.getId() == id);
	}

	public List<ProductModel> searchByName(String name) {
		if (name == null || name.isEmpty()) return findAll();
		String q = name.toLowerCase();
		List<ProductModel> result = new ArrayList<>();
		for (ProductModel p : products) {
			if (p.getName() != null && p.getName().toLowerCase().contains(q)) {
				result.add(p);
			}
		}
		return result;
	}
}
