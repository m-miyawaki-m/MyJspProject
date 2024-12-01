package beans;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShainBeanTest {

    private ShainBean shain;

    @Before
    public void setUp() {
        shain = new ShainBean();
    }

    @Test
    public void testSetAndGetId() {
        int expectedId = 123;
        shain.setId(expectedId);
        assertEquals(expectedId, shain.getId());
    }

    @Test
    public void testSetAndGetName() {
        String expectedName = "Taro";
        shain.setName(expectedName);
        assertEquals(expectedName, shain.getName());
    }

    @Test
    public void testSetAndGetSei() {
        String expectedSei = "Tanaka";
        shain.setSei(expectedSei);
        assertEquals(expectedSei, shain.getSei());
    }

    @Test
    public void testSetAndGetNen() {
        int expectedNen = 2024;
        shain.setNen(expectedNen);
        assertEquals(expectedNen, shain.getNen());
    }

    @Test
    public void testSetAndGetAddress() {
        String expectedAddress = "Tokyo, Japan";
        shain.setAddress(expectedAddress);
        assertEquals(expectedAddress, shain.getAddress());
    }
}
