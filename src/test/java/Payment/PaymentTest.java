package Payment;

import FileAndDatabase.Database;
import Payments.Payment;
import Swimmers.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    Database db;
    Database db2;
    Payment payment;

    @BeforeEach
    void setup(){
        db = new Database();
        db2 = new Database();
        payment = new Payment();
    }

    @Test
    void juniorActivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("juniorActive", 17, true, false, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 1000;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void juniorPassivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("juniorPassive", 17, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 500;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void normalActivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("normalActive", 22, true, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 1600;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void normalPassivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("normalPassive", 22, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 500;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void seniorActivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("seniorActive", 66, true, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 1200;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void seniorPassivePrice(){
        //arrange
        Swimmer testSwimmer = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 500;
        double actual = payment.swimmerMembershipPrice(db.getSwimmers().get(0));
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void membershipIncome(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 3100;
        double actual = payment.swimmersMembershipIncome(testSwimmers);
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void memberShipDebt(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        double expected = 2200;
        double actual = payment.swimmersMembershipDebt(testSwimmers);
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void getMissingPayers(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        ArrayList<Swimmer> notPaidSwimmers = new ArrayList<>(List.of(testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        db2.initSwimmers(notPaidSwimmers);
        //assert
        assertEquals(payment.getMissingPayers(testSwimmers), notPaidSwimmers);
    }
    @Test
    void howManyPaid(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        int expected = 3;
        double actual = payment.swimmersPaid(testSwimmers);
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void howManyDidNotPay(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        int expected = 3;
        double actual = payment.swimmersNotPaid(testSwimmers);
        //assert
        assertEquals(expected, actual);
    }
    @Test
    void setAllNotPaid(){
        //arange
        Swimmer testSwimmer1 = new Swimmer("juniorActive", 17, true, false, true);
        Swimmer testSwimmer2 = new Swimmer("juniorPassive", 17, false, true, true);
        Swimmer testSwimmer3 = new Swimmer("normalActive", 22, true, true, true);
        Swimmer testSwimmer1notPaid = new Swimmer("juniorActive", 17, true, false, false);
        Swimmer testSwimmer2notPaid = new Swimmer("juniorPassive", 17, false, true, false);
        Swimmer testSwimmer3notPaid = new Swimmer("normalActive", 22, true, true, false);
        Swimmer testSwimmer4 = new Swimmer("normalPassive", 22, false, true, false);
        Swimmer testSwimmer5 = new Swimmer("seniorActive", 66, true, true, false);
        Swimmer testSwimmer6 = new Swimmer("seniorPassive", 66, false, true, false);
        ArrayList<Swimmer> testSwimmers = new ArrayList<>(List.of(testSwimmer1, testSwimmer2, testSwimmer3, testSwimmer4, testSwimmer5, testSwimmer6));
        ArrayList<Swimmer> notPaidSwimmers = new ArrayList<>(List.of(testSwimmer1notPaid, testSwimmer2notPaid, testSwimmer3notPaid, testSwimmer4, testSwimmer5, testSwimmer6));
        //act
        db.initSwimmers(testSwimmers);
        db2.initSwimmers(notPaidSwimmers);
        payment.setSwimmersNotPaid(testSwimmers);
        //assert
        for (int i = 0; i < db.getSwimmers().size(); i++){
            assertEquals(db.getSwimmers().get(i).getHasPaid(), db2.getSwimmers().get(i).getHasPaid());
        }
    }
}