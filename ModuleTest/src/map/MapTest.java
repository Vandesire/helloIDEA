package map;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2024/12/31 00:02
 */
public class MapTest {

    @Test
    public void mapTest1() {

//        ArrayList<Map.Entry> list = new ArrayList<>();
//        HashMap map = new HashMap<Object,Object>();
//        Object dft = map.getOrDefault(1, 1);

        InputStream inputStream;
        try(InputStream stream = inputStream = new ObjectInputStream(Files.newInputStream(Paths.get("")))) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
        int n = 28;
        n = n | n >>> 1;
        n = n | n >>> 2;

        System.out.println(n);
    }
}
