package bt.changemoney;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    private static final Map<String, String> DICTIONARY = new HashMap<>();

    static {
        // Từ điển Anh - Việt
        DICTIONARY.put("hello", "Xin chào");
        DICTIONARY.put("goodbye", "Tạm biệt");
        DICTIONARY.put("thank you", "Cảm ơn");
        DICTIONARY.put("please", "Vui lòng");
        DICTIONARY.put("yes", "Có");
        DICTIONARY.put("no", "Không");
        DICTIONARY.put("water", "Nước");
        DICTIONARY.put("food", "Thức ăn");
        DICTIONARY.put("friend", "Bạn");
        DICTIONARY.put("love", "Yêu");
        DICTIONARY.put("book", "Sách");
        DICTIONARY.put("computer", "Máy tính");
        DICTIONARY.put("school", "Trường học");
        DICTIONARY.put("teacher", "Giáo viên");
        DICTIONARY.put("student", "Học sinh");
        DICTIONARY.put("house", "Nhà");
        DICTIONARY.put("money", "Tiền");
        DICTIONARY.put("work", "Công việc");
        DICTIONARY.put("happy", "Vui vẻ");
        DICTIONARY.put("beautiful", "Đẹp");
        DICTIONARY.put("cat", "Mèo");
        DICTIONARY.put("dog", "Chó");
        DICTIONARY.put("car", "Ô tô");
        DICTIONARY.put("phone", "Điện thoại");
        DICTIONARY.put("music", "Âm nhạc");
    }

    @GetMapping
    public String index() {
        return "dictionary/index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập từ cần tra cứu!");
            return "dictionary/index";
        }

        String searchKey = keyword.trim().toLowerCase();
        String meaning = DICTIONARY.get(searchKey);

        if (meaning != null) {
            model.addAttribute("keyword", keyword);
            model.addAttribute("meaning", meaning);
            model.addAttribute("found", true);
        } else {
            model.addAttribute("keyword", keyword);
            model.addAttribute("found", false);
        }

        return "dictionary/result";
    }
}
