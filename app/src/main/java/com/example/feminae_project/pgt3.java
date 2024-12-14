package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pgt3 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt3);


        ImageButton b1 = (ImageButton) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt3.this,pgt2.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt3.this,home.class);
                startActivity(in);
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt3.this,pgt3.class);
                startActivity(in);
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt3.this,pgt4.class);
                startActivity(in);
            }
        });
        ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt3.this,pgt5.class);
                startActivity(in);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        // Obtain a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

// Obtain a reference to the child node
        DatabaseReference childRef = databaseRef.child("User").child(userID).child("pggest");

        TextView texty=findViewById(R.id.text9);
        childRef.child("gest_age").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String gest_age = snapshot.getValue(String.class).trim();

                    // Convert gest_age to an integer
                    int gestAgeInt = 0; // Set default value to 0
                    try {
                        gestAgeInt = Integer.parseInt(gest_age);
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Error parsing gestational age to integer: " + e.getMessage());
                    }

                    // Update ImageView based on gest_age
                    switch (gestAgeInt) {
                        case 1:
                             texty.setText("The first few weeks of this month, you are not technically pregnant yet. Ovulation occurs near the middle of the month, and most mothers do not know they are pregnant even at the very end of the month. That doesn’t mean that changes aren’t happening inside your body. Your body makes these changes every month, but this time, your egg has been fertilized.");

                            break;
                        case 2:
                            texty.setText("A pregnancy test will usually alert you to your pregnancy this month when your period doesn’t come. You may also feel very tired. This fatigue is caused by the hormones flooding your body as it prepares to help your baby grow. You may also notice that you have an increased need to urinate and may even experience heartburn or morning sickness (nausea and vomiting).");
                            break;
                        // Add cases for other months and update ImageView and TextView accordingly
                        case 3:
                            texty.setText("While the uterus has been growing, it won’t be until the end of this month that it reaches the top of your pelvis. You probably haven’t gained more than a pound or two and may have even lost a few pounds if you’ve been sick or having aversions to some foods. You may also experience a decrease in libido in this first trimester of pregnancy. Your skin may glow, and you may have dark blotches on your face or lots of acne.");
                            break;
                        case 4:
                            texty.setText("You have made it to the second trimester! This means that you’ll likely start feeling more like your old self. You may not need as many naps, your morning sickness seems to fade away, and you may feel a spring in your step. You may feel these positive changes because the worry of early miscarriage is over, or because your hormones are a bit less volatile as the placenta has taken over the production of some hormones to sustain the pregnancy. Your sex drive is usually back to prepregnancy levels.");
                            break;
                        case 5:
                            texty.setText("This is a big month. The expanded blood volume has your heart beating a bit faster. Your kidneys are working hard, which means you are still making frequent trips to the bathroom. This month is also when you’ll most likely feel your baby move for the first time. Your uterus also reaches the point of your belly button, so you may have a few strangers take notice of your pregnancy.");
                            break;
                        case 6:
                            texty.setText("During this month of pregnancy, you likely feel great and are visibly pregnant. You may experience a bit more heartburn as your pregnant abdomen grows and makes less room in your stomach. You can also feel your baby more distinctly, which can be a lot of fun. Maternity clothes are most likely your primary wardrobe. You may also notice mild backaches occasionally.");
                            break;
                        case 7:
                            texty.setText("The third trimester is here, and you’re in the home stretch. For some, this can be a shock. Pregnancy seems like it will last forever, and now you realize that it’s almost time for your baby to arrive. This may make you start thinking more about planning for labor and bringing your baby home. You may become less interested in sex as you worry about your growing belly and the fatigue starts to set in. You may also begin to have practice contractions known as Braxton-Hicks contractions.");
                            break;
                        case 8:
                            texty.setText("Others can now feel your baby move, which may mean more hands on your belly than before. The fatigue is probably growing and you may also suffer from insomnia, which is an unfortunate combination. Remember to nap when you can, even if it’s not during traditional sleep hours. You may notice that your breasts have a slight discharge or film on them; this is normal and is just your body’s way of prepping for the baby. Your uterus may be so big that you have trouble breathing.");
                            break;
                        default:
                            // Set a default image if no matching case is found
                            texty.setText("Your body is now in get-ready mode. Your cervix will start changing at some point in these last weeks, depending on whether you’ve had a baby before, by softening and moving forward. The uterus will practice contracting until the big day. You may notice an increase in vaginal discharge. This is normal as long as it isn’t green and doesn’t have an offensive odor. Your comfort really depends on the baby’s position. For example, a low, head-down baby means better breathing, but more trips to the bathroom.");
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error retrieving gestational age value: " + error.getMessage());
            }
        });


    }
}