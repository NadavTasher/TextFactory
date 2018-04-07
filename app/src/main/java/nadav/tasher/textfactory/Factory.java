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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import java.util.ArrayList;
import java.util.Random;

import nadav.tasher.lightool.Light;

public class Factory extends Activity {
	int colo=Color.parseColor("#21557F");
	String text;
	String pretext;
	EditText input;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		start();
	}
	void doStart() {
		final Window window=getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(colo);
		window.setNavigationBarColor(colo);
		final Bitmap ico=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		ActivityManager.TaskDescription taskDescription=new ActivityManager.TaskDescription(getString(R.string.app_name), ico, colo);
		setTaskDescription(taskDescription);
		final LinearLayout ll=new LinearLayout(this);
		ll.setGravity(Gravity.CENTER);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setBackgroundColor(colo);
		final ImageView icon=new ImageView(this);
		Drawable iconDr=getDrawable(R.drawable.ic_tefa_r);
		icon.setImageDrawable(iconDr);
		ll.addView(icon);
		setContentView(ll);
		app();
	}
	void share(EditText et) {
		Intent s=new Intent(Intent.ACTION_SEND);
		s.putExtra(Intent.EXTRA_TEXT, et.getText().toString());
		s.setType("text/plain");
		s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(s, "Share"));
	}
	void app() {
		LinearLayout main=new LinearLayout(this);
		main.setOrientation(LinearLayout.VERTICAL);
		main.setGravity(Gravity.START);
		LinearLayout amain=new LinearLayout(this);
		amain.setOrientation(LinearLayout.VERTICAL);
		amain.setGravity(Gravity.START);
		int screenY=Light.Device.screenY(this);
		final LinearLayout navbarAll=new LinearLayout(this);
		navbarAll.setBackgroundColor(colo);
		navbarAll.setOrientation(LinearLayout.HORIZONTAL);
		navbarAll.setGravity(Gravity.CENTER);
		final ImageView nutIcon=new ImageView(this);
		final int nutSize=(screenY / 8) - screenY / 30;
		LinearLayout.LayoutParams nutParms=new LinearLayout.LayoutParams(nutSize, nutSize);
		nutIcon.setLayoutParams(nutParms);
		nutIcon.setImageDrawable(getDrawable(R.drawable.ic_tefa_r));
		final ObjectAnimator anim=ObjectAnimator.ofFloat(nutIcon, View.TRANSLATION_X, Light.Animations.VIBRATE_SMALL);
		anim.setDuration(1500);
		anim.setRepeatMode(ObjectAnimator.RESTART);
		anim.setRepeatCount(ObjectAnimator.INFINITE);
		navbarAll.addView(nutIcon);
		int navY=screenY / 8;
		LinearLayout.LayoutParams navParms=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, navY);
		navParms.gravity=Gravity.START;
		navbarAll.setLayoutParams(navParms);
		anim.start();
		amain.addView(navbarAll);
		input=new EditText(this);
		input.setHint("Your Text");
		if(getIntent().hasExtra(Intent.EXTRA_TEXT)){
			input.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
		}
		main.setBackgroundColor(colo);
		main.setPadding(3, 3, 3, 3);
		input.setPadding(10, 10, 10, 10);
		input.setTextColor(Color.BLACK);
		input.setHintTextColor(Color.GRAY);
		input.setBackground(getDrawable(R.drawable.back));
		input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_FLAG_MULTI_LINE | EditorInfo.IME_ACTION_NONE);
		final Button empty=new Button(this), pst=new Button(this), eyn=new Button(this), reverse=new Button(this), replace=new Button(this), multiply=new Button(this), copy=new Button(this), tocapital=new Button(this), tononcapital=new Button(this), randomize=new Button(this), share=new Button(this), undo=new Button(this);
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
		main.addView(empty);
		main.addView(undo);
		main.addView(copy);
		main.addView(share);
		main.addView(pst);
		main.addView(eyn);
		main.addView(reverse);
		main.addView(replace);
		main.addView(multiply);
		main.addView(tocapital);
		main.addView(tononcapital);
		main.addView(randomize);
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
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		input.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Light.Device.screenY(getApplicationContext()) / 3));
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
				AlertDialog.Builder ab=new AlertDialog.Builder(Factory.this);
				ab.setTitle("Encrypt");
				final EditText edt=new EditText(getApplicationContext());
				edt.setHint("Key");
				ab.setView(edt);
				ab.setPositiveButton("Encrypt", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						final ProgressDialog pd;
						pd=new ProgressDialog(Factory.this);
						pd.setMessage("Encrypting...");
						pd.setCancelable(false);
						pd.show();
						new Thread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								final String text1=Light.Stringer.Encryptor.EncryptionV2.encrypt(edt.getText().toString(), input.getText().toString());
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										pretext=input.getText().toString();
										text=text1;
										input.setText(text);
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
				AlertDialog.Builder ab=new AlertDialog.Builder(Factory.this);
				ab.setTitle("Decrypt");
				LinearLayout ll=new LinearLayout(getApplicationContext());
				ll.setOrientation(LinearLayout.VERTICAL);
				final EditText edt=new EditText(getApplicationContext());
				edt.setHint("Key");
				ab.setView(edt);
				ab.setPositiveButton("Decrypt", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						final ProgressDialog pd;
						pd=new ProgressDialog(Factory.this);
						pd.setMessage("Decrypting...");
						pd.setCancelable(false);
						pd.show();
						new Thread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								final String text1=Light.Stringer.Encryptor.EncryptionV2.decrypt(edt.getText().toString(), input.getText().toString());
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										pretext=input.getText().toString();
										text=text1;
										input.setText(text);
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
				pd=new ProgressDialog(Factory.this);
				pd.setMessage("Reversing...");
				pd.setCancelable(false);
				pd.show();
				new Thread(new Runnable() {
					@Override
					public void run() {
						final String s=Light.Stringer.reversed(input.getText().toString());
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								pretext=input.getText().toString();
								text=s;
								input.setText(text);
							}
						});
					}
				}).start();
			}
		});
		pst.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ClipboardManager clipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				final String s=clipboard.getPrimaryClip().getItemAt(0).getText().toString();
				pretext=input.getText().toString();
				text=s;
				input.setText(text);
			}
		});
		pst.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				ClipboardManager clipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				final String s=input.getText().toString() + clipboard.getPrimaryClip().getItemAt(0).getText().toString();
				pretext=input.getText().toString();
				text=s;
				input.setText(text);
				return true;
			}
		});
		reverse.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				final ProgressDialog pd;
				pd=new ProgressDialog(Factory.this);
				pd.setMessage("Word Reversing...");
				pd.setCancelable(false);
				pd.show();
				new Thread(new Runnable() {
					@Override
					public void run() {
						String finale="";
						ArrayList<String> words=Light.Stringer.cutOnEvery(input.getText().toString(), " ");
						for(int i=0; i<words.size(); i++){
							if(finale.equals("")){
								finale=finale + Light.Stringer.reversed(words.get(i));
							}else{
								finale=finale + " " + Light.Stringer.reversed(words.get(i));
							}
						}
						final String finalE=finale;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								pretext=input.getText().toString();
								text=finalE;
								input.setText(text);
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
				pretext=input.getText().toString();
				text=null;
				input.setText(null);
			}
		});
		replace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder ab=new AlertDialog.Builder(Factory.this);
				ab.setTitle("Replace");
				LinearLayout ll=new LinearLayout(getApplicationContext());
				ll.setOrientation(LinearLayout.VERTICAL);
				final EditText edt=new EditText(getApplicationContext());
				edt.setHint("Text To Replace");
				final EditText tr=new EditText(getApplicationContext());
				tr.setHint("Replacement Text");
				ll.addView(edt);
				ll.addView(tr);
				ab.setView(ll);
				ab.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						final ProgressDialog pd;
						pd=new ProgressDialog(Factory.this);
						pd.setMessage("Replacing...");
						pd.setCancelable(false);
						pd.show();
						new Thread(new Runnable() {
							@Override
							public void run() {
								String text1=input.getText().toString();
								if(text1.contains(edt.getText().toString())){
									final String nt=text1.replaceAll(edt.getText().toString(), tr.getText().toString());
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											pd.cancel();
											pretext=input.getText().toString();
											text=nt;
											input.setText(text);
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
				AlertDialog.Builder ab=new AlertDialog.Builder(Factory.this);
				ab.setTitle("Multiply");
				final EditText edt=new EditText(getApplicationContext());
				edt.setHint("Number Of Times");
				edt.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
				ab.setView(edt);
				ab.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						final ProgressDialog pd;
						pd=new ProgressDialog(Factory.this);
						pd.setMessage("Multiplying...");
						pd.setCancelable(false);
						pd.show();
						final String text1=input.getText().toString();
						final int times=Integer.parseInt(edt.getText().toString());
						new Thread(new Runnable() {
							@Override
							public void run() {
								String ret="";
								for(int i=0; i<times; i++){
									ret=ret + text1;
								}
								final String ret2=ret;
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										pd.cancel();
										pretext=input.getText().toString();
										text=ret2;
										input.setText(text);
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
				ClipboardManager clipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				ClipData clip=ClipData.newPlainText("TextFactoryData", input.getText().toString());
				clipboard.setPrimaryClip(clip);
			}
		});
		tocapital.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						final String s=input.getText().toString().toUpperCase();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pretext=input.getText().toString();
								text=s;
								input.setText(text);
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
				pd=new ProgressDialog(Factory.this);
				pd.setMessage("Wordify...");
				pd.setCancelable(false);
				pd.show();
				new Thread(new Runnable() {
					@Override
					public void run() {
						final String s;
						String allwords="";
						ArrayList<String> words=Light.Stringer.cutOnEvery(input.getText().toString(), " ");
						for(int w=0; w<words.size(); w++){
							String word=words.get(w);
							String newword="";
							for(int c=0; c<word.length(); c++){
								if(c==0){
									newword=newword + String.valueOf(word.charAt(c)).toUpperCase();
								}else{
									newword=newword + String.valueOf(word.charAt(c));
								}
							}
							words.remove(w);
							words.add(w, newword);
						}
						for(int ws=0; ws<words.size(); ws++){
							if(ws==0){
								allwords=allwords + words.get(ws);
							}else{
								allwords=allwords + " " + words.get(ws);
							}
						}
						s=allwords;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								pretext=input.getText().toString();
								text=s;
								input.setText(text);
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
						final String s=input.getText().toString().toLowerCase();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pretext=input.getText().toString();
								text=s;
								input.setText(text);
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
				pd=new ProgressDialog(Factory.this);
				pd.setMessage("Randomizing...");
				pd.setCancelable(false);
				pd.show();
				new Thread(new Runnable() {
					@Override
					public void run() {
						String nw="";
						String text1=input.getText().toString();
						ArrayList<String> allth=new ArrayList<>();
						for(int i=0; i<text1.length(); i++){
							allth.add(text1.charAt(i) + "");
						}
						for(int i=0; i<text1.length(); i++){
							int rnd=new Random().nextInt(allth.size());
							nw=nw + allth.get(rnd);
							allth.remove(rnd);
						}
						final String nw2=nw;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								pd.cancel();
								pretext=input.getText().toString();
								text=nw2;
								input.setText(text);
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
		amain.addView(input);
		ScrollView scrollView=new ScrollView(this);
		scrollView.addView(main);
		amain.addView(scrollView);
		scrollView.setBackgroundColor(colo);
		amain.setBackgroundColor(colo);
		setContentView(amain);
	}
	void start() {
		doStart();
	}
	void undo() {
		text=pretext;
		pretext=input.getText().toString();
		input.setText(text);
	}
	@Override
	public void onBackPressed() {
		undo();
	}
}