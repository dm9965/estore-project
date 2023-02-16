package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Shoe;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShoeFileDAO implements ShoeDAO{
        private static final Logger LOG = Logger.getLogger(ShoeFileDAO.class.getName());
        final Map<Integer,Shoe> shoes = new HashMap<>();   // Provides a local cache of the hero objects
        // so that we don't need to read from the file
        // each time
        private final ObjectMapper objectMapper;  // Provides conversion between Shoe
        // objects and JSON text format written
        // to the file
        private static int nextId;  // The next Id to assign to a new hero
        private final String filename;    // Filename to read from and write to

        /**
         * Creates a Hero File Data Access Object
         *
         * @param filename Filename to read from and write to
         * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
         *
         * @throws IOException when file cannot be accessed or read from
         */
        public ShoeFileDAO(@Value("${shoes.file}") String filename,ObjectMapper objectMapper) throws IOException {
            this.filename = filename;
            this.objectMapper = objectMapper;
            load();  // load the heroes from the file
        }

        /**
         * Generates the next id for a new {@linkplain Shoe shoe}
         *
         * @return The next id
         */
        private synchronized static int nextId() {
            int id = nextId;
            ++nextId;
            return id;
        }

        /**
         * Generates an array of {@linkplain Shoe heroes} from the tree map
         *
         * @return  The array of {@link Shoe heroes}, may be empty
         */
        public Shoe[] getAllShoes() {
            return getAllShoes(null);
        }

        /**
         * Generates an array of {@linkplain Shoe shoes} from the tree map for any
         * {@linkplain Shoe shoes} that contains the text specified by containsText
         * <br>
         * If containsText is null, the array contains all of the {@linkplain Hero heroes}
         * in the tree map
         *
         * @return  The array of {@link Shoe shoes}, may be empty
         */
        private Shoe[] getAllShoes(String containsText) { // if containsText == null, no filter
            ArrayList<Shoe> shoeArrayList = new ArrayList<>();

            for (Shoe shoe : shoes.values()) {
                if (containsText == null || shoe.getBrand().contains(containsText)) {
                    shoeArrayList.add(shoe);
                }
            }

            Shoe[] heroArray = new Shoe[shoeArrayList.size()];
            shoeArrayList.toArray(heroArray);
            return heroArray;
        }

        /**
         * Saves the {@linkplain Shoe shoes} from the map into the file as an array of JSON objects
         *
         * @return true if the {@link Shoe shoes} were written successfully
         *
         * @throws IOException when file cannot be accessed or written to
         */
        private boolean save() throws IOException {
            Shoe[] shoeArray = getAllShoes();

            // Serializes the Java Objects to JSON objects into the file
            // writeValue will thrown an IOException if there is an issue
            // with the file or reading from the file
            objectMapper.writeValue(new File(filename),shoeArray);
            return true;
        }

        /**
         * Loads {@linkplain Shoe shoes} from the JSON file into the map
         * <br>
         * Also sets next id to one more than the greatest id found in the file
         *
         * @return true if the file was read successfully
         *
         * @throws IOException when file cannot be accessed or read from
         */
        private boolean load() throws IOException {
            nextId = 0;
            // Deserializes the JSON objects from the file into an array of heroes
            // readValue will throw an IOException if there's an issue with the file
            // or reading from the file
            Shoe[] shoeArray = objectMapper.readValue(new File(filename),Shoe[].class);

            // Add each hero to the tree map and keep track of the greatest id
            for (Shoe shoe : shoeArray) {
                shoes.put(shoe.getId(),shoe);
                if (shoe.getId() > nextId)
                    nextId = shoe.getId();
            }
            // Make the next id one greater than the maximum from the file
            ++nextId;
            return true;
        }

        @Override
        public Shoe getShoeByID(int ID) {

        }
        /**
         ** {@inheritDoc}
         */
        @Override
        public Shoe[] searchShoes(String containsText) {
            synchronized(shoes) {
                return getAllShoes(containsText);
            }
        }

        /**
         ** {@inheritDoc}
         */
        @Override
        public Shoe createShoe(Shoe shoe) throws IOException {
            synchronized(shoes) {
                // We create a new hero object because the id field is immutable
                // and we need to assign the next unique id
                Shoe newShoe = new Shoe();
                newShoe.setBrand(newShoe.getBrand());
                newShoe.setColor(newShoe.getColor());
                newShoe.setId(newShoe.getId());
                newShoe.setSize(newShoe.getSize());
                newShoe.setPrice(newShoe.getPrice());
                newShoe.setMaterial(newShoe.getMaterial());
                newShoe.setStyle(newShoe.getStyle());

                shoes.put(newShoe.getId(),newShoe);
                save(); // may throw an IOException
                return newShoe;
            }
        }

        /**
         ** {@inheritDoc}
         */
        @Override
        public Shoe updateShoe(Shoe shoe) throws IOException {
            synchronized(shoes) {
                if (!shoes.containsKey(shoe.getId()))
                    return null;  // hero does not exist

                shoes.put(shoe.getId(),shoe);
                save(); // may throw an IOException
                return shoe;
            }
        }

        /**
         ** {@inheritDoc}
         */
        @Override
        public boolean deleteShoeById(int id) throws IOException {
            synchronized(shoes) {
                if (shoes.containsKey(id)) {
                    shoes.remove(id);
                    return save();
                }
                else
                    return false;
            }
        }
}
