package eecs1022.lab7.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab7.bank.model.Bank;
import eecs1022.lab7.bank.model.Client;

public class MainActivity extends AppCompatActivity {

    /* Hint: How do you share the same bank object between button clicks (attached with controller methods) of the app? */
    Bank b = new Bank();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint: How do you initialize an empty bank and displays its status to the output textview
         * when the app is first launched?
         */

        String startingStatus = b.getStatus();
        setContentsOfTextView(R.id.statusOutput,startingStatus);

    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void newClient(View view){
        String newName = getInputOfTextField(R.id.nameInput);
        String startingBal = getInputOfTextField(R.id.startingBalanceInput);
        if(newName.equals("")||startingBal.equals("")){
            setContentsOfTextView(R.id.statusOutput,"Enter Valid Values");
        }
        else {
            double amount = Double.parseDouble(startingBal);
            Client c1 = new Client(newName, amount);
            b.addClient(c1, amount);
            setContentsOfTextView(R.id.statusOutput, b.getStatus());
        }
    }

    public void processTransaction(View view) {

        String money = "";
        String option = getItemSelected(R.id.transactionSelect);



        if (option.equals("Deposit") ) {
            String to = getInputOfTextField(R.id.toInput);
            money = getInputOfTextField(R.id.amountInput);
            if (to.equals("") || money.equals("")) {
                setContentsOfTextView(R.id.statusOutput, "Enter Valid Values");
            } else {
                double amount = Double.parseDouble(money);
               // Client client = b.getClient(to);
                b.deposit(to, amount);
                setContentsOfTextView(R.id.statusOutput, b.getStatus());
            }
        }

        if (option.equals("Withdraw")) {
            String from = getInputOfTextField(R.id.fromInput);
            money = getInputOfTextField(R.id.amountInput);
            if (from.equals("") || money.equals("")) {
                setContentsOfTextView(R.id.statusOutput, "Enter Valid Values");
            } else {
                double amount = Double.parseDouble(money);
                //Client client = b.getClient(from);
                b.withdraw(from, amount);

                setContentsOfTextView(R.id.statusOutput, b.getStatus());
            }
        }

        if (option.equals("Transfer")) {

            String from = getInputOfTextField(R.id.fromInput);
            String to = getInputOfTextField(R.id.toInput);
            money = getInputOfTextField(R.id.amountInput);
            if (to.equals("") || money.equals("") || from.equals("")) {
                setContentsOfTextView(R.id.statusOutput, "Enter Valid Values");
            } else {
                double amount = Double.parseDouble(money);
                b.transfer(from, to, amount);
                //Client client = b.getClient(to);
                setContentsOfTextView(R.id.statusOutput, b.getStatus());
            }
        }

        if (option.equals("Print Statement")) {
            String from = getInputOfTextField(R.id.fromInput);
            if (from.equals("")) {
                setContentsOfTextView(R.id.statusOutput, "Enter Valid Values");
            } else {
                String statment = "";
                String [] stm =  b.getStatement(from);
                if(stm==null) {
                    statment = b.getStatus();
                }
                else {
                    for (int i = 0; i < b.getStatement(from).length; i++) {
                        statment += b.getStatement(from)[i] + ", ";

                    }
                }

                setContentsOfTextView(R.id.statusOutput, statment);

            }
        }
    }




    /* Hints on controller methods:
     *
     * Declare two controller methods, each of which declared with a parameter with type View (see Week 9 Tutorials).
     *  - One method (say cm1) should be attached to the "ADD A NEW ACCOUNT" button,
     *      whereas the other method (say cm2) should be attached to the "CONFIRM" button.
     *
     *  - Controller method cm1 should:
     *    + Retrieve the name of client and the initial balance in the corresponding textfields.
     *      You may need to convert the retrieved text, e.g., "23.4" to a double value using Double.parseDouble.
     *    + Then, invoke the relevant method on the shared bank object to add a new client/account accordingly.
     *    + Finally, display the status of the bank to the output textview.
     *
     * - Controller method cm2 should:
     *    + Retrieve the chosen service type listed in the spinner (Deposit, Withdraw, Transfer, Print Statement)
     *    + Depending on the service type chosen, retrieve the from-account, to-account, and/or amount accordingly.
     *      (See the "Assumed Usage Pattern of the App" section in your Lab7 PDF instructions)
     *    + Then, invoke the relevant method(s), depending on the chosen service type, on the shared bank object.
     *    + Finally, display the status of the bank (in the case of a deposit, withdraw, or transfer)
     *          or the statement of an account (in the case of print statement) to the ouptut textview.
     */
	
}