package com.example.tic_tac_toe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;

public class MainActivity extends AppCompatActivity {

    public Button b1, b2, b3, b4, b5, b6, b7, b8, b9, reset;
    public EditText u1, u2;


    public boolean u1turn = true;
    private boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);
        u1 = findViewById(R.id.user1);
        u2 = findViewById(R.id.user2);
        reset = findViewById(R.id.reset);
        u1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        u2.setImeOptions(EditorInfo.IME_ACTION_DONE);

        reset.setEnabled(false);

        // Initially lock game
        securestart();

        // Start game automatically when both names are filled
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String u1name = u1.getText().toString().trim();
                String u2name = u2.getText().toString().trim();

                if (!u1name.isEmpty() && !u2name.isEmpty() && !gameStarted) {
                    gameStarted = true;
                    securestart2();// unlock buttons
                    initBoardClicks();
                }
            }
        };

        u1.addTextChangedListener(watcher);
        u2.addTextChangedListener(watcher);

        // Reset button
        reset.setOnClickListener(v -> {
            updatebtn();
            gameStarted = false; // allow restart after reset
            securestart();
        });

        reset.setOnClickListener(v -> {

                    updatebtn();
                    securestart2();

                }
        );
    }

    //  setup all board clicks
    private void initBoardClicks() {
        b1.setOnClickListener(v -> {
            handleMove(b1);
            Toast.makeText(MainActivity.this, "Game Started! ", Toast.LENGTH_SHORT).show();
        }
);
        b2.setOnClickListener(v -> handleMove(b2));
        b3.setOnClickListener(v -> handleMove(b3));
        b4.setOnClickListener(v -> handleMove(b4));
        b5.setOnClickListener(v -> handleMove(b5));
        b6.setOnClickListener(v -> handleMove(b6));
        b7.setOnClickListener(v -> handleMove(b7));
        b8.setOnClickListener(v -> handleMove(b8));
        b9.setOnClickListener(v -> handleMove(b9));
    }

    private void handleMove(Button btn) {
        if (btn.getText().toString().isEmpty()) {
            if (u1turn) {
                btn.setText("X");
                checkwin();
                u1turn = false;
            } else {
                btn.setText("O");
                checkwin();
                u1turn = true;
            }
        }
    }

    private void checkwin() {
        String t1 = b1.getText().toString();
        String t2 = b2.getText().toString();
        String t3 = b3.getText().toString();
        String t4 = b4.getText().toString();
        String t5 = b5.getText().toString();
        String t6 = b6.getText().toString();
        String t7 = b7.getText().toString();
        String t8 = b8.getText().toString();
        String t9 = b9.getText().toString();
        String u1name = u1.getText().toString().trim();
        String u2name = u2.getText().toString().trim();

        boolean winRow1 = !t1.isEmpty() && t1.equals(t2) && t2.equals(t3);
        boolean winRow2 = !t4.isEmpty() && t4.equals(t5) && t5.equals(t6);
        boolean winRow3 = !t7.isEmpty() && t7.equals(t8) && t8.equals(t9);

        boolean winCol1 = !t1.isEmpty() && t1.equals(t4) && t4.equals(t7);
        boolean winCol2 = !t2.isEmpty() && t2.equals(t5) && t5.equals(t8);
        boolean winCol3 = !t3.isEmpty() && t3.equals(t6) && t6.equals(t9);

        boolean winDiag1 = !t1.isEmpty() && t1.equals(t5) && t5.equals(t9);
        boolean winDiag2 = !t3.isEmpty() && t3.equals(t5) && t5.equals(t7);

        if (winRow1 || winRow2 || winRow3 || winCol1 || winCol2 || winCol3 || winDiag1 || winDiag2) {
            if (u1turn) {
                Toast.makeText(this, "Winner is " + u1name, Toast.LENGTH_LONG).show();
                reset.setEnabled(true);



            } else {
                Toast.makeText(this, "Winner is " + u2name, Toast.LENGTH_LONG).show();
                reset.setEnabled(true);

            }
            securestart();
        }

        if (!winRow1 && !winRow2 && !winRow3 && !winCol1 && !winCol2 && !winCol3 && !winDiag1 && !winDiag2 &&
                !t1.isEmpty() && !t2.isEmpty() && !t3.isEmpty() &&
                !t4.isEmpty() && !t5.isEmpty() && !t6.isEmpty() &&
                !t7.isEmpty() && !t8.isEmpty() && !t9.isEmpty()) {
            Toast.makeText(this,  "  Match Draw " , Toast.LENGTH_LONG).show();
            reset.setEnabled(true);
            securestart();


        }

        }

        public void securestart () {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b5.setEnabled(false);
            b6.setEnabled(false);
            b7.setEnabled(false);
            b8.setEnabled(false);
            b9.setEnabled(false);
        }

        public void securestart2 () {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);
            b5.setEnabled(true);
            b6.setEnabled(true);
            b7.setEnabled(true);
            b8.setEnabled(true);
            b9.setEnabled(true);
        }

        public void updatebtn () {
            b1.setText("");
            b2.setText("");
            b3.setText("");
            b4.setText("");
            b5.setText("");
            b6.setText("");
            b7.setText("");
            b8.setText("");
            b9.setText("");

            u1turn = true;
        }
    }

