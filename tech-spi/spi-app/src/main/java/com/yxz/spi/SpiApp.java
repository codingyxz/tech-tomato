package com.yxz.spi;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class SpiApp {
    public static void main(String[] args) {

        ServiceLoader<ColorService> load = ServiceLoader.load(ColorService.class);

        for (ColorService service : load) {
            System.out.println(service + ":" + service.printColor());
        }

    }
}
