package org.iptime.mascore.mimi.etc;

import com.squareup.otto.Bus;

/**
 * Created by Owner on 2017-08-19.
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }

}
