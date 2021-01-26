package com.aixuexi.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class AXXDownload extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getProject();
        if (project != null){
            Global.instance().bindProject(project);
            PROD.newSB();
            DownloadSettingDialog.main(null);
        }
    }
}
