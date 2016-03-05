package tr.edu.metu.WikiKnowledgeExtractor;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;

import tr.edu.metu.WikiKnowledgeExtractor.elasticsearch.ElasticUtil;
import tr.edu.metu.WikiKnowledgeExtractor.entityextractor.DandelionUtil;
import tr.edu.metu.WikiKnowledgeExtractor.entityextractor.WikiText;
import tr.edu.metu.WikiKnowledgeExtractor.wikiminer.WikiGraphUtil;
import tr.edu.metu.WikiKnowledgeExtractor.wikiminer.WikiGraphUtil.WikiLabel;

/**
 * Hello world!
 *
 */

public class GraphCreator {
	private static final int MAX_PATH_LENGTH = 3;
	private static final int MAX_PROJECT_SIZE = 50;
	static boolean isSampleTrial = false;
	static String jampleJson = "{\"time\":85,\"annotations\":[{\"start\":0,\"end\":2,\"spot\":\"EU\",\"confidence\":0.7473,\"id\":9317,\"title\":\"European Union\",\"uri\":\"http://en.wikipedia.org/wiki/European_Union\",\"label\":\"European Union\",\"categories\":[\"1993 establishments\",\"European Union\",\"Federalism\",\"G20 nations\",\"International organizations of Europe\",\"Organizations awarded Nobel Peace Prizes\",\"Political systems\",\"United Nations General Assembly special observers\"],\"types\":[\"http://dbpedia.org/ontology/Place\",\"http://dbpedia.org/ontology/PopulatedPlace\",\"http://dbpedia.org/ontology/Country\"],\"alternateLabels\":[\"European Union\",\"EU\",\"European\",\"Europe\",\"European Union's\"]},{\"start\":3,\"end\":11,\"spot\":\"research\",\"confidence\":0.6807,\"id\":25524,\"title\":\"Research\",\"uri\":\"http://en.wikipedia.org/wiki/Research\",\"label\":\"Research\",\"categories\":[\"Research\",\"Research methods\"],\"types\":[],\"alternateLabels\":[\"Research\",\"Researcher\",\"Researchers\",\"Original research\",\"Study\"]},{\"start\":56,\"end\":59,\"spot\":\"ICT\",\"confidence\":0.7618,\"id\":1197962,\"title\":\"Information and communications technology\",\"uri\":\"http://en.wikipedia.org/wiki/Information_and_communications_technology\",\"label\":\"ICT\",\"categories\":[\"Communication\",\"Information technology\"],\"types\":[],\"alternateLabels\":[\"ICT\",\"Information and communications technology\",\"Information and Communication Technology\",\"Information and communication technologies\",\"Communication technology\"]},{\"start\":64,\"end\":72,\"spot\":\"critical\",\"confidence\":0.684,\"id\":42736966,\"title\":\"Critical theory\",\"uri\":\"http://en.wikipedia.org/wiki/Critical_theory\",\"label\":\"Critical theory\",\"categories\":[\"Critical theory\",\"Postmodernism\",\"Sociology index\"],\"types\":[],\"alternateLabels\":[\"Critical theory\",\"Critical theorist\",\"Critical\",\"Critical theorists\",\"Critical Sociology\"]},{\"start\":76,\"end\":82,\"spot\":\"Europe\",\"confidence\":0.7032,\"id\":9239,\"title\":\"Europe\",\"uri\":\"http://en.wikipedia.org/wiki/Europe\",\"label\":\"Europe\",\"categories\":[\"Europe\",\"Article Feedback 5 Additional Articles\",\"Continents\",\"Cultural concepts\"],\"types\":[\"http://dbpedia.org/ontology/Place\",\"http://dbpedia.org/ontology/PopulatedPlace\",\"http://dbpedia.org/ontology/Continent\"],\"alternateLabels\":[\"Europe\",\"European\",\"Continent\",\"Europe's\",\"Europea\"]},{\"start\":144,\"end\":156,\"spot\":\"productivity\",\"confidence\":0.7172,\"id\":424899,\"title\":\"Productivity\",\"uri\":\"http://en.wikipedia.org/wiki/Productivity\",\"label\":\"Productivity\",\"categories\":[\"Production and manufacturing\",\"Production economics\",\"Manufacturing\",\"Industry\",\"Economic growth\"],\"types\":[],\"alternateLabels\":[\"Productivity\",\"Productive\",\"Economic productivity\",\"Productivity growth\",\"Worker productivity\"]},{\"start\":161,\"end\":171,\"spot\":\"innovation\",\"confidence\":0.7375,\"id\":118450,\"title\":\"Innovation\",\"uri\":\"http://en.wikipedia.org/wiki/Innovation\",\"label\":\"Innovation\",\"categories\":[\"Innovation economics\",\"Innovation\",\"Design\",\"Science and technology studies\",\"Economics\"],\"types\":[],\"alternateLabels\":[\"Innovation\",\"Innovative\",\"Innovations\",\"Technological innovation\",\"Innovate\"]},{\"start\":177,\"end\":190,\"spot\":\"modernisation\",\"confidence\":0.692,\"id\":1117836,\"title\":\"Modernization theory\",\"uri\":\"http://en.wikipedia.org/wiki/Modernization_theory\",\"label\":\"Modernization\",\"categories\":[\"Development\",\"Sociological theories\",\"Sociocultural evolution\",\"Globalization-related theories\"],\"types\":[],\"alternateLabels\":[\"Modernization\",\"Modernization theory\",\"Modernisation\",\"Modernize\",\"Modernizing\"]},{\"start\":194,\"end\":209,\"spot\":\"public services\",\"confidence\":0.6704,\"id\":1291782,\"title\":\"Public service\",\"uri\":\"http://en.wikipedia.org/wiki/Public_service\",\"label\":\"Public services\",\"categories\":[\"Political economy\",\"Public administration\",\"Public services\"],\"types\":[],\"alternateLabels\":[\"Public service\",\"Public services\",\"Services\",\"Service\",\"Government service\"]},{\"start\":227,\"end\":234,\"spot\":\"science\",\"confidence\":0.7166,\"id\":26700,\"title\":\"Science\",\"uri\":\"http://en.wikipedia.org/wiki/Science\",\"label\":\"Science\",\"categories\":[\"Science\"],\"types\":[],\"alternateLabels\":[\"Science\",\"Scientific\",\"Sciences\",\"Scientific knowledge\",\"General Science\"]},{\"start\":239,\"end\":249,\"spot\":\"technology\",\"confidence\":0.7472,\"id\":29816,\"title\":\"Technology\",\"uri\":\"http://en.wikipedia.org/wiki/Technology\",\"label\":\"Technology\",\"categories\":[\"Technology\",\"Technology systems\"],\"types\":[],\"alternateLabels\":[\"Technology\",\"Technologies\",\"Technological\",\"Technical\",\"Technologist\"]},{\"start\":265,\"end\":268,\"spot\":\"ICT\",\"confidence\":0.7755,\"id\":1197962,\"title\":\"Information and communications technology\",\"uri\":\"http://en.wikipedia.org/wiki/Information_and_communications_technology\",\"label\":\"ICT\",\"categories\":[\"Communication\",\"Information technology\"],\"types\":[],\"alternateLabels\":[\"ICT\",\"Information and communications technology\",\"Information and Communication Technology\",\"Information and communication technologies\",\"Communication technology\"]},{\"start\":272,\"end\":280,\"spot\":\"products\",\"confidence\":0.6975,\"id\":240410,\"title\":\"Product (business)\",\"uri\":\"http://en.wikipedia.org/wiki/Product_%28business%29\",\"label\":\"Product\",\"categories\":[\"Business terms\",\"Marketing\",\"Products\",\"Supply chain management terms\"],\"types\":[],\"alternateLabels\":[\"Product\",\"Merchandise\",\"Products\",\"Consumer products\",\"Commercial products\"]},{\"start\":295,\"end\":305,\"spot\":\"businesses\",\"confidence\":0.7123,\"id\":39206,\"title\":\"Business\",\"uri\":\"http://en.wikipedia.org/wiki/Business\",\"label\":\"Business\",\"categories\":[\"Business\"],\"types\":[],\"alternateLabels\":[\"Business\",\"For-profit\",\"Enterprise\",\"Firm\",\"Businesses\"]},{\"start\":337,\"end\":349,\"spot\":\"productivity\",\"confidence\":0.7605,\"id\":424899,\"title\":\"Productivity\",\"uri\":\"http://en.wikipedia.org/wiki/Productivity\",\"label\":\"Productivity\",\"categories\":[\"Production and manufacturing\",\"Production economics\",\"Manufacturing\",\"Industry\",\"Economic growth\"],\"types\":[],\"alternateLabels\":[\"Productivity\",\"Productive\",\"Economic productivity\",\"Productivity growth\",\"Worker productivity\"]},{\"start\":363,\"end\":372,\"spot\":\"economies\",\"confidence\":0.7333,\"id\":6639133,\"title\":\"Economy\",\"uri\":\"http://en.wikipedia.org/wiki/Economy\",\"label\":\"Economic\",\"categories\":[\"Economic systems\",\"Economics\",\"Economies\"],\"types\":[\"http://dbpedia.org/ontology/Place\",\"http://dbpedia.org/ontology/PopulatedPlace\"],\"alternateLabels\":[\"Economy\",\"Economic\",\"Economies\",\"National economy\",\"Minister of Economy\"]},{\"start\":377,\"end\":382,\"spot\":\"value\",\"confidence\":0.7051,\"id\":4095185,\"title\":\"Value (economics)\",\"uri\":\"http://en.wikipedia.org/wiki/Value_%28economics%29\",\"label\":\"Value\",\"categories\":[\"Value\",\"Microeconomics\",\"Value theory\"],\"types\":[],\"alternateLabels\":[\"Value\",\"Economic value\",\"Monetary value\",\"Valuable\",\"Value for money\"]},{\"start\":393,\"end\":401,\"spot\":\"industry\",\"confidence\":0.7232,\"id\":14543,\"title\":\"Industry\",\"uri\":\"http://en.wikipedia.org/wiki/Industry\",\"label\":\"Industry\",\"categories\":[\"Industry\"],\"types\":[],\"alternateLabels\":[\"Industry\",\"Industrial\",\"Industries\",\"Industrial development\",\"Industrial activity\"]},{\"start\":406,\"end\":413,\"spot\":\"service\",\"confidence\":0.7258,\"id\":181586,\"title\":\"Service (economics)\",\"uri\":\"http://en.wikipedia.org/wiki/Service_%28economics%29\",\"label\":\"Services\",\"categories\":[\"Economics terminology\",\"Goods\",\"Services management and marketing\",\"Marketing\",\"Supply chain management terms\",\"Services\"],\"types\":[],\"alternateLabels\":[\"Services\",\"Service\",\"Business services\",\"Service companies\"]},{\"start\":414,\"end\":421,\"spot\":\"sectors\",\"confidence\":0.687,\"id\":12255159,\"title\":\"Economic sector\",\"uri\":\"http://en.wikipedia.org/wiki/Economic_sector\",\"label\":\"Sector\",\"categories\":[\"Economic systems\",\"Economic sectors\"],\"types\":[],\"alternateLabels\":[\"Sector\",\"Economic sector\",\"Sectors\",\"Economic sectors\",\"Industry\"]},{\"start\":423,\"end\":426,\"spot\":\"ICT\",\"confidence\":0.7583,\"id\":1197962,\"title\":\"Information and communications technology\",\"uri\":\"http://en.wikipedia.org/wiki/Information_and_communications_technology\",\"label\":\"ICT\",\"categories\":[\"Communication\",\"Information technology\"],\"types\":[],\"alternateLabels\":[\"ICT\",\"Information and communications technology\",\"Information and Communication Technology\",\"Information and communication technologies\",\"Communication technology\"]},{\"start\":440,\"end\":446,\"spot\":\"factor\",\"confidence\":0.6938,\"id\":11231,\"title\":\"Factors of production\",\"uri\":\"http://en.wikipedia.org/wiki/Factors_of_production\",\"label\":\"Factors of production\",\"categories\":[\"Production economics\",\"Microeconomics\",\"Labor\",\"Capital\",\"Resources\",\"Factors of production\"],\"types\":[],\"alternateLabels\":[\"Factors of production\",\"Resources\",\"Factor of production\",\"Resource\",\"Unit of production\"]},{\"start\":462,\"end\":468,\"spot\":\"change\",\"confidence\":0.7055,\"id\":329948,\"title\":\"Social change\",\"uri\":\"http://en.wikipedia.org/wiki/Social_change\",\"label\":\"Social change\",\"categories\":[\"Social change\",\"Social movements\",\"Social issues\",\"Sociology index\"],\"types\":[],\"alternateLabels\":[\"Social change\",\"Social development\",\"Social\",\"Development\",\"Change\"]},{\"start\":482,\"end\":492,\"spot\":\"innovation\",\"confidence\":0.7304,\"id\":118450,\"title\":\"Innovation\",\"uri\":\"http://en.wikipedia.org/wiki/Innovation\",\"label\":\"Innovation\",\"categories\":[\"Innovation economics\",\"Innovation\",\"Design\",\"Science and technology studies\",\"Economics\"],\"types\":[],\"alternateLabels\":[\"Innovation\",\"Innovative\",\"Innovations\",\"Technological innovation\",\"Innovate\"]},{\"start\":497,\"end\":507,\"spot\":\"creativity\",\"confidence\":0.692,\"id\":142910,\"title\":\"Creativity\",\"uri\":\"http://en.wikipedia.org/wiki/Creativity\",\"label\":\"Creativity\",\"categories\":[\"Creativity\",\"Cognition\",\"Educational psychology\",\"Positive psychology\",\"Aptitude\",\"Problem solving\",\"Concepts in aesthetics\"],\"types\":[],\"alternateLabels\":[\"Creativity\",\"Creative\",\"Creative thinking\",\"Creative process\",\"Creativeness\"]}],\"lang\":\"en\",\"langConfidence\":1.0,\"timestamp\":\"2016-01-26T18:19:18.014\"}";
	static boolean isSingleShortestPath = false;

