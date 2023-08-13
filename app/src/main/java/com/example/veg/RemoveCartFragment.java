package com.example.veg;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class RemoveCartFragment extends DialogFragment {
    View.OnClickListener onClickListener;


    MaterialButton mbRemoveCart;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public RemoveCartFragment(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public RemoveCartFragment() {

    }

    public static RemoveCartFragment newInstance(String param1, String param2) {
        RemoveCartFragment fragment = new RemoveCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

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
        View view = inflater.inflate(R.layout.fragment_remove_cart, container, false);
        mbRemoveCart = view.findViewById(R.id.mbRemoveCart);
        mbRemoveCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view);
                dismiss();
            }
        });
        return view;
    }

}
