package com.alex.rcup.alextest.fragments;

import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.alex.rcup.alextest.R;
import com.alex.rcup.alextest.tools.IOnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link menu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menu extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IOnFragmentInteractionListener mListener;
    private Context mContext;







    //Barre de menu
    private ImageButton mImageButton_menu_1;
    private ImageButton mImageButton_menu_2;
    private ImageButton mImageButton_menu_3;
    private ImageButton mImageButton_menu_4;
    private ImageButton mImageButton_menu_5;
    private ImageButton mImageButton_menu_6;
    private ImageButton mImageButton_menu_7;
    private ImageButton mImageButton_menu_8;
    private ImageButton mImageButton_menu_9;


    //Barre de navigation en bas
    private ImageButton mImageButton_nav_retour;
    private ImageButton mImageButton_nav_home;
    private ImageButton mImageButton_nav_coupe;



    public menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment menu.
     */
    // TODO: Rename and change types and number of parameters
    public static menu newInstance() {
        menu fragment = new menu();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_menu, container, false);
        mContext=v.getContext();

        //Barre de navigation en bas
        mImageButton_nav_retour= (ImageButton)v.findViewById(R.id.imageButton_nav_retour);
        mImageButton_nav_home=(ImageButton)v.findViewById(R.id.imageButton_nav_home);
        mImageButton_nav_coupe=(ImageButton)v.findViewById(R.id.imageButton_nav_coupe);

        mImageButton_nav_retour.setOnClickListener(this);
        mImageButton_nav_home.setOnClickListener(this);
        mImageButton_nav_coupe.setOnClickListener(this);





        //Menu
        mImageButton_menu_1=(ImageButton)v.findViewById(R.id.imageButton_menu_1);
        mImageButton_menu_2=(ImageButton)v.findViewById(R.id.imageButton_menu_2);
        mImageButton_menu_3=(ImageButton)v.findViewById(R.id.imageButton_menu_3);
        mImageButton_menu_4=(ImageButton)v.findViewById(R.id.imageButton_menu_4);
        mImageButton_menu_5=(ImageButton)v.findViewById(R.id.imageButton_menu_5);
        mImageButton_menu_6=(ImageButton)v.findViewById(R.id.imageButton_menu_6);
        mImageButton_menu_7=(ImageButton)v.findViewById(R.id.imageButton_menu_7);
        mImageButton_menu_8=(ImageButton)v.findViewById(R.id.imageButton_menu_8);
        mImageButton_menu_9=(ImageButton)v.findViewById(R.id.imageButton_menu_9);

        mImageButton_menu_1.setOnClickListener(this);
        mImageButton_menu_2.setOnClickListener(this);
        mImageButton_menu_3.setOnClickListener(this);
        mImageButton_menu_4.setOnClickListener(this);
        mImageButton_menu_5.setOnClickListener(this);
        mImageButton_menu_6.setOnClickListener(this);
        mImageButton_menu_7.setOnClickListener(this);
        mImageButton_menu_8.setOnClickListener(this);
        mImageButton_menu_9.setOnClickListener(this);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        switch(v.getId()){

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

            //Menu
            case R.id.imageButton_menu_1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, podo.newInstance())
                        .commit();
                break;

            case R.id.imageButton_menu_2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, healthreport.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, historique.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, cirage.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, paires.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_6:
                int icon = R.drawable.iconeapp;
                long when = System.currentTimeMillis();
                NotificationManager nm=(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent=new Intent(mContext,menu.class);
                PendingIntent  pending=PendingIntent.getActivity(mContext, 0, intent, 0);
                Notification notification;
                notification = new Notification.Builder(mContext)
                        .setContentTitle("Berluti ")
                        .setContentText(
                                "Pas un temps à mettre une Berluti dehors.").setSmallIcon(R.drawable.iconeapp)
                        .setContentIntent(pending).setWhen(when).setAutoCancel(true)
                        .build();
                notification.flags |= Notification.FLAG_LOCAL_ONLY;
                notification.defaults |= Notification.DEFAULT_SOUND;
                nm.notify(1, notification);

                break;
            case R.id.imageButton_menu_7:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, pressionplantaire.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_8:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, reglages.newInstance())
                        .commit();
                break;
            case R.id.imageButton_menu_9:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, sav.newInstance())
                        .commit();
                break;
        }


    }


    public interface OnFragmentInteractionListener {
        public void onMenuFragmentInteraction(String string);
    }
}
