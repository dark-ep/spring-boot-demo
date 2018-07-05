package cn.ep.spring.boot.ch03.s05.dynamic;

import org.slf4j.*;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
