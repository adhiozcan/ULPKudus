package gamatechno.gov.ulpkudus.config;

public class Service_URL {

	static String URL;

	public String get_address(int req_code) {

		switch (req_code) {
		case 0: // Berita
			URL = "http://ulp.kuduskab.go.id/index.php?option=com_gtjson&task=content.loadArticles";
			break;

		case 1: // Lelang
			URL = "http://ulp.kuduskab.go.id/?option=com_gtjson&task=loader.getJSON&request=lelang";
			break;

		case 2: // Regulasi
			URL = "http://ulp.kuduskab.go.id/?option=com_gtjson&task=loader.getJSON&request=lelang&parameter=regulasi";
			break;
		}

		return URL;
	}
}
