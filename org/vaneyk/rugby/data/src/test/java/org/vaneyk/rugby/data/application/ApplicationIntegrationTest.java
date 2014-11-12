package org.vaneyk.rugby.data.application;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vaneyk.commons.test.junit.category.IntegrationTest;
import org.vaneyk.rugby.data.Application;

@Category( IntegrationTest.class )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = Application.class )
public class ApplicationIntegrationTest
{

	@Test
	public void contextLoads()
	{
	}

}
