package org.vulcan.light.simpletomcat.container.filter;

import org.vulcan.light.simpletomcat.container.core.Context;

import javax.servlet.Filter;

/**
 * @author luxiaocong
 * @createdOn 2020/12/3
 */
public class ApplicationFilterConfig {

    private Context context;
    private FilterDef filterDef;

    public ApplicationFilterConfig(Context context, FilterDef filterDef) {
        this.context = context;
        this.filterDef = filterDef;
    }

    public Filter getFilter() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (this.filterDef != null) {
            Class clazz = this.getClass().getClassLoader().loadClass(filterDef.getFilterClass());
            return (Filter) clazz.newInstance();
        }
        return null;
    }

}
