package com.aixuexi.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DownloadSettingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox cb5;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField url5;
    private JTextField textField5;
    private JTextArea textAreaCurrentVersion;
    private JTextField url1;
    private JTextField path1;
    private JTextField url2;
    private JTextField url3;
    private JTextField url4;
    private JTextField path2;
    private JTextField path3;
    private JTextField path4;
    private JTextField path5;
    private JCheckBox cb1;
    private JCheckBox cb2;
    private JCheckBox cb3;
    private JCheckBox cb4;
    private JTextPane TextPane;
    private JTextPane TextPane1;
    private JButton buttonSync;
    private JButton templateButton;
    ArrayList<JTextField> jTextFieldsUrl = new ArrayList<>();
    ArrayList<JTextField> jTextFieldsPath = new ArrayList<>();
    ArrayList<JCheckBox> jCheckBoxes = new ArrayList<>();

    public DownloadSettingDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        jTextFieldsUrl.add(url1);
        jTextFieldsUrl.add(url2);
        jTextFieldsUrl.add(url3);
        jTextFieldsUrl.add(url4);
        jTextFieldsUrl.add(url5);

        jTextFieldsPath.add(path1);
        jTextFieldsPath.add(path2);
        jTextFieldsPath.add(path3);
        jTextFieldsPath.add(path4);
        jTextFieldsPath.add(path5);

        jCheckBoxes.add(cb1);
        jCheckBoxes.add(cb2);
        jCheckBoxes.add(cb3);
        jCheckBoxes.add(cb4);
        jCheckBoxes.add(cb5);
        String setting = Properties.instance().pjLevel().getValue("SETTING");
        Gson gson = new Gson();

        if (setting == null) {
            setting = getDefaultSetingString();

        }
        ArrayList<ResInfo> infos = null;
        try {
            infos = gson.fromJson(setting, new TypeToken<ArrayList<ResInfo>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            setting = getDefaultSetingString();
            infos = gson.fromJson(setting, new TypeToken<ArrayList<ResInfo>>() {
            }.getType());
        }
        System.out.println("===" + setting);
        for (int i = 0; i < infos.size(); i++) {
            ResInfo resInfo = infos.get(i);
            jTextFieldsUrl.get(i).setText(resInfo.resUrl);
            jTextFieldsPath.get(i).setText(resInfo.resPath);
        }
        textAreaCurrentVersion.setText("当前版本：\r\n" + PROD.getVersion(Global.instance().project(), infos));
        addListener();
    }

    @NotNull
    private String getDefaultSetingString() {
        String setting;
        setting = "[\n" +
                "  {\n" +
                "    \"resUrl\": \"http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/react.zip\",\n" +
                "    \"resPath\": \"/app/src/main/assets/react.zip\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"resUrl\": \"http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/prod.zip\",\n" +
                "    \"resPath\": \"/app/src/main/assets/prod.zip\"\n" +
                "  }\n" +
                "]";
        return setting;
    }

    private void addListener() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        buttonSync.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSync();
            }
        });
        templateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gson gson = new Gson();
                String setting = "[\n" +
                        "  {\n" +
                        "    \"resUrl\": \"http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/react.zip\",\n" +
                        "    \"resPath\": \"/app/src/main/assets/react.zip\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"resUrl\": \"http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/prod.zip\",\n" +
                        "    \"resPath\": \"/app/src/main/assets/prod.zip\"\n" +
                        "  }\n" +
                        "]";
                ArrayList<ResInfo> infos = gson.fromJson(setting, new TypeToken<ArrayList<ResInfo>>() {
                }.getType());
                System.out.println("===" + setting);
                for (int i = 0; i < infos.size(); i++) {
                    ResInfo resInfo = infos.get(i);
                    jTextFieldsUrl.get(i).setText(resInfo.resUrl);
                    jTextFieldsPath.get(i).setText(resInfo.resPath);
                }
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        JsonArray jsonElements = new JsonArray();
        // add your code here
        for (int i = 0; i < jTextFieldsPath.size(); i++) {
            JsonObject jsonObject = new JsonObject();
            String resPath = jTextFieldsPath.get(i).getText();
            String resUrl = jTextFieldsUrl.get(i).getText();
            jsonObject.addProperty("resPath", resPath);
            jsonObject.addProperty("resUrl", resUrl);
            jsonElements.add(jsonObject);
        }

        String s1 = jsonElements.toString();
        System.out.println("jsonElements==" + s1);
        Properties.instance().pjLevel().setValue("SETTING", s1);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onSync() {
        ApplicationManager.getApplication().runReadAction(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < jTextFieldsPath.size(); i++) {
                    JCheckBox jCheckBox = jCheckBoxes.get(i);
                    if(jCheckBox.isSelected()){//打钩资源才下载
                        String resPath = jTextFieldsPath.get(i).getText();
                        String resUrl = jTextFieldsUrl.get(i).getText();
                        ResInfo resInfo = new ResInfo();
                        resInfo.resUrl = resUrl;
                        resInfo.resPath = resPath;
                        PROD.downloadRes(resInfo);
                        ApplicationManager.getApplication().invokeLater(() -> {
                                    // Messages.showMessageDialog("Module !"+versionName, "Module"+versionName, Messages.getInformationIcon());

                                    textAreaCurrentVersion.setText("当前版本：\r\n" + PROD.getSB());
                                }
                        );
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        DownloadSettingDialog dialog = new DownloadSettingDialog();
        dialog.pack();
        dialog.setVisible(true);
//        System.exit(0);
    }


}
