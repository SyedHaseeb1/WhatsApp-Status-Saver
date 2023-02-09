package com.hsb.whatsappsaver.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.hsb.whatsappsaver.R;
import com.hsb.whatsappsaver.videoplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private ArrayList<String> arrayList, arrayList2, arrayList3;
    String foldername = "/WhatsApp/Media/.Statuses/";

    static MyAdapter2 myAdapter2;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        final GridView gridView = root.findViewById(R.id.gridview);
        final GridView gridView2 = root.findViewById(R.id.gridview2);
        File Status_Saver = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/");
        Status_Saver.mkdirs();

        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);


                if (Integer.parseInt(s) == 1) {
                    gridView.setAdapter(new MyAdapter(root.getContext()));
                } else if (Integer.parseInt(s) == 2) {
                    gridView2.setVisibility(View.VISIBLE);
                    myAdapter2 = new MyAdapter2(root.getContext());
                    myAdapter2.notifyDataSetChanged();
                    gridView2.invalidateViews();
                    gridView2.setAdapter(myAdapter2);

                    gridView2.invalidateViews();


                }

            }
        });
        return root;
    }

    @Override //For Fragments.
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // show menu when menu button is pressed
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        String message = "";
        if (item.getItemId() == R.id.Menu_AboutUs) {
            message = "You selected option 1!";
        } else if (item.getItemId() == R.id.Menu_LogOutMenu) {
            message = "You selected option 2!";
        } else {
            message = "Why would you select that!?";
        }

        // show message via toast
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        toast.show();

        return true;
    }

    //WhatsApp folder
    static final class MyAdapter extends BaseAdapter {
        private final List<Item> mItems = new ArrayList<MyAdapter.Item>();
        private final LayoutInflater mInflater;
        Context context1;
        int aa = 0;
        private static final String TAG = PlaceholderFragment.class.getSimpleName();
        TextView textview;
        // private ArrayAdapter<String> adapter;
        private ArrayList<String> arrayList, arrayList2, arrayList3;
        String foldername = "/WhatsApp/Media/.Statuses/";
        String android_11 = "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses/";


        public MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            context1 = context;
            arrayList = new ArrayList<>();
            arrayList2 = new ArrayList<>();
            arrayList3 = new ArrayList<>();
            if (Build.VERSION.SDK_INT > 10) {
                foldername = android_11;
            }
            addtofileslist_WhatsApp();


        }

        @Override
        public int getCount() {
            // arrayList3.size();
            return arrayList3.size();
        }

        @Override
        public String getItem(int i) {
            //arrayList3.get(i);
            return arrayList3.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            final ImageView picture2;
            TextView name;
            ImageView more;
            final String dest = Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/";
            if (v == null) {
                v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
                v.setTag(R.id.picture, ((View) v).findViewById(R.id.picture));
                v.setTag(R.id.picture2, ((View) v).findViewById(R.id.picture2));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.more, v.findViewById(R.id.more));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            picture2 = (ImageView) v.getTag(R.id.picture2);
            name = (TextView) v.getTag(R.id.text);
            more = (ImageView) v.getTag(R.id.more);

            //Item item = getItem(i);

            if (arrayList3.get(i).endsWith(".mp4")) {
                Glide.with(context1)
                        .load(R.drawable.video_icon)
                        .error(R.drawable.opps)
                        .into(picture2);
                Glide.with(context1)
                        .load(arrayList3.get(i).toString().replace(".mp4",".3gp"))
                        .error(R.drawable.opps)
                        .into(picture);
            }
            if (arrayList2.contains("Attempt to get length of null array")) {
                Glide.with(context1)
                        .load(R.drawable.nopic)
                        .error(R.drawable.opps)
                        .into(picture);
                name.setText(arrayList.get(i));
            } else {
                Glide.with(context1)
                        .load(" ")
                        .into(picture2);
            }

            Glide.with(context1)
                    .load(arrayList3.get(i))
                    .error(R.drawable.opps)
                    .into(picture);
            name.setText(arrayList.get(i));
            String[] split = arrayList.get(i).split("WhatsApp ");

            File file = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/"
                    + split[1]);


            if (file.exists()) {
                Glide.with(context1)
                        .load(R.drawable.done)
                        .error(R.drawable.opps)
                        .into(picture2);
            }

            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uri = arrayList3.get(i);
                    if (uri.endsWith(".mp4")) {
                        Intent intent = new Intent(context1, videoplayer.class);
                        intent.putExtra("uri", uri);
                        context1.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context1, com.hsb.whatsappsaver.openimage.class);
                        intent.putExtra("uri", uri);
                        context1.startActivity(intent);
                    }
                }
            });


            more.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(final View v) {
//                  PopupMenu menu = new PopupMenu(context1, v);
//                  menu.getMenu().add("Open");
//                  menu.getMenu().add("Delete");
//                  menu.show();
//                  menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                      @Override
//                      public boolean onMenuItemClick(MenuItem item) {
//                          CharSequence title = item.getTitle();
//                          if ("Open".equals(title)) {
//                              Toast.makeText(context1, "Open" +arrayList.get(i)+ " clicked", Toast.LENGTH_SHORT).show();
//                          } else if ("Delete".equals(title)) {
//                              Toast.makeText(context1, "Delete" + " clicked", Toast.LENGTH_SHORT).show();
//
//                          }
//                          return true;
//                      }
//                  });

                    @SuppressLint("RestrictedApi")
                    MenuBuilder menuBuilder = new MenuBuilder(context1);
                    final MenuInflater inflater = new MenuInflater(context1);
                    inflater.inflate(R.menu.menu1, menuBuilder);

                    @SuppressLint("RestrictedApi")
                    MenuPopupHelper optionsMenu = new MenuPopupHelper(context1, menuBuilder, v);
                    optionsMenu.setForceShowIcon(true);
                    optionsMenu.setGravity(Gravity.END);
                    // Set Item Click Listener
                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.copy: // Handle option1 Click
                                    String[] split = arrayList.get(i).split("WhatsApp ");

                                    File file = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/"
                                            + split[1]);


                                    if (file.exists()) {

                                        Toast.makeText(context1, "Already copied", Toast.LENGTH_SHORT).show();
                                    } else {
                                        copyFileOrDirectory(arrayList3.get(i), dest);
                                        Toast.makeText(context1, arrayList.get(i) + " copied", Toast.LENGTH_SHORT).show();
                                    }

                                    Glide.with(context1)
                                            .load(R.drawable.done)
                                            .error(R.drawable.opps)
                                            .into(picture2);
                                    //Toast.makeText(context1, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.share: // Handle share Click
                                    Log.e("Array 1", arrayList.get(i));//file name with whatsapp
                                    // Log.e("Array 2",arrayList2.get(i)); //nothing
                                    Log.e("Array 3", arrayList3.get(i));//path to file in .Status

                                    if (arrayList.get(i).endsWith(".jpg")) {
                                        Intent share = new Intent(Intent.ACTION_SEND);
                                        share.setType("image/*");
                                        String imagePath = arrayList3.get(i);
                                        File imageFileToShare = new File(imagePath);
                                        Uri imageUri = FileProvider.getUriForFile(
                                                context1, "com.hsb.whatsappsaver.provider", imageFileToShare);
                                        share.putExtra(Intent.EXTRA_STREAM, imageUri);
                                        context1.startActivity(Intent.createChooser(share, "Share file!"));

                                    } else if (arrayList.get(i).endsWith(".mp4")) {
                                        Intent share = new Intent(Intent.ACTION_SEND);
                                        share.setType("video/*");
                                        String imagePath = arrayList3.get(i);
                                        File imageFileToShare = new File(imagePath);
                                        Uri imageUri = FileProvider.getUriForFile(
                                                context1, "com.hsb.whatsappsaver.provider", imageFileToShare);
                                        share.putExtra(Intent.EXTRA_STREAM, imageUri);
                                        context1.startActivity(Intent.createChooser(share, "Share file!"));

                                    }


                                    return true;
                                default:
                                    return false;
                            }
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {
                        }
                    });
                    // Display the menu
                    optionsMenu.show();
                }
            });

            picture.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String[] split = arrayList.get(i).split("WhatsApp ");

                    File file = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/"
                            + split[1]);


                    if (file.exists()) {

                        Toast.makeText(context1, "Already copied", Toast.LENGTH_SHORT).show();
                    } else {
                        copyFileOrDirectory(arrayList3.get(i), dest);
                        Toast.makeText(context1, arrayList.get(i) + " copied", Toast.LENGTH_SHORT).show();
                    }

                    Glide.with(context1)
                            .load(R.drawable.done)
                            .error(R.drawable.opps)
                            .into(picture2);
                    return false;
                }
            });
            return v;
        }

        private static class Item {
            public final String name;
            public final String drawableId;

            Item(String name, String drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }


        public void addtofileslist_WhatsApp() {

            try {
                String kbs = null, mbs = null;


                String path = Environment.getExternalStorageDirectory().toString() + foldername;
                Log.d("Files", "Path: " + path);
                File directory = new File(path);


                final File[] files = directory.listFiles();
                File parentDir = directory.getParentFile();
                assert parentDir != null;

                if (parentDir.isDirectory() && directory.list().length == 0) {
                    Log.e("", directory + " Empty");
                } else {
                    Log.e("", directory + " Not empty");

                    assert files != null;
                    try {
                        for (File value : files) {
                            //  Log.d("Files", "FileName:" + value.getName());

                            File file = new File(path + value.getName());

                            String FILE_NAME = path + value.getName();
                            File filea = new File(FILE_NAME);

                            long sizeInBytes = file.length();
                            mbs = filea.length() / 1024 + " KB";
                            long sizeInMb = sizeInBytes / (1024);
                            if (filea.length() / 1024 > 999) {
                                mbs = filea.length() / 1048576 + " MB";

                            }

                            if (FILE_NAME.contains(".nomedia")) {

                            } else {
                                aa++;
                                arrayList.add(aa + "  WhatsApp " + value.getName());
                                arrayList3.add(path + value.getName());
                                Log.d("Files", "FileName Array:" + arrayList);
                                // Collections.sort(arrayList3);
                                // Collections.sort(arrayList);
                            }
                        }
                    } catch (Exception e) {
                        arrayList2.add("WhatsApp Not Installed");
                    }

                    //Log.d("Files", "End");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            //  progressDialog.show();
//        customAdapter.notifyDataSetChanged();


        }
    }

    //saved
    static final class MyAdapter2 extends BaseAdapter {
        private final LayoutInflater mInflater;
        Context context1;
        int aa = 0;
        private static final String TAG = PlaceholderFragment.class.getSimpleName();
        TextView textview;
        // private ArrayAdapter<String> adapter;
        private ArrayList<String> arrayList, arrayList2, arrayList3;
        String foldername = "/WhatsApp/Media/.Statuses/";
        final String MyFolder = "/Status Saver/WhatsApp Saver/";

        public MyAdapter2(Context context) {
            mInflater = LayoutInflater.from(context);
            context1 = context;
            arrayList = new ArrayList<>();
            arrayList2 = new ArrayList<>();
            arrayList3 = new ArrayList<>();
            File Status_Saver = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/");
            if (!Status_Saver.exists()) {
                Status_Saver.mkdir();
            } else {
                addtofileslist_WhatsApp_saver();
            }

        }

        @Override
        public int getCount() {
            // arrayList3.size();
            return arrayList3.size();
        }

        @Override
        public String getItem(int i) {
            //arrayList3.get(i);
            return arrayList3.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, final View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            final ImageView picture2;
            TextView name;
            ImageView more;
            if (v == null) {
                v = mInflater.inflate(R.layout.grid_item2, viewGroup, false);
                v.setTag(R.id.picture, ((View) v).findViewById(R.id.picture));
                v.setTag(R.id.picture2, ((View) v).findViewById(R.id.picture2));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.more, v.findViewById(R.id.more));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            picture2 = (ImageView) v.getTag(R.id.picture2);
            more = (ImageView) v.getTag(R.id.more);
            name = (TextView) v.getTag(R.id.text);

            //Item item = getItem(i);


            String path = Environment.getExternalStorageDirectory().toString() + MyFolder;
            Log.d("Files", "Path: " + path);
            File directory = new File(path);


            if (arrayList3.get(i).endsWith(".mp4")) {
                Glide.with(context1)
                        .load(R.drawable.video_icon)
                        .error(R.drawable.opps)
                        .into(picture2);

                Glide.with(context1)
                        .load(arrayList3.get(i))
                        .error(R.drawable.opps)
                        .into(picture);

                name.setText(arrayList.get(i));


            } else if (arrayList3.contains("null")) {
                name.setText(arrayList.get(i));
                Glide.with(context1)
                        .load(R.drawable.nopic)
                        .error(R.drawable.opps)
                        .into(picture);
            } else {
                Glide.with(context1)
                        .load(arrayList3.get(i))
                        .error(R.drawable.opps)
                        .into(picture);
                name.setText(arrayList.get(i));
                Glide.with(context1)
                        .load(" ")
                        .into(picture2);
            }


            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uri = arrayList3.get(i);
                    if (uri.endsWith(".mp4")) {
                        Intent intent = new Intent(context1, videoplayer.class);
                        intent.putExtra("uri", uri);
                        context1.startActivity(intent);
                    } else if (arrayList.get(i).contains("No pictures saved yet")) {
                        Toast.makeText(context1, "Nothing saved yet", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(context1, com.hsb.whatsappsaver.openimage.class);
                        intent.putExtra("uri", uri);
                        context1.startActivity(intent);
                    }
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(final View v) {
//                  PopupMenu menu = new PopupMenu(context1, v);
//                  menu.getMenu().add("Open");
//                  menu.getMenu().add("Delete");
//                  menu.show();
//                  menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                      @Override
//                      public boolean onMenuItemClick(MenuItem item) {
//                          CharSequence title = item.getTitle();
//                          if ("Open".equals(title)) {
//                              Toast.makeText(context1, "Open" +arrayList.get(i)+ " clicked", Toast.LENGTH_SHORT).show();
//                          } else if ("Delete".equals(title)) {
//                              Toast.makeText(context1, "Delete" + " clicked", Toast.LENGTH_SHORT).show();
//
//                          }
//                          return true;
//                      }
//                  });

                    @SuppressLint("RestrictedApi")
                    MenuBuilder menuBuilder = new MenuBuilder(context1);
                    final MenuInflater inflater = new MenuInflater(context1);
                    inflater.inflate(R.menu.menu, menuBuilder);

                    @SuppressLint("RestrictedApi")
                    MenuPopupHelper optionsMenu = new MenuPopupHelper(context1, menuBuilder, v);
                    optionsMenu.setForceShowIcon(true);
                    optionsMenu.setGravity(Gravity.END);
                    // Set Item Click Listener
                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.Menu_AboutUs: // Handle open Click
                                    String uri = arrayList3.get(i);
                                    if (uri.endsWith(".mp4")) {
                                        Intent intent = new Intent(context1, videoplayer.class);
                                        intent.putExtra("uri", uri);
                                        context1.startActivity(intent);
                                    } else if (arrayList.get(i).contains("No pictures saved yet")) {
                                        Toast.makeText(context1, "Nothing saved yet", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(context1, com.hsb.whatsappsaver.openimage.class);
                                        intent.putExtra("uri", uri);
                                        context1.startActivity(intent);
                                    }
                                    //Toast.makeText(context1, item.getTitle() + "", Toast.LENGTH_SHORT).show();
                                    return true;
                                case R.id.Menu_LogOutMenu: // Handle delete Click
                                    if (arrayList.contains("No pictures saved yet")) {
                                        Toast.makeText(context1, "Nothing saved yet", Toast.LENGTH_SHORT).show();
                                    } else {
                                        AlertDialog alertDialog = new AlertDialog.Builder(context1).create();
                                        alertDialog.setTitle("Kabootar");
                                        alertDialog.setMessage("Are you sure to delete?");
                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        String uri2 = arrayList3.get(i);
                                                        Log.e("array3", uri2);
                                                        File file = new File(uri2);
                                                        boolean deleted = file.delete();
                                                        if (deleted) {
                                                            Toast.makeText(context1, arrayList.get(i) + " Deleted", Toast.LENGTH_SHORT).show();
                                                            myAdapter2.addtofileslist_WhatsApp_saver();
                                                            myAdapter2.notifyDataSetChanged();
                                                        } else {
                                                            Toast.makeText(context1, "Kabootar Error", Toast.LENGTH_SHORT).show();

                                                        }
                                                        dialog.dismiss();
                                                    }
                                                });
                                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                        alertDialog.show();
                                    }
                                    return true;
                                case R.id.share:

                                    Log.e("array 1", arrayList.get(i));
                                    Log.e("array 2", arrayList3.get(i));
                                    if (arrayList.get(i).endsWith(".jpg")) {
                                        Intent share = new Intent(Intent.ACTION_SEND);
                                        share.setType("image/*");
                                        String imagePath = arrayList3.get(i);
                                        File imageFileToShare = new File(imagePath);
                                        Uri imageUri = FileProvider.getUriForFile(
                                                context1, "com.hsb.whatsappsaver.provider", imageFileToShare);
                                        share.putExtra(Intent.EXTRA_STREAM, imageUri);
                                        context1.startActivity(Intent.createChooser(share, "Share file!"));

                                    } else if (arrayList.get(i).endsWith(".mp4")) {
                                        Intent share = new Intent(Intent.ACTION_SEND);
                                        share.setType("video/*");
                                        String imagePath = arrayList3.get(i);
                                        File imageFileToShare = new File(imagePath);
                                        Uri imageUri = FileProvider.getUriForFile(
                                                context1, "com.hsb.whatsappsaver.provider", imageFileToShare);
                                        share.putExtra(Intent.EXTRA_STREAM, imageUri);
                                        context1.startActivity(Intent.createChooser(share, "Share file!"));

                                    }

                                default:
                                    return false;
                            }
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {
                        }
                    });
                    // Display the menu
                    optionsMenu.show();
                }
            });


            return v;
        }

        private static class Item {
            public final String name;
            public final String drawableId;

            Item(String name, String drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }


        public void addtofileslist_WhatsApp_saver() {

            try {
                arrayList.clear();
                arrayList3.clear();
                aa = 0;
                String kbs = null, mbs = null;

                String path = Environment.getExternalStorageDirectory().toString() + MyFolder;
                Log.d("Files", "Path: " + path);
                File directory = new File(path);


                final File[] files = directory.listFiles();
                File parentDir = directory.getParentFile();
                assert parentDir != null;

                if (directory.isDirectory() && directory.list().length == 0) {
                    Log.e("addfile", directory + " Empty");
                    arrayList3.add("null");
                    arrayList.add("No pictures saved yet");
                } else {
                    arrayList3.clear();
                    arrayList.clear();
                    Log.e("addfile", directory.getParentFile() + " Not empty");

                    assert files != null;
                    for (File value : files) {
                        //  Log.d("Files", "FileName:" + value.getName());

                        File file = new File(path + value.getName());

                        String FILE_NAME = path + value.getName();
                        File filea = new File(FILE_NAME);

                        long sizeInBytes = file.length();
                        mbs = filea.length() / 1024 + " KB";
                        long sizeInMb = sizeInBytes / (1024);
                        if (filea.length() / 1024 > 999) {
                            mbs = filea.length() / 1048576 + " MB";

                        }

                        if (FILE_NAME.contains(".nomedia")) {

                        } else {
                            aa++;
                            arrayList.add(aa + "  WhatsApp " + value.getName());
                            arrayList3.add(path + value.getName());
                            Log.d("Files", "FileName Array:" + arrayList);
                            // Collections.sort(arrayList3);
                            // Collections.sort(arrayList);
                        }
                    }

                    //Log.d("Files", "End");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            //  progressDialog.show();
//        customAdapter.notifyDataSetChanged();


        }
    }

    public static void copyFileOrDirectory(String srcDir, String dstDir) {
        File Status_Saver = new File(Environment.getExternalStorageDirectory().toString() + "/Status Saver/WhatsApp Saver/");
        if (!Status_Saver.exists()) {
            Status_Saver.mkdir();
        }
        try {

            File src = new File(srcDir);
            File dst = new File(dstDir, src.getName());

            if (src.isDirectory()) {

                String files[] = src.list();
                int filesLength = files.length;
                for (int i = 0; i < filesLength; i++) {
                    String src1 = (new File(src, files[i]).getPath());
                    String dst1 = dst.getPath();
                    copyFileOrDirectory(src1, dst1);

                }
            } else {
                copyFile(src, dst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();

            myAdapter2.addtofileslist_WhatsApp_saver();
            myAdapter2.notifyDataSetChanged();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
            //myAdapter2.notifyDataSetChanged();

        } finally {
            if (source != null) {
                source.close();
                // myAdapter2.notifyDataSetChanged();
            }
            if (destination != null) {
                destination.close();
                // myAdapter2.notifyDataSetChanged();
            }
        }
    }
}