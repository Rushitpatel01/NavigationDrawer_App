package com.example.navigationdrawerapp.ui;

import static android.app.Activity.RESULT_OK;
import static com.example.navigationdrawerapp.Activitys.MainActivity.editor;
import static com.example.navigationdrawerapp.Activitys.MainActivity.preferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerapp.R;

import java.io.ByteArrayOutputStream;

import Models.Instance_Class;
import Models.ProductData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddproductFragment extends Fragment {

         EditText t1 ,t2,t3;
         ImageView  img;
         Button btnadd;
         final  CharSequence[] items = {"Camera", "Gallery"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_addproduct,container,false);
        t1=view.findViewById(R.id.pname);
        t2=view.findViewById(R.id.pdes);
        t3=view.findViewById(R.id.pprize);
        img=view.findViewById(R.id.imageView);
        btnadd=view.findViewById(R.id.btnAdd);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Chose Image");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (items[which].equals("Camera")) {
                            launchCamera();


                        } else if (items[which].equals("Gallery")) {

                            Intent GalleryIntent = null;
                            GalleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            GalleryIntent.setType("image/*");
                            GalleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(GalleryIntent, 0);

                        }
                    }
                });
                builder.show();

                if (ContextCompat.checkSelfPermission(AddproductFragment.this.getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // If not granted, request the CAMERA permission
                    ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, 100);
                }
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Drawable drawable=img.getDrawable();
             Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();


                String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                String uid = preferences.getString("id", "1");
                editor.commit();

                Instance_Class.callAPI().productUser(uid,t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),base64Image).enqueue(new Callback<ProductData>() {
                    @Override
                    public void onResponse(Call<ProductData> call, Response<ProductData> response) {

                        if (response.body().getConnection() == 1) {
                            if (response.body().getproductaddd() == 1) {

                                editor.putString("userid", uid);
                                editor.commit();

                                Toast.makeText(getContext(), "Your Product Is Successful Add", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }



                    @Override
                    public void onFailure(Call<ProductData> call, Throwable t) {
                        Toast.makeText(getContext(), "Your Product Is Not Successful Add", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        return  view;
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //for take image from gallery
        if (resultCode == Activity.RESULT_OK) {

            Log.i("GalleryCode", "" + requestCode);
            Uri ImageURI = data.getData();
            img.setImageURI(ImageURI);

        }

        // for take image from camera
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Get the captured image from the intent's data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Do something with the captured image
            img.setImageBitmap(imageBitmap);
        }


    }

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 100);
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with launching the camera
                launchCamera();
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }

}
