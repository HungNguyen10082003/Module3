package bt.email;

import org.springframework.stereotype.Service;

@Service
public class SettingsService {
    private EmailSettings settings = new EmailSettings();

    public EmailSettings getSettings() {
        return settings;
    }

    public void setSettings(EmailSettings settings) {
        if (settings == null) return;
        this.settings = settings;
    }
}
