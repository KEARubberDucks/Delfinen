package Comparators;

import FileAndDatabase.Database;
import MainClasses.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeComparatorTest {

    Database db;
    Database db2;

    @BeforeEach
    void setUp() {
        db = new Database();
        db2 = new Database();
    }

    @Test
    void compareGreater() {
        //Arrange
        CompetetiveComparator typeComparator = new CompetetiveComparator();
        Swimmer superheroA = new Swimmer("A",18,false,false);
        Swimmer superheroB = new Swimmer("B",21,false,true);
        Swimmer superheroC = new Swimmer("C",100,false,true);
//
        //Act
        int expected = 4;
        int actual = typeComparator.compare(superheroA,superheroB);
        //Assert
        assertEquals(expected,actual);
    }
    @Test
    void compareEqual() {
        //Arrange
        CompetetiveComparator typeComparator = new CompetetiveComparator();
        Swimmer superheroA = new Swimmer("A",18,false,false);
        Swimmer superheroB = new Swimmer("B",21,false,true);
        Swimmer superheroC = new Swimmer("C",100,false,true);
        //Act
        int expected = 0;
        int actual = typeComparator.compare(superheroB,superheroC);
        //Assert
        assertEquals(expected,actual);
    }
    @Test
    void compareLower() {
        //Arrange
        CompetetiveComparator typeComparator = new CompetetiveComparator();
        Swimmer superheroA = new Swimmer("A",18,false,false);
        Swimmer superheroB = new Swimmer("B",21,false,true);
        Swimmer superheroC = new Swimmer("C",100,false,true);
        //Act
        int expected = -4;
        int actual = typeComparator.compare(superheroC,superheroA);
        //Assert
        assertEquals(expected,actual);
    }

}