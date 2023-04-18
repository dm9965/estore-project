package PersistenceTier;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Order;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartFileDAO;
import com.estore.api.estoreapi.persistence.OrderFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Persistence-tier")
public class OrderFileDAOTest {
    private OrderFileDAO mockOrderDAO;
    private CartFileDAO cartDAO;
    private Order mockOrder;
    Shoe mockShoe1 = new Shoe(1, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");
    Shoe mockShoe2 = new Shoe(2, "AJ1 Low", Sizing.MENS, 11, 199.99, "Jordan", "Leather", "Olive");
    ObjectMapper mockObjectMapper;
    public OrderFileDAOTest() {
    }

    @BeforeEach
    public void setup() throws IOException {
        Order[] mockOrderList = new Order[]{};
        this.mockOrder = new Order("john");
        this.mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Order[].class))).thenReturn(mockOrderList);
        this.mockOrderDAO = new OrderFileDAO("data/testing.txt", mockObjectMapper, this.cartDAO);
    }

    @Test
    public void testGetOrderSuccess() throws IOException {
        setup();
        Order order = mockOrderDAO.getOrder("john");
        Assertions.assertEquals("john", order.getUsername());
    }

    @Test
    public void testGetOrderFailure() throws IOException {
        setup();
        Order order = mockOrderDAO.getOrder("jane");
        assertNull(order);
    }

    @Test
    public void testGetAllOrders() throws IOException {
        setup();
        List<Order> orders = mockOrderDAO.getAllOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals("john", orders.get(0).getUsername());
    }

    @Test
    public void testCheckoutSuccess() throws IOException {
        setup();
        Cart cart = new Cart();
        cart.setUsername("john");
        ArrayList<Shoe> shoesInCart = new ArrayList<>();
        shoesInCart.add(mockShoe1);
        shoesInCart.add(mockShoe2);
        cart.setItems(shoesInCart);
        when(cartDAO.getCart("john")).thenReturn(cart);
        Order order = mockOrderDAO.checkout("john");
        assertNotNull(order);
        assertEquals("john", order.getUsername());
        assertEquals(2, order.getItems().size());
        assertEquals(250.0, order.getTotalCost());
    }

    @Test
    public void testCheckoutFailure() throws IOException {
        setup();
        Cart cart = new Cart();
        cart.setUsername("jane");
        when(cartDAO.getCart("jane")).thenReturn(cart);
        Order order = mockOrderDAO.checkout("jane");
        assertNull(order);
    }
}

