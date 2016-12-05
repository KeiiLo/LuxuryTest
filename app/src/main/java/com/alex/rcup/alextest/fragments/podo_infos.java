package com.alex.rcup.alextest.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.alex.rcup.alextest.MainActivity;
import com.alex.rcup.alextest.R;
import com.alex.rcup.alextest.tools.IOnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link podo_infos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link podo_infos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class podo_infos extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IOnFragmentInteractionListener mListener;

    private Button mButton_podoinfos;

    //Barre de navigation en bas
    private ImageButton mImageButton_nav_retour;
    private ImageButton mImageButton_nav_home;
    private ImageButton mImageButton_nav_coupe;

    public podo_infos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment podo_infos.
     */
    // TODO: Rename and change types and number of parameters
    //Ok et moi je vire les paramètres pour l'instant !
    /**
    public static podo_infos newInstance(String param1, String param2) {
        podo_infos fragment = new podo_infos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     */
    public static podo_infos newInstance() {
        podo_infos fragment = new podo_infos();
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
        View v=inflater.inflate(R.layout.fragment_podo_infos, container, false);

        // Inflate the layout for this fragment
        //Barre de navigation en bas
        mImageButton_nav_retour= (ImageButton)v.findViewById(R.id.imageButton_nav_retour);
        mImageButton_nav_home=(ImageButton)v.findViewById(R.id.imageButton_nav_home);
        mImageButton_nav_coupe=(ImageButton)v.findViewById(R.id.imageButton_nav_coupe);

        mImageButton_nav_retour.setOnClickListener(this);
        mImageButton_nav_home.setOnClickListener(this);
        mImageButton_nav_coupe.setOnClickListener(this);

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
                        .replace(R.id.container, podo.newInstance())
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
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        public void onPodoInfosFragmentInteraction(String string);
    }
}
