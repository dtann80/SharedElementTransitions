package com.dantann.recylerviewtemplate.framework;

import timber.log.Timber;

/**
 * Subclass of {@link Timber.DebugTree} that adds the method name to the log tag, so the tag would be the name of the calling class
 * followed by the calling method. This class logs all logs to the console and logcat.
 */
public class DebugTree extends Timber.DebugTree {

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return super.createStackElementTag(element) + "." + element.getMethodName();
    }
}
