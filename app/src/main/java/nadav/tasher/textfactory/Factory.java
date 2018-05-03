package nadav.tasher.textfactory;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.secrypto.java.Crypto;

import java.util.ArrayList;
import java.util.Random;

import nadav.tasher.lightool.graphics.views.appview.AppView;
import nadav.tasher.lightool.info.Device;
import nadav.tasher.lightool.tools.Animations;
import nadav.tasher.lightool.tools.transfer.Clip;

public class Factory extends Activity {
    static final int appColor = 0xFF45679a;
    private int history = 0;
    private EditText input;
    private Clip<String> clip = new Clip<>();
    private AppView appView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStageA();
    }

    private void initStageA() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        initStageB();
    }

    private void initStageB() {
        final Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        appView = new AppView(getApplicationContext(), getDrawable(R.drawable.ic_tefa_r), 0x333333);
        appView.setBackgroundColor(appColor);
        appView.overlaySelf(getWindow());
        initStageBC();
    }

    private void initStageBC() {
        final Bitmap ico = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(getString(R.string.app_name), ico, appView.getDrag().calculateOverlayedColor(appColor));
        setTaskDescription(taskDescription);
        initStageC();
    }

    private void initStageC() {
        LinearLayout all=new LinearLayout(getApplicationContext());
        all.setOrientation(LinearLayout.VERTICAL);
        all.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.START);
        LinearLayout bottom=new LinearLayout(getApplicationContext());
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setGravity(Gravity.CENTER);
        LinearLayout left=new LinearLayout(getApplicationContext());
        left.setOrientation(LinearLayout.VERTICAL);
        left.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.START);
        LinearLayout right=new LinearLayout(getApplicationContext());
        right.setOrientation(LinearLayout.VERTICAL);
        right.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.START);
        input = new EditText(this);
        input.setHint("Your Text");
        if (getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            input.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        }
        input.setPadding(10, 10, 10, 10);
        input.setTextColor(Color.BLACK);
        input.setHintTextColor(Color.GRAY);
        input.setBackground(getDrawable(R.drawable.back));
        input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_FLAG_MULTI_LINE | EditorInfo.IME_ACTION_NONE);
        final Button empty = new Button(this), pst = new Button(this), eyn = new Button(this), reverse = new Button(this), replace = new Button(this), multiply = new Button(this), copy = new Button(this), tocapital = new Button(this), tononcapital = new Button(this), randomize = new Button(this), share = new Button(this), undo = new Button(this);
        reverse.setText(R.string.rev);
        pst.setText(R.string.pst);
        eyn.setText(R.string.endy);
        undo.setText(R.string.undo);
        empty.setText(R.string.emp);
        replace.setText(R.string.rpl);
        multiply.setText(R.string.mp);
        copy.setText(R.string.cp);
        tocapital.setText(R.string.uc);
        tononcapital.setText(R.string.lc);
        randomize.setText(R.string.msu);
        share.setText(R.string.sr);
        left.addView(empty);
        left.addView(undo);
        left.addView(copy);
        left.addView(share);
        right.addView(pst);
        left.addView(eyn);
        left.addView(reverse);
        right.addView(replace);
        right.addView(multiply);
        right.addView(tocapital);
        right.addView(tononcapital);
        right.addView(randomize);
        copy.setBackground(getDrawable(R.drawable.button));
        pst.setBackground(getDrawable(R.drawable.button));
        eyn.setBackground(getDrawable(R.drawable.button));
        undo.setBackground(getDrawable(R.drawable.button));
        share.setBackground(getDrawable(R.drawable.button));
        reverse.setBackground(getDrawable(R.drawable.button));
        empty.setBackground(getDrawable(R.drawable.button));
        replace.setBackground(getDrawable(R.drawable.button));
        multiply.setBackground(getDrawable(R.drawable.button));
        tocapital.setBackground(getDrawable(R.drawable.button));
        tononcapital.setBackground(getDrawable(R.drawable.button));
        randomize.setBackground(getDrawable(R.drawable.button));
        copy.setTransformationMethod(null);
        pst.setTransformationMethod(null);
        eyn.setTransformationMethod(null);
        share.setTransformationMethod(null);
        undo.setTransformationMethod(null);
        reverse.setTransformationMethod(null);
        empty.setTransformationMethod(null);
        replace.setTransformationMethod(null);
        multiply.setTransformationMethod(null);
        tocapital.setTransformationMethod(null);
        tononcapital.setTransformationMethod(null);
        randomize.setTransformationMethod(null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Device.screenY(getApplicationContext()) / 2));
        input.setVerticalScrollBarEnabled(true);
        reverse.setLayoutParams(lp);
        eyn.setLayoutParams(lp);
        pst.setLayoutParams(lp);
        undo.setLayoutParams(lp);
        empty.setLayoutParams(lp);
        replace.setLayoutParams(lp);
        multiply.setLayoutParams(lp);
        copy.setLayoutParams(lp);
        tocapital.setLayoutParams(lp);
        tononcapital.setLayoutParams(lp);
        randomize.setLayoutParams(lp);
        share.setLayoutParams(lp);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undo();
            }
        });
        eyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Factory.this);
                ab.setTitle("Encrypt");
                final EditText edt = new EditText(getApplicationContext());
                edt.setHint("Key");
                ab.setView(edt);
                ab.setPositiveButton("Encrypt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog pd;
                        pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Encrypting...");
                        pd.setCancelable(false);
                        pd.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                final String text1 = Crypto.encrypt(input.getText().toString(), edt.getText().toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        clip.set(input.getText().toString());
                                        history = 0;
                                        input.setText(text1);
                                    }
                                });
                            }
                        }).start();
                    }
                });
                ab.show();
            }
        });
        eyn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Factory.this);
                ab.setTitle("Decrypt");
                LinearLayout ll = new LinearLayout(getApplicationContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                final EditText edt = new EditText(getApplicationContext());
                edt.setHint("Key");
                ab.setView(edt);
                ab.setPositiveButton("Decrypt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog pd;
                        pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Decrypting...");
                        pd.setCancelable(false);
                        pd.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                final String text1 = Crypto.decrypt(input.getText().toString(), edt.getText().toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        clip.set(input.getText().toString());
                                        history = 0;
                                        input.setText(text1);
                                    }
                                });
                            }
                        }).start();
                    }
                });
                ab.show();
                return true;
            }
        });
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd;
                pd = new ProgressDialog(Factory.this);
                pd.setMessage("Reversing...");
                pd.setCancelable(false);
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = reverse(input.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        });
                    }
                }).start();
            }
        });
        pst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                final String s = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                clip.set(input.getText().toString());
                input.setText(s);
            }
        });
        pst.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                final String s = input.getText().toString() + clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                clip.set(input.getText().toString());
                input.setText(s);
                return true;
            }
        });
        reverse.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final ProgressDialog pd;
                pd = new ProgressDialog(Factory.this);
                pd.setMessage("Word Reversing...");
                pd.setCancelable(false);
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String finale = "";
                        String[] words = input.getText().toString().split(" ");
                        for (int i = 0; i < words.length; i++) {
                            if (finale.equals("")) {
                                finale = finale + reverse(words[i]);
                            } else {
                                finale = finale + " " + reverse(words[i]);
                            }
                        }
                        final String finalE = finale;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(finalE);
                            }
                        });
                    }
                }).start();
                return true;
            }
        });
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clip.set(input.getText().toString());
                input.setText("");
            }
        });
        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Factory.this);
                ab.setTitle("Replace");
                LinearLayout ll = new LinearLayout(getApplicationContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                final EditText edt = new EditText(getApplicationContext());
                edt.setHint("Text To Replace");
                final EditText tr = new EditText(getApplicationContext());
                tr.setHint("Replacement Text");
                ll.addView(edt);
                ll.addView(tr);
                ab.setView(ll);
                ab.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog pd;
                        pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Replacing...");
                        pd.setCancelable(false);
                        pd.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String text1 = input.getText().toString();
                                if (text1.contains(edt.getText().toString())) {
                                    final String nt = text1.replaceAll(edt.getText().toString(), tr.getText().toString());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            pd.cancel();
                                            clip.set(input.getText().toString());
                                            history = 0;
                                            input.setText(nt);
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                });
                ab.show();
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Factory.this);
                ab.setTitle("Multiply");
                final EditText edt = new EditText(getApplicationContext());
                edt.setHint("Number Of Times");
                edt.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                ab.setView(edt);
                ab.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog pd;
                        pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Multiplying...");
                        pd.setCancelable(false);
                        pd.show();
                        final String text1 = input.getText().toString();
                        final int times = Integer.parseInt(edt.getText().toString());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String ret = "";
                                for (int i = 0; i < times; i++) {
                                    ret = ret + text1;
                                }
                                final String ret2 = ret;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pd.cancel();
                                        clip.set(input.getText().toString());
                                        history = 0;
                                        input.setText(ret2);
                                    }
                                });
                            }
                        }).start();
                    }
                });
                ab.show();
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextFactoryData", input.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
        tocapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = input.getText().toString().toUpperCase();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        });
                    }
                }).start();
            }
        });
        tocapital.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final ProgressDialog pd;
                pd = new ProgressDialog(Factory.this);
                pd.setMessage("Wordify...");
                pd.setCancelable(false);
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String old = input.getText().toString();
                        final StringBuilder b = new StringBuilder(old);
                        for (int a = 0; a < old.length(); a++) {
                            if (a == 0 || old.charAt(a - 1) == ' ') {
                                b.setCharAt(a, (String.valueOf(old.charAt(a))).toUpperCase().charAt(0));
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(b.toString());
                            }
                        });
                    }
                }).start();
                return true;
            }
        });
        tononcapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = input.getText().toString().toLowerCase();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        });
                    }
                }).start();
            }
        });
        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd;
                pd = new ProgressDialog(Factory.this);
                pd.setMessage("Randomizing...");
                pd.setCancelable(false);
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String nw = "";
                        String text1 = input.getText().toString();
                        ArrayList<String> allth = new ArrayList<>();
                        for (int i = 0; i < text1.length(); i++) {
                            allth.add(text1.charAt(i) + "");
                        }
                        for (int i = 0; i < text1.length(); i++) {
                            int rnd = new Random().nextInt(allth.size());
                            nw = nw + allth.get(rnd);
                            allth.remove(rnd);
                        }
                        final String nw2 = nw;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(nw2);
                            }
                        });
                    }
                }).start();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share(input);
            }
        });
        all.addView(input);
        all.addView(bottom);
        bottom.addView(left);
        bottom.addView(right);
        left.setLayoutParams(new LinearLayout.LayoutParams(Device.screenX(getApplicationContext())/2, ViewGroup.LayoutParams.MATCH_PARENT));
        right.setLayoutParams(new LinearLayout.LayoutParams(Device.screenX(getApplicationContext())/2, ViewGroup.LayoutParams.MATCH_PARENT));
        appView.setContent(all);
        setContentView(appView);
    }

    void share(EditText et) {
        Intent s = new Intent(Intent.ACTION_SEND);
        s.putExtra(Intent.EXTRA_TEXT, et.getText().toString());
        s.setType("text/plain");
        s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(s, "Share"));
    }

    private String reverse(String s) {
        String n = "";
        for (int a = s.length() - 1; a >= 0; a--) {
            n += s.charAt(a);
        }
        return n;
    }

    void undo() {
        input.setText(clip.getHistory().get(clip.getHistory().size() - 1 - history));
        if (history < clip.getHistory().size() - 1)
            history++;
    }

    @Override
    public void onBackPressed() {
        undo();
    }
}
