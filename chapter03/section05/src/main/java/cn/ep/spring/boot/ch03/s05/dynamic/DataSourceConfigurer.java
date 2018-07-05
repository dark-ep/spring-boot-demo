package cn.ep.spring.boot.ch03.s05.dynamic;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.*;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@MapperScan("cn.ep.spring.boot.ch03.s05.dao")
public class DataSourceConfigurer {

    public static final String DS_KEY_DEFAULT = DataSourceKey.master.name();

    /**
     * master DataSource
     *
     * @return data source
     */
    @Primary
    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave data source.
     *
     * @return the data source
     */
    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DataSourceRoutingDataSource dynamicRoutingDataSource = new DataSourceRoutingDataSource();
        Map<Object, Object> dataSourceMap = new WeakHashMap<>(2);
        dataSourceMap.put(DataSourceKey.master.name(), master());
        dataSourceMap.put(DataSourceKey.slave.name(), slave());

        // 将 master 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceMap.getOrDefault(DS_KEY_DEFAULT, DataSourceKey.master.name()));
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        // 将 Slave 数据源的 key 放在集合中，用于轮循
        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
        return dynamicRoutingDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return the sql session factory bean
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(MybatisProperties properties,
                                                       ObjectProvider<Interceptor[]> opInterceptorsProvider,
                                                       ResourceLoader resourceLoader,
                                                       ObjectProvider<DatabaseIdProvider> opDatabaseIdProvider,
                                                       ObjectProvider<List<ConfigurationCustomizer>> opConfigurationCustomizersProvider) {
        Interceptor[] interceptors = opInterceptorsProvider.getIfAvailable();
        DatabaseIdProvider databaseIdProvider = opDatabaseIdProvider.getIfAvailable();
        List<ConfigurationCustomizer> configurationCustomizers = opConfigurationCustomizersProvider.getIfAvailable();

        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        factory.setDataSource(dynamicDataSource());
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(properties.getConfigLocation())) {
            factory.setConfigLocation(resourceLoader.getResource(properties.getConfigLocation()));
        }
        org.apache.ibatis.session.Configuration configuration = properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(properties.getConfigLocation())) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : configurationCustomizers) {
                customizer.customize(configuration);
            }
        }
        factory.setConfiguration(configuration);
        if (properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(interceptors)) {
            factory.setPlugins(interceptors);
        }
        factory.setDatabaseIdProvider(databaseIdProvider);
        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
            factory.setMapperLocations(properties.resolveMapperLocations());
        }
        return factory;
    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
