package com.dmcelligott.secure.bookmarks;

import java.util.Arrays;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dmcelligott.bookmark.Application;
import com.dmcelligott.bookmark.dto.Bookmark;
import com.dmcelligott.bookmark.dto.Label;
import com.dmcelligott.bookmark.repository.BookmarkRepository;
import com.dmcelligott.bookmark.repository.LabelRepository;
import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationTests {
	
	private static final Label LABEL_NEWS = new Label("news");

	private static final Label LABEL_SEARCH = new Label("search");

	private static final Label LABEL_TUTORIAL = new Label("tutorial");

	@Autowired
	BookmarkRepository bookmarkRepository;
	
	@Autowired
	LabelRepository LabelRepository;
	
	@Value("${local.server.port}")
    int port;
	
	private Bookmark google;
	private Bookmark msn;
	private Bookmark howToGeek;

	@Before
	public void setUp() {
		google = new Bookmark();
		google.setTitle("Google");
		google.setUrl("http://www.google.com");
		google.setLabels(Arrays.asList(LABEL_SEARCH));
		google.setUserId(12345L);
		google.setCreatedDate(new Date());
		
		howToGeek = new Bookmark();
		howToGeek.setTitle("How To Geek");
		howToGeek.setUrl("http://www.howtogeek.com");
		howToGeek.setLabels(Arrays.asList(LABEL_TUTORIAL));
		howToGeek.setUserId(12345L);
		howToGeek.setCreatedDate(new Date());
		
		msn = new Bookmark();
		msn.setTitle("MSN");
		msn.setUrl("http://www.msn.com");
		msn.setLabels(Arrays.asList(LABEL_NEWS));
		msn.setUserId(54321L);
		msn.setCreatedDate(new Date());
		
		LabelRepository.save(Arrays.asList(LABEL_SEARCH, LABEL_NEWS, LABEL_TUTORIAL));
		
		bookmarkRepository.deleteAll();
		bookmarkRepository.save(Arrays.asList(google, msn, howToGeek));
		
		RestAssured.port = port;
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void canGetAllForUser() throws Exception {
		RestAssured.when()
			.get("/bookmark/user/12345")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("title", Matchers.hasItems("Google", "How To Geek"))
			.body("title", Matchers.not(Matchers.hasItem("MSN")));
	}

}
