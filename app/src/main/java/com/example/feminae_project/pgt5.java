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

public class pgt5 extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgt5);

        ImageButton b1 = (ImageButton) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt5.this,pgt2.class);
                startActivity(in);
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt5.this,pgt3.class);
                startActivity(in);
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt5.this,pgt4.class);
                startActivity(in);
            }
        });
        ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt5.this,pgt5.class);
                startActivity(in);
            }
        });

        ImageButton imgp = (ImageButton) findViewById(R.id.home);
        imgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(pgt5.this,home.class);
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
                            texty.setText("Make an appointment with your OBGYN to verify that you’re pregnant and to make sure that the pregnancy appears normal.\n" +
                                    "Take a look at your insurance plan to understand what prenatal care is covered.\n" +
                                    "Start keeping a baby diary! You’re definitely going to have a variety of emotions, and you may want to write some of them down. It will be fun to look back on this later.\n" +
                                    "Adjust your diet. You’ll need nutrients for two now, so you should eat plenty of fruits and vegetables. Make sure to drink plenty of water, too!\n" +
                                    "Quit smoking and drinking alcohol. We know it’s not as fun but it’s definitely best for your baby.\n" +
                                    "Exercise regularly. You don’t need to do anything strenuous, but make sure you’re getting light exercise at least three times per week.\n" +
                                    "Take folic acid supplements. They have been proven to greatly reduce the risk of some birth defects.\n" +
                                    "Rest! Remember that you’ll be exhausted in the early stages of your pregnancy, so make sure to get plenty of sleep whenever you can. It’s OK if you aren’t as productive as you used to be!\n" +
                                    "If you’re dealing with morning sickness, use ginger to settle your stomach and eat small, frequent meals.\n");
                            break;
                        case 2:
                            texty.setText("Schedule your prenatal tests. This is a crucial part of your pregnancy checklist! Doctors are busy, so it’s best to make your appointments in advance. Talk to your doctor to find out what prenatal test schedule they recommend.\n" +
                                    "Start budgeting for the baby. Having a baby isn’t cheap, so begin planning your finances right away.\n" +
                                    "Spend time with your partner! This is important, but it’s something that’s easy to overlook. Make sure you’re getting enough one-on-one time with your partner. Once your baby arrives, it might be hard to find time to be intimate with your loved one.\n" +
                                    "Decide where and how you’d like to give birth. You may want to deliver in a hospital with an OBGYN. Or you may want to have your baby in the comfort of your own home with the help of a midwife and doula. We recommend doing some research before deciding which option is right for you.");
                            break;
                        // Add cases for other months and update ImageView and TextView accordingly
                        case 3:
                            texty.setText("Decide whether or not you’d like to learn the sex of the baby. Some couples like to know, while others want to wait until the baby is born to find out. Talk with your partner and decide what’s best for you.\n" +
                                    "Research the maternity leave policy at your workplace. You might not be using this for some time, but ticking it off the to-do list early gives you one less thing to worry about.\n" +
                                    "Make an appointment with a dentist. Pregnant women are more susceptible to inflamed gums and gingivitis, so it’s important to get a cleaning and checkup with your dentist.\n" +
                                    "Look into childbirth classes, such as the Lamaze Technique, The Bradley Method, and HypnoBirthing, to name a few. These classes can help a great deal when you go into labor. But schedule them early, as they tend to fill up quickly.\n" +
                                    "Buy comfortable bras that fit your changing, tender breasts. Even though your belly isn’t big enough to need maternity clothes, your breasts might have grown enough to go up a bra size!");
                            break;
                        case 4:
                            texty.setText("Announce the good news! Your friends and family will be overjoyed to know that you have a baby on the way. Now is also a good time to let your colleagues and managers know that you’re pregnant.\n" +
                                    "Start shopping for maternity clothes. After the fourth month, your bump will start to grow considerably, so make sure you’ve got all the right attire.\n" +
                                    "Stock up on the skincare products you need! Try Mustela Soothing Moisturizing Balm, specially formulated to relieve itching sensations caused by pregnancy. And use our Stretch Marks Cream or Oil to ensure smooth, glamorous skin before and after giving birth.\n" +
                                    "Go on your babymoon! A babymoon is a pre-baby vacation for you and your partner. It’s a great way to spend time together before the later stages of your pregnancy make travel difficult. If you hit the beach, bring lots of sunscreen and learn about sunbathing with a bun in the oven.\n" +
                                    "Continue taking care of your body. This includes eating healthy, drinking water, and exercising regularly.");
                            break;
                        case 5:
                            texty.setText("Decide where your baby will sleep. Perhaps you’d like to have your newborn sleep in your room, or maybe you’d like to set up a nursery. Either way, you can start preparing now.\n" +
                                    "If you have chosen to find out the sex of your baby, decide if and how you want to tell your friends and family! A gender reveal party is a fun way to share the news and spend time with your loved ones.\n" +
                                    "Have your anomaly scan and ultrasound exam performed. These are important prenatal tests, as they ensure the health of your unborn baby.\n" +
                                    "Keep meeting with the person who will be delivering your baby. Developing this relationship will help you feel more comfortable in the delivery room.\n" +
                                    "Decide whether you will breastfeed or formula feed. If you’re planning on nursing, learn about different breastfeeding positions and what to expect. Be proactive and start using Nursing Comfort Balm now.");
                            break;
                        case 6:
                            texty.setText("Start planning your baby shower! This is an exciting part of any pregnancy. Get a group of close friends and family together to plan the perfect baby shower in your honor.\n" +
                                    "Research childcare options. The next few months are going to fly by, so looking into childcare options now will help you take care of other tasks later. You want to be as prepared as possible for your little bundle of joy.\n" +
                                    "Begin thinking about your baby’s name! You’ve probably been doing this since day one, but now’s the time to start talking with your partner about possible names.\n" +
                                    "Plan maternity leave with your employer. This is an important part of the pregnancy checklist. Setting up your maternity leave well in advance ensures a smooth transition away from, and back to, work.\n" +
                                    "Write a birth plan. This simply means thinking through your preferences and expectations for delivery day, then putting it all down on paper so everyone (doctors, midwife, your partner, etc.) is on the same page.");
                            break;
                        case 7:
                            texty.setText("\n" +
                                    "Ask friends and family for baby hand-me-downs. You might be able to borrow baby clothes, furniture, and strollers from friends and family members with older children.\n" +
                                    "Create a baby registry for your baby shower to let friends and family know which items would be the most helpful as you prepare for your baby’s arrival.\n" +
                                    "Connect with other soon-to-be-moms. This will give you the chance to share experiences, get advice about childbearing, and make new friends.\n" +
                                    "Find a good pediatrician. This will save you the hassle of scheduling various appointments with different pediatricians after your little one has arrived.");
                            break;
                        case 8:
                            texty.setText("Enjoy your baby shower! This should be a special time for you to connect with your loved ones and celebrate.\n" +
                                    "Finish setting up the nursery or the place where your baby will sleep. Your new little one will be here before you know it!\n" +
                                    "Pre-cook meals that you can keep in the freezer. This will help for those nights when you’re too tired or too busy to cook a nice meal at home.\n" +
                                    "Wash your baby’s clothes and sheets. The fabrics will be more comfortable for your baby as a result, and it’s much easier to get this task out of the way now.\n" +
                                    "Stock up on the baby products you’ll inevitably need like baby oils, moisturizers, and cleansers.");
                            break;
                        default:
                            // Set a default image if no matching case is found
                            texty.setText("Name your baby! You may have already chosen a name, but now’s the time to speak with your partner and finalize your decision.\n" +
                                    "Pack your hospital bag. Things like a change of clothes, toiletries, snacks, and cell phone chargers should all get packed in the hospital bag.\n" +
                                    "Pre-register with the hospital. This will make checking in to the hospital much easier when the big day comes since all of the paperwork will already be taken care of.\n" +
                                    "Make plans for your other children and pets. You could go into labor at any moment now, so knowing who will take care of your other children and your pets while you’re in the hospital is critical.\n" +
                                    "Install a baby seat in your car. Later on, you’ll thank yourself for doing this early on.\n" +
                                    "Relax and wait for the big day! This is probably the most important thing you can do. Enjoy the final moments of your pregnancy, and imagine how much love you’re going to shower your little one with.\n");
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