	public static void main(String[] args) {
		// List<WikiText> wikiTextList=null;
		// Start Elastic rest Service
		ElasticUtil elasticUtil = new ElasticUtil();
		elasticUtil.startElasticRestService();
		// Start Dandelion rest Service
		DandelionUtil dandelionUtil = new DandelionUtil();
		dandelionUtil.connectTextExtractionService();

		System.out.println("Neo4j başlıyor...");
		WikiGraphUtil wikiGraphUtil = new WikiGraphUtil();
		wikiGraphUtil.startUpCordisDB();
		/*
		 * if(!isSampleTrial){ dandelionUtil.connectTextExtractionService();
		 * wikiTextList=dandelionUtil.getWikiTextSet(new
		 * StringBuffer(programObjective)); } else{
		 * wikiTextList=dandelionUtil.extractJsonString(jampleJson).
		 * getAnnotations(); }
		 */
		// ICT Program:
		boolean isProjectConcept = false;
		createConceptualGraph(elasticUtil, dandelionUtil, wikiGraphUtil, isProjectConcept, 852);
		isProjectConcept = true;
		for (int i = 0; i < MAX_PROJECT_SIZE && i < ictProjectIdList.length; i++) {
			createConceptualGraph(elasticUtil, dandelionUtil, wikiGraphUtil, isProjectConcept, ictProjectIdList[i]);
			System.out.println("Project Number:" + i + " imported...");

		}
		wikiGraphUtil.shutdownNeo4j();
	}

