package com.example.feminae_project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pgt4 extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt4);

        ImageButton b1 = (ImageButton) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt4.this,pgt2.class);
                startActivity(in);
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt4.this,pgt3.class);
                startActivity(in);
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt4.this,pgt4.class);
                startActivity(in);
            }
        });
        ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt4.this,pgt5.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt4.this,home.class);
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
                            texty.setText("The baby is an embryo in the first month of pregnancy. It is suggested that you must have a snack that is rich in carbohydrate. To deal with the morning sickness you ought to consume meals that are comparatively smaller than the ones you usually have. However, you ought to increase the frequency of having food. Go in for easy to digest food and consume as much liquid as possible. Say no to high fat, spicy and fried food. Opt for folate-rich and Vitamin B6 rich food.");
                            break;
                        case 2:
                            texty.setText("The size of a baby is that of a kidney bean now.  During the second month, you must consume ginger for nausea. Apart from this, you must focus on getting as much vitamin E as possible. Do not forget to add raw almonds, olive oil, avocado, sunflower seeds, egg yolk and hazelnuts in your diet.");
                            break;
                        // Add cases for other months and update ImageView and TextView accordingly
                        case 3:
                            texty.setText("3 month old baby is almost 3 inches long by now and weighs equal to a pea pod. Ensure continuing the month two diet. It is now important that you add to your diet chart at least 10 glasses of water in a day. Also, you must have fluid-filled fruits as well as vegetables. All of these helps to keep the baby hydrated.");
                            break;
                        case 4:
                            texty.setText("4-Month-Old Baby Weight & Length: By this time, your baby is almost 5.5 inches long weighing 140g. Now you need to go in for consuming as much iron-rich foods as possible. In addition to this, you must go in for having protein intake. Prefer food like eggs plus free-range meats if you are non-vegetarian and if you are vegetarian then consume iron-rich plant foods like leafy greens as well as legumes. Do not forget to add a source of vitamin C to your diet.");
                            break;
                        case 5:
                            texty.setText("Now you have come almost midway through your pregnancy period. Carrying the 5 month old baby in your belly now gets a little difficult as the baby tends to gain weight. In the fifth month, the baby is around 10.5inches long. It is important that you now add foods to your diet that are rich in calcium like almonds, tahini, green leafy vegetables. Also, go in for consuming broccoli, oranges as well as tomatoes.");
                            break;
                        case 6:
                            texty.setText("6 Month old baby now weighs nearly 660g and you have probably started to experience and feel the growth of the baby already. Now you tend to feel hungrier on one hand and suffer from problems like constipation on the other hand. Going in for food like whole-grains, vegetables, fruits and legumes is highly suggested to prevent suffering from constipation. Also, it is suggested that you have one tablespoon of psyllium mixed with a glass of water prior to going to the bed.");
                            break;
                        case 7:
                            texty.setText("The baby now gains more weight and is almost 15 inches long. While your baby is developing, on one hand, you suffer from heartburn on the other hand. This month, it is important that you avoid having high-fat or fried foods and say a complete no to the spicy foods. In this stage, it is important for your body to have an adequate supply of protein to reduce the chances of preeclampsia and morning sickness apart from other complications.");
                            break;
                        case 8:
                            texty.setText("Your baby most probably weighs almost 2.4 kg  now. You now begin to experience frequent urination and backaches apart from breath shortness and sleeplessness. You must now go in for consumption of omega 3 fatty acids. Opt for consuming oily fish like salmon in addition to nuts and seeds. Do not forget to add ground flax seeds to your diet. Adding sour cherries is also recommended.");
                            break;
                        default:
                            // Set a default image if no matching case is found
                            texty.setText("Now the baby is almost 20.5 inches long and weighs almost 3.4 kg. you are now suggested to add garlic to your 9 month baby food chart. Also, do not forget to eat dates on daily basis. Last but not least, it is very important for you to consume 2 handfuls of dried raisins on weekly basis.");
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