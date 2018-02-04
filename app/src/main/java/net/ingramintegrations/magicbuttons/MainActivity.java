package net.ingramintegrations.magicbuttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*  ============================= Important Note ==============================
    This code is based off of the following GitHub repo:

    https://github.com/bloderxd/MagicButton


 */

public class MainActivity extends AppCompatActivity {
//  Create a regular button and three MagicButtons.
    Button normalButton;
    MagicButton FacebookButton, GitHubButton, TwitterButton;

//  Create an intent called goToActiviity
    Intent goToActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Hook UI elements to the elements in your layout file
        normalButton = (Button) findViewById(R.id.act_main_bt_normal_button);
        FacebookButton = (MagicButton) findViewById(R.id.magic_button);
        GitHubButton = (MagicButton) findViewById(R.id.github_button);
        TwitterButton = (MagicButton) findViewById(R.id.twitter_button);

//      Create a click listener for your normal button.
        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              When clicked, go to the Activity SecondActivity
                goToActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(goToActivity);
            }
        });

/*
        ============================= Configure Auto Close Time ==============================
        Setup the auto close time for your magic buttons by calling setAutocloseDuration() and
        passing in the number of seconds the button should wait before it auto closes.

        setAutoCloseDuration takes in Seconds NOT Milliseconds.

        For example...

        FacebookButton.setAutoCloseDuration(3);

        will set the MagicButton called FacebookButton to close automatically after 3 seconds.

        ============================== Configure Icon Animation ==============================

        To animate the icon when the button is clicked call setAnimateIcon and pass true or
        false.

        setAnimateIcon(true) turns on animations.
        setAnimateIcon(false) turns off animations.

        =======================================================================================
 */
        FacebookButton.setAutoCloseDuration(3);
        FacebookButton.setAnimateIcon(true);

        GitHubButton.setAutoCloseDuration(3);
        GitHubButton.setAnimateIcon(true);

        TwitterButton.setAutoCloseDuration(3);
        TwitterButton.setAnimateIcon(true);

/*
        ============================== Configure Click Listener ================================

        Configure your click listener for the MagicButton by calling setMagicButtonClickListener

        The MagicButton OverRides the setClickListener method to add extra functionality that is
        specific to MagicButtons.

        In your class though, treat it like any other ClickListener as you can see below.

        ========================================================================================
*/

        FacebookButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(goToActivity);
            }
        });

        GitHubButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(goToActivity);
            }
        });

        TwitterButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(goToActivity);
            }
        });

    }


}
