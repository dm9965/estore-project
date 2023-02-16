package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Shoe;

/**
 * Implements the functionality for JSON file-based persistence for Cart
 * 
 * @author Lucas Romero
 */
@Component
public class CartFileDAO implements CartDAO{
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    Map<Integer,Shoe> shoes;   // Provides a local cache of the shoe objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between shoe
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new shoe
    private String filename;    // Filename to read from and write to

    /**
     * Creates a shoe File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public CartFileDAO(@Value("${shoes.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the shoees from the file
    }

    /**
     * Generates the next id for a new {@linkplain shoe shoe}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain shoe shoees} from the tree map
     * 
     * @return  The array of {@link shoe shoees}, may be empty
     */
    private Shoe[] getShoesArray() {
        return getShoesArray(null);
    }

    /**
     * Generates an array of {@linkplain shoe shoees} from the tree map for any
     * {@linkplain shoe shoees} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain shoe shoees}
     * in the tree map
     * 
     * @return  The array of {@link shoe shoees}, may be empty
     */
    private Shoe[] getShoesArray(String containsText) { // if containsText == null, no filter
        ArrayList<Shoe> shoeArrayList = new ArrayList<>();

        for (Shoe shoe : shoes.values()) {
                shoeArrayList.add(shoe);
        }

        Shoe[] shoeArray = new Shoe[shoeArrayList.size()];
        shoeArrayList.toArray(shoeArray);
        return shoeArray;
    }

    

    /**
     * Saves the {@linkplain shoe shoees} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link shoe shoees} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Shoe[] shoeArray = getShoesArray();


        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),shoeArray);
        return true;
    }

    /**
     * Loads {@linkplain shoe shoees} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        shoes = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of shoees
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Shoe[] shoeArray = objectMapper.readValue(new File(filename),Shoe[].class);

        // Add each shoe to the tree map and keep track of the greatest id
        for (Shoe shoe : shoeArray) {
            shoes.put(shoe.getId(),shoe);
            if (shoe.getId() > nextId)
                nextId = shoe.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shoe[] getCart(int userID) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteShoe(int id) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addShoe(int id) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
