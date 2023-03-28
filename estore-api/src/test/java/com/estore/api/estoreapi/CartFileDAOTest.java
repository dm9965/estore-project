package com.estore.api.estoreapi;

import com.estore.api.estoreapi.enums.Sizing;
import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.CartFileDAO;
import com.estore.api.estoreapi.utils.FlatFileOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@Tag("Persistence-tier")
public class CartFileDAOTest {
    private ObjectMapper mockObjectMapper;

    private FlatFileOps mockFlatFileOps;

    private File mockDataFile;

    private Cart mockCart;

    private Shoe mockShoe = new Shoe (1, "Yeezy", Sizing.MENS, 12, 229.99, "Adidas", "Mesh", "Grey");

    private CartFileDAO mockCartDAO;

    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        this.mockCart = new Cart();
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        when(mockObjectMapper.readValue(any(File.class), eq(Cart.class))).thenReturn(this.mockCart);
        this.mockCartDAO = new CartFileDAO("data/testing.txt", mockObjectMapper);
    }

    @Test
    public void testAddToCart() throws Exception {
        String username = "user1";
        ArrayList<Shoe> items = new ArrayList<>();
        items.add(mockShoe);

        mockCartDAO.addToCart(username, mockShoe);
        Cart cart = new Cart();
        cart.setItems(items);
        cart.getItems().add(mockShoe);

        mockCartDAO.addToCart(username, mockShoe);

        Assertions.assertEquals(cart.getItems().size(), 2);

    }

    @Test
    public void testRemoveFromCart() throws Exception {
        String username = "user1";
        ArrayList<Shoe> items = new ArrayList<>();
        items.add(mockShoe);
        Cart cart = new Cart();
        cart.setUsername(username);
        cart.setItems(items);

        when(mockCart.getUsername()).thenReturn(username);
        when(mockCart.getItems()).thenReturn(items);

        Mockito.when(mockObjectMapper.readValue(any(File.class), eq(Cart[].class))).thenReturn(new Cart[]{cart});

        mockCartDAO.removeFromCart(username, mockShoe.getId());

        Mockito.verify(mockObjectMapper).writeValue(eq(mockDataFile), anyCollection());
        Mockito.verify(mockFlatFileOps);
        FlatFileOps.ensureDataFileExists(anyString());
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
