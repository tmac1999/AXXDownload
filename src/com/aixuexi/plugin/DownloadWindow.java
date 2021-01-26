package com.aixuexi.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DownloadWindow implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        Global.instance().bindProject(project);


//            printBeforeDownloadVersion(project,resInfos);

//            String basePath = project.getBasePath();
//            UrlHttpUtil.downLoad(prodURL, basePath +pathProd);
//            UrlHttpUtil.downLoad(reactURL, basePath +pathReact);
//            printAfterDownloadVersion(project);
//            Messages.showMessageDialog(project, sb.toString(), "Info", Messages.getInformationIcon());
//

        DownloadSettingDialog.main(null);
    }

    @Override
    public void init(ToolWindow window) {

    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return false;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return false;
    }
}
