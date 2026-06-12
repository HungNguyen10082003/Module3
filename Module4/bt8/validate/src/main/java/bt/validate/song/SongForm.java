package bt.validate.song;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SongForm {

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s]+$",
        message = "Tên bài hát không được chứa ký tự đặc biệt"
    )
    private String title;

    @NotBlank(message = "Nghệ sĩ thể hiện không được để trống")
    @Size(max = 300, message = "Nghệ sĩ thể hiện không được vượt quá 300 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s]+$",
        message = "Nghệ sĩ thể hiện không được chứa ký tự đặc biệt"
    )
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại nhạc không được vượt quá 1000 ký tự")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s,]+$",
        message = "Thể loại nhạc chỉ được chứa chữ, số, khoảng trắng và dấu phẩy"
    )
    private String genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}