	private static void createConceptualGraph(ElasticUtil elasticUtil, DandelionUtil dandelionUtil,
			WikiGraphUtil wikiGraphUtil, boolean isProjectConcept, int id) {
		String objective = null;
		if (!isProjectConcept)
			objective = elasticUtil.getProgramObjective(id);
		else
			objective = elasticUtil.getProjectObjective(id);
		if (objective == null) {
			System.out.println("No objective found");
			return;
		}
		List<WikiText> wikiTextList;
		wikiTextList = dandelionUtil.getWikiTextSet(new StringBuffer(objective), id, isProjectConcept);
		System.out.println("Çıkarılan Terim Sayısı:" + wikiTextList.size());
		List<Node> extractedWikiNodeList = new ArrayList<Node>();
		List<String> existingTitleList = new ArrayList<String>();
		for (WikiText wikiText : wikiTextList) {
			Node n = wikiGraphUtil.findPageNodeByTitleFromWikipedia(wikiText.getTitle());
			if (n != null && !extractedWikiNodeList.contains(n)) {
				extractedWikiNodeList.add(n);
				existingTitleList.add(wikiText.getTitle());
			}
		}
		System.out.println("Tekil Terim Sayısı:" + extractedWikiNodeList.size());
		System.out.println("Nodelar oluşturuldu...En kısa yollar bulunuyor...");

		Node projectOrProgramNode = null;
		if (isProjectConcept)
			projectOrProgramNode = wikiGraphUtil.createCordisProgramorProjectNode(WikiLabel.Project, id);
		else
			projectOrProgramNode = wikiGraphUtil.createCordisProgramorProjectNode(WikiLabel.Program, id);

		// TODO proje ve program nodeu ile relationlar kurulacak textte.
		// create all nodes in text.
		for (int i = 0; i < extractedWikiNodeList.size(); i++) {
			wikiGraphUtil.createCordisTermNodeForProjectOrProgram(extractedWikiNodeList.get(i), projectOrProgramNode,
					id, WikiLabel.ExistingTerm, isProjectConcept);
		}

		for (int i = 0; i < extractedWikiNodeList.size(); i++) {
			for (int j = 0; j < extractedWikiNodeList.size(); j++) {
				if (i != j) {

					List<Path> shortestPathList = new ArrayList<Path>();
					shortestPathList = wikiGraphUtil.findShortestWikiPathList(extractedWikiNodeList.get(i),
							extractedWikiNodeList.get(j), MAX_PATH_LENGTH, isSingleShortestPath);

					if (shortestPathList != null && shortestPathList.size() > 0) {
						for (Path shortestPath : shortestPathList) {
							wikiGraphUtil.importPathToCordisDB(shortestPath, isProjectConcept, id,
									projectOrProgramNode);
						}
					} else {
						// no shortest path!
					}
				}
			}
		}
		System.out.println("is Project Concept:" + isProjectConcept + " imported...");
	}

