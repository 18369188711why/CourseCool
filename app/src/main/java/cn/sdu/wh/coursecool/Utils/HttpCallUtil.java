package cn.sdu.wh.coursecool.Utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * 封装了HTTP的连接
 *
 * @author Administrator
 *
 */
public class HttpCallUtil {

    /**
     * post请求
     * @param url
     * @param name:请求的参数,map:请求的参数
     * @return
     */
    public String getConntionPost(String url, String name,String psw,List data,String method) {
        //Log.v("map2 ====", data.size()+"");
        String sb = new String();
        HttpPost request = null;
        HttpResponse response = null;
        List<NameValuePair> list = null;
        try {
            if (url != null) {

                request = new HttpPost(url);
                if(data != null){
                    list = putParam(data);
                }else if(name != null && psw != null){

                    list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("name", name));
                    list.add(new BasicNameValuePair("pass", psw));
                    list.add(new BasicNameValuePair("method", method));
                }
                request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() > 0){
                        sb = temp.trim().toString();
                    }else{
                        sb = "error response data length";
                    }
                }else{
                    sb = "error response code:"+response.getStatusLine().getStatusCode();
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }
    /**
     * 传递给服务端的数据，用Map进行封装
     * @param data
     * @return
     */
    public List putParam(List data){
        Log.v("map 3====", data.size()+"");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if(data != null){

            for(int i = 0;i<data.size();i++){
                Log.v("=="+data.get(i).toString(), data.get(i).toString());
                list.add(new BasicNameValuePair(data.get(i).toString(), data.get(i).toString()));

            }
        }
        return list;
    }
    /**
     * get请求
     * @param url
     * @param
     * @return
     */
    public String getConntionGet(String url){
        String str = new String();
        HttpGet request = null;
        HttpResponse response = null;
        try {
            if(url != null){

                request = new HttpGet(url);
                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() >= 0){
                        str = temp.substring(0, temp.length()-1);
                    }else{
                        str = "error response data length "+response.getStatusLine().getStatusCode();
                    }
                }else{
                    str = "error response :"+response.getStatusLine().getStatusCode();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /*
     * 根据位置获取主页面内容
     */
    public String getStatusPost(String url,String x,String y,List data,String method) {
        //Log.v("map2 ====", data.size()+"");
        String s = new String();
        HttpPost request = null;
        HttpResponse response = null;
        List<NameValuePair> list = null;
        try {
            if (url != null) {

                request = new HttpPost(url);
                if(data != null){
                    list = putParam(data);
                }else if(x != null && y !=null ){

                    list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("lng", x));
                    list.add(new BasicNameValuePair("lat", y));
                    list.add(new BasicNameValuePair("method", method));
                }
                request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));

                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() > 0){
                        s = temp.trim().toString();
                    }else{
                        s = "error response data length";
                    }
                }else{
                    s = "error response code:"+response.getStatusLine().getStatusCode();
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /*
     * 发表说说内容(附带位置信息)
     */
    public String getSayPost(String url,String x,String y,String uid,String content,String imgid,List data,String method) {
        //Log.v("map2 ====", data.size()+"");
        String s = new String();
        HttpPost request = null;
        HttpResponse response = null;
        List<NameValuePair> list = null;
        try {
            if (url != null) {

                request = new HttpPost(url);
                if(data != null){
                    list = putParam(data);
                }else if(x != null && y !=null ){

                    list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("uid", uid));
                    list.add(new BasicNameValuePair("content", content));
                    list.add(new BasicNameValuePair("imageId", imgid));
                    list.add(new BasicNameValuePair("lng", x));
                    list.add(new BasicNameValuePair("lat", y));
                    list.add(new BasicNameValuePair("method", method));
                }
                request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));

                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() > 0){
                        s = temp.trim().toString();
                    }else{
                        s = "error response data length";
                    }
                }else{
                    s = "error response code:"+response.getStatusLine().getStatusCode();
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /*
     *提交评论内容
     *
     */
    public String getReplyPost(String url,String uid,String sid,String content,List data,String method) {
        //Log.v("map2 ====", data.size()+"");
        String s = new String();
        HttpPost request = null;
        HttpResponse response = null;
        List<NameValuePair> list = null;
        try {
            if (url != null) {

                request = new HttpPost(url);
                if(data != null){
                    list = putParam(data);
                }else if(uid != null && sid !=null ){

                    list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("uid", uid));
                    list.add(new BasicNameValuePair("content", content));
                    list.add(new BasicNameValuePair("sid", sid));
                    list.add(new BasicNameValuePair("method", method));
                }
                request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));

                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() > 0){
                        s = temp.trim().toString();
                    }else{
                        s = "error response data length";
                    }
                }else{
                    s = "error response code:"+response.getStatusLine().getStatusCode();
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /*
     *
     * 获取评论详情页面（主页面点击说说板块之后调用showStatus()）
     *
     */
    public String getDetailPost(String url,String sid,List data,String method) {
        //Log.v("map2 ====", data.size()+"");
        String s = new String();
        HttpPost request = null;
        HttpResponse response = null;
        List<NameValuePair> list = null;
        try {
            if (url != null) {

                request = new HttpPost(url);
                if(data != null){
                    list = putParam(data);
                }else if(sid != null  ){

                    list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("sid", sid));
                    list.add(new BasicNameValuePair("method", method));
                }
                request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));

                response = new DefaultHttpClient().execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    String temp = EntityUtils.toString(response.getEntity());
                    if(temp.length() > 0){
                        s = temp.trim().toString();
                    }else{
                        s = "error response data length";
                    }
                }else{
                    s = "error response code:"+response.getStatusLine().getStatusCode();
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 取得图片
     * @param url
     * @param iv
     */
    public void getConntionImage(String url,ImageView iv){
        URL imageUrl = null;
        HttpURLConnection conn = null;
        try {
            if(url != null){
                imageUrl = new URL(url);
                conn = (HttpURLConnection) imageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream in = conn.getInputStream();
                Bitmap bp = BitmapFactory.decodeStream(in);
                if(iv != null){
                    iv.setImageBitmap(bp);
                }
            }else{
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}