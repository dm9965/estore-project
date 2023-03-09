package com.estore.api.estoreapi;

import com.estore.api.estoreapi.controller.ShoeController;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeDAO;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.estore.api.estoreapi.enums.Sizing.MENS;
import static com.estore.api.estoreapi.enums.Sizing.WOMENS;

@Tag("Persistence-tier")
public class ShoeFileDAOTest {
    private ShoeDAO mockShoeFileDAO;
  
   @BeforeEach
   public void setupShoeFileDAO() throws IOException {
        this.mockShoeFileDAO = Mockito.mock(ShoeDAO.class);
   }

   @Test
   public void testGetShoes() throws IOException {
        Shoe[] shoeArray = new Shoe[2];
        
        shoeArray[0] = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        shoeArray[1] = new Shoe(2, "Nike 2", WOMENS, 7, 199.99, "Nike", "poly", "Yellow");
      
        Mockito.when(this.mockShoeFileDAO.getAllShoes()).thenReturn(shoeArray);
      
        Shoe[] testShoeArray = this.mockShoeFileDAO.getAllShoes();
        Assertions.assertEquals(testShoeArray, shoeArray);
    }

    @Test
    public void testGetShoesNotFound() throws Exception {
        Shoe[] shoeArray = new Shoe[3];
        
        Mockito.when(this.mockShoeFileDAO.getAllShoes()).thenReturn(null);
        
        ResponseEntity<Shoe> response = this.mockShoeFileDAO.getAllShoes(shoeArray);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetShoesHandleException() throws Exception {
        Shoe[] shoeArray = new Shoe[3];
        
        Mockito.doThrow(new IOException()).when(this.mockShoeFileDAO).getAllShoes(shoeArray);
        
        ResponseEntity<Shoe> response = this.mockShoeFileDAO.getAllShoes(shoeArray);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

//------------------------------------------------------------------------------------

   @Test
   public void testSearchShoesByStyle() throws IOException {
    Shoe[] shoe = new Shoe[3];
        
    shoe[0] = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
    
    Mockito.when(this.mockShoeFileDAO.searchShoes(shoe.getStyle)).thenReturn(shoe.getStyle);
  
    Shoe[] testShoe = this.mockShoeFileDAO.searchShoes(shoe);
    Assertions.assertEquals(testShoe, shoe);
   }

   @Test
   public void testFindShoesByStyleNotFound() throws Exception {
       Shoe shoe = new Shoe();
      
       Mockito.when(this.mockShoeController.searchShoes(shoe.getStyle())).thenReturn(shoe);
      
       ResponseEntity<Shoe> response = this.mockShoeDAO.searchShoes(shoe);
       Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
       Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testFindShoesByStyleHandleException() throws Exception {
       Shoe shoe = new Shoe();
      
       Mockito.when(this.mockShoeFileDAO.searchShoes(shoe.getStyle())).thenReturn(shoe);
      
       ResponseEntity<Shoe> response = this.mockShoeFileDAO.searchShoes(shoe);
       Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
       Assertions.assertEquals(shoe, response.getBody());
   }

//-----------------------------------------------------------------------------------

   @Test
   public void testGetShoeById() throws IOException {
       Shoe shoe = new Shoe();
       
       Mockito.when(this.mockShoeFileDAO.getShoeById(shoe.getId())).thenReturn(shoe);
       
       ResponseEntity<Shoe> response = this.mockShoeFileDAO.getShoeById(shoe);
       Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
       Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testGetShoeByIdNotFound() throws IOException {
        Shoe shoe = new Shoe();
      
        Mockito.when(this.mockShoeFileDAO.getShoeByID(shoe.getId())).thenReturn(null);
   
        ResponseEntity<Shoe> response = this.mockShoeFileDAO.getShoeByID(shoe);
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testGetShoeByIdHandleException() throws IOException {
        Shoe shoe = new Shoe();
      
        Mockito.when(this.mockShoeFileDAO.getShoeById(shoe.getId())).thenReturn(shoe);
   
        ResponseEntity<Shoe> response = this.mockShoeFileDAO.getShoeByID(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
   }

 //-----------------------------------------------------------------------------------  

   @Test
   public void testDeleteShoeById() throws IOException {
        Shoe shoe = new Shoe();
      
        Mockito.when(this.mockShoeFileDAO.getAllShoes()).thenReturn(shoe);
      
        ResponseEntity<Shoe> response = this.mockShoeFileDAO.deleteShoeById(shoe.getId());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testDeleteShoeByIdNotFound() throws Exception {
        Shoe shoe = new Shoe();
      
        Mockito.when(this.mockShoeFileDAO.deleteShoeByID(shoe.getId())).thenReturn(shoe.getId);
   
        ResponseEntity<Shoe> response = this.mockShoeDAO.deleteShoeByID(shoe.getId());
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testDeleteShoeByIdHandleException() throws Exception {
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
      
       Mockito.when(this.mockShoeFileDAO.createShoe(shoe)).thenReturn(shoe);
      
       ResponseEntity<Shoe> response = this.ShoeFileDAO.createShoe(shoe);
       Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
       Assertions.assertEquals(shoe, response.getBody());
   }

   @Test
   public void testCreateShoeFailed() throws Exception {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        
        Mockito.when(this.mockShoeFileDAO.createShoe(shoe)).thenReturn(null);
        
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
       Shoe shoe = new Shoe();
      
       Mockito.when(this.mockShoeFileDAO.updateShoe()).thenReturn(shoe);
      
       ResponseEntity<Shoe> response = this.mockShoeFileDAO.updateShoe(shoe);
       Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
       Assertions.assertEquals(shoe, response.getBody());
    }

    @Test
    public void testUpdateShoeNotFound() throws Exception {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
        
        Mockito.when(this.mockShoeFileDAO.updateShoe(shoe)).thenReturn(null);
       
        ResponseEntity<Shoe> response = this.shoeFileDAO.updateShoe(shoe);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateShoeHandleException() throws IOException {
        Shoe shoe = new Shoe(1, "Jordan 1 Chicago", MENS, 12, 229.99, "Jordan", "leather", "Red");
       
        Mockito.doThrow(new IOException()).when(this.mockShoeFileDAO).updateShoe(shoe);
        
        ResponseEntity<Shoe> response = this.shoeFileDAO.updateShoe(shoe);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}