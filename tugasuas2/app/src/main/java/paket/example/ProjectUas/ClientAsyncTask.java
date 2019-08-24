package paket.example.ProjectUas;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ClientAsyncTask extends AsyncTask<Void, Void, String> {
    private final int CONNECTION_TIMEOUT_MILLISECONDS = 60000;
    private OnPostExecuteListener mPostExecuteListener = null;
    private Context mContext;
    private  String base_api_url = "https://api.banghasan.com/sholat/format/json/";

    public  String api = "";

    public static interface OnPostExecuteListener{
        void  onPostExecute(String result);
    }

    public ClientAsyncTask(Context mContext, String api_url, OnPostExecuteListener mPostExecuteListener){
        this.mPostExecuteListener = mPostExecuteListener;
        this.mContext = mContext;
        this.api = api_url;
    }


    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if (mPostExecuteListener != null){
            mPostExecuteListener.onPostExecute(result);
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        String api_url = base_api_url + api;
        HttpsURLConnection urlConnection = null;
        try {
            URL url = new URL(api_url);
            urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT_MILLISECONDS);
            urlConnection.setReadTimeout(CONNECTION_TIMEOUT_MILLISECONDS);

            String inString = stremToString(urlConnection.getInputStream());
            return inString;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return null;
    }
    private String stremToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line, result = "";
        try {
            do {
                line = bufferedReader.readLine();
                if (line != null){
                    result += line;
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
