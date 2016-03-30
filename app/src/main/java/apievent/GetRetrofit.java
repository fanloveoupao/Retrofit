package apievent;

import java.lang.annotation.Target;
import java.util.Map;

import model.GetModel;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;


/**
 * Created by bruse on 16/3/30.
 * <p>
 * Retrofit是通过注解把HTTP转化成java接口
 */
public interface GetRetrofit {
    //https://api.github.com/users/fanloveoupao
    //进行拼接型的get请求
    @GET("/users/{user}")
    Call<GetModel> getFreed(@Path("user") String user);

    /**
     * 这里我们使用@GET注解进行get请求,@GET("str");表示这里的
     * 请求地址是baseurl+str,{user}这里的user将会被方法getFreed里面的string所替代最后拼接成
     * 最终的请求路径
     */
    @GET("users/{user}")
    public Call<GetModel> getSingle(@Path("user") String user);

    //请求的参数过长时,只有一个键值对时
    @GET("/home")
    public Call<GetModel> getLong(@Query("t") String value);
    //这里拼接出的url就是https://api.github.com/users/home?t=value


    //更长时可以考虑
    @GET("/home")
    public Call<GetModel> getMany(@QueryMap Map<String, String> optiom);

    /**
     * 多个拼接，拥有多个键值对时
     * https://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=1212120055
     *
     * Map<String,String> a=new HashMap<>()
     *   a.put("t","home/index");
     *    a.put("lang","zh_CN");
     *    a.put("oken","1212120055");
     *
     *    多个键值对拼接到路径中
     * */

    //关于post的问题
    /**
     * 请求体
     *
     * 我们知道get和post中的一个区别就是，get把参数放到路径中而post是放进请求体中的
     * 下面是关于post的请求
     * */
    @FormUrlEncoded  //post请求一定要加这个
    @POST("/login")
    public Call<GetModel> login(@Field("username") String user,@Field("password") String pass);

    /**
     * 关于配置body的post中
     *
     * 不常用
     * */
    @FormUrlEncoded
    @POST("/index.php")
    public Call<GetModel> postBody(@Body GetModel getModel);

    /**
     * 关于配置请求头的
     * Http请求是存在请求头的有时我们要填写或者配置，不过一般我们用不到
     * 不过还是简单的做下讲解吧
     *
     * */
    @GET("/user")
    public Call<GetModel> getHeader(@Header("author") String autj);

    /**
     * 常用的注解
     *
     * @GET
     * @POST
     * @Field
     * @Query
     * @QueryMap
     * */
}


