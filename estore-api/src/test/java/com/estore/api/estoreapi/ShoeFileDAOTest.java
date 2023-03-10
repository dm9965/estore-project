package com.estore.api.estoreapi;

import com.estore.api.estoreapi.controller.ShoeController;
import com.estore.api.estoreapi.model.Shoe;
import com.estore.api.estoreapi.persistence.ShoeDAO;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.estore.api.estoreapi.enums.Sizing.MENS;
import static com.estore.api.estoreapi.enums.Sizing.WOMENS;

@Tag("Persistence-tier")
public class ShoeFileDAOTest {

    private File file;
    ObjectMapper objectmapper;
    Shoe shoe1, shoe2, shoe3;

    Shoe[] mockShoeArray;

    ShoeDAO mockShoeFileDAO;

    public ShoeFileDAOTest(){}

   @BeforeEach
   public void setupShoeFileDAO() throws IOException {
       this.mockShoeFileDAO = Mockito.mock(ShoeDAO.class);
       shoe1 = new Shoe(1, "Air Max 90", MENS, 9, 129.99, "Nike", "Leather", "Green");
       shoe2 = new Shoe(2, "Yeezy 500", MENS, 11, 229.99, "Adidas", "Multiple", "Tan");
       shoe3 = new Shoe(3, "Jordan 3 White Cement", WOMENS, 7, 224.99, "Jordan", "Leather", "White");
       mockShoeArray = new Shoe[]{shoe1, shoe2, shoe3};
       Mockito.when(objectmapper.readValue(file, Shoe[].class)).thenReturn(mockShoeArray);
   }
   @Test
   public void testGetShoes() throws IOException {
        Shoe[] shoeArray = mockShoeFileDAO.getAllShoes();
        Assertions.assertNotNull(shoeArray);
        Assertions.assertEquals(3, shoeArray.length);
        for (int i = 0; i < shoeArray.length; i++) {
            Assertions.assertEquals(shoeArray[i], this.mockShoeArray[i]);
        }
    }


//------------------------------------------------------------------------------------

   @Test
   public void testSearchShoesByStyle() throws IOException {
       Shoe[] shoeArray = this.mockShoeFileDAO.searchShoes("Yeezy");
       Assertions.assertEquals(shoeArray.length, 3);
       Assertions.assertEquals(shoeArray[0], this.mockShoeArray[1]);
   }

//-----------------------------------------------------------------------------------

   @Test
   public void testGetShoeById() throws IOException {
       Shoe shoe = this.mockShoeFileDAO.getShoeById(1);
       Assertions.assertEquals(shoe, this.mockShoeArray[0]);
   }


   @Test
   public void testDeleteShoeById() throws IOException {
       boolean result = (Boolean)Assertions.assertDoesNotThrow(() -> {
           return this.mockShoeFileDAO.deleteShoeById(1);
       }, "Unexpected exception thrown");
       Assertions.assertTrue(result);
       Assertions.assertEquals(this.mockShoeFileDAO.getAllShoes(), this.mockShoeArray.length - 1);
   }

//-----------------------------------------------------------------------------------

   @Test
   public void testCreateShoe() throws Exception {
       Shoe shoe = new Shoe(4, "Chuck Taylor", MENS, 8, 79.99, "Converse", "Canvas", "Black");
       Shoe result = (Shoe)Assertions.assertDoesNotThrow(() -> {
           return this.mockShoeFileDAO.createShoe(shoe);
       }, "Unexpected Exception Thrown");
       Assertions.assertNotNull(result);
       Shoe actual = this.mockShoeFileDAO.getShoeById(shoe.getId());
       Assertions.assertEquals(actual.getId(), shoe.getId());
       Assertions.assertEquals(actual.getStyle(), shoe.getStyle());
       Assertions.assertEquals(actual.getSizing(), shoe.getSizing());
       Assertions.assertEquals(actual.getSize(), shoe.getSize());
       Assertions.assertEquals(actual.getPrice(), shoe.getPrice());
       Assertions.assertEquals(actual.getBrand(), shoe.getBrand());
       Assertions.assertEquals(actual.getMaterial(), shoe.getMaterial());
       Assertions.assertEquals(actual.getColor(), shoe.getColor());
   }

//-----------------------------------------------------------------------------------

   @Test
   public void testUpdateShoe() throws IOException {
       Shoe shoe = new Shoe(5, "Stan Smiths", MENS, 11, 89.99, "Adidas", "Leather", "White");
       Shoe result = (Shoe)Assertions.assertDoesNotThrow(() -> {
           return this.mockShoeFileDAO.updateShoe(shoe);
       }, "Unexpected exception thrown");
       Assertions.assertNotNull(result);
       Shoe actual = this.mockShoeFileDAO.getShoeById(shoe.getId());
       Assertions.assertEquals(actual, shoe);

    }



}