package models;

public class Publisher {

    private int publisher_id;

    private String name;

    private String url;

    @Override
    public String toString() {
        return "ID da Editora: " + publisher_id + "\nNome da Editora: " + name + "\nURL: " + url;
    }



    public int getPublishers_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
