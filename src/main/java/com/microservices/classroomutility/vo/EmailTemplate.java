package com.microservices.classroomutility.vo;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;

public class EmailTemplate {

    private String template;
    private Map<String, String> replacementParams;

    public EmailTemplate(String customtemplate) {
        try {
            this.template = loadTemplate(customtemplate);
            System.out.println(template);
        } catch (Exception e) {
            this.template = "Empty";
        }
    }

    private String loadTemplate(String customtemplate) throws Exception {
        //ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = getClass().getClassLoader().getResource(customtemplate);
        File file = new File(fileUrl.toURI());
        String content = "Empty";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
            System.out.println(content);
        } catch (IOException e) {
            throw new Exception("Could not read template  = " + customtemplate);
        }
        return content;
    }

    public String getTemplate(Map<String, String> replacements) {
        String cTemplate = this.template;
        //Replace the String
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return cTemplate;
    }
}