	static int[] ictProjectIdList = { 85231, 85232, 85233, 85234, 85235, 85236, 85237, 85238, 85239, 85240, 85241,
			85242, 85243, 85244, 85245, 85246, 85247, 85248, 85249, 85250, 85251, 85252, 85253, 85254, 85255, 85263,
			85264, 85265, 85266, 85267, 85268, 85269, 85270, 85271, 85272, 85273, 85274, 85275, 85276, 85277, 85279,
			85280, 85281, 85282, 85283, 85284, 85285, 85286, 85287, 85288, 85289, 85290, 85291, 85292, 85294, 85295,
			85296, 85297, 85298, 85299, 85300, 85301, 85302, 85303, 85304, 85305, 85306, 85307, 85308, 85309, 85310,
			85311, 85312, 85313, 85314, 85315, 85316, 85317, 85318, 85319, 85320, 85321, 85322, 85323, 85324, 85325,
			85326, 85327, 85328, 85329, 85330, 85331, 85332, 85333, 85334, 85335, 85336, 85337, 85338, 85339, 85340,
			85341, 85342, 85343, 85344, 85345, 85346, 85347, 85348, 85349, 85350, 85351, 85352, 85353, 85354, 85355,
			85356, 85357, 85358, 85359, 85360, 85361, 85362, 85363, 85364, 85365, 85366, 85367, 85368, 85369, 85370,
			85374, 85375, 85376, 85377, 85378, 85379, 85380, 85381, 85382, 85383, 85384, 85385, 85386, 85387, 85388,
			85389, 85390, 85391, 85392, 85393, 85394, 85395, 85396, 85397, 85398, 85399, 85400, 85401, 85402, 85403,
			85404, 85405, 85406, 85407, 85408, 85409, 85410, 85411, 85412, 85413, 85414, 85415, 85416, 85417, 85418,
			85419, 85420, 85421, 85422, 85423, 85424, 85425, 85426, 85427, 85428, 85429, 85430, 85431, 85432, 85433,
			85434, 85435, 85436, 85437, 85438, 85439, 85440, 85441, 85477, 85442, 85443, 85444, 85445, 85446, 85447,
			85478, 85448, 85449, 85479, 85450, 85451, 85452, 85453, 85454, 85455, 85456, 85457, 85458, 85459, 85460,
			85461, 85462, 85463, 85464, 85465, 85466, 85467, 85468, 85469, 85470, 85480, 85471, 85481, 85472, 85482,
			85473, 85474, 85475, 85484, 85476, 85524, 85525, 85526, 85527, 85528, 85529, 85530, 85531, 85532, 85533,
			85534, 85535, 85536, 85537, 85538, 85539, 85540, 85541, 85542, 85543, 85544, 85545, 85546, 85547, 85548,
			85549, 85550, 85551, 85552, 85553, 85554, 85555, 85556, 85557, 85558, 85559, 85560, 85561, 85562, 85563,
			85564, 85566, 85567, 85568, 85569, 85570, 85571, 85572, 85573, 85574, 85575, 85727, 85728, 85774, 85729,
			85775, 85779, 85780, 85781, 85783, 85784, 85785, 85786, 86215, 86216, 86231, 86232, 86240, 86241, 86242,
			86366, 86374, 86387, 86388, 86389, 86390, 86391, 86409, 86410, 86411, 86412, 86413, 86414, 86415, 86592,
			86593, 86595, 86596, 86597, 86598, 86599, 86605, 86606, 86607, 86610, 86611, 86612, 86613, 86614, 86615,
			86616, 86617, 86618, 86619, 86620, 86621, 86624, 86625, 86626, 86627, 86628, 86629, 86630, 86631, 86632,
			86633, 86634, 86635, 86636, 86637, 86656, 86663, 86664, 86665, 86666, 86667, 86668, 86669, 86670, 86671,
			86672, 86673, 86674, 86675, 86676, 86677, 86678, 86679, 86680, 86681, 86682, 86683, 86717, 86718, 86719,
			86720, 86721, 86722, 86723, 86724, 86725, 86726, 86727, 86728, 87012, 87013, 87014, 87015, 87016, 87017,
			87018, 87019, 87020, 87021, 87022, 87023, 87265, 87266, 87267, 87268, 87269, 87270, 87271, 87272, 87273,
			87274, 87275, 87276, 87277, 87278, 87279, 87280, 87304, 87305, 87306, 87307, 87308, 87309, 87310, 87311,
			87312, 87313, 87314, 87315, 87316, 87317, 87318, 87319, 87364, 87365, 87366, 87367, 87368, 87369, 87370,
			87371, 87372, 87373, 87374, 87600, 87601, 87602, 87603, 87604, 87605, 87606, 87607, 87608, 87609, 87672,
			87673, 87674, 87675, 87676, 87677, 87678, 87679, 87680, 87681, 87706, 87707, 87708, 87709, 87710, 87711,
			87753, 87754, 87755, 87756, 87757, 87828, 87873, 87874, 87875, 87973, 88209, 88359, 88496, 88625, 88883,
			88970, 88971, 89025, 89026, 89027, 89028, 89029, 89030, 89031, 89032, 89033, 89034, 89035, 89036, 89235,
			89236, 89237, 89238, 89239, 89240, 89241, 89242, 89261, 89243, 89244, 89245, 89246, 89247, 89248, 89249,
			89250, 89251, 89252, 89253, 89254, 89255, 89256, 89257, 89258, 89259, 89260, 89449, 89450, 89451, 89452,
			89453, 89454, 89455, 89480, 89481, 89482, 89483, 89484, 89485, 89486, 89487, 89488, 89489, 89490, 89491,
			89492, 89493, 89494, 89495, 89496, 89510, 89511, 89512, 89519, 89520, 89521, 89522, 89523, 89800, 89890,
			90428, 90429, 90430, 90431, 90432, 90433, 90434, 90435, 90436, 90619, 91040, 91133, 91499, 91500, 91501,
			91502, 91503, 91504, 91505, 91507, 92143, 92305, 92306, 92566, 92567, 92807, 92864, 92865, 92866, 92867,
			92868, 93005, 93006, 93007, 93008, 93064, 93065, 93066, 93067, 93068, 93069, 93070, 93071, 93072, 93073,
			93074, 93075, 93076, 93077, 93251, 93252, 93254, 93255, 93256, 93257, 93258, 93259, 93260, 93261, 93262,
			93263, 93264, 93265, 93266, 93267, 93268, 93269, 93270, 93271, 93272, 93273, 93274, 93275, 93276, 93277,
			93278, 93279, 93280, 93536, 93537, 93538, 93539, 93540, 93541, 93542, 93543, 93544, 93545, 93546, 93547,
			93548, 93549, 93550, 93551, 93552, 93553, 93554, 93555, 93556, 93563, 93576, 93581, 93598, 93599, 93600,
			93601, 93602, 93603, 93626, 93627, 93628, 93629, 93630, 93710, 93711, 93712, 93713, 93714, 93715, 93716,
			93717, 93718, 93719, 93720, 93721, 93722, 93723, 93724, 93725, 93726, 93727, 93728, 93729, 93730, 93732,
			93733, 93734, 93735, 93736, 93737, 93738, 93739, 93741, 93742, 93743, 93745, 93746, 93747, 93749, 93750,
			93751, 93752, 93753, 93754, 93755, 93756, 93757, 93758, 93759, 93760, 93761, 93762, 93763, 93764, 93765,
			93766, 93767, 93768, 93769, 93770, 93771, 93772, 93773, 93774, 93775, 93776, 93777, 93778, 93779, 93780,
			93781, 93782, 93783, 93784, 93785, 93786, 93787, 93788, 93789, 93790, 93791, 93792, 93793, 93794, 93795,
			93796, 93797, 93798, 93799, 93800, 93801, 93802, 93803, 93805, 93806, 93807, 93808, 93809, 93810, 93811,
			93812, 93813, 93814, 93815, 93816, 93817, 93818, 93819, 93820, 93821, 93825, 93827, 93829, 93831, 93832,
			93833, 93834, 93835, 93836, 93837, 93838, 93839, 93840, 93842, 93843, 93844, 93845, 93846, 93852, 93853,
			93857, 93890, 93917, 93933, 93936, 93944, 93945, 93950, 93968, 93996, 94029, 94030, 94031, 94043, 94044,
			94045, 94122, 94140, 94147, 94148, 94149, 94150, 94151, 94217, 94223, 94233, 94234, 94288, 94311, 94327,
			94328, 94356, 94367, 94368, 94384, 94385, 94414, 94453, 94511, 94512, 94513, 94514, 94515, 94516, 94687,
			94688, 94730, 94731, 94740, 94741, 94791, 94909, 94910, 94911, 94912, 94913, 94914, 94915, 94916, 94918,
			94919, 94933, 94962, 94963, 94964, 94965, 94966, 94967, 95015, 95016, 95040, 95041, 95042, 95043, 95045,
			95046, 95047, 95048, 95086, 95088, 95098, 95099, 95100, 95101, 95102, 95103, 95104, 95141, 95142, 95143,
			95144, 95145, 95179, 95180, 95181, 95182, 95183, 95199, 95200, 95202, 95204, 95205, 95206, 95207, 95209,
			95211, 95213, 95214, 95239, 95249, 95250, 95251, 95252, 95253, 95254, 95255, 95257, 95299, 95300, 95301,
			95302, 95305, 95306, 95307, 95308, 95309, 95310, 95312, 95314, 95315, 95316, 95331, 95332, 95333, 95346,
			95347, 95348, 95373, 95374, 95402, 95403, 95423, 95424, 95428, 95429, 95444, 95445, 95446, 95447, 95448,
			95467, 95468, 95469, 95470, 95471, 95472, 95473, 95484, 95485, 95486, 95487, 95488, 95489, 95500, 95501,
			95502, 95510, 95511, 95512, 95513, 95514, 95515, 95516, 95531, 95532, 95534, 95545, 95546, 95547, 95548,
			95549, 95550, 95551, 95552, 95553, 95554, 95556, 95557, 95560, 95561, 95562, 95563, 95564, 95578, 95579,
			95580, 95581, 95582, 95583, 95584, 95585, 95586, 95587, 95588, 95589, 95590, 95591, 95592, 95593, 95594,
			95595, 95596, 95597, 95598, 95665, 95666, 95667, 95668, 95669, 95674, 95681, 95682, 95713, 95714, 95738,
			95739, 95740, 95741, 95742, 95743, 95752, 95753, 95755, 95756, 95757, 95776, 95793, 95795, 95815, 95816,
			95851, 95863, 95864, 95898, 95899, 95900, 95901, 95902, 95903, 95904, 95905, 95906, 95922, 95924, 95926,
			95927, 95928, 95929, 95930, 95931, 95932, 95933, 95934, 95935, 96235, 96288, 96343, 96344, 96468, 96469,
			96481, 96598, 96674, 96769, 96788, 96789, 96791, 96812, 96826, 96893, 96955, 97024, 97047, 97048, 97049,
			97085, 97109, 97110, 97165, 97166, 97182, 97242, 97243, 97302, 97303, 97304, 97336, 97337, 97338, 97393,
			97394, 97395, 97396, 97440, 97441, 97442, 97443, 97444, 97445, 97446, 97447, 97457, 97458, 97459, 97460,
			97461, 97462, 97463, 97464, 97465, 97466, 97471, 97472, 97473, 97474, 97475, 97476, 97477, 97478, 97572,
			97573, 97574, 97676, 97727, 97731, 97732, 97745, 97746, 97815, 97842, 97843, 97844, 97861, 97862, 97863,
			97865, 97919, 97926, 97956, 97979, 97980, 98002, 98048, 98063, 98118, 98138, 98140, 98164, 98212, 98318,
			98319, 98352, 98421, 99179, 99180, 99182, 99183, 99185, 99186, 99187, 99188, 99191, 99192, 99193, 99205,
			99254, 99270, 99281, 99283, 99306, 99337, 99412, 99441, 99442, 99460, 99475, 99491, 99492, 99493, 99511,
			99512, 99513, 99564, 99565, 99583, 99649, 99652, 99653, 99654, 99690, 99691, 99692, 99693, 99694, 99695,
			99696, 99697, 99768, 99786, 99793, 99794, 99795, 99796, 99797, 99798, 99799, 99800, 99801, 99802, 99803,
			99804, 99805, 99806, 99825, 99826, 99827, 99828, 99908, 99909, 99927, 99928, 99929, 99930, 99931, 99932,
			99933, 99934, 99935, 99987, 99988, 99989, 99994, 99995, 99996, 99997, 99998, 99999, 100009, 100010, 100030,
			100032, 100033, 100034, 100036, 100037, 100076, 100077, 100078, 100080, 100095, 100096, 100097, 100098,
			100099, 100100, 100101, 100102, 100103, 100104, 100106, 100127, 100145, 100146, 100147, 100148, 100149,
			100165, 100166, 100192, 100193, 100229, 100230, 100231, 100232, 100248, 100249, 100250, 100251, 100252,
			100254, 100273, 100274, 100275, 100322, 100323, 100324, 100325, 100698, 100699, 100700, 100701, 100702,
			100703, 100704, 100705, 100707, 100708, 100709, 100710, 100711, 100712, 100713, 100714, 100715, 100716,
			100717, 100718, 100719, 100720, 100721, 100722, 100723, 100724, 100725, 100726, 100731, 100732, 100733,
			100734, 100735, 100736, 100737, 100738, 100739, 100740, 100741, 100742, 100743, 100744, 100745, 100746,
			100747, 100748, 100749, 100750, 100751, 100752, 100753, 100754, 100755, 100756, 100757, 100758, 100759,
			100760, 100761, 100762, 100763, 100764, 100765, 100766, 100767, 100768, 100769, 100770, 100771, 100772,
			100773, 100774, 100775, 100792, 100793, 100794, 100795, 100796, 100798, 100799, 100800, 100801, 100802,
			100803, 100804, 100805, 100869, 100870, 100872, 100873, 100874, 100893, 100894, 100895, 100913, 100944,
			100945, 100975, 100976, 100982, 100983, 100984, 100985, 100986, 101211, 101212, 101213, 101214, 101215,
			101216, 101220, 101283, 101284, 101285, 101347, 101348, 101349, 101350, 101351, 101352, 101353, 101414,
			101415, 101416, 101417, 101459, 101476, 101477, 101478, 101496, 101533, 101534, 101535, 101569, 101723,
			101724, 101725, 101726, 101838, 101839, 101840, 101841, 101842, 101843, 101982, 102049, 102050, 102051,
			102118, 102119, 102120, 102121, 102122, 102123, 102124, 102125, 102143, 102144, 102145, 102146, 102147,
			102148, 102149, 102150, 102156, 102157, 102204, 102205, 102206, 102231, 102272, 102273, 102313, 102343,
			102344, 102378, 102440, 102441, 102483, 102484, 102559, 102560, 102984, 103419, 103420, 103499, 103572,
			103681, 103682, 103683, 103684, 103685, 103686, 103707, 103708, 103709, 103710, 103840, 103841, 103868,
			103889, 103901, 103902, 103948, 103949, 103998, 103999, 104027, 104073, 104074, 104075, 104129, 104132,
			104150, 104151, 104152, 104176, 104177, 104203, 104272, 104365, 104367, 104480, 104481, 104564, 104594,
			104608, 104675, 104753, 104761, 104779, 104780, 104781, 104782, 104783, 104824, 104877, 104878, 104913,
			104914, 104915, 104931, 104951, 104969, 104970, 104971, 104972, 104973, 104974, 104989, 104990, 105013,
			105014, 105035, 105051, 105052, 105053, 105062, 105092, 105093, 105094, 105115, 105116, 105117, 105118,
			105119, 105120, 105138, 105139, 105140, 105141, 105142, 105159, 105160, 105161, 105162, 105163, 105164,
			105165, 105166, 105167, 105168, 105169, 105170, 105171, 105172, 105190, 105191, 105192, 105193, 105194,
			105195, 105196, 105197, 105198, 105240, 105241, 105242, 105243, 105244, 105291, 105292, 105293, 105294,
			105295, 105296, 105297, 105298, 105299, 105300, 105301, 105381, 105382, 105383, 105384, 105385, 105386,
			105387, 105388, 105389, 105390, 105431, 105432, 105433, 105434, 105462, 105463, 105464, 105465, 105466,
			105467, 105468, 105469, 105470, 105471, 105472, 105539, 105540, 105541, 105542, 105543, 105544, 105545,
			105546, 105547, 105548, 105549, 105550, 105551, 105552, 105553, 105554, 105602, 105603, 105604, 105605,
			105606, 105607, 105608, 105609, 105621, 105622, 105623, 105624, 105632, 105633, 105634, 105635, 105636,
			105637, 105697, 105698, 105699, 105709, 105730, 105731, 105732, 105733, 105734, 105735, 105736, 105737,
			105738, 105768, 105769, 105770, 105771, 105772, 105773, 105774, 105816, 105817, 105818, 105819, 105820,
			105821, 105822, 105823, 105824, 105841, 105853, 105854, 105871, 105872, 105873, 105917, 105918, 105919,
			105920, 105921, 105938, 105974, 105975, 105976, 105977, 105978, 105990, 105991, 105992, 105993, 106007,
			106026, 106027, 106028, 106029, 106030, 106031, 106032, 106033, 106034, 106035, 106048, 106049, 106172,
			106173, 106174, 106213, 106214, 106215, 106216, 106217, 106218, 106219, 106237, 106238, 106239, 106272,
			106294, 106295, 106296, 106313, 106314, 106336, 106337, 106338, 106339, 106340, 106341, 106342, 106343,
			106344, 106345, 106346, 106388, 106389, 106390, 106391, 106414, 106415, 106429, 106430, 106473, 106474,
			106495, 106496, 106521, 106522, 106624, 106625, 106626, 106627, 106628, 106647, 106648, 106649, 106650,
			106651, 106685, 106686, 106687, 106688, 106689, 106690, 106691, 106692, 106729, 106783, 106784, 106785,
			106786, 106795, 106841, 106842, 106843, 106844, 106845, 106846, 106847, 106848, 106849, 106850, 106851,
			106852, 106854, 106855, 106858, 106859, 106860, 106885, 106886, 106887, 106888, 106902, 106903, 106904,
			106905, 106957, 106958, 106959, 106960, 106961, 106962, 106963, 106964, 106965, 106992, 106993, 106994,
			107030, 107031, 107155, 107156, 107157, 107358, 107493, 107658, 107728, 107845, 108001, 108002, 108023,
			108024, 108025, 108026, 108027, 108028, 108029, 108030, 108056, 108057, 108058, 108130, 108156, 108157,
			108158, 108228, 108232, 108366, 108367, 108499, 108514, 108515, 108537, 108626, 108643, 108662, 108663,
			108664, 108665, 108666, 108667, 108702, 108703, 108814, 108870, 108912, 108913, 108914, 108915, 108916,
			108917, 108918, 108919, 108920, 108921, 108922, 108923, 108924, 108925, 108926, 108927, 108928, 108929,
			108930, 108931, 108932, 108933, 108934, 108935, 108936, 108937, 108938, 108939, 108940, 108941, 108942,
			108943, 108944, 108945, 108946, 108947, 108948, 108958, 109021, 109060, 109108, 109109, 109151, 109223,
			109224, 109225, 109226, 109227, 109228, 109257, 109258, 109259, 109295, 109296, 109297, 109298, 109299,
			109300, 109301, 109302, 109303, 109304, 109407, 109408, 109530, 109531, 109691, 109692, 109693, 109694,
			109695, 109696, 109697, 109698, 109699, 109700, 109701, 109702, 109703, 109704, 109705, 109706, 109707,
			109708, 109709, 109710, 109711, 109712, 109780, 109781, 109782, 109803, 109804, 109805, 109806, 109807,
			109808, 109809, 109810, 109868, 109869, 109870, 109871, 109893, 109894, 109895, 109896, 109897, 109898,
			109899, 109916, 109917, 109942, 109943, 109944, 109945, 109946, 109947, 109948, 109949, 109981, 109996,
			109997, 109998, 109999, 110000, 110001, 110002, 110028, 110042, 110043, 110044, 110045, 110046, 110047,
			110048, 110129, 110130, 110131, 110132, 110133, 110134, 110135, 110136, 110137, 110155, 110156, 110157,
			110158, 110159, 110160, 110184, 110185, 110186, 110219, 110220, 110221, 110222, 110223, 110224, 110225,
			110226, 110227, 110248, 110268, 110269, 110310, 110311, 110312, 110313, 110314, 110325, 110326, 110374,
			110375, 110376, 110377, 110378, 110379, 110380, 110381, 110382, 110383, 110384, 110385, 110428, 110429,
			110430, 110431, 110455, 110456, 110457, 110458, 110466, 110503, 110504, 110505, 110506, 110507, 110508,
			110509, 110520, 110521, 110522, 110523, 110524, 110525, 110559, 110560, 110561, 110562, 110586, 110587,
			110588, 110589, 110590, 110591, 110592, 110593, 110594, 110595, 110596, 110597, 110598, 110627, 110628,
			110629, 110630, 110635, 110636, 110645, 110646, 110647, 110648, 110649, 110650, 110651, 110652, 110653,
			110654, 110655, 110656, 110657, 110658, 110681, 110682, 110713, 110714, 110715, 110716, 110717, 110718,
			110719, 110720, 110721, 110722, 110723, 110724, 110725, 110726, 110727, 110728, 110729, 110730, 110758,
			110759, 110760, 110761, 110771, 110772, 110773, 110792, 110793, 110827, 110828, 110829, 110830, 110876,
			110877, 110878, 110879, 110880, 110881, 110899, 110900, 110901, 110902, 110903, 110904, 110905, 110906,
			110907, 110908, 110909, 110910, 110927, 110928, 110970, 110971, 110972, 110973, 110987, 110988, 110989,
			110990, 110991, 110992, 110993, 111010, 111011, 111012, 111020, 111030, 111031, 111032, 111033, 111045 };
}
