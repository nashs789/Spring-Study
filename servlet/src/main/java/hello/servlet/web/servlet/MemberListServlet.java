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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> mems = memberRepository.findAll();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.write("<head>");
        pw.write("    <meta charset=\"UTF-8\">");
        pw.write("    <title>Title</title>");
        pw.write("</head>");
        pw.write("<body>");
        pw.write("<a href=\"/index.html\">메인</a>");
        pw.write("<table>");
        pw.write("    <thead>");
        pw.write("    <th>id</th>");
        pw.write("    <th>username</th>");
        pw.write("    <th>age</th>");
        pw.write("    </thead>");
        pw.write("    <tbody>");

        for (Member member : mems) {
            pw.write("    <tr>");
            pw.write("        <td>"+member.getId()+"</td>");
            pw.write("        <td>"+member.getUsername()+"</td>");
            pw.write("        <td>"+member.getAge()+"</td>");
            pw.write("    </tr>");
        }

        pw.write("    </tbody>");
        pw.write("</table>");
        pw.write("</body>");
        pw.write("</html>");
    }
}
