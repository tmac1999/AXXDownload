package com.aixuexi.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.ArrayList;

public class DownloadResource extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
//        Messages.showMessageDialog(project, "say hello world ~", "Info", Messages.getInformationIcon());

//        Messages.showMessageDialog(project, "say hello world ~", "Info", Messages.getInformationIcon());

        String setting = "";
        if (project!=null){
            Global.instance().bindProject(project);


            ArrayList<ResInfo> resInfos = new ArrayList<>();
//            printBeforeDownloadVersion(project,resInfos);

//            String basePath = project.getBasePath();
//            UrlHttpUtil.downLoad(prodURL, basePath +pathProd);
//            UrlHttpUtil.downLoad(reactURL, basePath +pathReact);
//            printAfterDownloadVersion(project);
//            Messages.showMessageDialog(project, sb.toString(), "Info", Messages.getInformationIcon());
//
        }
        PROD.newSB();
        DownloadSettingDialog.main(null);
    }
}
