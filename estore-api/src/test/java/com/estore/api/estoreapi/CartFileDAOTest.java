package com.estore.api.estoreapi;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@Tag("Persistence-tier")
public class CartFileDAOTest {
    ObjectMapper mockObjectMapper;

    Cart mockCart;

    Shoe mockShoe = new Shoe (1, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");

   CartFileDAO mockCartDAO;

    public CartFileDAOTest(){}

    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        this.mockCart = new Cart();
        ArrayList<Shoe> itemsInMockCart = new ArrayList<>();
        itemsInMockCart.add(mockShoe);
        mockCart.setItems(itemsInMockCart);
        mockCart.setUsername("dom");
        this.mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Cart.class))).thenReturn(this.mockCart);
        this.mockCartDAO = new CartFileDAO("data/cart-testing.txt", mockObjectMapper);
    }

    @Test
    public void testAddToCart() throws IOException {
        mockCartDAO.addToCart("dom", mockShoe);
        Cart cart = mockCartDAO.getCart("dom");
        ArrayList<Shoe> expectedItems = new ArrayList<>();
        expectedItems.add(mockShoe);
        Assertions.assertEquals(expectedItems, cart.getItems());
    }


    @Test
    public void testGetCart() throws Exception {
        String username = "user1";
        ArrayList<Shoe> items = new ArrayList<>();
        items.add(mockShoe);
        Cart cart = new Cart();
        cart.setUsername(username);
        cart.setItems(items);

        Cart result = mockCartDAO.getCart(username);

        Assertions.assertEquals(result.getUsername(), username);
        Assertions.assertEquals(result.getItems().size(), 1);
        Assertions.assertEquals(result.getItems().get(0), mockShoe);
    }
}

