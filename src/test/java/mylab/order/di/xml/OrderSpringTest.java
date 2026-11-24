package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    public void testShoppingCart() {
        // 장바구니가 주입되었는지 확인
        assertNotNull(shoppingCart);
        assertNotNull(shoppingCart.getProducts());

        // 상품 개수와 순서 확인
        assertEquals(2, shoppingCart.getProducts().size());
        assertEquals("노트북",
                shoppingCart.getProducts().get(0).getName());
        assertEquals("스마트폰",
                shoppingCart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderService() {
        // 주문 서비스와 장바구니가 주입되었는지 확인
        assertNotNull(orderService);
        assertNotNull(orderService.getShoppingCart());

        // 150000 + 800000 = 950000
        assertEquals(950000.0,
                orderService.calculateOrderTotal(), 0.001);
    }
}