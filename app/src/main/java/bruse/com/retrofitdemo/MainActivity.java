package bruse.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import apievent.GetRetrofit;
import model.GetModel;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Call;
//注意导入的包不要错

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.id_get);

    }

    public void getPath(View view) {
        //执行请求
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        GetRetrofit getRetrofit = retrofit.create(GetRetrofit.class);
        Call<GetModel> modelCall = getRetrofit.getSingle("fanloveoupao");
//        Call<GetModel> modelCall = getRetrofit.getFreed("fanloveoupao");
        modelCall.enqueue(new Callback<GetModel>() {
            @Override
            public void onResponse(Response<GetModel> response, Retrofit retrofit) {
                Log.i("TAG", "40行" + response.body().getLogin());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        /**
         * 这里先构建Retrofit的对象注意别把包导错了，因为要把返回的json数据转换成Model对象所以加入了
         *
         * GsonConverterFactort进行过转换
         * 最后利用http中的接口生成Call对象，然后进行调用enqueue队列进行执行
         *
         * */
    }
}
