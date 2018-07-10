package cn.ep.spring.boot.ch03.s07;

import com.mongodb.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.*;

@Configuration
public class MongoConfig {

    @Bean
    @ConfigurationProperties(
            prefix = "spring.data.mongodb.custom")
    MongoSettingsProperties mongoSettingsProperties() {
        return new MongoSettingsProperties();
    }

    // 覆盖默认的MongoDbFactory
    @Bean
    MongoDbFactory mongoDbFactory(MongoSettingsProperties mongoSettingsProperties) {
        //客户端配置（连接数、副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoSettingsProperties.getConnectionsPerHost());
        builder.minConnectionsPerHost(mongoSettingsProperties.getMinConnectionsPerHost());
        if (mongoSettingsProperties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(mongoSettingsProperties.getReplicaSet());
        }
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String host : mongoSettingsProperties.getHosts()) {
            Integer index = mongoSettingsProperties.getHosts().indexOf(host);
            Integer port = mongoSettingsProperties.getPorts().get(index);

            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }

        // 连接认证
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
                mongoSettingsProperties.getUsername(),
                mongoSettingsProperties.getAuthenticationDatabase() != null ? mongoSettingsProperties.getAuthenticationDatabase() : mongoSettingsProperties.getDatabase(),
                mongoSettingsProperties.getPassword().toCharArray());

        //创建客户端和Factory
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, mongoClientOptions);
        return new SimpleMongoDbFactory(mongoClient, mongoSettingsProperties.getDatabase());
    }
}
