package com.aixuexi.plugin;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class Properties {
    private final PropertiesComponent mProjectLevel;
    private final PropertiesComponent mApplicationLevel;

    Properties(@NotNull Project project) {
        mProjectLevel = PropertiesComponent.getInstance(project);
        mApplicationLevel = PropertiesComponent.getInstance();
    }

    public static Properties instance() {
        return Holder.INSTANCE;
    }

    public PropertiesComponent pjLevel() {
        return mProjectLevel;
    }

    public PropertiesComponent appLevel() {
        return mApplicationLevel;
    }

    static class Holder {
        final static Properties INSTANCE = new Properties(Global.instance().project());
    }
}
