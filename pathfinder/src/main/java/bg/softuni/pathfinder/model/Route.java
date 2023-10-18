package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    private User author;

    @ManyToMany
    private Set<Category> categories;

    public Route () {
        this.categories = new HashSet<>();
    }

    public Long getId () {
        return id;
    }

    public Route setId (Long id) {
        this.id = id;
        return this;
    }

    public String getDescription () {
        return description;
    }

    public Route setDescription (String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates () {
        return gpxCoordinates;
    }

    public Route setGpxCoordinates (String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel () {
        return level;
    }

    public Route setLevel (Level level) {
        this.level = level;
        return this;
    }

    public String getName () {
        return name;
    }

    public Route setName (String name) {
        this.name = name;
        return this;
    }

    public User getAuthor () {
        return author;
    }

    public Route setAuthor (User author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl () {
        return videoUrl;
    }

    public Route setVideoUrl (String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Route setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<Category> getCategories () {
        return categories;
    }

    public Route setCategories (Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public void addCategories (Set<Category> categories) {
        this.categories.addAll(categories);
    }
}
