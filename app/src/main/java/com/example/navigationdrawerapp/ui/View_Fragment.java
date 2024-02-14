package com.example.navigationdrawerapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerapp.R;

import Models.Instance_Class;
import Models.ViewProductData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_Fragment extends Fragment {

    RecyclerView recyclerView;
    ViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_view, container, false);
        recyclerView = view.findViewById(R.id.view_product_recycler);

        String uid = null;
        Instance_Class.callAPI().viewproductuser(uid).enqueue(new Callback<ViewProductData>() {
            @Override
            public void onResponse(Call<ViewProductData> call, Response<ViewProductData> response) {
                if(response.body().getConnection()==1 && response.body().getResult() ==1 ){


                    Toast.makeText(getContext(), "Data Available", Toast.LENGTH_LONG).show();

                }



            }

            @Override
            public void onFailure(Call<ViewProductData> call, Throwable t) {

            }
        });
        return view;
    }
}