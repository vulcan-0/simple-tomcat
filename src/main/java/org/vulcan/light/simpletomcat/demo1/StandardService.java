package org.vulcan.light.simpletomcat.demo1;

import org.vulcan.light.simpletomcat.demo1.connector.Connector;
import org.vulcan.light.simpletomcat.demo1.container.core.Container;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.Lifecycle;
import org.vulcan.light.simpletomcat.demo1.container.lifecycle.LifecycleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/12/1
 */
public class StandardService implements Service {

    private Server server;
    private Container container;
    private List<Connector> connectors = new ArrayList<Connector>();

    public void setServer(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return this.server;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return this.container;
    }

    public void addConnector(Connector connector) {
        connectors.add(connector);
    }

    public void removeConnector(Connector connector) {
        connectors.remove(connector);
    }

    public Connector[] findConnectors() {
        return connectors.toArray(new Connector[connectors.size()]);
    }

    public void start() {
        container.start();
        for (Connector connector : connectors) {
            if (connector instanceof Lifecycle) {
                ((Lifecycle) connector).start();
            }
        }
    }

    public void stop() {
        for (Connector connector : connectors) {
            if (connector instanceof Lifecycle) {
                ((Lifecycle) connector).stop();
            }
        }
        container.stop();
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

    }

}
