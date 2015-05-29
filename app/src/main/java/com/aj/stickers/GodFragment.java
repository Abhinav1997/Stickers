package com.aj.stickers;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GodFragment extends Fragment {

    View rootView;
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    public GodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_grid, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_items, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch(position) {
                    case 0:
                        shareimg(R.drawable.god1);
                        break;
                    case 1:
                        shareimg(R.drawable.god2);
                        break;
                    case 2:
                        shareimg(R.drawable.god3);
                        break;
                    case 3:
                        shareimg(R.drawable.god4);
                        break;
                    case 4:
                        shareimg(R.drawable.god5);
                        break;
                    case 5:
                        shareimg(R.drawable.god6);
                        break;
                    case 6:
                        shareimg(R.drawable.god7);
                        break;
                    case 7:
                        shareimg(R.drawable.god8);
                        break;
                    case 8:
                        shareimg(R.drawable.god9);
                        break;
                    case 9:
                        shareimg(R.drawable.god10);
                        break;
                    case 10:
                        shareimg(R.drawable.god11);
                        break;
                    case 11:
                        shareimg(R.drawable.god12);
                        break;
                    default:
                        break;
                }
            }
        });

        return rootView;
    }

    private void shareimg(int image) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        File sd = Environment.getExternalStorageDirectory();
        String fileName = "lstick_temp.png";
        File dest = new File(sd, fileName);
        try {
            FileOutputStream out;
            out = new FileOutputStream(dest);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "lstick_temp.png")));
        startActivity(Intent.createChooser(share, "Share via"));
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.images_god);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }
        return imageItems;
    }
};