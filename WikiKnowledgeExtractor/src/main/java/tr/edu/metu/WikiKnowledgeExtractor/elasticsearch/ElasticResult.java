package tr.edu.metu.WikiKnowledgeExtractor.elasticsearch;

public class ElasticResult {
	String _index,_type,found;
	int _id,_version;
	Source _source;
	public String get_index() {
		return _index;
	}
	public void set_index(String _index) {
		this._index = _index;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public String getFound() {
		return found;
	}
	public void setFound(String found) {
		this.found = found;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_version() {
		return _version;
	}
	public void set_version(int _version) {
		this._version = _version;
	}
	public Source get_source() {
		return _source;
	}
	public void set_source(Source _source) {
		this._source = _source;
	}
	

}

