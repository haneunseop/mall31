package cafe.jjdev.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.MemberService;
import cafe.jjdev.mall.vo.Member;

@Controller
public class MemberController {
	//-------------------2019/05/08 개인 과제--------------------
	@Autowired private MemberService memberService;
	// 1 로그인 폼 
	@GetMapping(value="/member/login")
	public String login(HttpSession session) {
		if(session.getAttribute("loginMember") != null) {
			return "redirect:/";
		}else {
			return "/member/login";
		}
	}
	
	// 2 로그인 액션 
	@PostMapping(value="/member/login") 
	public String login(HttpSession session, Member member) {
		int loginresult = memberService.login(session, member);
		if(loginresult == 0) {
			System.out.println("[cafe.jjdev.mall.controller.MemberController.getMember] POST 로그인 실패");
			return "redirect:/member/login";
		}else {
			System.out.println("[cafe.jjdev.mall.controller.MemberController.getMember] POST 로그인 성공");
			return "redirect:/";
		}
	}
	
	// 3 로그아웃
	@GetMapping(value="/member/logout")
	public String logout(HttpSession session) {
		memberService.logout(session);
		return "redirect:/";
	}
	
	// 4 회원 가입 폼  
	@GetMapping(value = "/member/addMember")
	public String addMember() {
		return "/member/addMember";
	}

	// 5 회원 가입 액션
	@PostMapping(value = "/member/addMember")
	public String addMember(Member member) {
		System.out.println("[cafe.jjdev.mall.controller.MemberController.addMember] POST member: "+member);
		memberService.addMember(member);
		return "redirect:/";
	}
	
	// 6 개인 정보 확인 
	@GetMapping(value="/member/getMember")
	public String getMember(Model model, HttpSession session) {
		// 로그인중인 사람의 정보만 보게 할것
		model.addAttribute("member", memberService.getMemberBySession(session));
		return "/member/getMember";
	}
	
	// 7 비밀번호만 수정 폼
	@GetMapping(value="/member/modifyMemberPw")
	public String modifyMemberPw(Model model, HttpSession session) {
		model.addAttribute("memberId", session.getAttribute("memberId"));
		System.out.println("[cafe.jjdev.mall.controller.MemberController.getMember] GET memberId: "+session.getAttribute("memberId"));
		return "/member/modifyMemberPw";
	}
	
	// 8 비밀번호 수정 액션
	@PostMapping(value="/member/modifyMemberPw")
	public String modifyMemberPw(Member member, @RequestParam(value = "currentMemberPw") String currentMemberPw) {
		System.out.println("[cafe.jjdev.mall.controller.MemberController.modifyMemberPw] POST member: "+member);
		System.out.println("[cafe.jjdev.mall.controller.MemberController.modifyMemberPw] POST currentMemberPw: "+currentMemberPw);
		memberService.ModifyMemberPw(member, currentMemberPw);
		return "redirect:/";
	}
	// 9 비밀번호 제외 수정 폼
	@GetMapping(value = "/member/modifyMemberExceptPw")
	public String modifyMemberExceptPw(Model model, HttpSession session) {
		model.addAttribute("member", memberService.getMemberBySession(session));	
		return "/member/modifyMemberExceptPw";
	}
	
	// 10 비밀번호 제외 수정 액션 
	@PostMapping(value = "/member/modifyMemberExceptPw")
	public String modifyMemberExceptPw(Member member) {
		System.out.println("[cafe.jjdev.mall.controller.MemberController.modifyMemberExceptPw] POST member: "+member);		
		memberService.modifyMember(member);
		return "redirect:/";
	}
	
	// 11 회원 탈퇴 폼
	@GetMapping(value = "/member/removeMember")
	public String removeMember() {
		return "/member/removeMember";
	}
	// 12 회원 탈퇴 액션 회원 탈퇴 시 id를 재사용 불가능하게 어딘가에 보관해야 한다.
	@PostMapping(value = "/member/removeMember")
	public String removeMember(Member member, HttpSession session) {
		System.out.println("[cafe.jjdev.mall.controller.MemberController.removeMember] POST member: "+member);		
		memberService.removeMember(member, session);
		return "redirect:/";
	}

	// 13 아이디 찾기 폼
	@GetMapping(value="/member/findId")
	public String findId() {
		return "/member/findId";
	}
	
	// 14 아이디 찾기 액션
	@PostMapping(value="/member/findId")
	public String findId(@RequestParam(value = "memberEmail") String memberEmail) {
		memberService.getMemberIdByEmail(memberEmail);
		return "redirect:/";
	}
	
	// 15 비밀번호 찾기 폼
	@GetMapping(value="/member/findPass")
	public String findPass() {
		return "/member/findPass";
	}
	
	// 16 비밀번호 찾기 액션
	@PostMapping(value="/member/findPass")
	public String findId(Member member) {
		memberService.getMemberPassByIdAndEmail(member);
		return "redirect:/";
	}
}
