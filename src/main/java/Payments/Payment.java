package Payments;

import Swimmers.Swimmer;

import java.util.ArrayList;

public class Payment {
    public int swimmersPaid(ArrayList<Swimmer> swimmers){
        int usersPaid = 0;
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("ja")) {
                if(swimmer.getIsActive().contains("ja")) {
                    if (swimmer.getAgeGroup().contains("junior")) {
                        usersPaid++;
                    } else if (swimmer.getAgeGroup().contains("senior")) {
                        usersPaid++;
                    } else {
                        usersPaid++;
                    }
                } else {
                    usersPaid++;
                }
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
        int usersPaid = 0;
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("nej")) {
                usersPaid++;
            }
        }
        return usersPaid;
    }
    public double swimmerMembershipPrice(Swimmer swimmer){
        double amountPaid = 0;
        if(swimmer.getIsActive().contains("ja")) {
            if (swimmer.getAgeGroup().contains("junior")) {
                amountPaid = amountPaid+1000;
            } else if (swimmer.getAgeGroup().contains("senior")) {
                amountPaid = amountPaid+(1600*0.75);
            } else {
                amountPaid = amountPaid+1600;
            }
        } else {
            amountPaid = amountPaid+500;
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
        double totalPaid = 0;
        for (Swimmer swimmer : swimmers){
            if(swimmer.getHasPaid().contains("nej")) {
                double amountPaid = swimmerMembershipPrice(swimmer);
                totalPaid = totalPaid+amountPaid;
            }
        } return totalPaid;
    }

}
