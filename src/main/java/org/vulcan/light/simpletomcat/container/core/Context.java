package org.vulcan.light.simpletomcat.container.core;

import org.vulcan.light.simpletomcat.container.filter.ApplicationFilterConfig;

import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/11/27
 */
public interface Context extends Container {

    /**
     * 添加过滤器
     *
     * @param filterConfig
     */
    void addFilterConfig(ApplicationFilterConfig filterConfig);

    /**
     * 移除过滤器
     *
     * @param filterConfig
     */
    void removeFilterConfig(ApplicationFilterConfig filterConfig);

    /**
     * 获取全部过滤器
     *
     * @return
     */
    List<ApplicationFilterConfig> findFilterConfigs();

}
