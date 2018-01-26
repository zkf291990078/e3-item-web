package cn.e3mall.item.test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ActiveMqTest {

	@Test 
	public void genHtml() throws Exception {
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration
				.setDirectoryForTemplateLoading(new File("C:/Users/john/git/e3-item-web/src/main/webapp/WEB-INF/ftl"));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("hello.ftl");
         Map data=new HashMap<>();
     	data.put("hello", "this is my first freemarker test.");
     	Writer out=new FileWriter(new File("D:/temp/term197/out/hello.html"));
     	template.process(data, out);
     	out.close();
	}

}
