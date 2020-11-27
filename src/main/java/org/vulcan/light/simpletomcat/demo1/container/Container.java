package org.vulcan.light.simpletomcat.demo1.container;

/**
 * @author luxiaocong
 * @createdOn 2020/11/7
 */
public interface Container {

    void addChild(Container container);

    void removeChild(Container container);

    Container findChild(String name);

    Container[] findChildren();

}
