package com.mm.kit.common.http.logging;


import com.mm.kit.common.log.VLog;

import okhttp3.internal.platform.Platform;

/**
 * Xlog impl
 */
public class X implements Logger {


    @Override
    public void log(int level, String tag, String msg) {
        final VLog.Logger log = VLog.scoped("http:" + tag);
        switch (level) {
            case Platform.INFO:
                log.i(msg);
                break;
            default:
                log.w(msg);
                break;
        }
    }

}
