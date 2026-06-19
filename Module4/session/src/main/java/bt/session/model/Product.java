package bt.session.model;

import java.util.List;

public class Product {

    private final long id;
    private final String name;
    private final String sku;
    private final long price;
    private final long oldPrice;
    private final String imageUrl;
    private final String teaser;
    private final List<String> features;
    private final boolean featured;

    public Product(long id, String name, String sku, long price, long oldPrice, String imageUrl,
                   String teaser, List<String> features, boolean featured) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.oldPrice = oldPrice;
        this.imageUrl = imageUrl;
        this.teaser = teaser;
        this.features = features;
        this.featured = featured;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public long getPrice() {
        return price;
    }

    public long getOldPrice() {
        return oldPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTeaser() {
        return teaser;
    }

    public List<String> getFeatures() {
        return features;
    }

    public boolean isFeatured() {
        return featured;
    }

    public String getFormattedPrice() {
        return MoneyFormatter.format(price);
    }

    public String getFormattedOldPrice() {
        return MoneyFormatter.format(oldPrice);
    }
}