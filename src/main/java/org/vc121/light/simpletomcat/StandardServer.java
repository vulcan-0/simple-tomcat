package org.vc121.light.simpletomcat;

import org.vc121.light.simpletomcat.container.lifecycle.LifecycleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luxiaocong
 * @createdOn 2020/12/1
 */
public class StandardServer implements Server {

    private int port;
    private List<Service> services = new ArrayList<Service>();

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public Service[] findServices() {
        return services.toArray(new Service[services.size()]);
    }

    public void start() {
        for (Service service : services) {
            service.start();
        }
        Runtime.getRuntime().addShutdownHook(new ServerShutdownHook(this));
    }

    public void stop() {
        for (Service service : services) {
            service.stop();
        }
    }

    public void addLifecycleListener(LifecycleListener lifecycleListener) {

    }

    public void removeLifecycleListener(LifecycleListener lifecycleListener) {

    }

    private class ServerShutdownHook extends Thread {

        private Server server;

        public ServerShutdownHook(Server server) {
            this.server = server;
        }

        @Override
        public void run() {
            server.stop();
        }

    }

}
