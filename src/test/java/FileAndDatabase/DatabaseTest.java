package FileAndDatabase;

import Swimmers.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database db;

    @BeforeEach
    void setup(){
        db = new Database();
        Swimmer swimmer1 = new Swimmer("control1", 42, true, false);
        Swimmer swimmer2 = new Swimmer("control2", 32, false, true);
        Swimmer swimmer3 = new Swimmer("control3", 44, true, true);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(swimmer1, swimmer2, swimmer3));
        db.initSwimmers(testSwimmers);
    }

    @Test
    void getSwimmers() {
        //arrange
        Swimmer swimmer1 = new Swimmer("control1", 42, true, false);
        Swimmer swimmer2 = new Swimmer("control2", 32, false, true);
        Swimmer swimmer3 = new Swimmer("control3", 44, true, true);
        ArrayList<Swimmer> controlSwimmers = new ArrayList<>(List.of(swimmer1, swimmer2, swimmer3));

        //act
        ArrayList<Swimmer> testSwimmers = db.getSwimmers();

        //assert
        for (Swimmer controlSwimmer : controlSwimmers) {
           // assertEquals(controlSwimmer.name, testSwimmers.get(controlSwimmers.indexOf(controlSwimmer)).name);
        }
    }
    @Test
    void getSwimmersEmpty(){
        //arrange
        db = new Database();

        //act
        ArrayList<Swimmer> testSwimmers = db.getSwimmers();

        //assert
        assertNull(testSwimmers);
    }

    @Test
    void initSwimmers() {
        //arrange
        Swimmer swimmer1 = new Swimmer("controlNew1", 42, true, false);
        Swimmer swimmer2 = new Swimmer("controlNew2", 32, false, true);
        Swimmer swimmer3 = new Swimmer("controlNew3", 44, true, true);
        ArrayList<Swimmer> controlSwimmers = new ArrayList<>(List.of(swimmer1, swimmer2, swimmer3));

        //act
        db.initSwimmers(controlSwimmers);
        ArrayList<Swimmer> testSwimmers = db.getSwimmers();

        //assert
        for (Swimmer controlSwimmer : controlSwimmers) {
         //   assertEquals(controlSwimmer.name, testSwimmers.get(controlSwimmers.indexOf(controlSwimmer)).name);
        }

    }
}