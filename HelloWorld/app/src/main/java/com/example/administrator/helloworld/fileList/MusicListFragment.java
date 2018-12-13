package com.example.administrator.helloworld.fileList;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.helloworld.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListFragment extends Fragment {

    private Context context;
    private View layout;

    private RecyclerView fileList;
    private List<File> files;
    private String path;
    private MyAdapter myAdapter;

    public MusicListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_music_list, container, false);
        bindView();
        init();
        return layout;
    }

    private void bindView() {
        fileList = layout.findViewById(R.id.fileList);

    }
    private void init() {
        context = getContext();
        files = new ArrayList<>();
        path = Environment.getExternalStorageDirectory().toString();
        getFiles();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        myAdapter = new MyAdapter();
        fileList.setLayoutManager(manager);
        fileList.setAdapter(myAdapter);
        fileList.setNestedScrollingEnabled(false);
    }

    private void getFiles() {
        File saveList = new File(context.getFilesDir(), "saveList");
        if (saveList.exists()) {
            if (files.size() == 0 && saveList.length() != 0) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(context.openFileInput("saveList"));
                    files = (ArrayList<File>) ois.readObject();
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                saveList.delete();
                search();
            }
        }
        if (files.size() > 0) {
            saveList.delete();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("saveList", context.MODE_PRIVATE));
                oos.writeObject(files);
                oos.flush();
                oos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void search() {
        File menu = new File(path);
        if (menu.isDirectory()) {
            for (File file : menu.listFiles()) {
                if (file.isDirectory()) {
                    path += "/" + file.getName();
                    search();
                } else {
                    if (file.getName().contains(".")) {
                        if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".mp3")) {
                            files.add(file);
                        }
                    }
                    continue;
                }
            }
        } else if (menu.getName().contains(".")) {
            if (menu.getName().substring(menu.getName().lastIndexOf(".")).equals(".mp3")) {
                files.add(menu);
            }
        }
        System.out.println(path);
        path = path.substring(0, path.lastIndexOf("/"));
        System.out.println(path);
        return;
    }

//    long time = 0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            System.out.println(System.currentTimeMillis() - time);
//            if (System.currentTimeMillis() - time < 1000) {
//                return super.onKeyDown(keyCode, event);
//            }
//
//            if (! path.equals(Environment.getExternalStorageDirectory().toString())) {
//                path = path.substring(0, path.lastIndexOf('/'));
//                getFiles();
//                myAdapter.notifyDataSetChanged();
//            } else {
//                Toast.makeText(FileListActivity.this, "双击退出", Toast.LENGTH_SHORT).show();
//            }
//
//            time = System.currentTimeMillis();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyAdapter.ViewHolder(getLayoutInflater().inflate(R.layout.item_filelist, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyAdapter.ViewHolder viewHolder = (ViewHolder) holder;
            final File curFile = files.get(position);
            viewHolder.fileName.setText(curFile.getName());
            viewHolder.fileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (curFile.isDirectory()) {

                        path += "/" + curFile.getName();
                        System.out.println(path);
                        getFiles();
                        notifyDataSetChanged();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return files == null ? 0 : files.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView fileName;
            public ViewHolder(View itemView) {
                super(itemView);

                fileName = itemView.findViewById(R.id.fileName);
            }
        }
    }

}
