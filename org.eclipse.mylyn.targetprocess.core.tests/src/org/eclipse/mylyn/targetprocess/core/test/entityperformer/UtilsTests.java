package org.eclipse.mylyn.targetprocess.core.test.entityperformer;

import junit.framework.Assert;

import org.eclipse.mylyn.targetprocess.core.TargetProcessAttributeMapper;
import org.eclipse.mylyn.targetprocess.core.entityperformer.PerformerBase;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.junit.Test;

public class UtilsTests {

	@Test
	public void willCorrectlyAddRepositoryUrlToImagesAndRemoveIt() {

		String description = "<div>"
				+ "<span >"
				+ "&#126;&#47;upload&#47;images&#47;IMG_6519.JPG"
				+ "<img height=\"1200\" src=\"&#126;&#47;upload&#47;images&#47;IMG_6522.JPG\" width=\"1600\" />dfgdsfg sdfgsdfgdfg<br /> "
				+ "<img height=\"1200\" src='&#126;&#47;upload&#47;images&#47;IMG_6519.JPG' width=\"1600\" /></span></div>"
				+ "<img height=\"1200\" src=\"&#126;&#47;upload&#47;images&#47;IMG_6519.JPG\" width=\"1600\" /></span></div>";

		String descriptionWithGoodImageUrl = "<div>"
				+ "<span >"
				+ "&#126;&#47;upload&#47;images&#47;IMG_6519.JPG"
				+ "<img height=\"1200\" src=\"http://test_host/tp/upload&#47;images&#47;IMG_6522.JPG\" width=\"1600\" />dfgdsfg sdfgsdfgdfg<br /> "
				+ "<img height=\"1200\" src='http://test_host/tp/upload&#47;images&#47;IMG_6519.JPG' width=\"1600\" /></span></div>"
				+ "<img height=\"1200\" src=\"http://test_host/tp/upload&#47;images&#47;IMG_6519.JPG\" width=\"1600\" /></span></div>";

		String userStoryDescription = "user story ghdfhfghfgh<img alt=\"\" height=\"1200\" src=\"~/upload/images/IMG_6522.JPG\" width=\"1600\" />";
		
		TaskData taskData = new TaskData(
				new TargetProcessAttributeMapper(new TaskRepository("", "http://test_host/tp")), "",
				"http://test_host/tp", "");

		String result = PerformerBase.addRepositoryUrlToImg(taskData, description);
		
		Assert.assertEquals(descriptionWithGoodImageUrl, result);
		
		Assert.assertEquals("user story ghdfhfghfgh<img alt=\"\" height=\"1200\" src=\"http://test_host/tp/upload/images/IMG_6522.JPG\" width=\"1600\" />",
				PerformerBase.addRepositoryUrlToImg(taskData,userStoryDescription));
		
		result = PerformerBase.removeRepositoryUrlFromImg(taskData, descriptionWithGoodImageUrl);
		Assert.assertEquals(description, result);
	}

	@Test
	public void willCorrectlyRemoveHtml() {
		String html = "1<bR>2<Br>3<BR>";
		String result = PerformerBase.removeHTML(html);
		Assert.assertEquals("1\n2\n3\n", result);
	}

	@Test
	public void willCorrectrlyRemoveHtmlWithBulletAndNumbered() {
		String html = "<ol>\n" + 
						"<li>12324\n" + 
						"</li><li>5567\n" + 
						"</li><li>6667</li></ol>\n" + 
						"<ul>\n"+ 
						"<li>1</li>\n" + 
						"<li></li>\n" + 
						"<li></li>\n" + 
						"<li></li>\n"+ 
						"<li><br>2<br>34<br><br><br></li></ul>";
 
		String result = PerformerBase.removeHTML(html);
		Assert.assertEquals("12324\n5567\n6667\n1\n\n\n\n\n2\n34\n\n\n\n", result);
	}
}
