package bt.validate.song;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class
SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        return songRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Song findById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found: " + id));
    }

    public Song create(SongForm form) {
        Song song = new Song();
        applyForm(song, form);
        return songRepository.save(song);
    }

    public Song update(Long id, SongForm form) {
        Song song = findById(id);
        applyForm(song, form);
        return songRepository.save(song);
    }

    private void applyForm(Song song, SongForm form) {
        song.setTitle(trimmed(form.getTitle()));
        song.setArtist(trimmed(form.getArtist()));
        song.setGenre(trimmed(form.getGenre()));
    }

    private String trimmed(String value) {
        return value == null ? null : value.trim();
    }
}