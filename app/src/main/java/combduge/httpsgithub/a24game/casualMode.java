package combduge.httpsgithub.a24game;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class casualMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        //initializes the new numbers
        final number number1 = new number();
        final number number2 = new number();
        final number number3 = new number();
        final number number4 = new number();

        //initializes boolean status
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //initializes Calculator
        final Calculator eval = new Calculator();

        //initializes textviews
        final TextView equation = findViewById(R.id.equationBox);

        //sets the buttons on the screen to the generated numbers and also event handlers for each button
        //after each number is pressed, it cannot be pressed again to prevent users from typing the number twice
        //number1
        final Button button1 = findViewById(R.id.numberOne);
        button1.setText(number1.valueToString());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation(number1.valueToString());
                equation.setText(eval.getEquation());
                button1.setEnabled(false);
                number1.setNumberUsed(true);
            }
        });


        //number2
        final Button button2 = findViewById(R.id.numberTwo);
        button2.setText(number2.valueToString());
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eval.writeToEquation(number2.valueToString());
                equation.setText(eval.getEquation());
                button2.setEnabled(false);
                number2.setNumberUsed(true);
            }
        });


        //number3
        final Button button3 = findViewById(R.id.numberThree);
        button3.setText(number3.valueToString());
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation(number3.valueToString());
                equation.setText(eval.getEquation());
                button3.setEnabled(false);
                number3.setNumberUsed(true);
            }
        });

        //number4
        final Button button4 = findViewById(R.id.numberFour);
        button4.setText(number4.valueToString());
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation(number4.valueToString());
                equation.setText(eval.getEquation());
                button4.setEnabled(false);
                number4.setNumberUsed(true);
            }
        });



        //handlers for the buttons to add to the equation

        //add
        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation("+");
                equation.setText(eval.getEquation());
            }
        });

        //subtract
        Button subButton = findViewById(R.id.subtract);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation("-");
                equation.setText(eval.getEquation());
            }
        });

        //multiply
        Button multiButton = findViewById(R.id.multiply);
        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation("*");
                equation.setText(eval.getEquation());
            }
        });

        //divide
        Button divButton = findViewById(R.id.divide);
        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation("/");
                equation.setText(eval.getEquation());
            }
        });

        //start bracket
        Button sBracketButton = findViewById(R.id.startBracket);
        sBracketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation("(");
                equation.setText(eval.getEquation());
            }
        });

        //end bracket
        Button eBracketButton = findViewById(R.id.endBracket);
        eBracketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.writeToEquation(")");
                equation.setText(eval.getEquation());
            }
        });

        //clears equation box and makes all numbers clickable again
        Button clearButton = findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eval.clearEquation();
                equation.setText(eval.getEquation());
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);

                number1.setNumberUsed(false);
                number2.setNumberUsed(false);
                number3.setNumberUsed(false);
                number4.setNumberUsed(false);
            }
        });

        //after all 4 numbers are inputted, user can press enter to finalize their answer

        final Button enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number1.getNumberUsed() && number2.getNumberUsed()
                        && number3.getNumberUsed() && number4.getNumberUsed()){

                    try{
                        boolean x = eval.checkAnswer(eval.getEquation());
                        AlertDialog.Builder builder = new AlertDialog.Builder(casualMode.this);
                        if(x){
                            builder.setMessage("Correct!")
                                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                            AlertDialog winNotification = builder.create();
                            winNotification.setTitle("24 Game");
                            winNotification.show();
                        }
                        else if(!x){
                            builder .setMessage("Incorrect")
                                    .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        }
                                    });
                            AlertDialog loseNotification = builder.create();
                            loseNotification.setTitle("24 Game");
                            loseNotification.show();
                        }
                        number1.generateNewValue();
                        number2.generateNewValue();
                        number3.generateNewValue();
                        number4.generateNewValue();

                        button1.setText(number1.valueToString());
                        button2.setText(number2.valueToString());
                        button3.setText(number3.valueToString());
                        button4.setText(number4.valueToString());

                        eval.clearEquation();
                        equation.setText(eval.getEquation());
                        button1.setEnabled(true);
                        button2.setEnabled(true);
                        button3.setEnabled(true);
                        button4.setEnabled(true);

                    }catch(Exception e){
                        builder .setMessage("Please enter a valid equation")
                                .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                        AlertDialog validAlert = builder.create();
                        validAlert.setTitle("24 Game");
                        validAlert.show();
                    }

                }
                else{
                    builder.setMessage("You must use all four numbers")
                            .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    AlertDialog info = builder.create();
                    info.setTitle("24 Game");
                    info.show();
                }
            }
        });

        Button skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                number1.generateNewValue();
                number2.generateNewValue();
                number3.generateNewValue();
                number4.generateNewValue();

                button1.setText(number1.valueToString());
                button2.setText(number2.valueToString());
                button3.setText(number3.valueToString());
                button4.setText(number4.valueToString());

                eval.clearEquation();
                equation.setText(eval.getEquation());
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
            }
        });

    }


}

