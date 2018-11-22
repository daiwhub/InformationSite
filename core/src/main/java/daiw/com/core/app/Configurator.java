package daiw.com.core.app;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2018/11/13  4:47 PM
 *
 *         ***************************
 */
public class Configurator {

    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();
    private static final HashMap<String, String> HEADERKEYS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     *
     */
    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
        if(! HEADERKEYS.isEmpty()){
            HEADERKEYS.clear();
        }
    }

    /**
     * 获取配置信息
     */
    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    final <T> T getConfiguration(Object key){
        final Object value = CONFIGS.get(key);
        if(value == null){
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) CONFIGS.get(key);
    }

    /**
     *配置APIHOST
     */
    public final Configurator withApiHost(String host){
        CONFIGS.put(ConfigKeys.API_HOST.name(),host);
        return this;
    }

    /**
     *检查配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if(! isReady){
           throw  new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    /**
     * 获取配置信息
     */
    public final HashMap<String, String> getHeaderkeys() {
        return HEADERKEYS;
    }

    /**
     *为不同的BaseUrl配置headervalue识别
     */
    public final Configurator withHeaderValues(String headerKey,String headerValues){
        HEADERKEYS.put(headerKey,headerValues);
        return this;
    }

    public final void configure(){
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }
}
