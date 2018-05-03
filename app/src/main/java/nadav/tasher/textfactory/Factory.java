package nadav.tasher.textfactory;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.secrypto.java.Crypto;

import java.util.ArrayList;
import java.util.Random;

import nadav.tasher.lightool.graphics.views.appview.AppView;
import nadav.tasher.lightool.graphics.views.appview.navigation.Drag;
import nadav.tasher.lightool.info.Device;
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
        appView.getDrag().setOnStateChangedListener(new Drag.OnStateChangedListener() {
            @Override
            public void onOpen() {
                appView.getDrag().close(true);
            }

            @Override
            public void onClose() {
                appView.getDrag().emptyContent();
            }
        });
        initStageBC();
    }

    private void initStageBC() {
        final Bitmap ico = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(getString(R.string.app_name), ico, appView.getDrag().calculateOverlayedColor(appColor));
        setTaskDescription(taskDescription);
        initStageC();
    }

    private void initStageC() {
        LinearLayout all = new LinearLayout(getApplicationContext());
        all.setOrientation(LinearLayout.VERTICAL);
        all.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
        LinearLayout bottom = new LinearLayout(getApplicationContext());
        bottom.setOrientation(LinearLayout.HORIZONTAL);
        bottom.setGravity(Gravity.CENTER);
        LinearLayout left = new LinearLayout(getApplicationContext());
        left.setOrientation(LinearLayout.VERTICAL);
        left.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
        LinearLayout middle = new LinearLayout(getApplicationContext());
        middle.setOrientation(LinearLayout.VERTICAL);
        middle.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
        LinearLayout right = new LinearLayout(getApplicationContext());
        right.setOrientation(LinearLayout.VERTICAL);
        right.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START);
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
        final Button empty = new Button(this), antiMalware = new Button(this), specialChars = new Button(this), paste = new Button(this), append = new Button(this), encrypt = new Button(this), decrypt = new Button(this), reverse = new Button(this), reverseWords = new Button(this), replace = new Button(this), multiply = new Button(this), copy = new Button(this), wordify = new Button(this), upper = new Button(this), lower = new Button(this), randomize = new Button(this), share = new Button(this), undo = new Button(this);
        reverse.setText(R.string.reverse);
        paste.setText(R.string.paste);
        append.setText(R.string.append);
        specialChars.setText(R.string.special_chars);
        antiMalware.setText(R.string.anti_malware);
        encrypt.setText(R.string.encrypt);
        decrypt.setText(R.string.decrypt);
        undo.setText(R.string.undo);
        empty.setText(R.string.empty);
        replace.setText(R.string.replace);
        multiply.setText(R.string.multiply);
        copy.setText(R.string.copy);
        wordify.setText(R.string.wordify);
        upper.setText(R.string.upper);
        lower.setText(R.string.lower);
        reverseWords.setText(R.string.reverse_words);
        randomize.setText(R.string.randomize);
        share.setText(R.string.share);
        left.addView(empty);
        left.addView(undo);
        left.addView(copy);
        left.addView(share);
        left.addView(reverse);
        left.addView(antiMalware);
        middle.addView(encrypt);
        middle.addView(decrypt);
        middle.addView(wordify);
        middle.addView(reverseWords);
        middle.addView(specialChars);
        middle.addView(randomize);
        right.addView(paste);
        right.addView(append);
        right.addView(replace);
        right.addView(multiply);
        right.addView(upper);
        right.addView(lower);
        copy.setBackground(getDrawable(R.drawable.button));
        paste.setBackground(getDrawable(R.drawable.button));
        append.setBackground(getDrawable(R.drawable.button));
        specialChars.setBackground(getDrawable(R.drawable.button));
        antiMalware.setBackground(getDrawable(R.drawable.button));
        encrypt.setBackground(getDrawable(R.drawable.button));
        decrypt.setBackground(getDrawable(R.drawable.button));
        undo.setBackground(getDrawable(R.drawable.button));
        share.setBackground(getDrawable(R.drawable.button));
        reverse.setBackground(getDrawable(R.drawable.button));
        empty.setBackground(getDrawable(R.drawable.button));
        replace.setBackground(getDrawable(R.drawable.button));
        multiply.setBackground(getDrawable(R.drawable.button));
        upper.setBackground(getDrawable(R.drawable.button));
        lower.setBackground(getDrawable(R.drawable.button));
        reverseWords.setBackground(getDrawable(R.drawable.button));
        wordify.setBackground(getDrawable(R.drawable.button));
        randomize.setBackground(getDrawable(R.drawable.button));
        copy.setTransformationMethod(null);
        paste.setTransformationMethod(null);
        append.setTransformationMethod(null);
        specialChars.setTransformationMethod(null);
        antiMalware.setTransformationMethod(null);
        encrypt.setTransformationMethod(null);
        decrypt.setTransformationMethod(null);
        share.setTransformationMethod(null);
        undo.setTransformationMethod(null);
        reverse.setTransformationMethod(null);
        empty.setTransformationMethod(null);
        replace.setTransformationMethod(null);
        multiply.setTransformationMethod(null);
        upper.setTransformationMethod(null);
        lower.setTransformationMethod(null);
        wordify.setTransformationMethod(null);
        reverseWords.setTransformationMethod(null);
        randomize.setTransformationMethod(null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Device.screenY(getApplicationContext()) / 2));
        input.setVerticalScrollBarEnabled(true);
        reverse.setLayoutParams(lp);
        encrypt.setLayoutParams(lp);
        decrypt.setLayoutParams(lp);
        paste.setLayoutParams(lp);
        append.setLayoutParams(lp);
        specialChars.setLayoutParams(lp);
        antiMalware.setLayoutParams(lp);
        undo.setLayoutParams(lp);
        empty.setLayoutParams(lp);
        replace.setLayoutParams(lp);
        multiply.setLayoutParams(lp);
        copy.setLayoutParams(lp);
        upper.setLayoutParams(lp);
        lower.setLayoutParams(lp);
        wordify.setLayoutParams(lp);
        reverseWords.setLayoutParams(lp);
        randomize.setLayoutParams(lp);
        share.setLayoutParams(lp);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undo();
            }
        });
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.addView(new Title(getApplicationContext(), "Encrypt Menu"));
                layout.addView(new Explain(getApplicationContext(), "Enter The Encryption Key To Encrypt Your Text:"));
                final EditText edit = new EditText(getApplicationContext());
                edit.setHint("Key");
                edit.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                edit.setHintTextColor(Color.LTGRAY);
                edit.setTextColor(Color.WHITE);
                layout.addView(edit);
                Button go = new Button(getApplicationContext());
                go.setText(getString(R.string.encrypt));
                go.setBackground(null);
                go.setAllCaps(true);
                go.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                go.setTextColor(Color.WHITE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Working...");
                        pd.setCancelable(false);
                        pd.show();
                        new RunInBackground(new RunInBackground.Processed() {
                            @Override
                            public void processed(String s) {
                                pd.cancel();
                                appView.getDrag().close(true);
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        }, new RunInBackground.Process() {
                            @Override
                            public String process() {
                                try {
                                    return Crypto.encrypt(input.getText().toString(), edit.getText().toString());
                                } catch (OutOfMemoryError | Exception ignored) {
                                    return input.getText().toString();
                                }
                            }
                        }).execute();
                    }
                });
                layout.addView(go);
                appView.getDrag().setContent(layout);
                appView.getDrag().open(false);
            }
        });
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.addView(new Title(getApplicationContext(), "Decrypt Menu"));
                layout.addView(new Explain(getApplicationContext(), "Enter The Encryption Key To Decrypt Your Text:"));
                final EditText edit = new EditText(getApplicationContext());
                edit.setHint("Key");
                edit.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                edit.setHintTextColor(Color.LTGRAY);
                edit.setTextColor(Color.WHITE);
                layout.addView(edit);
                Button go = new Button(getApplicationContext());
                go.setText(getString(R.string.decrypt));
                go.setBackground(null);
                go.setAllCaps(true);
                go.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                go.setTextColor(Color.WHITE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Working...");
                        pd.setCancelable(false);
                        pd.show();
                        new RunInBackground(new RunInBackground.Processed() {
                            @Override
                            public void processed(String s) {
                                pd.cancel();
                                appView.getDrag().close(true);
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        }, new RunInBackground.Process() {
                            @Override
                            public String process() {
                                try {
                                    return Crypto.decrypt(input.getText().toString(), edit.getText().toString());
                                } catch (OutOfMemoryError | Exception ignored) {
                                    return input.getText().toString();
                                }
                            }
                        }).execute();
                    }
                });
                layout.addView(go);
                appView.getDrag().setContent(layout);
                appView.getDrag().open(false);
            }
        });
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            return reverse(input.getText().toString());
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
            }
        });
        paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                final String s = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                clip.set(input.getText().toString());
                history = 0;
                input.setText(s);
            }
        });
        append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                final String s = input.getText().toString() + clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                clip.set(input.getText().toString());
                history = 0;
                input.setText(s);
            }
        });

        reverseWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            String[] words = input.getText().toString().split(" ");
                            for (String word : words) {
                                if (builder.length() == 0) {
                                    builder.append(reverse(word));
                                } else {
                                    builder.append(" ").append(reverse(word));
                                }
                            }
                            return builder.toString();
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
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
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.addView(new Title(getApplicationContext(), "Replace Menu"));
                layout.addView(new Explain(getApplicationContext(), "Enter The String To Replace And The Replacement String:"));
                final EditText edit = new EditText(getApplicationContext());
                edit.setHint("To Replace");
                edit.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                edit.setHintTextColor(Color.LTGRAY);
                edit.setTextColor(Color.WHITE);
                final EditText edit1 = new EditText(getApplicationContext());
                edit1.setHint("Replacement");
                edit1.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                edit1.setHintTextColor(Color.LTGRAY);
                edit1.setTextColor(Color.WHITE);
                layout.addView(edit);
                layout.addView(edit1);
                Button go = new Button(getApplicationContext());
                go.setText(getString(R.string.replace));
                go.setBackground(null);
                go.setAllCaps(true);
                go.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                go.setTextColor(Color.WHITE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Working...");
                        pd.setCancelable(false);
                        pd.show();
                        new RunInBackground(new RunInBackground.Processed() {
                            @Override
                            public void processed(String s) {
                                pd.cancel();
                                appView.getDrag().close(true);
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        }, new RunInBackground.Process() {
                            @Override
                            public String process() {
                                try {
                                    return input.getText().toString().replaceAll(edit.getText().toString(),edit1.getText().toString());
                                } catch (OutOfMemoryError | Exception ignored) {
                                    return input.getText().toString();
                                }
                            }
                        }).execute();
                    }
                });
                layout.addView(go);
                appView.getDrag().setContent(layout);
                appView.getDrag().open(false);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.addView(new Title(getApplicationContext(), "Multiply Menu"));
                layout.addView(new Explain(getApplicationContext(), "Enter The Number Of Times You Want Your Text Multiplied:"));
                final EditText edit = new EditText(getApplicationContext());
                edit.setHint("Number");
                edit.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                edit.setHintTextColor(Color.LTGRAY);
                edit.setTextColor(Color.WHITE);
                layout.addView(edit);
                Button go = new Button(getApplicationContext());
                go.setText(getString(R.string.multiply));
                go.setBackground(null);
                go.setAllCaps(true);
                go.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                go.setTextColor(Color.WHITE);
                go.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog pd = new ProgressDialog(Factory.this);
                        pd.setMessage("Working...");
                        pd.setCancelable(false);
                        pd.show();
                        new RunInBackground(new RunInBackground.Processed() {
                            @Override
                            public void processed(String s) {
                                pd.cancel();
                                appView.getDrag().close(true);
                                clip.set(input.getText().toString());
                                history = 0;
                                input.setText(s);
                            }
                        }, new RunInBackground.Process() {
                            @Override
                            public String process() {
                                try {
                                    StringBuilder builder = new StringBuilder();
                                    String text = input.getText().toString();
                                    for (int a = 0; a < Integer.parseInt(edit.getText().toString()); a++) {
                                        builder.append(text);
                                    }
                                    return builder.toString();
                                } catch (OutOfMemoryError | Exception ignored) {
                                    return input.getText().toString();
                                }
                            }
                        }).execute();
                    }
                });
                layout.addView(go);
                appView.getDrag().setContent(layout);
                appView.getDrag().open(false);
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
        wordify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            String old = input.getText().toString();
                            final StringBuilder b = new StringBuilder(old);
                            for (int a = 0; a < old.length(); a++) {
                                if (a == 0 || old.charAt(a - 1) == ' ') {
                                    b.setCharAt(a, (String.valueOf(old.charAt(a))).toUpperCase().charAt(0));
                                }
                            }
                            return b.toString();
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
            }
        });
        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            String old = input.getText().toString();
                            return old.toLowerCase();
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
            }
        });
        upper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            String old = input.getText().toString();
                            return old.toUpperCase();
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
            }
        });
        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(Factory.this);
                pd.setMessage("Working...");
                pd.setCancelable(false);
                pd.show();
                new RunInBackground(new RunInBackground.Processed() {
                    @Override
                    public void processed(String s) {
                        pd.cancel();
                        clip.set(input.getText().toString());
                        history = 0;
                        input.setText(s);
                    }
                }, new RunInBackground.Process() {
                    @Override
                    public String process() {
                        try {
                            StringBuilder builder = new StringBuilder();
                            ArrayList<Character> chars = new ArrayList<>();
                            char[] array = input.getText().toString().toCharArray();
                            for (char value : array) {
                                chars.add(value);
                            }
                            while (chars.size() != 0) {
                                int random = new Random().nextInt(chars.size());
                                builder.append(chars.get(random));
                                chars.remove(random);
                            }
                            return builder.toString();
                        } catch (OutOfMemoryError | Exception ignored) {
                            return input.getText().toString();
                        }
                    }
                }).execute();
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
        bottom.addView(middle);
        bottom.addView(right);
        left.setLayoutParams(new LinearLayout.LayoutParams(Device.screenX(getApplicationContext()) / 3, ViewGroup.LayoutParams.MATCH_PARENT));
        middle.setLayoutParams(new LinearLayout.LayoutParams(Device.screenX(getApplicationContext()) / 3, ViewGroup.LayoutParams.MATCH_PARENT));
        right.setLayoutParams(new LinearLayout.LayoutParams(Device.screenX(getApplicationContext()) / 3, ViewGroup.LayoutParams.MATCH_PARENT));
        appView.setContent(all);
        setContentView(appView);
    }

    private void share(EditText et) {
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

    private void undo() {
        if (clip.getHistory().size() > 0) {
            input.setText(clip.getHistory().get(clip.getHistory().size() - 1 - history));
            if (history < clip.getHistory().size() - 1)
                history++;
        }
    }

    @Override
    public void onBackPressed() {
        if (appView.getDrag().isOpen()) {
            appView.getDrag().close(true);
        } else {
            undo();
        }
    }

    class Title extends TextView {
        public Title(Context c, String text) {
            super(c);
            setText(text);
            setTextColor(Color.WHITE);
            setTextSize(30);
            setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        }
    }

    class Explain extends TextView {
        public Explain(Context c, String text) {
            super(c);
            setText(text);
            setTextColor(Color.WHITE);
            setTextSize(22);
            setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        }
    }
}
