package work.sunxu.personalblog.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.bmob.v3.BmobObject;

public class Blog extends BmobObject implements Parcelable{

    private String title;
    private String category;
    private String link;


    protected Blog(Parcel in) {
        title = in.readString();
        category = in.readString();
        link = in.readString();
    }


    public static final Creator<Blog> CREATOR = new Creator<Blog>() {
        @Override public Blog createFromParcel(Parcel in) {
            return new Blog(in);
        }


        @Override public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    @Override public String toString() {
        return "Blog{" + "title='" + title + '\'' + ", category='" + category + '\'' + ", link='" + link + '\'' + '}';
    }


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(link);
    }
}
