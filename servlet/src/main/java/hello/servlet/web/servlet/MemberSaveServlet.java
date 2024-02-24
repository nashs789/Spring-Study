package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        Member mem = new Member(username, age);

        memberRepository.save(mem);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter pw = resp.getWriter();

        pw.write("<html>\n" +
                 "<head>\n" +
                 "    <meta charset=\"UTF-8\">\n" +
                 "</head>\n" +
                 "<body>\n" +
                 "성공\n" +
                 "<ul>\n" +
                 "    <li>id="+mem.getId()+"</li>\n" +
                 "    <li>username="+mem.getUsername()+"</li>\n" +
                 "    <li>age="+mem.getAge()+"</li>\n" +
                 "</ul>\n" +
                 "<a href=\"/index.html\">메인</a>\n" +
                 "</body>\n" +
                 "</html>");
    }
}
