import com.google.gson.annotations.SerializedName;

public class PostResult {
	
	@SerializedName(value = "header")
	private PostHeaders headers;
	
	@SerializedName(value = "url")
	private String url;

	public PostHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(PostHeaders headers) {
		this.headers = headers;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
