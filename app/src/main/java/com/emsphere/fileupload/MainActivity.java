package com.emsphere.fileupload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    boolean hidden=true;
    LinearLayout mRevealView;
    ImageButton ib_gallery,ib_contacts,ib_location;
    ImageButton ib_video,ib_audio,ib_camera;
    TextView textView;
    ArrayList<String> arrayList;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_GALLARY = 2;
    static final int REQUEST_Document = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*toolbar=(Toolbar)findViewById(R.id.toolbar);
        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        //ib_audio=(ImageButton)findViewById(R.id.audio);
        ib_camera=(ImageButton)findViewById(R.id.camera);
       // ib_contacts=(ImageButton)findViewById(R.id.contacts);
        ib_gallery=(ImageButton)findViewById(R.id.gallery);
        ib_location=(ImageButton)findViewById(R.id.camera1);
       // ib_video=(ImageButton)findViewById(R.id.video);
        //ib_audio.setOnClickListener(this);
        ib_camera.setOnClickListener(this);
       // ib_contacts.setOnClickListener(this);
        ib_gallery.setOnClickListener(this);
        ib_location.setOnClickListener(this);
       // ib_video.setOnClickListener(this);
        setSupportActionBar(toolbar);
        mRevealView.setVisibility(View.INVISIBLE);*/

        textView=findViewById(R.id.filename);
        arrayList=new ArrayList<String>();
        arrayList.add(".bmp");
        arrayList.add(".gif");
        arrayList.add(".png");
        arrayList.add(".jpg");
        arrayList.add(".jpeg");
        arrayList.add(".doc");
        arrayList.add(".docx");
        arrayList.add(".pdf");
        arrayList.add(".txt");
        arrayList.add(".xls");
        arrayList.add(".xlsx");
    }

    // imagebutton click events
   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.camera:
                Snackbar.make(v, "Camera Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.camera1:
                Snackbar.make(v, "Location Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;

                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("file*//*");
                intent1.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent1, "Select a File to Upload"),
                            REQUEST_Document );
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.gallery:
                Snackbar.make(v, "Gallery Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT, Uri.parse("content://media/internal/images/media"));
                //intent.setType("image*//*");
                //intent.addCategory(Intent.CATEGORY_OPENABLE);
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            REQUEST_GALLARY );
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       *//* if (id == R.id.action_settings) {

            return true;
        }*//*

       if(id== R.id.action_attachment){// attachment icon click event

            // finding X and Y co-ordinates
            int cx = (mRevealView.getLeft() + mRevealView.getRight());
            int cy = (mRevealView.getTop());

            // to find  radius when icon is tapped for showing layout
            int startradius=0;
            int endradius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

            // performing circular reveal when icon will be tapped
            Animator animator = ViewAnimationUtils.createCircularReveal(mRevealView,                     cx, cy, startradius, endradius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(400);

            //reverse animation
            // to find radius when icon is tapped again for hiding layout
            //  starting radius will be the radius or the extent to which circular reveal animation is to be shown

            int reverse_startradius = Math.max(mRevealView.getWidth(),mRevealView.getHeight());

            //endradius will be zero
            int reverse_endradius=0;

            // performing circular reveal for reverse animation
            Animator animate = ViewAnimationUtils.createCircularReveal(mRevealView,cx,cy,reverse_startradius,reverse_endradius);
            if(hidden){

                // to show the layout when icon is tapped
                mRevealView.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            }
            else {
                mRevealView.setVisibility(View.VISIBLE);

                // to hide layout on animation end
                animate.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }
                });
                animate.start();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                String uriString = selectedFileURI.toString();
                File file = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + file.getName());
                String displayName = null;
                String uploadedFileName = file.getName().toString();
                // tokens = new StringTokenizer(uploadedFileName, ":");
                // String first = tokens.nextToken();
                //String file_1 = tokens.nextToken().trim();
                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = getContentResolver().query(selectedFileURI, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            String displayName1 = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            String fileExt = displayName1.substring(displayName1.lastIndexOf('.'));
                            if (arrayList.indexOf(fileExt) < 0) {
                                displayName="Invalid file format";

                            }else {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            }
                        }
                    } finally {
                        cursor.close();
                    }
                }
                textView.setText(displayName);
            }
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {
                String dirName="/Favorite";
                Bitmap image = (Bitmap) data.getExtras().get("data");
                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                String fileName = "fav" + timeStamp + ".JPG";


                File direct = new File(Environment.getExternalStorageDirectory() + dirName);

                if (!direct.exists()) {
                    File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + dirName);
                    wallpaperDirectory.mkdirs();
                }

                File file = new File(new File(Environment.getExternalStorageDirectory() + dirName), fileName);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    FileOutputStream out = new FileOutputStream(file);

                    //Bitmap bitmap = BitmapFactory.decodeFile(imagesPathArrayList.get(pos));
                    image.compress(Bitmap.CompressFormat.JPEG, 20, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textView.setText(fileName);
                // do whatever you want with the image now
            }
        }

        if (requestCode == REQUEST_Document) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                String uriString = selectedFileURI.toString();
                File file = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + file.getName());
                String displayName = null;
                String uploadedFileName = file.getName().toString();
                if (uriString.startsWith("file://")) {
                    String fileExt = uploadedFileName.substring(uploadedFileName.lastIndexOf('.'));
                    if (arrayList.indexOf(fileExt) < 0) {
                        displayName="Invalid file format";

                    }else {
                        displayName = file.getName();                    }
                    //displayName = file.getName();
                }
                textView.setText(displayName);
            }
        }
    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.demo);

        ImageButton imageButton= dialog.findViewById(R.id.gallery);
        ImageButton imageButton1= dialog.findViewById(R.id.camera);
        ImageButton imageButton2= dialog.findViewById(R.id.camera1);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            REQUEST_GALLARY );
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                dialog.dismiss();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("file/*");
                intent1.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent1, "Select a File to Upload"),
                            REQUEST_Document );
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void OnDialog(View view) {
        showDialog(MainActivity.this);
    }
    public void OnBrowse(View view) {
        //showFileChooser();
    }





   /* TextView textView;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.filename);
        arrayList=new ArrayList<String>();
        arrayList.add(".bmp");
        arrayList.add(".gif");
        arrayList.add(".png");
        arrayList.add(".jpg");
        arrayList.add(".jpeg");
        arrayList.add(".doc");
        arrayList.add(".docx");
        arrayList.add(".pdf");
        arrayList.add(".txt");
        arrayList.add(".xls");
        arrayList.add(".xlsx");
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file*//*");
        // Valid File Format : bmp, gif, png, jpg, jpeg, doc, docx, pdf, txt, xls, xlsx
        intent.setType("image*//*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
       // intent.addCategory(Intent.Ca);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                String uriString = selectedFileURI.toString();
                File file = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + file.getName());
                String displayName = null;
                String uploadedFileName = file.getName().toString();
               // tokens = new StringTokenizer(uploadedFileName, ":");
              // String first = tokens.nextToken();
               //String file_1 = tokens.nextToken().trim();
                if (uriString.startsWith("content://")) {
                    Cursor cursor = null;
                    try {
                        cursor = getContentResolver().query(selectedFileURI, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                           String displayName1 = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                           String fileExt = displayName1.substring(displayName1.lastIndexOf('.'));
                            if (arrayList.indexOf(fileExt) < 0) {
                                displayName="Invalid file format";

                            }else {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            }
                        }
                    } finally {
                        cursor.close();
                    }
                } else if (uriString.startsWith("file://")) {
                    String fileExt = uploadedFileName.substring(uploadedFileName.lastIndexOf('.'));
                    if (arrayList.indexOf(fileExt) < 0) {
                        displayName="Invalid file format";

                    }else {
                        displayName = file.getName();                    }
                    //displayName = file.getName();
                }
                textView.setText(displayName);
            }
        }
    }

    public void OnBrowse(View view) {
        showFileChooser();
    }*/
    
    //this project is added by vikas lakade date 20/12/2018
}
