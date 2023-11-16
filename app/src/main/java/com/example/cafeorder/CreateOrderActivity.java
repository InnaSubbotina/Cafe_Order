package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView textViewHello;
    private TextView textViewEdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;
    private String name;
    private String password;
    private StringBuilder builderAdditions;

//change for commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();

        if(intent.hasExtra("name")&& intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else { name = getString(R.string.defaul_name);
            password = getString(R.string.defaul_password);
        }
        drink = getString(R.string.tea);
        String hello = String.format(getString(R.string.welcome_name_order),name);
        textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setText(hello);
        textViewEdditions = findViewById(R.id.textViewEdditions);

        String adittions = String.format(getString(R.string.choose_add_tea),drink);
        textViewEdditions.setText(adittions);

        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee= findViewById(R.id.spinnerCoffee);
        builderAdditions = new StringBuilder();

    }

    public void onClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
        String adittions = String.format(getString(R.string.choose_add_tea),drink);
        textViewEdditions.setText(adittions);
    }

    public void onClickSendOrder(View view) {
        builderAdditions.setLength(0);
        if(checkBoxMilk.isChecked()){
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if(checkBoxSugar.isChecked()){
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if(checkBoxLemon.isChecked() && drink.equals (getString(R.string.tea))) {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String optionsOfDrink = "";
        if(drink.equals(R.string.tea)) {
            optionsOfDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionsOfDrink = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order), name,password,drink,optionsOfDrink);
        String additions;
        if (builderAdditions.length() > 0 ) {
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        } else {
            additions = "";
        }
        String fullOrder = order + additions;

        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order",fullOrder);
        startActivity(intent);

    }


}