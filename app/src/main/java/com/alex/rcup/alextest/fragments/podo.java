package com.alex.rcup.alextest.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.rcup.alextest.R;
import com.alex.rcup.alextest.tools.IOnFragmentInteractionListener;
import com.alex.rcup.alextest.tools.UIUpdater;

import android.os.Handler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link podo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link podo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class podo extends Fragment implements View.OnClickListener{

    private IOnFragmentInteractionListener mListener;

    public int dailySteps = 100;
    private int ressourcesRatio = dailySteps / 52;

    private Button mButton_podo;
    public static View view_podo;

    private ImageButton mButton_menu;

    //Barre de navigation en bas
    private ImageButton mImageButton_nav_retour;
    private ImageButton mImageButton_nav_home;
    private ImageButton mImageButton_nav_coupe;

    private ImageButton mImageButton_plus;


    private TextView mTextView_timer;
    public TextView mTextView_nbpas;
    private TextView mTextView_pourcentagepas;
    private Button mButton_start;
    private long startTime = 0;
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    /*Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            int nbdesecondes= (int) (millis/1000);

            mTextView_timer.setText(String.format("%d:%02d", minutes, seconds));
            mTextView_nbpas.setText(String.valueOf(nbdesecondes));
            //Les valeurs que je vais prendre 10 20 30
            if(nbdesecondes==5) {
                //Animation de l'image
              // mImageView_constell.setImageResource(R.drawable.appli49);
               // fadeInAndKeepImage(mImageView_constell);
                mImageView_constell.setImageResource(R.drawable.appli50);
            }
            else if (nbdesecondes==9){
               // fadeOutAndHideImage(mImageView_constell);
            }
            else if (nbdesecondes==10) {
               // mImageView_constell.setImageResource(R.drawable.appli44);
               // fadeInAndKeepImage(mImageView_constell);
                mImageView_constell.setImageResource(R.drawable.appli49);
            }
            else if (nbdesecondes==15) {
               // mImageView_constell.setImageResource(R.drawable.appli37);
               // fadeInAndKeepImage(mImageView_constell);
                mImageView_constell.setImageResource(R.drawable.appli48);
            }
            else if (nbdesecondes==20) {
               // mImageView_constell.setImageResource(R.drawable.appli32);
               // fadeInAndKeepImage(mImageView_constell);
                mImageView_constell.setImageResource(R.drawable.appli47);
            }
            else if (nbdesecondes==25) {
                //mImageView_constell.setImageResource(R.drawable.appli25);
                //fadeInAndKeepImage(mImageView_constell);
                mImageView_constell.setImageResource(R.drawable.appli46);
            }
            else if (nbdesecondes==30) {
                mImageView_constell.setImageResource(R.drawable.appli45);
            }
            else if (nbdesecondes==31) {
                mImageView_constell.setImageResource(R.drawable.appli44);
            }
            else if (nbdesecondes==32) {
                mImageView_constell.setImageResource(R.drawable.appli43);
            }
            else if (nbdesecondes==33) {
                mImageView_constell.setImageResource(R.drawable.appli42);
            }
            else if (nbdesecondes==34) {
                mImageView_constell.setImageResource(R.drawable.appli41);
            }
            else if (nbdesecondes==35) {
                mImageView_constell.setImageResource(R.drawable.appli40);
            }
            else if (nbdesecondes==36) {
                mImageView_constell.setImageResource(R.drawable.appli39);
            }
            else if (nbdesecondes==37) {
                mImageView_constell.setImageResource(R.drawable.appli38);
            }
            else if (nbdesecondes==38) {
                mImageView_constell.setImageResource(R.drawable.appli37);
            }
            else if (nbdesecondes==39) {
                mImageView_constell.setImageResource(R.drawable.appli36);
            }
            else if (nbdesecondes==40) {
                mImageView_constell.setImageResource(R.drawable.appli35);
            }
            else if (nbdesecondes==41) {
                mImageView_constell.setImageResource(R.drawable.appli34);
            }
            else if (nbdesecondes==42) {
                mImageView_constell.setImageResource(R.drawable.appli33);
            }
            else if (nbdesecondes==43) {
                mImageView_constell.setImageResource(R.drawable.appli32);
            }
            else if (nbdesecondes==44) {
                mImageView_constell.setImageResource(R.drawable.appli31);
            }
            else if (nbdesecondes==45) {
                mImageView_constell.setImageResource(R.drawable.appli30);
            }
            else if (nbdesecondes==46) {
                mImageView_constell.setImageResource(R.drawable.appli29);
            }
            else if (nbdesecondes==47) {
                mImageView_constell.setImageResource(R.drawable.appli28);
            }
            else if (nbdesecondes==48) {
                mImageView_constell.setImageResource(R.drawable.appli27);
            }
            else if (nbdesecondes==49) {
                mImageView_constell.setImageResource(R.drawable.appli26);
            }
            else if (nbdesecondes==50) {
                mImageView_constell.setImageResource(R.drawable.appli25);
            }
            else if (nbdesecondes==51) {
                mImageView_constell.setImageResource(R.drawable.appli24);
            }
            else if (nbdesecondes==52) {
                mImageView_constell.setImageResource(R.drawable.appli23);
            }
            else if (nbdesecondes==53) {
                mImageView_constell.setImageResource(R.drawable.appli22);
            }
            else if (nbdesecondes==54) {
                mImageView_constell.setImageResource(R.drawable.appli21);
            }
            else if (nbdesecondes==55) {
                mImageView_constell.setImageResource(R.drawable.appli20);
            }
            else if (nbdesecondes==56) {
                mImageView_constell.setImageResource(R.drawable.appli19);
            }
            else if (nbdesecondes==57) {
                mImageView_constell.setImageResource(R.drawable.appli18);
            }
            else if (nbdesecondes==58) {
                mImageView_constell.setImageResource(R.drawable.appli17);
            }
            else if (nbdesecondes==59) {
                mImageView_constell.setImageResource(R.drawable.appli16);
            }
            else if (nbdesecondes==60) {
                mImageView_constell.setImageResource(R.drawable.appli15);
            }
            else if (nbdesecondes==61) {
                mImageView_constell.setImageResource(R.drawable.appli14);
            }
            else if (nbdesecondes==62) {
                mImageView_constell.setImageResource(R.drawable.appli13);
            }
            else if (nbdesecondes==63) {
                mImageView_constell.setImageResource(R.drawable.appli12);
            }
            else if (nbdesecondes==64) {
                mImageView_constell.setImageResource(R.drawable.appli11);
            }
            else if (nbdesecondes==65) {
                mImageView_constell.setImageResource(R.drawable.appli10);
            }
            else if (nbdesecondes==66) {
                mImageView_constell.setImageResource(R.drawable.appli9);
            }
            else if (nbdesecondes==67) {
                mImageView_constell.setImageResource(R.drawable.appli8);
            }
            else if (nbdesecondes==68) {
                mImageView_constell.setImageResource(R.drawable.appli7);
            }
            else if (nbdesecondes==69) {
                mImageView_constell.setImageResource(R.drawable.appli6);
            }
            else if (nbdesecondes==70) {
                mImageView_constell.setImageResource(R.drawable.appli5);
            }
            else if (nbdesecondes==71) {
                mImageView_constell.setImageResource(R.drawable.appli4);
            }
            else if (nbdesecondes==72) {
                mImageView_constell.setImageResource(R.drawable.appli3);
            }
            else if (nbdesecondes==73) {
                mImageView_constell.setImageResource(R.drawable.appli2);
            }
            else if (nbdesecondes==74) {
                mImageView_constell.setImageResource(R.drawable.appli1);
            }

            timerHandler.postDelayed(this, 1000);
            //Le delai ("500") = 500 ms entre chaque appel de la fonction handler
        }
    };*/

    public void updateConstell(int steps, int goal) {
        Log.e("Update constell", "Here bitcheeeeeeeeeees");
        Log.e("Update constell", "Steps " + String.valueOf(steps));
        Log.e("Update constell", "Goal " + String.valueOf(goal));
        int times = steps / ressourcesRatio;
        int percentage = steps * 100 / goal;
        Log.e("Update constell", "Percents " + String.valueOf(percentage));
        mTextView_pourcentagepas.setText(String.valueOf(percentage) + "%");
        switch (times) {
            case 1:
                mImageView_constell.setImageResource(R.drawable.appli50);
                break;
            case 2:
                mImageView_constell.setImageResource(R.drawable.appli49);
                break;
            case 3:
                mImageView_constell.setImageResource(R.drawable.appli48);
                break;
            case 4:
                mImageView_constell.setImageResource(R.drawable.appli47);
                break;
            case 5:
                mImageView_constell.setImageResource(R.drawable.appli46);
                break;
            case 6:
                mImageView_constell.setImageResource(R.drawable.appli45);
                break;
            case 7:
                mImageView_constell.setImageResource(R.drawable.appli44);
                break;
            case 8:
                mImageView_constell.setImageResource(R.drawable.appli43);
                break;
            case 9:
                mImageView_constell.setImageResource(R.drawable.appli42);
                break;
            case 10:
                mImageView_constell.setImageResource(R.drawable.appli41);
                break;
            case 11:
                mImageView_constell.setImageResource(R.drawable.appli40);
                break;
            case 12:
                mImageView_constell.setImageResource(R.drawable.appli39);
                break;
            case 13:
                mImageView_constell.setImageResource(R.drawable.appli38);
                break;
            case 14:
                mImageView_constell.setImageResource(R.drawable.appli37);
                break;
            case 15:
                mImageView_constell.setImageResource(R.drawable.appli36);
                break;
            case 16:
                mImageView_constell.setImageResource(R.drawable.appli35);
                break;
            case 17:
                mImageView_constell.setImageResource(R.drawable.appli34);
                break;
            case 18:
                mImageView_constell.setImageResource(R.drawable.appli33);
                break;
            case 19:
                mImageView_constell.setImageResource(R.drawable.appli32);
                break;
            case 20:
                mImageView_constell.setImageResource(R.drawable.appli31);
                break;
            case 21:
                mImageView_constell.setImageResource(R.drawable.appli30);
                break;
            case 22:
                mImageView_constell.setImageResource(R.drawable.appli29);
                break;
            case 23:
                mImageView_constell.setImageResource(R.drawable.appli28);
                break;
            case 24:
                mImageView_constell.setImageResource(R.drawable.appli27);
                break;
            case 25:
                mImageView_constell.setImageResource(R.drawable.appli26);
                break;
            case 26:
                mImageView_constell.setImageResource(R.drawable.appli25);
                break;
            case 27:
                mImageView_constell.setImageResource(R.drawable.appli24);
                break;
            case 28:
                mImageView_constell.setImageResource(R.drawable.appli23);
                break;
            case 29:
                mImageView_constell.setImageResource(R.drawable.appli22);
                break;
            case 30:
                mImageView_constell.setImageResource(R.drawable.appli21);
                break;
            case 31:
                mImageView_constell.setImageResource(R.drawable.appli20);
                break;
            case 32:
                mImageView_constell.setImageResource(R.drawable.appli19);
                break;
            case 33:
                mImageView_constell.setImageResource(R.drawable.appli18);
                break;
            case 34:
                mImageView_constell.setImageResource(R.drawable.appli17);
                break;
            case 35:
                mImageView_constell.setImageResource(R.drawable.appli16);
                break;
            case 36:
                mImageView_constell.setImageResource(R.drawable.appli15);
                break;
            case 37:
                mImageView_constell.setImageResource(R.drawable.appli14);
                break;
            case 38:
                mImageView_constell.setImageResource(R.drawable.appli13);
                break;
            case 39:
                mImageView_constell.setImageResource(R.drawable.appli12);
                break;
            case 40:
                mImageView_constell.setImageResource(R.drawable.appli11);
                break;
            case 41:
                mImageView_constell.setImageResource(R.drawable.appli10);
                break;
            case 42:
                mImageView_constell.setImageResource(R.drawable.appli9);
                break;
            case 43:
                mImageView_constell.setImageResource(R.drawable.appli8);
                break;
            case 44:
                mImageView_constell.setImageResource(R.drawable.appli7);
                break;
            case 45:
                mImageView_constell.setImageResource(R.drawable.appli6);
                break;
            case 46:
                mImageView_constell.setImageResource(R.drawable.appli5);
                break;
            case 47:
                mImageView_constell.setImageResource(R.drawable.appli4);
                break;
            case 48:
                mImageView_constell.setImageResource(R.drawable.appli3);
                break;
            case 49:
                mImageView_constell.setImageResource(R.drawable.appli2);
                break;
            case 50:
                mImageView_constell.setImageResource(R.drawable.appli1);
                break;
            case 51:
                mImageView_constell.setImageResource(R.drawable.appli0);
                break;
            default:
                mImageView_constell.setImageResource(R.drawable.appli0);
                break;

        }
    }
    private ImageView mImageView_constell;

    private  Animation animationFadeIn;
    private  Animation animationFadeOut;

    private static podo instance;
    public podo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment podo.
     */
    // TODO: Rename and change types and number of parameters
    public static podo newInstance() {
        if (instance == null)
            instance = new podo();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_podo, container, false);
        view_podo=inflater.inflate(R.layout.fragment_podo, container, false);
        //mButton_podo = (Button)v.findViewById(R.id.button_podo);
        //mButton_podo.setOnClickListener(this);

        //Faire une fonction qui compte
        mTextView_timer = (TextView)v.findViewById(R.id.textView_timer);
        mButton_start= (Button)v.findViewById(R.id.button_start);
        mButton_start.setOnClickListener(this);

        mTextView_nbpas = (TextView)v.findViewById(R.id.nbpas);
        mTextView_pourcentagepas = (TextView)v.findViewById(R.id.pourcentagepas);

        //Images de la constellation
        mImageView_constell=(ImageView) v.findViewById(R.id.imageView_constellation);

        //Animations
        animationFadeIn = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fadein);
        animationFadeOut = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fadeout);


        //Barre de navigation en bas
        mImageButton_nav_retour= (ImageButton)v.findViewById(R.id.imageButton_nav_retour);
        mImageButton_nav_home=(ImageButton)v.findViewById(R.id.imageButton_nav_home);
        mImageButton_nav_coupe=(ImageButton)v.findViewById(R.id.imageButton_nav_coupe);

        mImageButton_nav_retour.setOnClickListener(this);
        mImageButton_nav_home.setOnClickListener(this);
        mImageButton_nav_coupe.setOnClickListener(this);

        //Bouton plus
        mImageButton_plus=(ImageButton)v.findViewById(R.id.imageButton_plus);
        mImageButton_plus.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof IOnFragmentInteractionListener) {
            mListener = (IOnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        switch(v.getId()){
            case R.id.button_podo:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, podo_infos.newInstance())
                        .commit();
                break;
            case R.id.button_start:
                if (mButton_start.getText().equals("stop")) {
//                    timerHandler.removeCallbacks(timerRunnable);
                    //mButton_start.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
//                    timerHandler.postDelayed(timerRunnable, 0);
                    mImageView_constell.setImageResource(android.R.color.transparent);
                    //mButton_start.setText("stop");
                }

                break;

            //Barre de navigation en bas
            case R.id.imageButton_plus:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, podo_infos.newInstance())
                        .commit();
                break;
            //Barre de navigation en bas
            case R.id.imageButton_nav_retour:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, menu.newInstance())
                        .commit();
                break;
            case R.id.imageButton_nav_home:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, menu.newInstance())
                        .commit();
                break;
            case R.id.imageButton_nav_coupe:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, score.newInstance())
                        .commit();
                break;
        }
    }


    private void fadeOutAndHideImage(final ImageView img)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                img.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        img.startAnimation(fadeOut);
    }
    private void fadeInAndKeepImage(final ImageView img)
    {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(1000);

        fadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
               img.setVisibility(View.VISIBLE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {
                img.setVisibility(View.INVISIBLE);
            }
        });

        img.startAnimation(fadeIn);
    }

    public interface OnFragmentInteractionListener {
        public void onPodoFragmentInteraction(String string);
    }


}
