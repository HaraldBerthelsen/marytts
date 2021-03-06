package marytts.language.ar;

import marytts.language.ar.Preprocess;
import marytts.util.dom.DomUtils;

import org.custommonkey.xmlunit.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author HB
 *
 *
 */
public class PreprocessTest {

	private static Preprocess module;

	@BeforeSuite
	public static void setUpBeforeClass() {
		module = new Preprocess();
	}

	@DataProvider(name = "DocData")
	private Object[][] numberExpansionDocData() {
		// @formatter:off
		return new Object[][] { { "1", "واحد" }, 
					{ "2", "إثنان" } };
		// @formatter:on
	}

	@DataProvider(name = "NumExpandData")
	private Object[][] numberExpansionDocDataCardinal() {
		// @formatter:off
		return new Object[][] { { "1", "واحد" }, 
					{ "2", "إثنان" }};
		// @formatter:on
	}

	// @DataProvider(name = "OrdinalExpandData")
	// private Object[][] numberExpansionDocDataOrdinal() {
	// 	// @formatter:off
	// 	return new Object[][] { { "2", "الثاني" },
	// 				{ "3", "الثالث" },
	// 				{ "4", "الرابع" } };
	// 	// @formatter:on
	// }

	@DataProvider(name = "FullDocTestData")
	private Object[][] numberExpansionFullDocTestData() {
		// @formatter:off
		return new Object[][] { { "1", "واحد" }, 
					{ "2", "إثنان" } };
		// @formatter:on
	}

	@Test(dataProvider = "FullDocTestData")
	public void testSpellout(String tokenised, String expected) throws Exception, ParserConfigurationException, SAXException,
			IOException {
		Document tokenisedDoc;
		Document expectedDoc;
		String tokens = "<maryxml xmlns=\"http://mary.dfki.de/2002/MaryXML\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"0.5\" xml:lang=\"ar\"><p><s><t>"
				+ tokenised + "</t></s></p></maryxml>";
		tokenisedDoc = DomUtils.parseDocument(tokens);
		String words = "<maryxml xmlns=\"http://mary.dfki.de/2002/MaryXML\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"0.5\" xml:lang=\"ar\"><p><s><mtu accent=\"last\" orig=\""
				+ tokenised + "\"><t>" + expected + "</t></mtu></s></p></maryxml>";
		expectedDoc = DomUtils.parseDocument(words);
		module.checkForNumbers(tokenisedDoc);
		Diff diff = XMLUnit.compareXML(expectedDoc, tokenisedDoc);

		//HB
		if (diff.identical() == false) {
		    System.err.println("tokenised: "+tokenised+", expected: "+expected);
		    System.err.println("tokenisedDoc: "+DomUtils.document2String(tokenisedDoc));
		    System.err.println("expectedDoc: "+DomUtils.document2String(expectedDoc));
		    System.err.println("diff.identical(): "+diff.identical());
		}

		Assert.assertTrue(diff.identical());
	}

	@Test(dataProvider = "NumExpandData")
	public void testExpandNum(String token, String word) {
		double x = Double.parseDouble(token);
		String actual = module.expandNumber(x);
		Assert.assertEquals(actual, word);
	}

	// @Test(dataProvider = "OrdinalExpandData")
	// public void testExpandOrdinal(String token, String word) {
	// 	double x = Double.parseDouble(token);
	// 	String actual = module.expandOrdinal(x);
	// 	Assert.assertEquals(actual, word);
	// }


	@DataProvider(name = "VocaliseData")
	private Object[][] vocalisationData() {
		// @formatter:off
	    return new Object[][] { 
		//With case ending
		//{ "المملكة", "الْمَمْلَكَةُ" }
		//Without case ending
		{ "المملكة", "الْمَمْلَكَة" }
		//, { "المغربية", "الْمَغْرِبِيَّة"}
	    };
		// @formatter:on
	}
	@Test(dataProvider = "VocaliseData")
	public void testVocalise(String token, String word) throws Exception {
            try {
                String actual = module.vocaliseText(token).trim();
                Assert.assertEquals(actual, word);
            } catch (java.net.ConnectException e) {
                System.out.println("Mishkal server not running - can't test vocalisation");
            }
	}

    //   ٌٍِ][ﻷضَصثقفًٌٌَُ      َ    َ
}
