package cn.ep.spring.boot.ch04.s10;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ConditionalOnClass(JSON.class)
public class FastJsonConfiguration implements WebMvcConfigurer {

    /**
     * 修改自定义消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1.创建一个convert消息转换对象
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        //2.创建一个fastJson的配置对象,然后配置格式化信息
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3.在convert中添加配置信息
        fastConvert.setFastJsonConfig(config);
        //4.将convert添加到converts里面
        converters.add(fastConvert);
    }

}

