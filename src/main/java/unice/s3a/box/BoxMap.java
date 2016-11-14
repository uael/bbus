package unice.s3a.box;

import java.util.HashMap;

/**
 * The type Box map.
 */
public class BoxMap extends HashMap<String, Box> {
    /**
     * Add box boolean.
     * @param name the name
     * @return the boolean
     */
    public boolean add(String name) {
        return this.put(name, new Box()) != null;
    }

    /**
     * Gets or add.
     * @param name the name
     * @return the or add
     */
    public Box getOrAdd(String name) {
        if (this.containsKey(name)) {
            return this.get(name);
        }
        Box m = new Box();
        this.put(name, m);
        return m;
    }
}
