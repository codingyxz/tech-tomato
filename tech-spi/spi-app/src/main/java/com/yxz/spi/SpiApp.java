package com.yxz.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class SpiApp {
    public static void main(String[] args) {

        ServiceLoader<ColorService> load = ServiceLoader.load(ColorService.class);

        Iterator<ColorService> iterator = load.iterator();

        while (iterator.hasNext()) {
            ColorService service = iterator.next();
            System.out.println(service + ":" + service.printColor());
        }

    }
}
