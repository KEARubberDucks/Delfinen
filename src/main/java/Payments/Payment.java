package Payments;

import Swimmers.Swimmer;

import java.time.Year;
import java.util.ArrayList;

public class Payment {
    private int currentYear;
    public int swimmersPaid(ArrayList<Swimmer> swimmers){
        int usersPaid = 0;
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("ja")) {
                    usersPaid++;
            }
        }
        return usersPaid;
    }
    public ArrayList<Swimmer> getMissingPayers(ArrayList<Swimmer> swimmers) {
        ArrayList<Swimmer> missingPayers = new ArrayList<>();
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("nej")) {
                missingPayers.add(swimmer);
            }
        } return missingPayers;
    }
    public int swimmersNotPaid(ArrayList<Swimmer> swimmers){
        return swimmers.size() - swimmersPaid(swimmers);
    }
    public double swimmerMembershipPrice(Swimmer swimmer){
        double amountPaid;
        if(swimmer.getIsActive().contains("ja")) {
            if (swimmer.getAgeGroup().contains("junior")) {
                amountPaid = 1000;
            } else if (swimmer.getAgeGroup().contains("senior")) {
                amountPaid = (1600*0.75);
            } else {
                amountPaid = 1600;
            }
        } else {
            amountPaid = 500;
        } return amountPaid;
    }
    public double swimmersMembershipIncome(ArrayList<Swimmer> swimmers){
        double totalPaid = 0;
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("ja")) {
                double amountPaid = swimmerMembershipPrice(swimmer);
                totalPaid = totalPaid+amountPaid;
            }
        } return totalPaid;
    }
    public double swimmersMembershipDebt(ArrayList<Swimmer> swimmers){
        double totalDebt = 0;
        for (Swimmer swimmer : getMissingPayers(swimmers)){
                double debt = swimmerMembershipPrice(swimmer);
                totalDebt += debt;
        } return totalDebt;
    }
    public void setSwimmersNotPaid(ArrayList<Swimmer> swimmers){
        for (Swimmer swimmer : swimmers){
            swimmer.setHasPaid(false);
        }
    }
    public int getCurrentYear() {
        currentYear = Year.now().getValue();
        return currentYear;
    }
}
