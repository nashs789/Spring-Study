package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("")
    public ModelAndView members(HttpServletRequest req, HttpServletResponse resp) {
        List<Member> mems = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", mems);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member mem = new Member(username, age);
        memberRepository.save(mem);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", mem);

        return mv;
    }
}
