package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.mine.UploadImageBean;
import com.queqiaolove.util.CommonUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zchk on 2016/11/8.
 */
public class UpLoadPhotoActivity extends Activity {
    private static String TYPE = "type";
    public static String PHOTO = "photo";
    public static String PIC23 = "pic23";
    public static String PIC24 = "pic24";
    private Activity mActivity;
    private String type;
    /*上传头像*/
    private final int SELECT_PIC_BY_TACK_PHOTO = 0;
    private final int SELECT_PIC_BY_PICK_PHOTO = 1;
    private Uri photoUri;
    private String picPath = "";
    private ProgressDialog progressDialog;
    private final int MY_PERMISSIONS_REQUEST_SELECTPHOTO_PHONE = 123;
    private final int MY_PERMISSIONS_REQUEST_TAKEPHOTO_PHONE = 124;
    private final int RESULT_CAMERA_CROP_PATH_RESULT = 3;
    private String carrier;//厂商
    private int selectflag;//上传方式
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadphoto);
        mActivity = this;
        activityOnCreate(getIntent().getExtras());
        initView();
    }

    private void initView() {
        switch (type) {
            case "photo":
                takePhoto();
                break;

            case "pic23":
                pickPhoto23();
                break;

            case "pic24":
                pickPhoto24();
                break;
        }
    }

    private void activityOnCreate(Bundle extras) {
        type = extras.getString(TYPE);
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, UpLoadPhotoActivity.class);
        intent.putExtra(TYPE, data);
        activity.startActivity(intent);
    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            /***
             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
             * 如果不使用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
             */
            ContentValues values = new ContentValues();
            photoUri = mActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Log.e("takephotouri", photoUri + "");
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
            mActivity.startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        } else {
            Toast.makeText(mActivity, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 从相册中取图片23
     */
    private void pickPhoto23() {
        Intent intent = new Intent();
        /*如果要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型*/
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    /**
     * 从相册中取图片24
     */
    private void pickPhoto24() {
        Intent intent = new Intent();
        /*如果要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型*/
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        mActivity.startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    /*打开相机，或相册的回调方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 点击取消按钮
        if (resultCode == RESULT_CANCELED) {
            finish();
            return;
        }

        // 可以使用同一个方法，这里分开写为了防止以后扩展不同的需求
        switch (requestCode) {
            case SELECT_PIC_BY_PICK_PHOTO:// 如果是直接从相册获取
                selectflag = SELECT_PIC_BY_PICK_PHOTO;
                photoUri = data.getData();
                //Log.e("photoUri", photoUri +"");
                if (Build.VERSION.SDK_INT >= 23) {
                    picPath = CommonUtils.getPath(mActivity, photoUri);
                    File userIcon = new File(picPath);
                    photoUri = Uri.fromFile(userIcon);
                    Log.e("pickphotoUri24", photoUri + "");
                } else {
                    picPath = CommonUtils.getPath(mActivity, photoUri);
                    File userIcon = new File(picPath);
                    photoUri = Uri.fromFile(userIcon);

                    Log.e("pickphotoUri23", photoUri + "");
                }
                doPhoto(requestCode, data);
                break;
            case SELECT_PIC_BY_TACK_PHOTO:// 如果是调用相机拍照时
                selectflag = SELECT_PIC_BY_TACK_PHOTO;
                carrier = Build.MANUFACTURER;
                Log.e("phonecarrier", carrier + "");
                if (Build.VERSION.SDK_INT >= 23) {
                    picPath = CommonUtils.getPath(mActivity, photoUri);
                    File userIcon = new File(picPath);
                    photoUri = Uri.fromFile(userIcon);
                    Log.e("takephotoUri24", photoUri + "");
                } else {
                    picPath = CommonUtils.getPath(mActivity, photoUri);
                    File userIcon = new File(picPath);
                    photoUri = Uri.fromFile(userIcon);
                    Log.e("takephotoUri23", photoUri + "");
                }
                doPhoto(requestCode, data);

                break;
            case RESULT_CAMERA_CROP_PATH_RESULT:// 如果裁剪成功
                doPhoto(requestCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, Intent data) {

        if (Build.VERSION.SDK_INT >= 23) {

            Log.e("userIcon", picPath);
            if (picPath.equals("") || picPath.equals(null)) {
                toast("图片读取错误");
                finish();
                return;
            }
            progressDialog = ProgressDialog.show(mActivity, null, "正在上传图片，请稍候...");
            uploadUserIcon();
            return;
        } else {
            Log.e("userIcon", picPath);
            if (picPath.equals("") || picPath.equals(null)) {
                toast("图片读取错误");
                finish();
                return;
            }
            progressDialog = ProgressDialog.show(mActivity, null, "正在上传图片，请稍候...");
            uploadUserIcon();
        }

    }

    /*上传照片*/
    private void uploadUserIcon() {
        userid = QueQiaoLoveApp.getUserId();
        File userIcon = new File(picPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), userIcon);
        /*MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", userIcon.getName(), requestBody);*/
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        Log.e("filename", userIcon.getName());
        mineAPI.uploadImage(userid,
                Constants.UPLOADIMAGE_PHOTO, 0, 0, requestBody).enqueue(new Callback<UploadImageBean>() {
            @Override
            public void onResponse(Call<UploadImageBean> call, Response<UploadImageBean> response) {
                UploadImageBean body = response.body();
                if (body.getReturnvalue().equals("true")) {
                    toast("上传成功");
                    if (progressDialog != null) progressDialog.dismiss();
                    finish();
                } else {
                    toast(body.getMsg());
                    if (progressDialog != null) progressDialog.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UploadImageBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });

    }

    /**
     * toast工具类
     */
    public void toast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }
}
