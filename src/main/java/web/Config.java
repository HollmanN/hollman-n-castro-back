package web;


import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import service.CubeSummationService;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Config extends ResourceConfig {

    public Config() {
        try {
            packages("web");

            register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(CubeSummationService.class).to(CubeSummationService.class);
                }
            });

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
