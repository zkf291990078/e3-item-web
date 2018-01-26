package cn.e3mall.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class ItemListener implements MessageListener {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Autowired
	private ItemService itemService;

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		// 3、使用Configuration对象获得Template对象。
		Configuration freemarkerConfig=freeMarkerConfigurer.getConfiguration();
		TextMessage text=(TextMessage) message;
		try {
			long itemId= Long.parseLong(text.getText());
			Template template = freemarkerConfig.getTemplate("item.ftl");
			// 4、创建数据集
			Map dataModel = new HashMap<>();
		TbItem tbItem=	itemService.getItemById(itemId);
		Item item=new Item(tbItem);	
		dataModel.put("item", item);
		TbItemDesc itemDesc=itemService.getItemDescById(itemId);
		dataModel.put("itemDesc", itemDesc);
			// 5、创建输出文件的Writer对象。
			Writer out = new FileWriter(new File("D:/temp/item/"+itemId+".html"));
			// 6、调用模板对象的process方法，生成文件。
			template.process(dataModel, out);
			// 7、关闭流。
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
