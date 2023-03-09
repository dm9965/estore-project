package com.estore.api.estoreapi;

import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.estore.api.estoreapi.enums.Sizing.MENS;
import static com.estore.api.estoreapi.enums.Sizing.WOMENS;

@Tag("Persistence-tier")
public class ShoeFileDAOTest {
    private ShoeDAO mockShoeDAO;

    @BeforeEach
    public void setupShoeFileDAO() throws IOException {
        this.mockShoeDAO = Mockito.mock(ShoeDAO.class);
    }

    @Test
    public void testGetShoes() throws IOException {
        Shoe[] shoeArray = new Shoe[2];
        shoeArray[0] = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        shoeArray[1] = new Shoe(2, "Nike 2", WOMENS, 7, 199.99, "Nike", "poly", "Yellow");

        Mockito.when(this.mockShoeDAO.getAllShoes()).thenReturn(shoeArray);

        Shoe[] testShoeArray = this.mockShoeDAO.getAllShoes();
        Assertions.assertEquals(testShoeArray, shoeArray);
    }

    @Test
    public void testGetShoesNotFound() throws Exception {
        Shoe shoe = new Shoe();
        Shoe[] shoeArray = new Shoe[shoe];

        Mockito.when(this.mockShoeController.getShoeById(shoeId)).thenReturn([""]);

        ResponseEntity<Shoe> response = this.shoeFileDAO.getAllShoes(shoe);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetShoesHandleException() throws Exception {
        Shoe shoe = new Shoe();
        Shoe[] shoeArray = new Shoe[shoe];

        Mockito.doThrow(new IOException()).when(this.ShoeFileDAO).getAllShoes(shoeArray);

        ResponseEntity<Shoe> response = this.shoeFileDAO.getAllShoes(shoeId);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

//-----------------------------------------------------------------------------------

    @Test
    public void testFindShoes() throws IOException {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.searchShoes(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.searchShoes(shoe);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testFindShoesNotFound() throws Exception {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.searchShoes(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.searchShoes(shoe);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testFindShoesHandleException() throws Exception {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.searchShoes(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.searchShoes(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

//-----------------------------------------------------------------------------------

    @Test
    public void testGetShoe() throws IOException {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.getShoeById(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.getShoeById(shoe.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testGetShoeNotFound() throws IOException {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.getShoeByID(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.getShoeByID(shoe);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testGetShoeHandleException() throws IOException {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.getShoeById(shoe.getId())).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeDAO.getShoeByID(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    //-----------------------------------------------------------------------------------

    @Test
    public void testDeleteShoe() throws IOException {
        Shoe[] shoeArray = new Shoe[shoeArray];

        Mockito.when(this.mockShoeController.getAllShoes()).thenReturn(shoeArray);

        ResponseEntity<Shoe> response = this.mockShoeFileDAO.getAllShoes(shoe);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testDeleteShoeNotFound() throws Exception {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.deleteShoeByID(shoe.getId())).thenReturn(shoe.getId);

        ResponseEntity<Shoe> response = this.mockShoeDAO.deleteShoeByID(shoe.getId());
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testDeleteShoeHandleException() throws Exception {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.deleteShoeById(shoe.getId())).thenReturn(shoe.getId);

        ResponseEntity<Shoe> response = this.mockShoeDAO.deleteShoeByID(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

//-----------------------------------------------------------------------------------

    @Test
    public void testCreateShoe() throws Exception {
        Shoe shoe = new Shoe();

        Mockito.when(this.mockShoeController.createShoe(shoe)).thenReturn(shoe);

        ResponseEntity<Shoe> response = this.mockShoeFileDAO.createShoe(shoe);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testCreateShoeFailed() throws Exception {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");

        Mockito.when(this.mockShoeController.createShoe(shoe)).thenReturn(null);

        ResponseEntity<Shoe> response = this.shoeFileDAO.create(shoe);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateShoeHandleException() throws Exception {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");

        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).createShoe(shoe);

        ResponseEntity<Shoe> response = this.shoeFileDAO.createShoe(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

//-----------------------------------------------------------------------------------

    @Test
    public void testUpdateShoe() throws IOException {
        Shoe[] shoeArray = new Shoe[shoeArray];

        Mockito.when(this.mockShoeDAO.updateShoe()).thenReturn(shoeArray);

        ResponseEntity<Shoe> response = this.mockShoeFileDAO.updateShoe(shoe);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testUpdateShoeNotFound() throws Exception {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");

        Mockito.when(this.mockShoeDAO.updateShoe(shoe)).thenReturn(null);

        ResponseEntity<Shoe> response = this.shoeFileDAO.updateShoe(shoe);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateShoeHandleException() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");

        Mockito.doThrow(new IOException()).when(this.mockShoeDAO).updateShoe(shoe);

        ResponseEntity<Shoe> response = this.shoeFileDAO.updateShoe(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}


