package me.heyboy.mymvpdemo.photo;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.heyboy.mymvpdemo.R;
import me.heyboy.mymvpdemo.model.entities.ImgRecorder;
import me.heyboy.mymvpdemo.services.PhotoImgService;


public class PhotoFragment extends Fragment implements PhotoContract.View {
    private final static String TAG = "PhotoFragment";

    private PhotoContract.Presenter mPhotoPresenter;
    private RecyclerView mRecyclerView;
    private List<ImgRecorder> mImgRecorderList=new ArrayList<>();

    public PhotoFragment() {
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoPresenter.setReources(getResources());

        try {
            mImgRecorderList=mPhotoPresenter.fetchRecorders();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_photo_gallery);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));


        setAdapter(mImgRecorderList);

        return view;
    }

    @Override
    public void setAdapter(List<ImgRecorder> imgRecorders) {
        if (isAdded()) {
            PhotoAdapter adapter = new PhotoAdapter(imgRecorders);
            mRecyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void setPresenter(PhotoContract.Presenter presenter) {
        mPhotoPresenter = presenter;
    }

    /**
     * 显示下载进度
     */
    @Override
    public void showLoadding() {

    }

    /**
     * 显示美女图片
     */
    @Override
    public void showImg() {

    }


    /**
     * RecyclerView 的 ViewHolder
     */
    public class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public PhotoHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imgview_photo_grallery);
        }

        //绑定设置图片
        public void bindDrawble(Drawable drawable) {
            mImageView.setImageDrawable(drawable);
        }
    }


    /**
     * RecyclerView 的 Adapter
     */
    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        private List<ImgRecorder> mImgRecorderList;

        //构造函数构造ImgRecorderList
        public PhotoAdapter(List<ImgRecorder> imgRecorderList) {
            mImgRecorderList = imgRecorderList;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.photo_grallery, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            ImgRecorder imgRecorder = mImgRecorderList.get(position);
            Drawable drawable = getResources().getDrawable(R.drawable.boduo);
            holder.bindDrawble(drawable);

            mPhotoPresenter.download(holder,imgRecorder.getPicUrl());
        }

        @Override
        public int getItemCount() {
            return mImgRecorderList.size();
        }
    }


    private class FetchPhotoItems extends AsyncTask<Void, Void, List<ImgRecorder>> {

        @Override
        protected List<ImgRecorder> doInBackground(Void... voids) {
            try {
                List<ImgRecorder> imgRecorderList = PhotoImgService.getImgRecorders();
                Log.i(TAG, "运行到FetchPhotoItems类中。获取记录的长度为：" + imgRecorderList.size());
                return imgRecorderList;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<ImgRecorder> items) {
            super.onPostExecute(items);
            mImgRecorderList = items;
            Log.i(TAG," 88888 onPostExecute mImgRecorderList size"+mImgRecorderList.size());
            setAdapter(mImgRecorderList);
        }

    }


}
