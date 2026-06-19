package bt.session.service;

import bt.session.model.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogService {

    private final List<Product> products = List.of(
            new Product(
                    1L,
                    "Sản phẩm 1",
                    "4564",
                    350000L,
                    400000L,
                    "/images/product-1.svg",
                    "Bó hoa tươi sáng, phù hợp gửi lời chúc mừng, kỷ niệm và khai trương.",
                    List.of(
                            "Tặng banner, thiệp trị giá 20.000đ miễn phí",
                            "Giảm ngay 20.000đ khi bạn tạo đơn hàng online",
                            "Giảm tiếp 3% cho đơn hàng online lần đầu, ưu đãi luỹ tiến cho khách thân thiết",
                            "Giao miễn phí trong nội thành 63/63 tỉnh",
                            "Giao gấp trong vòng 2 giờ",
                            "Cam kết 100% hoàn lại tiền nếu bạn không hài lòng",
                            "Cam kết hoa tươi trên 3 ngày"
                    ),
                    true
            ),
            new Product(
                    2L,
                    "Sản phẩm 2",
                    "3199",
                    200000L,
                    240000L,
                    "/images/product-2.svg",
                    "Thiết kế nhỏ gọn, tông pastel dịu nhẹ, hợp tặng sinh nhật và thăm hỏi.",
                    List.of(
                            "Banner và thiệp tặng kèm miễn phí",
                            "Giảm 15.000đ cho đơn đặt online",
                            "Giao nhanh nội thành trong ngày",
                            "Đảm bảo hoa tươi và đóng gói an toàn"
                    ),
                    false
            ),
            new Product(
                    3L,
                    "Sản phẩm 3",
                    "8821",
                    500000L,
                    560000L,
                    "/images/product-3.svg",
                    "Bó hoa cao cấp với sắc vàng - xanh nổi bật, hợp các dịp chúc mừng trang trọng.",
                    List.of(
                            "Phong cách sang trọng",
                            "Phù hợp lễ khai trương và sự kiện",
                            "Thiết kế theo yêu cầu riêng",
                            "Miễn phí thiệp chúc mừng"
                    ),
                    true
            ),
            new Product(
                    4L,
                    "Sản phẩm 4",
                    "9150",
                    600000L,
                    680000L,
                    "/images/product-4.svg",
                    "Bó hoa rực rỡ với sắc vàng nổi bật, tạo cảm giác tươi vui và may mắn.",
                    List.of(
                            "Đặt trước dễ dàng qua vài bước",
                            "Giao nhanh trong 2 giờ",
                            "Tư vấn lựa chọn theo ngân sách",
                            "Bảo đảm hình ảnh thật so với mẫu"
                    ),
                    true
            )
    );

    public List<Product> findAll() {
        return products;
    }

    public Product findById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm có mã " + id));
    }
}