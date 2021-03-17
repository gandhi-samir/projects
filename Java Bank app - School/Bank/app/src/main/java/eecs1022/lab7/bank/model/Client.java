package eecs1022.lab7.bank.model;

public class Client {
    String name="";
    double amount=0;
    String[] stm;
    String status="";


    public Client(String name, double amount) {
        this.name=name;
        this.amount=amount;
        status= name+": $"+String.format("%.2f",this.amount);
        stm = new String[1];
        stm[0]=status;
    }

    public String getStatus() {

        return status;
    }

    public String[] getStatement() {

        return stm;
    }

    public void deposit(double amount) {
        this.amount+=amount;
        String [] temp = stm;
        stm = new String[stm.length+1];
        for(int i =0; i < temp.length;i++){
            stm[i]=temp[i];
        }
        stm[stm.length-1]="Transaction DEPOSIT: $"+String.format("%.2f",amount);
        status= name+": $"+String.format("%.2f",this.amount);
        stm[0]=status;

    }

    public void withdraw(double amount) {
        this.amount-=amount;
        String [] temp = stm;
        stm = new String[stm.length+1];
        for(int i =0; i < temp.length;i++){
            stm[i]=temp[i];
        }
        stm[stm.length-1]="Transaction WITHDRAW: $"+String.format("%.2f",amount);
        status= name+": $"+String.format("%.2f",this.amount);
        stm[0]=status;

    }

    public String getName(){
        return this.name;
    }

    public double getAmount() {
        return amount;
    }
}
