import com.google.gson.annotations.SerializedName;

public class Articulo {
    @SerializedName(value = "id")
	private String id;
	@SerializedName(value = "id_user")
	private String  id_user; 
	@SerializedName(value = "is_public")
	private String  is_public; 
	@SerializedName(value = "abstract")
	private String  abstracto;
    @SerializedName(value = "body")
	private String  body;
    @SerializedName(value = "subtitle")
	private String  subtitle;
    @SerializedName(value = "update_date")
	private String  update_date;
    @SerializedName(value = "category")
	private String  category;
    @SerializedName(value = "title")
	private String  title;
    @SerializedName(value = "is_deleted")
	private String  is_deleted;
    @SerializedName(value = "thumbnail_image")
	private String  thumbnail_image;
    @SerializedName(value = "username")
	private String  username;
	
	@SerializedName(value = "image_description")
	private String  image_description;
	@SerializedName(value = "image_media_type")
	private String  image_media_type;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_User() {
		return id_user;
	}

	public void setId_User(String id_user) {
		this.id_user = id_user;
	}

	public String getIs_public() {
		return is_public;
	}

	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}

	public String getAbstract() {
		return abstracto;
	}

	public void setAbstract(String abstracto) {
		this.abstracto = abstracto;
	}

    public String getBody() {
		return body;
	}

	public void setbody(String body) {
		this.body = body;
	}

    public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

    public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

    public String getThumbnail_image() {
		return thumbnail_image;
	}

	public void setThumbnail_image(String thumbnail_image) {
		this.thumbnail_image = thumbnail_image;
	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getImage_description() {
		return image_description;
	}

	public void setImage_description(String image_description) {
		this.image_description = image_media_type;
	}

	public String getImage_media_type() {
		return image_media_type;
	}

	public void setImage_media_type(String image_media_type) {
		this.image_media_type = image_media_type;
	}
}
