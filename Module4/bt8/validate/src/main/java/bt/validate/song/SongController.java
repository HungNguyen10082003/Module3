package bt.validate.song;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/songs";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "songs/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("songForm", new SongForm());
        model.addAttribute("pageTitle", "Thêm mới bài hát");
        model.addAttribute("submitLabel", "Lưu");
        model.addAttribute("formAction", "/songs/create");
        return "songs/form";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("songForm") SongForm songForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Thêm mới bài hát");
            model.addAttribute("submitLabel", "Lưu");
            model.addAttribute("formAction", "/songs/create");
            return "songs/form";
        }

        songService.create(songForm);
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("songForm", toForm(song));
        model.addAttribute("pageTitle", "Cập nhật bài hát");
        model.addAttribute("submitLabel", "Cập nhật");
        model.addAttribute("formAction", "/songs/" + id + "/edit");
        model.addAttribute("songId", id);
        return "songs/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("songForm") SongForm songForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Cập nhật bài hát");
            model.addAttribute("submitLabel", "Cập nhật");
            model.addAttribute("formAction", "/songs/" + id + "/edit");
            model.addAttribute("songId", id);
            return "songs/form";
        }

        songService.update(id, songForm);
        return "redirect:/songs";
    }

    private SongForm toForm(Song song) {
        SongForm form = new SongForm();
        form.setTitle(song.getTitle());
        form.setArtist(song.getArtist());
        form.setGenre(song.getGenre());
        return form;
    }
}