package cn.ep.spring.boot.ch03.s05.dynamic;

import java.util.*;

public class DynamicDataSourceContextHolder {

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.master::name);

    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * The constant slaveDataSourceKeys.
     */
    public static List<Object> slaveDataSourceKeys = new ArrayList<>();

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return String.valueOf(CONTEXT_HOLDER.get());
    }

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * Check if give DataSource is in current DataSource list
     *
     * @param key the key
     * @return boolean boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
