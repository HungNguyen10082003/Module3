package bt.email;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping({"/settings"})
    public String settings(Model model) {
        model.addAttribute("settings", settingsService.getSettings());
        return "settings";
    }

    @PostMapping("/settings")
    public String update(@ModelAttribute EmailSettings settings) {
        settingsService.setSettings(settings);
        return "redirect:/settings";
    }
}
