package myenglish.web.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyEnglishIndexController {
	@RequestMapping(value="/")
	public String index(Model model) {
		return "redirect:/quiz";
	}
//	public String index(Model model) {
//		/* ログイン機能を作っていないが故の仮実装
//		 * 無理やりログインしたことにしている */
//		boolean iscreate = true;
//		if(!iscreate) {
//			return "redirect:/quiz";
//		}
//		return "index/index";
//	}

}
