package eecs1022.lab7.bank.model;

public class Bank {

    String status ="";
    int clientCounter=0;


    Client [] clientList= new Client[6];
    String [] stm;


    public String getStatus() {
        if(status.equals("")) {
            status="Accounts: {";
            for (int i = 0; i < clientList.length; i++) {
                if (clientList[i] != null) {
                    status += clientList[i].getStatus();


                    if (i >= 0 && i < clientCounter-1) {
                        status = status + ", ";

                    }


                }
            }
            status += "}";
        }


        return status;
    }

    public String[] getStatement(String name) {

        if(isClient(name)==false){
            status="Error: From-Account "+name+" does not exist";

            return null;
        }
        else {
            status="";

            for (int j = 0; j < clientList.length; j++) {
                if (clientList[j] != null) {
                    if (clientList[j].getName().equals(name)) {
                        stm = clientList[j].getStatement();
                    }
                }
            }
            return stm;
        }


    }

    public void deposit(String name, double amount) {


        if (isClient(name) == false) {
            status = "Error: To-Account " + name + " does not exist";
        } else if (amount <= 0) {
            status = "Error: Non-Positive Amount";
        }
        else {
            status="";

            for (int i = 0; i < clientList.length; i++) {
                if (clientList[i]!=null&&clientList[i].getName().equals(name)) {
                    clientList[i].deposit(amount);
                }
            }
        }
    }

    public void withdraw(String name, double amount) {
        if (isClient(name) == false) {
            status = "Error: From-Account " + name + " does not exist";
        } else if (amount <=0) {
            status = "Error: Non-Positive Amount";
        } else if (isGreater(name, amount) == true) {
            status = "Error: Amount too large to withdraw";

        }
        else {
            status="";
            for (int i = 0; i < clientList.length; i++) {
                if (clientList[i]!=null&&clientList[i].getName().equals(name)) {
                    clientList[i].withdraw(amount);
                }
            }
        }
    }

    public void transfer(String fromName, String toName, double amount) {
        if (isClient(fromName) == false) {
            status = "Error: From-Account " + fromName + " does not exist";
        }
        else if (isClient(toName) == false) {
            status = "Error: To-Account " + toName + " does not exist";
        }
        else if (amount <= 0) {
            status = "Error: Non-Positive Amount";
        }
        else if (isGreater(fromName, amount) == true) {
            status = "Error: Amount too large to transfer";

        }
        else {
            status="";
            for (int i = 0; i < clientList.length; i++) {
                if (clientList[i]!=null&&clientList[i].getName().equals(fromName)) {
                    clientList[i].withdraw(amount);
                }
            }
            for (int k = 0; k < clientList.length; k++) {
                if (clientList[k]!=null&&clientList[k].getName().equals(toName)) {
                    clientList[k].deposit(amount);
                }
            }
        }
    }

    public void addClient(String name, double amount) {

        if(clientCounter <6&& isClient(name)==false&&amount>0){
            status="";
            Client client = new Client(name, amount);
            clientList[clientCounter]=client;
            clientCounter++;
        }

        else if(clientCounter>=5){
            status="Error: Maximum Number of Accounts Reached";
        }
        else if(isClient(name) == true){
            status="Error: Client "+name+" already exists";
        }
        else if(amount <0){
            status="Error: Non-Positive Initial Balance";
        }


    }
    public void addClient(Client client, double amount) {

        if(clientCounter <6&& isClient(client.getName())==false&&amount>0){
            status="";

            clientList[clientCounter]=client;
            clientCounter++;
        }

        else if(clientCounter>=5){
            status="Error: Maximum Number of Accounts Reached";
        }
        else if(isClient(client.getName()) == true){
            status="Error: Client "+client.getName()+" already exists";
        }
        else if(amount <0){
            status="Error: Non-Positive Initial Balance";
        }


    }

    public Client getClient(String name){
        Client client = null;
        for(int i = 0; i < clientList.length;i++){
            if(clientList[i].getName().equals(name)){
                client = clientList[i];
                break;
            }


        }
        return client;
    }





    public boolean isClient(String name){
        boolean isClient = false;
        for(int i =0; i < clientList.length;i++) {
            if (clientList[i] != null) {
                if (clientList[i].getName().equals(name)) {
                    isClient = true;
                    break;
                }
            }
        }
        return isClient;
    }

    public boolean isGreater(String name, double amount) {

        boolean greaterAmount = false;
        for (int j = 0; j < clientList.length; j++) {
            if (clientList[j] != null&& clientList[j].getName().equals(name)) {
                if (amount > clientList[j].getAmount()) {
                    greaterAmount = true;
                    break;
                }
            }
        }
        return greaterAmount;
    }

}

