package eecs1022.lab7.bank.model;

public class Transaction {
    String transactionType="";
    double amount=0;


    public Transaction(String transactionType, double amount) {
        this.transactionType=transactionType;
        this.amount=amount;
    }

    public String getStatus() {


        String status= "Transaction "+transactionType+": $"+String.format("%.2f", amount);


        return status;
    }
}
